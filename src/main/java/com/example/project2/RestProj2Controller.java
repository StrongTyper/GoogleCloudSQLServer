package com.example.project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class RestProj2Controller {

    @Autowired
    private VehicleDao vehicleDao;
    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public Vehicle addVehicle(@RequestBody Vehicle newVehicle) {

        vehicleDao.create(newVehicle);
        return newVehicle;
    }

    @RequestMapping(value = "/getVehicle/{id}", method = RequestMethod.GET)
    public Vehicle getVehicle(@PathVariable("id") int id) {
        return vehicleDao.getById(id);
    }

    @RequestMapping(value = "/updateVehicle", method = RequestMethod.PUT)
    public Vehicle updateVehicle(@RequestBody Vehicle updatedVehicle) {

        vehicleDao.update(updatedVehicle);
        return updatedVehicle;
    }

    @RequestMapping(value = "/deleteVehicle/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") int id) {
        Vehicle vehicle = vehicleDao.find(id);
        if(vehicle != null)
        {
            vehicleDao.delete(vehicle);
            return new ResponseEntity<>("Vehicle deleted from database",HttpStatus.FOUND);
        }
        else
            return new ResponseEntity<>("Specified ID not found in database",HttpStatus.FOUND);
    }

    @RequestMapping(value = "/getLatestVehicles", method = RequestMethod.GET)
    public List<Vehicle> getLatestVehicles() throws IOException {

        List vehicles = vehicleDao.getLastTen();

        return vehicles;


    }
}