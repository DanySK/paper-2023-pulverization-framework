object Smartphone : Host {
    override val hostname = "smartphone"
    override val capabilities =
        setOf(EmbeddedDevice)
}

object Server : Host {
    override val hostname = "server-1"
    override val capabilities =
        setOf(HighCPU, LowLatencyComm)
}

pulverizationRuntime(
    configuration,
    "device-1",
    setOf(Smartphone, Server),
) {
    DeviceBehaviour() startsOn Server
    DeviceCommunication() startsOn Server
    DeviceSensor() startsOn Smartphone
    DeviceActuators() startsOn Smartphone

    reconfigurationRules {
        onDevice {
            HighLoadOnServer reconfigures {
                Behaviour movesTo Smartphone
            }
            LowBattery reconfigures {
                Behaviour movesTo Server
            }
        }
    }
}
