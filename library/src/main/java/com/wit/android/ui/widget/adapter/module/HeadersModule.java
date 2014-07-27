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
 * todo: description
 * </p>
 *
 * @param <H>
 * @author Martin Albedinsky
 */
public abstract class HeadersModule<H extends HeadersModule.Header> extends AdapterModule {

	/**
	 * Constants ===================================================================================
	 */

	/**
	 * Log TAG.
	 */
	private static final String TAG = HeadersModule.class.getSimpleName();

	/**
	 * Flag indicating whether the debug output trough log-cat is enabled or not.
	 */
	// private static final boolean DEBUG_ENABLED = false;

	/**
	 * Flag indicating whether the output trough log-cat is enabled or not.
	 */
	// private static final boolean LOG_ENABLED = true;

	/**
	 * Enums =======================================================================================
	 */

	/**
	 * Static members ==============================================================================
	 */

	/**
	 * Members =====================================================================================
	 */

	/**
	 * An attribute from the current theme, which should contain a resource of the style for the
	 * header view.
	 */
	private int mHeaderStyleAttr = android.R.attr.textViewStyle;

	/**
	 * Arrays --------------------------------------------------------------------------------------
	 */

	/**
	 * Set of headers managed by this module mapped to theirs positions.
	 */
	private final SparseArray<H> HEADERS = new SparseArray<>();

	/**
	 * Booleans ------------------------------------------------------------------------------------
	 */

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
	 * <p>
	 * Checks whether there is a header at the specified <var>position</var> or not.
	 * </p>
	 *
	 * @param position The position to check.
	 * @return <code>True</code> if there is a header item at the specified position, <code>false</code>
	 * otherwise.
	 */
	public boolean isHeaderAt(int position) {
		return HEADERS.get(position) != null;
	}

	/**
	 * <p>
	 * Checks whether this module has some headers or not.
	 * </p>
	 *
	 * @return <code>True</code> if this module does not have any headers, <code>false</code> otherwise.
	 */
	public boolean isEmpty() {
		return HEADERS.size() == 0;
	}

	/**
	 * <p>
	 * Clears the current headers data set of this module.
	 * </p>
	 */
	public void clearHeaders() {
		HEADERS.clear();
	}

	/**
	 * <p>
	 * Corrects the given <var>position</var> passed from the related adapter. Position will be
	 * corrected (decreased) by count of the headers counted by {@link #getHeadersCountBeforePosition(int)}.
	 * </p>
	 * <p>
	 * This should be used within the related adapter's {@link android.widget.Adapter#getItem(int) Adapter#getItem(int)}.
	 * </p>
	 *
	 * @param position The position to correct.
	 * @return Corrected position which can be used in the related adapter to access items from its
	 * data set.
	 */
	public int correctPosition(int position) {
		return position - getHeadersCountBeforePosition(position);
	}

	/**
	 * <p>
	 * Called to crate a view for header item at the specified <var>position</var>.
	 * </p>
	 * <p>
	 * <b>Note</b>, that a position passed here need to be the same position as passed to
	 * {@link android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup) Adapter#getView(int, android.view.View, android.view.ViewGroup)}.
	 * </p>
	 *
	 * @param position The position for which should be view created.
	 * @param inflater Valid layout inflater to create the requested view.
	 * @param root     The parent to that should be header's view eventually attached to.
	 * @return A view corresponding to the header item at the specified position.
	 */
	public View createHeaderView(int position, LayoutInflater inflater, ViewGroup root) {
		return HeaderHolder.createView(inflater.getContext(), mHeaderStyleAttr);
	}

	/**
	 * <p>
	 * Called to create a view holder for header item at the specified <var>position</var>.
	 * </p>
	 * <p>
	 * <b>Note</b>, that a position passed here need to be the same position as passed to
	 * {@link android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup) Adapter#getView(int, android.view.View, android.view.ViewGroup)}.
	 * </p>
	 *
	 * @param position   The position for which should be holder created.
	 * @param headerView The header's view, for which should be holder created.
	 * @return An instance of HeaderHolder for header's view at the specified position.
	 */
	public Object createHeaderViewHolder(int position, View headerView) {
		return (headerView instanceof TextView) ? new HeaderHolder((TextView) headerView) : new Object();
	}

	/**
	 * <p>
	 * Called to bind header's view at the specified <var>position</var>.
	 * </p>
	 * <p>
	 * <b>Note</b>, that a position passed here need to be the same position as passed to
	 * {@link android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup) Adapter#getView(int, android.view.View, android.view.ViewGroup)}.
	 * </p>
	 *
	 * @param position     The position for which should be holder's view populated with data.
	 * @param headerHolder An instance of holder created by {@link #createHeaderViewHolder(int, android.view.View)}
	 *                     for the specified position.
	 */
	public void bindHeaderView(int position, Object headerHolder) {
		if (headerHolder instanceof HeaderHolder) {
			final Header header = getHeader(position);
			if (header != null) {
				((HeaderHolder) headerHolder).setText(header.getText());
			} else {
				Log.e(TAG, "Invalid header at position(" + position + ").");
			}
		}
	}

	/**
	 * Getters + Setters ---------------------------------------------------------------------------
	 */

