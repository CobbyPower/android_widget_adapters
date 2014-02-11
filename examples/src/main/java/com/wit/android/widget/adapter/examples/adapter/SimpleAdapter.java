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
import android.widget.TextView;

import com.wit.android.widget.adapter.BaseAdapter;
import com.wit.android.widget.adapter.examples.R;

/**
 * <p>
 * Description.
 * </p>
 *
 * @author Martin Albedinsky
 */
public class SimpleAdapter extends BaseAdapter {

	/**
	 * Log TAG.
	 */
	private static final String TAG = SimpleAdapter.class.getSimpleName();

	final String[] MODELS;

	public SimpleAdapter(Context context) {
		super(context);
		MODELS = context.getResources().getStringArray(R.array.Data_Models);
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
	public View onCreateView(int position, LayoutInflater inflater, ViewGroup root) {
		return inflate(R.layout.listitem_simple);
	}

	@Override
	public void onBindView(int position, Object viewHolder) {
		((ViewHolder) viewHolder).setText((String) getItem(position));
	}

	@Override
	public Object onCreateViewHolder(int position, View itemView) {
		return new ViewHolder(itemView);
	}

	private class ViewHolder {

		TextView mTextView;

		ViewHolder(View itemView) {
			mTextView = (TextView) itemView;
		}

		void setText(CharSequence text) {
			mTextView.setText(text);
		}
	}
}
