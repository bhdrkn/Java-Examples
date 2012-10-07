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
package com.bahadirakin.model;

import java.io.Serializable;
import java.util.Comparator;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * This is a POJO Business Domain Object. Hibernate Bean.
 * 
 * <p>
 * All entities are Java Beans and should inherit this class to make sure they
 * are {@link Serializable} and {@link Cloneable} and have the id fileds.
 * </p>
 * 
 * @author Bahadır AKIN
 * 
 */
public abstract class AbstractEntity implements IEntity {

	private static final long serialVersionUID = 1L;

	public static final Comparator<IEntity> ID_COMPARATOR = new IdComparator();

	/**
	 * Default constructor (required by Hibernate).
	 */
	public AbstractEntity() {
	}

	/**
	 * Returns whether this instance represents a new transient instance.
	 * 
	 * @return <tt>true</tt> if <code>id</code> is <tt>null</tt>
	 */
	public boolean isNew() {
		return (this.getId() == null);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	protected static class IdComparator implements Comparator<IEntity>,
			Serializable {

		private static final long serialVersionUID = 1L;

		public int compare(IEntity arg0, IEntity arg1) {
			return new CompareToBuilder().append(arg0.getId(), arg1.getId())
					.toComparison();
		}

	}

	@Override
	public final boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		// isNew ???
		if (isNew() || null == obj) {
			return false;
		}

		if (getClass().equals(obj.getClass())) {
			IEntity entity = (IEntity) obj;
			return new EqualsBuilder().append(getId(), entity.getId())
					.isEquals();
		}

		return false;
	}

	public final int compareTo(IEntity o) {
		if (this.equals(o)) {
			return 0;
		}
		return new CompareToBuilder()
				.append(getClass(), o.getClass(),
						AbstractEntity.CLASS_COMPARATOR)
				.append(getId(), o.getId()).toComparison();
	}

	@Override
	public final int hashCode() {
		return new HashCodeBuilder().append(getClass()).append(getId())
				.toHashCode();
	}

	private static final Comparator<Class<?>> CLASS_COMPARATOR = new Comparator<Class<?>>() {

		public int compare(Class<?> o1, Class<?> o2) {
			return new CompareToBuilder()
					.append(o1.getSimpleName(), o2.getSimpleName())
					.append(o1.hashCode(), o2.hashCode()).toComparison();
		}
	};

}
