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
package com.wit.and.widget.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/**
 * <p>
 * public abstract class
 * </p>
 * <h5>BaseAdapter</h5>
 * <p>
 * extends {@link android.widget.BaseAdapter}
 * </p>
 * <h4>Class Overview</h4>
 * <p>
 * Updated <code>BaseAdapter</code> for better handling of using the adapters.
 * </p>
 * 
 * @see android.widget.BaseAdapter
 * @author Martin Albedinsky
 */
public abstract class BaseAdapter extends android.widget.BaseAdapter {
	/**
	 * Constants =============================
	 */

    /**
     * Log TAG.
     */
    // private static final String TAG = BaseAdapter.class.getSimpleName();

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
	 * <br/>
	 * <h5><i>public BaseAdapter()</i></h5>
	 * <p>
	 * Constructor #1.
	 * </p>
	 */
	public BaseAdapter() {
		this(null);
	}

	/**
	 * <br/>
	 * <h5><i>public BaseAdapter(Context context)</i></h5>
	 * <p>
	 * Constructor #2.
	 * </p>
	 * 
	 * @param context
	 *            Context in which will be this adapter used.
	 */
	public BaseAdapter(Context context) {
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
	 * <br/>
	 * <h5><i>public void onSaveState(Bundle outState)</i></h5>
	 * <p>
	 * Invoked from the parent context which being currently destroyed to save actual
	 * adapter state.
	 * </p>
	 * 
	 * @param outState
	 *            Outgoing state.
	 */
	public void onSaveInstanceState(Bundle outState) {
		// Custom implementation.
	}

	/**
	 * <br/>
	 * <h5><i>public void onRestoreState(Bundle savedState)</i></h5>
	 * <p>
	 * Invoked from the parent context which was restored with previous saved
	 * instance state to restore adapter state.
	 * </p>
	 * 
	 * @param savedState
	 *            Bundle from parent context with saved data populated in
	 *            {@link #onSaveInstanceState(Bundle)}.
	 */
	public void onRestoreInstanceState(Bundle savedState) {
		// Custom implementation.
	}

	/**
	 * Getters + Setters ---------------------
	 */

	/**
	 * <br/>
	 * <h5><i>public final Context getContext()</i></h5>
	 * <p>
	 * Returns context in which was this adapter initialized.
	 * </p>
	 * 
	 * @return Can be {@link Activity} context or {@link Context} or
	 *         <code>null</code>.
	 */
	public final Context getContext() {
		return mContext;
	}

	/**
	 * <br/>
	 * <h5><i>public final LayoutInflater getLayoutInflater()</i></h5>
	 * <p>
	 * Returns layout inflater from parent activity.
	 * </p>
	 * 
	 * @return
	 */
	public final LayoutInflater getLayoutInflater() {
		return mLayoutInflater;
	}

	/**
	 * <br/>
	 * <h5><i>public final Resources getResources()</i></h5>
	 * <p>
	 * Returns application resources.
	 * </p>
	 * 
	 * @return
	 */
	public final Resources getResources() {
		return mResources;
	}

	/**
	 * 
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * Protected -----------------------------
	 */

	/**
	 * <br/>
	 * <h5><i>protected final View inflate(int resource)</i></h5>
	 * <p>
	 * Inflates the given layout resource id.
	 * </p>
	 * 
	 * @param resource
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
