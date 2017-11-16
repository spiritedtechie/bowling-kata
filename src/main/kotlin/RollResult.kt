sealed class RollResult(val numberOfPins: Int)

data class PinsKnockedDown(val number: Int) : RollResult(number)

object NoPinsKnockedDown: RollResult(0)

object Strike : RollResult(10)

class Spare(numberOfPinsFirstRoll: Int, val numberOfPinsSecondRoll: Int) : RollResult(numberOfPinsFirstRoll + numberOfPinsSecondRoll)