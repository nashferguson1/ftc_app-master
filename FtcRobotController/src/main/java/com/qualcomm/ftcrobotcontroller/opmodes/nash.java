/* Copyright (c) 2014 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * TeleOp Mode
 * <p>
 * Enables control of the robot via the gamepad
 */
public class   nash extends OpMode {

	/*
	 * Note: the configuration of the servos is such that
	 * as the arm servo approaches 0, the arm position moves up (away from the floor).
	 * Also, as the claw servo approaches 0, the claw opens up (drops the game element).
	 */
	// TETRIX VALUES.
	//final static double ARM_MIN_RANGE  = 0.20;
	//final static double ARM_MAX_RANGE  = 0.90;
	//final static double CLAW_MIN_RANGE  = 0.20;
	//final static double CLAW_MAX_RANGE  = 0.7;

	// position of the arm servo.
	//double armPosition;

	// amount to change the arm servo position.
	//double armDelta = 0.1;

	// position of the claw servo
	//double clawPosition;

	// amount to change the claw servo position by
	//double clawDelta = 0.1;
	double increment = 0.1;
	double binposition;
	double leftclimberposition;
	double rightclimberposition;
	DcMotor motorbackright;
	DcMotor motorfrontright;
	DcMotor motorfrontleft;
	DcMotor motorbackleft;
	DcMotor topdebris;
	DcMotor middebris;
	DcMotor bottomlifter;
	DcMotor midlifter;
	Servo leftclimber;
	Servo bin;
	Servo rightclimber;

	/**
	 * Constructor
	 */
	public nash() {

	}

	/*
	 * Code to run when the op mode is first enabled goes here
	 *
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
	 */
	@Override
	public void init() {


		/*
		 * Use the hardwareMap to get the dc motors and servos by name. Note
		 * that the names of the devices must match the names used when you
		 * configured your robot and created the configuration file.
		 */

		/*
		 * For the demo Tetrix K9 bot we assume the following,
		 *   There are two motors "motor_1" and "motor_2"
		 *   "motor_1" is on the right side of the bot.
		 *   "motor_2" is on the left side of the bot and reversed.
		 *
		 * We also assume that there are two servos "servo_1" and "servo_6"
		 *    "servo_1" controls the arm joint of the manipulator.
		 *    "servo_6" controls the claw joint of the manipulator.
		 */
		//motorRight = hardwareMap.dcMotor.get("motor_2");
		//motorLeft = hardwareMap.dcMotor.get("motor_1");
		//motorLeft.setDirection(DcMotor.Direction.REVERSE);
		motorbackright = hardwareMap.dcMotor.get("motor1");
		motorbackleft = hardwareMap.dcMotor.get("motor2");
		motorfrontleft = hardwareMap.dcMotor.get("motor3");
		motorfrontright = hardwareMap.dcMotor.get("motor4");
		middebris = hardwareMap.dcMotor.get("motor5");
		midlifter = hardwareMap.dcMotor.get("motor6");
		topdebris = hardwareMap.dcMotor.get("motor9");
		bottomlifter = hardwareMap.dcMotor.get("motor10");
		leftclimber = hardwareMap.servo.get("motor7");
		rightclimber = hardwareMap.servo.get("motor8");
		bin = hardwareMap.servo.get("motor11");
		motorbackleft.setDirection(DcMotor.Direction.REVERSE);
		motorfrontleft.setDirection(DcMotor.Direction.REVERSE);
		//motorfrontright.setDirection(DcMotor.Direction.REVERSE);
		motorbackright.setDirection(DcMotor.Direction.REVERSE);
		//arm = hardwareMap.servo.get("servo_1");
		//claw = hardwareMap.servo.get("servo_6");

		// assign the starting position of the wrist and claw

		//motorfrontleft.setPower(1);
		//armPosition = 0.2;

		//motorfrontright.setPower(1);

		//motorbackleft.setPower(1);

		//motorbackright.setPower(1);
		//clawPosition = 0.2;
	}

