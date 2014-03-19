/*
 * =================================================================================
 * Copyright (C) 2013 - 2014 Martin Albedinsky [Wolf-ITechnologies]
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

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseIntArray;

/**
 * <h4>Class Overview</h4>
 * <p>
 * Selection module for {@link com.wit.android.widget.adapter.MultiAdapter}.
 * </p>
 *
 * @param <Adapter> Type of the adapter for which can be this selection module created and used.
 * @author Martin Albedinsky
 */
public class SelectionModule<Adapter extends AdapterModule.ModuleAdapter> extends AdapterModule<Adapter> {

	/**
	 * Constants =============================
	 */

	/**
	 * <p>
	 * Allows single selection mode.
	 * </p>
	 *
	 * @see #getSelectedPosition()
	 */
	public static final int MODE_SINGLE = 0x00;

	/**
	 * <p>
	 * Allows multiple selection mode.
	 * </p>
	 *
	 * @see #getSelectedPositions()
	 */
	public static final int MODE_MULTIPLE = 0x01;

	/**
	 * Flag indicating whether the debug output trough log-cat is enabled or not.
	 */
	// private static final boolean DEBUG = true;

	/**
	 * Flag indicating whether the output for user trough log-cat is enabled or not.
	 */
	// private static final boolean USER_LOG = true;

	/**
	 * <p>
	 * Bundle key for set of the currently selected items.
	 * </p>
	 */
	protected static final String BUNDLE_SELECTED_ITEMS = "com.wit.android.widget.adapter.module.SelectionModule.Bundle.SelectedItems";

	/**
	 * <p>
	 * Bundle key for the current selection mode.
	 * </p>
	 */
	protected static final String BUNDLE_MODE = "com.wit.android.widget.adapter.module.SelectionModule.Bundle.Mode";

	/**
	 * Bundle identifiers.
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = SelectionModule.class.getSimpleName();

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
	 * Current selection mode of this module.
	 */
	private int mMode = MODE_SINGLE;

	/**
	 * Listeners -----------------------------
	 */

	/**
	 * Arrays --------------------------------
	 */

	/**
	 * Set of the currently selected item's positions.
	 */
	private SparseIntArray aSelectedPositions = new SparseIntArray();

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
	 * Checks whether an item at the specified position is currently selected or not.
	 * </p>
	 *
	 * @param position Position of an item from the current adapter's data set.
	 * @return <code>True</code> if an item at the specified position is selected, <code>false</code>
	 * otherwise.
	 * @see #setSelected(int, boolean)
	 * @see #toggleSelectionState(int)
	 */
	public boolean isSelected(int position) {
		return contains(position);
	}

	/**
	 * <p>
	 * Changes selection state of an item at the specified position to
	 * the opposite one (<code>selected -> unselected; unselected -> selected</code>)
	 * and <b>notifies adapter</b>.
	 * </p>
	 *
	 * @param position Position of an item from the current adapter's data set.
	 * @return The count of currently selected positions.
	 * @see #setSelected(int, boolean)
	 */
	public int toggleSelectionState(int position) {
		setSelected(position, !contains(position));
		return getSelectionCount();
	}

	/**
	 * <p>
	 * Sets the given selection state for an item at the specified position
	 * and <b>notifies adapter</b>.
	 * </p>
	 *
	 * @param position Position of an item from the current adapter's data set.
	 * @param selected New selection state.
	 * @see #selectRange(int, int)
	 * @see #selectAll()
	 * @see #toggleSelectionState(int)
	 * @see #isAdapterNotificationEnabled()
	 */
	public void setSelected(int position, boolean selected) {
		switch (getMode()) {
			case MODE_MULTIPLE:
				break;
			case MODE_SINGLE:
				clearSelection(false);
				break;
		}

		if (selected) {
			select(position);
		} else {
			deselect(position);
		}
		notifyAdapter();
	}

	/**
	 * <p>
	 * Same as {@link #selectRange(int, int)} with parameters
	 * <code>(0, getAdapter().getCount())</code>.
	 * </p>
	 *
	 * @see #selectRange(int, int)
	 * @see #setSelected(int, boolean)
	 */
	public void selectAll() {
		this.checkActualModeFor(MODE_MULTIPLE, "select all items");
		selectRange(0, getAdapter().getCount());
	}

