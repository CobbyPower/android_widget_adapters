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

/**
 * <h4>Interface Overview</h4>
 * <p>
 * todo: description
 * </p>
 *
 * @author Martin Albedinsky
 */
public interface OnDataSetActionListener<Adapter extends BaseAdapter> {

	/**
	 * Methods =====================================================================================
	 */

	/**
	 * <p>
	 * Invoked whenever the specified <var>action</var> was selected for the specified <var>position</var>
	 * within the passed <var>adapter</var> in which is this callback registered.
	 * </p>
	 *
	 * @param adapter  An instance of the adapter in which was the specified action selected.
	 * @param action   The action which was currently selected.
	 * @param position The position for which was the specified action selected.
	 * @param id       An id of an item at the specified position within the current data set of the
	 *                 passed adapter.
	 */
	public void onDataSetActionSelected(Adapter adapter, int action, int position, long id);

	/**
	 * Inner classes ===============================================================================
	 */
}
