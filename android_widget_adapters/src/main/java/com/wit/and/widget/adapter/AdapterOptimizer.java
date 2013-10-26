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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <h4>Class Overview</h4>
 * <p>
 * Optimization manager which handles the optimization of the
 * {@link android.widget.BaseAdapter#getView(int, View, ViewGroup)} method of adapter.
 * </p>
 *
 * @author Martin Albedinsky
 */
public class AdapterOptimizer {

	/**
	 * Constants =============================
	 */

	/**
	 * Log TAG.
	 */
	//private static final String TAG = AdapterOptimizer.class.getSimpleName();

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
	 * Base adapter to optimize.
	 */
	private OptimizedAdapter mOptimizedAdapter;

	/**
	 *
	 */
	private int mCurrentItemViewType = 0;

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
	 * Creates new instance of adapter optimizer.
	 * </p>
	 *
	 * @param optimizedAdapter Base adapter which should be optimized.
	 */
	public AdapterOptimizer(OptimizedAdapter optimizedAdapter) {
		if (optimizedAdapter == null)
			throw new IllegalArgumentException("Invalid optimized adapter.");

		// Assign.
		this.mOptimizedAdapter = optimizedAdapter;
	}

	/**
	 * Methods ===============================
	 */

	/**
	 * Public --------------------------------
	 */

	/**
	 * <p>
	 * Runs the optimized algorithm for the
	 * {@link android.widget.BaseAdapter#getView(int, View, ViewGroup)}
	 * method of the current optimized adapter.
	 * </p>
	 *
	 * @param position    Position of the item in the adapter.
	 * @param convertView Convert view for the item.
	 * @param root        Parent view.
	 * @return Created item's view.
	 */
	public View performOptimizedGetView(int position, View convertView, ViewGroup root) {
		Object viewHolder;

		// Obtain current item view type.
		this.mCurrentItemViewType = mOptimizedAdapter.getItemViewType(position);

		// Check if we have converted view.
		if (convertView == null) {
			convertView = mOptimizedAdapter.onCreateItemView(position, mOptimizedAdapter.getLayoutInflater(), root);

			if (convertView == null) {
				throw new NullPointerException("Convert view at position(" + position + ") can't be NULL.");
			}

			// Set holder to the new view.
			convertView.setTag(viewHolder = mOptimizedAdapter.onCreateItemViewHolder(position, convertView));
		} else {
			viewHolder = convertView.getTag();
		}

		// Bind item view with data.
		mOptimizedAdapter.onBindItemView(position, viewHolder);

		// Return new/recreated item view with binded data.
		return convertView;
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
	public int getCurrentItemViewType() {
		return mCurrentItemViewType;
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

	/**
	 * <h4>Class Overview</h4>
	 * <p>
	 * </p>
	 *
	 * @author Martin Albedinsky
	 */
	public static interface OptimizedAdapter {

		/**
		 * Methods ===============================
		 */

		/**
		 * <p>
		 * Invoked to create view for the item from the adapter data set. This is
		 * invoked only if the convert view is equal to <code>null</code>. To create
		 * view you can use the given inflater or create it manually.
		 * </p>
		 *
		 * @param position Position of view in the adapter view.
		 * @param inflater Layout inflater provided by {@link #getLayoutInflater()}.
		 * @param root     Parent view.
		 * @return Created item view.
		 */
		public View onCreateItemView(int position, LayoutInflater inflater, ViewGroup root);

		/**
		 * <p>
		 * Invoked to bind item view with data. This is invoked whether the
		 * <code>getView()</code> method on the adapter is called.
		 * </p>
		 *
		 * @param position   Position of view in the adapter view.
		 * @param viewHolder View holder of the actual item view on the given position.
		 *                   This is also holder which was set to the view by
		 *                   <code>onCreateItemViewHolder()</code> method while was view created.
		 */
		public void onBindItemView(int position, Object viewHolder);

		/**
		 * <p>
		 * Invoked to obtain view holder for the actually created item view.
		 * </p>
		 *
		 * @param position Position of the <var>itemView</var> in the adapter view.
		 * @param itemView View returned by <code>onCreateItemView</code> method.
		 * @return Holder for the requested item's view.
		 */
		public Object onCreateItemViewHolder(int position, View itemView);

		/**
		 * <p>
		 * Returns layout inflater.
		 * </p>
		 *
		 * @return Layout inflater.
		 */
		public LayoutInflater getLayoutInflater();

		/**
		 * <p>
		 * See {@link android.widget.BaseAdapter#getItemViewType(int)}.
		 * </p>
		 */
		public int getItemViewType(int position);
	}
}
