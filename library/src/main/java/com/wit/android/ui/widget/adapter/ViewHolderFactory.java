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

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * <h4>Interface Overview</h4>
 * todo: description
 *
 * @author Martin Albedinsky
 */
public interface ViewHolderFactory {

	/**
	 * Called to instantiate an instance of ViewHolder for the specified <var>position</var> and
	 * <var>view</var>.
	 * <p/>
	 * If not <code>null</code>, {@link ViewHolder#create(int, android.view.View)} will be invoked
	 * upon instance of the created holder.
	 *
	 * @param adapter  An instance of adapter which requests the view holder creation.
	 * @param position The position of an item from the adapter data set for which should be holder
	 *                 created.
	 * @param view     The view created for the specified position by the adapter for which should
	 *                 be holder created.
	 * @return New instance of ViewHolder or <code>null</code> if this factory does not provide
	 * holder for the specified adapter.
	 */
	@Nullable
	public ViewHolder createHolder(@NonNull FactoryHolderAdapter adapter, int position, @NonNull View view);
}
