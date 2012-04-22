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

import com.bahadirakin.xstream.model.User;

public class UserXmlFactory extends AbstractXmlFactory<User> {

	private static final long serialVersionUID = 1L;

	public static final String USER_XML_FILE = "user.xml";

	public UserXmlFactory() {
		super();
	}
}
