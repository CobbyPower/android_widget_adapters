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
package com.wit.and.widget.adapter.module;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * <h4>Class Overview</h4>
 * <p>
 * </p>
 *
 * @param <Header>
 * @param <Adapter>
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
	private static final String TAG = BaseHeadersModule.class.getSimpleName();

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
	 * Listeners -----------------------------
	 */

	/**
	 * Arrays --------------------------------
	 */

	/**
	 *
	 */
	private SparseArray<Header> aHeaders = new SparseArray<Header>();

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
	 * </p>
	 *
	 * @param position
	 * @return
	 */
	public boolean isHeaderAt(int position) {
		return aHeaders.get(position) != null;
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @return
	 */
	public boolean isEmpty() {
		return getHeadersCount() == 0;
	}

	/**
	 * <p>
	 * </p>
	 */
	public void clearHeaders() {
		aHeaders.clear();
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param itemPosition
	 * @return
	 */
	public int correctPosition(int itemPosition) {
		return itemPosition - getHeadersCountAboveItem(itemPosition);
	}

	/**
	 * Getters + Setters ---------------------
	 */

	/**
	 * <p>
	 * </p>
	 *
	 * @param itemPosition
	 * @return
	 */
	public int getHeadersCountAboveItem(int itemPosition) {
		int count = 0;
		for (int i = 0; i < aHeaders.size(); i++) {
			if (aHeaders.keyAt(i) < itemPosition) {
				count++;
			} else {
				break;
			}
		}
		return count;
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param position
	 * @return
	 */
	public Header getHeader(int position) {
		return aHeaders.get(position);
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @return
	 */
	public int getHeadersCount() {
		return aHeaders.size();
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @return
	 */
	public List<Header> getHeaders() {
		List<Header> headers = new ArrayList<Header>(aHeaders.size());
		for (int i = 0; i < aHeaders.size(); i++) {
			headers.add(aHeaders.get(aHeaders.keyAt(i)));
		}
		return headers;
	}

	/**
	 * Protected -----------------------------
	 */

	/**
	 * <p>
	 * </p>
	 *
	 * @param header
	 * @param position
	 */
	protected void addHeader(Header header, int position) {
		aHeaders.append(position, header);
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param position
	 */
	protected void removeHeader(int position) {
		aHeaders.remove(position);
	}

	/**
	 * Private -------------------------------
	 */

	/**
	 * Abstract methods ----------------------
	 */

	/**
	 * <p>
	 * </p>
	 *
	 * @param position
	 * @param headerView
	 * @return
	 */
	public abstract Object createHeaderViewHolder(int position, View headerView);

	/**
	 * <p>
	 * </p>
	 *
	 * @param position
	 * @param inflater
	 * @param root
	 * @return
	 */
	public abstract View createHeaderView(int position, LayoutInflater inflater, ViewGroup root);

	/**
	 * <p>
	 * </p>
	 *
	 * @param position
	 * @param headerHolder
	 */
	public abstract void bindHeaderView(int position, Object headerHolder);

	/**
	 * Inner classes =========================
	 */

	/**
	 * Interface =============================
	 */
}
