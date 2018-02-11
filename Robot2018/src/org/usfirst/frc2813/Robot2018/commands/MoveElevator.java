// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2813.Robot2018.commands;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2813.Robot2018.Robot;

/**
 *
 */
public class MoveElevator extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
	private Encoder quadratureEncoder1;
	private DigitalInput digitalInput1;
	private SpeedController speedController1;
	private static final double DESIRED_ENCODER_VALUE = 10;//the desired encoder value to stop at top TODO replace with desired encoder value
	private boolean direction;
    public MoveElevator(boolean upDown) {//if moving up, true; down, false
    	direction=upDown;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.elevator);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    //@Override
    protected void initialize() {
    	quadratureEncoder1 = Robot.elevator.quadratureEncoder1;
    	digitalInput1 = Robot.elevator.digitalInput1;
    	speedController1 = Robot.elevator.speedController1;
    }

    // Called repeatedly when this Command is scheduled to run
    //@Override
    protected void execute() {
    	if(direction == true) {
    		speedController1.set(1);//TODO not sure if 1 or -1 is up
    	}
    	else if (direction == false) {
    		speedController1.set(-1);//TODO not sure if -1 or 1 is down
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    //@Override
    protected boolean isFinished() {
    	if (digitalInput1.get()==true) {//if limit switch has been pressed, reset the encoder
    		quadratureEncoder1.reset();
    	}
        return quadratureEncoder1.getDistance()>DESIRED_ENCODER_VALUE || digitalInput1.get()==true;//If limit switch pressed OR desired encoder value is reached, STOP
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
