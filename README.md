# ShareMate

ShareMate  分享伴侣,一个不要依赖其他SDK的分享SDK.非常简单

#### 示例:

![](https://github.com/fushenghua/ShareMate/raw/master/images/screenshot.png)

### 使用方法


**TWITTER**

```
 new ShareAction(MainActivity.this)
    .withPlatform(ShareAction.SHARE_MEDIA.TWITTER)
    .withText("https://github.com/fushenghua/ShareMate.git")
    .share();
```


### 配置


```
compile 'com.mate.share:ShareMate:1.0.0'
```
