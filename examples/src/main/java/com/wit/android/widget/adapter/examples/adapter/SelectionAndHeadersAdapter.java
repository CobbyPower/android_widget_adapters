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
		assignModule(SELECTOR, 0x01);
	}

	/**
	 * Toggles selection state of the item at the requested position.
	 *
	 * @param position Position of item.
	 */
	public void toggleItemSelectionState(int position) {
		SELECTOR.toggleItemSelectionState(position);
	}

	/**
	 * Returns the count of currently selected items.
	 *
	 * @return Currently selected items count.
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
		/**
		 * Remember that selector module holds positions as they are (it doesn't
		 * know anything about the headers module, so the positions of the selected
		 * items are real positions in the list view but not in the data set)
		 * so don't forgot to always correct raw position with headers module.
		 */
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

	/**
	 */
	@Override
	public void onBindItemView(int position, Object viewHolder) {
		switch (currentItemViewType()) {
			case VIEW_TYPE_ITEM:
				// Handle here only selection and let other stuffs to parent.
				final ViewHolder holder = (ViewHolder) viewHolder;
				holder.setSelected(SELECTOR.isSelected(position));
			default:
				super.onBindItemView(position, viewHolder);
		}
	}
}
