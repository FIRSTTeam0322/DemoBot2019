package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.*;
import com.analog.adis16448.frc.ADIS16448_IMU;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

/**
 * This class defines sensors on the chassis.
 */
public class ChassisSensors extends Subsystem {
    private final PowerDistributionPanel powerDistributionPanel = RobotMap.chassisSensorspowerDistributionPanel;
    private final ADIS16448_IMU IMU = RobotMap.chassisSensorsIMU;

    @Override
    public void initDefaultCommand() {
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
	public double getPitch() {
		return IMU.getPitch();
	}
	
	public double getRoll() {
		return IMU.getRoll();
	}
	
	public double getYaw() {
		return IMU.getYaw();
	}
	public double getAngleX() {
	    return IMU.getAngleX();
	}

	public double getAngleY() {
		return IMU.getAngleY();
	}

	public double getAngleZ() {
		return IMU.getAngleZ();
	}

	public double getAccelX() {
	    return IMU.getAccelX();
	}

	public double getAccelY() {
	    return IMU.getAccelY();
	}

	public double getAccelZ() {
	    return IMU.getAccelZ();
	}

	public double getMagX() {
	    return IMU.getMagX();
	}

	public double getMagY() {
	    return IMU.getMagY();
	}

	public double getMagZ() {
		return IMU.getMagZ();
	}

	public double getBarometricPressure() {
	    return IMU.getBarometricPressure();
	}

	public double getTemperature() {
	    return IMU.getTemperature();
	}
	
	public void dashboardUpdater() {
		SmartDashboard.putNumber("Gyro Angle X", IMU.getAngleX());
		SmartDashboard.putNumber("Gyro Angle Y", IMU.getAngleY());
		SmartDashboard.putNumber("Gyro Angle Z", IMU.getAngleZ());
		SmartDashboard.putNumber("X-Axis Acceleration", IMU.getAccelX());
		SmartDashboard.putNumber("Y-Axis Acceleration", IMU.getAccelY());
		SmartDashboard.putNumber("Z-Axis Acceleration", IMU.getAccelZ());
		SmartDashboard.putNumber("Temperature", IMU.getTemperature());
		SmartDashboard.putNumber("Pressure", IMU.getBarometricPressure());
	}
}
