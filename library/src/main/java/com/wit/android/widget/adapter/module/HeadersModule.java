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
package com.wit.android.widget.adapter.module;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * <h4>Class Overview</h4>
 * <p>
 * Base implementation for an adapter modules which can provide headers data set for
 * "header-based" adapters.
 * </p>
 *
 * @param <H> Type of header item provided by implementation of this headers module.
 * @param <Adapter> Type of the adapter for which can be this headers module created and used.
 *
 * @author Martin Albedinsky
 */
public abstract class HeadersModule<H extends HeadersModule.Header, Adapter extends AdapterModule.ModuleAdapter> extends AdapterModule<Adapter> {

	/**
	 * Constants =============================
	 */

	/**
	 * Log TAG.
	 */
	private static final String TAG = HeadersModule.class.getSimpleName();

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
	 * Xml attribute which should contain the style for the header view.
	 */
	private int mHeaderStyleAttr = android.R.attr.textViewStyle;

	/**
	 * Listeners -----------------------------
	 */

	/**
	 * Arrays --------------------------------
	 */

	/**
	 * Array of headers managed by this module.
	 */
	private final SparseArray<H> HEADERS = new SparseArray<>();

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
	 * <p>
	 * Checks whether there is header at the requested
	 * <var>position</var> or not.
	 * </p>
	 *
	 * @param position Position in data set to check.
	 * @return <code>True</code> if there is a header item at the requested position,
	 * <code>false</code> otherwise.
	 */
	public boolean isHeaderAt(int position) {
		return HEADERS.get(position) != null;
	}

	/**
	 * <p>
	 * Checks whether this headers module has some headers or not.
	 * </p>
	 *
	 * @return <code>True</code> if this module doesn't have any headers,
	 * <code>false</code> otherwise.
	 */
	public boolean isEmpty() {
		return getHeadersCount() == 0;
	}

	/**
	 * <p>
	 * Clears current headers data set of this headers module.
	 * </p>
	 */
	public void clearHeaders() {
		HEADERS.clear();
	}

	/**
	 * <p>
	 * Corrects the given position passed from the related adapter. Position will be
	 * corrected by count of the headers counted by {@link #getHeadersCountBeforePosition(int)}.
	 * </p>
	 *
	 * @param position Position to correct.
	 * @return Corrected position which can be used in the related adapter to access
	 * items from its data set.
	 */
	public int correctPosition(int position) {
		return position - getHeadersCountBeforePosition(position);
	}

	/**
	 * <p>
	 * Called to create view holder for header item at the given position.
	 * </p>
	 *
	 * @param position Position of the header item from the current headers data set.
	 * @param headerView The header's view, for which should be holder created.
	 * @return The view holder for header's view at the specified position.
	 */
	public Object createHeaderViewHolder(int position, View headerView) {
		return (headerView instanceof TextView) ? new HeaderHolder((TextView) headerView) : new Object();
	}

	/**
	 * <p>
	 * Called to crate view for header item at the given position.
	 * </p>
	 *
	 * @param position Position of the header item from the current headers data set.
	 * @param inflater Layout inflater for the current context.
	 * @param root The parent to that will be this view eventually attached to.
	 * @return The view corresponding to header item at the specified position.
	 */
	public View createHeaderView(int position, LayoutInflater inflater, ViewGroup root) {
		return HeaderHolder.createView(inflater.getContext(), mHeaderStyleAttr);
	}

	/**
	 * <p>
	 * Called to bind header's view at the given position.
	 * </p>
	 *
	 * @param position Position of the header item from the current headers data set.
	 * @param headerHolder The header's view holder created by
	 * {@link #createHeaderViewHolder(int, android.view.View)}.
	 */
	public void bindHeaderView(int position, Object headerHolder) {
		if (headerHolder instanceof HeaderHolder) {
			Header header = getHeader(position);
			if (header != null) {
				((HeaderHolder) headerHolder).setText(header.getText());
			} else {
				Log.e(TAG, "Invalid header at position(" + position + ").");
			}
		}
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
	 * <p>
	 * Counts headers presented in the current headers
	 * data set before the requested <var>position</var>.
	 * </p>
	 *
	 * @param position Position, to which should be headers counted.
	 * @return The count of headers before the requested position.
	 */
	public int getHeadersCountBeforePosition(int position) {
		int count = 0;
		for (int i = 0; i < HEADERS.size(); i++) {
			if (HEADERS.keyAt(i) < position) {
				count++;
			} else {
				break;
			}
		}
		return count;
	}

	/**
	 * <p>
	 * Returns header associated with the specified position
	 * from the current headers data set of this module.
	 * </p>
	 *
	 * @param position Position of the header to obtain.
	 * @return The header at the requested position.
	 */
	public H getHeader(int position) {
		return HEADERS.get(position);
	}

	/**
	 * <p>
	 * Returns count of the headers in the current headers
	 * data set of this module.
	 * </p>
	 *
	 * @return Headers count.
	 */
	public int getHeadersCount() {
		return HEADERS.size();
	}

	/**
	 * <p>
	 * Returns headers data set of this module.
	 * </p>
	 *
	 * @return List of headers.
	 */
	public List<H> getHeaders() {
		List<H> headers = new ArrayList<H>(HEADERS.size());
		for (int i = 0; i < HEADERS.size(); i++) {
			headers.add(HEADERS.get(HEADERS.keyAt(i)));
		}
		return headers;
	}

	/**
	 * Protected -----------------------------
	 */

	/**
	 * <p>
	 * Adds the given header at the specified position into
	 * the current headers data set of this module. If there
	 * is already header at the specified position, the current
	 * one will be replaced by the given one.
	 * </p>
	 *
	 * @param header H to add.
	 * @param position Position, at which should be header added.
	 */
	protected void addHeader(H header, int position) {
		HEADERS.append(position, header);
	}

	/**
	 * <p>
	 * Removes header at the specified position from the current
	 * headers data set of this module.
	 * </p>
	 *
	 * @param position Position, at which should be header removed.
	 */
	protected void removeHeader(int position) {
		HEADERS.remove(position);
	}

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
		 * @param text H text.
		 */
		public void setText(CharSequence text) {
			if (mTextView != null) {
				mTextView.setText(text);
			}
		}
	}

	/**
	 * <h4>Interface Overview</h4>
	 * <p>
	 * Simple implementation of {@link HeadersModule.Header header}
	 * item.
	 * </p>
	 *
	 * @author Martin Albedinsky
	 */
	public static class SimpleHeader implements Header {

		/**
		 * Members ===============================
		 */

		/**
		 * Header text value.
		 */
		private String mText;

		/**
		 * Constructors ==========================
		 */

		/**
		 * <p>
		 * Creates a new instance of SimpleHeader with the given text value.
		 * </p>
		 *
		 * @param text Text value for header.
		 */
		public SimpleHeader(String text) {
			this.mText = text;
		}

		/**
		 * Methods ===============================
		 */

		/**
		 */
		@Override
		public String getText() {
			return mText;
		}
	}

	/**
	 * Interface =============================
	 */

	/**
	 * <h4>Interface Overview</h4>
	 * <p>
	 * Required interface for header item used by {@link HeadersModule} module.
	 * </p>
	 *
	 * @author Martin Albedinsky
	 */
	public static interface Header {

		/**
		 * Methods ===============================
		 */

		/**
		 * <p>
		 * Returns text value of this header instance.
		 * </p>
		 *
		 * @return The text value of this header.
		 */
		public String getText();
	}
}
