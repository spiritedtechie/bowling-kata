data class Frame(private val number: Int) {

    private val totalPins = 10;
    private var rollResults: List<RollResult> = emptyList();

    private val wasAStrike = {
        pinsKnockedDownInRoll: Int ->
        pinsKnockedDownInRoll == totalPins
    }

    private val noPinsKnockedDown = {
        pinsKnockedDownInRoll: Int -> pinsKnockedDownInRoll == 0
    }

    private val invalidNumberOfPins = {
        pinsKnockedDownInRoll: Int ->
        pinsKnockedDownInRoll > 10 || pinsKnockedDownInRoll < 0
    }

    private val totalPinsSoFar = {
        rollResults
                .map(RollResult::numberOfPins)
                .sum()
    }

    private val rollCausesExceedingOfTotalPins = { pinsKnockedDown: Int -> totalPinsSoFar() + pinsKnockedDown > 10 }

    fun rolled(numberOfPins: Int) {

        if (invalidNumberOfPins(numberOfPins)) throw InvalidRollException()
        if (rollCausesExceedingOfTotalPins(numberOfPins)) throw InvalidRollException()

        if (noPinsKnockedDown(numberOfPins)) {
            addRollResult(NoPinsKnockedDown)
        } else if (wasAStrike(numberOfPins)) {
            addRollResult(Strike)
        } else {
            addRollResult(PinsKnockedDown(numberOfPins))
        }
    }

    private fun addRollResult(newRollResult: RollResult) {
        rollResults += rollResults + newRollResult
    }

    fun getFrameResults(): List<RollResult> = rollResults
}
