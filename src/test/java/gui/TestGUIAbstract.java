package gui;

import gui.*;
import states.Context;
import states.timer.AbstractTimer;

import org.junit.Before;

public abstract class TestGUIAbstract {

	protected Context c;
	protected TestGUI g;

    @Before
    public void setup() {
    	c = new Context();
    	g = new TestGUI(c);
    	//before each test, reset the timer values to avoid interference between tests:
    	AbstractTimer.resetInitialValues();
    }

}
