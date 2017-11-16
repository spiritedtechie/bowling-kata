data class Frame(val number: Int) {

    private val totalPins = 10;
    private var frameResults: Map<Player, List<RollResult>> = emptyMap();

    private val rollWasAStrike = {
        pinsKnockedDownInRoll: Int ->
        pinsKnockedDownInRoll == totalPins
    }

    private val invalidNumberOfPins = {
        pinsKnockedDownInRoll: Int ->
        pinsKnockedDownInRoll > 10 || pinsKnockedDownInRoll <= 0
    }

    private val totalPinsForPlayer = { player: Player ->
        frameResults
                .getOrDefault(player, emptyList())
                .map(RollResult::numberOfPins)
                .sum()
    }

    private val rollCausesExceedingOfTotalPins = { player: Player, pinsKnockedDown: Int -> totalPinsForPlayer(player) + pinsKnockedDown > 10 }

    fun rolled(player: Player, pinsKnockedDown: Int): Boolean {

        if (invalidNumberOfPins(pinsKnockedDown)) {
            throw InvalidRollException();
        }

        if (rollCausesExceedingOfTotalPins(player, pinsKnockedDown)) {
            throw InvalidRollException();
        }

        if (rollWasAStrike(pinsKnockedDown)) {
            addNewRollResult(player, Strike)
        } else {
            addNewRollResult(player, PinsKnockedDown(pinsKnockedDown))
        }

        return true;
    }

    private fun addNewRollResult(player: Player, newRollResult: RollResult) {
        val playerResults = frameResults.getOrDefault(player, emptyList())
        val updatedPlayerResults = playerResults + newRollResult
        setFrameRollResults(player, updatedPlayerResults)
    }

    private fun setFrameRollResults(player: Player, playerResults: List<RollResult>) {
        frameResults += Pair(player, playerResults)
    }

    fun getPlayerResults(player: Player): List<RollResult> = frameResults.getOrDefault(player, emptyList())
}
