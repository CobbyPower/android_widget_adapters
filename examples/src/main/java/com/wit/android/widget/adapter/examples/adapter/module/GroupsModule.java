/*
 * =================================================================================
 * Copyright (C) 2014 Martin Albedinsky [Wolf-ITechnologies]
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
package com.wit.android.widget.adapter.examples.adapter.module;

import com.wit.android.widget.adapter.examples.adapter.GroupsAdapter;
import com.wit.android.widget.adapter.examples.model.Connection;
import com.wit.android.widget.adapter.examples.model.Group;
import com.wit.android.widget.adapter.module.BaseHeadersModule;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * Description.
 * </p>
 *
 * @author Martin Albedinsky
 */
public class GroupsModule extends BaseHeadersModule<Group, GroupsAdapter> {

	/**
	 * Log TAG.
	 */
	// private static final String TAG = GroupsModule.class.getSimpleName();

	private Group mLastGroup;

	/**
	 */
	@Override
	public void clearHeaders() {
		super.clearHeaders();
		this.mLastGroup = null;
	}

	/**
	 *
	 * @param connections
	 */
	public void processConnectionGroups(List<Connection> connections) {
		clearHeaders();

		// Sort connections by groups.
		Collections.sort(connections, new Comparator<Connection>() {
			@Override
			public int compare(Connection first, Connection second) {
				final Group firstGroup = first.getGroup();
				final Group secondGroup = second.getGroup();

				if (firstGroup.ordinal() > secondGroup.ordinal()) {
					return 1;
				} else if (firstGroup.ordinal() < secondGroup.ordinal()) {
					return -1;
				}
				return 0;
			}
		});

		// Process list of connections to create groups data set.
		for (int i = 0; i < connections.size(); i++) {
			this.processConnectionItem(connections.get(i), i);
		}

		// Notify adapter to reload data set views.
		notifyAdapter();
	}

	/**
	 *
	 * @param connection
	 * @param position
	 */
	private void processConnectionItem(Connection connection, int position) {
		final Group group = connection.getGroup();
		if (mLastGroup == null || !mLastGroup.equals(group)) {
			// Insert group header into the current headers data set.
			addHeader(group, getHeadersCount() + position);
			// Save last checked group.
			this.mLastGroup = group;
		}
	}
}
