package com.bahadirakin.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bahadirakin.model.Car;

public interface ICarService extends Serializable{

	 void delete(Object arg0);

	 List<Car> getAllCars();

	 Car updateCar(Car arg0);

	 void createNewCar(Car arg0);

}