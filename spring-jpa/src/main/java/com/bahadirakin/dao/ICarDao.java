package com.bahadirakin.dao;

import java.io.Serializable;
import java.util.List;

import com.bahadirakin.model.Car;

public interface ICarDao extends Serializable{

	 void detach(Object arg0);

	 List<Car> findAll();

	 Car merge(Car arg0);

	 void persist(Car arg0);

}