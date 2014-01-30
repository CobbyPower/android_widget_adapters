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
package com.wit.android.widget.adapter.module;

import android.database.Cursor;

import java.util.List;

/**
 * <h4>Class Overview</h4>
 * <p>
 * </p>
 *
 * @param <Adapter>
 * @author Martin Albedinsky
 */
public class AlphabeticHeaders<Adapter extends AdapterModule.ModuleAdapter> extends BaseHeadersModule<BaseHeadersModule.SimpleHeader, Adapter> {

	/**
	 * Constants =============================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = AlphabeticHeaders.class.getSimpleName();

	/**
	 * Flag indicating whether the debug output trough log-cat is enabled or not.
	 */
	// private static final boolean DEBUG = false;

	/**
	 * Flag indicating whether the output for user trough log-cat is enabled or not.
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
	 * Char which was lastly processed from the current alphabetic data set.
	 * This is only for internal purpose.
	 */
	private String mLastChar = "";

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
	 */
	@Override
	public void clearHeaders() {
		super.clearHeaders();
		this.resetLastChar();
	}

	/**
	 * <p>
	 * Like {@link #processAlphabeticList(java.util.List)} in that difference, that
	 * here will be the given cursor iterated to obtain first characters for headers
	 * data set.
	 * </p>
	 *
	 * @param cursor An alphabetic cursor to process.
	 * @param <C>    Type of the given alphabetic cursor.
	 */
	public <C extends Cursor & AlphabeticItem> void processAlphabeticCursor(C cursor) {
		// Clear current headers.
		clearHeaders();
		// Process the given cursor.
		if (cursor.getCount() > 0 && cursor.moveToFirst()) {
			do {
				this.processAlphabeticItem(cursor, cursor.getPosition());
			} while (cursor.moveToNext());
		}
		notifyAdapter();
	}

	/**
	 * <p>
	 * Processes the given alphabetic list. Whole list will be iterated and for each of
	 * its items will be checked the first char provided by
	 * {@link com.wit.android.widget.adapter.module.AlphabeticHeaders.AlphabeticItem#getName()},
	 * so created headers data set will contains all different first characters founded at the
	 * first positions of obtained names.
	 * </p>
	 * <p>
	 * Also, the adapter, to which is this module attached, will be notified about data set change.
	 * </p>
	 * <p>
	 * <b>Note</b>, that the given <var>cursor</var> should be already sorted, otherwise the final
	 * headers data set can contains duplicates.
	 * </p>
	 *
	 * @param list An alphabetic list to process.
	 * @see #processAlphabeticCursor(android.database.Cursor)
	 */
	public void processAlphabeticList(List<AlphabeticItem> list) {
		// Clear current headers.
		clearHeaders();
		// Process the given list.
		for (int i = 0; i < list.size(); i++) {
			this.processAlphabeticItem(list.get(i), i);
		}
		notifyAdapter();
	}

	/**
	 * Getters + Setters ---------------------
	 */

	/**
	 * Protected -----------------------------
	 */

	/**
	 * <p>
	 * Process the given alphabetic <var>item</var> and creates header item from it if its
	 * first character from its name is different from the last processed one.
	 * </p>
	 *
	 * @param item     An alphabetic item to process.
	 * @param position Position of the given item form the adapter's data set.
	 */
	protected final void processAlphabeticItem(AlphabeticItem item, int position) {
		final String name = item.getName();
		if (name != null && name.length() > 0) {
			// Obtain first char from item name.
			String currentChar = name.substring(0, 1);
			if (!currentChar.equals(mLastChar)) {
				addHeader(new SimpleHeader(currentChar), getHeadersCount() + position);
			}
			// Save current as last.
			this.mLastChar = currentChar;
		}
	}

	/**
	 * Private -------------------------------
	 */

	/**
	 * Resets the value of the current last char.
	 */
	private void resetLastChar() {
		this.mLastChar = "";
	}

	/**
	 * Abstract methods ----------------------
	 */

	/**
	 * Inner classes =========================
	 */

	/**
	 * Interface =============================
	 */

	/**
	 * <h4>Interface Overview</h4>
	 * <p>
	 * Required interface for an items which can be processed by
	 * {@link com.wit.android.widget.adapter.module.AlphabeticHeaders} module.
	 * </p>
	 *
	 * @author Martin Albedinsky
	 */
	public static interface AlphabeticItem {

		/**
		 * Methods ===============================
		 */

		/**
		 * <p>
		 * Returns name of this alphabetic item.
		 * </p>
		 *
		 * @return Item's name.
		 */
		public String getName();
	}
}
