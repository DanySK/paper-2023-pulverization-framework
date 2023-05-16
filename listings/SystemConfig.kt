object HighCPU : Capability
object LowLatencyComm : Capability
object EmbeddedDevice : Capability

pulverizedSystem {
  device("control-center") {
    Behavior and State deployableOn HighCPU
    Communication deployableOn LowLatencyComm
    Sensors deployableOn EmbeddedDevice
  }
  device("iot-sensor") {
    Behavior deployableOn setOf(HighCPU, EmbeddedDevice)
    Communication deployableOn LowLatencyComm
    Sensors and Actuators deployableOn EmbeddedDevice
  }
}
