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
package com.wit.and.widget.adapter;

import android.os.Bundle;

/**
 * <p>
 * public interface
 * </p>
 * <h5>IMultiAdapter</h5>
 * <p>
 * 
 * </p>
 * <h4>Intervace Overview</h4>
 * <p>
 * Required interface for selection adapter.
 * </p>
 * 
 * @param <Adapter>
 *            Type of the adapter.
 * 
 * @author Martin Albedinsky
 * 
 * @see OptMultiAdapter
 */
public interface IMultiAdapter<Adapter extends BaseAdapter> {

	/**
	 * Methods ===============================
	 */

	/**
	 * <br/>
	 * <h5><i>public void onSaveInstanceState(Bundle outState)</i></h5>
	 * <p>
	 * Invoked to handle saving of the adapter state.
	 * </p>
	 * 
	 * @param outState
	 *            Outgoing bundle state.
	 */
	public void onSaveInstanceState(Bundle outState);

	/**
	 * <br/>
	 * <h5><i>public void onRestoreInstanceState(Bundle savedInstanceState)</i></h5>
	 * <p>
	 * Invoked to handle restoring of the adapter state.
	 * </p>
	 * 
	 * @param savedInstanceState
	 *            Saved adapter state.
	 */
	public void onRestoreInstanceState(Bundle savedInstanceState);

	/**
	 * <br/>
	 * <h5><i>public void addModule(AdapterModule&lt;A&gt; module, int
	 * moduleID)</i></h5>
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
	 * <br/>
	 * <h5><i>public <M> M getModule(int moduleID)</i></h5>
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
