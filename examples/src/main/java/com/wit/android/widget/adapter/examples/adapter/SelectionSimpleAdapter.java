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

/**
 * <p>
 * Description.
 * </p>
 *
 * @author Martin Albedinsky
 */
public class SelectionSimpleAdapter extends BaseMultiAdapter<SelectionSimpleAdapter> {

	/**
	 * Log TAG.
	 */
	private static final String TAG = SelectionSimpleAdapter.class.getSimpleName();

	private final SelectionModule<SelectionSimpleAdapter> SELECTOR = new SelectionModule<SelectionSimpleAdapter>();
	{
		// Enable multi-selection mode.
		SELECTOR.setMode(SelectionModule.MODE_MULTIPLE);
	}

	final String[] MODELS;

	public SelectionSimpleAdapter(Context context) {
		super(context);
		MODELS = context.getResources().getStringArray(R.array.Data_Models);
		// Adding module, attaches this adapter to it and also saving/restoring state can be handled
		// when invoking onSaveInstanceState() onRestoreInstanceState() on this adapter form its context.
		addModule(SELECTOR, 0);
	}

	public void toggleItemSelectedState(int position) {
		SELECTOR.toggleItemSelectedState(position);
	}

	@Override
	public int getCount() {
		return MODELS.length;
	}

	@Override
	public Object getItem(int position) {
		return (position < MODELS.length) ? MODELS[position] : "";
	}

	@Override
	public View onCreateItemView(int position, LayoutInflater inflater, ViewGroup root) {
		return inflate(R.layout.listitem_simple_adapter);
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

	private class ViewHolder {

		StateTextView mTextView;

		ViewHolder(View itemView) {
			mTextView = (StateTextView) itemView;
			// This is very important. Without this false flag the selection will not be working.
			mTextView.setHandleDefaultStates(false);
		}

		void setText(CharSequence text) {
			mTextView.setText(text);
		}

		void setSelected(boolean selected) {
			mTextView.setSelectionState(selected);
		}
	}
}
