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

/**
 * <p>
 * Description.
 * </p>
 *
 * @author Martin Albedinsky
 */
public class HeadersGroupsAdapter extends BaseMultiAdapter<HeadersGroupsAdapter> {

	/**
	 * Log TAG.
	 */
	private static final String TAG = HeadersGroupsAdapter.class.getSimpleName();

	public HeadersGroupsAdapter(Context context) {
		super(context);
	}

	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public View onCreateItemView(int position, LayoutInflater inflater, ViewGroup root) {
		return null;
	}

	@Override
	public void onBindItemView(int position, Object viewHolder) {

	}

	@Override
	public Object onCreateItemViewHolder(int position, View itemView) {
		return null;
	}
}
