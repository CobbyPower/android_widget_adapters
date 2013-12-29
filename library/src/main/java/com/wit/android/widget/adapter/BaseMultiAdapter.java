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
import android.os.Bundle;
import android.util.SparseArray;

import com.wit.android.widget.adapter.internal.IMultiAdapter;
import com.wit.android.widget.adapter.module.AdapterModule;

/**
 * <h4>Class Overview</h4>
 * <p>
 * TODO:
 * </p>
 *
 * @param <Adapter> Type of the adapter which extends this base multi-module adapter.
 *
 * @author Martin Albedinsky
 */
public abstract class BaseMultiAdapter<Adapter extends AdapterModule.ModuleAdapter> extends BaseAdapter implements IMultiAdapter<Adapter>, AdapterModule.ModuleAdapter {
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
	 * Manager for adapter's modules.
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
	 * Crates new instance of BaseMultiAdapter with the given context.
	 * </p>
	 *
	 * @param context Context in which will be this adapter used.
	 * @see com.wit.android.widget.adapter.BaseAdapter#BaseAdapter(android.content.Context)
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
	 * <h4>Class Overview</h4>
	 * <p>
	 * Manages {@link BaseMultiAdapter} modules.
	 * </p>
	 *
	 * @param <Adapter> Type of the adapter in which is this module manager presented.
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
		private final SparseArray<AdapterModule<Adapter>> aModules = new SparseArray<AdapterModule<Adapter>>();

		/**
		 * Methods ===============================
		 */

		/**
		 * Protected -----------------------------
		 */

		/**
		 * <p>
		 * Adds the given module into the current modules of this manager. If there is
		 * already some module with the same <var>moduleID</var>l, the old module will
		 * be replaced by the new one.
		 * </p>
		 *
		 * @param module Module to add.
		 * @param moduleID Id by which can be the given module obtained from this manager.
		 * @see #getModule(int)
		 */
		protected void addModule(AdapterModule<Adapter> module, int moduleID) {
			aModules.append(moduleID, module);
		}

		/**
		 * <p>
		 * Returns a module added into this manager.
		 * </p>
		 *
		 * @param moduleID Id of a module to obtain.
		 * @return The module which is represented by the given <var>moduleID</var>.
		 * @see #addModule(com.wit.android.widget.adapter.module.AdapterModule, int)
		 */
		protected AdapterModule<Adapter> getModule(int moduleID) {
			return aModules.get(moduleID);
		}

		/**
		 * <p>
		 * Removes a module from the current modules set of this manager.
		 * </p>
		 *
		 * @param moduleID Id of a module to remove.
		 */
		protected void removeModule(int moduleID) {
			aModules.remove(moduleID);
		}

		/**
		 * <p>
		 * Called to save state of this manager instance. If the given <var>outState</var>
		 * is invalid, there will be created a new bundle and {@link #onSaveInstanceState(android.os.Bundle)}
		 * will be invoked immediately.
		 * </p>
		 *
		 * @param outState Outgoing state in which should this manager instance save its
		 *                 state.
		 * @see #onSaveInstanceState(android.os.Bundle)
		 */
		protected void dispatchSaveState(Bundle outState) {
			for (int i = 0; i < aModules.size(); i++) {
				aModules.get(aModules.keyAt(i)).dispatchSaveInstanceState(outState);
			}
		}

		/**
		 * <p>
		 * Called to restore state of this manager instance. If the given <var>savedState</var>
		 * is valid, {@link #onRestoreInstanceState(android.os.Bundle)} will be invoked
		 * immediately.
		 * </p>
		 *
		 * @param savedState Should be the bundle with saved state in
		 *                   {@link #onSaveInstanceState(android.os.Bundle)}.
		 * @see #onRestoreInstanceState(android.os.Bundle)
		 */
		protected void dispatchRestoreState(Bundle savedState) {
			for (int i = 0; i < aModules.size(); i++) {
				aModules.get(aModules.keyAt(i)).dispatchRestoreInstanceState(savedState);
			}
		}
	}

	/**
	 * Interface =============================
	 */
}
