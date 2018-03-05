package hu.bme.mit.train.interfaces;

public interface TrainUser {

	int getJoystickPosition();

	boolean getAlarmFlag();

	void overrideJoystickPosition(int joystickPosition, boolean buttonPressed);

	void setAlarmState(boolean b);
	
	boolean getAlarmState();

}
