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
package com.wit.android.widget.adapter;

import android.content.Context;
import android.database.Cursor;

/**
 * <h4>Class Overview</h4>
 * <p>
 * TODO:
 * </p>
 *
 * @param <C> Type of the cursor which will represents data set for this adapter.
 * @author Martin Albedinsky
 */
public abstract class SimpleCursorAdapter<C extends Cursor> extends BaseAdapter {

	/**
	 * Constants =============================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = SimpleCursorAdapter.class.getSimpleName();

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
	 * Cursor with data for this adapter.
	 */
	private C mCursor;

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
	 * Creates new instance of SimpleCursorAdapter with the given
	 * context.
	 * </p>
	 *
	 * @param context Context in which will be this adapter used.
	 */
	public SimpleCursorAdapter(Context context) {
		this(context, null);
	}

	/**
	 * <p>
	 * Creates new instance of SimpleCursorAdapter with the given
	 * context and cursor as data set for this adapter.
	 * </p>
	 *
	 * @param context Context in which will be this adapter used.
	 * @param cursor  Cursor as data set for this adapter.
	 */
	public SimpleCursorAdapter(Context context, C cursor) {
		super(context);
		this.mCursor = cursor;
	}

	/**
	 * Methods ===============================
	 */

	/**
	 * Public --------------------------------
	 */

	/**
	 * <p>
	 * Checks whether this adapter has valid cursor or not.
	 * </p>
	 *
	 * @return <code>True</code> if this adapter's cursor is valid, <code>false</code>
	 * otherwise.
	 */
	public boolean hasCursor() {
		return mCursor != null;
	}

	/**
	 * <p>
	 * Checks whether this adapter has valid cursor and that cursor
	 * has some items or not.
	 * </p>
	 *
	 * @return <code>True</code> if this adapter's cursor is valid and has some items,
	 * <code>false</code> otherwise.
	 * @see #hasCursor()
	 */
	@Override
	public boolean isEmpty() {
		return !hasCursor() || mCursor.getCount() == 0;
	}

	/**
	 * Getters + Setters ---------------------
	 */

	/**
	 * @see #hasCursor()
	 */
	@Override
	public int getCount() {
		return !hasCursor() ? mCursor.getCount() : 0;
	}

	/**
	 * <p>
	 * Same as {@link #getCursorAt(int)}.
	 * </p>
	 */
	@Override
	public Object getItem(int position) {
		return getCursorAt(position);
	}

	/**
	 * <p>
	 * Returns the current cursor moved to the requested position.
	 * </p>
	 *
	 * @param position The position, to which should be cursor moved.
	 * @return The current cursor moved to the requested position or <code>null</code> if
	 * cursor can't be moved to that position because it isn't valid or doesn't have any
	 * items.
	 * @see #hasCursor()
	 */
	public C getCursorAt(int position) {
		return moveCursorTo(position) ? mCursor : null;
	}

	/**
	 * <p>
	 * Returns the current cursor.
	 * </p>
	 *
	 * @return The current cursor of this adapter.
	 */
	public C getCursor() {
		return mCursor;
	}

	/**
	 * <p>
	 * Like {@link #changeCursor(android.database.Cursor)}, but this
	 * will return the current cursor (<b>not closed</b>) of this adapter.
	 * </p>
	 *
	 * @param cursor A new cursor for this adapter.
	 * @return The current cursor of this adapter.
	 * @see #changeCursor(android.database.Cursor)
	 */
	public C swapCursor(C cursor) {
		C current = mCursor;
		// Assign new cursor.
		this.reloadCursor(cursor);
		// Return previous one.
		return current;
	}

	/**
	 * <p>
	 * Changes the current cursor of this adapter by the
	 * given one. The current cursor will be closed before
	 * it changes for the new one.
	 * </p>
	 *
	 * @param cursor A new cursor for this adapter.
	 * @see #swapCursor(android.database.Cursor)
	 */
	public void changeCursor(C cursor) {
		// Close the previous cursor.
		if (mCursor != null) {
			mCursor.close();
			mCursor = null;
		}
		// Assign new cursor.
		this.reloadCursor(cursor);
	}

	/**
	 * Protected -----------------------------
	 */

	/**
	 */
	@Override
	public void onBindItemView(int position, Object viewHolder) {
		if (moveCursorTo(position)) {
			onBindItemView(position, mCursor, viewHolder);
		}
	}

	/**
	 * <p>
	 * Reloads the current cursor of this adapter as data set.
	 * </p>
	 *
	 * @param cursor A new cursor for this adapter..
	 * @return <code>True</code> if cursor was successfully reloaded,
	 * <code>false</code> otherwise. <b>For now this will always return <code>true</code></b>.
	 */
	protected final boolean reloadCursor(C cursor) {
		this.mCursor = cursor;
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		notifyDataSetChanged();
		return true;
	}

	/**
	 * <p>
	 * Moves the current cursor of this adapter to the requested position.
	 * </p>
	 *
	 * @param position Position to move cursor to.
	 * @return <code>True</code> if cursor was moved to requested position,
	 * <code>false</code> otherwise.
	 * @see #hasCursor()
	 */
	protected boolean moveCursorTo(int position) {
		return (hasCursor() && mCursor.getCount() > position && mCursor.moveToPosition(position));
	}

	/**
	 * Private -------------------------------
	 */

	/**
	 * Abstract methods ----------------------
	 */

	/**
	 * <p>
	 * Invoked to bind a view for the specified position of this adapter's cursor.
	 * This is invoked always as {@link #onBindItemView(int, Object)} on this adapter
	 * is called and the current cursor was successfully moved to the specified position
	 * by {@link #moveCursorTo(int)}.
	 * </p>
	 *
	 * @param position   The position of the item from this adapter's cursor.
	 * @param cursor     The current cursor of this adapter moved to the specified position.
	 * @param viewHolder Same type of holder as provided by
	 *                   {@link #onCreateItemViewHolder(int, android.view.View)} for the specified
	 *                   position.
	 */
	protected abstract void onBindItemView(int position, C cursor, Object viewHolder);

	/**
	 * Inner classes =========================
	 */

	/**
	 * Interface =============================
	 */
}
