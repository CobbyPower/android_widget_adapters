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
import android.view.AbsSavedState;

/**
 * <h4>Class Overview</h4>
 * <p>
 * todo: description
 * </p>
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
	 * Constants ===================================================================================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = AdapterModule.class.getSimpleName();

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
	 * An instance of the adapter to which is this module attached.
	 */
	ModuleAdapter mAdapter;

	/**
	 * Arrays --------------------------------------------------------------------------------------
	 */

	/**
	 * Booleans ------------------------------------------------------------------------------------
	 */

	/**
	 * Flag indicating whether the adapter to which is this module attached, should be notified in case,
	 * that its data set should be changed due to changes made by this module.
	 */
	private boolean bNotificationEnabled = true;

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
	 * Called to save the current state of this module.
	 * </p>
	 *
	 * @return Saved state of this module or {@link BaseSavedState#EMPTY_STATE} if this module does
	 * not need to save its state.
	 */
	public Parcelable dispatchSaveInstanceState() {
		return onSaveInstanceState();
	}

	/**
	 * <p>
	 * Called to restore a previous state, saved by {@link #dispatchSaveInstanceState()}, of this
	 * module.
	 * </p>
	 *
	 * @param savedState Should be the same state as obtained by {@link #dispatchSaveInstanceState()}
	 *                   before.
	 */
	public void dispatchRestoreInstanceState(Parcelable savedState) {
		if (savedState != null) {
			onRestoreInstanceState(savedState);
		}
	}

	/**
	 * <p>
	 * Called to attach this module to the given <var>adapter</var>.
	 * </p>
	 *
	 * @param adapter An instance of the adapter by which will be this module used.
	 */
	public final void dispatchAttachToAdapter(ModuleAdapter adapter) {
		onAttachedToAdapter(mAdapter = adapter);
	}

	/**
	 * <p>
	 * Returns flag indicating whether this module requires to be its state staved or not.
	 * </p>
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
	 * <p>
	 * Returns flag indicating whether the adapter to which is this module attached should be notified
	 * in case, that its data set should be changed due to changes made by this module.
	 * </p>
	 *
	 * @return <code>True</code> when adapter auto-notification is enabled, <code>false</code> otherwise.
	 * @see #setAdapterNotificationEnabled(boolean)
	 */
	public boolean isAdapterNotificationEnabled() {
		return bNotificationEnabled;
	}

	/**
	 * <p>
	 * Sets flag indicating whether the adapter to which is this module attached should be notified in case,
	 * that its data set should be changed due to changes made by this module.
	 * </p>
	 *
	 * @param enabled <code>True</code> to enable adapter auto-notification, <code>false</code> to
	 *                disable it.
	 * @see #isAdapterNotificationEnabled()
	 */
	public void setAdapterNotificationEnabled(boolean enabled) {
		this.bNotificationEnabled = enabled;
	}

	/**
	 * Protected -----------------------------------------------------------------------------------
	 */

	/**
	 * <p>
	 * Invoked immediately after {@link #dispatchSaveInstanceState()} was called, to save the current
	 * state of this module.
	 * </p>
	 * <p>
	 * If you decide to override this method, do not forget to call <code>super.onSaveInstanceState()</code>
	 * and pass super state obtained from the super to constructor of your {@link BaseSavedState}
	 * implementation with such a parameter to ensure the state of all classes along the chain is saved.
	 * </p>
	 *
	 * @return Return here your implementation of {@link BaseSavedState} if you want to save state of
	 * your module, otherwise no implementation of this method is necessary.
	 */
	protected Parcelable onSaveInstanceState() {
		return BaseSavedState.EMPTY_STATE;
	}

	/**
	 * <p>
	 * Called immediately after {@link #dispatchRestoreInstanceState(android.os.Parcelable)} was called
	 * with the valid (not-null) <var>savedState</var> to restore a previous state, (saved in {@link #onSaveInstanceState()}),
	 * of this module.
	 * </p>
	 *
	 * @param savedState Before saved state of this module.
	 */
	protected void onRestoreInstanceState(Parcelable savedState) {
	}

	/**
	 * <p>
	 * Invoked immediately after {@link #dispatchAttachToAdapter(com.wit.android.ui.widget.adapter.module.AdapterModule.ModuleAdapter)}.
	 * </p>
	 *
	 * @param adapter An instance of the adapter to which was this module right now attached.
	 */
	protected void onAttachedToAdapter(ModuleAdapter adapter) {
	}

	/**
	 * <p>
	 * Notifies the adapter to which is this module attached by {@link ModuleAdapter#notifyDataSetChanged()}.
	 * </p>
	 * <p>
	 * This should be called whenever the data set of the attached adapter should be reloaded due
	 * to changes made by this module.
	 * </p>
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
	 * Abstract methods ----------------------------------------------------------------------------
	 */

	/**
	 * Inner classes ===============================================================================
	 */

	/**
	 * <h4>Class Overview</h4>
	 * <p>
	 * A {@link AbsSavedState} implementation that should be used by inheritance hierarchies of
	 * {@link AdapterModule} to ensure the state of all classes along the chain is saved.
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
		 *                   within <code>onSaveInstanceState()</code> of a specific {@link AdapterModule}
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

	/**
	 * <h4>Interface Overview</h4>
	 * <p>
	 * Base interface for "module based" adapter which want to use one of {@link AdapterModule} implementations.
	 * </p>
	 *
	 * @author Martin Albedinsky
	 */
	public static interface ModuleAdapter {

		/**
		 * <p>
		 * See {@link android.widget.BaseAdapter#notifyDataSetChanged()}.
		 * </p>
		 */
		public void notifyDataSetChanged();

		/**
		 * <p>
		 * See {@link android.widget.BaseAdapter#getCount()}.
		 * </p>
		 */
		public int getCount();
	}
}
