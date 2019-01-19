package frc.robot;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.analog.adis16448.frc.ADIS16448_IMU;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static WPI_TalonSRX chassisleftFrontDriveMotor;
    public static WPI_TalonSRX chassisleftRearDriveMotor;
    public static SpeedControllerGroup chassisleftSideDriveMotors;
    public static WPI_TalonSRX chassisrightFrontDriveMotor;
    public static WPI_TalonSRX chassisrightRearDriveMotor;
    public static SpeedControllerGroup chassisrightSideDriveMotors;
    public static DifferentialDrive chassisrobotDrive;
    public static PowerDistributionPanel chassisSensorspowerDistributionPanel;
    public static CANifier ledControlCANifier;
    public static ADIS16448_IMU chassisSensorsIMU;
    
    public static double autonDistance, autonRotation, autonSpeed, autonTime;
    private static final double WHEEL_DIAMETER = 6.0;
    public static final double WHEEL_DIAMETER_METRIC = 0.152;   //In Meters for FRC Pathfinder
	//private static final double CYCLES_PER_REVOLUTION = 1440.0;
	private static final double ENCODER_GEAR_RATIO = 1.0;
	private static final double GEAR_RATIO = 1.0;
    private static final double FUDGE_FACTOR = 1.0;
	public static final double ENCODER_PULSE_DISTANCE = Math.PI * WHEEL_DIAMETER / ENCODER_GEAR_RATIO / GEAR_RATIO * FUDGE_FACTOR;
    public static final double AXLE_TRACK = 0.559;              //In Meters for FRC Pathfinder
    public static final double MAX_VELOCITY = 3.8;              //In Meters per Second  ***EDIT (Maybe?)***
	
    public static double redInt, greenInt, blueInt;
    public static long ledBlinkRate;
    
    public static void init() {
        chassisleftFrontDriveMotor = new WPI_TalonSRX(1);
        chassisleftRearDriveMotor = new WPI_TalonSRX(2);
        
        chassisleftSideDriveMotors = new SpeedControllerGroup(chassisleftFrontDriveMotor, chassisleftRearDriveMotor  );
        chassisleftSideDriveMotors.setName("Chassis", "leftSideDriveMotors");
        chassisleftSideDriveMotors.setInverted(true);
        
        chassisrightFrontDriveMotor = new WPI_TalonSRX(3);
        chassisrightRearDriveMotor = new WPI_TalonSRX(4);
                
        chassisrightSideDriveMotors = new SpeedControllerGroup(chassisrightFrontDriveMotor, chassisrightRearDriveMotor  );
        chassisrightSideDriveMotors.setName("Chassis", "rightSideDriveMotors");
        chassisrightSideDriveMotors.setInverted(true);
        
        chassisrobotDrive = new DifferentialDrive(chassisleftSideDriveMotors, chassisrightSideDriveMotors);
        chassisrobotDrive.setName("Chassis", "robotDrive");
        chassisrobotDrive.setSafetyEnabled(true);
        chassisrobotDrive.setExpiration(5.0);
        chassisrobotDrive.setMaxOutput(1.0);

        
        chassisSensorspowerDistributionPanel = new PowerDistributionPanel(0);
        chassisSensorspowerDistributionPanel.setName("ChassisSensors", "powerDistributionPanel");

        ledControlCANifier = new CANifier(0);

        chassisSensorsIMU = new ADIS16448_IMU();
        chassisSensorsIMU.setName("ChassisSensors", "IMU");
        chassisSensorsIMU.calibrate();
        
        RobotMap.autonDistance = 36.0;
        RobotMap.autonSpeed = 0.5;
        RobotMap.autonRotation = 0.0;
        RobotMap.autonTime = 5.0;
        
        redInt = 100.0;
        greenInt = 100.0;
        blueInt = 100.0;
        ledBlinkRate = 500;
    }
}
