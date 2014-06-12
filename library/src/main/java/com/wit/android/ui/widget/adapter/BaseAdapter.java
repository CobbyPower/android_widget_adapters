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

import com.wit.android.ui.widget.adapter.annotation.ItemHolder;
import com.wit.android.ui.widget.adapter.annotation.ItemView;

/**
 * <h4>Class Overview</h4>
 * <p>
 * </p>
 *
 * @author Martin Albedinsky
 * @see com.wit.android.ui.widget.adapter.annotation.ItemHolder
 * @see com.wit.android.ui.widget.adapter.annotation.ItemView
 * @see com.wit.android.ui.widget.adapter.BaseSpinnerAdapter
 */
public abstract class BaseAdapter extends android.widget.BaseAdapter implements StateAdapter {

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
	 * Enums =======================================================================================
	 */

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
	 * Layout inflater from the passed mContext.
	 */
	protected final LayoutInflater mLayoutInflater;

	/**
	 * Application resources from the passed mContext.
	 */
	protected final Resources mResources;

	/**
	 * Item view type for the current {@link #getView(int, android.view.View, android.view.ViewGroup)}
	 * iteration.
	 */
	private int mCurrentItemViewType = -1;

	/**
	 *
	 */
	private int mItemView = -1;

	/**
	 *
	 */
	private Class<? extends ItemHolder.ViewHolder> mClassOfHolder = null;

