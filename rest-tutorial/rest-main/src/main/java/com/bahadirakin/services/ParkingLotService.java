package com.bahadirakin.services;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bahadirakin.dao.IBaseDAO;
import com.bahadirakin.dao.ICarDAO;
import com.bahadirakin.dao.IUserDAO;
import com.bahadirakin.dao.impl.CarDAO;
import com.bahadirakin.dao.impl.UserDAO;
import com.bahadirakin.model.Car;
import com.bahadirakin.model.IEntity;
import com.bahadirakin.model.User;

@Path("parking")
public class ParkingLotService {
	private static final Logger logger = LoggerFactory
			.getLogger(ParkingLotService.class);

	private static final String TEXT_XML_RESULT = "<?xml version=\"1.0\"?><result>#result</result>";

	private IUserDAO userDAO;
	private ICarDAO carDAO;

	public ParkingLotService() {
		userDAO = new UserDAO();
		carDAO = new CarDAO();
	}

	@Path("/getAllUsers")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<User> getAllUsers() {
		return userDAO.getAll();
	}

	@Path("/isCarExist")
	@GET
	@Produces(MediaType.TEXT_XML)
	public String isCarExists(
			@QueryParam(value = "liecencePlate") String liecencePlate) {
		try {
			return TEXT_XML_RESULT.replace("#result",
					"" + carDAO.isExist(liecencePlate));
		} catch (Exception e) {
			logger.error(
					"Error while checking car existance for liecencePlate:"
							+ liecencePlate, e);
			return TEXT_XML_RESULT.replace("#result", "false");
		}
	}

	@Path("/createUser")
	@GET
	@Produces(MediaType.TEXT_XML)
	public String createUser(@QueryParam(value = "name") String name,
			@QueryParam(value = "surname") String surname,
			@QueryParam(value = "location") String location) {
		User user = new User(name, surname, location);
		Serializable serializable = userDAO.save(user);
		try {
			return TEXT_XML_RESULT.replace("#result", "" + serializable);
		} catch (Exception e) {
			logger.error("Error while creating user. ", e);
			return TEXT_XML_RESULT.replace("#result", "-1");
		}
	}

	@Path("/addCar")
	@GET
	@Produces(MediaType.TEXT_XML)
	public String addCar(@QueryParam(value = "userId") Integer userId,
			@QueryParam(value = "liecencePlate") String liecencePlate,
			@QueryParam(value = "model") String model) {
		User user = userDAO.getById(userId);
		if (user == null) {
			logger.warn("User was not found for id:" + userId);
			return TEXT_XML_RESULT.replace("#result", "-2");
		}

		try {
			Car car = new Car(user, liecencePlate, model);
			Serializable carId = carDAO.save(car);
			return TEXT_XML_RESULT.replace("#result", "" + carId);
		} catch (Throwable e) {
			logger.error("Error while creating car for user :" + userId, e);
			return TEXT_XML_RESULT.replace("#result", "-1");
		}
	}

	@Path("/deleteUser")
	@DELETE
	@Produces(MediaType.TEXT_XML)
	public String deleteUser(@QueryParam(value = "id") Integer id) {
		return deleteEntity(id, userDAO);
	}

	@Path("/deleteCar")
	@DELETE
	@Produces(MediaType.TEXT_XML)
	public String deleteCar(@QueryParam(value = "id") Integer id) {
		return deleteEntity(id, carDAO);
	}

	// DRY
	private <T extends IEntity> String deleteEntity(Integer id, IBaseDAO<T> dao) {
		T entity = dao.getById(id);
		if (entity == null) {
			logger.warn("Entity was not found for id :" + id);
			return TEXT_XML_RESULT.replace("#result", "false");
		}
		try {
			dao.delete(entity);
			return TEXT_XML_RESULT.replace("#result", "true");
		} catch (Throwable e) {
			logger.error(
					"Error while deleting entity from database. Id: " + id, e);
			return TEXT_XML_RESULT.replace("#result", "false");
		}
	}
}
