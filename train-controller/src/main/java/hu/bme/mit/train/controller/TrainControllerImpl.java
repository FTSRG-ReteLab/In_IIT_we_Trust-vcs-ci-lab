package hu.bme.mit.train.controller;

import java.util.Timer;
import java.util.TimerTask;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController {
	
	private Timer timer;
	
    private int step = 0;
    private int referenceSpeed = 0;
    private int speedLimit = 0;

    private Table<Long, Integer, Integer> tachograph = HashBasedTable.create();

    public Table<Long, Integer, Integer> getTachograph(){
        return tachograph;
    }

    @Override
    public void followSpeed() {
        if (referenceSpeed < 0) {
            referenceSpeed = 0;
        } else {
            if (referenceSpeed + step > 0) {
                referenceSpeed += step;
            } else {
                referenceSpeed = 0;
            }
        }
        
        enforceSpeedLimit();
        tachograph.put(System.currentTimeMillis(), step, referenceSpeed);
    }

    @Override
    public int getReferenceSpeed() {
        return referenceSpeed;
    }

    @Override
    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
        enforceSpeedLimit();
    }

    private void enforceSpeedLimit() {
        if (referenceSpeed > speedLimit) {
            referenceSpeed = speedLimit;
        }
    }
    
    @Override
    public void setJoystickPosition(int joystickPosition, boolean buttonPressed) {
    	if(timer == null){
    		timer = new Timer(true);
    		timer.scheduleAtFixedRate(new TimerTask() {
				
				@Override
				public void run() {
					followSpeed();
					
				}
			}, 10, 100);
    				
    	}
        if (buttonPressed) {
            this.step = 0;
            this.referenceSpeed = 0;
        } else {
            this.step = joystickPosition;
        }
    }
}
