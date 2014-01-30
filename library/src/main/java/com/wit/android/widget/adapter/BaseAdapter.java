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
package com.wit.android.widget.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.wit.android.widget.adapter.internal.BaseWidgetAdapter;

/**
 * <h4>Class Overview</h4>
 * <p>
 * TODO:
 * </p>
 *
 * @author Martin Albedinsky
 * @see com.wit.android.widget.adapter.internal.BaseWidgetAdapter
 * @see com.wit.android.widget.adapter.AdapterOptimizer
 */
public abstract class BaseAdapter extends BaseWidgetAdapter implements AdapterOptimizer.OptimizedAdapter {

    /**
     * Constants =============================
     */

    /**
     * Log TAG.
     */
    // private static final String TAG = BaseAdapter.class.getSimpleName();

    /**
     * Flag indicating whether the debug output trough log-cat is enabled or not.
     */
    // private static final boolean DEBUG = true;

    /**
     * Flag indicating whether the output for user trough log-cat is enabled or not.
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
     * Optimization manager for getView() method.
     */
    private final AdapterOptimizer OPTIMIZER = new AdapterOptimizer(this);;

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
     * Creates new instance of optimized BaseAdapter with the given
     * context.
     * </p>
     *
     * @param context Context in which will be this adapter used.
     */
    public BaseAdapter(Context context) {
        super(context);
    }

	/**
     * Methods ===============================
     */

    /**
     * Public --------------------------------
     */

    /**
     * <p>
     * Performs optimized algorithm for this method.
     * </p>
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return OPTIMIZER.performOptimizedGetView(position, convertView, parent);
    }

    /**
     * Getters + Setters ---------------------
     */

	/**
     * Protected -----------------------------
     */

	/**
	 * <p>
	 * Returns item's view type for the currently iterated position.
	 * </p>
	 *
	 * @return View type.
	 * @see AdapterOptimizer#getCurrentItemViewType()
	 */
	protected int currentItemViewType() {
		return OPTIMIZER.getCurrentItemViewType();
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
