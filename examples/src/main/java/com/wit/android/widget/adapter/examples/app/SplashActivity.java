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
package com.wit.android.widget.adapter.examples.app;

import com.wit.android.examples.annotations.ExDelay;
import com.wit.android.examples.annotations.ExHomeActivity;
import com.wit.android.examples.annotations.ExLogo;
import com.wit.android.examples.app.ExSplashActivity;
import com.wit.android.widget.adapter.examples.R;

/**
 * <p>
 * Simple splash activity with logo.
 * </p>
 *
 * @author Martin Albedinsky
 */
@ExDelay(1000)
@ExLogo(R.drawable.ic_logo)
@ExHomeActivity(HomeActivity.class)
public class SplashActivity extends ExSplashActivity {}
