/*
 * =================================================================================
 * Copyright (C) 2014 Martin Albedinsky [Wolf-ITechnologies]
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
package com.wit.android.widget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wit.android.widget.adapter.annotation.AdapterItemHolder;
import com.wit.android.widget.adapter.annotation.AdapterItemView;

/**
 * <h4>Class Overview</h4>
 * <p>
 * Description.
 * </p>
 *
 * @author Martin Albedinsky
 */
public abstract class SimpleMultiAdapter extends BaseMultiAdapter<SimpleMultiAdapter> {

	/**
	 * Constants =============================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = SimpleAdapter.class.getSimpleName();

	/**
	 * Flag indicating whether the debug output trough log-cat is enabled or not.
	 */
	// private static final boolean DEBUG = true;

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
	 *
	 */
	private int mItemView = 0;

	/**
	 *
	 */
	private Class<? extends AdapterItemHolder.ViewHolder> mClassOfHolder = null;

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
	 * <p>
	 * </p>
	 *
	 * @param context
	 */
	public SimpleMultiAdapter(Context context) {
		super(context);
		final Class<? extends SimpleMultiAdapter> classOfAdapter = getClass();
		/**
		 * Process class annotations.
		 */
		// Retrieve item view.
		if (classOfAdapter.isAnnotationPresent(AdapterItemView.class)) {
			this.mItemView = classOfAdapter.getAnnotation(AdapterItemView.class).value();
		}
		// Retrieve item view holder.
		if (classOfAdapter.isAnnotationPresent(AdapterItemHolder.class)) {
			this.mClassOfHolder = classOfAdapter.getAnnotation(AdapterItemHolder.class).value();
		}
	}

	/**
	 * Methods ===============================
	 */

	/**
	 * Public --------------------------------
	 */

	/**
	 */
	@Override
	public View onCreateView(int position, LayoutInflater inflater, ViewGroup parent) {
		return (mItemView > 0) ? inflate(mItemView) : null;
	}

	/**
	 */
	@Override
	public Object onCreateViewHolder(int position, View itemView) {
		if (mClassOfHolder != null) {
			AdapterItemHolder.ViewHolder holder = null;
			try {
				holder = mClassOfHolder.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} finally {
				if (holder != null) {
					holder.onCreate(itemView);
				}
			}
			return holder;
		}
		return null;
	}

	/**
	 * Getters + Setters ---------------------
	 */

	/**
	 * <p>
	 * </p>
	 *
	 * @param resource
	 */
	public void setItemView(int resource) {
		this.mItemView = resource;
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @return
	 */
	public int getItemView() {
		return mItemView;
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param classOfHolder
	 */
	public void setClassOfItemViewHolder(Class<? extends AdapterItemHolder.ViewHolder> classOfHolder) {
		this.mClassOfHolder = classOfHolder;
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @return
	 */
	public Class<? extends AdapterItemHolder.ViewHolder> getClassOfOfItemViewHolder() {
		return mClassOfHolder;
	}

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
