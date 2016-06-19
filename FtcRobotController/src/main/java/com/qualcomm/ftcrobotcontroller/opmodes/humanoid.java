package com.qualcomm.ftcrobotcontroller.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class humanoid extends OpMode {
    double position = 0;
    Servo s1;
    Servo s2;
    Servo s3;
    Servo s4;
    Servo s5;
    Servo s6;
    Servo s7;
    Servo s8;
    DcMotor ls;
    DcMotor rs;

    public humanoid()
    {

    }
    @Override
    public void init()
    {
        s1 = hardwareMap.servo.get("sone");
        s2 = hardwareMap.servo.get("stwo");
        s3 = hardwareMap.servo.get("sthree");
        s4 = hardwareMap.servo.get("sfour");
        s5 = hardwareMap.servo.get("sfive");
        s6 = hardwareMap.servo.get("ssix");
        s7 = hardwareMap.servo.get("sseven");
        s8 = hardwareMap.servo.get("seight");
        ls = hardwareMap.dcMotor.get("leftshoulder");
        rs = hardwareMap.dcMotor.get("rightshoulder");

    }
    @Override
    public void loop()
    {
        int t = 0;
        int x = 0;
        double s1position = 0;
        double s2position = 0;
        double s3position = 0;
        double s4position = 0;
        double s5position = 0;
        double s6position = 0;
        double s7position = 0;
        double s8position = 0;


       if (gamepad1.back)
       {
           if (gamepad1.a) {
               s1.setPosition(s1position + .1);
                s1position = s1position + .1;
           }

           if (gamepad1.b) {
               s1.setPosition(s1position - .1);
               s1position = s1position - .1;

           }

           if (gamepad1.x) {
               s3.setPosition(s3position + .1);
               s3position = s3position + .1;
           }

           if (gamepad1.y) {
               s3.setPosition(s3position - .1);
               s3position = s3position - .1;
           }

           if (gamepad1.dpad_left)
           {
               s2.setPosition(s2position + .1);
               s2position = s2position + .1;
           }

           if (gamepad1.dpad_right)
           {
               s2.setPosition(s2position - .1);
               s2position = s2position - .1;
           }

           if (gamepad1.left_stick_button)
           {
               s4.setPosition(1);
           }

           if (gamepad1.right_stick_button)
           {
               s4.setPosition(0);
           }

       }

        if (gamepad1.start)
        {
            if (gamepad1.a)
            {
                s5.setPosition(s5position + .1);
                s5position = s5position + .1;
            }

            if (gamepad1.b)
            {
                s5.setPosition(s5position - .1);
                s5position = s5position - .1;
            }

            if (gamepad1.x)
            {
                s7.setPosition(s7position + .1);
                s7position = s7position + .1;
            }

            if (gamepad1.y)
            {
                s7.setPosition(s7position - .1);
                s7position = s7position - .1;
            }

            if (gamepad1.dpad_left)
            {
                s6.setPosition(position + .1);
                s6position = s6position + .1;
            }

            if (gamepad1.dpad_right)
            {
                s6.setPosition(position - .1);
                s6position = s6position - .1;
            }

            if (gamepad1.left_stick_button)
            {
                s8.setPosition(1);
            }

            if (gamepad1.right_stick_button)
            {
                s8.setPosition(0);
            }
        }

    }

}
