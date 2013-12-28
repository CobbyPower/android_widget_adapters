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

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * <h4>Class Overview</h4>
 * <p>
 * </p>
 *
 * @param <Adapter>
 * @author Martin Albedinsky
 */
public class AlphabeticHeaders<Adapter extends AdapterModule.ModuleAdapter> extends BaseHeadersModule<AlphabeticHeaders.AlphabeticHeader, Adapter> {

	/**
	 * Constants =============================
	 */

	/**
	 * Log TAG.
	 */
	private static final String TAG = AlphabeticHeaders.class.getSimpleName();

	/**
	 * Indicates if debug private output trough log-cat is enabled.
	 */
	// private static final boolean DEBUG = false;

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
	 * Xml attribute which should contain the style for the header view.
	 */
	private int mHeaderStyleAttr = android.R.attr.textViewStyle;

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
	 */
	@Override
	public Object createHeaderViewHolder(int position, View headerView) {
		return (headerView instanceof TextView) ? new HeaderHolder((TextView) headerView) : new Object();
	}

	/**
	 */
	@Override
	public View createHeaderView(int position, LayoutInflater inflater, ViewGroup root) {
		return HeaderHolder.createView(inflater.getContext(), mHeaderStyleAttr);
	}

	/**
	 */
	@Override
	public void bindHeaderView(int position, Object headerHolder) {
		if (headerHolder instanceof HeaderHolder) {
			AlphabeticHeader header = getHeader(position);
			if (header != null) {
				((HeaderHolder) headerHolder).setText(header.getAlphabeticChar());
			} else {
				Log.e(TAG, "Invalid header at position(" + position + ").");
			}
		}
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
	 * @see #processAlphabeticList(java.util.List)
	 */
	public <C extends Cursor & AlphabeticItem> void processAlphabeticCursor(C cursor) {
		// Clear current headers.
		clearHeaders();
		// Process the given cursor.
		if (cursor.getCount() > 0 && cursor.moveToFirst()) {
			this.resetLastChar();
			do {
				this.processAlphabeticItem(cursor, cursor.getPosition());
			} while (cursor.moveToNext());
			this.resetLastChar();
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
		this.resetLastChar();
		notifyAdapter();
	}

	/**
	 * Getters + Setters ---------------------
	 */

	/**
	 * <p>
	 * Sets an xml attribute which should contain style for the header view. See
	 * {@link com.wit.android.widget.adapter.module.AlphabeticHeaders.HeaderHolder#createView(android.content.Context, int)}
	 * for more information in which theme should be the given <var>styleAttr</var> placed.
	 * </p>
	 *
	 * @param styleAttr An xml attribute to style the view for the header item provided
	 *                  by this headers module.
	 * @see #getHeaderStyleAttr()
	 * @see com.wit.android.widget.adapter.module.AlphabeticHeaders.HeaderHolder
	 */
	public void setHeaderStyleAttr(int styleAttr) {
		this.mHeaderStyleAttr = styleAttr;
	}

	/**
	 * <p>
	 * Returns the xml attribute set by {@link #setHeaderStyleAttr(int)}.
	 * </p>
	 *
	 * @return Xml attribute.
	 * @see #setHeaderStyleAttr(int)
	 */
	public int getHeaderStyleAttr() {
		return mHeaderStyleAttr;
	}

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
				addHeader(new SimpleAlphabeticHeader(currentChar), getHeadersCount() + position);
			}
			// Save current as last.
			this.mLastChar = currentChar;
		}
	}

	/**
	 * Private -------------------------------
	 */

	/**
	 * Resets the value of the last char.
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
	 * <h4>Class Overview</h4>
	 * <p>
	 * </p>
	 *
	 * @author Martin Albedinsky
	 */
	public static class HeaderHolder {

		/**
		 * Members ===============================
		 */

		/**
		 * Root view of this header holder.
		 */
		private TextView mTextView;

		/**
		 * Constructors ==========================
		 */

		/**
		 * <p>
		 * Creates the new HeaderHolder instance with the given TextView.
		 * </p>
		 *
		 * @param textView Root view of this holder.
		 */
		public HeaderHolder(TextView textView) {
			this.mTextView = textView;
		}

		/**
		 * Methods ===============================
		 */

		/**
		 * Public --------------------------------
		 */

		/**
		 * <p>
		 * Creates the new instance of TextView for this HeaderHolder.
		 * </p>
		 *
		 * @param context   Context for the TextView.
		 * @param styleAttr An xml attribute for the TextView constructor
		 *                  {@link android.widget.TextView#TextView(android.content.Context, android.util.AttributeSet, int)}.
		 * @return New instance of TextView.
		 */
		public static TextView createView(Context context, int styleAttr) {
			return new TextView(context, null, styleAttr);
		}

		/**
		 * Getters + Setters ---------------------
		 */

		/**
		 * <p>
		 * Returns TextView passed to the constructor {@link #HeaderHolder(android.widget.TextView)}.
		 * </p>
		 *
		 * @return TextView instance.
		 */
		public TextView getTextView() {
			return mTextView;
		}

		/**
		 * <p>
		 * Sets the given <var>text</var> as text for the current TextView.
		 * </p>
		 *
		 * @param text Header text.
		 */
		public void setText(CharSequence text) {
			if (mTextView != null) {
				mTextView.setText(text);
			}
		}
	}

	/**
	 * Simple implementation of alphabetic header for internal purpose.
	 */
	private static final class SimpleAlphabeticHeader implements AlphabeticHeader {

		/**
		 * Members ===============================
		 */

		/**
		 * First alphabetic char for this header.
		 */
		private String mAlphabeticChar;

		/**
		 * Constructors ==========================
		 */

		/**
		 * Creates the new instance of the SimpleAlphabeticHeader with
		 * the given char.
		 *
		 * @param alphabeticChar Char for this alphabetic header.
		 */
		SimpleAlphabeticHeader(String alphabeticChar) {
			this.mAlphabeticChar = alphabeticChar;
		}

		/**
		 * Methods ===============================
		 */

		/**
		 */
		@Override
		public String getAlphabeticChar() {
			return mAlphabeticChar;
		}
	}

	/**
	 * Interface =============================
	 */

	/**
	 * <h4>Interface Overview</h4>
	 * <p>
	 * Required interface for header item used by
	 * {@link com.wit.android.widget.adapter.module.AlphabeticHeaders} module.
	 * </p>
	 *
	 * @author Martin Albedinsky
	 */
	public static interface AlphabeticHeader {

		/**
		 * Methods ===============================
		 */

		/**
		 * <p>
		 * Returns character (can be more characters) which represents
		 * this alphabetic header item.
		 * </p>
		 *
		 * @return Alphabetic char of this header item.
		 */
		public String getAlphabeticChar();
	}

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
