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
package com.wit.android.ui.widget.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.AbsSavedState;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wit.android.ui.widget.adapter.annotation.ItemView;
import com.wit.android.ui.widget.adapter.annotation.ItemViewHolder;

/**
 * <h4>Class Overview</h4>
 * <p>
 * todo: description
 * </p>
 * <h6>Accepted annotations</h6>
 * <ul>
 * <li>{@link com.wit.android.ui.widget.adapter.annotation.ItemView @ItemView} <b>[class, recursive]</b></li>
 * <p>
 * If this annotation is presented, a resource id provided by this annotation will be used to inflate
 * the desired view in {@link #onCreateView(int, android.view.LayoutInflater, android.view.ViewGroup)}.
 * </p>
 * <li>{@link com.wit.android.ui.widget.adapter.annotation.ItemViewHolder @ItemViewHolder} <b>[class, recursive]</b></li>
 * <p>
 * If this annotation is presented, a class provided by this annotation will be used to instantiate
 * an instance of the desired holder in {@link #onCreateViewHolder(int, android.view.View)}.
 * </p>
 * </ul>
 * <h5>State saving</h5>
 * <pre>
 * public class MyAdapter extends BaseAdapter&lt;String&gt; {
 *
 *     // ...
 *
 *     &#64;Override
 *     protected Parcelable onSaveInstanceState() {
 *         final MyAdapterState state = new MyAdapterState(super.onSaveInstanceState());
 *
 *         // ...
 *         // Pass here all data of this adapter which need to be saved to the state.
 *         // ...
 *
 *         return state;
 *     }
 *
 *     &#64;Override
 *     protected void onRestoreInstanceState(Parcelable savedState) {
 *          if (!(savedState instanceof MyAdapterState)) {
 *              // Passed savedState is not our state, let super to process it.
 *              super.onRestoreInstanceState(savedState);
 *              return;
 *          }
 *
 *          final MyAdapterState state = (MyAdapterState) savedState;
 *          // Pass superState to super to process it.
 *          super.onRestoreInstanceState(savedState.getSuperState());
 *
 *          // ...
 *          // Set here all data of this adapter which need to be restored from the savedState.
 *          // ...
 *     }
 *
 *     // ...
 *
 *     // Implementation of BaseSavedState for this adapter.
 *     static class MyAdapterState extends BaseSavedState {
 *
 *         // Each implementation of saved state need to have its own CREATOR provided.
 *         public static final Creator&lt;MyAdapterState&gt; CREATOR = new Creator&lt;&gt; {
 *
 *              &#64;Override
 *              public MyAdapterState createFromParcel(Parcel source) {
 *                  return new MyAdapterState(source);
 *              }
 *
 *              &#64;Override
 *              public MyAdapterState[] newArray(int size) {
 *                  return new MyAdapterState[size];
 *              }
 *         }
 *
 *         MyAdapterState(Parcel source) {
 *              super(source);
 *              // Restore here state's data.
 *         }
 *
 *         // Constructor used to chain the state of inheritance hierarchies.
 *         MyAdapterState(Parcelable superState) {
 *              super(superState);
 *         }
 *
 *         &#64;Override
 *         public void writeToParcel(Parcel dest, int flags) {
 *              super.writeToParcel(dest, flags);
 *              // Save here state's data.
 *         }
 *     }
 * }
 * </pre>
 *
 * @param <Item> A type of the item presented within a data set of a subclass of this BaseAdapter.
 * @author Martin Albedinsky
 * @see com.wit.android.ui.widget.adapter.annotation.ItemViewHolder
 * @see com.wit.android.ui.widget.adapter.annotation.ItemView
 * @see com.wit.android.ui.widget.adapter.BaseSpinnerAdapter
 */
public abstract class BaseAdapter<Item> extends android.widget.BaseAdapter implements StateAdapter {

	/**
	 * Constants ===================================================================================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = BaseAdapter.class.getSimpleName();

	/**
	 * Flag indicating whether the debug output trough log-cat is enabled or not.
	 */
	// private static final boolean DEBUG_ENABLED = true;

