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
        frame.rolled(3);

        assertEquals(frame.getFrameResults().last(), PinsKnockedDown(3));
    }

    @Test
    fun shouldRecordStrikeIf10PinsKnockedDownInFirstRoll() {
        frame.rolled(10);

        assertTrue(frame.getFrameResults().last() is Strike);
    }

    @Test(expected = InvalidRollException::class)
    fun shouldThrowInvalidRollExceptionIfInvalidNumberOfPinsOnFirstRoll_GreaterThan10() {
        frame.rolled(12);
    }

    @Test(expected = InvalidRollException::class)
    fun shouldThrowInvalidRollExceptionIfInvalidNumberOfPinsOnFirstRoll_Zero() {
        frame.rolled(0);
    }

    @Test(expected = InvalidRollException::class)
    fun shouldThrowInvalidRollExceptionIfInvalidNumberOfPinsOnFirstRoll_Negative() {
        frame.rolled(-1);
    }

    @Test(expected = InvalidRollException::class)
    fun shouldThrowInvalidRollExceptionIfRollCausesExceedingOfPinLimit() {
        frame.rolled(5);
        frame.rolled(6);
    }
}


