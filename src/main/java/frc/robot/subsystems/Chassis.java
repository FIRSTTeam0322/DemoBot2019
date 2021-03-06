package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.*;
import frc.robot.utilities.F310Controller;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class defines the chassis subsystem and all of its parts.
 */
public class Chassis extends Subsystem {
    private final WPI_TalonSRX leftFrontDriveMotor = RobotMap.chassisleftFrontDriveMotor;
    private final WPI_TalonSRX leftRearDriveMotor = RobotMap.chassisleftRearDriveMotor;
    private final SpeedControllerGroup leftSideDriveMotors = RobotMap.chassisleftSideDriveMotors;
    private final WPI_TalonSRX rightFrontDriveMotor = RobotMap.chassisrightFrontDriveMotor;
    private final WPI_TalonSRX rightRearDriveMotor = RobotMap.chassisrightRearDriveMotor;
    private final SpeedControllerGroup rightSideDriveMotors = RobotMap.chassisrightSideDriveMotors;
    private final DifferentialDrive robotDrive = RobotMap.chassisrobotDrive;

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoystick());
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    @Override
    public void periodic() {
        // Put code here to be run every loop
    	dashboardUpdater();
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void driveWithJoystick(F310Controller driveStick) {
    	robotDrive.arcadeDrive(driveStick.getY(Hand.kLeft), -(driveStick.getX(Hand.kRight)), true);
    }
    
    public void autonDriveSystem(double xSpeed, double zRotation) {
    	robotDrive.arcadeDrive(-(xSpeed), zRotation);
    }

    public void pathfinderDriveSystem(double leftSpeed, double rightSpeed) {
    	robotDrive.tankDrive(leftSpeed, rightSpeed);
    }

    public void brakesOn() {
    	leftFrontDriveMotor.setNeutralMode(NeutralMode.Brake);
        leftRearDriveMotor.setNeutralMode(NeutralMode.Brake);
        rightFrontDriveMotor.setNeutralMode(NeutralMode.Brake);
        rightRearDriveMotor.setNeutralMode(NeutralMode.Brake);
    }
    
    public void brakesOff() {
    	leftFrontDriveMotor.setNeutralMode(NeutralMode.Coast);
        leftRearDriveMotor.setNeutralMode(NeutralMode.Coast);
        rightFrontDriveMotor.setNeutralMode(NeutralMode.Coast);
        rightRearDriveMotor.setNeutralMode(NeutralMode.Coast);
    }
    
    public void resetEncoders() {
    	leftFrontDriveMotor.getSensorCollection().setQuadraturePosition(0, 0);
    	leftRearDriveMotor.getSensorCollection().setQuadraturePosition(0, 0);
    	rightFrontDriveMotor.getSensorCollection().setQuadraturePosition(0, 0);
    	rightRearDriveMotor.getSensorCollection().setQuadraturePosition(0, 0);
    }
    
    public double getEncoderData(int encoder) {
    	switch(encoder)
    	{
    		case 1:
    			return (RobotMap.ENCODER_PULSE_DISTANCE / leftFrontDriveMotor.getSensorCollection().getQuadraturePosition());
    		
    		case 2:
    			return (RobotMap.ENCODER_PULSE_DISTANCE / leftRearDriveMotor.getSensorCollection().getQuadraturePosition());
    		
    		case 3:
    			return (RobotMap.ENCODER_PULSE_DISTANCE / rightFrontDriveMotor.getSensorCollection().getQuadraturePosition());
    		
    		case 4:
    			return (RobotMap.ENCODER_PULSE_DISTANCE / rightRearDriveMotor.getSensorCollection().getQuadraturePosition());
    		
    		default:
    			return 0.0;
    	}
    }

    public int getRawEncoderData(int encoder) {
    	switch(encoder)
    	{
    		case 1:
    			return leftFrontDriveMotor.getSensorCollection().getQuadraturePosition();
    		
    		case 2:
    			return leftRearDriveMotor.getSensorCollection().getQuadraturePosition();
    		
    		case 3:
    			return rightFrontDriveMotor.getSensorCollection().getQuadraturePosition();
    		
    		case 4:
    			return rightRearDriveMotor.getSensorCollection().getQuadraturePosition();
    		
    		default:
    			return 0;
    	}
    }
    
    public void dashboardUpdater() {
    	SmartDashboard.putNumber("Left Front Distance", (double)this.getEncoderData(1));
    	SmartDashboard.putNumber("Left Rear Distance", (double)this.getEncoderData(2));
    	SmartDashboard.putNumber("Right Front Distance", (double)this.getEncoderData(3));
    	SmartDashboard.putNumber("Right Rear Distance", (double)this.getEncoderData(4));
    }
    
}
