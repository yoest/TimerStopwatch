package states;

import static org.junit.Assert.*;

import org.junit.*;

import states.timer.*;
import states.stopwatch.AbstractStopwatch;

public class TimerTests {

	private static Context context;
	private ClockState current;

	@Before
	public void setup() {
        context = new Context(); // create the state machine context
        AbstractTimer.resetInitialValues();
	}
		
	@Test
	public void testInitialState() {
		/* When initialising the context (see setup() method above)
		 * its currentState will be initialised with the initial state
		 * of timer, i.e. the IdleTimer state.
		 */
		current = context.currentState;
		
	    assertEquals(Mode.timer, current.getMode());
	    assertSame(IdleTimer.Instance(), current);
	    assertEquals("For the value of timer we ", 0, AbstractTimer.getTimer());
	    assertEquals("For the value of memTimer we ", 0, AbstractTimer.getMemTimer());
	}
	
	@Test
	public void testInitialAbstractTimer() {
		// The initial state of composite state AbstractTimer should be IdleTimer
		assertSame(AbstractTimer.Instance(), IdleTimer.Instance());
	}
	
	@Test
	public void testInitialActiveTimer() {
		// The initial state of composite state ActiveTimer should be RunningTimer
		assertSame(ActiveTimer.Instance(), RunningTimer.Instance());
	}
	
	@Test
	public void testHistoryState() {		
		current = AbstractTimer.Instance();
		// after processing the left() event, we should arrive in the initial state of AbstractStopwatch
		ClockState newState = current.left();
		assertEquals(AbstractStopwatch.Instance(), newState);
		/* after another occurrence of the left() event, we should return to the original state
		 * because we used history states		
		 */
		assertEquals(current, newState.left());
	}

}
