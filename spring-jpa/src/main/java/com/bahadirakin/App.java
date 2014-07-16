package com.bahadirakin;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bahadirakin.model.Car;
import com.bahadirakin.service.CarService;
import com.bahadirakin.service.ICarService;


/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Application is going to be started");
		final AbstractApplicationContext applContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");		
		applContext.registerShutdownHook();
		System.out.println("Application is started");
		
		final ICarService carService = (ICarService) applContext.getBean("carService");
		final List<Car> cars = carService.getAllCars();
		
		if(cars == null || cars.isEmpty()){
			System.out.println("Cars is empty.");
			
		}else{
			System.out.println("Cars: " + cars);
		}
		
		Thread.sleep(10000);
	}
}
