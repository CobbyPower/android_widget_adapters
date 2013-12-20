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
import android.widget.CheckBox;
import android.widget.TextView;

import com.wit.android.widget.adapter.examples.R;
import com.wit.android.widget.adapter.module.SelectionModule;
import com.wit.android.widget.adapter.widget.StateLinearLayout;

/**
 * <p>
 * Description.
 * </p>
 *
 * @author Martin Albedinsky
 */
public class SelectionMultiAdapter extends SelectionSingleAdapter implements View.OnClickListener {

	/**
	 * Log TAG.
	 */
	private static final String TAG = SelectionMultiAdapter.class.getSimpleName();

	public SelectionMultiAdapter(Context context) {
		super(context);
		// Set up multiple selection mode.
		SELECTOR.setMode(SelectionModule.MODE_MULTIPLE);
	}

	@Override
	public View onCreateItemView(int position, LayoutInflater inflater, ViewGroup root) {
		return inflate(R.layout.listitem_simple_check);
	}

	@Override
	public void onBindItemView(int position, Object viewHolder) {
		final ViewHolder holder = (ViewHolder) viewHolder;
		holder.setText((String) getItem(position));
		// Update selection state whenever the list view is updated.
		holder.setSelected(SELECTOR.isSelected(position));
		// Save current position. See implementation.
		holder.setPosition(position);
	}

	@Override
	public Object onCreateItemViewHolder(int position, View itemView) {
		return new ViewHolder(itemView);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.ListItem_Simple_Check_CheckBox:
				// Clicked check box in the list item.
				// Toggle item selection state.
				toggleItemSelectionState((Integer) v.getTag());
				break;
		}
	}

	/**
	 * Simple view holder for the adapter item view.
	 */
	private class ViewHolder {

		private StateLinearLayout mLayout;
		private TextView mTextView;
		private CheckBox mCheckBox;

		ViewHolder(View itemView) {
			this.mLayout = (StateLinearLayout) itemView;
			/**
			 * This is very important. Without this false flag the selection will not be working.
			 */
			mLayout.setHandleDefaultStates(false);
			this.mTextView = (TextView) mLayout.findViewById(R.id.ListItem_Simple_Check_TextView);
			this.mCheckBox = (CheckBox) mLayout.findViewById(R.id.ListItem_Simple_Check_CheckBox);
			// Handle click on the check box.
			mCheckBox.setOnClickListener(SelectionMultiAdapter.this);
		}

		void setText(String text) {
			mTextView.setText(text);
		}

		void setSelected(boolean selected) {
			// Update selection state of the layout and also update checked state
			// of the check box.
			mLayout.setSelectionState(selected);
			mCheckBox.setChecked(selected);
		}

		void setPosition(int position) {
			/**
			 * We will save current position as check box's tag.
			 * When the check box will be clicked, tag will be
			 * obtained an item to which belongs this check box
			 * will be selected/deselected.
			 */
			mCheckBox.setTag(position);
		}
	}
}
