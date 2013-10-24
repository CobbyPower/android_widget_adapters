/*
 * =================================================================================
 * Copyright (C) 2013 Martin Albedinsky [Wolf-ITechnologies]
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
package com.wit.and.widget.adapter.internal;

import android.os.Bundle;

import com.wit.and.widget.adapter.module.AdapterModule;

/**
 * <h4>Interface Overview</h4>
 * <p>
 * Required interface for multi-module adapter.
 * </p>
 * 
 * @param <Adapter>
 *            Type of the multi-module adapter.
 * 
 * @author Martin Albedinsky
 * 
 * @see com.wit.and.widget.adapter.BaseMultiAdapter
 */
public interface IMultiAdapter<Adapter extends AdapterModule.ModuleAdapter> {

	/**
	 * Methods ===============================
	 */

	/**
	 * <p>
	 * Invoked to handle saving of the adapter state.
	 * </p>
	 * 
	 * @param outState
	 *            Outgoing bundle state.
	 */
	public void onSaveInstanceState(Bundle outState);

	/**
	 * <p>
	 * Invoked to handle restoring of the adapter state.
	 * </p>
	 * 
	 * @param savedInstanceState
	 *            Saved adapter state.
	 */
	public void onRestoreInstanceState(Bundle savedInstanceState);

	/**
	 * <p>
	 * Adds the {@link AdapterModule} into this adapter.
	 * </p>
	 * 
	 * @param module
	 *            Specific module.
	 * @param moduleID
	 *            Module id by which can be the module later obtained.
	 */
	public void addModule(AdapterModule<Adapter> module, int moduleID);

	/**
	 * <p>
	 * Returns the {@link AdapterModule} of this adapter.
	 * </p>
	 * 
	 * @param moduleID
	 *            Id of the module.
	 * @return Specific adapter module or <code>null</code> if there is no
	 *         module for the requested id. See {@link 
	 */
	public <M> M getModule(int moduleID);
}
