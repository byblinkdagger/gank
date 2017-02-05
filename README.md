>由于本人一直是在android rom公司工作，几乎接触不到纯上层apk的整体开发与设计（偶尔有些也是内嵌在rom framework层的小功能apk，哈哈其实就是流氓软件~）。所以为了满足自己的兴趣，过年期间用很少的闲暇、零碎时间撸了一个干货集中营的客户端（由于种种原因只实现了三个api接口），用到的开源库主要有：retrofit、rxjava、picasso等，另外第一次尝试使用了MVP的架构~~虽然用得有些多此一举的感觉。

目前还未对代码及功能做任何二次优化，所以可能存在各种问题，欢迎指出，后面有时间在慢慢补坑吧~
######本客户端gayhub地址：https://github.com/byblinkdagger/gank      欢迎star~~

* 整体依赖：
```
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:23.4.0'
    compile 'com.android.support:design:23.4.0'
    compile 'com.android.support:cardview-v7:23.4.0'
    compile 'com.jakewharton:butterknife:5.1.1'
    compile 'com.squareup.retrofit2:retrofit:2.1.0'
    compile 'com.squareup.retrofit2:converter-gson:2.1.0'
    compile 'com.squareup.retrofit2:adapter-rxjava:2.1.0'
    compile 'io.reactivex:rxjava:1.1.0'
    compile 'io.reactivex:rxandroid:1.1.0'
    compile 'com.squareup.picasso:picasso:2.5.2'
    compile 'com.android.support:support-v4:23.4.0'
    compile 'com.android.support:recyclerview-v7:23.4.0'
    compile 'com.ashokvarma.android:bottom-navigation-bar:1.3.0'
    compile 'com.cjj.materialrefeshlayout:library:1.3.0'
    compile 'com.nineoldandroids:library:2.4.0'
    compile 'com.google.code.gson:gson:2.2.4'
    compile 'com.squareup.okhttp3:okhttp:3.4.2'
}
```
* 运行截图(不要吐嘈大图，嘻嘻）：

![111](http://upload-images.jianshu.io/upload_images/2555073-ef62fef410fdd5ad.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![222](http://upload-images.jianshu.io/upload_images/2555073-22d842e2e668a9c6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![333](http://upload-images.jianshu.io/upload_images/2555073-40dd17cc1cef6e8f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![444](http://upload-images.jianshu.io/upload_images/2555073-555562494e828063.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![555](http://upload-images.jianshu.io/upload_images/2555073-c4aeea4856b939aa.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![666](http://upload-images.jianshu.io/upload_images/2555073-2e8e03952462cda8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![777](http://upload-images.jianshu.io/upload_images/2555073-48030ac8eab72057.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


* 整体效果：

![](http://upload-images.jianshu.io/upload_images/2555073-8d50bc8095d20d7b.gif?imageMogr2/auto-orient/strip)


######最后致谢：代码家的干货集中营所提供的api 及drakeet开源的Meizhi
