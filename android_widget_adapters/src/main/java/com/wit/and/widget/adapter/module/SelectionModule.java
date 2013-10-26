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

import android.os.Bundle;
import android.util.SparseIntArray;

import com.wit.and.widget.adapter.model.SelectableItem;

/**
 * <h4>Class Overview</h4>
 * <p>
 * Selection manager for the {@link com.wit.and.widget.adapter.internal.IMultiAdapter}.
 * </p>
 *
 * @param <Item>    The select-able item type.
 * @param <Adapter> Type of the adapter for which is this module created.
 *
 * @author Martin Albedinsky
 */
public class SelectionModule<Item extends SelectableItem, Adapter extends SelectionModule.SelectableItemsAdapter<Item>> extends AdapterModule<Adapter> {

	/**
	 * Constants =============================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = SelectionModule.class.getSimpleName();

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
	public static final int MODE_SINGLE = 0x00;

	/**
	 * <p>
	 * </p>
	 */
	public static final int MODE_MULTIPLE = 0x01;

	/**
	 * Bundle identifiers.
	 */

	/**
	 * <p>
	 * </p>
	 */
	protected static final String BUNDLE_SELECTED_ITEMS = "com.wit.and.widget.adapter.module.SelectionModule.Bundle.SelectedItems";

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
	 * @see #toggleItemSelectedState(int)
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
	public void toggleItemSelectedState(int position) {
		setItemSelected(position, !contains(position));
		notifyAdapter();
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
	 *
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putIntArray(BUNDLE_SELECTED_ITEMS, getSelectedPositions());
	}

	/**
	 *
	 */
	@Override
	public void onRestoreInstanceState(Bundle savedState) {
		super.onRestoreInstanceState(savedState);
		int[] selected = savedState.getIntArray(BUNDLE_SELECTED_ITEMS);
		if (selected != null && selected.length > 0) {
			for (int i : selected) {
				selectItem(i);
			}
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
	 * Returns the selected item at the given position.
	 * </p>
	 *
	 * @return Item which is at this time selected or <code>null</code> if there
	 * is no item selected at the given position.
	 */
	public Item getSelectedItem(int position) {
		return getItem(position);
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
	 * Returns the array with positions of currently selected items.
	 * </p>
	 *
	 * @return Array with positions of the currently selected items. Note that
	 * this isn't necessary sorted array.
	 * @throws java.lang.IllegalStateException If current mode isn't set to {@link #MODE_MULTIPLE}.
	 */
	public int[] getSelectedPositions() {
		this.checkActualModeFor(MODE_MULTIPLE, "obtain selected items position");

		int[] pos = new int[aSelectedItems.size()];
		for (int i = 0; i < pos.length; i++) {
			pos[i] = aSelectedItems.keyAt(i);
		}
		return pos;
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
		return aSelectedItems.get(position) != 0;
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

		// Select also adapter item if is available.
		if (hasAdapterSelectableItems()) {
			Item item = getItem(position);
			if (item != null) {
				// Select item.
				item.setSelected(true);
			}
		}
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
		aSelectedItems.removeAt(position);

		// Deselect also adapter item if is available.
		if (hasAdapterSelectableItems()) {
			Item item = getItem(position);
			if (item != null && item.isSelected()) {
				// Un-select item.
				item.setSelected(false);
			}
		}
	}

	/**
	 * <p>
	 * Returns the select-able item at the requested position. Same as
	 * {@link #getSelectedItem(int)}.
	 * </p>
	 *
	 * @param position Position of the item.
	 * @return Select-able item or <code>null</code> if there is no item at the
	 * given position.
	 */
	@SuppressWarnings("unchecked")
	protected final Item getItem(int position) {
		return getAdapter().getSelectableItem(position);
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
		// Deselect adapter items if are available.
		if (hasAdapterSelectableItems()) {
			for (int i = 0; i < aSelectedItems.size(); i++) {
				Item item = getItem(aSelectedItems.keyAt(i));
				if (item != null) {
					// Un-select item.
					item.setSelected(false);
				}
			}
		}

		// Clear also selected items.
		aSelectedItems.clear();
		if (notify) {
			notifyAdapter();
		}
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
			throw new IllegalStateException("Can't " + action + ". Not in required mode(" + getModeName(requiredMode) + ").");
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
	 * Returns flag indicating, whether the attached adapter has selectable items
	 * presented or not.
	 *
	 * @return <code>True</code> if adapter has selectable items, <code>false</code> otherwise.
	 */
	private boolean hasAdapterSelectableItems() {
		return getAdapter().hasSelectableItems();
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

	/**
	 * <h4>Interface Overview</h4>
	 * <p>
	 * Base required interface for adapter with selectable items which is using the
	 * {@link com.wit.and.widget.adapter.module.SelectionModule} to provide management
	 * with its selectable items.
	 * </p>
	 * <p>
	 * Note that the {@link #getSelectableItem(int)} method doesn't require to be implemented
	 * to be selection module functional, because with or without this implementation will
	 * be items selected according to theirs position in the adapter. So if you have for
	 * example implementation of {@link com.wit.and.widget.adapter.BaseCursorAdapter}, than you
	 * have all your items provided by that adapter stored in <code>Cursor</code> which can't
	 * extends {@link com.wit.and.widget.adapter.model.SelectableItem} so you just return
	 * in the {@link #hasSelectableItems()} method <code>false</code> and you can check for
	 * selected item by calling {@link com.wit.and.widget.adapter.module.SelectionModule#isSelected(int)}
	 * method on your selection module of that adapter.
	 * </p>
	 *
	 * @param <Item> The select-able item type.
	 * @author Martin Albedinsky
	 * @see com.wit.and.widget.adapter.model.SelectableItem
	 * @see com.wit.and.widget.adapter.module.SelectionModule
	 */
	public static interface SelectableItemsAdapter<Item extends SelectableItem> extends ModuleAdapter {
		/**
		 * Methods ===============================
		 */

		/**
		 * <p>
		 * Returns selectable item for the requested position.
		 * </p>
		 *
		 * @param position Position of the select-able item in the adapter.
		 * @return Selectable item from adapter, or <code>null</code> if the given position
		 * is out of items range or adapter doesn't have any selectable items presented.
		 * @see #hasSelectableItems()
		 */
		public Item getSelectableItem(int position);

		/**
		 * <p>
		 * Returns flag indicating, whether adapter has selectable items presented
		 * (accessible by {@link #getSelectableItem(int)}) or not.
		 * </p>
		 *
		 * @return <code>True</code> if adapter has selectable items presented, <code>false</code> otherwise.
		 * @see #getSelectableItem(int)
		 */
		public boolean hasSelectableItems();
	}

}
