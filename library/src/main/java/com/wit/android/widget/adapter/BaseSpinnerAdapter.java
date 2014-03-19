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

import com.wit.android.widget.adapter.annotation.DropDownHolder;
import com.wit.android.widget.adapter.annotation.DropDownView;
import com.wit.android.widget.adapter.annotation.ItemHolder;

/**
 * <h4>Class Overview</h4>
 * <p>
 * Description.
 * </p>
 *
 * @author Martin Albedinsky
 * @see com.wit.android.widget.adapter.annotation.ItemHolder
 * @see com.wit.android.widget.adapter.annotation.ItemView
 * @see com.wit.android.widget.adapter.annotation.DropDownView
 * @see com.wit.android.widget.adapter.annotation.DropDownHolder
 */
public abstract class BaseSpinnerAdapter<Item> extends BaseAdapter {

	/**
	 * Constants =============================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = BaseSpinnerAdapter.class.getSimpleName();

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
	private Item mSelectedItem = null;

	/**
	 *
	 */
	private int mDropDownView = -1;

	/**
	 *
	 */
	private Class<? extends ItemHolder.ViewHolder> mClassOfDropDownHolder = null;

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
	 */
	public BaseSpinnerAdapter() {
		this(null);
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param context
	 */
	public BaseSpinnerAdapter(Context context) {
		super(context);
		final Class<?> classOfAdapter = ((Object) this).getClass();
		/**
		 * Process class annotations.
		 */
		// Retrieve item view.
		if (classOfAdapter.isAnnotationPresent(DropDownView.class)) {
			this.mDropDownView = classOfAdapter.getAnnotation(DropDownView.class).value();
		}
		// Retrieve item view holder.
		if (classOfAdapter.isAnnotationPresent(DropDownHolder.class)) {
			this.mClassOfDropDownHolder = classOfAdapter.getAnnotation(DropDownHolder.class).value();
		}
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
	 * @param position
	 */
	public void dispatchItemSelected(int position) {
		this.mSelectedItem = getItem(position);
		notifyDataSetChanged();
	}

	/**
	 */
	@Override
	public void onBindView(int position, Object viewHolder) {
		onUpdateView(getSelectedItem(), viewHolder);
	}

	/**
	 */
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		Object viewHolder;
		if (convertView == null) {
			// Dispatch to create new drop down view.
			convertView = onCreateDropDownView(position, getLayoutInflater(), parent);
			if (convertView == null) {
				throw new NullPointerException("Convert drop down view at position(" + position + ") can't be NULL.");
			}
			// Set holder to the new drop down view.
			convertView.setTag(viewHolder = onCreateDropDownViewHolder(position, convertView));
		} else {
			viewHolder = convertView.getTag();
		}
		// Dispatch to bind drop down view with data.
		onBindDropDownView(position, viewHolder);
		return convertView;
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
	public Item getSelectedItem() {
		return mSelectedItem;
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param resource
	 */
	public void setDropDownViewResource(int resource) {
		this.mDropDownView = resource;
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @return
	 */
	public int getDropDownViewResource() {
		return mDropDownView;
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param classOfHolder
	 */
	public void setClassOfDropDownViewHolder(Class<? extends ItemHolder.ViewHolder> classOfHolder) {
		this.mClassOfDropDownHolder = classOfHolder;
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @return
	 */
	public Class<? extends ItemHolder.ViewHolder> getClassOfOfDropDownViewHolder() {
		return mClassOfDropDownHolder;
	}


	/**
	 * Protected -----------------------------
	 */

	/**
	 * <p>
	 * </p>
	 *
	 * @param position
	 * @param inflater
	 * @param parent
	 * @return
	 */
	protected View onCreateDropDownView(int position, LayoutInflater inflater, ViewGroup parent) {
		if (mDropDownView >= 0) {
			return inflate(mDropDownView);
		}
		return onCreateView(position, inflater, parent);
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param position
	 * @param itemView
	 * @return
	 */
	protected Object onCreateDropDownViewHolder(int position, View itemView) {
		if (mClassOfDropDownHolder != null) {
			ItemHolder.ViewHolder holder = null;
			try {
				holder = mClassOfDropDownHolder.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new IllegalStateException("Failed to create holder from class '" + mClassOfDropDownHolder + "'. Check if this holder class is public and has public empty constructor.");
			} finally {
				if (holder != null) {
					holder.onCreate(itemView);
				}
			}
			return holder;
		}
		return onCreateViewHolder(position, itemView);
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param position
	 * @param viewHolder
	 */
	protected void onBindDropDownView(int position, Object viewHolder) {
		onBindView(position, viewHolder);
	}

	/**
	 * Private -------------------------------
	 */

	/**
	 * Abstract methods ----------------------
	 */

	/**
	 */
	@Override
	public abstract Item getItem(int position);

	/**
	 * <p>
	 * </p>
	 *
	 * @param selectedItem
	 * @param viewHolder
	 */
	protected abstract void onUpdateView(Item selectedItem, Object viewHolder);

	/**
	 * Inner classes =========================
	 */

	/**
	 * Interface =============================
	 */
}
