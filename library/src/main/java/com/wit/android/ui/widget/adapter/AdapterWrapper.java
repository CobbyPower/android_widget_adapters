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

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.WrapperListAdapter;

/**
 * <h4>Class Overview</h4>
 * <p>
 * Description.
 * </p>
 *
 * @author Martin Albedinsky
 */
public abstract class AdapterWrapper implements WrapperListAdapter {

	/**
	 * Constants ===================================================================================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = AdapterWrapper.class.getSimpleName();

	/**
	 * Flag indicating whether the output trough log-cat is enabled or not.
	 */
	// private static final boolean LOG_ENABLED = true;

	/**
	 * Flag indicating whether the debug output trough log-cat is enabled or not.
	 */
	// private static final boolean DEBUG_ENABLED = true;

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
	 *
	 */
	protected final ListAdapter mAdapter;

	/**
	 * Arrays --------------------------------------------------------------------------------------
	 */

	/**
	 * Booleans ------------------------------------------------------------------------------------
	 */

	/**
	 * Constructors ================================================================================
	 */

	/**
	 * <p>
	 * </p>
	 *
	 * @param adapter
	 */
	public AdapterWrapper(ListAdapter adapter) {
		if (adapter == null) {
			throw new NullPointerException("Can not wrap invalid adapter.");
		}
		this.mAdapter = adapter;
	}

	/**
	 * Methods =====================================================================================
	 */

	/**
	 * Wrapped methods -----------------------------------------------------------------------------
	 */

	/**
	 */
	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		mAdapter.registerDataSetObserver(observer);
	}

	/**
	 */
	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		mAdapter.unregisterDataSetObserver(observer);
	}

	/**
	 */
	@Override
	public int getCount() {
		return mAdapter.getCount();
	}

	/**
	 */
	@Override
	public Object getItem(int position) {
		return mAdapter.getItem(position);
	}

	/**
	 */
	@Override
	public long getItemId(int position) {
		return mAdapter.getItemId(position);
	}

	/**
	 */
	@Override
	public boolean hasStableIds() {
		return mAdapter.hasStableIds();
	}

	/**
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return mAdapter.getView(position, convertView, parent);
	}

	/**
	 */
	@Override
	public int getItemViewType(int position) {
		return mAdapter.getItemViewType(position);
	}

	/**
	 */
	@Override
	public int getViewTypeCount() {
		return mAdapter.getViewTypeCount();
	}

	/**
	 */
	@Override
	public boolean isEmpty() {
		return mAdapter.isEmpty();
	}

	/**
	 * Public --------------------------------------------------------------------------------------
	 */

	/**
	 * Getters + Setters ---------------------------------------------------------------------------
	 */

	/**
	 * <p>
	 * </p>
	 *
	 * @return
	 */
	public ListAdapter getWrappedAdapter() {
		return mAdapter;
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
