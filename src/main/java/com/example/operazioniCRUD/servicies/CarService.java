package com.example.operazioniCRUD.servicies;

import com.example.operazioniCRUD.entities.Car;
import com.example.operazioniCRUD.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    CarRepository repo;

    public Car addCar(Car car){
        return repo.save(car);
    }

    public List<Car> listOfCars(){
        return (List<Car>) repo.findAll();
    }

    public Optional<Car> getCar(Long id){
        if(repo.existsById(id)){
            return repo.findById(id);
        }else {
            return Optional.empty();
        }
    }
    public Optional<Car> typeUpdate(Long id,Car car){
        Optional<Car> updatedType = repo.findById(id);
        if(updatedType.isPresent()){
            updatedType.get().setType(car.getType());
            repo.save(updatedType.get());
            return updatedType;
        }else {
            return Optional.empty();
        }
    }
    public Optional<Car> deleteCar(Car car){
        if(repo.existsById(car.getId())){
            repo.delete(car);
            return Optional.of(car);
        }else {
            return Optional.empty();
        }
    }
    public List<Car> deleteCars(){
        List<Car> deletedCars = repo.findAll();
        repo.deleteAll();
        return deletedCars;
    }

}
