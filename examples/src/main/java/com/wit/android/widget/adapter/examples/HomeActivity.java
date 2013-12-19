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
package com.wit.android.widget.adapter.examples;

import android.os.Bundle;

import com.wit.android.examples.app.ExHomeActivity;
import com.wit.android.examples.model.navigation.NavigationHeader;
import com.wit.android.examples.model.navigation.NavigationItem;
import com.wit.android.examples.model.navigation.NavigationLabel;
import com.wit.android.widget.adapter.examples.fragment.FragmentsFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Description.
 * </p>
 *
 * @author Martin Albedinsky
 */
public class HomeActivity extends ExHomeActivity {

	/**
	 * Log TAG.
	 */
	private static final String TAG = HomeActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getFragmentController().setFragmentFactory(new FragmentsFactory());
	}

	@Override
	protected List<NavigationItem> onCreateNavigationItems() {
		final List<NavigationItem> items = new ArrayList<NavigationItem>();
		items.add(new NavigationHeader(getString(R.string.Navigation_Header_Examples)));
		items.add(new NavigationLabel(getString(R.string.Navigation_Label_BaseAdapter), FragmentsFactory.FRAGMENT_BASE_ADAPTER));
		items.add(new NavigationLabel(getString(R.string.Navigation_Label_SelectionModule), FragmentsFactory.FRAGMENT_SELECTION_MODULE));
		items.add(new NavigationLabel(getString(R.string.Navigation_Label_HeadersModule), FragmentsFactory.FRAGMENT_HEADERS_MODULE));
		items.add(new NavigationLabel(getString(R.string.Navigation_Label_SelectionAndHeadersModule), FragmentsFactory.FRAGMENT_SELECTION_AND_HEADERS_MODULE));
		return items;
	}

	@Override
	protected boolean onNavigationItemClick(int position, final long id) {
		registerAction(new Runnable() {
			@Override
			public void run() {
				getFragmentController().showFragment((int) id);
			}
		});
		return true;
	}
}
