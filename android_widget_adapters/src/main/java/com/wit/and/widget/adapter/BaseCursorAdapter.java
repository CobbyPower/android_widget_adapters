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
package com.wit.and.widget.adapter;

import android.content.Context;
import android.database.Cursor;

/**
 * <h4>Class Overview</h4>
 * <p>
 * </p>
 *
 * @author Martin Albedinsky
 *
 * @param <C> Type of the cursor from which will this adapter bind the views.
 */
public abstract class BaseCursorAdapter<C extends Cursor> extends BaseAdapter {

    /**
     * Constants =============================
     */

    /**
     * Log TAG.
     */
    private static final String TAG = BaseCursorAdapter.class.getSimpleName();

    /**
     * Indicates if debug private output trough log-cat is enabled.
     */
    // private static final boolean DEBUG = true;

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
     * </p>
     *
     * @param context
     * @see BaseAdapter#BaseAdapter(Context)
     */
    public BaseCursorAdapter(Context context) {
        this(context, null);
    }

    /**
     * <p>
     * </p>
     *
     * @param context
     * @param cursor
     * @see BaseAdapter#BaseAdapter(Context)
     */
    public BaseCursorAdapter(Context context, C cursor) {
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
     * </p>
     *
     * @return
     */
    public boolean hasCursor() {
        return mCursor != null;
    }

    /**
     * <p>
     * </p>
     *
     * @return
     */
    public boolean hasData() {
        return mCursor != null && mCursor.getCount() > 0;
    }

    /**
     * Getters + Setters ---------------------
     */

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
     * </p>
     *
     * @param position
     * @return
     */
    public C getCursorAt(int position) {
        return moveCursorTo(position) ? mCursor : null;
    }

    /**
     * <p>
     * Returns current cursor hold by this adapter.
     * </p>
     *
     * @return Current cursor.
     */
    public C getCursor() {
        return mCursor;
    }

    /**
     * <p>
     * </p>
     *
     * @param cursor
     * @return
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
     * </p>
     *
     * @param cursor
     */
    public void changeCursor(C cursor) {
        // Close the previous cursor.
        if (mCursor != null) {
            mCursor.close();
            mCursor = null;
        }

        if (cursor != null) {
            // Assign new cursor.
            this.reloadCursor(cursor);
        }
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
     * Reloads the current cursor with the given one.
     * </p>
     *
     * @param cursor Must be valid cursor.
     * @return <code>True</code> if cursor was successfully reloaded, <code>false</code> otherwise.
     */
    protected final boolean reloadCursor(C cursor) {
        this.mCursor = cursor;
        if (mCursor.moveToFirst()) {
            notifyDataSetChanged();
            return true;
        }
        return false;
    }

    /**
     * Private -------------------------------
     */

    /**
     *
     * @param position
     * @return
     */
    private boolean moveCursorTo(int position) {
        return (mCursor != null && mCursor.getCount() > position && mCursor.moveToPosition(position));
    }

    /**
     * Abstract methods ----------------------
     */

    /**
     * <p>
     * </p>
     *
     * @param position
     * @param cursor
     * @param viewHolder
     */
    protected abstract void onBindItemView(int position, C cursor, Object viewHolder);

    /**
     * Inner classes =========================
     */

    /**
     * Interface =============================
     */
}
