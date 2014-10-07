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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * <h4>Class Overview</h4>
 * todo: description
 *
 * @param <Item> A type of the item presented within a data set of a subclass of this SimpleSpinnerAdapter.
 * @author Martin Albedinsky
 */
public abstract class SimpleSpinnerAdapter<Item> extends BaseSpinnerAdapter<Item> {

	/**
	 * Interface ===================================================================================
	 */

	/**
	 * Constants ===================================================================================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = "SimpleSpinnerAdapter";

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
	 * Creates a new instance of SimpleSpinnerAdapter.
	 *
	 * @param context Context in which will be this adapter used.
	 */
	public SimpleSpinnerAdapter(@NonNull Context context) {
		super(context);
	}

	/**
	 * Same as {@link #SimpleSpinnerAdapter(android.content.Context, java.util.List)} so the given
	 * <var>items</var> array will be converted to List.
	 */
	public SimpleSpinnerAdapter(@NonNull Context context, @NonNull Item[] items) {
		this(context, Arrays.asList(items));
	}

	/**
	 * Creates a new instance of SimpleSpinnerAdapter with the given <var>items</var> data set.
	 *
	 * @param context Context in which will be this adapter used.
	 * @param items   List of items to be used as initial data set for this adapter.
	 */
	public SimpleSpinnerAdapter(@NonNull Context context, @NonNull List<Item> items) {
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
	 * Returns the current data set of this adapter.
	 *
	 * @return Data set of this adapter or <code>null</code> if there is no data set presented within
	 * this adapter.
	 */
	@Nullable
	public List<Item> getItems() {
		return mItems;
	}

	/**
	 * Like {@link #changeItems(java.util.List)}, but this will also return the old data set.
	 */
	@Nullable
	public List<Item> swapItems(@Nullable List<Item> items) {
		final List<Item> oldItems = mItems;
		changeItems(items);
		return oldItems;
	}

	/**
	 * Changes the current data set of this adapter.
	 * <p/>
	 * This will also notify data set change if the given <var>items</var> are valid, otherwise will
	 * notify data set invalidation.
	 *
	 * @param items A set of items to set as the current data set for this adapter.
	 * @see #swapItems(java.util.List)
	 * @see #clearItems()
	 */
	public void changeItems(@Nullable List<Item> items) {
		this.mItems = items;
		if (items != null) {
			notifyDataSetChanged();
		} else {
			notifyDataSetInvalidated();
		}
	}

	/**
	 * Same as {@link #changeItems(java.util.List)} so the given <var>items</var> array will be converted
	 * to List.
	 */
	public void changeItems(@NonNull Item[] items) {
		changeItems(Arrays.asList(items));
	}

	/**
	 * Clears the current data set of this adapter.
	 * <p/>
	 * This will also notify data set change.
	 */
	public void clearItems() {
		if (hasItems()) {
			mItems.clear();
			notifyDataSetChanged();
		}
	}

	/**
	 * Returns flag indicating whether there are some items within the current data set of this adapter
	 * or not.
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
	@Nullable
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
	 * Inner classes ===============================================================================
	 */
}