	/*
	 * This method will be called repeatedly in a loop
	 *
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#run()
	 */
	@Override
	public void loop() {

		/*
		 * Gamepad 1
		 *
		 * Gamepad 1 controls the motors via the left stick, and it controls the
		 * wrist/claw via the a,b, x, y buttons
		 */

		// throttle: left_stick_y ranges from -1 to 1, where -1 is full up, and
		// 1 is full down
		// direction: left_stick_x ranges from -1 to 1, where -1 is full left
		// and 1 is full right
		float throttle = -gamepad1.left_stick_y;
		float direction = gamepad1.right_stick_y;
		float right = throttle;
		float left = direction;

		right = (float) scaleInputSimple(right);
		left = (float) scaleInputSimple(left);
		//float direction = gamepad1.left_stick_x;
		//float right = throttle;
		//float left = throttle + direction;
		motorbackleft.setPower(left);
		motorfrontright.setPower(right);
		motorbackright.setPower(right);
		motorfrontleft.setPower(left);


		// scale the joystick value to make it easier to control
		// the robot more precisely at slower speeds.
		//right = (float)scaleInput(right);
		//left =  (float)scaleInput(leftt);
		//right = (float)scaleInput(right);
		//left =  (float)scaleInput(left);


		// update the position of the arm.

		/*if (gamepad1.a) {
			// if the A button is pushed on gamepad1, increment the position of
			// the bin servo.
			//armPosition += armDelta;
			binposition += increment;

		}

		if (gamepad1.y) {
			// if the Y button is pushed on gamepad1, decrease the position of
			// the bin servo.
			//armPosition -= armDelta;
			binposition -= increment;
		}*/

		// update the position of the claw

		if (gamepad1.a) {
			// if the A button is pushed on gamepad1, increment the position of
			// the arm servo.
			//armPosition += armDelta;
			leftclimberposition += increment;
		}

		if (gamepad1.y) {
			// if the Y button is pushed on gamepad1, decrease the position of
			// the arm servo.
			//armPosition -= armDelta;
			leftclimberposition -= increment;
		}


		if (gamepad1.left_stick_y > 0) {
			//clawPosition += clawDelta;
			motorbackright.setPower(1);
			motorfrontright.setPower(1);
			motorfrontleft.setPower(1);
			motorbackleft.setPower(1);
		}

		if (gamepad1.left_stick_y < 0) {
			//clawPosition -= clawDelta;
			motorbackright.setPower(-1);
			motorfrontright.setPower(-1);
			motorfrontleft.setPower(-1);
			motorbackleft.setPower(-1);
		}

		if (gamepad1.left_stick_x > 0)
		{
			motorbackleft.setPower(1);
			motorfrontleft.setPower(1);
		}

		if (gamepad1.left_stick_x < 0){
			motorbackleft.setPower(-1);
			motorfrontleft.setPower(-1);
		}

		if (gamepad1.right_stick_y > 0)
		{
			middebris.setPower(1);
		}

		if (gamepad1.right_stick_y < 0)
		{
			middebris.setPower(-1);
		}

		/*if (1 > 0)
		{
			motorbackright.setPower(1);
			motorbackleft.setPower(1);
			motorfrontright.setPower(1);
			motorfrontleft.setPower(1);
		}*/


//
		//
		//
		//
		//
		//
		//GAME CONTROLLER TWO STARTS HERE
		//
		//
		//
		//


		// update the position of the claw
		if (gamepad2.x) {
			//clawPosition += clawDelta;
			rightclimberposition += increment;
		}

		if (gamepad2.b) {
			//clawPosition -= clawDelta;
			rightclimberposition -= increment;
		}


		if (gamepad2.a) {
			// if the A button is pushed on gamepad1, increment the position of
			// the bin servo.
			//armPosition += armDelta;
			binposition += increment;

		}

		if (gamepad2.y) {
			// if the Y button is pushed on gamepad1, decrease the position of
			// the bin servo.
			//armPosition -= armDelta;
			binposition -= increment;
		}

		// clip the position values so that they never exceed their allowed range.
		//armPosition = Range.clip(armPosition, ARM_MIN_RANGE, ARM_MAX_RANGE);
		//clawPosition = Range.clip(clawPosition, CLAW_MIN_RANGE, CLAW_MAX_RANGE);

		// write position values to the wrist and claw servo
		//arm.setPosition(armPosition);
		//claw.setPosition(clawPosition);



		/*
		 * Send telemetry data back to driver station. Note that if we are using
		 * a legacy NXT-compatible motor controller, then the getPower() method
		 * will return a null value. The legacy NXT-compatible motor controllers
		 * are currently write only.
		 */
		//telemetry.addData("Text", "*** Robot Data***");
		//telemetry.addData("arm", "arm:  " + String.format("%.2f", armPosition));
		//telemetry.addData("claw", "claw:  " + String.format("%.2f", clawPosition));
		//telemetry.addData("left tgt pwr",  "left  pwr: " + String.format("%.2f", left));
		//telemetry.addData("right tgt pwr", "right pwr: " + String.format("%.2f", right));

	}

