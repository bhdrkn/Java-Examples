package com.bahadirakin.service;

import java.util.List;

import com.bahadirakin.model.Car;

public interface ICarService {

	List<Car> getAllCars();
	
	void saveCar(final Car car);
}
