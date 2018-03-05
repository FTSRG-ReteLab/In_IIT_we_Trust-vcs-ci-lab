package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.user.TrainUserImpl;

import static org.mockito.Mockito.*;

public class TrainSensorTest {

	private TrainSensor sensor;
	private MockTrainController controller;
	private TrainUserImpl user;
	
	private class MockTrainController implements TrainController{

		@Override
		public void followSpeed() {
			
		}
		
		private int referenceSpeed;

		@Override
		public int getReferenceSpeed() {
			return referenceSpeed;
		}
		
		public void setReferenceSpeed(int speed){
			referenceSpeed = speed;
		}

		@Override
		public void setSpeedLimit(int speedLimit) {
			
		}

		@Override
		public void setJoystickPosition(int joystickPosition, boolean buttonPressed) {
			
		}
		
	}
	
    @Before
    public void before() {
    	controller = new MockTrainController();
    	user = new TrainUserImpl(controller);
        sensor = new TrainSensorImpl(controller, user);
    }

    @Test
    public void TestNoAlarm() {
    	Assert.assertFalse(user.getAlarmState());
        controller.setReferenceSpeed(250);
        sensor.overrideSpeedLimit(250);
    	Assert.assertFalse(user.getAlarmState());
    }

    @Test
    public void TestNegSpeed() {
    	Assert.assertFalse(user.getAlarmState());
        controller.setReferenceSpeed(250);
        sensor.overrideSpeedLimit(-250);
    	Assert.assertTrue(user.getAlarmState());
    }

    @Test
    public void TestBigSpeed() {
    	Assert.assertFalse(user.getAlarmState());
        controller.setReferenceSpeed(250);
        sensor.overrideSpeedLimit(5000);
    	Assert.assertTrue(user.getAlarmState());
    }

    @Test
    public void TestNullSpeed() {
    	Assert.assertFalse(user.getAlarmState());
        controller.setReferenceSpeed(250);
        sensor.overrideSpeedLimit(120);
    	Assert.assertTrue(user.getAlarmState());
    }
}
