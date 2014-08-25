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
package com.wit.android.ui.widget.adapter;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

/**
 * <h4>Class Overview</h4>
 * <p>
 * todo: description
 * </p>
 *
 * @param <Item> A type of the item presented within a data set of a subclass of this SimpleAdapter.
 * @author Martin Albedinsky
 */
public abstract class SimpleAdapter<Item> extends BaseAdapter<Item> {

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
	// private static final boolean DEBUG_ENABLED = true;

	/**
	 * Flag indicating whether the output trough log-cat is enabled or not.
	 */
	// private static final boolean LOG_ENABLED = true;

	/**
	 * Static members ==============================================================================
	 */

	/**
	 * Members =====================================================================================
	 */

	/**
	 * Data set of this adapter.
	 */
	private List<Item> mItems;

	/**
	 * Constructors ================================================================================
	 */

	/**
	 * <p>
	 * Creates a new instance of SimpleAdapter.
	 * </p>
	 *
	 * @param context Context in which will be this adapter used.
	 */
	public SimpleAdapter(Context context) {
		super(context);
	}

	/**
	 * <p>
	 * Same as {@link #SimpleAdapter(android.content.Context, java.util.List)} so the given <var>items</var>
	 * array will be converted to List.
	 * </p>
	 */
	public SimpleAdapter(Context context, Item[] items) {
		this(context, Arrays.asList(items));
	}

	/**
	 * <p>
	 * Creates a new instance of SimpleAdapter with the given <var>items</var> data set.
	 * </p>
	 *
	 * @param context Context in which will be this adapter used.
	 * @param items   List of items to be used as initial data set for this adapter.
	 */
	public SimpleAdapter(Context context, List<Item> items) {
		super(context);
		this.mItems = items;
	}

	/**
	 * Methods =====================================================================================
	 */

	/**
	 * Public --------------------------------------------------------------------------------------
	 */

	/**
	 * <p>
	 * Returns the current data set of this adapter.
	 * </p>
	 *
	 * @return Data set of this adapter or <code>null</code> if there is no data set presented within
	 * this adapter.
	 */
	public List<Item> getItems() {
		return mItems;
	}

	/**
	 * <p>
	 * Like {@link #changeItems(java.util.List)}, but this will also return the old data set.
	 * </p>
	 */
	public List<Item> swapItems(List<Item> items) {
		final List<Item> oldItems = mItems;
		changeItems(items);
		return oldItems;
	}

	/**
	 * <p>
	 * Changes the current data set of this adapter.
	 * </p>
	 * <p>
	 * This will also notify data set change if the given <var>items</var> are valid, otherwise will
	 * notify data set invalidation.
	 * </p>
	 *
	 * @param items A set of items to set as the current data set for this adapter.
	 * @see #swapItems(java.util.List)
	 * @see #clearItems()
	 */
	public void changeItems(List<Item> items) {
		this.mItems = items;
		if (items != null) {
			notifyDataSetChanged();
		} else {
			notifyDataSetInvalidated();
		}
	}

	/**
	 * <p>
	 * Same as {@link #changeItems(java.util.List)} so the given <var>items</var> array will be converted
	 * to List.
	 * </p>
	 */
	public void changeItems(Item[] items) {
		changeItems(Arrays.asList(items));
	}

	/**
	 * <p>
	 * Clears the current data set of this adapter.
	 * </p>
	 * <p>
	 * This will also notify data set change.
	 * </p>
	 */
	public void clearItems() {
		if (hasItems()) {
			mItems.clear();
			notifyDataSetChanged();
		}
	}

	/**
	 * <p>
	 * Returns flag indicating whether there are some items within the current data set of this adapter
	 * or not.
	 * </p>
	 *
	 * @return <code>True</code> if this adapter has some items, <code>false</code> otherwise.
	 */
	public boolean hasItems() {
		return mItems != null && mItems.size() > 0;
	}

	/**
	 * Getters + Setters ---------------------------------------------------------------------------
	 */

	/**
	 */
	@Override
	public int getCount() {
		return hasItems() ? mItems.size() : 0;
	}

	/**
	 */
	@Override
	public Item getItem(int position) {
		return (hasItems() && (position >= 0 && position < mItems.size())) ? mItems.get(position) : null;
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
