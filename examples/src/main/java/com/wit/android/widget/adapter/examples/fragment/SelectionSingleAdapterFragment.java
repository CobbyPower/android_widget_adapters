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
package com.wit.android.widget.adapter.examples.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.wit.android.examples.app.fragment.ExListFragment;
import com.wit.android.widget.adapter.examples.adapter.SelectionSingleAdapter;

/**
 * <p>
 * Description.
 * </p>
 *
 * @author Martin Albedinsky
 */
public class SelectionSingleAdapterFragment extends ExListFragment<SelectionSingleAdapter> {

	/**
	 * Log TAG.
	 */
	private static final String TAG = SelectionSingleAdapterFragment.class.getSimpleName();

	public static SelectionSingleAdapterFragment newInstance() {
		return new SelectionSingleAdapterFragment();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		setAdapter(new SelectionSingleAdapter(getActivity()));
	}

	@Override
	protected void onListItemClick(ListView listView, View itemView, int position, long id) {
		getAdapter().toggleItemSelectedState(position);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getAdapter().dispatchSaveInstanceState(outState);
	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);
		getAdapter().dispatchRestoreInstanceState(savedInstanceState);
	}
}
