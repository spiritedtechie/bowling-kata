data class Frame(val number: Int) {

    private val totalPins = 10;
    private var frameResults: List<RollResult> = emptyList();

    private val rollWasAStrike = {
        pinsKnockedDownInRoll: Int ->
        pinsKnockedDownInRoll == totalPins
    }

    private val invalidNumberOfPins = {
        pinsKnockedDownInRoll: Int ->
        pinsKnockedDownInRoll > 10 || pinsKnockedDownInRoll <= 0
    }

    private val totalPinsForPlayer = {
        frameResults
                .map(RollResult::numberOfPins)
                .sum()
    }

    private val rollCausesExceedingOfTotalPins = { pinsKnockedDown: Int -> totalPinsForPlayer() + pinsKnockedDown > 10 }

    fun rolled(pinsKnockedDown: Int): Boolean {

        if (invalidNumberOfPins(pinsKnockedDown)) {
            throw InvalidRollException();
        }

        if (rollCausesExceedingOfTotalPins(pinsKnockedDown)) {
            throw InvalidRollException();
        }

        if (rollWasAStrike(pinsKnockedDown)) {
            addNewRollResult(Strike)
        } else {
            addNewRollResult(PinsKnockedDown(pinsKnockedDown))
        }

        return true;
    }

    private fun addNewRollResult(newRollResult: RollResult) {
        frameResults += frameResults + newRollResult
    }

    fun getFrameResults(): List<RollResult> = frameResults
}
