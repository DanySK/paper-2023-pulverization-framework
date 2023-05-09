object HighLoadOnServer : ReconfigurationEvent() {
    override val predicate = { it > 0.90 }
    override events = flowOf { }
}

object LowBattery : ReconfigurationEvent() {
    override val predicate = { it < 0.20 }
    override events = flowOf { }
}