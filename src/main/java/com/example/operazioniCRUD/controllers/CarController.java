package com.example.operazioniCRUD.controllers;

import com.example.operazioniCRUD.entities.Car;
import com.example.operazioniCRUD.servicies.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController{
    @Autowired
    CarService service;
    @PostMapping("/createcar")
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        Car savedCar = service.addCar(car);
        return ResponseEntity.ok().body(savedCar);
    }
    @GetMapping("/cars")
    public ResponseEntity<List<Car>> listOfCars(){
        return ResponseEntity.ok().body(service.listOfCars());
    }
    @GetMapping("/car/{id}")
    public ResponseEntity<Car> getCar(@PathVariable Long id){
        Optional<Car> findedCarOptional = service.getCar(id);
        if(findedCarOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok().body(findedCarOptional.get());
    }
    @PutMapping("/updatetype/{id}")
    public ResponseEntity<Car> updateType(
            @PathVariable Long id,
            @RequestBody Car newCarType){
        Optional<Car> changedTypeCarOptional = service.typeUpdate(id,newCarType);
        if(changedTypeCarOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(changedTypeCarOptional.get());
    }
    @DeleteMapping("/deletecar")
    public ResponseEntity<Car> deleteCar(@RequestBody Car car){
        Optional<Car> carToDeleteOptional = service.deleteCar(car);
        if(carToDeleteOptional.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(carToDeleteOptional.get());
    }
    @DeleteMapping("/deletecars")
    public ResponseEntity<List<Car>> deleteCars(){
        return ResponseEntity.ok().body(service.deleteCars());
    }

}
