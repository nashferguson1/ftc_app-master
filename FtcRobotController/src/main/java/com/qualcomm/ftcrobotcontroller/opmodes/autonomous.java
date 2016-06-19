package com.qualcomm.ftcrobotcontroller.opmodes;



import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import android.graphics.Color;

/**
 * Created by Jack on 12/2/2015.
 */
public class autonomous extends LinearOpMode {
	OpticalDistanceSensor opticalDistanceSensor;
	DcMotor rightside;
	DcMotor leftside;
	Servo arm;
	ColorSensor colorSensor;
	double armPosition;
	@Override
	public void runOpMode() throws InterruptedException {


		//opticalDistanceSensor = hardwareMap.opticalDistanceSensor.get("opticalDistanceSensor");
		leftside = hardwareMap.dcMotor.get("leftside");
		rightside = hardwareMap.dcMotor.get("rightside");

		waitForStart();
		colorSensor.enableLed(true);
		//while(opticalDistanceSensor.getLightDetected() < 0.68) { //value continuously checked
		//motorLeft.setPower(-0.2);
		// motorRight.setPower(-0.2);
		/*if(colorSensor.blue() < colorSensor.red())
		{
			arm.setPosition(1);
		}

		else if(colorSensor.red() < colorSensor.blue())
		{
			arm.setPosition(0);  //Sees blue
		}*/


		if (1 > 0)
		{
			leftside.setPower(.2);
			rightside.setPower(.2);
		}

            /*else
            {
                //Sees some other color
            }

            /*motorRight.setPower(-0.3); //Only happens after robot detects white line
            sleep(2000);
            motorLeft.setPower(-0.3);
            sleep(500);
            arm.setPosition(0);
            motorRight.setPower(0);
            motorLeft.setPower(0);*/

		//telemetry.addData("Reflectance", opticalDistanceSensor.getLightDetected());
		telemetry.addData("arm", "arm:  " + String.format("%.2f", armPosition));

	}

}