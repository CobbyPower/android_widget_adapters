/*
 * =================================================================================================
 *                 Copyright (C) 2013 - 2014 Martin Albedinsky [Wolf-ITechnologies]
 * =================================================================================================
 *         Licensed under the Apache License, Version 2.0 or later (further "License" only).
 * -------------------------------------------------------------------------------------------------
 * You may use this file only in compliance with the License. More details and copy of this License
 * you may obtain at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * You can redistribute, modify or publish any part of the code written within this file but as it
 * is described in the License, the software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES or CONDITIONS OF ANY KIND.
 *
 * See the License for the specific language governing permissions and limitations under the License.
 * =================================================================================================
 */
package com.wit.android.ui.widget.adapter.module;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseIntArray;

/**
 * <h4>Class Overview</h4>
 * <p>
 * todo: description
 * </p>
 *
 * @author Martin Albedinsky
 */
public class SelectionModule extends AdapterModule {

	/**
	 * Constants ===================================================================================
	 */

	/**
	 * <p>
	 * Mode to allow only single selected position.
	 * </p>
	 *
	 * @see #getSelectedPosition()
	 */
	public static final int MODE_SINGLE = 0x00;

	/**
	 * <p>
	 * Mode to allow multiple selected positions.
	 * </p>
	 *
	 * @see #getSelectedPositions()
	 */
	public static final int MODE_MULTIPLE = 0x01;

	/**
	 * Flag indicating whether the debug output trough log-cat is enabled or not.
	 */
	// private static final boolean DEBUG_ENABLED = true;

	/**
	 * Flag indicating whether the output trough log-cat is enabled or not.
	 */
	// private static final boolean LOG_ENABLED = true;

	/**
	 * Log TAG.
	 */
	// private static final String TAG = SelectionModule.class.getSimpleName();

	/**
	 * Static members ==============================================================================
	 */

	/**
	 * Members =====================================================================================
	 */

	/**
	 * Current selection mode of this module.
	 */
	private int mMode = MODE_SINGLE;

	/**
	 * Set of the currently selected positions.
	 */
	private SparseIntArray mSelectedPositions = new SparseIntArray();

	/**
	 * Constructors ================================================================================
	 */

	/**
	 * Methods =====================================================================================
	 */

	/**
	 * Public --------------------------------------------------------------------------------------
	 */

	/**
	 * <p>
	 * Checks whether the specified position is currently selected or not.
	 * </p>
	 *
	 * @param position The position of an item of which selection state to check.
	 * @return <code>True</code> if the specified position is selected, <code>false</code> otherwise.
	 * @see #setSelected(int, boolean)
	 * @see #toggleSelectionState(int)
	 */
	public boolean isSelected(int position) {
		return contains(position);
	}

	/**
	 * <p>
	 * Changes selection state of the specified position to the opposite one
	 * (<code>selected -> unselected; unselected -> selected</code>) and <b>notifies adapter</b>.
	 * </p>
	 *
	 * @param position The position of an item of which selection state to toggle.
	 * @return Count of the currently selected positions.
	 * @see #setSelected(int, boolean)
	 * @see #isAdapterNotificationEnabled()
	 */
	public int toggleSelectionState(int position) {
		setSelected(position, !contains(position));
		return getSelectionCount();
	}

	/**
	 * <p>
	 * Changes selection state of the specified position to the desired one and <b>notifies adapter</b>.
	 * </p>
	 *
	 * @param position The position of an item of which selection state to change.
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
	 * Same as {@link #selectRange(int, int)} with parameters <code>(0, ModuleAdapter.getCount())</code>.
	 * </p>
	 *
	 * @see #setSelected(int, boolean)
	 */
	public void selectAll() {
		this.checkActualModeFor(MODE_MULTIPLE, "select all items");
		selectRange(0, mAdapter.getCount());
	}

	/**
	 * <p>
	 * Selects positions in the range <code>[startPosition, startPosition + count]</code> and
	 * <b>notifies adapter</b>.
	 * </p>
	 * <p>
	 * All previously selected positions will remain selected.
	 * </p>
	 *
	 * @param startPosition The position from which to start selection.
	 * @param count         Count of items to select from the start position.
	 * @throws IllegalStateException     If the current mode is not set to {@link #MODE_MULTIPLE}.
	 * @throws IndexOutOfBoundsException If <code>startPosition + count > n</code>.
	 * @see #selectAll()
	 * @see #setSelected(int, boolean)
	 * @see #isAdapterNotificationEnabled()
	 */
	public void selectRange(int startPosition, int count) {
		this.checkActualModeFor(MODE_MULTIPLE, "select all items");
		// Check correct index.
		final int n = mAdapter.getCount();
		if (startPosition + count > n) {
			throw new IndexOutOfBoundsException("Incorrect count(" + count + ") for start position(" + startPosition + "). Adapter has only " + n + " items.");
		}

		// Select all items in the requested range.
		for (int i = startPosition; i < startPosition + count; i++) {
			select(i);
		}
		notifyAdapter();
	}