	/**
	 * <p>
	 * Sets an xml attribute from the current theme, which contains a resource of style with attributes
	 * for header view. See {@link com.wit.android.ui.widget.adapter.module.AlphabeticHeaders.HeaderHolder#createView(android.content.Context, int)}
	 * for more information in which theme should be the given <var>styleAttr</var> placed.
	 * </p>
	 *
	 * @param styleAttr An xml attribute.
	 * @see #getHeaderStyleAttr()
	 * @see com.wit.android.ui.widget.adapter.module.AlphabeticHeaders.HeaderHolder
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
	 * Counts headers presented in the current headers data set before the requested <var>position</var>.
	 * </p>
	 *
	 * @param position The position, to which should be headers counted.
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
	 * Returns count of headers in the current headers data set of this module.
	 * </p>
	 *
	 * @return Headers count.
	 */
	public int size() {
		return HEADERS.size();
	}

	/**
	 * <p>
	 * Returns a header associated with the specified position from the current headers data set of
	 * this module.
	 * </p>
	 *
	 * @param position Position of the desired header to obtain.
	 * @return An instance of header at the requested position or <code>null</code> if there is no
	 * header item at the requested position.
	 */
	public H getHeader(int position) {
		return HEADERS.get(position);
	}

	/**
	 * <p>
	 * Returns the current headers data set of this module.
	 * </p>
	 *
	 * @return Set of headers mapped to theirs positions.
	 */
	public SparseArray<H> getHeaders() {
		return HEADERS;
	}

	/**
	 * <p>
	 * Returns the current headers data set of this module.
	 * </p>
	 *
	 * @return List of the current headers.
	 */
	public List<H> getHeadersList() {
		final List<H> headers = new ArrayList<>(HEADERS.size());
		for (int i = 0; i < HEADERS.size(); i++) {
			headers.add(HEADERS.get(HEADERS.keyAt(i)));
		}
		return headers;
	}

	/**
	 * Protected -----------------------------------------------------------------------------------
	 */

	/**
	 * <p>
	 * Adds the given header at the specified position into the current headers data set of this module.
	 * If there is already header at the specified position, the current one will be replaced by the
	 * given one.
	 * </p>
	 *
	 * @param header   Header to add.
	 * @param position The position, at which should be header added.
	 */
	protected void addHeader(H header, int position) {
		HEADERS.append(position, header);
	}

	/**
	 * <p>
	 * Removes a header at the specified position from the current headers data set of this module.
	 * </p>
	 *
	 * @param position The position, at which should be header removed.
	 */
	protected void removeHeader(int position) {
		HEADERS.remove(position);
	}

	/**
	 * Private -------------------------------------------------------------------------------------
	 */

	/**
	 * Abstract methods ----------------------------------------------------------------------------
	 */

	/**
	 * Inner classes ===============================================================================
	 */

	/**
	 * <h4>Class Overview</h4>
	 * <p>
	 * todo: description
	 * </p>
	 *
	 * @author Martin Albedinsky
	 */
	public static class HeaderHolder {

		/**
		 * Members =================================================================================
		 */

		/**
		 * Root view of this header holder.
		 */
		private TextView mTextView;

		/**
		 * Constructors ============================================================================
		 */

		/**
		 * <p>
		 * Creates a new instance of HeaderHolder with the given TextView.
		 * </p>
		 *
		 * @param textView Root view of this holder.
		 */
		public HeaderHolder(TextView textView) {
			this.mTextView = textView;
		}

		/**
		 * Methods =================================================================================
		 */

		/**
		 * <p>
		 * Creates a new instance of TextView with the given default style attribute.
		 * </p>
		 *
		 * @param context   Valid context with which will be TextView created.
		 * @param styleAttr An xml attribute for the TextView's constructor
		 *                  {@link android.widget.TextView#TextView(android.content.Context, android.util.AttributeSet, int)}.
		 * @return New instance of TextView.
		 */
		public static TextView createView(Context context, int styleAttr) {
			return new TextView(context, null, styleAttr);
		}

		/**
		 * <p>
		 * Returns an instance of TextView passed to constructor {@link #HeaderHolder(android.widget.TextView)}.
		 * </p>
		 *
		 * @return Instance of TextView of this holder.
		 */
		public TextView getTextView() {
			return mTextView;
		}

		/**
		 * <p>
		 * Sets the string value for the current TextView.
		 * </p>
		 *
		 * @param resId A resource id of the desired text.
		 */
		public void setText(int resId) {
			if (mTextView != null) {
				mTextView.setText(resId);
			}
		}

		/**
		 * <p>
		 * Sets the string value for the current TextView.
		 * </p>
		 *
		 * @param text The desired text.
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
	 * Simple implementation of {@link com.wit.android.ui.widget.adapter.module.HeadersModule.Header Header}
	 * item for {@link HeadersModule}.
	 * </p>
	 *
	 * @author Martin Albedinsky
	 */
	public static class SimpleHeader implements Header {

		/**
		 * Members =================================================================================
		 */

		/**
		 * Header text value.
		 */
		private String mText;

		/**
		 * Constructors ============================================================================
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
		 * Methods =================================================================================
		 */

		/**
		 */
		@Override
		public String getText() {
			return mText;
		}
	}

	/**
	 * Interface ===================================================================================
	 */

	/**
	 * <h4>Interface Overview</h4>
	 * <p>
	 * Required interface for header item used by {@link com.wit.android.ui.widget.adapter.module.HeadersModule}
	 * module.
	 * </p>
	 *
	 * @author Martin Albedinsky
	 */
	public static interface Header {

		/**
		 * <p>
		 * Returns a text value of this header instance.
		 * </p>
		 *
		 * @return The text value of this header item.
		 */
		public String getText();
	}
}
