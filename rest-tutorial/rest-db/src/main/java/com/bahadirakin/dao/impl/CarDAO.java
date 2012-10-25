package com.bahadirakin.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.bahadirakin.dao.ICarDAO;
import com.bahadirakin.model.Car;

public class CarDAO extends BaseHibernateDAO<Car> implements ICarDAO {

	private static final long serialVersionUID = 1L;

	public boolean isExist(String liecencePlate) {
		List<Car> cars = this.findAllByCriteria(Restrictions.eq(
				"liecencePlate", liecencePlate));
		if (cars == null) {
			return false;
		}

		if (cars.size() < 1) {
			return false;
		} else {
			return true;
		}
	}
}
