package com.test.service.impl;

import com.test.entity.CarPark;
import com.test.exception.OutOfBoundException;
import com.test.entity.Command;
import com.test.entity.Direction;
import com.test.service.Car;
import org.springframework.stereotype.Service;

@Service
public class CarImpl implements Car {

    public CarImpl(int x, int y, Direction direction, CarPark carPark) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.carPark = carPark;
    }

    private int x;
    private int y;
    private Direction direction;
    private CarPark carPark;

    public Car move(Command command) throws OutOfBoundException {
        if(command.getClockWise()){
            turnClockWise();
        }
        //move
        if(command.getStep() != null && command.getStep() > 0){
            for (int i = 0; i < command.getStep(); i++) {
                moveOneStep();
            }
        }
        return this;
    }

    private void turnClockWise(){
        switch (direction){
            case NORTH: direction = Direction.EAST;break;
            case EAST: direction = Direction.SOUTH;break;
            case SOUTH: direction = Direction.WEST;break;
            case WEST: direction = Direction.NORTH;break;
        }
    }

    private void moveOneStep() throws OutOfBoundException {
        switch (direction){
            case EAST: x++;break;
            case WEST: x--;break;
            case NORTH: y++;break;
            case SOUTH: y--;break;
        }
        //check
        if (x < 1 || y < 1 || x > carPark.getX() || y > carPark.getY()){
            throw new OutOfBoundException(this);
        }

    }

    public int getPositionX() {
        return x;
    }

    public int getPositionY() {
        return y;
    }

    public Direction getOrientation() {
        return direction;
    }

}
