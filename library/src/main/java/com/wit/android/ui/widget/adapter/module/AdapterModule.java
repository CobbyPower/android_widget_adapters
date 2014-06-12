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
package com.wit.android.ui.widget.adapter.module;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.AbsSavedState;

/**
 * <h4>Class Overview</h4>
 * <p>
 * </p>
 *
 * @author Martin Albedinsky
 */
public abstract class AdapterModule {

	/**
	 * Constants ===================================================================================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = AdapterModule.class.getSimpleName();

	/**
	 * Flag indicating whether the debug output trough log-cat is enabled or not.
	 */
	// private static final boolean DEBUG_ENABLED = true;

	/**
	 * Flag indicating whether the output trough log-cat is enabled or not.
	 */
	// private static final boolean LOG_ENABLED = true;

	/**
	 * Enums =======================================================================================
	 */

	/**
	 * Static members ==============================================================================
	 */

	/**
	 * Members =====================================================================================
	 */

	/**
	 * The adapter to which is this module attached.
	 */
	ModuleAdapter mAdapter;

	/**
	 * Arrays --------------------------------------------------------------------------------------
	 */

	/**
	 * Booleans ------------------------------------------------------------------------------------
	 */

	/**
	 * Flag indicating whether the adapter to which is this module attached, should be
	 * notified in case, that the data set of this adapter module was changed.
	 */
	private boolean bAdapterNotificationEnabled = true;

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
	 * <p>
	 * </p>
	 *
	 * @return
	 */
	public Parcelable dispatchSaveInstanceState() {
		return onSaveInstanceState();
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param savedState
	 */
	public void dispatchRestoreInstanceState(Parcelable savedState) {
		if (savedState != null) {
			onRestoreInstanceState(savedState);
		}
	}

	/**
	 * <p>
	 * Called to attach this adapter module to the given adapter.
	 * </p>
	 *
	 * @param adapter The adapter to which will be this adapter module attached.
	 */
	public final void dispatchAttachToAdapter(ModuleAdapter adapter) {
		onAttachedToAdapter(mAdapter = adapter);
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @return
	 */
	public boolean requiresStateSaving() {
		return false;
	}

	/**
	 * Getters + Setters ---------------------------------------------------------------------------
	 */

	/**
	 * <p>
	 * Returns flag indicating whether the adapter to which is this module attached
	 * should be notified in case, that the data set of this adapter module was changed.
	 * </p>
	 *
	 * @return <code>True</code> in case, that adapter notification is enabled, <code>false</code>
	 * otherwise.
	 * @see #enableAdapterNotification(boolean)
	 */
	public boolean isAdapterNotificationEnabled() {
		return bAdapterNotificationEnabled;
	}

	/**
	 * <p>
	 * Sets flag indicating whether the adapter to which is this module attached
	 * should be notified in case, that the data set of this adapter module was
	 * changed.
	 * </p>
	 *
	 * @param enable <code>True</code> to enable, <code>false</code> to disable.
	 * @see #isAdapterNotificationEnabled()
	 */
	public void enableAdapterNotification(boolean enable) {
		this.bAdapterNotificationEnabled = enable;
	}

	/**
	 * Protected -----------------------------------------------------------------------------------
	 */

	/**
	 * <p>
	 * </p>
	 *
	 * @return
	 */
	protected Parcelable onSaveInstanceState() {
		return BaseSavedState.EMPTY_STATE;
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param savedState
	 */
	protected void onRestoreInstanceState(Parcelable savedState) {
	}

	/**
	 * <p>
	 * Invoked immediately after {@link #dispatchAttachToAdapter(com.wit.android.ui.widget.adapter.module.AdapterModule.ModuleAdapter)}.
	 * </p>
	 *
	 * @param adapter The adapter to which was this module right now attached.
	 */
	protected void onAttachedToAdapter(ModuleAdapter adapter) {
	}

	/**
	 * <p>
	 * Notifies the adapter to which is this module attached. This should be fired
	 * whenever the data set of this adapter module on which the attached adapter
	 * depends, was changed.
	 * </p>
	 *
	 * @see #isAdapterNotificationEnabled()
	 * @see #enableAdapterNotification(boolean)
	 */
	protected final void notifyAdapter() {
		if (isAdapterNotificationEnabled()) {
			mAdapter.notifyDataSetChanged();
		}
	}

	/**
	 * Private -------------------------------------------------------------------------------------
	 */

	/**
	 * Abstract methods ----------------------------------------------------------------------------
	 */

	/**
	 * Inner classes ===============================================================================
	 */

	/**
	 * <h4>Class Overview</h4>
	 * <p>
	 * </p>
	 *
	 * @author Martin Albedinsky
	 */
	public static class BaseSavedState extends AbsSavedState {

		/**
		 * Members =================================================================================
		 */

		/**
		 * <p>
		 * </p>
		 */
		public static final Creator<BaseSavedState> CREATOR = new Creator<BaseSavedState>() {

			/**
			 */
			@Override
			public BaseSavedState createFromParcel(Parcel source) {
				return new BaseSavedState(source);
			}

			/**
			 */
			@Override
			public BaseSavedState[] newArray(int size) {
				return new BaseSavedState[size];
			}
		};

		/**
		 * Constructors ============================================================================
		 */

		/**
		 * <p>
		 * </p>
		 *
		 * @param source
		 */
		protected BaseSavedState(Parcel source) {
			super(source);
		}

		/**
		 * <p>
		 * </p>
		 *
		 * @param parentState
		 */
		protected BaseSavedState(Parcelable parentState) {
			super(parentState);
		}
	}

	/**
	 * Interface ===================================================================================
	 */

	/**
	 * <h4>Interface Overview</h4>
	 * <p>
	 * Base interface for "module based" adapter.
	 * </p>
	 *
	 * @author Martin Albedinsky
	 */
	public static interface ModuleAdapter {

		/**
		 * <p>
		 * See {@link android.widget.BaseAdapter#notifyDataSetChanged()}.
		 * </p>
		 */
		public void notifyDataSetChanged();

		/**
		 * <p>
		 * See {@link android.widget.BaseAdapter#getCount()}.
		 * </p>
		 */
		public int getCount();
	}
}
