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
package com.wit.and.internal.view;

import com.wit.and.view.StateView;

/**
 * <p>
 * public interface
 * </p>
 * <h5>IStateView</h5>
 * 
 * <h4>Interface Overview</h4>
 * <p>
 * Simple interface for state views. This is used for the state views which
 * should handle the custom handling of its states.
 * </p>
 * 
 * @author Martin Albedinsky
 */
public interface IStateView {

	/**
	 * Methods ===============================
	 */

	/**
	 * <br/>
	 * <h5><i>public void setHandleDefaultStates(boolean handle)</i></h5>
	 * <p>
	 * Sets the boolean flag to indicate if the view should handle default
	 * states.
	 * </p>
	 * 
	 * @param handle
	 *            If set to true the view will handle default setting of the
	 *            states otherwise states of this view can be handled only using
	 *            by overriding methods.
	 */
	public void setHandleDefaultStates(boolean handle);

	/**
	 * <br/>
	 * <h5><i>public boolean handleDefaultStates()</i></h5>
	 * <p>
	 * Checks if the view handle default states.
	 * </p>
	 * 
	 * @return True if the view handle default states otherwise false.
	 */
	public boolean handleDefaultStates();

	/**
	 * <br/>
	 * <h5><i>public void setSelected(boolean selected, boolean override)</i></h5>
	 * <p>
	 * Overrides handling of the selection mode of the state view.
	 * </p>
	 * 
	 * @param selected
	 *            True if the view must be selected otherwise false.
	 * @param override
	 */
	public void setSelected(boolean selected, boolean override);

	/**
	 * <br/>
	 * <h5><i>public void setOnVisibilityListener(OnStateViewVisibilityListener
	 * listener)</i></h5>
	 * <p>
	 * Registers the callback to be invoked when the state view visibility was
	 * changed.
	 * </p>
	 * 
	 * @param listener
	 */
	public void setOnVisibilityListener(StateView.OnStateViewVisibilityListener listener);
}