	/**
	 * <p>
	 * Marks an items from the current adapter's data set
	 * in the range <code>&lt;startPosition, startPosition + count&gt;</code>
	 * as <b>selected</b> and <b>notifies adapter</b>.
	 * </p>
	 *
	 * @param startPosition Start position for selection.
	 * @param count         Count of items to select from the start position.
	 * @throws IllegalStateException     If the current mode isn't set to {@link #MODE_MULTIPLE}.
	 * @throws IndexOutOfBoundsException If <code>startPosition + count > n</code>.
	 * @see #selectAll()
	 * @see #setSelected(int, boolean)
	 * @see #isAdapterNotificationEnabled()
	 */
	public void selectRange(int startPosition, int count) {
		this.checkActualModeFor(MODE_MULTIPLE, "select all items");

		// Check correct index.
		final int n = getAdapter().getCount();
		if (startPosition + count > n) {
			throw new IndexOutOfBoundsException("Incorrect count(" + count + ") for start position at(" + startPosition + "). Adapter has only " + n + " items.");
		}

		// Clear all selected items.
		clearSelection(false);

		// Select all items in the range.
		for (int i = startPosition; i < startPosition + count; i++) {
			select(i);
		}
		notifyAdapter();
	}

	/**
	 * <p>
	 * Marks all items from the current adapter's data set to <b>unselected</b>
	 * and <b>notifies adapter</b>.
	 * </p>
	 *
	 * @see #clearSelectionInRange(int, int)
	 * @see #isAdapterNotificationEnabled()
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
	 * @throws IllegalStateException If current mode isn't set to {@link #MODE_MULTIPLE}.
	 * @see #clearSelection()
	 * @see #isAdapterNotificationEnabled()
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
			deselect(i);
		}

		// Selected positions are stored as sorted array of integers (from lowest to highest),
		// so just obtain start and end key for the requested range.
		final int startKeyIndex = aSelectedPositions.indexOfKey(startPosition);
		final int endKeyIndex = aSelectedPositions.indexOfKey(startPosition + count);

		for (int i = startKeyIndex; i < endKeyIndex; i++) {
			deselect(aSelectedPositions.keyAt(i));
		}
		notifyAdapter();
	}

	/**
	 */
	@Override
	public boolean requiresStateSaving() {
		return aSelectedPositions.size() > 0;
	}

	/**
	 * Getters + Setters ---------------------
	 */

