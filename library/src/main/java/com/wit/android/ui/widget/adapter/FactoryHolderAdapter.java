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

import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;

/**
 * <h3>Interface Overview</h3>
 * Required interface for adapter which uses {@link com.wit.android.ui.widget.adapter.ViewHolderFactory}
 * to instantiate holder for its views.
 *
 * @author Martin Albedinsky
 */
public interface FactoryHolderAdapter extends ListAdapter, SpinnerAdapter {

	/**
	 * Returns the id of this factory holder adapter instance.
	 *
	 * @return Adapter id.
	 */
	public int getAdapterId();

	/**
	 * Returns a type of an item's view for the currently iterated position.
	 *
	 * @return View type provided by {@link #getItemViewType(int)} for the currently iterated position.
	 */
	public int getCurrentViewType();
}
