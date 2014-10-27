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

import com.wit.android.ui.widget.adapter.ViewHolderFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <h3>Annotation Overview</h3>
 * Defines an annotation for determining a class of {@link com.wit.android.ui.widget.adapter.ViewHolderFactory}
 * responsible for instantiation of {@link com.wit.android.ui.widget.adapter.ViewHolder}
 * instances for adapter's item views.
 *
 * @author Martin Albedinsky
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ItemViewHolderFactory {

	/**
	 * A class of the desired ViewHolderFactory.
	 * <p>
	 * <b>Note</b>, that such a class must have public access and empty public constructor without
	 * parameters.
	 */
	Class<? extends ViewHolderFactory> value();
}
