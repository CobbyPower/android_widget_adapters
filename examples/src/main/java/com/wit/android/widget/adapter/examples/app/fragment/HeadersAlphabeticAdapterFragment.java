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
package com.wit.android.widget.adapter.examples.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wit.android.examples.app.fragment.ExListFragment;
import com.wit.android.widget.adapter.examples.R;
import com.wit.android.widget.adapter.examples.adapter.HeadersAlphabeticAdapter;

/**
 * <p>
 * Description.
 * </p>
 *
 * @author Martin Albedinsky
 */
public class HeadersAlphabeticAdapterFragment extends ExListFragment<HeadersAlphabeticAdapter> {

	/**
	 * Log TAG.
	 */
	private static final String TAG = HeadersAlphabeticAdapterFragment.class.getSimpleName();

	public static HeadersAlphabeticAdapterFragment newInstance() {
		return new HeadersAlphabeticAdapterFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.listview, null);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		setActionBarTitle(R.string.Navigation_Label_HeadersAdapter_Alphabetic);
		// Do not show divider.
		getListView().setDividerHeight(0);
		setAdapter(new HeadersAlphabeticAdapter(getActivity()));
	}
}
