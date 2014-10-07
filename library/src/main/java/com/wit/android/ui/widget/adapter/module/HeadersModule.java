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
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
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
 * todo: description
 *
 * @param <H> A type of the header items presented within a subclass of this HeadersModule.
 * @author Martin Albedinsky
 */
public abstract class HeadersModule<H extends HeadersModule.Header> extends AdapterModule {

	/**
	 * Interface ===================================================================================
	 */

	/**
	 * <h4>Interface Overview</h4>
	 * Required interface for header item used by {@link com.wit.android.ui.widget.adapter.module.HeadersModule}
	 * module.
	 *
	 * @author Martin Albedinsky
	 */
	public static interface Header {

		/**
		 * Returns a text value of this header instance.
		 *
		 * @return The text value of this header item.
		 */
		@NonNull
		public String getText();
	}

	/**
	 * Constants ===================================================================================
	 */

	/**
	 * Log TAG.
	 */
	private static final String TAG = "HeadersModule";

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
	 * Set of headers managed by this module mapped to theirs positions.
	 */
	private final SparseArray<H> HEADERS = new SparseArray<>();

	/**
	 * An attribute from the current theme, which should contain a resource of the style for the
	 * header view.
	 */
	private int mHeaderStyleAttr = android.R.attr.textViewStyle;

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
	 * Checks whether there is a header at the specified <var>position</var> or not.
	 *
	 * @param position The position to check.
	 * @return <code>True</code> if there is a header item at the specified position, <code>false</code>
	 * otherwise.
	 */
	public boolean isHeaderAt(int position) {
		return HEADERS.get(position) != null;
	}

	/**
	 * Checks whether this module has some headers or not.
	 *
	 * @return <code>True</code> if this module does not have any headers, <code>false</code> otherwise.
	 */
	public boolean isEmpty() {
		return HEADERS.size() == 0;
	}

	/**
	 * Clears the current headers data set of this module.
	 */
	public void clearHeaders() {
		HEADERS.clear();
	}

	/**
	 * Corrects the given <var>position</var> passed from the related adapter. Position will be
	 * corrected (decreased) by count of the headers counted by {@link #getHeadersCountBeforePosition(int)}.
	 * <p/>
	 * This should be used within the related adapter's {@link android.widget.Adapter#getItem(int) Adapter#getItem(int)}.
	 *
	 * @param position The position to correct.
	 * @return Corrected position which can be used in the related adapter to access items from its
	 * data set.
	 */
	public int correctPosition(int position) {
		return position - getHeadersCountBeforePosition(position);
	}

	/**
	 * Called to crate a view for header item at the specified <var>position</var>.
	 * <p/>
	 * <b>Note</b>, that a position passed here need to be the same position as passed to
	 * {@link android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup) Adapter#getView(int, android.view.View, android.view.ViewGroup)}.
	 *
	 * @param position The position for which should be view created.
	 * @param inflater Valid layout inflater to create the requested view.
	 * @param root     The parent to that should be header's view eventually attached to.
	 * @return A view corresponding to the header item at the specified position.
	 */
	@NonNull
	public View createHeaderView(int position, @NonNull LayoutInflater inflater, @NonNull ViewGroup root) {
		return HeaderHolder.createView(inflater.getContext(), mHeaderStyleAttr);
	}

	/**
	 * Called to create a view holder for header item at the specified <var>position</var>.
	 * <p/>
	 * <b>Note</b>, that a position passed here need to be the same position as passed to
	 * {@link android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup) Adapter#getView(int, android.view.View, android.view.ViewGroup)}.
	 *
	 * @param position   The position for which should be holder created.
	 * @param headerView The header's view, for which should be holder created.
	 * @return An instance of HeaderHolder for header's view at the specified position.
	 */
	@Nullable
	public Object createHeaderViewHolder(int position, @NonNull View headerView) {
		return (headerView instanceof TextView) ? new HeaderHolder((TextView) headerView) : new Object();
	}

