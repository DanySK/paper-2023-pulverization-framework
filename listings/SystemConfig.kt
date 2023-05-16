object HighCPU : Capability
object LowLatencyComm : Capability
object EmbeddedDevice : Capability

pulverizedSystem {
  device("control-center") {
    Behaviour and State deployableOn HighCPU
    Communication deployableOn LowLatencyComm
    Sensors deployableOn EmbeddedDevice
  }
  device("iot-sensor") {
    Behaviour deployableOn setOf(HighCPU, EmbeddedDevice)
    Communication deployableOn LowLatencyComm
    Sensors and Actuators deployableOn EmbeddedDevice
  }
}
