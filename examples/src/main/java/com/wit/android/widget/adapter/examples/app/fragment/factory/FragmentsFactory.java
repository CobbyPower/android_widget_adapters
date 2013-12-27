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
package com.wit.android.widget.adapter.examples.app.fragment.factory;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.wit.android.examples.internal.app.fragment.ExFragmentController;
import com.wit.android.examples.internal.app.fragment.ExFragmentFactory;
import com.wit.android.widget.adapter.examples.app.fragment.HeadersAlphabeticAdapterFragment;
import com.wit.android.widget.adapter.examples.app.fragment.HeadersGroupsAdapterFragment;
import com.wit.android.widget.adapter.examples.app.fragment.SelectionAndHeadersAdapterFragment;
import com.wit.android.widget.adapter.examples.app.fragment.SelectionMultiAdapterFragment;
import com.wit.android.widget.adapter.examples.app.fragment.SelectionSingleAdapterFragment;
import com.wit.android.widget.adapter.examples.app.fragment.SimpleAdapterFragment;

/**
 * <p>
 * Description.
 * </p>
 *
 * @author Martin Albedinsky
 */
public class FragmentsFactory extends ExFragmentFactory {

	public static final int FRAGMENT_SIMPLE_ADAPTER = 0x01;
	public static final int FRAGMENT_SELECTION_SIMPLE_ADAPTER = 0x02;
	public static final int FRAGMENT_SELECTION_CHECK_ADAPTER = 0x03;
	public static final int FRAGMENT_HEADERS_ALPHABETIC_ADAPTER = 0x04;
	public static final int FRAGMENT_HEADERS_GROUPS_ADAPTER = 0x05;
	public static final int FRAGMENT_SELECTION_AND_HEADERS_ADAPTER = 0x06;

	/**
	 * Log TAG.
	 */
	private static final String TAG = FragmentsFactory.class.getSimpleName();

	@Override
	public Fragment createFragmentInstance(int fragmentID, Bundle bundle) {
		Fragment fragment = null;
		switch (fragmentID) {
			case FRAGMENT_SIMPLE_ADAPTER:
				fragment = SimpleAdapterFragment.newInstance();
				break;
			case FRAGMENT_SELECTION_SIMPLE_ADAPTER:
				fragment = SelectionSingleAdapterFragment.newInstance();
				break;
			case FRAGMENT_SELECTION_CHECK_ADAPTER:
				fragment = SelectionMultiAdapterFragment.newInstance();
				break;
			case FRAGMENT_HEADERS_ALPHABETIC_ADAPTER:
				fragment = HeadersAlphabeticAdapterFragment.newInstance();
				break;
			case FRAGMENT_HEADERS_GROUPS_ADAPTER:
				fragment = HeadersGroupsAdapterFragment.newInstance();
				break;
			case FRAGMENT_SELECTION_AND_HEADERS_ADAPTER:
				fragment = SelectionAndHeadersAdapterFragment.newInstance();
				break;
		}
		return fragment;
	}

	@Override
	public ExFragmentController.ShowOptions getFragmentShowOptions(int fragmentID) {
		return new ExFragmentController.ShowOptions()
				.showDirection(ExFragmentController.ShowDirection.FROM_RIGHT)
				.addToBackStack(false);
	}

	@Override
	public String getFragmentTag(int fragmentID) {
		return createFragmentTag(FragmentsFactory.class, Integer.toString(fragmentID));
	}
}
