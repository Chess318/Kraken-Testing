package frc.robot.subsystems;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Kraken extends SubsystemBase {

    final TalonFX leftMotorController = new TalonFX(26);
    final TalonFX rightMotorController = new TalonFX(27);
    final TalonFXConfigurator leftMotorConfigurator = leftMotorController.getConfigurator();
    final TalonFXConfigurator rightMotorConfigurator = rightMotorController.getConfigurator();
    final TalonFXConfiguration leftMotorConfiguration = new TalonFXConfiguration();
    final TalonFXConfiguration rightMotorConfiguration = new TalonFXConfiguration();

    // final CANcoder cancoder = new CANcoder(23);
    // final CANcoderConfiguration cancoderConfig = new CANcoderConfiguration();




    public Kraken(){
        leftMotorConfiguration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive; 
        leftMotorConfiguration.CurrentLimits.withStatorCurrentLimit(80);

        //rightMotorConfiguration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive; 
        rightMotorConfiguration.CurrentLimits.withStatorCurrentLimit(80);

        rightMotorConfigurator.apply(rightMotorConfiguration); 
        leftMotorConfigurator.apply(leftMotorConfiguration); 

        rightMotorController.setNeutralMode(NeutralModeValue.Brake);
        leftMotorController.setNeutralMode(NeutralModeValue.Brake);

        rightMotorController.setControl(new Follower(leftMotorController.getDeviceID(), true));


       
    }

    public Command set(double speed)
    {
        return this.run(() -> leftMotorController.setControl(new DutyCycleOut(speed))); 
        //return this.run(() -> motorController.setControl(speed)); 
    }

    //  //returns position in unit of rotations
    //  public double getPosition(){
    //     return motorController.getPosition().getValueAsDouble(); 
    // }

    // public Command setHeight(double height){
    //     return this.run (() -> motorController.setPosition(height)); 
    // } 

    public void periodic()
    {
        //SmartDashboard.putNumber("current position", getPosition());
    }


    
}