	/**
	 * Flag indicating whether the output for user trough log-cat is enabled or not.
	 */
	// private static final boolean USER_LOG = true;

	/**
	 * Flag indicating whether the output for user trough log-cat is enabled or not.
	 */
	// private static final boolean USER_LOG = true;

	/**
	 * Static members ==============================================================================
	 */

	/**
	 * Members =====================================================================================
	 */

	/**
	 * Context in which will be this adapter used.
	 */
	protected final Context mContext;

	/**
	 * Layout inflater used to inflate new views for this adapter.
	 */
	protected final LayoutInflater mLayoutInflater;

	/**
	 * Application resources.
	 */
	protected final Resources mResources;

	/**
	 * Item view type for the current {@link #getView(int, android.view.View, android.view.ViewGroup)}
	 * iteration.
	 */
	private int mCurrentViewType;

	/**
	 * Resource id of the view which should be inflated as item view.
	 */
	private int mViewRes = -1;

	/**
	 * Class to be used to instantiate an instance of holder for item view.
	 */
	private Class<? extends ViewHolder> mClassOfHolder = null;

	/**
	 * Registered OnDataSetListener callback.
	 */
	private OnDataSetListener mDataSetListener;

	/**
	 * Registered OnDataSetActionListener callback.
	 */
	private OnDataSetActionListener mDataSetActionListener;

	/**
	 * Constructors ================================================================================
	 */

	/**
	 * <p>
	 * Creates a new instance of BaseAdapter within the given <var>context</var>.
	 * </p>
	 * <p>
	 * If {@link com.wit.android.ui.widget.adapter.annotation.ItemView @ItemView} or
	 * {@link com.wit.android.ui.widget.adapter.annotation.ItemViewHolder @ItemViewHolder}
	 * annotations are presented above subclass of this BaseAdapter, they will be processed here.
	 * </p>
	 *
	 * @param context Context in which will be this adapter used.
	 * @throws java.lang.NullPointerException If the given context is <code>null</code>.
	 */
	public BaseAdapter(Context context) {
		if (context == null) {
			throw new NullPointerException("Invalid context.");
		}

		final Class<?> classOfAdapter = ((Object) this).getClass();
		/**
		 * Process class annotations.
		 */
		// Obtain item view.
		final ItemView itemView = AdapterAnnotations.obtainAnnotationFrom(classOfAdapter, ItemView.class, BaseAdapter.class);
		if (itemView != null) {
			this.mViewRes = itemView.value();
		}
		// Obtain item view holder.
		final ItemViewHolder itemViewHolder = AdapterAnnotations.obtainAnnotationFrom(classOfAdapter, ItemViewHolder.class, BaseAdapter.class);
		if (itemViewHolder != null) {
			this.mClassOfHolder = itemViewHolder.value();
		}

		// Set up.
		this.mContext = context;
		this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mResources = context.getResources();
	}

	/**
	 * Methods =====================================================================================
	 */

	/**
	 * Public --------------------------------------------------------------------------------------
	 */

	/**
	 */
	@Override
	public Parcelable dispatchSaveInstanceState() {
		return onSaveInstanceState();
	}

	/**
	 */
	@Override
	public void dispatchRestoreInstanceState(Parcelable savedState) {
		if (savedState != null) {
			onRestoreInstanceState(savedState);
		}
	}

