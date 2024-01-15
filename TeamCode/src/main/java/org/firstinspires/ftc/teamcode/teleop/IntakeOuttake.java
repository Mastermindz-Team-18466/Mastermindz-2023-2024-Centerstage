package org.firstinspires.ftc.teamcode.teleop;

public class IntakeOuttake {
    Claw claw;
    DepositHorizontalSlides deposit_horizontal_slides;
    HorizontalSlides horizontal_slides;
    Intake intake;
    VerticalSlides vertical_slides;
    Sensors sensors;
    public Instructions instruction;
    public SpecificInstructions specificInstruction;
    public SpecificInstructions previousSpecificInstruction;
    private long previous_action = System.currentTimeMillis();
    private double waitTime = 0;

    public IntakeOuttake(Sensors sensors, Claw claw, DepositHorizontalSlides deposit_horizontal_slides, HorizontalSlides horizontal_slides, Intake intake, VerticalSlides vertical_slides) {
        this.sensors = sensors;
        this.claw = claw;
        this.deposit_horizontal_slides = deposit_horizontal_slides;
        this.horizontal_slides = horizontal_slides;
        this.intake = intake;
        this.vertical_slides = vertical_slides;

        instruction = Instructions.CLOSED;
        specificInstruction = SpecificInstructions.CLOSED;
    }

    public void reset(SpecificInstructions next) {
        previous_action = System.currentTimeMillis();
        waitTime = specificInstruction.time()   ;
        specificInstruction = next;
    }

    public void reset(double time, SpecificInstructions next) {
        previous_action = System.currentTimeMillis();
        waitTime = time;
        specificInstruction = next;
    }

