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
package com.bahadirakin.xstream.factory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.ParameterizedType;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@SuppressWarnings("unchecked")
public abstract class AbstractXmlFactory<T> implements IXmlFactory<T> {

	private static final long serialVersionUID = 1L;
	protected XStream xStream = null;

	public AbstractXmlFactory() {
		super();
		xStream = new XStream(new DomDriver());
		xStream.processAnnotations(((Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0]));
		xStream.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
	}

	public void toXmlFile(final T t, final String filePath) {
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
					file));
			xStream.toXML(t, bufferedWriter);
		} catch (Exception e) {
			System.err.println("Exception in toXmlFile M:" + e.getMessage()
					+ " C: " + e.getCause() + " ST: " + e.getStackTrace());
		}
	}

	public T fromXmlFile(final String filePath) {
		T t = null;
		try {
			File file = new File(filePath);
			BufferedReader bufferedReader = new BufferedReader(new FileReader(
					file));
			t = ((T) this.xStream.fromXML(bufferedReader));
		} catch (Exception e) {
			System.err.println("Exception in fromXmlFile M:" + e.getMessage()
					+ " C: " + e.getCause() + " ST: " + e.getStackTrace());
		}

		return t;
	}

	public String toXmlString(final T t) {
		return this.xStream.toXML(t);
	}

	public T fromXmlString(String xmlString) {
		T t = null;
		t = ((T) this.xStream.fromXML(xmlString));
		return t;
	}
}
