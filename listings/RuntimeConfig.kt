// Reconfiguration events
expect fun cpuLoad(): Double
object HighLoad : ReconfigurationEvent<Double>() {
  override val predicate = { it > 0.90 }
  override val events: Flow<Double> = cpuLoad()
}
object LowBattery : ReconfigurationEvent<Double>() {
  override val predicate = { it < 0.20 }
  override val events: Flow<Double> = cpuLoad()
}
// Available hosts
object Smartphone : Host {
  override val hostname = "smartphone"
  override val capabilities = setOf(EmbeddedDevice)
}
object Server : Host {
  override val hostname = "amazon-aws"
  override val capabilities=setOf(HighCPU,LowLatencyComm)
}
// Runtime initial setup and runtime reconfiguration rules
val infrastructure = setOf(Smartphone, Server)
pulverizationRuntime(conf, "iot-sensor", infrastructure) {
  DeviceBehaviour() startsOn Server
  DeviceCommunication() startsOn Server
  DeviceSensor() startsOn Smartphone
  DeviceActuators() startsOn Smartphone
  reconfigurationRules {
    onDevice {
      HighLoad reconfigures{Behaviour movesTo Smartphone}
      LowBattery reconfigures {Behaviour movesTo Server}
    }
  }
}