	/**
	 * <p>
	 * Deselects all currently selected positions and <b>notifies adapter</b>.
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
	 * Deselects all currently selected positions in the range <code>[startPosition, startPosition + count]</code>
	 * and <b>notifies adapter</b>.
	 * </p>
	 *
	 * @param startPosition The position from which to start deselection.
	 * @param count         Count of items to deselect from the start position.
	 * @throws IllegalStateException     If the current mode is not set to {@link #MODE_MULTIPLE}.
	 * @throws IndexOutOfBoundsException If <code>startPosition + count > n</code>.
	 * @see #clearSelection()
	 * @see #isAdapterNotificationEnabled()
	 */
	public void clearSelectionInRange(int startPosition, int count) {
		this.checkActualModeFor(MODE_MULTIPLE, "clear all selected items");
		// Check correct index.
		final int n = mAdapter.getCount();
		if (startPosition + count > n) {
			throw new IndexOutOfBoundsException("Incorrect count(" + count + ") for start position(" + startPosition + "). Adapter has only " + n + " items.");
		}

		// Deselect all items in the requested range.
		for (int i = startPosition; i < startPosition + count; i++) {
			deselect(i);
		}
		notifyAdapter();
	}

	/**
	 * @return <code>True</code> whether there are some selected positions to save, <code>false</code>
	 * otherwise.
	 */
	@Override
	public boolean requiresStateSaving() {
		return mSelectedPositions.size() > 0;
	}

	/**
	 * Getters + Setters ---------------------------------------------------------------------------
	 */

	/**
	 * <p>
	 * Returns the sparse array with the currently selected positions.
	 * </p>
	 *
	 * @return {@link android.util.SparseIntArray} with positions which are at this time selected.
	 * <b>Note</b> that this array is sorted for optimization (from lowest to highest position).
	 */
	public SparseIntArray getSelection() {
		return mSelectedPositions;
	}

	/**
	 * <p>
	 * Returns the current selection size.
	 * </p>
	 *
	 * @return Count of the currently selected positions.
	 */
	public int getSelectionCount() {
		return mSelectedPositions.size();
	}

	/**
	 * <p>
	 * Returns the position which is at this time selected.
	 * </p>
	 *
	 * @return Currently selected position or <code>-1</code> if there is no position selected at
	 * this time.
	 * @throws IllegalStateException If the current mode is not set to {@link #MODE_SINGLE}.
	 * @see #getSelectedPositions()
	 */
	public int getSelectedPosition() {
		this.checkActualModeFor(MODE_SINGLE, "obtain selected item position");
		return mSelectedPositions.size() > 0 ? mSelectedPositions.get(mSelectedPositions.keyAt(0)) : -1;
	}

	/**
	 * <p>
	 * Same as {@link #getSelectedPositions(boolean)} with <code>true</code> for <var>ascending</var>
	 * parameter, so items in the array will be sorted ascending.
	 * </p>
	 */
	public int[] getSelectedPositions() {
		return getSelectedPositions(true);
	}

	/**
	 * <p>
	 * Returns an array with all positions which are currently selected.
	 * </p>
	 * <p>
	 * <b>Note</b>, that array is obtained/processed from the {@link android.util.SparseIntArray},
	 * so its items will be by default sorted ascending.
	 * </p>
	 *
	 * @param ascending <code>False</code> to sort array items descending, <code>true</code> to ascending.
	 * @return Array with all currently selected positions.
	 * @throws IllegalStateException If the current mode is not set to {@link #MODE_MULTIPLE}.
	 * @see #getSelectedPositions()
	 */
	public int[] getSelectedPositions(boolean ascending) {
		this.checkActualModeFor(MODE_MULTIPLE, "obtain selected items position");
		final int[] positions = new int[mSelectedPositions.size()];
		for (int i = 0; i < positions.length; i++) {
			positions[i] = mSelectedPositions.keyAt(i);
		}
		return ascending ? positions : reversePositions(positions);
	}

	/**
	 * <p>
	 * Sets the current selection mode of this module to the specified one.
	 * </p>
	 *
	 * @param mode The desired selection mode. One of {@link #MODE_SINGLE} or {@link #MODE_MULTIPLE}.
	 * @see #getMode()
	 */
	public void setMode(int mode) {
		switch (mode) {
			case MODE_SINGLE:
			case MODE_MULTIPLE:
				this.mMode = mode;
		}
	}

