package hu.bme.mit.train.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TrainControllerImplTest {
    @Test
    public void setJoystickPosition() {
        TrainControllerImpl trainController = new TrainControllerImpl();
        trainController.setSpeedLimit(Integer.MAX_VALUE);

        
        trainController.setJoystickPosition(1000, true);
        trainController.followSpeed();
        Assert.assertEquals("Reference speed not 0 after pressing button", 0, trainController.getReferenceSpeed());


        trainController.setJoystickPosition(1000, false);
        trainController.followSpeed();
        Assert.assertNotEquals("Reference speed 0 after releasing button", 0, trainController.getReferenceSpeed());
    }

}