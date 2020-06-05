package com.test;



import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.util.Properties;
import java.util.Scanner;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.Resource;


@Component
public class TestMoveCar {
    private static int x = 0;
    private static int y = 0;

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
//            e.printStackTrace();
            return null;
        }
        return properties.get(key);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static void move(String command)throws Exception{
        TestMoveCar car = new TestMoveCar();
        if(command.equals("N")){
            if(car.getY() == positionY){
                throw new Exception("超出运行范围");
            }
            car.setY(car.getY() +1);
        }
        if(command.equals("S")){
            if(car.getY() == 0){
                throw new Exception("超出运行范围");
            }
            car.setY(car.getY() -1);
        }

        if(command.equals("E")){
            if(car.getX() == positionX){
                throw new Exception("超出运行范围");
            }
            car.setX(car.getX() +1);
        }
        if(command.equals("W")){
            if(car.getX() == 0){
                throw new Exception("超出运行范围");
            }
            car.setX(car.getX() -1);
        }
        System.out.println("小汽车坐标："+ "x:" + car.getX() + ",y:"+car.getY());
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("/n");
        System.out.println("开始启动你的小汽车吧，N：向前开 S：向后退  W：向左开  E向右开：");
        while (true){
            try {
                String command = sc.nextLine();
                move(command);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
