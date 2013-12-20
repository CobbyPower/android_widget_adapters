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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wit.android.widget.adapter.BaseMultiAdapter;
import com.wit.android.widget.adapter.examples.R;
import com.wit.android.widget.adapter.module.SelectionModule;
import com.wit.android.widget.adapter.widget.StateTextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * Description.
 * </p>
 *
 * @author Martin Albedinsky
 */
public class SelectionSingleAdapter extends BaseMultiAdapter<SelectionSingleAdapter> {

	/**
	 * Log TAG.
	 */
	private static final String TAG = SelectionSingleAdapter.class.getSimpleName();

	final List<String> MODELS;

	protected final SelectionModule<SelectionSingleAdapter> SELECTOR = new SelectionModule<SelectionSingleAdapter>();
	{
		// Set up single selection mode.
		SELECTOR.setMode(SelectionModule.MODE_SINGLE);
	}

	public SelectionSingleAdapter(Context context) {
		super(context);
		MODELS = new ArrayList<String>(Arrays.asList(context.getResources().getStringArray(R.array.Data_Models)));
		// Adding module, attaches this adapter to it and also saving/restoring state can be handled
		// when invoking onSaveInstanceState() onRestoreInstanceState() on this adapter form its context.
		addModule(SELECTOR, 0);
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
			// Safely (reverse order) remove all items at selected positions.
			MODELS.remove(pos);
		}
		// Clear selection.
		clearSelectedItems();
	}

	@Override
	public int getCount() {
		return MODELS.size();
	}

	@Override
	public Object getItem(int position) {
		return (position < MODELS.size()) ? MODELS.get(position) : "";
	}

	@Override
	public View onCreateItemView(int position, LayoutInflater inflater, ViewGroup root) {
		return inflate(R.layout.listitem_simple);
	}

	@Override
	public void onBindItemView(int position, Object viewHolder) {
		final ViewHolder holder = (ViewHolder) viewHolder;
		holder.setText((String) getItem(position));
		holder.setSelected(SELECTOR.isSelected(position));
	}

	@Override
	public Object onCreateItemViewHolder(int position, View itemView) {
		return new ViewHolder(itemView);
	}

	/**
	 * Item view holder.
	 */
	private class ViewHolder {

		/**
		 * Sate text view which can handle custom selection state.
		 */
		StateTextView mTextView;

		ViewHolder(View itemView) {
			mTextView = (StateTextView) itemView;
			/**
			 * This is very important. Without this false flag the selection will not be working.
			 */
			mTextView.setHandleDefaultStates(false);
		}

		/**
		 * Sets text of this item's holder.
		 *
		 * @param text
		 */
		void setText(String text) {
			mTextView.setText(text);
		}

		/**
		 * Changes selection state of this item's holder to the given one.
		 *
		 * @param selected
		 */
		void setSelected(boolean selected) {
			mTextView.setSelectionState(selected);
		}
	}
}
