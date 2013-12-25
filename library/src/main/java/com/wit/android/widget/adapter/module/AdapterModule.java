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
package com.wit.android.widget.adapter.module;

import android.os.Bundle;

/**
 * <h4>Class Overview</h4>
 * <p>
 * </p>
 *
 * @param <Adapter> Type of the adapter for which is this module created.
 *
 * @author Martin Albedinsky
 */
public abstract class AdapterModule<Adapter extends AdapterModule.ModuleAdapter> {

	/**
	 * Constants =============================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = AdapterModule.class.getSimpleName();

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
	 *
	 */
	private Adapter mAdapter;

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
	 *
	 */
	private boolean bAdapterNotificationEnabled = true;

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
	 * <p>
	 * </p>
	 *
	 * @param outState
	 */
	public void dispatchSaveInstanceState(Bundle outState) {
		if (outState != null) {
			onSaveInstanceState(outState);
		}
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param savedState
	 */
	public void dispatchRestoreInstanceState(Bundle savedState) {
		if (savedState != null) {
			onRestoreInstanceState(savedState);
		}
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param adapter
	 */
	public final void dispatchAttachToAdapter(Adapter adapter) {
		this.mAdapter = adapter;
		onAttachToAdapter(adapter);
	}

	/**
	 * Getters + Setters ---------------------
	 */

	/**
	 * <p>
	 * </p>
	 *
	 * @return
	 */
	public boolean isAdapterNotificationEnabled() {
		return bAdapterNotificationEnabled;
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param enable
	 */
	public void enableAdapterNotification(boolean enable) {
		this.bAdapterNotificationEnabled = enable;
	}

	/**
	 * Protected -----------------------------
	 */

	/**
	 * <p>
	 * Invoked to save state of this module.
	 * </p>
	 *
	 * @param outState Outgoing bundle state. Always valid bundle.
	 */
	protected void onSaveInstanceState(Bundle outState) {
	}

	/**
	 * <p>
	 * Invoked to restore saved state of this module.
	 * </p>
	 *
	 * @param savedState Saved adapter state. Always valid bundle.
	 */
	protected void onRestoreInstanceState(Bundle savedState) {
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param adapter
	 */
	protected void onAttachToAdapter(Adapter adapter) {
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @return
	 */
	protected final Adapter getAdapter() {
		return mAdapter;
	}

	/**
	 * <p>
	 * </p>
	 */
	protected final void notifyAdapter() {
		if (isAdapterNotificationEnabled()) {
			mAdapter.notifyDataSetChanged();
		}
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
		 * Methods ===============================
		 */

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
