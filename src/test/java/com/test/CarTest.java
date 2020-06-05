package com.test;

import com.test.entity.CarPark;
import com.test.entity.Command;
import com.test.entity.Direction;
import com.test.exception.OutOfBoundException;
import com.test.service.Car;
import com.test.service.impl.CarImpl;
import org.junit.Test;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.Scanner;

import static com.test.entity.Direction.*;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@Component
public class CarTest {
    private static int positionX = Integer.parseInt(getCommonYml("positionX","application.yml").toString());
    private static int positionY = Integer.parseInt(getCommonYml("positionY","application.yml").toString());


    public static Object getCommonYml(String key,String proName){
        Resource resource = new ClassPathResource(proName);
        Properties properties = null;
        try {
            YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
            yamlFactory.setResources(resource);
            properties =  yamlFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return properties.get(key);
    }

    CarPark carPark = new CarPark(positionX, positionY);

    private void assertCar(Car car, int x, int y, Direction direction){
        assertEquals(direction, car.getOrientation());
        assertEquals(x, car.getPositionX());
        assertEquals(y, car.getPositionY());
    }

    @Test
    public void criteriaOne() throws OutOfBoundException {
        Car car = new CarImpl(1, 1, NORTH, carPark);
        car.move(new Command(true, 0));
        assertCar(car,1, 1, EAST);
    }

    @Test
    public void criteriaTwo() throws OutOfBoundException {
        Car car = new CarImpl(1, 1, NORTH, carPark);
        car.move(new Command(false, 1));
        assertCar(car,1, 2, NORTH);
    }

    @Test
    public void criteriaThree() throws OutOfBoundException {
        Car car = new CarImpl(1, 1, EAST, carPark);
        car.move(new Command(false, 1));
        assertCar(car,2, 1, EAST);
    }

    @Test
    public void criteriaFour(){
        Car car = new CarImpl(1, 1, WEST, carPark);
        OutOfBoundException ex = null;
        try {
            car.move(new Command(false, 1));
        }catch (OutOfBoundException e){
            ex = e;
        }
        assertNotNull(ex);
    }

    @Test
    public void criteriaFive() throws OutOfBoundException {
        Car car = new CarImpl(1, 1, EAST, carPark);
        car.move(new Command(false, 2));
        assertCar(car,3, 1, EAST);
    }

}
