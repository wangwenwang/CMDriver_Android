# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\Downloads\developmentTools\Android\AndroidSDK/tools/proguard/proguard-android.txt
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


-dontwarn com.igexin.**
-keep class com.igexin.** {*; }
-keep class org.json.** { *; }
-dontwarn  com.marswin89.marsdaemon.**
-keep class com.marswin89.marsdaemon.**
-keep class com.marswin89.marsdaemon.**{*;}
-keep class com.baidu.** {*;}
-keep class vi.com.** {*;}
-dontwarn com.baidu.**
-keep class com.getbase.floatingactionbutton.**{*;}
-keep class uk.co.senab.photoview.**{*;}
-keep class com.kaidongyuan.app.kdydriver.bean.**{*;}
-keep class com.kaidongyuan.app.kdydriver.bean.order.Order
-keep class com.kaidongyuan.app.kdydriver.ui.activity.OrderDetailActivity
-keep class com.kaidongyuan.app.kdydriver.adapter.OrderDetailsSimpleAdapter