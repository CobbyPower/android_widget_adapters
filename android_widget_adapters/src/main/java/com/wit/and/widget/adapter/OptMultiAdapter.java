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

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;

import com.wit.and.internal.widget.adapter.DefaultSelectionModule;
import com.wit.and.widget.StateRelativeLayout;

/**
 * <p>
 * public abstract class
 * </p>
 * <h5>OptMultiAdapter</h5>
 * <p>
 * extends {@link OptBaseAdapter},<br/>
 * implements {@link IMultiAdapter}
 * </p>
 * <h4>Class Overview</h4>
 * <p>
 * </p>
 * <p>
 * As default contains default {@link SelectionModule} implementation. Selector module can be
 * obtained under the {@link #MODULE_SELECTOR} id.
 * </p>
 * 
 * @see OptBaseAdapter
 * @see com.wit.and.widget.StateLinearLayout
 * @see StateRelativeLayout
 * @see com.wit.and.view.StateView
 * 
 * @author Martin Albedinsky
 * 
 * @param <Adapter>
 *            Type of the adapter which extends this adapter.
 */
public abstract class OptMultiAdapter<Adapter extends OptBaseAdapter> extends OptBaseAdapter implements IMultiAdapter<Adapter> {
	/**
	 * Constants =============================
	 */

    /**
     * Log TAG.
     */
    // private static final String TAG = OptMultiAdapter.class.getSimpleName();

    /**
     * Indicates if debug private output trough log-cat is enabled.
     */
    // private static final boolean DEBUG = true;

    /**
     * Indicates if logging for user output trough log-cat is enabled.
     */
    // private static final boolean USER_LOG = true;

	/**
	 * 
	 */
	public static final int MODULE_SELECTOR = 0x2001;

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
	private final ModuleManager<Adapter> MODULES_MANAGER = new ModuleManager<Adapter>();

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
	 * <br/>
	 * <h5><i>public OptMultiAdapter(Context context)</i></h5>
	 * <p>
	 * See {@link OptBaseAdapter#OptBaseAdapter(Context)}.
	 * </p>
	 * 
	 * @param context
	 */
	public OptMultiAdapter(Context context) {
		super(context);
        addModule(new DefaultSelectionModule<Adapter>(), MODULE_SELECTOR);
	}

	/**
	 * Methods ===============================
	 */

	/**
	 * Public --------------------------------
	 */

	/**
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void addModule(AdapterModule<Adapter> module, int moduleID) {
		module.onAttachToAdapter((Adapter) this);
		MODULES_MANAGER.addModule(module, moduleID);
	}

	/**
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <M> M getModule(int moduleID) {
		return (M) MODULES_MANAGER.getModule(moduleID);
	}

	/**
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		MODULES_MANAGER.dispatchOnSaveState(outState);
	}

	@Override
	public void onRestoreInstanceState(Bundle savedState) {
		super.onRestoreInstanceState(savedState);
		MODULES_MANAGER.dispatchOnRestoreState(savedState);
	}

	/**
	 * Getters + Setters ---------------------
	 */

	/**
	 * Protected -----------------------------
	 */

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
	 * <p>
	 * protected static class
	 * </p>
	 * <h5>ModuleManager</h5>
	 * <p>
	 * </p>
	 * <h4>Class Overview</h4>
	 * <p>
	 * Manager to handle {@link OptMultiAdapter} modules.
	 * </p>
	 * 
	 * @param <Adapter>
	 *            Type of the adapter in which is this module manager presented.
	 * 
	 * @author Martin Albedinsky
	 * 
	 */
	protected static class ModuleManager<Adapter extends BaseAdapter> {

		/**
		 * Constants =============================
		 */

        /**
         * Log TAG.
         */
        // private static final String TAG =  ModuleManager.class.getSimpleName();

		/**
		 * Enums =================================
		 */

		/**
		 * Members ===============================
		 */

		/**
		 * Listeners -----------------------------
		 */

		/**
		 * Static --------------------------------
		 */

		/**
		 * Arrays --------------------------------
		 */

		/**
		 * Array with adapter modules.
		 */
		private SparseArray<AdapterModule<Adapter>> aModules = new SparseArray<AdapterModule<Adapter>>();

		/**
		 * Booleans ------------------------------
		 */

		/**
		 * Indicates if debug private output trough log-cat is enabled.
		 */
		// private static final boolean DEBUG = true;

		/**
		 * Indicates if logging for user output trough log-cat is enabled.
		 */
		// private static final boolean USER_LOG = true;

		/**
		 * Constructors ==========================
		 */

		/**
		 * Methods ===============================
		 */

		/**
		 * Public --------------------------------
		 */

		/**
		 * Getters + Setters ---------------------
		 */

		/**
		 * Protected -----------------------------
		 */

		void addModule(AdapterModule<Adapter> module, int moduleID) {
			aModules.append(moduleID, module);
		}

		AdapterModule<Adapter> getModule(int moduleID) {
			return aModules.get(moduleID);
		}

		void dispatchOnSaveState(Bundle outState) {
			for (int i = 0; i < aModules.size(); i++) {
				aModules.get(aModules.keyAt(i)).onSaveInstanceState(outState);
			}
		}

		void dispatchOnRestoreState(Bundle savedState) {
			for (int i = 0; i < aModules.size(); i++) {
				aModules.get(aModules.keyAt(i)).onRestoreInstanceState(savedState);
			}
		}
	}

	/**
	 * Interface =============================
	 */
}
