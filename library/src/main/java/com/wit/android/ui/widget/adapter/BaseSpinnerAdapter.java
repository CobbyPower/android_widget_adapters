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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wit.android.ui.widget.adapter.annotation.DropDownView;
import com.wit.android.ui.widget.adapter.annotation.DropDownViewHolder;
import com.wit.android.ui.widget.adapter.annotation.DropDownViewHolderFactory;

/**
 * <h3>Class Overview</h3>
 * todo: description
 * <h3>Accepted annotations</h3>
 * <ul>
 * <li>
 * {@link com.wit.android.ui.widget.adapter.annotation.DropDownView @DropDownView} <b>[class - inherited]</b>
 * <p>
 * If this annotation is presented, a resource id provided by this annotation will be used to inflate
 * the desired drop down view in {@link #onCreateDropDownView(int, android.view.LayoutInflater, android.view.ViewGroup)}.
 * </li>
 * <li>
 * {@link com.wit.android.ui.widget.adapter.annotation.DropDownViewHolderFactory @DropDownViewHolderFactory} <b>[class - inherited]</b>
 * <p>
 * If this annotation is presented, a class provided by this annotation will be used to create instances
 * of holders for drop down views.
 * </li>
 * <li>
 * {@link com.wit.android.ui.widget.adapter.annotation.DropDownViewHolder @DropDownViewHolder} <b>[class - inherited]</b>
 * <p>
 * If this annotation is presented, a class provided by this annotation will be used to instantiate
 * an instance of the desired drop down view holder in {@link #onCreateDropDownViewHolder(int, android.view.View)}.
 * </li>
 * <li>{@link BaseAdapter + super annotations}</li>
 * </ul>
 *
 * @param <Item> A type of the item presented within a data set of a subclass of this BaseSpinnerAdapter.
 * @author Martin Albedinsky
 */
public abstract class BaseSpinnerAdapter<Item> extends BaseAdapter<Item> {

	/**
	 * Interface ===================================================================================
	 */

	/**
	 * Constants ===================================================================================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = "BaseSpinnerAdapter";

	/**
	 * Flag indicating whether the debug output trough log-cat is enabled or not.
	 */
	// private static final boolean DEBUG_ENABLED = true;

	/**
	 * Flag indicating whether the output trough log-cat is enabled or not.
	 */
	// private static final boolean LOG_ENABLED = true;

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
	private Class<? extends ViewHolder> mClassOfDropDownHolder;

	/**
	 * Factory responsible for instantiation of drop down view holders for this adapter.
	 */
	private ViewHolderFactory mDropDownHolderFactory;

	/**
	 * Constructors ================================================================================
	 */

	/**
	 * Creates a new instance of BaseSpinnerAdapter within the given <var>context</var>.
	 * <p>
	 * If {@link com.wit.android.ui.widget.adapter.annotation.DropDownView @DropDownView},
	 * {@link com.wit.android.ui.widget.adapter.annotation.DropDownViewHolderFactory @DropDownViewHolderFactory}
	 * or {@link com.wit.android.ui.widget.adapter.annotation.DropDownViewHolder @DropDownViewHolder}
	 * annotations are presented above subclass of this BaseSpinnerAdapter, they will be processed here.
	 *
	 * @param context Context in which will be this adapter used.
	 */
	public BaseSpinnerAdapter(@NonNull Context context) {
		super(context);
		this.processAnnotations(((Object) this).getClass());
	}

	/**
	 * Methods =====================================================================================
	 */

	/**
	 * Public --------------------------------------------------------------------------------------
	 */

