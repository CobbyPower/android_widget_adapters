/*
 * =================================================================================
 * Copyright (C) 2014 Martin Albedinsky [Wolf-ITechnologies]
 * =================================================================================
 * Licensed under the Apache License, Version 2.0 or later (further "License" only);
 * ---------------------------------------------------------------------------------
 * You may use this file only in compliance with the License. More details and copy
 * of this License you may obtain at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * You can redistribute, modify or publish any part of the code written in this
 * file but as it is described in the License, the software distributed under the 
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES or CONDITIONS OF
 * ANY KIND.
 * 
 * See the License for the specific language governing permissions and limitations
 * under the License.
 * =================================================================================
 */
package com.wit.android.widget.adapter.examples.model;

/**
 * <p>
 * Description.
 * </p>
 *
 * @author Martin Albedinsky
 */
public class Connection {

	/**
	 * Log TAG.
	 */
	// private static final String TAG = Connection.class.getSimpleName();

	/**
	 *
	 */
	private String mName;

	/**
	 *
	 */
	private Group mGroup;

	/**
	 *
	 * @param name
	 * @param group
	 */
	public Connection(String name, Group group) {
		this.mName = name;
		this.mGroup = group;
	}

	/**
	 *
	 * @return
	 */
	public Group getGroup() {
		return mGroup;
	}

	/**
	 *
	 * @return
	 */
	public String getName() {
		return mName;
	}
}