import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetUserDetailsTest {

    private lateinit var frame: Frame

    @Before
    fun setUp() {
        frame = Frame(1)
    }

    @Test
    fun shouldRecordTwoRollsIfLessThan10PinsKnockedDown() {
        frame.rolled(3, 1)

        assertEquals(2, frame.getFrameResults().size)
        assertEquals(NormalRoll(3), frame.getFrameResults().first())
        assertEquals(NormalRoll(1), frame.getFrameResults().last())
    }

    @Test
    fun shouldRecordStrikeIf10PinsKnockedDownInFirstRoll() {
        frame.rolled(10, 0)

        assertEquals(1, frame.getFrameResults().size)
        assertTrue(frame.getFrameResults().first() is Strike)
    }

    @Test
    fun shouldRecordNoPinsKnockedDownIfNoPinsKnockedDownInFirstRoll() {
        frame.rolled(0, 1)

        assertEquals(2, frame.getFrameResults().size)
        assertTrue(frame.getFrameResults().first() is NoPinsKnockedDown)
        assertEquals(NormalRoll(1), frame.getFrameResults().last())
    }

    @Test
    fun shouldRecordNoPinsKnockedDownIfNoPinsKnockedDownInSecondRoll() {
        frame.rolled(1, 0)

        assertEquals(2, frame.getFrameResults().size)
        assertEquals(NormalRoll(1), frame.getFrameResults().first())
        assertTrue(frame.getFrameResults().last() is NoPinsKnockedDown)
    }

    @Test
    fun shouldRecordSpareIf10PinsKnockedDownOverTwoRolls() {
        frame.rolled(4, 6)

        assertEquals(1, frame.getFrameResults().size)
        assertEquals(Spare(4, 6), frame.getFrameResults().first())
    }

    @Test(expected = InvalidRollException::class)
    fun shouldThrowInvalidRollExceptionIfTotalPinsGreaterThan10() {
        frame.rolled(9, 2)
    }

    @Test(expected = InvalidRollException::class)
    fun shouldThrowInvalidRollExceptionIfInvalidNumberOfPinsInFirstRoll() {
        frame.rolled(-1, 1)
    }

    @Test(expected = InvalidRollException::class)
    fun shouldThrowInvalidRollExceptionIfInvalidNumberOfPinsInSecondRoll() {
        frame.rolled(1, -1)
    }

    @Test(expected = InvalidRollException::class)
    fun shouldThrowInvalidRollExceptionIfRollsExceedPinLimit() {
        frame.rolled(5, 6)
    }
}


