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
package com.wit.android.ui.widget.adapter.annotation;

import com.wit.android.ui.widget.adapter.ViewHolder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <h3>Annotation Overview</h3>
 * <b>This annotation was deprecated in 2.3</b>, use {@link com.wit.android.ui.widget.adapter.annotation.ItemViewHolderFactory @ItemViewHolderFactory}
 * instead.
 * <p>
 * Defines an annotation for determining which class should be used to instantiate a holder for item
 * view.
 * <h3>Usage</h3>
 * <ul>
 * <li>{@link com.wit.android.ui.widget.adapter.BaseAdapter BaseAdapter}</li>
 * </ul>
 *
 * @author Martin Albedinsky
 */
@Deprecated
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ItemViewHolder {

	/**
	 * A class to be used to instantiate the <b>holder</b> for item view.
	 * <p>
	 * <b>Note</b>, that such a class must have public access and empty public constructor without
	 * parameters.
	 */
	Class<? extends ViewHolder> value();
}
