package com.example.project2;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

//@Component
public class MyTasks {

    RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRate = 1000)
    public void addVehicle() {
        String url = "http://localhost:8080/addVehicle";
        Random gen = new Random();
        String genName = RandomStringUtils.random(10, true, true);
        int year = gen.nextInt(31)+1986;
        int price = gen.nextInt(30001)+15000;
        Vehicle veh = new Vehicle(genName,year,price);
        restTemplate.postForObject(url,veh,Vehicle.class);
    }

    @Scheduled(fixedRate = 3000)
    public void deleteVehicle() {
        Random gen = new Random();
        int id = gen.nextInt(100)+1;
        String url = "http://localhost:8080/deleteVehicle/"+id+"";
        try {
            restTemplate.delete(url, id, Vehicle.class);
        } catch(HttpClientErrorException e) {
            System.out.println("Element ID not found for deletion");
        }
    }

    @Scheduled(fixedRate = 2000)
    public void updateVehicle() {
        String url = "http://localhost:8080/updateVehicle";
        Random gen = new Random();
        int id = gen.nextInt(100)+1;
        Vehicle veh = new Vehicle(id,"UpdateCar",9999,99999);
        restTemplate.put(url,veh,Vehicle.class);
    }

    @Scheduled(fixedRate = 3600000)
    public void getLatestVehicles() {
        String url = "http://localhost:8080/getLatestVehicles";
        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(url,Object[].class);
        Object[] objects = responseEntity.getBody();
        for(int i = 0; i < objects.length; i++)
        {
            System.out.println(objects[i].toString());
        }
    }
}
