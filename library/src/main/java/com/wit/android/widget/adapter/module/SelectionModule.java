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

import android.os.Bundle;
import android.util.SparseIntArray;

/**
 * <h4>Class Overview</h4>
 * <p>
 * Selection manager for the {@link com.wit.android.widget.adapter.internal.IMultiAdapter}.
 * </p>
 *
 * @param <Adapter> Type of the adapter for which is this module created.
 * @author Martin Albedinsky
 */
public class SelectionModule<Adapter extends AdapterModule.ModuleAdapter> extends AdapterModule<Adapter> {

	/**
	 * Constants =============================
	 */

	/**
	 * <p>
	 * </p>
	 */
	public static final int MODE_SINGLE = 0x00;

	/**
	 * <p>
	 * </p>
	 */
	public static final int MODE_MULTIPLE = 0x01;

	/**
	 * Indicates if debug private output trough log-cat is enabled.
	 */
	// private static final boolean DEBUG = true;

	/**
	 * Indicates if logging for user output trough log-cat is enabled.
	 */
	// private static final boolean USER_LOG = true;

	/**
	 * <p>
	 * </p>
	 */
	protected static final String BUNDLE_SELECTED_ITEMS = "com.wit.android.widget.adapter.module.SelectionModule.Bundle.SelectedItems";

	/**
	 * <p>
	 * </p>
	 */
	protected static final String BUNDLE_MODE =  "com.wit.android.widget.adapter.module.SelectionModule.Bundle.Mode";

	/**
	 * Bundle identifiers.
	 */

	/**
	 * Log TAG.
	 */
	private static final String TAG = SelectionModule.class.getSimpleName();

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
	private int mMode = MODE_SINGLE;

	/**
	 * Listeners -----------------------------
	 */

	/**
	 * Arrays --------------------------------
	 */

	/**
	 *
	 */
	private SparseIntArray aSelectedItems = new SparseIntArray();

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
	 * Checks if the item at the given position is currently selected.
	 * </p>
	 *
	 * @param position Position of the item in the adapter.
	 * @return <code>True</code> if the item is selected, <code>false</code>
	 * otherwise.
	 * @see #setItemSelected(int, boolean)
	 * @see #toggleItemSelectionState(int)
	 */
	public boolean isSelected(int position) {
		return contains(position);
	}

	/**
	 * <p>
	 * Resolves the select-able item selected state. If the item is selected the
	 * flag will be set to <code>false</code> otherwise to <code>true</code>.
	 * </p>
	 * <p>
	 * Also adapter will be notified about data set change.
	 * </p>
	 *
	 * @param position Position of the select-able item in the adapter.
	 * @see #setItemSelected(int, boolean)
	 */
	public void toggleItemSelectionState(int position) {
		setItemSelected(position, !contains(position));
	}

	/**
	 * <p>
	 * Sets the given selected state of the select-able item
	 * of attached adapter at the given position.
	 * </p>
	 * <p>
	 * Also adapter will be notified about data set change.
	 * </p>
	 *
	 * @param position Position of the select-able item in the adapter.
	 * @param selected New selected state.
	 * @see #selectRange(int, int)
	 * @see #selectAll()
	 */
	public void setItemSelected(int position, boolean selected) {
		switch (getMode()) {
			case MODE_MULTIPLE:
				break;
			case MODE_SINGLE:
				clearSelection(false);
				break;
		}

		if (selected) {
			selectItem(position);
		} else {
			deselectItem(position);
		}
		notifyAdapter();
	}

	/**
	 * <p>
	 * Same as {@link #selectRange(int, int)}, where full range of items
	 * of attached adapter will be selected as
	 * <code>selectRange(0, itemsCount)</code>.
	 * </p>
	 * <p>
	 * Also adapter will be notified about data set change.
	 * </p>
	 *
	 * @throws java.lang.IllegalStateException If current mode isn't set to {@link #MODE_MULTIPLE}.
	 * @see #selectRange(int, int)
	 * @see #setItemSelected(int, boolean)
	 */
	public void selectAll() {
		this.checkActualModeFor(MODE_MULTIPLE, "select all items");
		selectRange(0, getAdapter().getCount());
	}

