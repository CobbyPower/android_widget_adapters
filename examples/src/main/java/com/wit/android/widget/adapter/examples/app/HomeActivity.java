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
package com.wit.android.widget.adapter.examples.app;

import android.content.res.Resources;
import android.os.Bundle;

import com.wit.android.examples.app.ExHomeActivity;
import com.wit.android.examples.model.navigation.INavigationItem;
import com.wit.android.examples.model.navigation.NavigationHeader;
import com.wit.android.examples.model.navigation.NavigationItem;
import com.wit.android.widget.adapter.examples.R;
import com.wit.android.widget.adapter.examples.app.fragment.SimpleAdapterFragment;
import com.wit.android.widget.adapter.examples.app.fragment.FragmentsFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Examples Home activity with the navigation and content container for fragments.
 * </p>
 *
 * @author Martin Albedinsky
 */
public class HomeActivity extends ExHomeActivity {

	/**
	 * Log TAG.
	 */
	// private static final String TAG = HomeActivity.class.getSimpleName();

	/**
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getFragmentController().setFragmentFactory(new FragmentsFactory());
	}

	/**
	 */
	@Override
	protected List<INavigationItem> onCreateNavigationItems(Resources res) {
		final List<INavigationItem> items = new ArrayList<INavigationItem>();
		items.add(new NavigationHeader(getString(R.string.Navigation_Header_Examples)));
		items.add(NavigationItem.create(
				FragmentsFactory.FRAGMENT_SIMPLE_ADAPTER,
				R.string.Navigation_Label_SimpleAdapter,
				res
		));
		items.add(NavigationItem.create(
				FragmentsFactory.FRAGMENT_SELECTION_SIMPLE_ADAPTER,
				R.string.Navigation_Label_SelectionAdapter_Single,
				res
		));
		items.add(NavigationItem.create(
				FragmentsFactory.FRAGMENT_SELECTION_MULTI_ADAPTER,
				R.string.Navigation_Label_SelectionAdapter_Multi,
				res
		));
		items.add(NavigationItem.create(
				FragmentsFactory.FRAGMENT_HEADERS_ALPHABETIC_ADAPTER,
				R.string.Navigation_Label_HeadersAdapter_Alphabetic,
				res
		));
		items.add(NavigationItem.create(
				FragmentsFactory.FRAGMENT_HEADERS_GROUPS_ADAPTER,
				R.string.Navigation_Label_HeadersAdapter_Groups,
				res
		));
		items.add(NavigationItem.create(
				FragmentsFactory.FRAGMENT_SELECTION_AND_HEADERS_ADAPTER,
				R.string.Navigation_Label_SelectionAndHeadersAdapter,
				res
		));
		return items;
	}

	/**
	 */
	@Override
	protected int onShowInitialFragment() {
		getFragmentController().showFragment(SimpleAdapterFragment.newInstance());
		return 1;
	}
}
