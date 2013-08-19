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
package com.wit.and.internal.widget.adapter;

import com.wit.and.widget.adapter.OptBaseAdapter;
import com.wit.and.widget.adapter.SelectableItem;
import com.wit.and.widget.adapter.SelectionModule;

/**
 * <p>
 * public class
 * </p>
 * <h5>DefaultSelectionModule</h5>
 * <p>
 * extends {@link com.wit.and.widget.adapter.SelectionModule}
 * </p>
 * <h4>Class Overview</h4>
 * <p>
 * </p>
 * 
 * @param <Adapter>
 *            Type of the {@link OptBaseAdapter}.
 * 
 * @author Martin Albedinsky
 */
public class DefaultSelectionModule<Adapter extends OptBaseAdapter> extends SelectionModule<SelectableItem, Adapter> {

	/**
	 * Constants =============================
	 */

    /**
     * Log TAG.
     */
    // private static final String TAG = DefaultSelectionModule.class.getSimpleName();

    /**
     * Indicates if debug private output trough log-cat is enabled.
     */
    // private static final boolean DEBUG = true;

    /**
     * Indicates if logging for user output trough log-cat is enabled.
     */
    // private static final boolean USER_LOG = true;

	/**
	 * Enums =================================
	 */

    /**
     * Static members ========================
     */

	/**
	 * Members ===============================
	 */

	/**
	 * Listeners -----------------------------
	 */

	/**
	 * Arrays --------------------------------
	 */

	/**
	 * Booleans ------------------------------
	 */

	/**
	 * Constructors ==========================
	 */

	/**
	 * Methods ===============================
	 */

	/**
	 * Public --------------------------------
	 */

	/**
	 * 
	 */
	@Override
	public void setItemSelected(int position, boolean selected) {
        switch (getMode()) {
            case MODE_MULTIPLE:
                break;
            case MODE_SINGLE:
                clearSelection(false);
                break;
        }

		if (selected) {
            add(position);
		} else {
			remove(position);
		}
		notifyAdapter();
	}

	@Override
	public void selectAll() {
		// TODO Auto-generated method stub
	}

	@Override
	public void selectRange(int startPosition, int count) {
		// TODO Auto-generated method stub
	}

	/**
	 * Getters + Setters ---------------------
	 */

	/**
	 * Protected -----------------------------
	 */

	/**
	 * Private -------------------------------
	 */

	/**
	 * Abstract methods ----------------------
	 */

	/**
	 * Inner classes =========================
	 */

	/**
	 * Interface =============================
	 */
}
