object HighCPU : Capability
object LowLatencyComm : Capability
object EmbeddedDevice : Capability

val confgiuration = pulverizationSystem {
    device("device-1") {
        Behaviour and State deployableOn HighCPU
        Communication deployableOn LowLatencyComm
        Sensors deployableOn EmbeddedDevice
    }
    device("device-2") {
        Behaviour deployableOn
            setOf(HighCPU, EmbeddedDevice)
        Communication deployableOn LowLatencyComm
        Sensors and Actuators deployableOn
            EmbeddedDevice
    }
}
