/*
 * =================================================================================================
 *                    Copyright (C) 2014 Martin Albedinsky [Wolf-ITechnologies]
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

import android.content.Context;

import java.util.List;

/**
 * <h4>Class Overview</h4>
 * <p>
 * Description.
 * </p>
 *
 * @author Martin Albedinsky
 */
public abstract class SimpleAdapter<I> extends BaseAdapter {

	/**
	 * Constants ===================================================================================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = SimpleAdapter.class.getSimpleName();

	/**
	 * Flag indicating whether the debug output trough log-cat is enabled or not.
	 */
	// private static final boolean DEBUG = true;

	/**
	 * Flag indicating whether the output for user trough log-cat is enabled or not.
	 */
	// private static final boolean USER_LOG = true;

	/**
	 * Enums =======================================================================================
	 */

	/**
	 * Static members ==============================================================================
	 */

	/**
	 * Members =====================================================================================
	 */

	/**
	 * Listeners -----------------------------------------------------------------------------------
	 */

	/**
	 * Arrays --------------------------------------------------------------------------------------
	 */

	/**
	 *
	 */
	private List<I> aItems;

	/**
	 * Booleans ------------------------------------------------------------------------------------
	 */

	/**
	 * Constructors ================================================================================
	 */

	/**
	 * <p>
	 * </p>
	 */
	public SimpleAdapter() {
		super();
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param context
	 */
	public SimpleAdapter(Context context) {
		super(context);
	}

	/**
	 * Methods =====================================================================================
	 */

	/**
	 * Public --------------------------------------------------------------------------------------
	 */

	/**
	 * <p>
	 * </p>
	 */
	public List<I> swapItems(List<I> items) {
		final List<I> oldItems = aItems;
		changeItems(items);
		return oldItems;
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param items
	 * @see #swapItems(java.util.List)
	 */
	public void changeItems(List<I> items) {
		if (items != null) {
			this.aItems = items;
			notifyDataSetChanged();
		}
	}

	/**
	 * <p>
	 * </p>
	 */
	public void clearItems() {
		if (hasItems()) {
			aItems.clear();
		}
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @return
	 */
	public boolean hasItems() {
		return aItems != null && aItems.size() > 0;
	}

	/**
	 * Getters + Setters ---------------------------------------------------------------------------
	 */

	/**
	 */
	@Override
	public int getCount() {
		return hasItems() ? aItems.size() : 0;
	}

	/**
	 */
	@Override
	public I getItem(int position) {
		return (hasItems() && (position >= 0 && position < aItems.size())) ? aItems.get(position) : null;
	}

	/**
	 * Protected -----------------------------------------------------------------------------------
	 */

	/**
	 * Private -------------------------------------------------------------------------------------
	 */

	/**
	 * Abstract methods ----------------------------------------------------------------------------
	 */

	/**
	 * Inner classes ===============================================================================
	 */

	/**
	 * Interface ===================================================================================
	 */
}
