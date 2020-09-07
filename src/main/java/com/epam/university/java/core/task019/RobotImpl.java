package com.epam.university.java.core.task019;

public class RobotImpl implements Robot {

    RobotPosition robotPosition = new RobotPositionImpl();
    Vision vision = Vision.PL_X;

    @Override
    public RobotPosition getPosition() {
        return robotPosition;
    }

    @Override
    public void setPosition(RobotPosition position) {
        this.robotPosition = position;
    }

    @Override
    public void invokeAction(RobotCommand command) {
        switch (command) {
            case TURN_LEFT:
                switch (vision) {
                    case PL_X:
                        this.vision = Vision.PL_Y;
                        break;
                    case PL_Y:
                        this.vision = Vision.MIN_X;
                        break;
                    case MIN_X:
                        this.vision = Vision.MIN_Y;
                        break;
                    case MIN_Y:
                        this.vision = Vision.PL_X;
                        break;
                    default:
                        break;
                }
                break;
            case TURN_RIGHT:
                switch (vision) {
                    case PL_X:
                        this.vision = Vision.MIN_Y;
                        break;
                    case PL_Y:
                        this.vision = Vision.PL_X;
                        break;
                    case MIN_X:
                        this.vision = Vision.PL_Y;
                        break;
                    case MIN_Y:
                        this.vision = Vision.MIN_X;
                        break;
                    default:
                        break;
                }
                break;
            case MOVE_FORWARD:
                switch (vision) {
                    case PL_X:
                        this.getPosition().setX(this.getPosition().getX() + 1);
                        break;
                    case PL_Y:
                        this.getPosition().setY(this.getPosition().getY() + 1);
                        break;
                    case MIN_X:
                        this.getPosition().setX(this.getPosition().getX() - 1);
                        break;
                    case MIN_Y:
                        this.getPosition().setY(this.getPosition().getY() - 1);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }
}
