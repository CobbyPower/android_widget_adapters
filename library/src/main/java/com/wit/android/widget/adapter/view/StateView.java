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
package com.wit.android.widget.adapter.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.wit.android.widget.adapter.internal.view.ISelectableView;

/**
 * <h4>Class Overview</h4>
 * <p>
 * Updated {@link android.view.View} to allow custom management of the
 * selection state  of this view when is used in the item view for some
 * of the {@link android.widget.AdapterView} implementations.
 * </p>
 *
 * @author Martin Albedinsky
 * @see com.wit.android.widget.adapter.widget.StateLinearLayout
 * @see com.wit.android.widget.adapter.widget.StateRelativeLayout
 * @see com.wit.android.widget.adapter.widget.StateFrameLayout
 */
public class StateView extends View implements ISelectableView {

	/**
	 * Constants =============================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = StateView.class.getSimpleName();

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
	private boolean bAllowDefaultSelection = true;

	/**
	 * Constructors ==========================
	 */

	/**
	 * <p>
	 * Same as {@link android.view.View#View(android.content.Context)}.
	 * </p>
	 */
	public StateView(Context context) {
		super(context);
	}

	/**
	 * <p>
	 * Same as {@link android.view.View#View(android.content.Context, android.util.AttributeSet)}.
	 * </p>
	 */
	public StateView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * <p>
	 * Same as {@link android.view.View#View(android.content.Context, android.util.AttributeSet, int)}.
	 * </p>
	 */
	public StateView(Context context, AttributeSet attrs, int defStyle) {
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
	 */
	@Override
	public void setSelected(boolean selected) {
		/**
		 * We don't want to support this operation. The main reason is that when
		 * the user select some item in the ListView this ListView re-selects
		 * each item which is in the current time selected using this method. So
		 * method implemented below is override for this to handle custom
		 * selecting of the view.
		 */
		if (allowsDefaultSelection()) {
			super.setSelected(selected);
		}
	}

	/**
	 */
	@Override
	public void setSelectionState(boolean selected) {
		super.setSelected(selected);
	}

	/**
	 */
	@Override
	public void setAllowDefaultSelection(boolean allow) {
		this.bAllowDefaultSelection = allow;
	}

	/**
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
