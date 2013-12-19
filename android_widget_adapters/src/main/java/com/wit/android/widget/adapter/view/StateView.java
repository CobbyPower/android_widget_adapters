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

import com.wit.android.widget.adapter.internal.view.IStateView;
import com.wit.android.widget.adapter.widget.StateRelativeLayout;

/**
 * <h4>Class Overview</h4>
 * <p>
 * Updated {@link View} to handle custom management of the states in the adapter
 * view (especially when the ListView or GridView should provide multiple
 * selection mode).
 * </p>
 * <p>
 * See also {@link com.wit.android.widget.adapter.widget.StateLinearLayout} and {@link StateRelativeLayout} which
 * provides this functionality too.
 * </p>
 *
 * @see View
 * @see IStateView
 *
 * @author Martin Albedinsky
 */
public class StateView extends View implements IStateView {

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
	 * Visibility changed callback.
	 */
	private OnStateViewVisibilityListener iVisibilityListener;

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
	 * <br/>
	 * <h5><i></i></h5>
	 * <p>
	 * Constructor #1.
	 * </p>
	 *
	 * @param context The actual application context.
	 */
	public StateView(Context context) {
		super(context);
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param context The actual application context.
	 * @param attrs
	 */
	public StateView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param context  The actual application context.
	 * @param attrs
	 * @param defStyle
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
	 *
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
		if (handleDefaultStates()) {
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
	public void setOnVisibilityListener(OnStateViewVisibilityListener listener) {
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

	/**
	 * <h4>Interface Overview</h4>
	 * <p>
	 * Interface to handle callback when the state view visibility was changed.
	 * </p>
	 *
	 * @author Martin Albedinsky
	 */
	public static interface OnStateViewVisibilityListener {

		/**
		 * Methods ===============================
		 */

		/**
		 * <br/>
		 * <h5><i>public void onVisibilityChanged(View stateView, int
		 * visibility)</i></h5>
		 * <p>
		 * Invoked from the state view when its (see {@link View} .
		 * <code>onVisibilityChanged()</code> description) visibility was
		 * changed.
		 * </p>
		 *
		 * @param stateView  This isn't necessary the <code>IStateView</code> child.
		 * @param visibility Actual visibility of the passed view.
		 */
		public void onVisibilityChanged(View stateView, int visibility);
	}
}