	/**
	 * Called to bind header's view at the specified <var>position</var>.
	 * <p/>
	 * <b>Note</b>, that a position passed here need to be the same position as passed to
	 * {@link android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup) Adapter#getView(int, android.view.View, android.view.ViewGroup)}.
	 *
	 * @param position     The position for which should be holder's view populated with data.
	 * @param headerHolder An instance of holder created by {@link #createHeaderViewHolder(int, android.view.View)}
	 *                     for the specified position.
	 */
	public void bindHeaderView(int position, @NonNull Object headerHolder) {
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
	 * Sets an xml attribute from the current theme, which contains a resource of style with attributes
	 * for header view. See {@link com.wit.android.ui.widget.adapter.module.AlphabeticHeaders.HeaderHolder#createView(android.content.Context, int)}
	 * for more information in which theme should be the given <var>styleAttr</var> placed.
	 *
	 * @param styleAttr An xml attribute.
	 * @see #getHeaderStyleAttr()
	 * @see com.wit.android.ui.widget.adapter.module.AlphabeticHeaders.HeaderHolder
	 */
	public void setHeaderStyleAttr(@AttrRes int styleAttr) {
		this.mHeaderStyleAttr = styleAttr;
	}

	/**
	 * Returns the xml attribute set by {@link #setHeaderStyleAttr(int)}.
	 *
	 * @return Xml attribute.
	 * @see #setHeaderStyleAttr(int)
	 */
	@AttrRes
	public int getHeaderStyleAttr() {
		return mHeaderStyleAttr;
	}

	/**
	 * Counts headers presented in the current headers data set before the requested <var>position</var>.
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
	 * Returns count of headers in the current headers data set of this module.
	 *
	 * @return Headers count.
	 */
	public int size() {
		return HEADERS.size();
	}

	/**
	 * Returns a header associated with the specified position from the current headers data set of
	 * this module.
	 *
	 * @param position Position of the desired header to obtain.
	 * @return An instance of header at the requested position or <code>null</code> if there is no
	 * header item at the requested position.
	 */
	@Nullable
	public H getHeader(int position) {
		return HEADERS.get(position);
	}

	/**
	 * Returns the current headers data set of this module.
	 *
	 * @return Set of headers mapped to theirs positions.
	 */
	@NonNull
	public SparseArray<H> getHeaders() {
		return HEADERS;
	}

	/**
	 * Returns the current headers data set of this module.
	 *
	 * @return List of the current headers.
	 */
	@NonNull
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
	 * Adds the given header at the specified position into the current headers data set of this module.
	 * If there is already header at the specified position, the current one will be replaced by the
	 * given one.
	 *
	 * @param header   Header to add.
	 * @param position The position, at which should be header added.
	 */
	protected void addHeader(@NonNull H header, int position) {
		HEADERS.append(position, header);
	}

	/**
	 * Removes a header at the specified position from the current headers data set of this module.
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
	 * Inner classes ===============================================================================
	 */

	/**
	 * <h4>Class Overview</h4>
	 * todo: description
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
		 * Creates a new instance of HeaderHolder with the given TextView.
		 *
		 * @param textView Root view of this holder.
		 */
		public HeaderHolder(@NonNull TextView textView) {
			this.mTextView = textView;
		}

		/**
		 * Methods =================================================================================
		 */

		/**
		 * Creates a new instance of TextView with the given default style attribute.
		 *
		 * @param context   Valid context with which will be TextView created.
		 * @param styleAttr An xml attribute for the TextView's constructor
		 *                  {@link android.widget.TextView#TextView(android.content.Context, android.util.AttributeSet, int)}.
		 * @return New instance of TextView.
		 */
		@NonNull
		public static TextView createView(@NonNull Context context, @AttrRes int styleAttr) {
			return new TextView(context, null, styleAttr);
		}

		/**
		 * Returns an instance of TextView passed to constructor {@link #HeaderHolder(android.widget.TextView)}.
		 *
		 * @return Instance of TextView of this holder.
		 */
		@NonNull
		public TextView getTextView() {
			return mTextView;
		}

		/**
		 * Sets the string value for the current TextView.
		 *
		 * @param resId A resource id of the desired text.
		 */
		public void setText(@StringRes int resId) {
			mTextView.setText(resId);
		}

		/**
		 * Sets the string value for the current TextView.
		 *
		 * @param text The desired text.
		 */
		public void setText(@Nullable CharSequence text) {
			mTextView.setText(text);
		}
	}

	/**
	 * <h4>Interface Overview</h4>
	 * Simple implementation of {@link com.wit.android.ui.widget.adapter.module.HeadersModule.Header Header}
	 * item for {@link HeadersModule}.
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
		 * Creates a new instance of SimpleHeader with the given text value.
		 *
		 * @param text Text value for header.
		 */
		public SimpleHeader(@NonNull String text) {
			this.mText = text;
		}

		/**
		 * Methods =================================================================================
		 */

		/**
		 */
		@NonNull
		@Override
		public String getText() {
			return mText;
		}
	}
}
