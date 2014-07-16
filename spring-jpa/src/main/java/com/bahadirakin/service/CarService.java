package com.bahadirakin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bahadirakin.dao.ICarDao;
import com.bahadirakin.model.Car;

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, value = "transactionManager")
public class CarService implements ICarService {

	private static final long serialVersionUID = 1L;
	
	private ICarDao carDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bahadirakin.service.ICarService#delete(java.lang.Object)
	 */
	@Override
	public void delete(Object arg0) {
		carDao.detach(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bahadirakin.service.ICarService#getAllCars()
	 */
	@Override
	public List<Car> getAllCars() {
		return carDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bahadirakin.service.ICarService#updateCar(com.bahadirakin.model.Car)
	 */
	@Override
	public Car updateCar(Car arg0) {
		return carDao.merge(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bahadirakin.service.ICarService#createNewCar(com.bahadirakin.model
	 * .Car)
	 */
	@Override
	public void createNewCar(Car arg0) {
		carDao.persist(arg0);
	}

	public ICarDao getCarDao() {
		return carDao;
	}

	public void setCarDao(ICarDao carDao) {
		this.carDao = carDao;
	}

}
