package frc.robot.subsystems;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Kraken extends SubsystemBase {

    final TalonFX motorController = new TalonFX(23);
    final TalonFXConfigurator configurator = motorController.getConfigurator();
    final TalonFXConfiguration configuration = new TalonFXConfiguration();
    final MotorOutputConfigs motorConfigs = new MotorOutputConfigs();  
    final CurrentLimitsConfigs limitConfigs = new CurrentLimitsConfigs();
    final FeedbackConfigs feedbackConfigs = new FeedbackConfigs();
    // final CANcoder cancoder = new CANcoder(23);
    // final CANcoderConfiguration cancoderConfig = new CANcoderConfiguration();




    public Kraken(){
        motorConfigs.Inverted = InvertedValue.Clockwise_Positive; 

        limitConfigs.StatorCurrentLimit = 80;
        limitConfigs.StatorCurrentLimitEnable = true;

        feedbackConfigs.withSensorToMechanismRatio(4); 

        



        //applies distinct configurations to the configurator 
        configurator.apply(limitConfigs);
        configurator.apply(motorConfigs);
        configurator.apply(feedbackConfigs); 
        configurator.apply(configuration); 

       
    }

    public Command set(double speed)
    {
        return this.run(() -> motorController.setControl(new DutyCycleOut(speed))); 
        //return this.run(() -> motorController.setControl(speed)); 
    }

     //returns position in unit of rotations
     public double getPosition(){
        return motorController.getPosition().getValueAsDouble(); 
    }

    public Command setHeight(double height){
        return this.run (() -> motorController.setPosition(height)); 
    } 

    public void periodic()
    {
        SmartDashboard.putNumber("current position", getPosition());
    }


    
}
