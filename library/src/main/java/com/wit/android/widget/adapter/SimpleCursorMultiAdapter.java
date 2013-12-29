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
package com.wit.android.widget.adapter;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import com.wit.android.widget.adapter.internal.IMultiAdapter;
import com.wit.android.widget.adapter.module.AdapterModule;

/**
 * <h4>Class Overview</h4>
 * <p>
 * TODO:
 * </p>
 *
 * @param <Adapter> Type of the adapter which extends this base multi-module adapter.
 * @param <C> Type of the cursor which will represents data set for this adapter.
 *
 * @author Martin Albedinsky
 */
public abstract class SimpleCursorMultiAdapter<C extends Cursor, Adapter extends AdapterModule.ModuleAdapter> extends SimpleCursorAdapter<C> implements IMultiAdapter<Adapter> {

	/**
	 * Constants =============================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = BaseMultiAdapter.class.getSimpleName();

	/**
	 * Indicates if debug private output trough log-cat is enabled.
	 */
	// private static final boolean DEBUG = true;

	/**
	 * Indicates if logging for user output trough log-cat is enabled.
	 */
	// private static final boolean USER_LOG = true;

	/**
	 * Enums =================================
	 */

	/**
	 * Static members ========================
	 */

	/**
	 * Members ===============================
	 */

	/**
	 * Modules manager.
	 */
	private final BaseMultiAdapter.ModuleManager<Adapter> MODULES_MANAGER = new BaseMultiAdapter.ModuleManager<Adapter>();

	/**
	 * Listeners -----------------------------
	 */

	/**
	 * Arrays --------------------------------
	 */

	/**
	 * Booleans ------------------------------
	 */

	/**
	 * Constructors ==========================
	 */

	/**
	 * <p>
	 * Creates new instance of SimpleCursorMultiAdapter with the given context.
	 * </p>
	 *
	 * @param context Context in which will be this adapter used.
	 */
	public SimpleCursorMultiAdapter(Context context) {
		super(context);
	}

	/**
	 * <p>
	 * Creates new instance of SimpleCursorMultiAdapter with the given context and
	 * cursor as data set for this adapter.
	 * </p>
	 *
	 * @param context Context in which will be this adapter used.
	 * @param cursor  Cursor as data set for this adapter.
	 */
	public SimpleCursorMultiAdapter(Context context, C cursor) {
		super(context, cursor);
	}

	/**
	 * Methods ===============================
	 */

	/**
	 * Public --------------------------------
	 */

	/**
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void assignModule(AdapterModule<Adapter> module, int moduleID) {
		module.dispatchAttachToAdapter((Adapter) this);
		MODULES_MANAGER.addModule(module, moduleID);
	}

	/**
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <M> M obtainModule(int moduleID) {
		return (M) MODULES_MANAGER.getModule(moduleID);
	}

	/**
	 */
	@Override
	public void removeModule(int moduleID) {
		MODULES_MANAGER.removeModule(moduleID);
	}

	/**
	 * Getters + Setters ---------------------
	 */

	/**
	 * Protected -----------------------------
	 */

	/**
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		MODULES_MANAGER.dispatchSaveState(outState);
	}

	/**
	 */
	@Override
	protected void onRestoreInstanceState(Bundle savedState) {
		super.onRestoreInstanceState(savedState);
		MODULES_MANAGER.dispatchRestoreState(savedState);
	}

	/**
	 * Private -------------------------------
	 */

	/**
	 * Abstract methods ----------------------
	 */

	/**
	 * Inner classes =========================
	 */

	/**
	 * Interface =============================
	 */
}
