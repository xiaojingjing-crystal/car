package com.test;

import com.test.etc.OutOfBoundariesException;
import com.test.entity.Command;
import com.test.entity.Direction;

public interface Car {

    Car move(Command command) throws OutOfBoundariesException;
    int getPositionX();
    int getPositionY();
    Direction getOrientation();

}
