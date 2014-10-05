/*
 * =================================================================================================
 *                 Copyright (C) 2013 - 2014 Martin Albedinsky [Wolf-ITechnologies]
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
package com.wit.android.ui.widget.adapter.module;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.List;

/**
 * <h4>Class Overview</h4>
 * todo: description
 *
 * @author Martin Albedinsky
 */
public class AlphabeticHeaders extends HeadersModule<HeadersModule.SimpleHeader> {

	/**
	 * Interface ===================================================================================
	 */

	/**
	 * <h4>Interface Overview</h4>
	 * Required interface for item which can be processed by {@link com.wit.android.ui.widget.adapter.module.AlphabeticHeaders}
	 * module.
	 *
	 * @author Martin Albedinsky
	 */
	public static interface AlphabeticItem {

		/**
		 * Returns name of this alphabetic item.
		 *
		 * @return This item's name.
		 */
		@NonNull
		public String getName();
	}

	/**
	 * Constants ===================================================================================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = "AlphabeticHeaders";

	/**
	 * Flag indicating whether the debug output trough log-cat is enabled or not.
	 */
	// private static final boolean DEBUG_ENABLED = false;

	/**
	 * Flag indicating whether the output trough log-cat is enabled or not.
	 */
	// private static final boolean LOG_ENABLED = true;

	/**
	 * Static members ==============================================================================
	 */

	/**
	 * Members =====================================================================================
	 */

	/**
	 * Char which was lastly processed from the current alphabetic data set.
	 * <p/>
	 * This is only for internal purpose.
	 */
	private String mLastChar = "";

	/**
	 * Constructors ================================================================================
	 */

	/**
	 * Methods =====================================================================================
	 */

	/**
	 * Public --------------------------------------------------------------------------------------
	 */

	/**
	 */
	@Override
	public void clearHeaders() {
		super.clearHeaders();
		this.resetLastChar();
	}

	/**
	 * Like {@link #processAlphabeticList(java.util.List)} in that difference, that here will be the
	 * given cursor iterated to obtain first characters for headers data set.
	 *
	 * @param cursor An alphabetic cursor to process.
	 * @param <C>    A type of the given alphabetic cursor.
	 */
	public <C extends Cursor & AlphabeticItem> void processAlphabeticCursor(@NonNull C cursor) {
		clearHeaders();
		if (cursor.getCount() > 0 && cursor.moveToFirst()) {
			do {
				this.processAlphabeticItem(cursor, cursor.getPosition());
			} while (cursor.moveToNext());
		}
		notifyAdapter();
	}

	/**
	 * Processes the given list of alphabetic items. Whole list will be iterated and for each of its
	 * items will be checked the first char of name provided by
	 * {@link com.wit.android.ui.widget.adapter.module.AlphabeticHeaders.AlphabeticItem#getName()},
	 * so created headers data set will contains all different first characters founded at the
	 * first positions of obtained names.
	 * <p/>
	 * Also, the adapter, to which is this module attached, will be notified about data set change.
	 * <p/>
	 * <b>Note</b>, that the given <var>cursor</var> should be already sorted, otherwise the final
	 * headers data set can contain duplicates.
	 *
	 * @param list Alphabetic list to process.
	 * @see #processAlphabeticCursor(android.database.Cursor)
	 */
	public void processAlphabeticList(@NonNull List<AlphabeticItem> list) {
		// Clear current headers.
		clearHeaders();
		// Process the given list.
		for (int i = 0; i < list.size(); i++) {
			this.processAlphabeticItem(list.get(i), i);
		}
		notifyAdapter();
	}

	/**
	 * Getters + Setters ---------------------------------------------------------------------------
	 */

	/**
	 * Protected -----------------------------------------------------------------------------------
	 */

	/**
	 * Process the given alphabetic <var>item</var> and creates header item from it.
	 * <p/>
	 * <b>Note</b>, that there is no check for the same headers within the current set of headers.
	 *
	 * @param item     An alphabetic item to process.
	 * @param position The position at which should be header presented within the headers data set.
	 */
	protected final void processAlphabeticItem(AlphabeticItem item, int position) {
		final String name = item.getName();
		if (!TextUtils.isEmpty(name)) {
			// Obtain first char from item name.
			String currentChar = name.substring(0, 1);
			if (!currentChar.equals(mLastChar)) {
				addHeader(new SimpleHeader(currentChar), size() + position);
			}
			this.mLastChar = currentChar;
		}
	}

	/**
	 * Private -------------------------------------------------------------------------------------
	 */

	/**
	 * Resets value of the current last char.
	 */
	private void resetLastChar() {
		this.mLastChar = "";
	}

	/**
	 * Inner classes ===============================================================================
	 */
}
