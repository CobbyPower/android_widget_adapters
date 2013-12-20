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
 *
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
	 *
	 */
	private int mHeaderStyleAttr = android.R.attr.textViewStyle;

	/**
	 *
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
				((HeaderHolder) headerHolder).mTextView.setText(header.getAlphabeticChar());
			} else {
				Log.e(TAG, "Invalid header at position(" + position + ").");
			}
		}
	}

	/**
	 * <p>
	 * </p>
	 * <p>
	 * Also adapter will be notified about data set change.
	 * </p>
	 *
	 * @param cursor
	 * @param <C>
	 */
	public <C extends Cursor & AlphabeticItem> void processAlphabeticCursor(C cursor) {
		if (cursor.getCount() > 0 && cursor.moveToFirst()) {
			this.resetLastChar();
			do  {
				this.processAlphabeticItem(cursor, cursor.getPosition());
			} while (cursor.moveToNext());
			this.resetLastChar();
		}
		notifyAdapter();
	}

	/**
	 * <p>
	 * </p>
	 * <p>
	 * Also adapter will be notified about data set change.
	 * </p>
	 *
	 * @param itemList
	 */
	public void processAlphabeticList(List<AlphabeticItem> itemList) {
		this.resetLastChar();
		for (int i = 0; i < itemList.size(); i++) {
			this.processAlphabeticItem(itemList.get(i), i);
		}
		this.resetLastChar();
		notifyAdapter();
	}

	/**
	 * Getters + Setters ---------------------
	 */

	/**
	 * <p>
	 * </p>
	 *
	 * @param styleAttr
	 *
	 * @see #getHeaderStyleAttr()
	 */
	public void setHeaderStyleAttr(int styleAttr) {
		this.mHeaderStyleAttr = styleAttr;
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @return
	 *
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
	 * </p>
	 *
	 * @param item
	 * @param position
	 */
	protected final void processAlphabeticItem(AlphabeticItem item, int position) {
		final String name = item.getName();
		if (name != null && name.length() > 0) {
			// Obtain first char from item name.
			String currentChar = name.substring(0, 1);
			if (!currentChar.equals(mLastChar)) {
				addHeader(new BaseAlphabeticHeader(currentChar), getHeadersCount() + position);
			}
			// Save current as last.
			this.mLastChar = currentChar;
		}
	}

	/**
	 * Private -------------------------------
	 */

	/**
	 *
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
	 *
	 */
	public static class HeaderHolder {

		/**
		 * Members ===============================
		 */

		/**
		 *
		 */
		private TextView mTextView;

		/**
		 * Constructors ==========================
		 */

		/**
		 * <p>
		 * </p>
		 *
		 * @param textView
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
		 * </p>
		 *
		 * @param context
		 * @param styleAttr
		 * @return
		 */
		public static TextView createView(Context context, int styleAttr) {
			return new TextView(context, null, styleAttr);
		}

		/**
		 * Getters + Setters ---------------------
		 */

		/**
		 * <p>
		 * </p>
		 *
		 * @return
		 */
		public TextView getTextView() {
			return mTextView;
		}
	}

	/**
	 *
	 */
	private static final class BaseAlphabeticHeader implements AlphabeticHeader {

		/**
		 * Members ===============================
		 */

		private String alphabeticChar;

		/**
		 * Constructors ==========================
		 */

		/**
		 *
		 * @param alphabeticChar
		 */
		BaseAlphabeticHeader(String alphabeticChar) {
			this.alphabeticChar = alphabeticChar;
		}

		/**
		 * Methods ===============================
		 */

		/**
		 */
		@Override
		public String getAlphabeticChar() {
			return alphabeticChar;
		}
	}

	/**
	 * Interface =============================
	 */

	/**
	 * <h4>Interface Overview</h4>
	 * <p>
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
		 * </p>
		 *
		 * @return
		 */
		public String getAlphabeticChar();

	}

	/**
	 * <h4>Interface Overview</h4>
	 * <p>
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
		 * </p>
		 *
		 * @return
		 */
		public String getName();
	}
}
