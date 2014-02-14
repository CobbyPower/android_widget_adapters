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
package com.wit.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.wit.android.widget.adapter.view.ISelectableView;

/**
 * <h4>Class Overview</h4>
 * <p>
 * Updated {@link android.widget.RelativeLayout} to allow custom management of
 * the selection state of this layout when used in the item view for some
 * of the {@link android.widget.AdapterView} implementations.
 * </p>
 *
 * @author Martin Albedinsky
 * @see com.wit.android.widget.adapter.view.StateView
 * @see SelectableLinearLayout
 * @see SelectableFrameLayout
 */
public class SelectableRelativeLayout extends RelativeLayout implements ISelectableView {

	/**
	 * Constants =============================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = SelectableRelativeLayout.class.getSimpleName();

	/**
	 * Flag indicating whether the debug output trough log-cat is enabled or not.
	 */
	// private static final boolean DEBUG = true;

	/**
	 * Flag indicating whether the output for user trough log-cat is enabled or not.
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
	 * Listeners -----------------------------
	 */

	/**
	 * Arrays --------------------------------
	 */

	/**
	 * Booleans ------------------------------
	 */

	/**
	 * Indicates if the view should default handle states.
	 */
	private boolean bAllowDefaultSelection = false;

	/**
	 * Constructors ==========================
	 */

	/**
	 * <p>
	 * Same as {@link android.widget.RelativeLayout#RelativeLayout(android.content.Context)}.
	 * </p>
	 */
	public SelectableRelativeLayout(Context context) {
		super(context);
	}

	/**
	 * <p>
	 * Same as {@link android.widget.RelativeLayout#RelativeLayout(android.content.Context, android.util.AttributeSet)}.
	 * </p>
	 */
	public SelectableRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * <p>
	 * Same as {@link android.widget.RelativeLayout#RelativeLayout(android.content.Context, android.util.AttributeSet, int)}.
	 * </p>
	 */
	public SelectableRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * Methods ===============================
	 */

	/**
	 * Public --------------------------------
	 */

	/**
	 * Getters + Setters ---------------------
	 */

	/**
	 *
	 */
	@Override
	public void setSelected(boolean selected) {
		/**
		 * We don't want to support this operation. The main reason is that when
		 * the user selects some item in the AdapterView and that AdapterView re-selects
		 * each item which is in the current time selected using this method. So
		 * method implemented below allows handle selection of this view from
		 * adapter which doesn't touch other items in the AdapterView
		 */
		if (bAllowDefaultSelection) {
			super.setSelected(selected);
		}
	}

	/**
	 *
	 */
	@Override
	public void setSelectionState(boolean selected) {
		super.setSelected(selected);
	}

	/**
	 *
	 */
	@Override
	public void setAllowDefaultSelection(boolean allow) {
		this.bAllowDefaultSelection = allow;
	}

	/**
	 *
	 */
	@Override
	public boolean allowsDefaultSelection() {
		return bAllowDefaultSelection;
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
