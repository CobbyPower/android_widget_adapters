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
import android.support.annotation.Nullable;
import android.view.AbsSavedState;

/**
 * <h4>Class Overview</h4>
 * todo: description
 * <h5>State saving</h5>
 * <pre>
 * public class MyModule extends AdapterModule {
 *
 *     // ...
 *
 *     &#64;Override
 *     protected Parcelable onSaveInstanceState() {
 *         final MyModuleState state = new MyModuleState(super.onSaveInstanceState());
 *
 *         // ...
 *         // Pass here all data of this module which need to be saved to the state.
 *         // ...
 *
 *         return state;
 *     }
 *
 *     &#64;Override
 *     protected void onRestoreInstanceState(Parcelable savedState) {
 *          if (!(savedState instanceof MyModuleState)) {
 *              // Passed savedState is not our state, let super to process it.
 *              super.onRestoreInstanceState(savedState);
 *              return;
 *          }
 *
 *          final MyModuleState state = (MyModuleState) savedState;
 *          // Pass superState to super to process it.
 *          super.onRestoreInstanceState(savedState.getSuperState());
 *
 *          // ...
 *          // Set here all data of this module which need to be restored from the savedState.
 *          // ...
 *     }
 *
 *     // ...
 *
 *     // Implementation of BaseSavedState for this module.
 *     static class MyModuleState extends BaseSavedState {
 *
 *         // Each implementation of saved state need to have its own CREATOR provided.
 *         public static final Creator&lt;MyModuleState&gt; CREATOR = new Creator&lt;&gt; {
 *
 *              &#64;Override
 *              public MyModuleState createFromParcel(Parcel source) {
 *                  return new MyModuleState(source);
 *              }
 *
 *              &#64;Override
 *              public MyModuleState[] newArray(int size) {
 *                  return new MyModuleState[size];
 *              }
 *         }
 *
 *         MyModuleState(Parcel source) {
 *              super(source);
 *              // Restore here state's data.
 *         }
 *
 *         // Constructor used to chain the state of inheritance hierarchies.
 *         MyModuleState(Parcelable superState) {
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
 * @author Martin Albedinsky
 */
public abstract class AdapterModule {

	/**
	 * Interface ===================================================================================
	 */

	/**
	 * <h4>Interface Overview</h4>
	 * Base interface for "module based" adapter which want to use one of {@link AdapterModule} implementations.
	 *
	 * @author Martin Albedinsky
	 */
	public static interface ModuleAdapter {

		/**
		 * See {@link android.widget.BaseAdapter#notifyDataSetChanged()}.
		 */
		public void notifyDataSetChanged();

		/**
		 * See {@link android.widget.BaseAdapter#getCount()}.
		 */
		public int getCount();
	}

	/**
	 * Constants ===================================================================================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = "AdapterModule";

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
	 * An instance of the adapter to which is this module attached.
	 */
	ModuleAdapter mAdapter;

