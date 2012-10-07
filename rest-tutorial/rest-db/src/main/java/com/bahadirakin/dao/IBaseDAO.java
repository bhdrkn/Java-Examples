/*
 *   Copyright 2012 Bahadır AKIN
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.bahadirakin.dao;

import java.io.Serializable;
import java.util.List;

import com.bahadirakin.model.IEntity;

/**
 * Base Data Access Interface
 * 
 * @author Bahadır AKIN
 * 
 * @param IEntity
 *            Class
 */
public interface IBaseDAO<T extends IEntity> extends Serializable {

	/**
	 * Insert a new entity;
	 * 
	 * @param entity
	 *            - detached entity object
	 */
	public Serializable save(T entity);

	/**
	 * Inserts a new detached entity or updates if entity already exists
	 * 
	 * @param entity
	 *            - entity object to be inserted or updated
	 */
	public void saveOrUpdate(T entity);

	/**
	 * Deletes entity from persistence store, AKA. Database
	 * 
	 * @param entity
	 */
	public void delete(T entity);

	/**
	 * Remove this instance from session cache
	 * 
	 * @param entity
	 */
	public void detach(T entity);

	/**
	 * Reloads an entity from persistence store
	 * 
	 * @param entity
	 */
	public void refresh(T entity);

	/**
	 * 
	 * Gets entity by its Id.
	 * 
	 * <p>
	 * If there is other access to persistence store make synchronize
	 * <code>true</code>. Otherwise requested object may not be found.
	 * </p>
	 * 
	 * @param id
	 *            Id of entity
	 * 
	 * @return
	 */
	T getById(Integer id);

	/**
	 * Gets all the entities from persistence store.
	 * 
	 * <p>
	 * If there is other access to persistence store make synchronize
	 * <code>true</code>. Otherwise some of entities may not be found.
	 * </p>
	 * 
	 * @return
	 */
	List<T> getAll();

	/**
	 * Gets an Entity by executing given SQL query.
	 * 
	 * <p>
	 * Returns and Runs Unique Results
	 * </p>
	 * 
	 * @param query
	 *            SQL Query
	 * 
	 * @return Unique Result
	 */
	T getBySql(String query);

	/**
	 * Gets All Entities by executing given SQL query.
	 * 
	 * <p>
	 * Returns and Runs list;
	 * </p>
	 * 
	 * @param query
	 *            SQL Query
	 * 
	 * @return List of Result
	 */
	List<T> getAllBySql(String query);

	/**
	 * Executes given Sql query.
	 * 
	 * <p>
	 * Use with DELETE and UPDATE
	 * </p>
	 * 
	 * @param query
	 */
	void executeSQLQuery(String query);
}