	/**
	 * <p>
	 * Sets the selected state to <code>true</code> for the select-able items
	 * of attached adapter at the given positions
	 * <code>(startPosition, startPosition + count)</code>.
	 * </p>
	 * <p>
	 * Also adapter will be notified about data set change.
	 * </p>
	 *
	 * @param startPosition Position for selection start.
	 * @param count         Count of the items to select from the start position.
	 * @throws java.lang.IllegalStateException If current mode isn't set to {@link #MODE_MULTIPLE}.
	 * @see #selectAll()
	 * @see #setItemSelected(int, boolean)
	 */
	public void selectRange(int startPosition, int count) {
		this.checkActualModeFor(MODE_MULTIPLE, "select all items");

		// Check correct index.
		final int n = getAdapter().getCount();
		if (startPosition + count > n) {
			throw new IndexOutOfBoundsException("Incorrect count(" + count + ") for start offset at(" + startPosition + "). Adapter has only " + n + " items.");
		}

		// Clear all selected items.
		clearSelection(false);

		// Select all items in the range.
		for (int i = startPosition; i < startPosition + count; i++) {
			selectItem(i);
		}
		notifyAdapter();
	}

	/**
	 * <p>
	 * Sets the selected state to <code>false</code> for all select-able items.
	 * </p>
	 * <p>
	 * Also adapter will be notified about data set change.
	 * </p>
	 */
	public void clearSelection() {
		clearSelection(true);
	}

	/**
	 * <p>
	 * Sets the selected state to <code>true</code> for the select-able items
	 * of attached adapter at the given positions
	 * <code>(startPosition, startPosition + count)</code>.
	 * </p>
	 * <p>
	 * Also adapter will be notified about data set change.
	 * </p>
	 *
	 * @param startPosition Position for selection start.
	 * @param count         Count of the items to select from the start position.
	 * @throws java.lang.IllegalStateException If current mode isn't set to {@link #MODE_MULTIPLE}.
	 * @see #selectAll()
	 * @see #setItemSelected(int, boolean)
	 */
	public void clearSelectionInRange(int startPosition, int count) {
		this.checkActualModeFor(MODE_MULTIPLE, "clear all selected items");

		// Check correct index.
		final int n = getAdapter().getCount();
		if (startPosition + count > n) {
			throw new IndexOutOfBoundsException("Incorrect count(" + count + ") for start offset at(" + startPosition + "). Adapter has only " + n + " items.");
		}

		// Deselect all items in the range.
		for (int i = startPosition; i < startPosition + count; i++) {
			deselectItem(i);
		}

		// Selected positions are stored as sorted array of integers (from lowest to highest),
		// so just obtain start and end key for the requested range.
		final int startKeyIndex = aSelectedItems.indexOfKey(startPosition);
		final int endKeyIndex = aSelectedItems.indexOfKey(startPosition + count);

		for (int i = startKeyIndex; i < endKeyIndex; i++) {
			deselectItem(aSelectedItems.keyAt(i));
		}
		notifyAdapter();
	}

	/**
	 * Getters + Setters ---------------------
	 */

