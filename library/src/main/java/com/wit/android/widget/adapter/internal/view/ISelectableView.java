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
package com.wit.android.widget.adapter.internal.view;

/**
 * <h4>Interface Overview</h4>
 * <p>
 * Base interface for selectable views. Allows to enable/disable default
 * management of the selection state of a selectable view implementation
 * and provides custom method ({@link #setSelectionState(boolean)})
 * for selection state management.
 * </p>
 *
 * @author Martin Albedinsky
 */
public interface ISelectableView {

	/**
	 * Methods ===============================
	 */

	/**
	 * <p>
	 * Sets flag indicating whether this view should allow default selection state
	 * management or not.
	 * </p>
	 *
	 * @param allow <code>True</code> to allow default selection in this view,
	 *              <code>false</code> otherwise.
	 * @see #allowsDefaultSelection()
	 * @see #setSelectionState(boolean)
	 */
	public void setAllowDefaultSelection(boolean allow);

	/**
	 * <p>
	 * Returns flag indicating whether this view allows default selection or not.
	 * </p>
	 *
	 * @return <code>True</code> if default selection is allowed,
	 * <code>false</code> otherwise.
	 * @see #setAllowDefaultSelection(boolean)
	 * @see #setSelectionState(boolean)
	 */
	public boolean allowsDefaultSelection();

	/**
	 * <p>
	 * Sets the selection state of this selectable view.
	 * </p>
	 *
	 * @param selected <code>True</code> for selected, <code>false</code> for unselected state.
	 */
	public void setSelectionState(boolean selected);
}