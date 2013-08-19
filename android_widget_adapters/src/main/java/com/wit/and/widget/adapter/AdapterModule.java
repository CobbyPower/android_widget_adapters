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
package com.wit.and.widget.adapter;

import android.os.Bundle;
import android.widget.BaseAdapter;

/**
 * <p>
 * public abstract class
 * </p>
 * <h5>AdapterModule</h5>
 * <p>
 * </p>
 * <h4>Class Overview</h4>
 * <p>
 * </p>
 * 
 * @param <Adapter>
 *            Type of the adapter for which is this module created.
 * 
 * @author Martin Albedinsky
 */
public abstract class AdapterModule<Adapter extends BaseAdapter> {
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
	 * Constructors ==========================
	 */

	/**
	 * Methods ===============================
	 */

	/**
	 * Public --------------------------------
	 */

	/**
	 * <br/>
	 * <h5><i>public void onSaveInstanceState(Bundle outState)</i></h5>
	 * <p>
	 * Invoked to save the module state.
	 * </p>
	 * 
	 * @param outState
	 *            Outgoing bundle state.
	 */
	public void onSaveInstanceState(Bundle outState) {
	}

	/**
	 * <br/>
	 * <h5><i>public void onRestoreInstanceState(Bundle savedState)</i></h5>
	 * <p>
	 * Invoked to restore the module state.
	 * </p>
	 * 
	 * @param savedState
	 *            Saved adapter state.
	 */
	public void onRestoreInstanceState(Bundle savedState) {
	}

	/**
	 * Getters + Setters ---------------------
	 */

	/**
	 * Protected -----------------------------
	 */

	protected void onAttachToAdapter(Adapter adapter) {
		mAdapter = adapter;
	}

	/**
	 * 
	 * @return
	 */
	protected final Adapter getAdapter() {
		return mAdapter;
	}

	/**
	 * 
	 */
	protected final void notifyAdapter() {
		this.mAdapter.notifyDataSetChanged();
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
