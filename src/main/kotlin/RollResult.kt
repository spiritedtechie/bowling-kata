sealed class RollResult

data class PinsKnockedDown(val numberOfPins: Int): RollResult()

object Strike: RollResult()

object Spare : RollResult()