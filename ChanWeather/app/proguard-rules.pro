# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/chan/Library/android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-optimizationpasses 5
-allowaccessmodification
-dontpreverify

-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose

# Note that if you want to enable optimization, you cannot just
# include optimization flags in your own project configuration file;
# instead you will need to point to the
# "proguard-android-optimize.txt" file instead of this one from your
# project.properties file.

-keepattributes *Annotation*
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService

# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames class * {
    native <methods>;
}

# keep setters in Views so that animations can still work.
# see http://proguard.sourceforge.net/manual/examples.html#beans
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

# We want to keep methods in Activity that could be used in the XML attribute onClick
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclassmembers class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator CREATOR;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

#bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

# 并保留源文件名为"Proguard"字符串，而非原始的类名 并保留行号
-renamesourcefileattribute Proguard
-keepattributes SourceFile, LineNumberTable

# butterknife
-dontwarn butterknife.**
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }
-keepnames class * { @butterknife.InjectView *;}

#保证被注解的域不被变成private
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#event bus
-dontwarn org.greenrobot.**
-keep class org.greenrobot.** {*;}
-keepclasseswithmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}

#个推
-dontwarn com.igexin.**
-keep class com.igexin.**{*;}

#RxJava
-dontwarn rx.**
-keep class rx.** {*;}

#status bar
-dontwarn com.readystatesoftware.**
-keep class com.readystatesoftware.** {*;}

#retrofit
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-dontwarn okio.**

#map输出位置
-printmapping build/outputs/mapping/release/mapping.txt

#图片加载库
-dontwarn com.facebook.**
-keep class com.facebook.** {*;}

#制图
-dontwarn com.github.**
-keep class com.github.** {*;}

-keep class com.chan.chanweather.bean.CityResponse {*;}
-keep class com.chan.chanweather.bean.WeatherResponse {*;}
-keep class com.chan.chanweather.bean.WeatherTypeResponse {*;}

-keep class com.chan.chanweather.db.wrapper.CityWrapper {*;}
-keep class com.chan.chanweather.db.dao.CityDao {*;}

-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
