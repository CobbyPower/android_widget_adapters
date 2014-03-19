/*
 * =================================================================================
 * Copyright (C) 2013 - 2014 Martin Albedinsky [Wolf-ITechnologies]
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
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;

import com.wit.android.widget.adapter.module.AdapterModule;

/**
 * <h4>Class Overview</h4>
 * <p>
 * </p>
 *
 * @param <Adapter> Type of the adapter which extends this base multi-module adapter.
 *
 * @author Martin Albedinsky
 */
public abstract class BaseMultiAdapter<Adapter extends AdapterModule.ModuleAdapter> extends BaseAdapter implements MultiAdapter<Adapter>, AdapterModule.ModuleAdapter {
	/**
	 * Constants =============================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = BaseMultiAdapter.class.getSimpleName();

	/**
	 * Flag indicating whether the debug output trough log-cat is enabled or not.
	 */
	// private static final boolean DEBUG = true;

	/**
	 * Flag indicating whether the output for user trough log-cat is enabled or not.
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
	private final ModuleManager<Adapter> MODULES_MANAGER = new ModuleManager<>();

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
	protected Parcelable onSaveInstanceState() {
		final Bundle modulesState = MODULES_MANAGER.dispatchSaveState();
		// If some of the modules save its state we need to save state of this adapter.
		if (modulesState != null) {
			final SavedState savedState = new SavedState(super.onSaveInstanceState());
			savedState.modulesState = modulesState;
			return savedState;
		}
		return super.onSaveInstanceState();
	}

	/**
	 */
	@Override
	protected void onRestoreInstanceState(Parcelable savedState) {
		if (!(savedState instanceof SavedState)) {
			super.onRestoreInstanceState(savedState);
			return;
		}

		final SavedState state = (SavedState) savedState;
		super.onRestoreInstanceState(state.getParentState());
		// Dispatch to restore modules state.
		MODULES_MANAGER.dispatchRestoreState(state.modulesState);
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
	 * Manages {@link com.wit.android.widget.adapter.BaseMultiAdapter} modules.
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
		 *
		 */
		private static final String BUNDLE_MODULE_STATE_KEY_FORMAT = "com.wit.android.widget.adapter.BaseMultiAdapter.BUNDLE.ModuleState.%d";

		/**
		 * Array with adapter modules.
		 */
		private final SparseArray<AdapterModule<Adapter>> modules = new SparseArray<>();

		/**
		 * Methods ===============================
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
			modules.append(moduleID, module);
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
			return modules.get(moduleID);
		}

		/**
		 * <p>
		 * Removes a module from the current modules set of this manager.
		 * </p>
		 *
		 * @param moduleID Id of a module to remove.
		 */
		protected void removeModule(int moduleID) {
			modules.remove(moduleID);
		}

		/**
		 *
		 *
		 * @return
		 */
		Bundle dispatchSaveState() {
			final int n = modules.size();
			if (n > 0) {
				final Bundle states = new Bundle();
				boolean save = false;

				for (int i = 0; i < n; i++) {
					int moduleID = modules.keyAt(i);
					AdapterModule module = modules.get(moduleID);
					if (module.requiresStateSaving()) {
						save = true;
						states.putParcelable(
								createModuleStateKey(moduleID),
								module.dispatchSaveInstanceState()
						);
					}
				}
				return save ? states : null;
			}
			return null;
		}

		/**
		 *
		 * @param savedState
		 */
		void dispatchRestoreState(Bundle savedState) {
			if (savedState != null) {
				final int n = modules.size();
				if (n > 0) {
					for (int i = 0; i < n; i++) {
						int moduleID = modules.keyAt(i);
						String key = createModuleStateKey(moduleID);
						if (savedState.containsKey(key)) {
							AdapterModule module = modules.get(moduleID);
							module.dispatchRestoreInstanceState(
									savedState.getParcelable(key)
							);
						}
					}
				}
			}
		}

		/**
		 *
		 * @param moduleID
		 * @return
		 */
		String createModuleStateKey(int moduleID) {
			return String.format(BUNDLE_MODULE_STATE_KEY_FORMAT, moduleID);
		}
	}

	/**
	 * <h4>Class Overview</h4>
	 * <p>
	 * </p>
	 *
	 * @author Martin Albedinsky
	 */
	public static class SavedState extends BaseSavedState {

		/**
		 * Members ===============================
		 */

		/**
		 * <p>
		 * </p>
		 */
		@SuppressWarnings("hiding")
		public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {

			/**
			 */
			@Override
			public SavedState createFromParcel(Parcel source) {
				return new SavedState(source);
			}

			/**
			 */
			@Override
			public SavedState[] newArray(int size) {
				return new SavedState[size];
			}
		};

		/**
		 *
		 */
		private Bundle modulesState = null;

		/**
		 * Constructors ==========================
		 */

		/**
		 * <p>
		 * </p>
		 */
		public SavedState(Parcelable superState) {
			super(superState);
		}

		/**
		 *
		 * @param source
		 */
		private SavedState(Parcel source) {
			super(source);
			this.modulesState = source.readBundle();
		}

		/**
		 * Methods ===============================
		 */

		/**
		 */
		@Override
		public void writeToParcel(Parcel dest, int flags) {
			super.writeToParcel(dest, flags);
			dest.writeBundle(modulesState);
		}
	}

	/**
	 * Interface =============================
	 */
}
