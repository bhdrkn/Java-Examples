package com.bahadirakin.webapp;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bahadirakin.model.Car;
import com.bahadirakin.service.ICarService;

@Component
@Scope("request")
public class CarBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CarBean.class);

	@Autowired
	private ICarService carService;
	private Car car;

	private List<Car> cars;

	public CarBean() {
		super();
	}

	@PostConstruct
	public void init() {
		this.car = new Car();
		this.cars = carService.getAllCars();
	}

	public void save() {
		logger.info("new car is going to be saved: {} ", this.car);
		this.carService.saveCar(car);
		this.init();
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public ICarService getCarService() {
		return carService;
	}

	public void setCarService(ICarService carService) {
		this.carService = carService;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

}
