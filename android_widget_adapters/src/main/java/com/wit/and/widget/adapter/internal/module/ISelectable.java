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
package com.wit.and.widget.adapter.internal.module;

/**
 * <h4>Interface Overview</h4>
 * <p>
 * Simple interface for selectable objects. This can be used for list view items
 * for example.
 * </p>
 * 
 * @author Martin Albedinsky
 */
public interface ISelectable {

    /**
	 * Methods ===============================
	 */

	/**
	 * <p>
	 * Changes the selection state of this object.
	 * </p>
	 * 
	 * @param selected
	 *            True if the object must be selected otherwise false.
	 */
	public void setSelected(boolean selected);

	/**
	 * <p>
	 * Checks the selection state of this object.
	 * </p>
	 * 
	 * @return True if this object is selected otherwise false.
	 */
	public boolean isSelected();
}
