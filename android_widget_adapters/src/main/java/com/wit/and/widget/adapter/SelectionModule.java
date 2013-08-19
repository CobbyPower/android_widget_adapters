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

import android.os.Bundle;
import android.util.SparseIntArray;

/**
 * <p>
 * public abstract class
 * </p>
 * <h5>SelectionModule</h5>
 * <p>
 * extends {@link AdapterModule}
 * </p>
 * <h4>Class Overview</h4>
 * <p>
 * Selection manager for the {@link IMultiAdapter}.
 * </p>
 *
 * @param <Item>
 *            The select-able item type.
 * @param <Adapter>
 *            Type of the adapter for which is this module created.
 *
 * @author Martin Albedinsky
 *
 */
public abstract class SelectionModule<Item extends SelectableItem, Adapter extends BaseAdapter> extends AdapterModule<Adapter> {

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
     * <br/>
     * <h5><i</i></h5>
     * <p>
     * </p>
     */
    public static final int MODE_SINGLE = 0x00;

    /**
     * <br/>
     * <h5><i</i></h5>
     * <p>
     * </p>
     */
    public static final int MODE_MULTIPLE = 0x01;

	/**
	 * Bundle identifiers.
	 */

	/**
	 */
	protected static final String BUNDLE_SELECTED_ITEMS = "com.wit.and.widget.SelectionModule.Bundle.SelectedItems";

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
	 * <br/>
	 * <h5><i>public boolean isSelected(int position)</i></h5>
	 * <p>
	 * Checks if the item at the given position is currently selected.
	 * </p>
	 *
	 * @param position
	 *            Position of the item in the adapter.
	 * @return <code>True</code> if the item is selected, <code>false</code>
	 *         otherwise.
	 */
	public boolean isSelected(int position) {
		return contains(position);
	}

	/**
	 * Getters + Setters ---------------------
	 */

	/**
	 * <br/>
	 * <h5><i>public void toggleItemSelectedState(int position)</i></h5>
	 * <p>
	 * Resolves the select-able item selected state. If the item is selected the
	 * flag will be set to <code>false</code> otherwise to <code>true</code>.
	 * </p>
	 * <p>
	 * <i>Note that this also notify the adapter that the data set was
	 * changed.</i>
	 * </p>
	 *
	 * @param position
	 *            Position of the select-able item in the adapter.
	 */
	public void toggleItemSelectedState(int position) {
		setItemSelected(position, !contains(position));
		notifyAdapter();
	}

	/**
	 * <br/>
	 * <h5><i>public SparseIntArray getSelectedItems()</i></h5>
	 * <p>
	 * Returns the sparse array with currently selected items positions.
	 * </p>
	 *
	 * @return {@link SparseIntArray} with select-able items positions which are in this time
	 *         selected. Note that this array is sorted for optimization (from
	 *         lowest to highest position).
	 */
	public SparseIntArray getSelectedItems() {
		return aSelectedItems;
	}

	/**
	 * <br/>
	 * <h5><i>public &lt;I&gt; getSelectedOption(int position)</i></h5>
	 * <p>
	 * Returns the selected item at the given position.
	 * </p>
	 *
	 * @return Item which is in this time selected or <code>null</code> if there
	 *         is no item selected at the given position.
	 */
	public Item getSelectedItem(int position) {
		return get(position);
	}

    /**
     * <br/>
     * <h5><i>public int getSelectedPosition()</i></h5>
     * <p>
     * </p>
     * <p>
     * <i>Note, that this supported only in {@link #MODE_SINGLE} mode.</i>
     * </p>
     *
     * @return
     * @throws IllegalStateException
     * @see #getSelectedPositions()
     */
    public int getSelectedPosition() {
        if (mMode != MODE_SINGLE)
            throw new IllegalStateException("Can't obtain selected item position. Not in MODE_SINGLE.");

        return aSelectedItems.size() > 0 ? aSelectedItems.get(aSelectedItems.keyAt(0)) : -1;
    }

	/**
	 * <br/>
	 * <h5><i>public int[] getSelectedPositions()</i></h5>
	 * <p>
	 * Returns the array with positions of currently selected items.
	 * </p>
	 *
	 * @return Array with positions of the currently selected items. Note that
	 *         this isn't necessary sorted array.
	 */
	public int[] getSelectedPositions() {
		int[] pos = new int[aSelectedItems.size()];

		for (int i = 0; i < pos.length; i++) {
			pos[i] = aSelectedItems.keyAt(i);
		}

		return pos;
	}

