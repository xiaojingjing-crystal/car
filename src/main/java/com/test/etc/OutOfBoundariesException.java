package com.test.etc;

import com.test.Car;

public class OutOfBoundariesException extends Exception {

    public OutOfBoundariesException(Car car){
        super("OutOfBoundaries at"
                + car.getPositionX() + "/" + car.getPositionY()
                + ", heading " + car.getOrientation().name());
    }

}