	/**
	 * <b>This method was deprecated in 2.4</b>, use {@link android.widget.Spinner#setSelection(int) Spinner#setSelection(int)}
	 * or {@link android.widget.Spinner#setSelection(int, boolean) Spinner#setSelection(int, boolean)}
	 * instead.
	 * <p>
	 * Called to dispatch that item at the specified <var>position</var> was selected within the
	 * {@link android.widget.Spinner Spinner} to which is this adapter attached.
	 *
	 * @param position Position of an item which was selected.
	 * @return {@code True} if the current selected position was changed, {@code false} otherwise.
	 */
	@Deprecated
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
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		this.mSelectedPosition = position;
		return super.getView(position, convertView, parent);
	}

	/**
	 * Performs optimized algorithm for this method using the <b>Holder</b> pattern.
	 */
	@Override
	public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		Object viewHolder;
		if (convertView == null) {
			// Dispatch to create new drop down view.
			convertView = onCreateDropDownView(position, mLayoutInflater, parent);
			// Resolve holder for the newly created drop down view.
			final Object holder = onCreateDropDownViewHolder(position, convertView);
			if (holder != null) {
				convertView.setTag(viewHolder = holder);
			} else {
				viewHolder = convertView;
			}
		} else {
			final Object holder = convertView.getTag();
			viewHolder = holder != null ? holder : convertView;
		}
		// Dispatch to bind drop down view with data.
		onBindDropDownView(position, viewHolder);
		return convertView;
	}

	/**
	 * Getters + Setters ---------------------------------------------------------------------------
	 */

	/**
	 * Returns the currently selected item.
	 *
	 * @return An item obtained by {@link #getItem(int)} for the current selected position.
	 * @see #getSelectedPosition()
	 */
	@Nullable
	public Item getSelectedItem() {
		return getItem(mSelectedPosition);
	}

	/**
	 * Returns the currently selected position.
	 *
	 * @return Position which was set as selected by {@link #dispatchItemSelected(int)} or {@code 0}
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
	 * Invoked to create a drop down view for an item from the current data set at the specified position.
	 * <p>
	 * This is invoked only if <var>convertView</var> for the specified <var>position</var> in
	 * {@link #getDropDownView(int, android.view.View, android.view.ViewGroup)} was {@code null}.
	 * <p>
	 * <b>Note</b>, that if {@link com.wit.android.ui.widget.adapter.annotation.DropDownView @DropDownView}
	 * annotation is presented, a resource id provided by this annotation will be used to inflate the
	 * requested view, otherwise {@link #onCreateView(int, android.view.LayoutInflater, android.view.ViewGroup)}
	 * will be called.
	 *
	 * @param position Position of the item from the current data set for which should be a new drop
	 *                 down view created.
	 * @param inflater Layout inflater which can be used to inflate the requested view.
	 * @param parent   A parent view, to resolve correct layout params for the newly creating view.
	 * @return New instance of the requested view.
	 * @see #inflate(int, android.view.ViewGroup)
	 */
	@NonNull
	protected View onCreateDropDownView(int position, @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
		if (mDropDownViewRes >= 0) {
			return inflater.inflate(mDropDownViewRes, parent, false);
		}
		return onCreateView(position, inflater, parent);
	}

	/**
	 * Invoked to create a view holder for a drop down view of an item from the current data set at
	 * the specified position.
	 * <p>
	 * This is invoked only if <var>convertView</var> for the specified <var>position</var> in
	 * {@link #getDropDownView(int, android.view.View, android.view.ViewGroup)} was {@code null},
	 * so as view also holder need to be created.
	 * <p>
	 * If {@link com.wit.android.ui.widget.adapter.annotation.DropDownViewHolderFactory @DropDownViewHolderFactory}
	 * annotation is presented, factory instantiated from the class provided by this annotation will
	 * be used to create the requested drop down view holder, otherwise
	 * {@link com.wit.android.ui.widget.adapter.annotation.DropDownViewHolder @DropDownViewHolder}
	 * annotation will be processed as described below.
	 * <p>
	 * If {@link com.wit.android.ui.widget.adapter.annotation.DropDownViewHolder @DropDownViewHolder}
	 * annotation is presented, a class provided by this annotation will be used to instantiate the
	 * requested drop down view holder, otherwise {@link #onCreateViewHolder(int, android.view.View)}
	 * will be called.
	 *
	 * @param position Position of the item from the current data set for which should be a new drop
	 *                 down view holder created.
	 * @param itemView An instance of the same view as obtained from
	 *                 {@link #onCreateDropDownView(int, android.view.LayoutInflater, android.view.ViewGroup)}
	 *                 for the specified position.
	 * @return New instance of the requested drop down view holder.
	 * @throws IllegalStateException If the class provided by @DropDownViewHolder annotation
	 *                               can not be accessed or does not have an empty public
	 *                               constructor.
	 */
	@Nullable
	protected Object onCreateDropDownViewHolder(int position, @NonNull View itemView) {
		if (mDropDownHolderFactory != null) {
			final ViewHolder holder = mDropDownHolderFactory.createHolder(this, position, itemView);
			if (holder != null) {
				holder.create(position, itemView);
				return holder;
			}
		}
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
	 */
	@Override
	protected void onBindView(int position, @NonNull Object viewHolder) {
		final Item selectedItem = getSelectedItem();
		if (selectedItem != null) {
			onUpdateView(position, selectedItem, viewHolder);
		}
	}

	/**
	 * Invoked to set up and populate a drop down view of an item from the current data set at the
	 * specified position. This is invoked whenever {@link #getDropDownView(int, android.view.View, android.view.ViewGroup)}
	 * is called.
	 * <p>
	 * <b>Note</b>, that if {@link #onCreateDropDownViewHolder(int, android.view.View)} returns
	 * {@code null}  for the specified <var>position</var> here passed <var>viewHolder</var> will
	 * be the view created by {@link #onCreateDropDownView(int, android.view.LayoutInflater, android.view.ViewGroup)}
	 * for the specified position or just recycled view for such a position. This approach can be used,
	 * when a view hierarchy of the specific spinner drop down item is represented by one custom view,
	 * where such a view represents a holder for all its child views.
	 * <p>
	 * By default this will call {@link #onUpdateView(int, Object, Object)} with an item obtained by
	 * {@link #getItem(int)} for the specified position.
	 *
	 * @param position   Position of the item from the current data set of which drop down view to
	 *                   set up.
	 * @param viewHolder An instance of the same holder as provided by {@link #onCreateDropDownViewHolder(int, android.view.View)}
	 *                   for the specified position or converted view as holder as described above.
	 */
	protected void onBindDropDownView(int position, @NonNull Object viewHolder) {
		final Item item = getItem(position);
		if (item != null) {
			onUpdateView(position, item, viewHolder);
		}
	}

	/**
	 * Invoked to update views within the given <var>viewHolder</var> with data of the given <var>item</var>.
	 * <p>
	 * By default this will try to bind the given <var>viewHolder</var> (if it is instanceof {@link ViewHolder}),
	 * otherwise implementation of this method is <b>required</b> or exception will be thrown.
	 *
	 * @param position   Position of the item from the current data set.
	 * @param item       Always valid item obtained by {@link #getItem(int)} for the specified position.
	 * @param viewHolder An instance of the same holder as provided by {@link #onCreateDropDownViewHolder(int, android.view.View)}
	 *                   or {@link #onCreateViewHolder(int, android.view.View)} for the specified position.
	 * @throws IllegalStateException If updating process for the given <var>item</var> fails.
	 */
	protected void onUpdateView(int position, @NonNull Item item, @NonNull Object viewHolder) {
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
	 * Called to process all class annotations of this <var>classOfAdapter</var>.
	 *
	 * @param classOfAdapter The class of this adapter of which annotations to process.
	 */
	private void processAnnotations(Class<?> classOfAdapter) {
		// Obtain drop down view.
		final DropDownView dropDownView = AdapterAnnotations.obtainAnnotationFrom(
				classOfAdapter, DropDownView.class, BaseAdapter.class
		);
		if (dropDownView != null) {
			this.mDropDownViewRes = dropDownView.value();
		}
		// Obtain drop down view holder factory.
		final DropDownViewHolderFactory holderFactory = AdapterAnnotations.obtainAnnotationFrom(
				classOfAdapter, DropDownViewHolderFactory.class, BaseAdapter.class
		);
		if (holderFactory != null) {
			try {
				this.mDropDownHolderFactory = holderFactory.value().newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new IllegalStateException(
						"Failed to create drop down view holder factory from class(" + holderFactory.value() + "). " +
								"Check if this factory class has public access and empty public constructor."
				);
			}
		}
		// Obtain drop down view holder.
		final DropDownViewHolder dropDownViewHolder = AdapterAnnotations.obtainAnnotationFrom(
				classOfAdapter, DropDownViewHolder.class, BaseAdapter.class
		);
		if (dropDownViewHolder != null) {
			this.mClassOfDropDownHolder = dropDownViewHolder.value();
		}
	}

	/**
	 * Inner classes ===============================================================================
	 */
}
