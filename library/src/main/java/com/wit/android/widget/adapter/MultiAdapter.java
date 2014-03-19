/*
 * =================================================================================
 * Copyright (C) 2013 -2014 Martin Albedinsky [Wolf-ITechnologies]
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
package com.wit.android.widget.adapter;

import com.wit.android.widget.adapter.module.AdapterModule;

/**
 * <h4>Interface Overview</h4>
 * <p>
 * Required interface for multi-module adapter.
 * </p>
 *
 * @param <Adapter> Type of a multi-module adapter.
 * @author Martin Albedinsky
 * @see com.wit.android.widget.adapter.BaseMultiAdapter
 */
public interface MultiAdapter<Adapter extends AdapterModule.ModuleAdapter> {

	/**
	 * Methods ===============================
	 */

	/**
	 * <p>
	 * Assigns the given <var>module</var> to this adapter.
	 * </p>
	 *
	 * @param module   An adapter module to assign.
	 * @param moduleID Id by which can be the given module obtained from this adapter.
	 * @see #obtainModule(int)
	 */
	public void assignModule(AdapterModule<Adapter> module, int moduleID);

	/**
	 * <p>
	 * Returns an adapter module assigned to this adapter.
	 * </p>
	 *
	 * @param moduleID Id of an adapter module to obtain.
	 * @return The adapter module which is represented by the given <var>moduleID</var>.
	 * @see #assignModule(com.wit.android.widget.adapter.module.AdapterModule, int)
	 */
	public <M> M obtainModule(int moduleID);

	/**
	 * <p>
	 * Removes an adapter module assigned to this adapter.
	 * </p>
	 *
	 * @param moduleID Id of an adapter module to remove.
	 */
	public void removeModule(int moduleID);
}
