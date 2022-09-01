/*
 * Copyright (c) 2015-present, Horcrux.
 * All rights reserved.
 *
 * This source code is licensed under the MIT-style license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.myproject;

import android.widget.Toast;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.module.annotations.ReactModule;
import javax.annotation.Nonnull;

@ReactModule(name = MyNativeModule.NAME)
class MyNativeModule extends ReactContextBaseJavaModule {
  MyNativeModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  public static final String NAME = "MyNativeModule";

  @Nonnull
  @Override
  public String getName() {
    return NAME;
  }

  @SuppressWarnings("unused")
  @ReactMethod
  public void toast(String message) {
    UiThreadUtil.runOnUiThread(
      new Runnable() {
        @Override
        public void run() {
          Toast.makeText(getReactApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
      });
  }
}
