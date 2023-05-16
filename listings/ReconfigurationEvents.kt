object HighLoadOnServer : ReconfigurationEvent() {
    override val predicate = { it > 0.90 }
    override val events = flowOf { }
}
object LowBattery : ReconfigurationEvent() {
    override val predicate = { it < 0.20 }
    override val events = flowOf { }
}