	/**
	 * <p>
	 * This will also notify the current {@link OnDataSetListener} callback if it is presented.
	 * </p>
	 */
	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
		notifyDataSetChangedInner();
	}

	/**
	 * <p>
	 * This will also notify the current {@link OnDataSetActionListener} callback if it is presented.
	 * </p>
	 */
	@Override
	public void notifyDataSetInvalidated() {
		super.notifyDataSetChanged();
		notifyDataSetInvalidatedInner();
	}

	/**
	 * <p>
	 * Performs optimized algorithm for this method using the <b>Holder</b> pattern.
	 * </p>
	 *
	 * @throws java.lang.NullPointerException If {@link #onCreateView(int, android.view.LayoutInflater, android.view.ViewGroup)}
	 *                                        returns <code>null</code> for the specified <var>position</var>.
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Object viewHolder;
		// Obtain current item view type.
		this.mCurrentViewType = getItemViewType(position);
		if (convertView == null) {
			// Dispatch to create new view.
			convertView = onCreateView(position, mLayoutInflater, parent);
			if (convertView == null) {
				throw new NullPointerException("Created view for position(" + position + ") can not be null.");
			}
			// Resolve holder for the newly created view.
			final Object holder = onCreateViewHolder(position, convertView);
			if (holder != null) {
				convertView.setTag(viewHolder = holder);
			} else {
				viewHolder = convertView;
			}
		} else {
			final Object holder = convertView.getTag();
			viewHolder = holder != null ? holder : convertView;
		}
		// Dispatch to bind view with data.
		onBindView(position, viewHolder);
		return convertView;
	}

	/**
	 * Getters + Setters ---------------------------------------------------------------------------
	 */

	/**
	 * <p>
	 * Returns the context with which was this adapter created.
	 * </p>
	 *
	 * @return Same context as passed during initialization.
	 */
	public Context getContext() {
		return mContext;
	}

	/**
	 * <p>
	 * Returns layout inflater instance provided by the context passed during initialization of this
	 * adapter.
	 * </p>
	 *
	 * @return An instance of LayoutInflater.
	 */
	public LayoutInflater getLayoutInflater() {
		return mLayoutInflater;
	}

	/**
	 * <p>
	 * Returns an application's resources provided by the context passed during initialization of
	 * this adapter.
	 * </p>
	 *
	 * @return An application's resources.
	 */
	public Resources getResources() {
		return mResources;
	}

	/**
	 * <p>
	 * Wrapped {@link android.content.res.Resources#getString(int)} for the current resources.
	 * </p>
	 */
	public String getString(int resId) {
		return (mResources != null) ? mResources.getString(resId) : "";
	}

	/**
	 * <p>
	 * Wrapped {@link android.content.res.Resources#getString(int, Object...)} for the current resources.
	 * </p>
	 */
	public String getString(int resId, Object... args) {
		return (mResources != null) ? mResources.getString(resId, args) : "";
	}

	/**
	 * <p>
	 * Wrapped {@link android.content.res.Resources#getText(int)} for the current resources.
	 * </p>
	 */
	public CharSequence getText(int resId) {
		return (mResources != null) ? mResources.getText(resId) : "";
	}

	/**
	 * <p>
	 * Wrapped {@link android.content.res.Resources#getText(int, java.lang.CharSequence)} for the
	 * current resources.
	 * </p>
	 */
	public CharSequence getText(int resId, CharSequence def) {
		return (mResources != null) ? mResources.getText(resId, def) : "";
	}

	/**
	 * @return By default, the given position as id.
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * <p>
	 * Registers a callback to be invoked when the data set of this adapter has been changed or
	 * invalidated.
	 * </p>
	 *
	 * @param listener Listener callback.
	 */
	public void setOnDataSetListener(OnDataSetListener listener) {
		this.mDataSetListener = listener;
	}

	/**
	 * <p>
	 * Removes the current OnDataSetListener callback.
	 * </p>
	 */
	public void removeOnDataSetListener() {
		this.mDataSetListener = null;
	}

	/**
	 * <p>
	 * Registers a callback to be invoked when there was a specific action performed above the
	 * current data set of this adapter.
	 * </p>
	 *
	 * @param listener Listener callback.
	 */
	public void setOnDataSetActionListener(OnDataSetActionListener listener) {
		this.mDataSetActionListener = listener;
	}

	/**
	 * <p>
	 * Removes the current OnDataSetActionListener callback.
	 * </p>
	 */
	public void removeOnDataSetActionListener() {
		this.mDataSetActionListener = null;
	}

	/**
	 * Protected -----------------------------------------------------------------------------------
	 */

	/**
	 * <p>
	 * Returns a type of an item's view for the currently iterated position.
	 * </p>
	 *
	 * @return View type provided by {@link #getItemViewType(int)} for the currently iterated position.
	 */
	protected int currentViewType() {
		return mCurrentViewType;
	}

	/**
	 * <p>
	 * Inflates a new view hierarchy from the given xml resource.
	 * </p>
	 *
	 * @param resource Resource id of a view to inflate.
	 * @param parent   A parent view, to resolve correct layout params for the newly creating view.
	 * @return The root view of the inflated view hierarchy.
	 * @see android.view.LayoutInflater#inflate(int, android.view.ViewGroup)
	 */
	protected final View inflate(int resource, ViewGroup parent) {
		return mLayoutInflater.inflate(resource, parent, false);
	}

	/**
	 * <p>
	 * Called to notify, that the given <var>action</var> was performed for the specified <var>position</var>.
	 * </p>
	 * <p>
	 * If {@link #onDataSetActionSelected(int, int, Object)} will not process this call, the current
	 * {@link OnDataSetActionListener} will be notified if it is presented.
	 * </p>
	 *
	 * @param action   Action to be dispatched.
	 * @param position The position for which was the given action performed.
	 * @param data     Additional data for the selected action to be dispatched to the listener.
	 */
	protected void notifyDataSetActionSelected(int action, int position, Object data) {
		if (!onDataSetActionSelected(action, position, data)) {
			notifyDataSetActionSelectedInner(action, position, data);
		}
	}

	/**
	 * <p>
	 * Invoked immediately after {@link #notifyDataSetActionSelected(int, int, Object)} was called.
	 * </p>
	 *
	 * @return <code>True</code> to indicate that this event was processed here, otherwise the current
	 * {@link OnDataSetActionListener} will be notified about this event if it is presented.
	 */
	protected boolean onDataSetActionSelected(int action, int position, Object data) {
		return false;
	}

	/**
	 * <p>
	 * Invoked to create a view for an item from the current data set at the specified position.
	 * </p>
	 * <p>
	 * This is invoked only if <var>convertView</var> for the specified <var>position</var> in
	 * {@link #getView(int, android.view.View, android.view.ViewGroup)} was <code>null</code>.
	 * </p>
	 * <p>
	 * <b>Note</b>, that if {@link com.wit.android.ui.widget.adapter.annotation.ItemView @ItemView}
	 * annotation is presented, a resource id provided by this annotation will be used to inflate the
	 * requested view, otherwise implementation of this method is <b>required</b> or exception will
	 * be thrown.
	 * </p>
	 *
	 * @param position Position of the item from the current data set for which should be a new view
	 *                 created.
	 * @param inflater Layout inflater which can be used to inflate the requested view.
	 * @param parent   A parent view, to resolve correct layout params for the newly creating view.
	 * @return New instance of the requested view.
	 * @throws com.wit.android.ui.widget.adapter.MissingUIAnnotationException If there is no @ItemView annotation presented.
	 * @see #inflate(int, android.view.ViewGroup)
	 */
	protected View onCreateView(int position, LayoutInflater inflater, ViewGroup parent) {
		if (mViewRes >= 0) {
			return inflater.inflate(mViewRes, parent, false);
		}
		throw new MissingUIAnnotationException(
				"Can not to create view for position(" + position + ") without resource id. " +
						"No @ItemView annotation presented."
		);
	}

	/**
	 * <p>
	 * Invoked to create a view holder for a view of an item from the current data set at the
	 * specified position.
	 * </p>
	 * <p>
	 * This is invoked only if <var>convertView</var> for the specified <var>position</var> in
	 * {@link #getView(int, android.view.View, android.view.ViewGroup)} was <code>null</code>, so as
	 * view also holder need to be created.
	 * </p>
	 * <p>
	 * <b>Note</b>, that if {@link com.wit.android.ui.widget.adapter.annotation.ItemViewHolder @ItemViewHolder}
	 * annotation is presented, a class provided by this annotation will be used to instantiate the
	 * requested view holder, otherwise <code>null</code> holder will be returned so the view created
	 * by {@link #onCreateView(int, android.view.LayoutInflater, android.view.ViewGroup)} for the
	 * specified position will be passed as holder to {@link #onBindView(int, Object)}.
	 * </p>
	 *
	 * @param position Position of the item from the current data set for which should be a new view
	 *                 holder created.
	 * @param itemView An instance of the same view as obtained from
	 *                 {@link #onCreateView(int, android.view.LayoutInflater, android.view.ViewGroup)}
	 *                 for the specified position.
	 * @return New instance of the requested view holder.
	 * @throws java.lang.IllegalStateException If the class provided by @ItemViewHolder annotation can not
	 *                                         be accessed or does not have an empty public constructor.
	 */
	public Object onCreateViewHolder(int position, View itemView) {
		if (mClassOfHolder != null) {
			ViewHolder holder;
			try {
				holder = mClassOfHolder.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new IllegalStateException(
						"Failed to create view holder from class(" + mClassOfHolder + "). " +
								"Check if this holder class has public access and empty public constructor."
				);
			}
			holder.create(position, itemView);
			return holder;
		}
		// Return null holder, so view created by onCreateView(...) will be passed as holder to onBindView(...).
		return null;
	}

	/**
	 * <p>
	 * Invoked to set up and populate a view of an item from the current data set at the specified
	 * position. This is invoked whenever {@link #getView(int, android.view.View, android.view.ViewGroup)}
	 * is called.
	 * </p>
	 * <p>
	 * <b>Note</b>, that if {@link #onCreateViewHolder(int, android.view.View)} returns <code>null</code>
	 * for the specified <var>position</var> here passed <var>viewHolder</var> will be the view created
	 * by {@link #onCreateView(int, android.view.LayoutInflater, android.view.ViewGroup)} for the
	 * specified position or just recycled view for such a position. This approach can be used, when
	 * a view hierarchy of the specific list item is represented by one custom view, where such a view
	 * represents a holder for all its child views.
	 * </p>
	 * <p>
	 * By default this will try to bind the given <var>viewHolder</var> (if it is instanceof {@link ViewHolder}),
	 * otherwise implementation of this method is <b>required</b> or exception will be thrown.
	 * </p>
	 *
	 * @param position   Position of the item from the current data set of which view to set up.
	 * @param viewHolder An instance of the same holder as provided by {@link #onCreateViewHolder(int, android.view.View)}
	 *                   for the specified position or converted view as holder as described above.
	 * @throws java.lang.IllegalStateException If binding process for the specified position fails.
	 */
	protected void onBindView(int position, Object viewHolder) {
		final Item item = getItem(position);
		if (item == null || !bindViewInner(position, item, viewHolder)) {
			throw new IllegalStateException(
					"Failed to bind view at position(" + position + "). " +
							viewHolder + " is not instance of ViewHolder or item for this position is null."
			);
		}
	}

	/**
	 * <p>
	 * Invoked immediately after {@link #dispatchSaveInstanceState()} was called, to save the current
	 * state of this adapter.
	 * </p>
	 * <p>
	 * If you decide to override this method, do not forget to call <code>super.onSaveInstanceState()</code>
	 * and pass super state obtained from the super to constructor of your {@link BaseSavedState}
	 * implementation with such a parameter to ensure the state of all classes along the chain is saved.
	 * </p>
	 *
	 * @return Return here your implementation of {@link BaseSavedState} if you want to save state of
	 * your adapter, otherwise no implementation of this method is necessary.
	 */
	protected Parcelable onSaveInstanceState() {
		return BaseSavedState.EMPTY_STATE;
	}

	/**
	 * <p>
	 * Called immediately after {@link #dispatchRestoreInstanceState(android.os.Parcelable)} was called
	 * with the valid (not-null) <var>savedState</var> to restore a previous state, (saved in {@link #onSaveInstanceState()}),
	 * of this adapter.
	 * </p>
	 *
	 * @param savedState Before saved state of this adapter.
	 */
	protected void onRestoreInstanceState(Parcelable savedState) {
	}

	/**
	 * Called to dispatch data set change to the current OnDataSetListener callback.
	 */
	@SuppressWarnings("unchecked")
	void notifyDataSetChangedInner() {
		if (mDataSetListener != null) {
			mDataSetListener.onDataSetChanged(this);
		}
	}

	/**
	 * Called to dispatch data set invalidation to the current OnDataSetListener callback.
	 */
	@SuppressWarnings("unchecked")
	void notifyDataSetInvalidatedInner() {
		if (mDataSetListener != null) {
			mDataSetListener.onDataSetInvalidated(this);
		}
	}

	/**
	 * Inner implementation of {@link #notifyDataSetActionSelected(int, int, Object)} to hide such an
	 * implementation.
	 */
	@SuppressWarnings("unchecked")
	void notifyDataSetActionSelectedInner(int action, int position, Object data) {
		if (mDataSetActionListener != null) {
			mDataSetActionListener.onDataSetActionSelected(this, action, position, getItemId(position), data);
		}
	}

	/**
	 * Private -------------------------------------------------------------------------------------
	 */

	/**
	 * Inner implementation of {@link #onBindView(int, Object)} to hide such an implementation.
	 */
	@SuppressWarnings("unchecked")
	boolean bindViewInner(int position, Item item, Object viewHolder) {
		if (viewHolder instanceof ViewHolder) {
			((ViewHolder<Item, BaseAdapter<Item>>) viewHolder).bind(position, item, this);
			return true;
		}
		return false;
	}

	/**
	 * Abstract methods ----------------------------------------------------------------------------
	 */

	/**
	 */
	@Override
	public abstract Item getItem(int position);

	/**
	 * Inner classes ===============================================================================
	 */

	/**
	 * <h4>Class Overview</h4>
	 * <p>
	 * A {@link AbsSavedState} implementation that should be used by inheritance hierarchies of {@link BaseAdapter}
	 * to ensure the state of all classes along the chain is saved.
	 * </p>
	 *
	 * @author Martin Albedinsky
	 */
	public static class BaseSavedState extends AbsSavedState {

		/**
		 * Members =================================================================================
		 */

		/**
		 * <p>
		 * Creator used to create an instance or array of instances of BaseSavedState from {@link android.os.Parcel}.
		 * </p>
		 */
		public static final Creator<BaseSavedState> CREATOR = new Creator<BaseSavedState>() {
			/**
			 */
			@Override
			public BaseSavedState createFromParcel(Parcel source) {
				return new BaseSavedState(source);
			}

			/**
			 */
			@Override
			public BaseSavedState[] newArray(int size) {
				return new BaseSavedState[size];
			}
		};

		/**
		 * Constructors ============================================================================
		 */

		/**
		 * <p>
		 * Creates a new instance BaseSavedState with the given <var>superState</var> to allow
		 * chaining of saved states in {@link #onSaveInstanceState()} and also in
		 * {@link #onRestoreInstanceState(android.os.Parcelable)}.
		 * </p>
		 *
		 * @param superState A super state obtained from <code>super.onSaveInstanceState()</code>
		 *                   within <code>onSaveInstanceState()</code> of a specific {@link BaseAdapter}
		 *                   implementation.
		 */
		protected BaseSavedState(Parcelable superState) {
			super(superState);
		}

		/**
		 * <p>
		 * Called form {@link #CREATOR} to create an instance of BaseSavedState form the given parcel
		 * <var>source</var>.
		 * </p>
		 *
		 * @param source Parcel with data for a new instance.
		 */
		protected BaseSavedState(Parcel source) {
			super(source);
		}
	}

	/**
	 * Interface ===================================================================================
	 */
}
