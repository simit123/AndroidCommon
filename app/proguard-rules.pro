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
#-renamesourcefileattribute SourceFile、
# ------------------------------基本指令区---------------------------------
-optimizationpasses 5 #指定压缩级别
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/* #混淆时采用的算法
-verbose #打印混淆的详细信息
-dontoptimize #关闭优化
-dontpreverify #不进行预校验，可加快混淆速度
-keepattributes *Annotation* #保留注解中的参数
-keepattributes Signature  # 避免混淆泛型, 这在JSON实体映射时非常重要
-ignorewarnings # 屏蔽警告
-dontskipnonpubliclibraryclasses  # 指定不去忽略非公共的库类(不跳过library中的非public的类)
-dontskipnonpubliclibraryclassmembers  # 指定不去忽略包可见的库类的成员
-keepattributes SourceFile,LineNumberTable # 抛出异常时保留代码行号
-printmapping proguardMapping.txt

# -------------------------------基本指令区--------------------------------


#---------------------------------默认保留区---------------------------------
#继承activity,application,service,broadcastReceiver,contentprovider....不进行混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.support.multidex.MultiDexApplication
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep class android.support.** {*;}


-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService
#表示不混淆上面声明的类，最后这两个类我们基本也用不上，是接入Google原生的一些服务时使用的。


 #不混淆View中的set***() 和 get***()方法 以保证属性动画正常工作  某个类中的某个方法不混淆
 #自定义View的set get方法 和 构造方法不混淆
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
#这个主要是在layout 中写的onclick方法android:onclick="onClick"，不进行混淆
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

#实现Serializable接口的类重写父类方法保留
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
# 保留R文件中所有静态字段，以保证正确找到每个资源的ID
-keepclassmembers class **.R$* {
    public static <fields>;
}


-keepclassmembers class * {
    void *(*Event);
}

#保留枚举类中的values和valueOf方法
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#保留Parcelable实现类中的Creator字段，以保证Parcelable机制正常工作
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
#// natvie 方法不混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

#保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

#不混淆包含native方法的类的类名以及native方法名
-keepclasseswithmembernames class * {
                native <methods>;
}


#对含有反射类的处理
-dontwarn com.suchengkeji.android.confusiondemo.md.**
-keep class com.suchengkeji.android.confusiondemo.md.** { *; }

#--------------------------------默认保留区--------------------------------------------


#----------------------------- WebView(项目中没有可以忽略) -----------------------------
#
#webView需要进行特殊处理
-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
   public *;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, jav.lang.String);
}
#在app中与HTML5的JavaScript的交互进行特殊处理
#我们需要确保这些js要调用的原生方法不能够被混淆，于是我们需要做如下处理：
-keepclassmembers class com.ljd.example.JSInterface {
    <methods>;
}

#----------------------------- WebView(项目中没有可以忽略) -----------------------------


#----------------------------- 实体类不可混淆 ------------------------------------------

#----------------------------- 添加实体类混淆规则 ---------------------------------------

#----------------------------- 实体类不可混淆 ------------------------------------------


#----------------------------- 第三方类库 ------------------------------------------

#----------------------------------retrofit start--------------------------------------------
# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

#----------------------------------retrofit end--------------------------------------------

#----------------------------------okhttp start-----------------------------------------------------
# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform
#-------------------------------------okhttp end----------------------------------------------------
#--------------------------------------okio start---------------------------------------------------
# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*
#--------------------------------------okio end-----------------------------------------------------
#--------------------------------------rxjava start-------------------------------------------------
-dontwarn java.util.concurrent.Flow*
#--------------------------------------rxjava end---------------------------------------------------
#--------------------------------------eventBus start-----------------------------------------------
-keepattributes *Annotation*
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}
#--------------------------------------eventBus end-------------------------------------------------

#--------------------------------------glide start--------------------------------------------------
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

#--------------------------------------glide end----------------------------------------------------

#----------------------------- 第三方类库 ----------------------------------------------------------
