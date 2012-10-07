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
package com.bahadirakin.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hibernate Utility
 * 
 * @author Bahadır AKIN
 * 
 */
public class HibernateUtil {

	private static final Logger LOG = LoggerFactory
			.getLogger(HibernateUtil.class);

	/**
	 * One and Only SessionFacotry
	 */
	private SessionFactory factory = null;

	/**
	 * Singleton Pattern
	 */
	private static HibernateUtil hibernateUtil = null;

	public static HibernateUtil getInstance() {
		if (hibernateUtil == null) {
			hibernateUtil = new HibernateUtil();
		}
		return hibernateUtil;
	}

	private HibernateUtil() {
		// Constructor
	}

	public SessionFactory getFactory() {
		try {
			if (factory == null) {
				factory = new AnnotationConfiguration().configure()
						.buildSessionFactory();
			} else if (factory.isClosed()) {
				factory = new AnnotationConfiguration().configure()
						.buildSessionFactory();
			}
		} catch (Exception e) {
			LOG.error("Exception in getFactory while creating new Factory M:"
					+ e.getMessage() + " C: " + e.getCause() + " ST: "
					+ e.getStackTrace());
		}
		return factory;
	}

	public Session getCurrentSession() {
		Session session = null;
		try {
			session = getFactory().getCurrentSession();
		} catch (Exception e) {
			LOG.error("Exception in getCurrentSession M: " + e.getMessage()
					+ " C: " + e.getCause());
		}
		return session;
	}
}
