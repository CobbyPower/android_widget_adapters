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
import com.wit.android.widget.adapter.module.AlphabeticHeaders;
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
public class HeadersAlphabeticAdapter extends BaseMultiAdapter<HeadersAlphabeticAdapter> {

	/**
	 * Log TAG.
	 */
	private static final String TAG = HeadersAlphabeticAdapter.class.getSimpleName();

	/**
	 * Two types of views (item, header).
	 */
	protected static final int VIEW_TYPE_COUNT = 2;
	protected static final int VIEW_TYPE_HEADER = 0x00;
	protected static final int VIEW_TYPE_ITEM = 0x01;

	protected final List<AlphabeticHeaders.AlphabeticItem> MODELS = new ArrayList<AlphabeticHeaders.AlphabeticItem>();

	protected final AlphabeticHeaders<HeadersAlphabeticAdapter> HEADERS = new AlphabeticHeaders<HeadersAlphabeticAdapter>();

	public HeadersAlphabeticAdapter(Context context) {
		super(context);
		// Process array from resources to required data set of alphabetic items.
		final String[] models = context.getResources().getStringArray(R.array.Data_Models);
		// Sort array first.
		Arrays.sort(models);
		for (String model : models) {
			MODELS.add(new ModelItem(model));
		}

		// Adding module, attaches this adapter to it and also saving/restoring state can be handled
		// when invoking onSaveInstanceState() onRestoreInstanceState() on this adapter form its context.
		assignModule(HEADERS, 0);
		// Set attribute which holds the custom style for the header view.
		// Note, that this attribute must be placed in the main application theme.
		HEADERS.setHeaderStyleAttr(R.attr.adapterViewHeaderStyle);

		// Generate headers.
		this.updateHeaders();
	}

	/**
	 *
	 * @param position
	 * @return
	 */
	public boolean isItemAt(int position) {
		return !HEADERS.isHeaderAt(position);
	}

	/**
	 */
	@Override
	public int getCount() {
		// Total count of the adapter is count of its data set + headers generated by module.
		return MODELS.size() + HEADERS.getHeadersCount();
	}

	/**
	 */
	@Override
	public Object getItem(int position) {
		// We will handle only obtaining of the items from data set.
		// If you would like handle also obtaining the header item
		// you can do this like so: HEADERS.getHeader(position)
		/**
		 * It is very important to ask headers module to correct
		 * current position. The correction is done way, that position will
		 * be decreased by count of the headers before that position.
		 */
		final int corrPos = HEADERS.correctPosition(position);
		return (corrPos < MODELS.size()) ? MODELS.get(corrPos) : new ModelItem("");
	}

	@Override
	public int getItemViewType(int position) {
		// Headers module knows the positions of generated headers so we can resolve
		// header vs. item view type.
		return HEADERS.isHeaderAt(position) ? VIEW_TYPE_HEADER : VIEW_TYPE_ITEM;
	}

	@Override
	public int getViewTypeCount() {
		return VIEW_TYPE_COUNT;
	}

	@Override
	public View onCreateItemView(int position, LayoutInflater inflater, ViewGroup root) {
		View view = null;
		switch (currentItemViewType()) {
			case VIEW_TYPE_ITEM:
				view = inflate(R.layout.listitem_simple);
				break;
			case VIEW_TYPE_HEADER:
				view = HEADERS.createHeaderView(position, inflater, root);
				break;
		}
		return view;
	}

	@Override
	public void onBindItemView(int position, Object viewHolder) {
		switch (currentItemViewType()) {
			case VIEW_TYPE_ITEM:
				((ViewHolder) viewHolder).setText(
						((AlphabeticHeaders.AlphabeticItem) getItem(position)).getName()
				);
				break;
			case VIEW_TYPE_HEADER:
				HEADERS.bindHeaderView(position, viewHolder);
				break;
		}
	}

	@Override
	public Object onCreateItemViewHolder(int position, View itemView) {
		switch (currentItemViewType()) {
			case VIEW_TYPE_ITEM:
				return new ViewHolder(itemView);
			case VIEW_TYPE_HEADER:
				return HEADERS.createHeaderViewHolder(position, itemView);
		}
		return null;
	}

	/**
	 * Generates all headers for the current data set.
	 * This should be called whenever data set was changed.
	 */
	protected void updateHeaders() {
		// Note, that at this place we assume that data set is already
		// sorted alphabetically.
		// Module also fires data set changed on the attached(this) adapter.
		HEADERS.processAlphabeticList(MODELS);
	}

	protected class ViewHolder {

		StateTextView mTextView;

		ViewHolder(View itemView) {
			this.mTextView = (StateTextView) itemView;
			mTextView.setAllowDefaultSelection(false);
		}

		protected void setText(String text) {
			mTextView.setText(text);
		}

		protected void setSelected(boolean selected) {
			mTextView.setSelectionState(selected);
		}
	}

	public static class ModelItem implements AlphabeticHeaders.AlphabeticItem {

		String mName;

		ModelItem(String name) {
			this.mName = name;
		}

		@Override
		public String getName() {
			return mName;
		}
	}
}
