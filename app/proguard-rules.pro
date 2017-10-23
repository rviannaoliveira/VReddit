# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Retrofit 2.X
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

#Fabric
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**
-keepattributes *Annotation*

#Picass && OKHTTP && OKIO
-dontwarn com.squareup.okhttp.**
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote okhttp3.**, okio.**, retrofit2.**, pl.droidsonroids.**

# java.nio.file.* usage which cannot be used at runtime. Animal sniffer annotation.
-dontwarn okio.**

# JDK 7-only method which is @hide on Android. Animal sniffer annotation.
-dontwarn okio.DeflaterSink

#GSON
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

#Stetho
-keep class com.facebook.stetho.** { *; }
-dontwarn com.facebook.stetho.**

#Timber
-dontwarn org.jetbrains.annotations.NonNls

#ALL
-ignorewarnings

-keep class * {
    public private *;
}
