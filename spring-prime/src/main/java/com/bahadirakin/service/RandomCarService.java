package com.bahadirakin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bahadirakin.model.Car;

public class RandomCarService implements ICarService {

	private static final Logger logger = LoggerFactory
			.getLogger(RandomCarService.class);

	private static final int RANDOM_LIST_SIZE = 25;

	private static final String[] COLORS = new String[] { "Black", "White",
			"Green", "Red", "Blue", "Orange", "Silver", "Yellow", "Brown",
			"Maroon" };

	private static final String[] BRANDS = new String[] { "BMW", "Mercedes",
			"Volvo", "Audi", "Renault", "Fiat", "Volkswagen", "Honda",
			"Jaguar", "Ford" };

	private List<Car> savedCars;

	public RandomCarService() {
		savedCars = new ArrayList<>();
	}

	@Override
	public List<Car> getAllCars() {
		final List<Car> list = new ArrayList<Car>();
		list.addAll(savedCars);

		for (int i = 0; i < RANDOM_LIST_SIZE; i++) {
			list.add(new Car(getRandomId(), getRandomBrand(), getRandomYear(),
					getRandomColor(), getRandomPrice()));
		}

		logger.info("Returning all the cars with size {}", list.size());
		return list;
	}

	@Override
	public void saveCar(Car car) {
		car.setId(getRandomId());
		savedCars.add(car);
		logger.info("Car is saved: {}", car);
	}

	private String getRandomId() {
		return UUID.randomUUID().toString().substring(0, 8);
	}

	private int getRandomYear() {
		return (int) (Math.random() * 50 + 1960);
	}

	private String getRandomColor() {
		return COLORS[(int) (Math.random() * COLORS.length)];
	}

	private String getRandomBrand() {
		return BRANDS[(int) (Math.random() * BRANDS.length)];
	}

	private int getRandomPrice() {
		return (int) (Math.random() * 100000);
	}

}
