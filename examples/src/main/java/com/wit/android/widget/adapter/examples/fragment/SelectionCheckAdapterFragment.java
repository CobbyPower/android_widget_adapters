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
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.wit.android.examples.app.fragment.ExListFragment;
import com.wit.android.widget.adapter.examples.R;
import com.wit.android.widget.adapter.examples.adapter.SelectionCheckAdapter;

/**
 * <p>
 * Description.
 * </p>
 *
 * @author Martin Albedinsky
 */
public class SelectionCheckAdapterFragment extends ExListFragment<SelectionCheckAdapter> {

	/**
	 * Log TAG.
	 */
	private static final String TAG = SelectionCheckAdapterFragment.class.getSimpleName();

	private ActionMode mActionMode;

	private String mSelectedItemsFormat;

	private SelectionCheckAdapter mAdapter;

	private TextView mActionModeTextView;

	public static SelectionCheckAdapterFragment newInstance() {
		return new SelectionCheckAdapterFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.mSelectedItemsFormat = getString(R.string.Format_SelectedItems);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		setAdapter(mAdapter = new SelectionCheckAdapter(getActivity()));
	}

	@Override
	protected void onListItemClick(ListView listView, int position, long id) {
		// Update selected items only when in action mode.
		if (mActionMode != null) {
			mAdapter.toggleItemSelectedState(position);
			// Update number of selected items in the contextual action bar.
			this.updateSelectedItemsText(mAdapter.getSelectedItemsCount());
		}
	}

	@Override
	protected boolean onListItemLongClick(ListView listView, int position, long id) {
		if (mActionMode != null) {
			// Already in the action mode.
			return false;
		}

		// Start the action mode.
		this.mActionMode = ((ActionBarActivity) getActivity()).startSupportActionMode(new ActionModeCallback());
		mAdapter.toggleItemSelectedState(position);
		updateSelectedItemsText(mAdapter.getSelectedItemsCount());
		return true;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mAdapter.dispatchSaveInstanceState(outState);
	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);
		getAdapter().dispatchRestoreInstanceState(savedInstanceState);
	}

	private void updateSelectedItemsText(int selectedCount) {
		// Update number of selected items in the contextual action bar.
		mActionModeTextView.setText(String.format(mSelectedItemsFormat, selectedCount));
	}

	/**
	 * Action mode callback handles inflating menu and also handles clicks on the action buttons
	 * in the action mode action bar.
	 */
	private class ActionModeCallback implements ActionMode.Callback {

		@Override
		public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
			actionMode.getMenuInflater().inflate(R.menu.selection_check_adapter, menu);
			actionMode.setCustomView(
					// Set up custom action mode title.
					mActionModeTextView = (TextView) getLayoutInflater(null).inflate(com.wit.android.examples.R.layout.ex_action_mode_title, null)
			);
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
					mAdapter.deleteSelectedItems();
					processed = true;
					break;
				default:
			}
			if (processed) {
				// Hide the contextual action bar.
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