	/**
	 * Flag indicating whether the adapter to which is this module attached, should be notified in case,
	 * that its data set should be changed due to changes made by this module.
	 */
	private boolean mNotificationEnabled = true;

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
	 * Called to save the current state of this module.
	 *
	 * @return Saved state of this module or {@link BaseSavedState#EMPTY_STATE} if this module does
	 * not need to save its state.
	 */
	@NonNull
	public final Parcelable dispatchSaveInstanceState() {
		return onSaveInstanceState();
	}

	/**
	 * Called to restore a previous state, saved by {@link #dispatchSaveInstanceState()}, of this
	 * module.
	 *
	 * @param savedState Should be the same state as obtained by {@link #dispatchSaveInstanceState()}
	 *                   before.
	 */
	public final void dispatchRestoreInstanceState(@NonNull Parcelable savedState) {
		onRestoreInstanceState(savedState);
	}

	/**
	 * Called to attach this module to the given <var>adapter</var>.
	 *
	 * @param adapter An instance of the adapter by which will be this module used.
	 */
	public final void dispatchAttachToAdapter(@NonNull ModuleAdapter adapter) {
		onAttachedToAdapter(mAdapter = adapter);
	}

	/**
	 * Returns flag indicating whether this module requires to be its state staved or not.
	 *
	 * @return <code>True</code> to call {@link #dispatchSaveInstanceState()} up on this module,
	 * <code>false</code> otherwise.
	 */
	public boolean requiresStateSaving() {
		return false;
	}

	/**
	 * Getters + Setters ---------------------------------------------------------------------------
	 */

	/**
	 * Returns flag indicating whether the adapter to which is this module attached should be notified
	 * in case, that its data set should be changed due to changes made by this module.
	 *
	 * @return <code>True</code> when adapter auto-notification is enabled, <code>false</code> otherwise.
	 * @see #setAdapterNotificationEnabled(boolean)
	 */
	public boolean isAdapterNotificationEnabled() {
		return mNotificationEnabled;
	}

	/**
	 * Sets flag indicating whether the adapter to which is this module attached should be notified in case,
	 * that its data set should be changed due to changes made by this module.
	 *
	 * @param enabled <code>True</code> to enable adapter auto-notification, <code>false</code> to
	 *                disable it.
	 * @see #isAdapterNotificationEnabled()
	 */
	public void setAdapterNotificationEnabled(boolean enabled) {
		this.mNotificationEnabled = enabled;
	}

	/**
	 * Protected -----------------------------------------------------------------------------------
	 */

	/**
	 * Invoked immediately after {@link #dispatchSaveInstanceState()} was called, to save the current
	 * state of this module.
	 * <p/>
	 * If you decide to override this method, do not forget to call <code>super.onSaveInstanceState()</code>
	 * and pass super state obtained from the super to constructor of your {@link BaseSavedState}
	 * implementation with such a parameter to ensure the state of all classes along the chain is saved.
	 *
	 * @return Return here your implementation of {@link BaseSavedState} if you want to save state of
	 * your module, otherwise no implementation of this method is necessary.
	 */
	@NonNull
	protected Parcelable onSaveInstanceState() {
		return BaseSavedState.EMPTY_STATE;
	}

	/**
	 * Called immediately after {@link #dispatchRestoreInstanceState(android.os.Parcelable)} was called,
	 * to restore a previous state, (saved in {@link #onSaveInstanceState()}),
	 * of this module.
	 *
	 * @param savedState Before saved state of this module.
	 */
	protected void onRestoreInstanceState(@NonNull Parcelable savedState) {
	}

	/**
	 * Invoked immediately after {@link #dispatchAttachToAdapter(com.wit.android.ui.widget.adapter.module.AdapterModule.ModuleAdapter)}.
	 *
	 * @param adapter An instance of the adapter to which was this module right now attached.
	 */
	protected void onAttachedToAdapter(@NonNull ModuleAdapter adapter) {
	}

	/**
	 * Notifies the adapter to which is this module attached by {@link ModuleAdapter#notifyDataSetChanged()}.
	 * <p/>
	 * This should be called whenever the data set of the attached adapter should be reloaded due
	 * to changes made by this module.
	 *
	 * @see #isAdapterNotificationEnabled()
	 */
	protected final void notifyAdapter() {
		if (isAdapterNotificationEnabled()) {
			mAdapter.notifyDataSetChanged();
		}
	}

	/**
	 * Private -------------------------------------------------------------------------------------
	 */

	/**
	 * Inner classes ===============================================================================
	 */

	/**
	 * <h4>Class Overview</h4>
	 * A {@link AbsSavedState} implementation that should be used by inheritance hierarchies of
	 * {@link AdapterModule} to ensure the state of all classes along the chain is saved.
	 *
	 * @author Martin Albedinsky
	 */
	public static class BaseSavedState extends AbsSavedState {

		/**
		 * Members =================================================================================
		 */

		/**
		 * Creator used to create an instance or array of instances of BaseSavedState from {@link android.os.Parcel}.
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
		 * Creates a new instance BaseSavedState with the given <var>superState</var> to allow
		 * chaining of saved states in {@link #onSaveInstanceState()} and also in
		 * {@link #onRestoreInstanceState(android.os.Parcelable)}.
		 *
		 * @param superState A super state obtained from <code>super.onSaveInstanceState()</code>
		 *                   within <code>onSaveInstanceState()</code> of a specific {@link AdapterModule}
		 *                   implementation.
		 */
		protected BaseSavedState(@Nullable Parcelable superState) {
			super(superState);
		}

		/**
		 * Called form {@link #CREATOR} to create an instance of BaseSavedState form the given parcel
		 * <var>source</var>.
		 *
		 * @param source Parcel with data for a new instance.
		 */
		protected BaseSavedState(@NonNull Parcel source) {
			super(source);
		}
	}
}
