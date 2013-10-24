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

import com.wit.and.widget.adapter.internal.IMultiAdapter;
import com.wit.and.widget.adapter.module.AdapterModule;
import com.wit.and.widget.adapter.widget.StateRelativeLayout;

/**
 * <h4>Class Overview</h4>
 * <p>
 * </p>
 *
 * @see BaseAdapter
 * @see com.wit.and.widget.adapter.widget.StateLinearLayout
 * @see StateRelativeLayout
 * @see com.wit.and.widget.adapter.view.StateView
 * 
 * @author Martin Albedinsky
 * 
 * @param <Adapter> Type of the adapter which extends this base multi-module adapter.
 */
public abstract class BaseMultiAdapter<Adapter extends AdapterModule.ModuleAdapter> extends BaseAdapter implements IMultiAdapter<Adapter> {
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
	 * <p>
	 * </p>
	 * 
	 * @param context
     * @see BaseAdapter#BaseAdapter(Context)
	 */
	public BaseMultiAdapter(Context context) {
		super(context);
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
		module.dispatchAttachToAdapter((Adapter) this);
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

    /**
     */
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
	 * <h4>Class Overview</h4>
	 * <p>
	 * Manager to handle {@link BaseMultiAdapter} modules.
	 * </p>
	 * 
	 * @param <Adapter>
	 *            Type of the adapter in which is this module manager presented.
	 * 
	 * @author Martin Albedinsky
	 */
	protected static class ModuleManager<Adapter extends AdapterModule.ModuleAdapter> {

		/**
		 * Members ===============================
		 */

		/**
		 * Arrays --------------------------------
		 */

		/**
		 * Array with adapter modules.
		 */
		private SparseArray<AdapterModule<Adapter>> aModules = new SparseArray<AdapterModule<Adapter>>();

		/**
		 * Methods ===============================
		 */

		/**
		 * Protected -----------------------------
		 */

        /**
         * <p>
         * </p>
         *
         * @param module
         * @param moduleID
         */
		void addModule(AdapterModule<Adapter> module, int moduleID) {
			aModules.append(moduleID, module);
		}

        /**
         * <p>
         * </p>
         *
         * @param moduleID
         * @return
         */
		AdapterModule<Adapter> getModule(int moduleID) {
			return aModules.get(moduleID);
		}

        /**
         * <p>
         * </p>
         *
         * @param outState
         */
		void dispatchOnSaveState(Bundle outState) {
			for (int i = 0; i < aModules.size(); i++) {
				aModules.get(aModules.keyAt(i)).dispatchSaveInstanceState(outState);
			}
		}

        /**
         * <p>
         * </p>
         *
         * @param savedState
         */
		void dispatchOnRestoreState(Bundle savedState) {
			for (int i = 0; i < aModules.size(); i++) {
				aModules.get(aModules.keyAt(i)).dispatchRestoreInstanceState(savedState);
			}
		}
	}

	/**
	 * Interface =============================
	 */
}
