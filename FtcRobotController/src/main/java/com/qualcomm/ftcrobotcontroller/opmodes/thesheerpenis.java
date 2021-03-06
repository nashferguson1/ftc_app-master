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
public class thesheerpenis extends OpMode {

   /*
    * Note: the configuration of the servos is such that
    * as the arm servo approaches 0, the arm position moves up (away
from the floor).
    * Also, as the claw servo approaches 0, the claw opens up (drops
the game element).
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
    DcMotor lspool;
    DcMotor lift;
    DcMotor leftside;
    DcMotor rightside;
    DcMotor deposit;
    DcMotor collect;
    DcMotor spool;
    Servo swivel;
    Servo dump;

    /**
     * Constructor
     */
    public thesheerpenis() {

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
        leftside = hardwareMap.dcMotor.get("leftside");
        rightside = hardwareMap.dcMotor.get("rightside");
        collect = hardwareMap.dcMotor.get("collect");
        lift = hardwareMap.dcMotor.get("lift");
        deposit = hardwareMap.dcMotor.get("deposit");
        //collect = hardwareMap.dcMotor.get("collect");
        dump = hardwareMap.servo.get("dump");
        swivel = hardwareMap.servo.get("swivel");


        // assign the starting position of the wrist and claw
        //armPosition = 0.2;
        //clawPosition = 0.2;
        //.setPower(1);
        //armPosition = 0.2;
        //rightside.setDirection(DcMotor.Direction.REVERSE);
        leftside.setDirection(DcMotor.Direction.REVERSE);
        //leftside.setPower(1);

        // .setPower(1);

        // .setPower(1);
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

       */



        if (gamepad1.a)
        {
            dump.setPosition(.90);
        }

        if (gamepad1.b)
        {
            dump.setPosition(0);
        }

        if (gamepad1.x)
        {
            swivel.setPosition(.90);
        }

        if (gamepad1.y)
        {
            swivel.setPosition(0);
        }


        if (gamepad1.left_stick_y > .2)
        {
        leftside.setPower(-1);

        }

        if (gamepad1.left_stick_y < -.2)
        {
            leftside.setPower(1);
        }

        if (gamepad1.right_stick_y > .2)
        {
            rightside.setPower(-1);
        }

        if (gamepad1.right_stick_y < -.2)
        {
            rightside.setPower(1);
        }

        if (gamepad1.right_stick_y > -.2 && gamepad1.right_stick_y < .2)
        {
            rightside.setPower(0);

        }

        if (gamepad1.left_stick_y > -.2 && gamepad1.left_stick_y < .2)
        {
            leftside.setPower(0);
        }




        // CONTROLLER 2

        if (gamepad2.left_stick_y > .2)
        {
            lift.setPower(1);
        }


        if (gamepad2.left_stick_y < .2 && gamepad2.left_stick_y > -.2)
        {
            lift.setPower(0);
        }

        if (gamepad2.left_stick_y < -.2)
        {
            lift.setPower(-1);
        }

        if (gamepad2.right_bumper)
        {
            collect.setPower(1);
        }

        if (gamepad2.left_bumper)
        {
            collect.setPower(-1);
        }

        if (gamepad2.start)
        {
            collect.setPower(0);
        }

        /*if (gamepad2.a)
        {
            lift.setPower(0);
        }*/


        if (gamepad2.x)
        {
            deposit.setPower(1);
        }

        if (gamepad2.y)
        {
            deposit.setPower(-1);
        }

        if (gamepad2.b)
        {
            deposit.setPower(0);
        }




    }
}