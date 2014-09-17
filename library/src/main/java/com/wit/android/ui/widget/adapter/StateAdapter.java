/*
 * =================================================================================================
 *                    Copyright (C) 2014 Martin Albedinsky [Wolf-ITechnologies]
 * =================================================================================================
 *         Licensed under the Apache License, Version 2.0 or later (further "License" only).
 * -------------------------------------------------------------------------------------------------
 * You may use this file only in compliance with the License. More details and copy of this License 
 * you may obtain at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * You can redistribute, modify or publish any part of the code written within this file but as it 
 * is described in the License, the software distributed under the License is distributed on an 
 * "AS IS" BASIS, WITHOUT WARRANTIES or CONDITIONS OF ANY KIND.
 * 
 * See the License for the specific language governing permissions and limitations under the License.
 * =================================================================================================
 */
package com.wit.android.ui.widget.adapter;

import android.os.Parcelable;

/**
 * <h4>Interface Overview</h4>
 * todo: description
 *
 * @author Martin Albedinsky
 */
public interface StateAdapter {

	/**
	 * Called to save the current state of this adapter.
	 *
	 * @return Saved state of this adapter or an <b>empty</b> state if this adapter does not need to
	 * save its state.
	 */
	public Parcelable dispatchSaveInstanceState();

	/**
	 * Called to restore a previous state, saved by {@link #dispatchSaveInstanceState()}, of this
	 * adapter.
	 *
	 * @param savedState Should be the same state as obtained by {@link #dispatchSaveInstanceState()}
	 *                   before.
	 */
	public void dispatchRestoreInstanceState(Parcelable savedState);
}