	/**
     * <br/>
     * <h5><i>public void clearSelection()</i></h5>
     * <p>
     * Sets the selected state to <code>false</code> for all select-able items.
     * </p>
     * <p>
     * <i>Note that this also notify the adapter that the data set was
     * changed.</i>
     * </p>
     */
	public void clearSelection() {
		clearSelection(true);
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
                add(i);
            }
        }
        notifyAdapter();
	}

    /**
     * <br/>
     * <h5><i></i></h5>
     * <p>
     * </p>
     *
     * @param mode
     */
    public void setMode(int mode) {
        this.mMode = mode;
    }

    /**
     * <br/>
     * <h5><i></i></h5>
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
	 * <br/>
	 * <h5><i>protected final boolean contains(int position)</i></h5>
	 * <p>
	 * Returns flag indicating if the selected items array contains at the
	 * requested position some selected item.
	 * </p>
	 *
	 * @param position
	 *            The position of the item.
	 * @return <code>True</code> if the array contains that item,
	 *         <code>false</code> otherwise.
	 */
	protected final boolean contains(int position) {
		return aSelectedItems.get(position) != 0;
	}

	/**
	 * <br/>
	 * <h5><i>protected final void add(int position)</i></h5>
	 * <p>
	 * Adds the item with given position into array of selected items.
	 * </p>
	 *
	 * @param position
	 *            The position of the item from the adapter.
	 */
	protected final void add(int position) {
		aSelectedItems.append(position, position);

        Item item = get(position);
        if (item != null) {
            // Select item.
            item.setSelected(true);
        }
    }

	/**
	 * <br/>
	 * <h5><i>protected final I get(int position)</i></h5>
	 * <p>
	 * Returns the select-able item at the requested position. Same as
	 * {@link #getSelectedItem(int)}.
	 * </p>
	 *
	 * @param position
	 *            Position of the item.
	 * @return Select-able item or <code>null</code> if there is no item at the
	 *         given position.
	 */
    @SuppressWarnings("unchecked")
	protected final Item get(int position) {
		return (Item) getAdapter().getItem(position);
	}

	/**
	 * <br/>
	 * <h5><i>protected final void remove(int position)</i></h5>
	 * <p>
	 * Removes the item at the requested position from selected items array.
	 * </p>
	 *
	 * @param position
	 *            The position of the item to remove.
	 */
	protected final void remove(int position) {
        Item item = get(position);
        if (item != null && item.isSelected()) {
            aSelectedItems.removeAt(position);
            // Un-select item.
            item.setSelected(false);
        }
	}

    /**
     * <br/>
     * <h5><i>protected void clearSelection(boolean notify)</i></h5>
     * <p>
     * Sets the selected state to <code>false</code> for all select-able items.
     * </p>
     *
     * @param notify
     */
    protected void clearSelection(boolean notify) {
        for (int i = 0; i < aSelectedItems.size(); i++) {
            Item item = get(aSelectedItems.keyAt(i));
            if (item != null) {
                aSelectedItems.removeAt(i);
                // Un-select item.
                item.setSelected(false);
            }
        }
        // Clear selected items.
        aSelectedItems.clear();
        if (notify) {
            notifyAdapter();
        }
    }

	/**
	 * Private -------------------------------
	 */

	/**
	 * Abstract methods ----------------------
	 */

	/**
	 * <br/>
	 * <h5><i>public void setItemSelected(int position, boolean selected)</i></h5>
	 * <p>
	 * Sets the given selected state of the select-able item at the given
	 * position.
	 * </p>
	 * <p>
	 * <i>Note that this implementation should notify the adapter that the data
	 * set was changed.</i>
	 * </p>
	 *
	 * @param position
	 *            Position of the select-able item in the adapter.
	 * @param selected
	 *            New selected state.
	 */
	public abstract void setItemSelected(int position, boolean selected);

	/**
	 * <br/>
	 * <h5><i>public void selectAll()</i></h5>
	 * <p>
	 * Sets the selected state to <code>true</code> for all select-able items.
	 * </p>
	 * <p>
	 * <i>Note that this implementation should notify the adapter that the data
	 * set was changed.</i>
	 * </p>
	 */
	public abstract void selectAll();

	/**
	 * <br/>
	 * <h5><i>public void selectRange(int startPosition, int count)</i></h5>
	 * <p>
	 * Sets the selected state to <code>true</code> for the select-able items at
	 * the positions <code>(startPosition, startPosition + count)</code>.
	 * </p>
	 * <p>
	 * <i>Note that this implementation should notify the adapter that the data
	 * set was changed.</i>
	 * </p>
	 *
	 * @param startPosition
	 *            Position for selection start.
	 * @param count
	 *            Count of the items to select from the start position.
	 */
	public abstract void selectRange(int startPosition, int count);

	/**
	 * Inner classes =========================
	 */

	/**
	 * Interface =============================
	 */

}
