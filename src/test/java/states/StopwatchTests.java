package states;

import states.ClockState;
import states.Context;
import states.Mode;
import states.stopwatch.AbstractStopwatch;
import states.stopwatch.ResetStopwatch;
import states.timer.AbstractTimer;
import org.junit.*;

import static org.junit.Assert.*;

public class StopwatchTests {

	private static Context context;
	private ClockState current;

	@Before
	public void setup() {
        context = new Context(); // create the state machine context
        AbstractStopwatch.resetInitialValues();
        context.currentState = AbstractStopwatch.Instance();
	}
		
	@Test
	public void testInitialState() {
		//context.tick(); //no tick() needed for this test;
		/* When initialising the context (see setup() method above)
		 * its currentState will be inialised with the initial state
		 * of timer, i.e. the IdleTimer state.
		 */
		current = context.currentState;
		
	    assertEquals(Mode.stopwatch, current.getMode());
	    assertSame(ResetStopwatch.Instance(), current);
	    assertEquals("For the value of totalTime we ",0, AbstractStopwatch.getTotalTime());
	    assertEquals("For the value of lapTime we ",0, AbstractStopwatch.getLapTime());
	}

	@Test
	public void testInitialAbstractStopwatch() {
		// The initial state of composite state AbstractStopwatch should be ResetStopwatch
		assertSame(AbstractStopwatch.Instance(), ResetStopwatch.Instance());
	}
	
	@Test
	public void testHistoryState() {		
		current = AbstractStopwatch.Instance();
		// after processing the left() event, we should arrive in the initial state of AbstractStopwatch
		ClockState newState = current.left();
		assertEquals(AbstractTimer.Instance(), newState);
		/* after another occurrence of the left() event, we should return to the original state
		 * because we used history states		
		 */
		assertEquals(current, newState.left());
	}

}
