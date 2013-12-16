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

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/**
 * <h4>Class Overview</h4>
 * <p>
 * Updated {@link android.widget.BaseAdapter} for better handling and using the widget adapters.
 * </p>
 *
 * @see android.widget.BaseAdapter
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
	 * Context in which is this adapter initialized.
	 */
	private Context mContext = null;

	/**
	 * Layout inflater from parent activity.
	 */
	private LayoutInflater mLayoutInflater = null;

	/**
	 * Resources from parent activity.
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
	 * </p>
	 */
	public BaseWidgetAdapter() {
		this(null);
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param context Context in which will be this adapter used.
	 */
	public BaseWidgetAdapter(Context context) {
		if (context != null) {
			this.mContext = context;
			this.mLayoutInflater = LayoutInflater.from(context);
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
	 * Invoked from the parent context which being currently destroyed to save actual
	 * adapter state.
	 * </p>
	 *
	 * @param outState Outgoing state.
	 */
	public void onSaveInstanceState(Bundle outState) {
	}

	/**
	 * <p>
	 * Invoked from the parent context which was restored with previous saved
	 * instance state to restore adapter state.
	 * </p>
	 *
	 * @param savedState Bundle from parent context with saved data populated in
	 *                   {@link #onSaveInstanceState(Bundle)}.
	 */
	public void onRestoreInstanceState(Bundle savedState) {
	}

	/**
	 * Getters + Setters ---------------------
	 */

	/**
	 * <p>
	 * Returns context in which was this adapter initialized.
	 * </p>
	 *
	 * @return Can be {@link Activity} context or {@link Context} or
	 * <code>null</code>.
	 */
	public Context getContext() {
		return mContext;
	}

	/**
	 * <p>
	 * Returns layout inflater from parent activity.
	 * </p>
	 *
	 * @return
	 */
	public LayoutInflater getLayoutInflater() {
		return mLayoutInflater;
	}

	/**
	 * <p>
	 * Returns application resources.
	 * </p>
	 *
	 * @return
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
	 * Inflates the layout for the given resource id.
	 * </p>
	 *
	 * @param resource Resource id of layout to inflate.
	 * @return Inflated layout if the given resource id exists.
	 */
	protected final View inflate(int resource) {
		return mLayoutInflater.inflate(resource, null, false);
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
