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
import android.view.View;

import com.wit.android.examples.app.fragment.ExListFragment;
import com.wit.android.widget.adapter.examples.R;
import com.wit.android.widget.adapter.examples.adapter.GroupsAdapter;
import com.wit.android.widget.adapter.examples.model.Connection;
import com.wit.android.widget.adapter.examples.model.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Description.
 * </p>
 *
 * @author Martin Albedinsky
 */
public class GroupsAdapterFragment extends ExListFragment<GroupsAdapter> {

	/**
	 * Log TAG.
	 */
	private static final String TAG = GroupsAdapterFragment.class.getSimpleName();

	public static GroupsAdapterFragment newInstance() {
		return new GroupsAdapterFragment();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		setActionBarTitle(R.string.Navigation_Label_HeadersAdapter_Groups);

		// Set up adapter.
		final GroupsAdapter adapter = new GroupsAdapter(getActivity());
		adapter.loadConnections(getConnections());
		setAdapter(adapter);
	}

	private List<Connection> getConnections() {
		final List<Connection> connections = new ArrayList<Connection>();

		// Process array from resources.
		final String[] models = getResources().getStringArray(R.array.Data_Models);
		for (String name : models) {
			connections.add(new Connection(name, Group.random()));
		}
		return connections;
	}
}
