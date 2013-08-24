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
package com.wit.and.widget.adapter.module;

import android.view.View;

import com.wit.and.widget.adapter.internal.module.ISelectable;
import com.wit.and.widget.adapter.internal.view.IStateView;
import com.wit.and.widget.adapter.view.StateView;
import com.wit.and.widget.adapter.widget.StateLinearLayout;
import com.wit.and.widget.adapter.widget.StateRelativeLayout;

/**
 * <p>
 * public class
 * </p>
 * <h5>SelectableItem</h5>
 * <p>
 * implements {@link com.wit.and.widget.adapter.internal.module.ISelectable}
 * </p>
 * <h4>Class Overview</h4>
 * <p>
 * 
 * </p>
 * 
 * @author Martin Albedinsky
 * @see ISelectable
 */
public class SelectableItem implements ISelectable, StateView.OnStateViewVisibilityListener {
	/**
	 * Constants =============================
	 */

    /**
     * Log TAG.
     */
    // private static final String TAG = SelectableItem.class.getSimpleName();

    /**
     * Indicates if debug private output trough log-cat is enabled.
     */
    // private static final boolean DEBUG = true;

    /**
     * Indicates if logging for user output trough log-cat is enabled.
     */
    // private static final boolean USER_LOG = true;

	/**
	 * Enums =================================
	 */

    /**
     * Static members ========================
     */

	/**
	 * Members ===============================
	 */

	/**
	 * The view by which is this item presented in the ListView or GridView or
	 * only included in some layout. When the item is selected this view will be
	 * invalidated to handle changes in the background drawable.
	 */
	private View mItemView;

	/**
	 * Listeners -----------------------------
	 */

	/**
	 * Arrays --------------------------------
	 */

	/**
	 * Booleans ------------------------------
	 */

	/**
	 * Selected state for this selectable item.
	 */
	private boolean mSelected = false;

	/**
	 * Constructors ==========================
	 */

	/**
	 * <br/>
	 * <h5><i>public SelectableItem()</i></h5>
	 * <p>
	 * Constructor #1.
	 * </p>
	 */
	public SelectableItem() {
		// Empty.
	}

	/**
	 * Methods ===============================
	 */

	/**
	 * Public --------------------------------
	 */

	/**
	 * 
	 */
	@Override
	public void onVisibilityChanged(View stateView, int visibility) {
		if (stateView.equals(mItemView) && visibility != View.VISIBLE) {
			// We need to handle this when the ListView or GridView is scrolled
			// the item view will be hidden for a while before it will be used
			// again (as convert view).
			// This ensures that only for visible items will be selection
			// handled.
			mItemView = null;
		}
	}

	/**
	 * Getters + Setters ---------------------
	 */

	/**
	 * 
	 */
	@Override
	public void setSelected(boolean selected) {
		this.mSelected = selected;
	}

	/**
	 * 
	 */
	@Override
	public boolean isSelected() {
		return mSelected;
	}

	/**
	 * <br/>
	 * <h5><i>public void invalidateState()</i></h5>
	 * <p>
	 * Invalidates the states of this object to actual one. Invoke this whenever
	 * the states of this object were changed.
	 * </p>
	 */
	public void invalidateState() {
		// Check for list item layout.
		// This object is implemented to handle items in the list view so it
		// should have the layout which can be selected.
		if (this.mItemView != null) {
			// Get actual state.
			final boolean selectedSate = this.isSelected();

			if (mItemView instanceof StateLinearLayout) {
				((StateLinearLayout) mItemView).setSelected(selectedSate, true);
			} else if (mItemView instanceof StateRelativeLayout) {
				((StateRelativeLayout) mItemView).setSelected(selectedSate, true);
			} else {
				this.mItemView.setSelected(selectedSate);
			}
		}
	}

	/**
	 * <br/>
	 * <h5><i>public void reloadView(View view)</i></h5>
	 * <p>
	 * Reloads the view of this item. This should be {@link IStateView}
	 * otherwise the selection mode will be not handled.
	 * </p>
	 * <p>
	 * Also invalidates the given view to handle current state of the background
	 * selector.
	 * </p>
	 * 
	 * @param view
	 */
	public void reloadView(View view) {
		this.mItemView = view;

		// This is the top parent view in the list view item. We don't want to
		// default handle its states only by override methods.
		if (view instanceof IStateView) {
			((IStateView) view).setHandleDefaultStates(false);
			((IStateView) view).setOnVisibilityListener(this);
		}

		// Invalidate state.
		invalidateState();
	}

	/**
	 * <br/>
	 * <h5><i>public View getListItemView()</i></h5>
	 * <p>
	 * Returns this item's view.
	 * </p>
	 * 
	 * @return
	 */
	public View getItemView() {
		return mItemView;
	}

	/**
	 * Protected -----------------------------
	 */

	/**
	 * Private -------------------------------
	 */

	/**
	 * Abstract methods ----------------------
	 */

	/**
	 * Inner classes =========================
	 */

	/**
	 * Interface =============================
	 */
}
