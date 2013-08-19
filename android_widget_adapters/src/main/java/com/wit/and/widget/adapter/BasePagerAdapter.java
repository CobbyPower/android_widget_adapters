/*
 * =================================================================================
 * Copyright (C) 2012 Martin Albedinsky [Wolf-ITechnologies]
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
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * <p>
 * public abstract class
 * </p>
 * <h5>BasePagerAdapter</h5>
 * <p>
 * extends {@link FragmentPagerAdapter}
 * </p>
 * <h4>Class Overview</h4>
 * <p>
 * 
 * </p>
 * 
 * @author Martin Albedinsky
 */
public abstract class BasePagerAdapter extends FragmentPagerAdapter {

	/**
	 * Constants =============================
	 */

    /**
     * Log TAG.
     */
    // private static final String TAG = BasePagerAdapter.class.getSimpleName();

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
	 * Context in which was this adapter created.
	 */
	private Context mContext;

	/**
	 * Resources from parent activity.
	 */
	private Resources mResources;

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
	 * <h5><i>public BasePagerAdapter(FragmentManager fragmentManager)</i></h5>
	 * <p>
	 * Constructor #1.
	 * </p>
	 * 
	 * @param fragmentManager
	 */
	public BasePagerAdapter(FragmentManager fragmentManager) {
		this(fragmentManager, null);
	}

	/**
	 * <br/>
	 * <h5><i>public BasePagerAdapter(FragmentManager fragmentManager,
	 * Context context)</i></h5>
	 * <p>
	 * Constructor #2.
	 * </p>
	 * 
	 * @param fragmentManager
	 * @param context
	 *            Context in which will be this adapter used.
	 */
	public BasePagerAdapter(FragmentManager fragmentManager, Context context) {
		super(fragmentManager);

		if (context != null) {
			this.mContext = context;
			this.mResources = context.getResources();
		}
	}

	/**
	 * <br/>
	 * <h5><i>public CharSequence[] getItems()</i></h5>
	 * <p>
	 * Returns items of this adapter.
	 * </p>
	 * 
	 * @return
	 */
	public CharSequence[] getItems() {
		return new CharSequence[] {};
	}

	/**
	 * <br/>
	 * <h5><i>public CharSequence getPageTitle(int position)</i></h5>
	 * <p>
	 * Returns page title.
	 * </p>
	 * 
	 * @param position
	 *            Position of the fragment for which you want title.
	 * @return
	 */
	public CharSequence getPageTitle(int position) {
		return "";
	}

	/**
	 * Methods ===============================
	 */

	/**
	 * Public --------------------------------
	 */

	/**
	 * Getters + Setters ---------------------
	 */

	/**
	 * <br/>
	 * <h5><i>public final Context getContext()</i></h5>
	 * <p>
	 * Returns context in which was this adapter created.
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
	 * <h5><i>public final Resources resources()</i></h5>
	 * <p>
	 * Returns resources from parent activity.
	 * </p>
	 * 
	 * @return
	 */
	public final Resources getResources() {
		return mResources;
	}

	/**
	 * Protected -----------------------------
	 */

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
