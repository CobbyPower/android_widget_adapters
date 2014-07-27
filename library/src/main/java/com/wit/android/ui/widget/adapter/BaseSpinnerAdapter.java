/*
 * =================================================================================================
 *                    Copyright (C) 2014 Martin Albedinsky [Wolf-ITechnologies]
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
package com.wit.android.ui.widget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wit.android.ui.widget.adapter.annotation.DropDownViewHolder;
import com.wit.android.ui.widget.adapter.annotation.DropDownView;

/**
 * <h4>Class Overview</h4>
 * <p>
 * todo: description
 * </p>
 * <h6>Accepted annotations</h6>
 * <ul>
 * <li>{@link com.wit.android.ui.widget.adapter.annotation.DropDownView @DropDownView} <b>[class]</b></li>
 * <p>
 * If this annotation is presented, a resource id provided by this annotation will be used to inflate
 * the desired drop down view in {@link #onCreateDropDownView(int, android.view.LayoutInflater, android.view.ViewGroup)}.
 * </p>
 * <li>{@link com.wit.android.ui.widget.adapter.annotation.DropDownViewHolder @DropDownViewHolder} <b>[class]</b></li>
 * <p>
 * If this annotation is presented, a class provided by this annotation will be used to instantiate
 * an instance of the desired drop down view holder in {@link #onCreateDropDownViewHolder(int, android.view.View)}.
 * </p>
 * <li>{@link BaseAdapter + super annotations}</li>
 * </ul>
 *
 * @param <Item> A type of the item presented within a data set of a subclass of this BaseSpinnerAdapter.
 * @author Martin Albedinsky
 * @see com.wit.android.ui.widget.adapter.annotation.DropDownView
 * @see com.wit.android.ui.widget.adapter.annotation.DropDownViewHolder
 */
public abstract class BaseSpinnerAdapter<Item> extends BaseAdapter<Item> {

	/**
	 * Constants ===================================================================================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = BaseSpinnerAdapter.class.getSimpleName();

	/**
	 * Flag indicating whether the debug output trough log-cat is enabled or not.
	 */
	// private static final boolean DEBUG_ENABLED = true;

	/**
	 * Flag indicating whether the output trough log-cat is enabled or not.
	 */
	// private static final boolean LOG_ENABLED = true;

	/**
	 * Enums =======================================================================================
	 */

	/**
	 * Static members ==============================================================================
	 */

	/**
	 * Members =====================================================================================
	 */

	/**
	 * Currently selected position.
	 */
	private int mSelectedPosition;

	/**
	 * Resource id of the view which should be inflated as drop down view.
	 */
	private int mDropDownViewRes = -1;

	/**
	 * Class to be used to instantiate an instance of holder for drop down view.
	 */
	private Class<? extends ViewHolder> mClassOfDropDownHolder = null;

	/**
	 * Arrays --------------------------------------------------------------------------------------
	 */

	/**
	 * Booleans ------------------------------------------------------------------------------------
	 */

	/**
	 * Constructors ================================================================================
	 */

	/**
	 * <p>
	 * Creates a new instance of BaseSpinnerAdapter within the given <var>context</var>.
	 * </p>
	 * <p>
	 * If {@link com.wit.android.ui.widget.adapter.annotation.DropDownView @DropDownView} or
	 * {@link com.wit.android.ui.widget.adapter.annotation.DropDownViewHolder @DropDownViewHolder}
	 * annotations are presented above subclass of this BaseSpinnerAdapter, they will be processed here.
	 * </p>
	 *
	 * @param context Context in which will be this adapter used.
	 */
	public BaseSpinnerAdapter(Context context) {
		super(context);
		final Class<?> classOfAdapter = ((Object) this).getClass();
		/**
		 * Process class annotations.
		 */
		// Obtain item view.
		if (classOfAdapter.isAnnotationPresent(DropDownView.class)) {
			this.mDropDownViewRes = classOfAdapter.getAnnotation(DropDownView.class).value();
		}
		// Obtain item view holder.
		if (classOfAdapter.isAnnotationPresent(DropDownViewHolder.class)) {
			this.mClassOfDropDownHolder = classOfAdapter.getAnnotation(DropDownViewHolder.class).value();
		}
	}

