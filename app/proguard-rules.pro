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
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


-keep class birthdaygreetings.birthdayframe.greetingswishes.model.RootNew**  { *; }
-keep class birthdaygreetings.birthdayframe.greetingswishes.model.Agespecific  { *; }
-keep class birthdaygreetings.birthdayframe.greetingswishes.model.Belated  { *; }
-keep class birthdaygreetings.birthdayframe.greetingswishes.model.Birthdaygif  { *; }
-keep class birthdaygreetings.birthdayframe.greetingswishes.model.BirthdayNamePoem  { *; }
-keep class birthdaygreetings.birthdayframe.greetingswishes.model.CreateCards  { *; }
-keep class birthdaygreetings.birthdayframe.greetingswishes.model.Family  { *; }

-keep class birthdaygreetings.birthdayframe.greetingswishes.model.Happybirthday  { *; }
-keep class birthdaygreetings.birthdayframe.greetingswishes.model.Inspiration  { *; }
-keep class birthdaygreetings.birthdayframe.greetingswishes.model.Love  { *; }
-keep class birthdaygreetings.birthdayframe.greetingswishes.model.NameOnBirthdayCake  { *; }
-keep class birthdaygreetings.birthdayframe.greetingswishes.model.Religiou  { *; }
-keep class birthdaygreetings.birthdayframe.greetingswishes.model.Warmmessage  { *; }


-keep class com.sm.newadlib.model.* {*;}
-dontwarn retrofit2.**
-keep class retrofit2.* { *; }


-keep public class com.google.android.gms.** { public protected *; }
-keepattributes Annotation



-dontwarn com.unity3d.services.ads.**
-dontwarn com.google.firebase.**
-dontwarn okhttp3.internal.platform.**


-keepattributes Annotation