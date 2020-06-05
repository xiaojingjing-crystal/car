package com.test;

import com.test.entity.CarPark;
import com.test.entity.Command;
import com.test.service.Car;
import com.test.service.impl.CarImpl;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.Scanner;

import static com.test.entity.Direction.NORTH;

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



    public static void main(String[] args) {
        CarPark carPark = new CarPark(positionX, positionY);
        Car car = new CarImpl(1, 1, NORTH, carPark);
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("/n");

        while (true){
            try {
                System.out.println("开始启动你的小汽车吧，小汽车顺时针转动，小汽车初始状态为（1,1,NORTH)，停车场大小："+positionX+"*"+positionY);
                System.out.println("小汽车是否要转移方向（0：不转动，1转动）");
                Boolean direct = sc.nextLine().equals("1") ? true : false;
                System.out.println("请输入小汽车移动的步数：");
                Integer step = Integer.valueOf(sc.nextLine());
                Command command = new Command(direct, step);
                car.move(command);
                System.out.println("小汽车的位置：x=" + car.getPositionX() + ",y=" +car.getPositionY() + ",positon=" +car.getOrientation());
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

}
