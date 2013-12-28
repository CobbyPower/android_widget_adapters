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
 * Base implementation for adapter module, which can help to clearly manage
 * a data set of an adapter.
 * </p>
 *
 * @param <Adapter> Type of the adapter for which can be this module created and used.
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
	 * The adapter to which is this module attached.
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
	 * Flag indicating whether the adapter to which is this module attached, should be
	 * notified in case, that the data set of this adapter module was changed.
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
	 * Called to save state of this adapter module instance. If the given <var>outState</var>
	 * is invalid, there will be created a new bundle and {@link #onSaveInstanceState(android.os.Bundle)}
	 * will be invoked immediately.
	 * </p>
	 *
	 * @param outState Outgoing state in which should this adapter module instance save its
	 *                 state.
	 * @see #onSaveInstanceState(android.os.Bundle)
	 */
	public void dispatchSaveInstanceState(Bundle outState) {
		if (outState != null) {
			outState = new Bundle();
		}
		onSaveInstanceState(outState);
	}

	/**
	 * <p>
	 * Called to restore state of this adapter module instance. If the given <var>savedState</var>
	 * is valid, {@link #onRestoreInstanceState(android.os.Bundle)} will be invoked
	 * immediately.
	 * </p>
	 *
	 * @param savedState Should be the bundle with saved state in
	 *                   {@link #onSaveInstanceState(android.os.Bundle)}.
	 * @see #onRestoreInstanceState(android.os.Bundle)
	 */
	public void dispatchRestoreInstanceState(Bundle savedState) {
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
	public final void dispatchAttachToAdapter(Adapter adapter) {
		onAttachedToAdapter(mAdapter = adapter);
	}

	/**
	 * Getters + Setters ---------------------
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
	 * Protected -----------------------------
	 */

	/**
	 * <p>
	 * Invoked to save state of this adapter module instance. This is invoked whenever
	 * {@link #dispatchSaveInstanceState(android.os.Bundle)} is called.
	 * </p>
	 *
	 * @param outState Outgoing state. Always valid bundle.
	 * @see #onRestoreInstanceState(android.os.Bundle)
	 */
	protected void onSaveInstanceState(Bundle outState) {
	}

	/**
	 * <p>
	 * Invoked to restore state of this adapter module instance. Note, that this is invoked
	 * only in case that the bundle passed to {@link #dispatchRestoreInstanceState(android.os.Bundle)}
	 * is valid.
	 * </p>
	 *
	 * @param savedState Bundle with saved data populated in the
	 *                   {@link #onSaveInstanceState(Bundle)}. Always valid bundle.
	 */
	protected void onRestoreInstanceState(Bundle savedState) {
	}

	/**
	 * <p>
	 * Invoked immediately after {@link #dispatchAttachToAdapter(com.wit.android.widget.adapter.module.AdapterModule.ModuleAdapter)}.
	 * </p>
	 *
	 * @param adapter The adapter to which was this module right now attached.
	 */
	protected void onAttachedToAdapter(Adapter adapter) {
	}

	/**
	 * <p>
	 * Returns the adapter to which is this module attached.
	 * </p>
	 *
	 * @return The attached adapter.
	 */
	protected final Adapter getAdapter() {
		return mAdapter;
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