	/**
	 * <p>
	 * Returns the current selection mode of this module.
	 * </p>
	 *
	 * @return Current selection mode (one of {@link #MODE_SINGLE}, {@link #MODE_MULTIPLE}) or {@link #MODE_SINGLE}
	 * by default.
	 * @see #setMode(int)
	 */
	public int getMode() {
		return mMode;
	}

	/**
	 * Protected -----------------------------------------------------------------------------------
	 */

	/**
	 * <p>
	 * Checks whether a set of the currently selected positions contains the specified position or not.
	 * </p>
	 *
	 * @param position The position to check.
	 * @return <code>True</code> if the specified position is presented within a set of the currently
	 * selected positions, <code>false</code> otherwise.
	 */
	protected final boolean contains(int position) {
		return mSelectedPositions.indexOfKey(position) >= 0;
	}

	/**
	 * <p>
	 * Adds the specified position into a set of the currently selected positions.
	 * </p>
	 *
	 * @param position The position to add into the selected ones.
	 */
	protected final void select(int position) {
		mSelectedPositions.append(position, position);
	}

	/**
	 * <p>
	 * Removes the specified position form a set of the currently selected positions.
	 * </p>
	 *
	 * @param position The position to remove from the selected ones.
	 */
	protected final void deselect(int position) {
		final int index = mSelectedPositions.indexOfKey(position);
		if (index >= 0) {
			mSelectedPositions.removeAt(index);
		}
	}

	/**
	 * <p>
	 * Removes all positions form a set of the currently selected positions.
	 * </p>
	 *
	 * @param notify <code>True</code> to notify the currently attached adapter by {@link #notifyAdapter()},
	 *               <code>false</code> otherwise.
	 */
	protected final void clearSelection(boolean notify) {
		mSelectedPositions.clear();
		if (notify) {
			notifyAdapter();
		}
	}

	/**
	 */
	@Override
	protected Parcelable onSaveInstanceState() {
		final SavedState state = new SavedState(super.onSaveInstanceState());
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
		super.onRestoreInstanceState(state.getSuperState());

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
	 * Private -------------------------------------------------------------------------------------
	 */

	/**
	 * Check whether the current mode complies with the requested mode for the given action. If not
	 * the IllegalStateException is thrown.
	 *
	 * @param requiredMode Flag of the required mode.
	 * @param action       Action for which is this check performed.
	 */
	private void checkActualModeFor(int requiredMode, String action) {
		if (mMode != requiredMode) {
			throw new IllegalStateException("Can't " + action + ". Not in required(" + getModeName(requiredMode) + ") mode.");
		}
	}

	/**
	 * Returns name for the given mode.
	 *
	 * @param mode Mode identifier.
	 * @return Name of the requested mode.
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
	 * Abstract methods ----------------------------------------------------------------------------
	 */

	/**
	 * Inner classes ===============================================================================
	 */

	/**
	 * <h4>Class Overview</h4>
	 * <p>
	 * todo: description
	 * </p>
	 *
	 * @author Martin Albedinsky
	 */
	public static class SavedState extends BaseSavedState {

		/**
		 * Members =================================================================================
		 */

		/**
		 * <p>
		 * Creator used to create an instance or array of instances of SavedState from {@link android.os.Parcel}.
		 * </p>
		 */
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
		 * Selection module mode to save and restore.
		 */
		private int mode = MODE_SINGLE;

		/**
		 * Selection module selected items set to save and restore.
		 */
		private int[] selectedItems = {};

		/**
		 * Constructors ============================================================================
		 */

		/**
		 * <p>
		 * Creates a new instance SavedState with the given <var>superState</var> to allow
		 * chaining of saved states in {@link #onSaveInstanceState()} and also in
		 * {@link #onRestoreInstanceState(android.os.Parcelable)}.
		 * </p>
		 *
		 * @param superState A super state obtained from <code>super.onSaveInstanceState()</code>
		 *                   within <code>onSaveInstanceState()</code> of a specific {@link SelectionModule}
		 *                   implementation.
		 */
		protected SavedState(Parcelable superState) {
			super(superState);
		}

		/**
		 * <p>
		 * Called form {@link #CREATOR} to create an instance of SavedState form the given parcel
		 * <var>source</var>.
		 * </p>
		 *
		 * @param source Parcel with data for a new instance.
		 */
		protected SavedState(Parcel source) {
			super(source);
			this.mode = source.readInt();
			this.selectedItems = source.createIntArray();
		}

		/**
		 * Methods =================================================================================
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
	 * Interface ===================================================================================
	 */
}
