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
import android.support.annotation.NonNull;
import android.util.SparseIntArray;

/**
 * <h4>Class Overview</h4>
 * todo: description
 *
 * @author Martin Albedinsky
 */
public class SelectionModule extends AdapterModule {

	/**
	 * Interface ===================================================================================
	 */

	/**
	 * Constants ===================================================================================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = "SelectionModule";

	/**
	 * Flag indicating whether the debug output trough log-cat is enabled or not.
	 */
	// private static final boolean DEBUG_ENABLED = true;

	/**
	 * Flag indicating whether the output trough log-cat is enabled or not.
	 */
	// private static final boolean LOG_ENABLED = true;

	/**
	 * Mode to allow only single selected position.
	 *
	 * @see #getSelectedPosition()
	 */
	public static final int MODE_SINGLE = 0x00;

	/**
	 * Mode to allow multiple selected positions.
	 *
	 * @see #getSelectedPositions()
	 */
	public static final int MODE_MULTIPLE = 0x01;

	/**
	 * Static members ==============================================================================
	 */

	/**
	 * Members =====================================================================================
	 */

	/**
	 * Set of the currently selected positions.
	 */
	private final SparseIntArray SELECTION = new SparseIntArray();

	/**
	 * Current selection mode of this module.
	 */
	private int mMode = MODE_SINGLE;

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
	 * Checks whether the specified position is currently selected or not.
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
	 * Changes selection state of the specified position to the opposite one
	 * (<code>selected -> unselected; unselected -> selected</code>) and <b>notifies adapter</b>.
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
	 * Changes selection state of the specified position to the desired one and <b>notifies adapter</b>.
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
	 * Same as {@link #selectRange(int, int)} with parameters <code>(0, ModuleAdapter.getCount())</code>.
	 *
	 * @see #setSelected(int, boolean)
	 */
	public void selectAll() {
		this.checkActualModeFor(MODE_MULTIPLE, "select all items");
		selectRange(0, mAdapter.getCount());
	}

	/**
	 * Selects positions in the range <code>[startPosition, startPosition + count]</code> and
	 * <b>notifies adapter</b>.
	 * <p/>
	 * All previously selected positions will remain selected.
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
	 * Deselects all currently selected positions and <b>notifies adapter</b>.
	 *
	 * @see #clearSelectionInRange(int, int)
	 * @see #isAdapterNotificationEnabled()
	 */
	public void clearSelection() {
		clearSelection(true);
	}

	/**
	 * Deselects all currently selected positions in the range <code>[startPosition, startPosition + count]</code>
	 * and <b>notifies adapter</b>.
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
		return SELECTION.size() > 0;
	}

	/**
	 * Getters + Setters ---------------------------------------------------------------------------
	 */

	/**
	 * Returns the sparse array with the currently selected positions.
	 *
	 * @return {@link android.util.SparseIntArray} with positions which are at this time selected.
	 * <b>Note</b> that this array is sorted for optimization (from lowest to highest position).
	 */
	@NonNull
	public SparseIntArray getSelection() {
		return SELECTION;
	}

	/**
	 * Returns the current selection size.
	 *
	 * @return Count of the currently selected positions.
	 */
	public int getSelectionCount() {
		return SELECTION.size();
	}

	/**
	 * Returns the position which is at this time selected.
	 *
	 * @return Currently selected position or <code>-1</code> if there is no position selected at
	 * this time.
	 * @throws IllegalStateException If the current mode is not set to {@link #MODE_SINGLE}.
	 * @see #getSelectedPositions()
	 */
	public int getSelectedPosition() {
		this.checkActualModeFor(MODE_SINGLE, "obtain selected item position");
		return SELECTION.size() > 0 ? SELECTION.get(SELECTION.keyAt(0)) : -1;
	}

	/**
	 * Same as {@link #getSelectedPositions(boolean)} with <code>true</code> for <var>ascending</var>
	 * parameter, so items in the array will be sorted ascending.
	 */
	@NonNull
	public int[] getSelectedPositions() {
		return getSelectedPositions(true);
	}

	/**
	 * Returns an array with all positions which are currently selected.
	 * <p/>
	 * <b>Note</b>, that array is obtained/processed from the {@link android.util.SparseIntArray},
	 * so its items will be by default sorted ascending.
	 *
	 * @param ascending <code>False</code> to sort array items descending, <code>true</code> to ascending.
	 * @return Array with all currently selected positions.
	 * @throws IllegalStateException If the current mode is not set to {@link #MODE_MULTIPLE}.
	 * @see #getSelectedPositions()
	 */
	@NonNull
	public int[] getSelectedPositions(boolean ascending) {
		this.checkActualModeFor(MODE_MULTIPLE, "obtain selected items position");
		final int[] positions = new int[SELECTION.size()];
		for (int i = 0; i < positions.length; i++) {
			positions[i] = SELECTION.keyAt(i);
		}
		return ascending ? positions : reversePositions(positions);
	}

	/**
	 * Sets the current selection mode of this module to the specified one.
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
	 * Returns the current selection mode of this module.
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
	 * Checks whether a set of the currently selected positions contains the specified position or not.
	 *
	 * @param position The position to check.
	 * @return <code>True</code> if the specified position is presented within a set of the currently
	 * selected positions, <code>false</code> otherwise.
	 */
	protected final boolean contains(int position) {
		return SELECTION.indexOfKey(position) >= 0;
	}

	/**
	 * Adds the specified position into a set of the currently selected positions.
	 *
	 * @param position The position to add into the selected ones.
	 */
	protected final void select(int position) {
		SELECTION.append(position, position);
	}

	/**
	 * Removes the specified position form a set of the currently selected positions.
	 *
	 * @param position The position to remove from the selected ones.
	 */
	protected final void deselect(int position) {
		final int index = SELECTION.indexOfKey(position);
		if (index >= 0) {
			SELECTION.removeAt(index);
		}
	}

	/**
	 * Removes all positions form a set of the currently selected positions.
	 *
	 * @param notify <code>True</code> to notify the currently attached adapter by {@link #notifyAdapter()},
	 *               <code>false</code> otherwise.
	 */
	protected final void clearSelection(boolean notify) {
		SELECTION.clear();
		if (notify) {
			notifyAdapter();
		}
	}

	/**
	 */
	@NonNull
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
	protected void onRestoreInstanceState(@NonNull Parcelable savedState) {
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
	 * Inner classes ===============================================================================
	 */

	/**
	 * <h4>Class Overview</h4>
	 * todo: description
	 *
	 * @author Martin Albedinsky
	 */
	public static class SavedState extends BaseSavedState {

		/**
		 * Members =================================================================================
		 */

		/**
		 * Creator used to create an instance or array of instances of SavedState from {@link android.os.Parcel}.
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
		 * Creates a new instance SavedState with the given <var>superState</var> to allow
		 * chaining of saved states in {@link #onSaveInstanceState()} and also in
		 * {@link #onRestoreInstanceState(android.os.Parcelable)}.
		 *
		 * @param superState A super state obtained from <code>super.onSaveInstanceState()</code>
		 *                   within <code>onSaveInstanceState()</code> of a specific {@link SelectionModule}
		 *                   implementation.
		 */
		protected SavedState(Parcelable superState) {
			super(superState);
		}

		/**
		 * Called form {@link #CREATOR} to create an instance of SavedState form the given parcel
		 * <var>source</var>.
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
		public void writeToParcel(@NonNull Parcel dest, int flags) {
			super.writeToParcel(dest, flags);
			dest.writeInt(mode);
			dest.writeIntArray(selectedItems);
		}
	}
}
