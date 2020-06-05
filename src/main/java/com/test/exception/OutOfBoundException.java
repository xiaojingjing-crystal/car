package com.test.exception;

import com.test.service.Car;

public class OutOfBoundException extends Exception {

    public OutOfBoundException(Car car){
        super("超出移动范围："
                + car.getPositionX() + "/" + car.getPositionY()
                + ", 小汽车方向 " + car.getOrientation().name());
    }

}
