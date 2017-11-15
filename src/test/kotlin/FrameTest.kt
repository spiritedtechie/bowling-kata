import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetUserDetailsTest {

    private lateinit var frame: Frame

    @Before
    fun setUp() {
        frame = Frame(1);
    }

    @Test
    fun shouldRecordPinsKnockedDownIfLessThan10PinsKnockedDown() {
        val player = Player("Bob")

        frame.rolled(player, 3);

        assertEquals(player.getPlayerResults(frame).last(), PinsKnockedDown(3));
    }

    @Test
    fun shouldRecordStrikeIf10PinsKnockedDownInFirstRoll() {
        val player = Player("Bob")

        frame.rolled(player, 10);

        assertTrue(player.getPlayerResults(frame).last() is Strike);
    }

    @Test(expected = InvalidRollException::class)
    fun shouldThrowInvalidRollExceptionIfInvalidNumberOfPinsOnFirstRoll_GreaterThan10() {
        val player = Player("Bob")

        frame.rolled(player, 12);
    }

    @Test(expected = InvalidRollException::class)
    fun shouldThrowInvalidRollExceptionIfInvalidNumberOfPinsOnFirstRoll_Zero() {
        val player = Player("Bob")

        frame.rolled(player, 0);
    }

    @Test(expected = InvalidRollException::class)
    fun shouldThrowInvalidRollExceptionIfInvalidNumberOfPinsOnFirstRoll_Negative() {
        val player = Player("Bob")

        frame.rolled(player, -1);
    }

    @Test(expected = InvalidRollException::class)
    fun shouldThrowInvalidRollExceptionIfRollCausesExceedingOfPinLimit() {
        val player = Player("Bob")

        frame.rolled(player, 5);
        frame.rolled(player, 6);
    }
}


