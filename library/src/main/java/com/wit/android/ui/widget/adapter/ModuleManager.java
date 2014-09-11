/*
 * =================================================================================================
 *                    Copyright (C) 2014 Martin Albedinsky [Wolf-ITechnologies]
 * =================================================================================================
 *         Licensed under the Apache License, Version 2.0 or later (further "License" only).
 * -------------------------------------------------------------------------------------------------
 * You may use this file only in compliance with the License. More details and copy of this License 
 * you may obtain at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * You can redistribute, modify or publish any part of the code written within this file but as it 
 * is described in the License, the software distributed under the License is distributed on an 
 * "AS IS" BASIS, WITHOUT WARRANTIES or CONDITIONS OF ANY KIND.
 * 
 * See the License for the specific language governing permissions and limitations under the License.
 * =================================================================================================
 */
package com.wit.android.ui.widget.adapter;

import android.os.Bundle;
import android.util.SparseArray;

import com.wit.android.ui.widget.adapter.module.AdapterModule;

/**
 * <h4>Class Overview</h4>
 * todo: description
 *
 * @author Martin Albedinsky
 */
public class ModuleManager {

	/**
	 * Interface ===================================================================================
	 */

	/**
	 * Constants ===================================================================================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = "ModuleManager";

	/**
	 * Flag indicating whether the output trough log-cat is enabled or not.
	 */
	// private static final boolean LOG_ENABLED = true;

	/**
	 * Flag indicating whether the debug output trough log-cat is enabled or not.
	 */
	// private static final boolean DEBUG_ENABLED = true;

	/**
	 *
	 */
	private static final String BUNDLE_MODULE_STATE_KEY_FORMAT = "com.wit.android.ui.widget.adapter.ModuleManager.BUNDLE.ModuleState.%d";

	/**
	 * Static members ==============================================================================
	 */

	/**
	 * Members =====================================================================================
	 */

	/**
	 * Array with adapter modules.
	 */
	private SparseArray<AdapterModule> mModules;

	/**
	 * Constructors ================================================================================
	 */

	/**
	 * Methods =====================================================================================
	 */

	/**
	 * Public --------------------------------------------------------------------------------------
	 */

	/**
	 * Adds the given module into the current modules of this manager. If there is already a module
	 * with the same <var>moduleId</var>, the old module will be replaced by the new one.
	 *
	 * @param module   An instance of the desired module to add.
	 * @param moduleId An id by which can be the given module obtained from this manager by {@link #getModule(int)}.
	 */
	public void addModule(AdapterModule module, int moduleId) {
		if (module != null) {
			if (mModules == null) {
				this.mModules = new SparseArray<>();
			}
			mModules.append(moduleId, module);
		}
	}

	/**
	 * Returns a module added into this manager.
	 *
	 * @param moduleId An id of the desired module to obtain.
	 * @return The module which was added into this manager under the specified <var>moduleId</var>
	 * by {@link #addModule(AdapterModule, int)} or <code>null</code> if there is no module with
	 * such an id.
	 */
	public AdapterModule getModule(int moduleId) {
		return mModules != null ? mModules.get(moduleId) : null;
	}

	/**
	 * Removes a module from the current modules set of this manager.
	 *
	 * @param moduleId An id of the desired module to remove.
	 */
	public void removeModule(int moduleId) {
		if (mModules != null) {
			mModules.remove(moduleId);
		}
	}

	/**
	 * Called to save the current state of all modules presented within this manager.
	 *
	 * @return Saved state of all modules or <code>null</code> if there are not any modules presented
	 * or none of the current modules requires state saving.
	 * @see AdapterModule#requiresStateSaving()
	 */
	public Bundle dispatchSaveModulesState() {
		final int n = mModules != null ? mModules.size() : 0;
		if (n > 0) {
			final Bundle states = new Bundle();
			boolean save = false;

			AdapterModule module;
			for (int i = 0; i < n; i++) {
				int moduleId = mModules.keyAt(i);
				module = mModules.get(moduleId);
				if (module.requiresStateSaving()) {
					save = true;
					states.putParcelable(
							createModuleStateKey(moduleId),
							module.dispatchSaveInstanceState()
					);
				}
			}
			return save ? states : null;
		}
		return null;
	}

	/**
	 * Called to restore a previous state, saved by {@link #dispatchSaveModulesState()}, of all modules
	 * presented within this manager.
	 *
	 * @param savedState Should be the same state as obtained by {@link #dispatchSaveModulesState()}
	 *                   before.
	 */
	public void dispatchRestoreModulesState(Bundle savedState) {
		if (savedState != null && mModules != null) {
			final int n = mModules.size();
			if (n > 0) {
				String key;
				for (int i = 0; i < n; i++) {
					int moduleID = mModules.keyAt(i);
					key = createModuleStateKey(moduleID);
					if (savedState.containsKey(key)) {
						mModules.get(moduleID).dispatchRestoreInstanceState(
								savedState.getParcelable(key)
						);
					}
				}
			}
		}
	}

	/**
	 * Getters + Setters ---------------------------------------------------------------------------
	 */

	/**
	 * Protected -----------------------------------------------------------------------------------
	 */

	/**
	 * Creates a bundle key for the specified <var>moduleId</var>.
	 *
	 * @param moduleID The id for which should be key created.
	 * @return Created bundle key.
	 */
	String createModuleStateKey(int moduleID) {
		return String.format(BUNDLE_MODULE_STATE_KEY_FORMAT, moduleID);
	}

	/**
	 * Private -------------------------------------------------------------------------------------
	 */

	/**
	 * Inner classes ===============================================================================
	 */
}