    public void update() {
        switch (instruction) {
            case CLOSED:
                switch (specificInstruction) {
                    case CLOSE_CLAWS:
                        Claw.close_left_claw();
                        Claw.close_right_claw();
                        reset(SpecificInstructions.RETRACT_DEPOSIT_HORIZONTAL);
                        break;
                    case RETRACT_DEPOSIT_HORIZONTAL:
                        if (System.currentTimeMillis() - previous_action > waitTime) {
                            DepositHorizontalSlides.intake();
                            reset(SpecificInstructions.TILT_CLAWS);
                        }
                        break;
                    case TILT_CLAWS:
                        if (System.currentTimeMillis() - previous_action > waitTime) {
                            Claw.intake_tilt();
                            reset(SpecificInstructions.STOP_ROLLERS);
                        }
                        break;
                    case STOP_ROLLERS:
                        if (System.currentTimeMillis() - previous_action > waitTime) {
                            Intake.stop();
                            reset(SpecificInstructions.RETRACT_HORIZONTAL);
                        }
                        break;
                    case RETRACT_HORIZONTAL:
                        if (System.currentTimeMillis() - previous_action > waitTime) {
                            HorizontalSlides.extend();
                            reset(SpecificInstructions.RETRACT_VERTICAL);
                        }
                        break;
                    case RETRACT_VERTICAL:
                        if (System.currentTimeMillis() - previous_action > waitTime) {
                            VerticalSlides.go_to_ground();
                        }
                        break;
                }
                break;

            case CLOSED_INTAKE:
                switch (specificInstruction) {
                    case SPIN_ROLLERS:
                        Intake.intake();
                        reset(SpecificInstructions.REVERSE_ROLLERS);
                        break;
                    case REVERSE_ROLLERS:
                        if (Sensors.get_left_pixel_sensor_distance() < 0.75 && Sensors.get_right_pixel_sensor_distance() < 0.75) {
                            Intake.outtake();
                            reset(2, SpecificInstructions.CLOSE_CLAWS);
                        }
                        break;
                    case CLOSE_CLAWS:
                        if (System.currentTimeMillis() - previous_action > waitTime) {
                            Claw.close_right_claw();
                            Claw.close_left_claw();
                        }
                        break;
                }
                break;

            case OPEN_INTAKE:
                switch (specificInstruction) {
                    case EXTEND_HORIZONTAL:
                        HorizontalSlides.extend();
                        reset(SpecificInstructions.SPIN_ROLLERS);
                        break;
                    case SPIN_ROLLERS:
                        if (System.currentTimeMillis() - previous_action > waitTime) {
                            Intake.intake();
                            reset(SpecificInstructions.REVERSE_ROLLERS);
                        }
                        break;
                    case REVERSE_ROLLERS:
                        if (Sensors.get_left_pixel_sensor_distance() < 0.75 && Sensors.get_right_pixel_sensor_distance() < 0.75) {
                            Intake.outtake();
                            reset(2, SpecificInstructions.RETRACT_HORIZONTAL);
                        }
                        break;
                    case RETRACT_HORIZONTAL:
                        if (System.currentTimeMillis() - previous_action > waitTime) {
                            HorizontalSlides.retract();
                            reset(SpecificInstructions.CLOSE_CLAWS);
                        }
                        break;
                    case CLOSE_CLAWS:
                        if (System.currentTimeMillis() - previous_action > waitTime) {
                            Claw.close_right_claw();
                            Claw.close_left_claw();
                        }
                        break;
                }
                break;

            case OPEN_LEFT_CLAW:
                switch (specificInstruction) {
                    case OPEN_LEFT_CLAW:
                        Claw.open_left_claw();
                        break;
                }
                break;

            case OPEN_RIGHT_CLAW:
                switch (specificInstruction) {
                    case OPEN_RIGHT_CLAW:
                        Claw.open_right_claw();
                        break;
                }
                break;

            case DEPOSIT:
                switch (specificInstruction) {
                    case EXTEND_VERTICAL:
                        VerticalSlides.go_to_low();
                        reset(SpecificInstructions.EXTEND_DEPOSIT_HORIZONTAL);
                        break;
                    case EXTEND_DEPOSIT_HORIZONTAL:
                        if (System.currentTimeMillis() - previous_action > waitTime) {
                            DepositHorizontalSlides.deposit();
                            reset(SpecificInstructions.TILT_CLAWS);
                        }
                        break;
                    case TILT_CLAWS:
                        if (System.currentTimeMillis() - previous_action > waitTime) {
                            Claw.deposit_tilt();
                        }
                        break;
                }
                break;
        }

        Claw.set();
        DepositHorizontalSlides.set();
        Intake.setIntake();
        Intake.setDropdown();
        VerticalSlides.set();
        HorizontalSlides.set();
    }

    public void setInstructions(Instructions instruction) {
        this.instruction = instruction;
    }

    public void setSpecificInstruction(SpecificInstructions specificInstruction) {
        this.specificInstruction = specificInstruction;
    }
    public enum Instructions {
        CLOSED,
        CLOSED_INTAKE,
        OPEN_INTAKE,
        DEPOSIT,
        OPEN_LEFT_CLAW,
        OPEN_RIGHT_CLAW,
    }
    public enum SpecificInstructions {
        CLOSED(0),
        SPIN_ROLLERS(0),
        REVERSE_ROLLERS(0),
        STOP_ROLLERS(0),
        EXTEND_HORIZONTAL(0),
        RETRACT_HORIZONTAL(0),
        EXTEND_DEPOSIT_HORIZONTAL(0),
        RETRACT_DEPOSIT_HORIZONTAL(0),
        CLOSE_CLAWS(0),
        TILT_CLAWS(0),
        DROPDOWN_DOWN(0),
        DROPDOWN_UP(0),
        OPEN_LEFT_CLAW(0),
        OPEN_RIGHT_CLAW(0),
        EXTEND_VERTICAL(0),
        RETRACT_VERTICAL(0);

        private final int executionTime;

        SpecificInstructions(int executionTime) {
            this.executionTime = executionTime;
        }

        public int time() {
            return executionTime;
        }
    }
}
