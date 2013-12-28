/*
 * =================================================================================
 * Copyright (C) 2012 - 2013 Martin Albedinsky [Wolf-ITechnologies]
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
package com.wit.android.widget.adapter.internal;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/**
 * <h4>Class Overview</h4>
 * <p>
 * Updated {@link android.widget.BaseAdapter} implementation with accessible
 * {@link android.content.res.Resources} and also {@link android.view.LayoutInflater}
 * for better management when inflating, creating, and binding the views for an items
 * provided by implementation of this adapter.
 * </p>
 *
 * @author Martin Albedinsky
 */
public abstract class BaseWidgetAdapter extends android.widget.BaseAdapter {

	/**
	 * Constants =============================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = BaseWidgetAdapter.class.getSimpleName();

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
	 * Context in which will be this adapter used.
	 */
	private Context mContext = null;

	/**
	 * Layout inflater from the passed context.
	 */
	private LayoutInflater mLayoutInflater = null;

	/**
	 * Application resources from the passed context.
	 */
	private Resources mResources = null;

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
	 * Creates new instance of BaseWidgetAdapter without context. Note, that
	 * this adapter will not provide {@link android.content.res.Resources} or
	 * {@link android.view.LayoutInflater} which can be obtained form a context.
	 * </p>
	 *
	 * @see #BaseWidgetAdapter(android.content.Context)
	 */
	public BaseWidgetAdapter() {
		this(null);
	}

	/**
	 * <p>
	 * Creates new instance of BaseWidgetAdapter with the given context.
	 * {@link android.content.res.Resources}, {@link android.view.LayoutInflater}
	 * and also the given context can be accessed immediately after an instance
	 * of this adapter is created.
	 * </p>
	 *
	 * @param context Context in which will be this adapter used.
	 */
	public BaseWidgetAdapter(Context context) {
		if (context != null) {
			this.mContext = context;
			this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			this.mResources = context.getResources();
		}
	}

	/**
	 * Methods ===============================
	 */

	/**
	 * Public --------------------------------
	 */

	/**
	 * <p>
	 * Called to save state of this adapter instance. If the given <var>outState</var>
	 * is invalid, there will be created a new bundle and {@link #onSaveInstanceState(android.os.Bundle)}
	 * will be invoked immediately.
	 * </p>
	 *
	 * @param outState Outgoing state in which should this adapter instance save its
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
	 * Called to restore state of this adapter instance. If the given <var>savedState</var>
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
	 * Getters + Setters ---------------------
	 */

	/**
	 * <p>
	 * Returns the context with which was this adapter created.
	 * </p>
	 *
	 * @return Same context as in initialization.
	 */
	public Context getContext() {
		return mContext;
	}

	/**
	 * <p>
	 * Returns layout inflater instance provided by the context passed
	 * during initialization of this adapter.
	 * </p>
	 * <p>
	 * <b>Note</b>, that LayoutInflater is provided only in case, when this
	 * adapter was created with a valid context.
	 * </p>
	 *
	 * @return Layout inflater instance.
	 */
	public LayoutInflater getLayoutInflater() {
		return mLayoutInflater;
	}

	/**
	 * <p>
	 * Returns an application's resources provided by the context
	 * passed during initialization of this adapter.
	 * </p>
	 * <p>
	 * <b>Note</b>, that Resources are provided only in case, when this
	 * adapter was created with a valid context.
	 * </p>
	 *
	 * @return An application's resources.
	 */
	public Resources getResources() {
		return mResources;
	}

	/**
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * Protected -----------------------------
	 */

	/**
	 * <p>
	 * Inflates a new view hierarchy from the given xml resource.
	 * </p>
	 *
	 * @param resource Resource id of a view to inflate.
	 * @return The root view of the inflated view hierarchy.
	 * @see android.view.LayoutInflater#inflate(int, android.view.ViewGroup)
	 */
	protected final View inflate(int resource) {
		return mLayoutInflater.inflate(resource, null);
	}

	/**
	 * <p>
	 * Invoked to save state of this adapter instance. This is invoked whenever
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
	 * Invoked to restore state of this adapter instance. Note, that this is invoked
	 * only in case that the bundle passed to {@link #dispatchRestoreInstanceState(android.os.Bundle)}
	 * is valid.
	 * </p>
	 *
	 * @param savedState Bundle with saved data populated in
	 *                   {@link #onSaveInstanceState(Bundle)}. Always valid bundle.
	 */
	protected void onRestoreInstanceState(Bundle savedState) {
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
