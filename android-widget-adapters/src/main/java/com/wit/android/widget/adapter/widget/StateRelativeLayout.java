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
package com.wit.android.widget.adapter.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.wit.android.widget.adapter.internal.view.IStateView;
import com.wit.android.widget.adapter.view.StateView;

/**
 * <h4>Class Overview</h4>
 * <p>
 * Updated {@link android.widget.RelativeLayout} to handle custom management of the states in
 * the list view (especially when the ListView or GridView should provide
 * multiple selection mode).
 * </p>
 *
 * @author Martin Albedinsky
 * @see RelativeLayout
 * @see IStateView
 */
public class StateRelativeLayout extends RelativeLayout implements IStateView {

	/**
	 * Constants =============================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = StateRelativeLayout.class.getSimpleName();

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
	 * Visibility changed callback.
	 */
	private StateView.OnStateViewVisibilityListener iVisibilityListener;

	/**
	 * Arrays --------------------------------
	 */

	/**
	 * Booleans ------------------------------
	 */

	/**
	 * Indicates if the view should default handle states.
	 */
	private boolean bHandleDefaultStates = true;

	/**
	 * Constructors ==========================
	 */

	/**
	 * <p>
	 * </p>
	 *
	 * @param context Current context.
	 */
	public StateRelativeLayout(Context context) {
		super(context);
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param context Current context.
	 * @param attrs
	 */
	public StateRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param context  Current context.
	 * @param attrs
	 * @param defStyle
	 */
	public StateRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
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
		if (bHandleDefaultStates) {
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
	public void setHandleDefaultStates(boolean handle) {
		this.bHandleDefaultStates = handle;
	}

	/**
	 *
	 */
	@Override
	public boolean handleDefaultStates() {
		return bHandleDefaultStates;
	}

	/**
	 *
	 */
	@Override
	public void setOnVisibilityListener(StateView.OnStateViewVisibilityListener listener) {
		iVisibilityListener = listener;
	}

	/**
	 * Protected -----------------------------
	 */

	/**
	 *
	 */
	@Override
	protected void onVisibilityChanged(View changedView, int visibility) {
		super.onVisibilityChanged(changedView, visibility);

		if (iVisibilityListener != null) {
			// Fire visibility changed callback.
			iVisibilityListener.onVisibilityChanged(changedView, visibility);
		}
	}

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
