/*
 * =================================================================================================
 *                 Copyright (C) 2013 - 2014 Martin Albedinsky [Wolf-ITechnologies]
 * =================================================================================================
 *         Licensed under the Apache License, Version 2.0 or later (further "License" only).
 * -------------------------------------------------------------------------------------------------
 * You may use this file only in compliance with the License. More details and copy of this License
 * you may obtain at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * You can redistribute, modify or publish any part of the code written within this file but as it
 * is described in the License, the software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES or CONDITIONS OF ANY KIND.
 *
 * See the License for the specific language governing permissions and limitations under the License.
 * =================================================================================================
 */
package com.wit.android.widget.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <h4>Class Overview</h4>
 * <p>
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
	 * Adapter to optimize.
	 */
	private OptimizedAdapter mOptimizedAdapter;

	/**
	 * Item view type for the current {@link #performOptimizedGetView(int, android.view.View, android.view.ViewGroup)}
	 * iteration.
	 */
	private int mCurrentItemViewType = -1;

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
	 * Creates new instance of AdapterOptimizer.
	 * </p>
	 *
	 * @param optimizedAdapter Adapter, of which {@link android.widget.BaseAdapter#getView(int, android.view.View, android.view.ViewGroup)}
	 *                         should be optimized.
	 */
	public AdapterOptimizer(OptimizedAdapter optimizedAdapter) {
		if (optimizedAdapter == null) {
			throw new IllegalArgumentException("Invalid optimized adapter.");
		}
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
	 * Performs an optimized algorithm instead of the current optimized adapter's
	 * {@link android.widget.BaseAdapter#getView(int, android.view.View, android.view.ViewGroup)}
	 * method.
	 * </p>
	 *
	 * @param position    The position of the item from the current optimized adapter's data set.
	 * @param convertView The old view to reuse if possible. For more information see
	 *                    documentation of {@link android.widget.BaseAdapter#getView(int, android.view.View, android.view.ViewGroup)}.
	 * @param parent      The parent view, to that will be this view eventually assigned to.
	 * @return A view corresponding to the item at the specified position in the
	 * current optimized adapter's data set.
	 */
	public View performOptimizedGetView(int position, View convertView, ViewGroup parent) {
		Object viewHolder;
		// Obtain current item view type.
		this.mCurrentItemViewType = mOptimizedAdapter.getItemViewType(position);
		if (convertView == null) {
			// Dispatch to create new view.
			convertView = mOptimizedAdapter.onCreateView(position, mOptimizedAdapter.getLayoutInflater(), parent);
			if (convertView == null) {
				throw new NullPointerException("Convert view at position(" + position + ") can't be NULL.");
			}
			// Set holder to the new view.
			convertView.setTag(viewHolder = mOptimizedAdapter.onCreateViewHolder(position, convertView));
		} else {
			viewHolder = convertView.getTag();
		}
		// Dispatch to bind view with data.
		mOptimizedAdapter.onBindView(position, viewHolder);
		return convertView;
	}

	/**
	 * Getters + Setters ---------------------
	 */

	/**
	 * <p>
	 * While there is currently running iteration process trough current
	 * optimized adapter's data set to obtain view for each of its data
	 * set items, this returns view type for the currently iterated position
	 * obtained from adapter by {@link android.widget.BaseAdapter#getItemViewType(int)}.
	 * </p>
	 *
	 * @return View type for the item's view from the current optimized adapter's data set
	 * at the currently iterated position.
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
		 * Invoked to create a view for the item from this optimized adapter's data set
		 * at the specified position. This is invoked only if the <var>convertView</var> for
		 * {@link android.widget.BaseAdapter#getView(int, android.view.View, android.view.ViewGroup)}
		 * is <code>NULL</code>. You can use the given inflater to inflate requested view or you can
		 * create it manually.
		 * </p>
		 *
		 * @param position The position of the item from this optimized adapter's data set.
		 * @param inflater Layout inflater provided by {@link #getLayoutInflater()}.
		 * @param parent   The parent view, to that will be this view eventually assigned to.
		 * @return Created item view.
		 */
		public View onCreateView(int position, LayoutInflater inflater, ViewGroup parent);

		/**
		 * <p>
		 * Invoked to set up and populate a view for an item from this optimized adapter's data set
		 * at the specified <var>position</var>. This is invoked whenever
		 * {@link android.widget.BaseAdapter#getView(int, android.view.View, android.view.ViewGroup)}
		 * on this adapter is called.
		 * </p>
		 *
		 * @param position   The position of item within this optimized adapter's data set.
		 * @param viewHolder Same type of holder as provided by
		 *                   {@link #onCreateViewHolder(int, android.view.View)} for the specified
		 *                   position.
		 */
		public void onBindView(int position, Object viewHolder);

		/**
		 * <p>
		 * Invoked to obtain view holder for the actually created item view.
		 * </p>
		 *
		 * @param position The position of the item from this optimized adapter's data set.
		 * @param itemView Same type of view as provided by
		 *                 {@link #onCreateView(int, android.view.LayoutInflater, android.view.ViewGroup)}
		 *                 at the specified position.
		 * @return Created holder for the given <var>itemView</var>.
		 */
		public Object onCreateViewHolder(int position, View itemView);

		/**
		 * <p>
		 * Returns layout inflater for the current context.
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
