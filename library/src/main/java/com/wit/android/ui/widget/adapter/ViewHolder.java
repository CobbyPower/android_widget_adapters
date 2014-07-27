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

import android.view.View;

/**
 * <h4>Interface Overview</h4>
 * <p>
 * todo: description
 * </p>
 *
 * @param <Item>    A type of the item of which data will be presented within views hold by implementation
 *                  of this holder.
 * @param <Adapter> A type of the adapter where will be implementation of this holder used.
 * @author Martin Albedinsky
 */
public interface ViewHolder<Item, Adapter extends BaseAdapter> {

	/**
	 * <p>
	 * Called to perform all necessary operations to create a valid instance of this holder, like
	 * to obtain all views which need to be hold by this holder.
	 * </p>
	 *
	 * @param position The position for which is this holder being created.
	 * @param itemView The view of an item for which is this holder being created. This view can be
	 *                 used to access all views to be hold by this holder for later easy access.
	 * @see #bind(int, Object, BaseAdapter)
	 */
	public void create(int position, View itemView);

	/**
	 * <p>
	 * Called to set up and populate all views hold by this holder with data of the given <var>item</var>.
	 * </p>
	 *
	 * @param position The position of the given item.
	 * @param item     Item obtained from the given <var>adapter</var> for the specified position.
	 * @param adapter  An instance of the adapter which requests to bind views of this holder.
	 * @see #create(int, android.view.View)
	 */
	public void bind(int position, Item item, Adapter adapter);
}
