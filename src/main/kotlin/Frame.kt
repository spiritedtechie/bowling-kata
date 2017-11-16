data class Frame(private val number: Int) {

    private val totalPins = 10;
    private var rollResults: List<Roll> = emptyList();

    private val invalidNumberOfPins = { pinsInFirstRoll: Int, pinsInSecondRoll: Int ->
        pinsInFirstRoll < 0 || pinsInSecondRoll < 0
    }

    private val rollCausesExceedingOfTotalPins = { pinsInFirstRoll: Int, pinsInSecondRoll: Int ->
        pinsInFirstRoll + pinsInSecondRoll > 10
    }

    fun rolled(firstRoll: Int, secondRoll: Int) {

        if (invalidNumberOfPins(firstRoll, secondRoll)) throw InvalidRollException()
        if (rollCausesExceedingOfTotalPins(firstRoll, secondRoll)) throw InvalidRollException()

        if (firstRoll == totalPins && secondRoll == 0) {
            addRollResult(Strike);
        } else if (firstRoll + secondRoll == 10) {
                addRollResult(Spare(firstRoll, secondRoll))
        } else if (firstRoll + secondRoll < totalPins) {
            if (firstRoll == 0) {
                addRollResult(NoPinsKnockedDown)
            } else {
                addRollResult(NormalRoll(firstRoll))
            }

            if (secondRoll == 0) {
                addRollResult(NoPinsKnockedDown)
            } else {
                addRollResult(NormalRoll(secondRoll))
            }
        }
    }

    private fun addRollResult(newRollResult: Roll) {
        rollResults += newRollResult
    }

    fun getFrameResults(): List<Roll> = rollResults
}
