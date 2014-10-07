/*
 * =================================================================================================
 *                 Copyright (C) 2013 - 2014 Martin Albedinsky [Wolf-ITechnologies]
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

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.wit.android.ui.widget.adapter.module.AdapterModule;

/**
 * <h4>Class Overview</h4>
 * todo: description
 *
 * @param <Item> A type of the item presented within a data set of a subclass of this BaseMultiAdapter.
 * @author Martin Albedinsky
 */
public abstract class BaseMultiAdapter<Item> extends BaseAdapter<Item> implements AdapterModule.ModuleAdapter {

	/**
	 * Interface ===================================================================================
	 */

	/**
	 * Constants ===================================================================================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = "BaseMultiAdapter";

	/**
	 * Flag indicating whether the debug output trough log-cat is enabled or not.
	 */
	// private static final boolean DEBUG_ENABLED = true;

	/**
	 * Flag indicating whether the output trough log-cat is enabled or not.
	 */
	// private static final boolean LOG_ENABLED = true;

	/**
	 * Static members ==============================================================================
	 */

	/**
	 * Members =====================================================================================
	 */

	/**
	 * Manager modules presented within this adapter.
	 */
	private final ModuleManager MODULES_MANAGER = new ModuleManager();

	/**
	 * Constructors ================================================================================
	 */

	/**
	 * Crates a new instance of BaseMultiAdapter within the given context.
	 *
	 * @param context Context in which will be this adapter used.
	 */
	public BaseMultiAdapter(@NonNull Context context) {
		super(context);
	}

	/**
	 * Methods =====================================================================================
	 */

	/**
	 * Public --------------------------------------------------------------------------------------
	 */

	/**
	 * Wrapped {@link ModuleManager#addModule(AdapterModule, int)} for this adapter.
	 *
	 * @see #obtainModule(int)
	 */
	public void attachModule(@NonNull AdapterModule module, int moduleId) {
		module.dispatchAttachToAdapter(this);
		MODULES_MANAGER.addModule(module, moduleId);
	}

	/**
	 * Wrapped {@link ModuleManager#getModule(int)} for this adapter.
	 *
	 * @see #attachModule(com.wit.android.ui.widget.adapter.module.AdapterModule, int)
	 */
	@Nullable
	public AdapterModule obtainModule(int moduleId) {
		return MODULES_MANAGER.getModule(moduleId);
	}

	/**
	 * Wrapped {@link ModuleManager#removeModule(int)} for this adapter.
	 */
	public void removeModule(int moduleId) {
		MODULES_MANAGER.removeModule(moduleId);
	}

	/**
	 * Getters + Setters ---------------------------------------------------------------------------
	 */

	/**
	 * Protected -----------------------------------------------------------------------------------
	 */

	/**
	 * This will also save the state of all currently assigned modules.
	 */
	@NonNull
	@Override
	protected Parcelable onSaveInstanceState() {
		final Bundle modulesState = MODULES_MANAGER.dispatchSaveModulesState();
		// If some of the modules save its state we need to save state of this adapter.
		if (!Bundle.EMPTY.equals(modulesState)) {
			final SavedState savedState = new SavedState(super.onSaveInstanceState());
			savedState.modulesState = modulesState;
			return savedState;
		}
		return super.onSaveInstanceState();
	}

	/**
	 * This will also restore the state of all currently assigned modules.
	 */
	@Override
	protected void onRestoreInstanceState(@NonNull Parcelable savedState) {
		if (!(savedState instanceof SavedState)) {
			super.onRestoreInstanceState(savedState);
			return;
		}

		final SavedState state = (SavedState) savedState;
		super.onRestoreInstanceState(state.getSuperState());
		// Dispatch to restore modules state.
		MODULES_MANAGER.dispatchRestoreModulesState(state.modulesState);
	}

	/**
	 * Private -------------------------------------------------------------------------------------
	 */

	/**
	 * Inner classes ===============================================================================
	 */

	/**
	 * <h4>Class Overview</h4>
	 * A {@link BaseSavedState} implementation that should be used by inheritance hierarchies of
	 * {@link BaseMultiAdapter} to ensure the state of all classes along the chain is saved.
	 *
	 * @author Martin Albedinsky
	 */
	public static class SavedState extends BaseSavedState {

		/**
		 * Members =================================================================================
		 */

		/**
		 * Creator used to create an instance or array of instances of SavedState from {@link android.os.Parcel}.
		 */
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
		 * State of all modules holding by ModuleManager obtained by {@link ModuleManager#dispatchSaveModulesState()}
		 * when saving current state of {@link BaseMultiAdapter}.
		 */
		private Bundle modulesState;

		/**
		 * Constructors ============================================================================
		 */

		/**
		 * Creates a new instance SavedState with the given <var>superState</var> to allow
		 * chaining of saved states in {@link #onSaveInstanceState()} and also in
		 * {@link #onRestoreInstanceState(android.os.Parcelable)}.
		 *
		 * @param superState A super state obtained from <code>super.onSaveInstanceState()</code>
		 *                   within <code>onSaveInstanceState()</code> of a specific {@link BaseMultiAdapter}
		 *                   implementation.
		 */
		protected SavedState(@Nullable Parcelable superState) {
			super(superState);
		}

		/**
		 * Called form {@link #CREATOR} to create an instance of SavedState form the given parcel
		 * <var>source</var>.
		 *
		 * @param source Parcel with data for a new instance.
		 */
		protected SavedState(@NonNull Parcel source) {
			super(source);
			// Use class loader for classes out of the Android SDK.
			this.modulesState = source.readBundle(AdaptersConfig.class.getClassLoader());
		}

		/**
		 * Methods =================================================================================
		 */

		/**
		 */
		@Override
		public void writeToParcel(@NonNull Parcel dest, int flags) {
			super.writeToParcel(dest, flags);
			dest.writeBundle(modulesState);
		}
	}
}
