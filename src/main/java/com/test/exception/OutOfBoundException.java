package com.test.exception;

import com.test.service.Car;

public class OutOfBoundException extends Exception {

    public OutOfBoundException(Car car){
        super("OutOfBoundaries at"
                + car.getPositionX() + "/" + car.getPositionY()
                + ", heading " + car.getOrientation().name());
    }

}