	/**
	 * <p>
	 * Returns the sparse array with currently selected items positions.
	 * </p>
	 *
	 * @return {@link SparseIntArray} with select-able items positions which are in this time
	 * selected. Note that this array is sorted for optimization (from
	 * lowest to highest position).
	 */
	public SparseIntArray getSelectedItems() {
		return aSelectedItems;
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @return
	 */
	public int getSelectedItemsCount() {
		return aSelectedItems.size();
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @return
	 * @throws java.lang.IllegalStateException If current mode isn't set to {@link #MODE_SINGLE}.
	 * @see #getSelectedPositions()
	 */
	public int getSelectedPosition() {
		this.checkActualModeFor(MODE_SINGLE, "obtain selected item position");
		return aSelectedItems.size() > 0 ? aSelectedItems.get(aSelectedItems.keyAt(0)) : -1;
	}

	/**
	 * <p>
	 * Same as {@link #getSelectedPositions(boolean)} with <code>true</code>, so items
	 * in array will be sorted ascending.
	 * </p>
	 */
	public int[] getSelectedPositions() {
		return getSelectedPositions(true);
	}

	/**
	 * <p>
	 * Returns array with positions of the currently selected items.
	 * </p>
	 * <p>
	 * <b>Note</b>, that array is obtained/processed from the {@link android.util.SparseIntArray}, so
	 * its items will be sorted ascending.
	 * </p>
	 *
	 * @param ascending <code>True</code> to sort array items ascending, <code>false</code> to descending.
	 * @return Array with positions of the currently selected items.
	 * @throws java.lang.IllegalStateException If current mode isn't set to {@link #MODE_MULTIPLE}.
	 * @see #getSelectedPositions()
	 */
	public int[] getSelectedPositions(boolean ascending) {
		this.checkActualModeFor(MODE_MULTIPLE, "obtain selected items position");

		int[] positions = new int[aSelectedItems.size()];
		for (int i = 0; i < positions.length; i++) {
			positions[i] = aSelectedItems.keyAt(i);
		}
		return ascending ? positions : reversePositions(positions);
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param mode
	 */
	public void setMode(int mode) {
		this.mMode = mode;
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @return
	 */
	public int getMode() {
		return mMode;
	}

	/**
	 * Protected -----------------------------
	 */

	/**
	 * <p>
	 * Returns flag indicating if the selected items array contains at the
	 * requested position some selected item.
	 * </p>
	 *
	 * @param position The position of the item.
	 * @return <code>True</code> if the array contains that item,
	 * <code>false</code> otherwise.
	 */
	protected final boolean contains(int position) {
		return aSelectedItems.indexOfKey(position) >= 0;
	}

	/**
	 * <p>
	 * Adds the item with given position into array of selected items.
	 * </p>
	 *
	 * @param position The position of the item to select.
	 */
	protected final void selectItem(int position) {
		// Add into selected items.
		aSelectedItems.append(position, position);
	}

	/**
	 * <p>
	 * Removes the item at the requested position from selected items array.
	 * </p>
	 *
	 * @param position The position of the item to deselect.
	 */
	protected final void deselectItem(int position) {
		// Remove from selected items.
		aSelectedItems.removeAt(aSelectedItems.indexOfKey(position));
	}

	/**
	 * <p>
	 * Sets the selected state to <code>false</code> for all select-able items
	 * of attached adapter.
	 * </p>
	 *
	 * @param notify If set to <code>true</code> adapter will be also notified about
	 *               data set change.
	 */
	protected void clearSelection(boolean notify) {
		// Clear also selected items.
		aSelectedItems.clear();
		if (notify) {
			notifyAdapter();
		}
	}

	/**
	 *
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		// Save mode.
		outState.putInt(BUNDLE_MODE, mMode);

		// Change mode to obtain selected positions.
		mMode = MODE_MULTIPLE;
		// Save selected item positions.
		outState.putIntArray(BUNDLE_SELECTED_ITEMS, getSelectedPositions());
	}

	/**
	 *
	 */
	@Override
	protected void onRestoreInstanceState(Bundle savedState) {
		super.onRestoreInstanceState(savedState);

		// Restore selected item positions.
		int[] selected = savedState.getIntArray(BUNDLE_SELECTED_ITEMS);
		if (selected != null && selected.length > 0) {
			for (int i : selected) {
				selectItem(i);
			}
		}
		// Restore mode.
		this.mMode = savedState.getInt(BUNDLE_MODE);
		notifyAdapter();
	}

	/**
	 * Private -------------------------------
	 */

	/**
	 * Check if the current mode complies with requested mode for
	 * the given action. If not the exception is thrown.
	 *
	 * @param requiredMode ID of required mode.
	 * @param action       Action for which is this check performed.
	 */
	private void checkActualModeFor(int requiredMode, String action) {
		if (mMode != requiredMode)
			throw new IllegalStateException("Can't " + action + ". Not in required(" + getModeName(requiredMode) + ") mode.");
	}

	/**
	 * Returns name for the given mode id.
	 *
	 * @param mode Mode identifier.
	 * @return Name of requested mode.
	 */
	private String getModeName(int mode) {
		switch (mode) {
			case MODE_MULTIPLE:
				return "MODE_MULTIPLE";
			case MODE_SINGLE:
				return "MODE_SINGLE";
		}
		return "";
	}

	/**
	 * Reverses the given array of positions.
	 *
	 * @param positions Array to reverse.
	 * @return Reversed array of given positions.
	 */
	private int[] reversePositions(int[] positions) {
		final int[] reverse = new int[positions.length];
		for (int i = 0; i < reverse.length; i++) {
			reverse[i] = positions[positions.length - (i + 1)];
		}
		return reverse;
	}

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
