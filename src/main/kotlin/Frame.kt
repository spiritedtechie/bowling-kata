data class Frame(val number: Int) {

    val totalPins = 10;

    private val invalidNumberOfPins = {
        pinsKnockedDownInRoll: Int ->
        pinsKnockedDownInRoll > 10 || pinsKnockedDownInRoll <= 0
    }

    fun rolled(player: Player, pinsKnockedDown: Int): Boolean {
        if (invalidNumberOfPins(pinsKnockedDown)) {
            throw InvalidRollException();
        }

        player.rolled(this, pinsKnockedDown)

        return true;
    }
}
