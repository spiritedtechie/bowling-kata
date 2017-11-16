sealed class Roll(numberOfPins: Int)

data class NormalRoll(val numberOfPins: Int): Roll(numberOfPins)

object NoPinsKnockedDown: Roll(0)

object Strike : Roll(10)

data class Spare(val numberOfPinsFirstRoll: Int,  val numberOfPinsSecondRoll: Int) : Roll(numberOfPinsFirstRoll + numberOfPinsSecondRoll)