	/**
	 *
	 */
	private OnDataSetListener lDataSetListener = null;

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
	 * Creates new instance of optimized BaseAdapter with the given mContext.
	 * </p>
	 *
	 * @param context Context in which will be this adapter used.
	 */
	public BaseAdapter(Context context) {
		if (context == null) {
			throw new NullPointerException("Invalid mContext.");
		}

		final Class<?> classOfAdapter = ((Object) this).getClass();
		/**
		 * Process class annotations.
		 */
		// Retrieve item view.
		if (classOfAdapter.isAnnotationPresent(ItemView.class)) {
			this.mItemView = classOfAdapter.getAnnotation(ItemView.class).value();
		}
		// Retrieve item view holder.
		if (classOfAdapter.isAnnotationPresent(ItemHolder.class)) {
			this.mClassOfHolder = classOfAdapter.getAnnotation(ItemHolder.class).value();
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
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
		if (lDataSetListener != null) {
			lDataSetListener.onDataSetChanged(this);
		}
	}

	/**
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void notifyDataSetInvalidated() {
		super.notifyDataSetChanged();
		if (lDataSetListener != null) {
			lDataSetListener.onDataSetInvalidated(this);
		}
	}

	/**
	 * <p>
	 * Performs optimized algorithm for this method.
	 * </p>
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Object viewHolder;
		// Obtain current item view type.
		this.mCurrentItemViewType = getItemViewType(position);
		if (convertView == null) {
			// Dispatch to create new view.
			convertView = onCreateView(position, mLayoutInflater, parent);
			if (convertView == null) {
				throw new NullPointerException("Convert view at position(" + position + ") can't be NULL.");
			}
			// Set holder to the new view.
			convertView.setTag(viewHolder = onCreateViewHolder(position, convertView));
		} else {
			viewHolder = convertView.getTag();
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
	 * Returns the mContext with which was this adapter created.
	 * </p>
	 *
	 * @return Same mContext as in initialization.
	 */
	public Context getContext() {
		return mContext;
	}

	/**
	 * <p>
	 * Returns layout inflater instance provided by the mContext passed
	 * during initialization of this adapter.
	 * </p>
	 * <p>
	 * <b>Note</b>, that LayoutInflater is provided only in case, when this
	 * adapter was created with a valid mContext.
	 * </p>
	 *
	 * @return Layout inflater instance.
	 */
	public LayoutInflater getLayoutInflater() {
		return mLayoutInflater;
	}

	/**
	 * <p>
	 * Returns an application's resources provided by the mContext
	 * passed during initialization of this adapter.
	 * </p>
	 * <p>
	 * <b>Note</b>, that Resources are provided only in case, when this
	 * adapter was created with a valid mContext.
	 * </p>
	 *
	 * @return An application's resources.
	 */
	public Resources getResources() {
		return mResources;
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param resID
	 * @return
	 */
	public String getString(int resID) {
		return (mResources != null) ? mResources.getString(resID) : "";
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param resID
	 * @param args
	 * @return
	 */
	public String getString(int resID, Object... args) {
		return (mResources != null) ? mResources.getString(resID, args) : "";
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param resID
	 * @return
	 */
	public CharSequence getText(int resID) {
		return (mResources != null) ? mResources.getText(resID) : "";
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param resID
	 * @param def
	 * @return
	 */
	public CharSequence getText(int resID, CharSequence def) {
		return (mResources != null) ? mResources.getText(resID, def) : "";
	}

	/**
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param listener
	 */
	public void setOnDataSetListener(OnDataSetListener listener) {
		this.lDataSetListener = listener;
	}

	/**
	 * <p>
	 * </p>
	 */
	public void removeOnDataSetListener() {
		this.lDataSetListener = null;
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param resource
	 */
	public void setItemViewResource(int resource) {
		this.mItemView = resource;
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @return
	 */
	public int getItemViewResource() {
		return mItemView;
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param classOfHolder
	 */
	public void setClassOfItemViewHolder(Class<? extends ItemHolder.ViewHolder> classOfHolder) {
		this.mClassOfHolder = classOfHolder;
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @return
	 */
	public Class<? extends ItemHolder.ViewHolder> getClassOfOfItemViewHolder() {
		return mClassOfHolder;
	}

	/**
	 * Protected -----------------------------------------------------------------------------------
	 */

	/**
	 * <p>
	 * Invoked to create a view for the item from this optimized adapter's data set
	 * at the specified position. This is invoked only if the <var>convertView</var> for
	 * {@link android.widget.BaseAdapter#getView(int, android.view.View, android.view.ViewGroup)}
	 * is <code>NULL</code>. You can use the given inflater to inflate requested view or you can
	 * create it manually.
	 * </p>
	 *
	 * @param position The position of the item from this optimized adapter's data set.
	 * @param inflater Layout inflater provided by {@link #getLayoutInflater()}.
	 * @param parent   The parent view, to that will be this view eventually assigned to.
	 * @return Created item view.
	 */
	protected View onCreateView(int position, LayoutInflater inflater, ViewGroup parent) {
		if (mItemView >= 0) {
			return inflate(mItemView, parent);
		}
		throw new IllegalStateException("Can't create view without resource id. Don't you forget to specify it?");
	}

	/**
	 * <p>
	 * Invoked to create view holder for the actually created item view.
	 * </p>
	 *
	 * @param position The position of the item from this optimized adapter's data set.
	 * @param itemView Same type of view as provided by
	 *                 {@link #onCreateView(int, android.view.LayoutInflater, android.view.ViewGroup)}
	 *                 at the specified position.
	 * @return Created holder for the given <var>itemView</var>.
	 */
	public Object onCreateViewHolder(int position, View itemView) {
		if (mClassOfHolder != null) {
			ItemHolder.ViewHolder holder = null;
			try {
				holder = mClassOfHolder.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new IllegalStateException("Failed to create holder from class '" + mClassOfHolder + "'. Check if this holder class is public and has public empty constructor.");
			} finally {
				if (holder != null) {
					holder.onCreate(itemView);
				}
			}
			return holder;
		}
		throw new IllegalStateException("Can't create view holder without class of holder. Don't you forget to specify it?");
	}

	/**
	 * <p>
	 * Returns item's view type for the currently iterated position.
	 * </p>
	 *
	 * @return View type.
	 */
	protected int currentItemViewType() {
		return mCurrentItemViewType;
	}

	/**
	 * <p>
	 * Inflates a new view hierarchy from the given xml resource.
	 * </p>
	 *
	 * @param resource Resource id of a view to inflate.
	 * @param parent
	 * @return The root view of the inflated view hierarchy.
	 * @see android.view.LayoutInflater#inflate(int, android.view.ViewGroup)
	 */
	protected final View inflate(int resource, ViewGroup parent) {
		return mLayoutInflater.inflate(resource, parent, false);
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @return
	 */
	protected Parcelable onSaveInstanceState() {
		return BaseSavedState.EMPTY_STATE;
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param savedState
	 */
	protected void onRestoreInstanceState(Parcelable savedState) {
	}

	/**
	 * Private -------------------------------------------------------------------------------------
	 */

	/**
	 * Abstract methods ----------------------------------------------------------------------------
	 */

	/**
	 * <p>
	 * Invoked to set up and populate a view for an item from this optimized adapter's data set
	 * at the specified <var>position</var>. This is invoked whenever
	 * {@link android.widget.BaseAdapter#getView(int, android.view.View, android.view.ViewGroup)}
	 * on this adapter is called.
	 * </p>
	 *
	 * @param position   The position of item within this optimized adapter's data set.
	 * @param viewHolder Same type of holder as provided by
	 *                   {@link #onCreateViewHolder(int, android.view.View)} for the specified
	 *                   position.
	 */
	protected abstract void onBindView(int position, Object viewHolder);

	/**
	 * Inner classes ===============================================================================
	 */

	/**
	 * <h4>Class Overview</h4>
	 * <p>
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
		 * </p>
		 *
		 * @param source
		 */
		protected BaseSavedState(Parcel source) {
			super(source);
		}

		/**
		 * <p>
		 * </p>
		 *
		 * @param parentState
		 */
		protected BaseSavedState(Parcelable parentState) {
			super(parentState);
		}
	}

	/**
	 * Interface ===================================================================================
	 */

	/**
	 * <h4>Interface Overview</h4>
	 * <p>
	 * </p>
	 *
	 * @param <Adapter>
	 * @author Martin Albedinsky
	 */
	public static interface OnDataSetListener<Adapter extends BaseAdapter> {

		/**
		 * <p>
		 * </p>
		 *
		 * @param adapter
		 */
		public void onDataSetChanged(Adapter adapter);


		/**
		 * <p>
		 * </p>
		 *
		 * @param adapter
		 */
		public void onDataSetInvalidated(Adapter adapter);
	}
}
