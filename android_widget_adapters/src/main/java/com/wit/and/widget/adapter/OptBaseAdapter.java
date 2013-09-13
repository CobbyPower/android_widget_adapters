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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wit.and.widget.adapter.internal.BaseAdapter;

/**
 * <h4>Class Overview</h4>
 * <p>
 * Optimized base adapter to handle optimization of creating views for the
 * <code>ListView, GridView, ...</code> items.
 * </p>
 * 
 * @author Martin Albedinsky
 * @see BaseAdapter
 */
public abstract class OptBaseAdapter extends BaseAdapter {

	/**
	 * Constants =============================
	 */

    /**
     * Log TAG.
     */
    // private static final String TAG = OptBaseAdapter.class.getSimpleName();

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
	 * Optimization manager for getView() method.
	 */
	private final Optimizer OPTIMIZER = new Optimizer();

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
	 * See {@link BaseAdapter#BaseAdapter(Context)}.
	 * </p>
	 * 
	 * @param context
     *          Context in which is being adapter created.
	 */
	public OptBaseAdapter(Context context) {
		super(context);
	}

	/**
	 * Methods ===============================
	 */

	/**
	 * Public --------------------------------
	 */

	/**
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup root) {
		return OPTIMIZER.optimizeGetView(position, convertView, root);
	}

	/**
	 * Getters + Setters ---------------------
	 */

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
	 * <p>
	 * Invoked to create view for the item from the adapter data set. This is
	 * invoked only if the convert view is equal to <code>null</code>. To create
	 * view you can use the given inflater or create it manually.
	 * </p>
	 * 
	 * @param position
	 * @param inflater
	 * @param root
	 * @return Created item view.
	 */
	protected abstract View onCreateItemView(int position, LayoutInflater inflater, ViewGroup root);

	/**
	 * <p>
	 * Invoked to bind item view by data. This is invoked whether the
	 * <code>getView()</code> method on the adapter is called.
	 * </p>
	 * 
	 * @param position
	 * @param viewHolder
	 *            View holder of the actual item view on the given position.
	 *            This is also holder which was set to the view by
	 *            <code>onCreateItemViewHolder()</code> method while was view created.
	 */
	protected abstract void onBindItemView(int position, Object viewHolder);

	/**
     * <p>
	 * Invoked to obtain view holder for the actually created item view.
	 * </p>
	 * 
	 * @param position
	 * @param itemView
	 *            View returned by <code>onCreateItemView</code> method.
	 * @return
	 */
	protected abstract Object onCreateItemViewHolder(int position, View itemView);

	/**
	 * Inner classes =========================
	 */

	/**
	 * <h4>Class Overview</h4>
	 * <p>
	 * Optimization manager which handles the optimization of the
	 * {@link #getView(int, View, ViewGroup)} method in the {@link BaseAdapter}.
	 * </p>
	 * 
	 * @author Martin Albedinsky
	 */
	protected class Optimizer {

		/**
		 * Constants =============================
		 */

        /**
         * Log TAG.
         */
        // private static final String TAG = Optimizer.class.getSimpleName();

		/**
		 * Enums =================================
		 */

		/**
		 * Members ===============================
		 */

		/**
		 * Listeners -----------------------------
		 */

		/**
		 * Static --------------------------------
		 */

		/**
		 * Arrays --------------------------------
		 */

		/**
		 * Booleans ------------------------------
		 */

		/**
		 * Indicates if debug private output trough log-cat is enabled.
		 */
		// private static final boolean DEBUG = true;

		/**
		 * Indicates if logging for user output trough log-cat is enabled.
		 */
		// private static final boolean USER_LOG = true;

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
		 * Getters + Setters ---------------------
		 */

		/**
		 * Protected -----------------------------
		 */

		/**
		 * <br/>
		 * <h5><i>protected View optimizeGetView(int position, View convertView,
		 * ViewGroup root)</i></h5>
		 * <p>
		 * Runs the optimized algorithm for the
		 * {@link #getView(int, View, ViewGroup)} method of the
		 * {@link BaseAdapter}.
		 * </p>
		 * 
		 * @param position
		 *            Position for the item in the adapter.
		 * @param convertView
		 *            Convert view for the item.
		 * @param root
		 *            View group of the adapter.
		 * @return
		 */
		protected View optimizeGetView(int position, View convertView, ViewGroup root) {
			Object viewHolder;

			// Check if we have converted view.
			if (convertView == null) {
				convertView = onCreateItemView(position, getLayoutInflater(), root);

                if (convertView == null) {
                    throw new NullPointerException("Convert view at position(" + position + ") can't be NULL.");
                }

				// Set holder to the new view.
				convertView.setTag(viewHolder = onCreateItemViewHolder(position, convertView));
			} else {
				viewHolder = convertView.getTag();
			}

			// Bind item view with data.
			onBindItemView(position, viewHolder);

			// Return new/recreated item view with binded data.
			return convertView;
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

	/**
	 * Interface =============================
	 */
}
