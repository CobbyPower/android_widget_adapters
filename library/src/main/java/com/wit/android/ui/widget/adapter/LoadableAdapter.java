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
public interface LoadableAdapter {

	/**
	 * Constants ===================================================================================
	 */

	/**
	 * <p>
	 * Flag for {@link #dispatchLoadingStatus(int)} to dispatch that loading of data set just started.
	 * </p>
	 */
	public static final int STATUS_LOADING_STARTED = 0x01;

	/**
	 * <p>
	 * Flag for {@link #dispatchLoadingStatus(int)} to dispatch that loading of data set just finished.
	 * </p>
	 */
	public static final int STATUS_LOADING_FINISHED = 0x02;

	/**
	 * Methods =====================================================================================
	 */

	/**
	 * <p>
	 * Called to dispatch loading status to this loadable adapter implementation.
	 * </p>
	 *
	 * @param status Current loading status. One of {@link #STATUS_LOADING_STARTED} or {@link #STATUS_LOADING_FINISHED}.
	 */
	public void dispatchLoadingStatus(int status);

	/**
	 * Inner classes ===============================================================================
	 */
}