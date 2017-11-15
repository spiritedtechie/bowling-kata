data class Player(val name: String) {

    private val totalPins = 10;

    private var frameResults: Map<Frame, List<RollResult>> = emptyMap();

    private fun totalPinsForPlayer(frame: Frame): Int {
        return frameResults.getOrDefault(frame, emptyList())
                .map(RollResult::numberOfPins)
                .sum()
    }

    private val rollWasAStrike = {
        pinsKnockedDownInRoll: Int ->
        pinsKnockedDownInRoll == totalPins
    }

    fun rolled(frame: Frame, pinsKnockedDown: Int) {
        val rollCausesExceedingOfTotalPins = { pinsKnockedDown: Int -> totalPinsForPlayer(frame) + pinsKnockedDown > 10 }

        if (rollCausesExceedingOfTotalPins(pinsKnockedDown)) {
            throw InvalidRollException();
        }

        if (rollWasAStrike(pinsKnockedDown)) {
            addNewRollResult(frame, Strike)
        } else {
            addNewRollResult(frame, PinsKnockedDown(pinsKnockedDown))
        }
    }

    private fun addNewRollResult(frame: Frame, newRollResult: RollResult) {
        val frameRollResults = frameResults.getOrDefault(frame, emptyList())
        val updatedPlayerResults = frameRollResults + newRollResult
        setFrameRollResults(frame, updatedPlayerResults)
    }

    private fun setFrameRollResults(frame: Frame, playerResults: List<RollResult>) {
        frameResults += Pair(frame, playerResults)
    }

    fun getPlayerResults(frame: Frame) = frameResults.getOrDefault(frame, emptyList())

}