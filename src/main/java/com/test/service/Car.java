package com.test.service;

import com.test.entity.Command;
import com.test.entity.Direction;
import com.test.exception.OutOfBoundException;

public interface Car {

    Car move(Command command) throws OutOfBoundException;
    int getPositionX();
    int getPositionY();
    Direction getOrientation();

}
