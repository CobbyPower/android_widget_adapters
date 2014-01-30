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
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.wit.android.examples.app.fragment.ExListFragment;
import com.wit.android.widget.adapter.examples.R;
import com.wit.android.widget.adapter.examples.adapter.SelectionAndHeadersAdapter;

/**
 * <p>
 * Description.
 * </p>
 *
 * @author Martin Albedinsky
 */
public class SelectionAndHeadersAdapterFragment extends ExListFragment<SelectionAndHeadersAdapter> {

	/**
	 * Log TAG.
	 */
	// private static final String TAG = SelectionAndHeadersAdapterFragment.class.getSimpleName();

	private SelectionAndHeadersAdapter mAdapter;

	private ActionMode mActionMode;

	private String mSelectedItemsFormat;

	public static SelectionAndHeadersAdapterFragment newInstance() {
		return new SelectionAndHeadersAdapterFragment();
	}

	/**
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.listview, null);
	}

	/**
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		setActionBarTitle(R.string.Navigation_Label_SelectionAndHeadersAdapter);

		this.mSelectedItemsFormat = getString(R.string.Format_SelectedItems);
		// Do not show divider.
		getListView().setDividerHeight(0);
		setAdapter(mAdapter = new SelectionAndHeadersAdapter(getActivity()));
	}

	/**
	 */
	@Override
	protected void onListItemClick(ListView listView, View itemView, int position, long id) {
		if (!mAdapter.isItemAt(position)) {
			return;
		}

		// Update selected items only when in action mode.
		if (mActionMode != null) {
			mAdapter.toggleItemSelectionState(position);
			// Update number of selected items in the contextual action bar.
			this.updateSelectedItemsText(mAdapter.getSelectedItemsCount());
		} else {
			Toast.makeText(getActivity(), R.string.Toast_LongPressToEnableActionMode, Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 */
	@Override
	protected boolean onListItemLongClick(ListView listView, View itemView, int position, long id) {
		if (!mAdapter.isItemAt(position) || mActionMode != null) {
			// Already in the action mode or no item long pressed.
			return false;
		}

		// Start the action mode.
		this.mActionMode = ((ActionBarActivity) getActivity()).startSupportActionMode(new ActionModeCallback());
		mAdapter.toggleItemSelectionState(position);
		updateSelectedItemsText(mAdapter.getSelectedItemsCount());
		return true;
	}

	/**
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (hasAdapter()) {
			getAdapter().dispatchSaveInstanceState(outState);
		}
	}

	/**
	 */
	@Override
	public void onViewStateRestored(Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);
		if (hasAdapter()) {
			getAdapter().dispatchRestoreInstanceState(savedInstanceState);
		}
	}

	private void updateSelectedItemsText(int selectedCount) {
		// Update number of selected items in the contextual action bar.
		mActionMode.setTitle(String.format(mSelectedItemsFormat, selectedCount));
	}

	/**
	 * Action mode callback handles inflating menu and also handles clicks on the action buttons
	 * in the action mode action bar.
	 */
	private class ActionModeCallback implements ActionMode.Callback {

		@Override
		public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
			actionMode.getMenuInflater().inflate(R.menu.selection_adapter, menu);
			return true;
		}

		@Override
		public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
			return false;
		}

		@Override
		public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
			boolean processed = false;
			switch (menuItem.getItemId()) {
				case com.wit.android.examples.R.id.Ex_App_Menu_Discard:
					// Dispatch to adapter to delete all selected items.
					mAdapter.deleteSelectedItems();
					processed = true;
					break;
				default:
			}
			if (processed) {
				// Hide contextual action bar.
				actionMode.finish();
			}
			return processed;
		}

		@Override
		public void onDestroyActionMode(ActionMode actionMode) {
			mAdapter.clearSelectedItems();
			mActionMode = null;
		}
	}
}