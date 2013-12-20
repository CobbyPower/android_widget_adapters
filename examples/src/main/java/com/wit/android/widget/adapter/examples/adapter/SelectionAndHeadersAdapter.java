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
package com.wit.android.widget.adapter.examples.adapter;

import android.content.Context;

import com.wit.android.widget.adapter.module.SelectionModule;

/**
 * <p>
 * Description.
 * </p>
 *
 * @author Martin Albedinsky
 */
public class SelectionAndHeadersAdapter extends HeadersAlphabeticAdapter {

	/**
	 * Log TAG.
	 */
	private static final String TAG = SelectionAndHeadersAdapter.class.getSimpleName();

	protected final SelectionModule<HeadersAlphabeticAdapter> SELECTOR = new SelectionModule<HeadersAlphabeticAdapter>();
	{
		// Set up multiple selection mode.
		SELECTOR.setMode(SelectionModule.MODE_MULTIPLE);
	}

	public SelectionAndHeadersAdapter(Context context) {
		super(context);
		// Add selector module, but with different id to not overlap headers module.
		addModule(SELECTOR, 0x01);
	}

	public void toggleItemSelectionState(int position) {
		SELECTOR.toggleItemSelectionState(position);
	}

	/**
	 * Returns the count of currently selected items.
	 *
	 * @return
	 */
	public int getSelectedItemsCount() {
		return SELECTOR.getSelectedItemsCount();
	}

	/**
	 * Clears the selected items so no item will be selected.
	 */
	public void clearSelectedItems() {
		// Selector will also fire notify data set change on the attached(this) adapter.
		SELECTOR.clearSelection();
	}

	/**
	 * Removes from the current data set all currently selected items.
	 */
	public void deleteSelectedItems() {
		// Obtain positions of selected items from selector in descending order.
		final int[] positions = SELECTOR.getSelectedPositions(false);
		for (int pos : positions) {
			// Safely (in reverse order) remove all items at selected positions.
			/**
			 * Also remember to correct the position.
			 */
			MODELS.remove(HEADERS.correctPosition(pos));
		}
		// Update headers.
		updateHeaders();
		// Clear selection.
		clearSelectedItems();
	}

	@Override
	public void onBindItemView(int position, Object viewHolder) {
		switch (currentItemViewType()) {
			case VIEW_TYPE_ITEM:
				final ViewHolder holder = (ViewHolder) viewHolder;
				holder.setSelected(SELECTOR.isSelected(position));
			default:
				super.onBindItemView(position, viewHolder);
		}
	}
}
