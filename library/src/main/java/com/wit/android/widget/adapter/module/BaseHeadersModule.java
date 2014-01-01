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

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * <h4>Class Overview</h4>
 * <p>
 * Base implementation for an adapter modules which can provide headers data set for
 * "header-based" adapters.
 * </p>
 *
 * @param <Header> Type of header item provided by implementation of this headers module.
 * @param <Adapter> Type of the adapter for which can be this headers module created and used.
 *
 * @author Martin Albedinsky
 */
public abstract class BaseHeadersModule<Header, Adapter extends AdapterModule.ModuleAdapter> extends AdapterModule<Adapter> {

	/**
	 * Constants =============================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = BaseHeadersModule.class.getSimpleName();

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
	 * Listeners -----------------------------
	 */

	/**
	 * Arrays --------------------------------
	 */

	/**
	 * Array of headers managed by this module.
	 */
	private final SparseArray<Header> HEADERS = new SparseArray<Header>();

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
	 * Getters + Setters ---------------------
	 */

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
	public Header getHeader(int position) {
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
	public List<Header> getHeaders() {
		List<Header> headers = new ArrayList<Header>(HEADERS.size());
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
	 * @param header Header to add.
	 * @param position Position, at which should be header added.
	 */
	protected void addHeader(Header header, int position) {
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
	 * <p>
	 * Called to create view holder for header item at the given position.
	 * </p>
	 *
	 * @param position Position of the header item from the current headers data set.
	 * @param headerView The header's view, for which should be holder created.
	 * @return The view holder for header's view at the specified position.
	 */
	public abstract Object createHeaderViewHolder(int position, View headerView);

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
	public abstract View createHeaderView(int position, LayoutInflater inflater, ViewGroup root);

	/**
	 * <p>
	 * Called to bind header's view at the given position.
	 * </p>
	 *
	 * @param position Position of the header item from the current headers data set.
	 * @param headerHolder The header's view holder created by
	 * {@link #createHeaderViewHolder(int, android.view.View)}.
	 */
	public abstract void bindHeaderView(int position, Object headerHolder);

	/**
	 * Inner classes =========================
	 */

	/**
	 * Interface =============================
	 */
}
