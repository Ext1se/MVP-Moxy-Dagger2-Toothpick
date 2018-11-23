package com.elegion.test.behancer;

import android.app.Application;

import com.elegion.test.behancer.common.BaseView;
import com.elegion.test.behancer.di.ScopesName;
import com.elegion.test.behancer.di.module.AppModule;
import com.elegion.test.behancer.di.module.NetworkModule;
import com.elegion.test.behancer.di.module.SingletonProfileFragmentModule;
import com.elegion.test.behancer.di.module.SingletonProjectsFragmentModule;
import com.elegion.test.behancer.di.module.ViewModule;
import com.elegion.test.behancer.helper_picasso.PicassoHelper;

import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.configuration.Configuration;
import toothpick.registries.FactoryRegistry;
import toothpick.registries.FactoryRegistryLocator;
import toothpick.registries.MemberInjectorRegistry;
import toothpick.registries.MemberInjectorRegistryLocator;
import toothpick.smoothie.module.SmoothieApplicationModule;

public class AppDelegate extends Application {

    private static Scope sAppScope;
    private static Scope sSingletonProjectsFragmentScope;
    private static Scope sSingletonProfileFragmentScope;

    @Override
    public void onCreate() {
        super.onCreate();
        PicassoHelper.createPicasso(this);

        Configuration configuration;
        if (BuildConfig.DEBUG) {
            configuration = Configuration.forDevelopment().preventMultipleRootScopes();
        } else {
            configuration = Configuration.forProduction().disableReflection();
        }
        Toothpick.setConfiguration(configuration);
        //MemberInjectorRegistryLocator.setRootRegistry(new com.elegion.test.behancer.MemberInjectorRegistry());
        //FactoryRegistryLocator.setRootRegistry(new com.elegion.test.behancer.FactoryRegistry());

        //sAppScope = Toothpick.openScope(AppDelegate.class);
        sAppScope = Toothpick.openScope(ScopesName.APP_SCOPE);
        sAppScope.installModules(new SmoothieApplicationModule(this), new AppModule(this), new NetworkModule());
    }

    public static Scope getAppScope() {
        return sAppScope;
    }

    public static Scope createSingletonProjectsFragmentScope(BaseView view) {
        if (sSingletonProjectsFragmentScope == null) {
            sSingletonProjectsFragmentScope = Toothpick.openScopes(ScopesName.APP_SCOPE,
                    ScopesName.SINGLETON_PROJECTS_FRAGMENT_SCOPE);
            sSingletonProjectsFragmentScope.installModules(
                    new ViewModule(view),
                    new SingletonProjectsFragmentModule());
        }
        return sSingletonProjectsFragmentScope;
    }

    public static void clearSingletonProjectsFragmentScope() {
        Toothpick.closeScope(ScopesName.SINGLETON_PROJECTS_FRAGMENT_SCOPE);
        sSingletonProjectsFragmentScope = null;
    }


    public static Scope createSingletonProfileFragmentScope(BaseView view) {
        if (sSingletonProfileFragmentScope == null) {
            sSingletonProfileFragmentScope = Toothpick.openScopes(ScopesName.APP_SCOPE,
                    ScopesName.SINGLETON_PROFILE_FRAGMENT_SCOPE);
            sSingletonProfileFragmentScope.installModules(
                    new ViewModule(view),
                    new SingletonProfileFragmentModule());
        }
        return sSingletonProfileFragmentScope;
    }

    public static void clearSingletonProfileFragmentScope() {
        Toothpick.closeScope(ScopesName.SINGLETON_PROFILE_FRAGMENT_SCOPE);
        sSingletonProfileFragmentScope = null;
    }

}
