object Smartphone : Host {
    override val hostname = "smartphone"
    override val capabilities = setOf(EmbeddedDevice)
}

object Server : Host {
    override val hostname = "server-1"
    override val capabilities = setOf(HighCPU, LowLatencyComm)
}

val availableHosts = setOf(Smartphone, Server)

pulverizationRuntime(configuration, "device", availableHosts) {
    DeviceBehaviour() startsOn Server
    DeviceState() startsOn Smartphone
}