	/**
	 * <p>
	 * Returns the sparse array with currently selected positions.
	 * </p>
	 *
	 * @return {@link android.util.SparseIntArray} with select-able positions which are in this time
	 * selected. Note that this array is sorted for optimization (from lowest to highest position).
	 */
	public SparseIntArray getSelection() {
		return aSelectedPositions;
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @return
	 */
	public int getSelectionCount() {
		return aSelectedPositions.size();
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @return
	 * @throws IllegalStateException If current mode isn't set to {@link #MODE_SINGLE}.
	 * @see #getSelectedPositions()
	 */
	public int getSelectedPosition() {
		this.checkActualModeFor(MODE_SINGLE, "obtain selected item position");
		return aSelectedPositions.size() > 0 ? aSelectedPositions.get(aSelectedPositions.keyAt(0)) : -1;
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
	 * @throws IllegalStateException If current mode isn't set to {@link #MODE_MULTIPLE}.
	 * @see #getSelectedPositions()
	 */
	public int[] getSelectedPositions(boolean ascending) {
		this.checkActualModeFor(MODE_MULTIPLE, "obtain selected items position");

		int[] positions = new int[aSelectedPositions.size()];
		for (int i = 0; i < positions.length; i++) {
			positions[i] = aSelectedPositions.keyAt(i);
		}
		return ascending ? positions : reversePositions(positions);
	}

	/**
	 * <p>
	 * Sets the current selection mode of this module to the specified
	 * one.
	 * </p>
	 *
	 * @param mode Selection mode to set.
	 * @see #getMode()
	 */
	public void setMode(int mode) {
		this.mMode = mode;
	}

	/**
	 * <p>
	 * Returns the current selection mode of this module.
	 * </p>
	 *
	 * @return Selection mode, one of the {@link #MODE_MULTIPLE}, {@link #MODE_SINGLE}.
	 * @see #setMode(int)
	 */
	public int getMode() {
		return mMode;
	}

	/**
	 * Protected -----------------------------
	 */

	/**
	 * <p>
	 * Checks whether the set of currently selected item's positions contains
	 * the specified position.
	 * </p>
	 *
	 * @param position Position of an item from the current adapter's data set.
	 * @return <code>True</code> if the currently selected item's positions set contains specified
	 * position, <code>false</code> otherwise..
	 */
	protected final boolean contains(int position) {
		return aSelectedPositions.indexOfKey(position) >= 0;
	}

	/**
	 * <p>
	 * Adds the specified position into the set of currently selected positions.
	 * </p>
	 *
	 * @param position The position of an item from the current adapter's data set.
	 */
	protected final void select(int position) {
		// Add into selected items.
		aSelectedPositions.append(position, position);
	}

	/**
	 * <p>
	 * Removes the specified position form the set of currently selected positions.
	 * </p>
	 *
	 * @param position The position of an item from the current adapter's data set.
	 */
	protected final void deselect(int position) {
		final int index = aSelectedPositions.indexOfKey(position);
		if (index >= 0) {
			// Remove from selected items.
			aSelectedPositions.removeAt(index);
		}
	}

	/**
	 * <p>
	 * Removes all positions form the set of currently selected positions.
	 * </p>
	 *
	 * @param notify If set to <code>true</code>, adapter will be also notified about
	 *               selection data set change.
	 */
	protected final void clearSelection(boolean notify) {
		// Clear also selected items.
		aSelectedPositions.clear();
		if (notify) {
			notifyAdapter();
		}
	}

	/**
	 */
	@Override
	protected Parcelable onSaveInstanceState() {
		final SavedState state = new SavedState(super.onSaveInstanceState());
		// Save mode.
		state.mode = mMode;
		// Change mode to obtain selected positions.
		this.mMode = MODE_MULTIPLE;
		// Save selected item positions.
		state.selectedItems = getSelectedPositions();
		return state;
	}

	/**
	 */
	@Override
	protected void onRestoreInstanceState(Parcelable savedState) {
		if (!(savedState instanceof SavedState)) {
			super.onRestoreInstanceState(savedState);
			return;
		}

		final SavedState state = (SavedState) savedState;
		super.onRestoreInstanceState(state.getParentState());

		// Restore selected item positions.
		int[] selected = state.selectedItems;
		if (selected != null && selected.length > 0) {
			for (int i : selected) {
				select(i);
			}
		}
		// Restore mode.
		this.mMode = state.mode;
		notifyAdapter();
	}

	/**
	 * Private -------------------------------
	 */

	/**
	 * Check whether the current mode complies with requested mode for
	 * the given action. If not the illegal state exception is thrown.
	 *
	 * @param requiredMode ID of required mode.
	 * @param action       Action for which is this check performed.
	 */
	private void checkActualModeFor(int requiredMode, String action) {
		if (mMode != requiredMode) {
			throw new IllegalStateException("Can't " + action + ". Not in required(" + getModeName(requiredMode) + ") mode.");
		}
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
	 * @return Reversed array.
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
	 * <h4>Class Overview</h4>
	 * <p>
	 * </p>
	 *
	 * @author Martin Albedinsky
	 */
	public static class SavedState extends BaseSavedState {

		/**
		 * Members ===============================
		 */

		/**
		 * <p>
		 * </p>
		 */
		@SuppressWarnings("hiding")
		public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {

			/**
			 */
			@Override
			public SavedState createFromParcel(Parcel source) {
				return new SavedState(source);
			}

			/**
			 */
			@Override
			public SavedState[] newArray(int size) {
				return new SavedState[size];
			}
		};

		/**
		 *
		 */
		private int mode = MODE_SINGLE;

		/**
		 *
		 */
		private int[] selectedItems = {};

		/**
		 * Constructors ==========================
		 */

		/**
		 * <p>
		 * </p>
		 */
		public SavedState(Parcelable superState) {
			super(superState);
		}

		/**
		 *
		 * @param source
		 */
		private SavedState(Parcel source) {
			super(source);
			this.mode = source.readInt();
			this.selectedItems = source.createIntArray();
		}

		/**
		 * Methods ===============================
		 */

		/**
		 */
		@Override
		public void writeToParcel(Parcel dest, int flags) {
			super.writeToParcel(dest, flags);
			dest.writeInt(mode);
			dest.writeIntArray(selectedItems);
		}
	}

	/**
	 * Interface =============================
	 */
}
