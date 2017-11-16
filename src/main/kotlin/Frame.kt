data class Frame(val number: Int) {

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

    fun rolled(pinsKnockedDown: Int): Boolean {

        if (invalidNumberOfPins(pinsKnockedDown)) {
            throw InvalidRollException();
        }

        if (rollCausesExceedingOfTotalPins(pinsKnockedDown)) {
            throw InvalidRollException();
        }

        if (noPinsKnockedDown(pinsKnockedDown)) {
            addNewRollResult(NoPinsKnockedDown)
        } else if (wasAStrike(pinsKnockedDown)) {
            addNewRollResult(Strike)
        } else {
            addNewRollResult(PinsKnockedDown(pinsKnockedDown))
        }

        return true;
    }

    private fun addNewRollResult(newRollResult: RollResult) {
        rollResults += rollResults + newRollResult
    }

    fun getFrameResults(): List<RollResult> = rollResults
}