	/*
	 * Code to run when the op mode is first disabled goes here
	 *
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
	 */
	@Override
	public void stop() {

	}


	/*
	 * This method scales the joystick input so for low joystick values, the
	 * scaled value is less than linear.  This is to make it easier to drive
	 * the robot more precisely at slower speeds.
	 */
	private double scaleInputSimple(double pwr) {
		if (pwr > 0.0) {
			if (pwr < 0.05) //0 PWR on chart
				return 0.0;
			else if (pwr >= 0.05 && pwr < 0.10) //0.05 on chart
				return 0.01;
			else if (pwr >= 0.10 && pwr < 0.15) //0.10 on chart
				return 0.02;
			else if (pwr >= 0.15 && pwr < 0.20) //0.15 on chart
				return 0.03;
			else if (pwr >= 0.20 && pwr < 0.25) //0.20 on chart
				return 0.04;
			else if (pwr >= 0.25 && pwr < 0.30) //0.25 on chart
				return 0.05;
			else if (pwr >= 0.30 && pwr < 0.35) //0.30 on chart
				return 0.06;
			else if (pwr >= 0.35 && pwr < 0.40) //0.35 on chart
				return 0.07;
			else if (pwr >= 0.40 && pwr < 0.45) //0.40 on chart
				return 0.075;
			else if (pwr >= 0.45 && pwr < 0.50) //0.45 on chart
				return 0.08;
			else if (pwr >= 0.50 && pwr < 0.55) //0.50 on chart
				return 0.09;
			else if (pwr >= 0.55 && pwr < 0.60) //0.55 on chart
				return 0.10;
			else if (pwr >= 0.60 && pwr < 0.65) //0.60 on chart
				return 0.113;
			else if (pwr >= 0.65 && pwr < 0.70) //0.65 on chart
				return 0.126;
			else if (pwr >= 0.70 && pwr < 0.75) //0.70 on chart
				return 0.14;
			else if (pwr >= 0.75 && pwr < 0.80) //0.75 on chart
				return 0.15;
			else if (pwr >= 0.80 && pwr < 0.85) //0.80 on chart
				return 0.19;
			else if (pwr >= 0.85 && pwr < 0.90) //0.85 on chart
				return 0.225;
			else
				return 1.0;
		} else {
			if (pwr > -0.05) //0 PWR on chart
				return 0.0;
			else if (pwr <= -0.05 && pwr > -0.10) //0.05 on chart
				return -0.01;
			else if (pwr <= -0.10 && pwr > -0.15) //0.10 on chart
				return -0.02;
			else if (pwr <= -0.15 && pwr > -0.20) //0.15 on chart
				return -0.03;
			else if (pwr <= -0.20 && pwr > -0.25) //0.20 on chart
				return -0.04;
			else if (pwr <= -0.25 && pwr > -0.30) //0.25 on chart
				return -0.05;
			else if (pwr <= -0.30 && pwr > -0.35) //0.30 on chart
				return -0.06;
			else if (pwr <= -0.35 && pwr > -0.40) //0.35 on chart
				return -0.07;
			else if (pwr <= -0.40 && pwr > -0.45) //0.40 on chart
				return -0.075;
			else if (pwr <= -0.45 && pwr > -0.50) //0.45 on chart
				return -0.08;
			else if (pwr <= -0.50 && pwr > -0.55) //0.50 on chart
				return -0.09;
			else if (pwr <= -0.55 && pwr > -0.60) //0.55 on chart
				return -0.10;
			else if (pwr <= -0.60 && pwr > -0.65) //0.60 on chart
				return -0.113;
			else if (pwr <= -0.65 && pwr > -0.70) //0.65 on chart
				return -0.126;
			else if (pwr <= -0.70 && pwr > -0.75) //0.70 on chart
				return -0.14;
			else if (pwr <= -0.75 && pwr > -0.80) //0.75 on chart
				return -0.15;
			else if (pwr <= -0.80 && pwr > -0.85) //0.80 on chart
				return -0.19;
			else if (pwr <= -0.85 && pwr > -0.90) //0.85 on chart
				return -0.225;
			else
				return -1.0;
		}

	}
}