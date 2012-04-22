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

import java.io.Serializable;

/**
 * Interface for all XML Factory instances
 * 
 * @author Bahadır AKIN
 *
 * @param <T> Model class for factoring
 */
public interface IXmlFactory<T> extends Serializable{

	void toXmlFile(T t, String filePath);

	T fromXmlFile(String filePath);
	
	T fromXmlString(String xmlString);

	String toXmlString(T t);
}