	/**
	 * Methods =====================================================================================
	 */

	/**
	 * Public --------------------------------------------------------------------------------------
	 */

	/**
	 * <p>
	 * Called to dispatch that item at the specified <var>position</var> was selected.
	 * </p>
	 *
	 * @param position Position of an item which was selected.
	 * @return <code>True</code> if the current selected position was changed, <code>false</code>
	 * otherwise.
	 */
	public boolean dispatchItemSelected(int position) {
		if (mSelectedPosition != position) {
			this.mSelectedPosition = position;
			notifyDataSetChanged();
			return true;
		}
		return false;
	}

	/**
	 */
	@Override
	public void onBindView(int position, Object viewHolder) {
		final Item selectedItem = getSelectedItem();
		if (selectedItem != null) {
			onUpdateView(position, selectedItem, viewHolder);
		}
	}

	/**
	 * <p>
	 * Performs optimized algorithm for this method using the <b>Holder</b> pattern.
	 * </p>
	 */
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		Object viewHolder;
		if (convertView == null) {
			// Dispatch to create new drop down view.
			convertView = onCreateDropDownView(position, getLayoutInflater(), parent);
			if (convertView == null) {
				throw new IllegalStateException("Created drop down view at position(" + position + ") can not be NULL.");
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
	 * Getters + Setters ---------------------------------------------------------------------------
	 */

	/**
	 * <p>
	 * Returns the currently selected item.
	 * </p>
	 *
	 * @return An item obtained by {@link #getItem(int)} for the current selected position.
	 * @see #getSelectedPosition()
	 */
	public Item getSelectedItem() {
		return getItem(mSelectedPosition);
	}

	/**
	 * <p>
	 * Returns the currently selected position.
	 * </p>
	 *
	 * @return Position which was set as selected by {@link #dispatchItemSelected(int)} or <code>0</code>
	 * by default.
	 * @see #getSelectedItem()
	 */
	public int getSelectedPosition() {
		return mSelectedPosition;
	}

	/**
	 * Protected -----------------------------------------------------------------------------------
	 */

	/**
	 * <p>
	 * Invoked to create a drop down view for an item from the current data set at the specified position.
	 * </p>
	 * <p>
	 * This is invoked only if <var>convertView</var> for the specified <var>position</var> in
	 * {@link #getDropDownView(int, android.view.View, android.view.ViewGroup)} was <code>null</code>.
	 * </p>
	 * <p>
	 * <b>Note</b>, that if {@link com.wit.android.ui.widget.adapter.annotation.DropDownView @DropDownView}
	 * annotation is presented, a resource id provided by this annotation will be used to inflate the
	 * requested view, otherwise {@link #onCreateView(int, android.view.LayoutInflater, android.view.ViewGroup)}
	 * will be called.
	 * </p>
	 *
	 * @param position Position of the item from the current data set for which should be a new drop
	 *                 down view created.
	 * @param inflater Layout inflater which can be used to inflate the requested view.
	 * @param parent   A parent view, to resolve correct layout params for the newly creating view.
	 * @return New instance of the requested view.
	 * @see #inflate(int, android.view.ViewGroup)
	 */
	protected View onCreateDropDownView(int position, LayoutInflater inflater, ViewGroup parent) {
		if (mDropDownViewRes >= 0) {
			return inflater.inflate(mDropDownViewRes, parent, false);
		}
		return onCreateView(position, inflater, parent);
	}

	/**
	 * <p>
	 * Invoked to create a view holder for a drop down view of an item from the current data set at
	 * the specified position.
	 * </p>
	 * <p>
	 * This is invoked only if <var>convertView</var> for the specified <var>position</var> in
	 * {@link #getDropDownView(int, android.view.View, android.view.ViewGroup)} was <code>null</code>,
	 * so as view also holder need to be created.
	 * </p>
	 * <p>
	 * <b>Note</b>, that if {@link com.wit.android.ui.widget.adapter.annotation.DropDownViewHolder @DropDownViewHolder}
	 * annotation is presented, a class provided by this annotation will be used to instantiate the
	 * requested drop down view holder, otherwise {@link #onCreateViewHolder(int, android.view.View)}
	 * will be called.
	 * </p>
	 *
	 * @param position Position of the item from the current data set for which should be a new drop
	 *                 down view holder created.
	 * @param itemView An instance of the same view as obtained from
	 *                 {@link #onCreateDropDownView(int, android.view.LayoutInflater, android.view.ViewGroup)}
	 *                 for the specified position.
	 * @return New instance of the requested drop down view holder.
	 * @throws java.lang.IllegalStateException If the class provided by @DropDownViewHolder annotation
	 *                                         can not be accessed or does not have an empty public
	 *                                         constructor.
	 */
	protected Object onCreateDropDownViewHolder(int position, View itemView) {
		if (mClassOfDropDownHolder != null) {
			ViewHolder holder;
			try {
				holder = mClassOfDropDownHolder.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new IllegalStateException(
						"Failed to create drop down view holder from class(" + mClassOfDropDownHolder + "). " +
								"Check if this holder class has public access and empty public constructor."
				);
			}
			holder.create(position, itemView);
			return holder;
		}
		return onCreateViewHolder(position, itemView);
	}

	/**
	 * <p>
	 * Invoked to set up and populate a drop down view of an item from the current data set at the
	 * specified position.
	 * </p>
	 * <p>
	 * This is invoked whenever {@link #getDropDownView(int, android.view.View, android.view.ViewGroup)}
	 * is called.
	 * </p>
	 * <p>
	 * By default this will call {@link #onUpdateView(int, Object, Object)} with an item obtained by
	 * {@link #getItem(int)} for the specified position.
	 * </p>
	 *
	 * @param position   Position of the item from the current data set of which drop down view to
	 *                   set up.
	 * @param viewHolder An instance of the same holder as provided by {@link #onCreateDropDownViewHolder(int, android.view.View)}
	 *                   for the specified position.
	 */
	protected void onBindDropDownView(int position, Object viewHolder) {
		final Item item = getItem(position);
		if (item != null) {
			onUpdateView(position, item, viewHolder);
		}
	}

	/**
	 * <p>
	 * Invoked to update views within the given <var>viewHolder</var> with data of the given <var>item</var>.
	 * </p>
	 * <p>
	 * By default this will try to bind the given <var>viewHolder</var> (if it is instanceof {@link ViewHolder}),
	 * otherwise implementation of this method is <b>required</b> or exception will be thrown.
	 * </p>
	 *
	 * @param position   Position of the item from the current data set.
	 * @param item       Always valid item obtained by {@link #getItem(int)} for the specified position.
	 * @param viewHolder An instance of the same holder as provided by {@link #onCreateDropDownViewHolder(int, android.view.View)}
	 *                   or {@link #onCreateViewHolder(int, android.view.View)} for the specified position.
	 * @throws java.lang.IllegalStateException If updating process for the given <var>item</var> fails.
	 */
	protected void onUpdateView(int position, Item item, Object viewHolder) {
		if (!bindViewInner(position, item, viewHolder)) {
			throw new IllegalStateException(
					"Failed to update view at position(" + position + "). " +
							viewHolder + " is not instance of ViewHolder."
			);
		}
	}

	/**
	 * Private -------------------------------------------------------------------------------------
	 */

	/**
	 * Abstract methods ----------------------------------------------------------------------------
	 */

	/**
	 * Inner classes ===============================================================================
	 */

	/**
	 * Interface ===================================================================================
	 */
}
