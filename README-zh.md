# ShareMate

ShareMate  分享伴侣,一个不要依赖其他SDK的分享SDK.非常简单


#### 示例:

![](https://github.com/fushenghua/ShareMate/raw/master/images/screenshot.png)

### 特征

* 支持平台 (**Twitter/Facebook/WhatsApp/Messager/System Share、SMS**)

* 不需要申请app key

### 使用方法


**TWITTER**

```
 new ShareAction(MainActivity.this)
    .withPlatform(ShareAction.SHARE_MEDIA.TWITTER)
    .withText("https://github.com/fushenghua/ShareMate.git")
    .share();
```


**System**

``` java
 new ShareAction(MainActivity.this)
 .withPlatform(ShareAction.SHARE_MEDIA.MORE)
 .withTitle("Share to")
 .withText("https://github.com/fushenghua/ShareMate.git")
 .share();
```


### 配置


```
compile 'com.mate.share:sharemate:1.0.3'
```

## License
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)


```
MIT License

Copyright (c) 2017 fushenghua

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```