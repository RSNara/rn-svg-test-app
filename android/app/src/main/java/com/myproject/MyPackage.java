package com.myproject;

import com.facebook.react.ReactPackage;
import com.facebook.react.TurboReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.module.annotations.ReactModuleList;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import com.facebook.react.uimanager.ViewManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Nonnull;

@ReactModuleList(
  nativeModules = {
    MyNativeModule.class,
  })
public class MyPackage extends TurboReactPackage implements ViewManagerOnDemandReactPackage {

  @Nonnull
  @Override
  public List<ViewManager> createViewManagers(@Nonnull ReactApplicationContext reactContext) {
    return Collections.emptyList();
  }

  @Override
  public NativeModule getModule(String name, @Nonnull ReactApplicationContext reactContext) {
    switch (name) {
      case MyNativeModule.NAME:
        return new MyNativeModule(reactContext);
      default:
        return null;
    }
  }

  @Override
  Collection<String> getViewManagerNames(ReactApplicationContext reactContext) {
    return Collections.emptyList();
  }

  @Override
  ViewManager createViewManager(ReactApplicationContext reactContext, String viewManagerName) {
    return null;
  }

  @Override
  public ReactModuleInfoProvider getReactModuleInfoProvider() {
    try {
      Class<?> reactModuleInfoProviderClass =
          Class.forName("com.myproject.MyPackage$$ReactModuleInfoProvider");
      return (ReactModuleInfoProvider) reactModuleInfoProviderClass.newInstance();
    } catch (ClassNotFoundException e) {
      // ReactModuleSpecProcessor does not run at build-time. Create this ReactModuleInfoProvider by hand.
      return () -> {
        final Map<String, ReactModuleInfo> reactModuleInfoMap = new HashMap<>();

        Class<? extends NativeModule>[] moduleList =
            new Class[] {
              MyNativeModule.class,
            };

        for (Class<? extends NativeModule> moduleClass : moduleList) {
          ReactModule reactModule = moduleClass.getAnnotation(ReactModule.class);

          reactModuleInfoMap.put(
              reactModule.name(),
              new ReactModuleInfo(
                  reactModule.name(),
                  moduleClass.getName(),
                  reactModule.canOverrideExistingModule(),
                  reactModule.needsEagerInit(),
                  reactModule.hasConstants(),
                  reactModule.isCxxModule(),
                  TurboModule.class.isAssignableFrom(moduleClass)));
        }

        return reactModuleInfoMap;
      };
    } catch (InstantiationException | IllegalAccessException e) {
      throw new RuntimeException(
          "No ReactModuleInfoProvider for MyPackage$$ReactModuleInfoProvider", e);
    }
  }

  @SuppressWarnings("unused")
  public List<Class<? extends JavaScriptModule>> createJSModules() {
    return Collections.emptyList();
  }
}