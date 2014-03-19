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
package com.wit.android.widget.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wit.android.widget.adapter.annotation.ItemHolder;
import com.wit.android.widget.adapter.annotation.ItemView;

/**
 * <h4>Class Overview</h4>
 * <p>
 * </p>
 *
 * @author Martin Albedinsky
 * @see com.wit.android.widget.adapter.annotation.ItemHolder
 * @see com.wit.android.widget.adapter.annotation.ItemView
 * @see BaseSpinnerAdapter
 */
public abstract class BaseAdapter extends android.widget.BaseAdapter implements AdapterOptimizer.OptimizedAdapter {

	/**
	 * Constants =============================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = BaseAdapter.class.getSimpleName();

	/**
	 * Flag indicating whether the debug output trough log-cat is enabled or not.
	 */
	// private static final boolean DEBUG = true;

	/**
	 * Flag indicating whether the output for user trough log-cat is enabled or not.
	 */
	// private static final boolean USER_LOG = true;

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
	 * Optimization manager for getView() method.
	 */
	private final AdapterOptimizer OPTIMIZER = new AdapterOptimizer(this);

	/**
	 * Context in which will be this adapter used.
	 */
	protected final Context mContext;

	/**
	 * Layout inflater from the passed context.
	 */
	protected final LayoutInflater mLayoutInflater;

	/**
	 * Application resources from the passed context.
	 */
	protected final Resources mResources;

	/**
	 *
	 */
	private int mItemView = -1;

	/**
	 *
	 */
	private Class<? extends ItemHolder.ViewHolder> mClassOfHolder = null;

	/**
	 * Listeners -----------------------------
	 */

	/**
	 *
	 */
	private OnDataSetListener lDataSetListener = null;

	/**
	 * Arrays --------------------------------
	 */

	/**
	 * Booleans ------------------------------
	 */

	/**
	 * Constructors ==========================
	 */

	/**
	 * <p>
	 * Creates new instance of optimized simple BaseAdapter.
	 * </p>
	 */
	public BaseAdapter() {
		this(null);
	}

	/**
	 * <p>
	 * Creates new instance of optimized BaseAdapter with the given
	 * context.
	 * </p>
	 *
	 * @param context Context in which will be this adapter used.
	 */
	public BaseAdapter(Context context) {
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
		if (context != null) {
			this.mContext = context;
			this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			this.mResources = context.getResources();
		} else {
			this.mContext = null;
			this.mLayoutInflater = null;
			this.mResources = null;
		}
	}

	/**
	 * Methods ===============================
	 */

	/**
	 * Public --------------------------------
	 */

	/**
	 *
	 * @return
	 */
	public Parcelable dispatchSaveInstanceState() {
		return onSaveInstanceState();
	}

	/**
	 * <p>
	 * </p>
	 *
	 * @param savedState
	 */
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
		return OPTIMIZER.performOptimizedGetView(position, convertView, parent);
	}

	/**
	 */
	@Override
	public View onCreateView(int position, LayoutInflater inflater, ViewGroup parent) {
		if (mItemView >= 0) {
			return inflate(mItemView);
		}
		throw new IllegalStateException("Can't create view without resource id. Don't you forget to specify it?");
	}

	/**
	 */
	@Override
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
	 * Getters + Setters ---------------------
	 */

	/**
	 * <p>
	 * Returns the context with which was this adapter created.
	 * </p>
	 *
	 * @return Same context as in initialization.
	 */
	public Context getContext() {
		return mContext;
	}

	/**
	 * <p>
	 * Returns layout inflater instance provided by the context passed
	 * during initialization of this adapter.
	 * </p>
	 * <p>
	 * <b>Note</b>, that LayoutInflater is provided only in case, when this
	 * adapter was created with a valid context.
	 * </p>
	 *
	 * @return Layout inflater instance.
	 */
	public LayoutInflater getLayoutInflater() {
		return mLayoutInflater;
	}

	/**
	 * <p>
	 * Returns an application's resources provided by the context
	 * passed during initialization of this adapter.
	 * </p>
	 * <p>
	 * <b>Note</b>, that Resources are provided only in case, when this
	 * adapter was created with a valid context.
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
	 * Protected -----------------------------
	 */

	/**
	 * <p>
	 * Returns item's view type for the currently iterated position.
	 * </p>
	 *
	 * @return View type.
	 * @see AdapterOptimizer#getCurrentItemViewType()
	 */
	protected int currentItemViewType() {
		return OPTIMIZER.getCurrentItemViewType();
	}

	/**
	 * <p>
	 * Inflates a new view hierarchy from the given xml resource.
	 * </p>
	 *
	 * @param resource Resource id of a view to inflate.
	 * @return The root view of the inflated view hierarchy.
	 * @see android.view.LayoutInflater#inflate(int, android.view.ViewGroup)
	 */
	protected final View inflate(int resource) {
		return mLayoutInflater.inflate(resource, null);
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
	 * Private -------------------------------
	 */

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
	public static class BaseSavedState implements Parcelable {

		/**
		 * Members ===============================
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
		 *
		 */
		private static final BaseSavedState EMPTY_STATE = new BaseSavedState();

		/**
		 *
		 */
		private Parcelable parentState;

		/**
		 * Constructors ==========================
		 */

		/**
		 * <p>
		 * </p>
		 */
		protected BaseSavedState() {
			this((Parcelable) null);
		}

		/**
		 * <p>
		 * </p>
		 *
		 * @param source
		 */
		protected BaseSavedState(Parcel source) {
			// FIXME: use correct class loader
			this.parentState = source.readParcelable(null);
		}

		/**
		 * <p>
		 * </p>
		 *
		 * @param parentState
		 */
		protected BaseSavedState(Parcelable parentState) {
			this.parentState = parentState;
		}

		/**
		 * Methods ===============================
		 */

		/**
		 */
		@Override
		public int describeContents() {
			return 0;
		}

		/**
		 */
		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeParcelable(parentState, flags);
		}

		/**
		 * <p>
		 * </p>
		 *
		 * @return
		 */
		public Parcelable getParentState() {
			return parentState;
		}
	}

	/**
	 * Interface =============================
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
