## 如何帮助一个没碰过后台的人写一个api

昨天试着帮助一个同学写一个api，发现网上的教程对于一个没写过servlet的人来说真的很不友好。本来想收集一篇从环境配置开始讲起的教程，实在是太难了。 一开始以为萌新就别用springboot框架，原生java调一下servlet，写两个post函数就齐活了，后来想到还要搞tomcat，数据库说不定还要自己去下cdbc的jar包。而这一整套流程很多实体书上都有，但是网上的教程就会跳过很多步骤。所以打算写一个傻瓜版的api撰写教程，怎么偷懒怎么来的那种。

### 工具准备

*  一台联网的mac 默认你电脑上装了brew 没装的话下面两个任务自己想办法完成
*  给它装上java 不想指定版本，问就是1.8 要把环境都陪好哦～有很多攻略可以查 
*  `brew cask install java` 
*  给它装上gradle 不想解释这是啥 装就完事了
*  `brew install gradle`
*  装一个idea 
*  https://www.jetbrains.com 这是官网 自己下载 免费版的就ok
*  装spirngboot
```
brew tap pivotal/tap
brew install springboot
```
*  齐活了大概

### 开始干活
* 第一步 新建一个项目 打开你的bash 或者zsh 或者任何控制台新建一个项目
`spring init -dweb,thymeleaf,websocket --build gradle`

* 会下载一个叫demo的zip包
* 解压它 你得到了一个demo文件夹 里面就是已经准备好的项目结构
* 看不懂没关系 我也看不懂
* 用你的idea打开这个demo文件夹
* 在控制台（demo目录下）下输入
* `gradle build`
* 你的gradle正在帮你搞定所有的依赖
* 找到src/java/com.example.demo文件夹 在这里新建一个Welocme 的JAVA类
![Alt text](https://github.com/biyuxuan12/Blog/blob/master/image/601575974673_.pic.jpg)
贴上如下代码
```
package com.example.demo.welcomeController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Welcome {

    @GetMapping("/")
    public String welcome(String echostring) {

        System.out.println(echostring);
     return (echostring);
    }
}
```
* 好的 一个基础版的api完成了
* 我们很开心的用 gradle bootRun运行起这个网站，在浏览器输入以下网址访问
![Alt text](https://github.com/biyuxuan12/Blog/blob/master/image/741575975916_.pic.jpg)
* 用url的方式把我们想要传递的参数穿给了后台再传回来
*然后删掉我们刚刚写的类。
### 给我们的后台加上json的读取
* 当然光有后台连教程都进行不下去，我们先写一个尽可能精简的前端。
* 在这个位置新建好我们前台文件，这个位置很重要
![Alt text](https://github.com/biyuxuan12/Blog/blob/master/image/indexhtmlloc.png)

* 然后前台的代码如下：
```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js">
    </script>
    <script>

function post() {
    var name=document.getElementById("imgname").value;
    var size=document.getElementById("imgsize").value;
    var data1={
        name:name,
        size:size
    };
    $.ajax({
        type: "POST",
        url: "/api",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data1),
        dataType: "json",
        success: function (message) {
            console.log(message);
        },
        error: function (message) {
            $("#request-process-patent").html("提交数据失败！");
        }
    });
}
    </script>
</head>
<body>
图片名：<input type="text" name="image" id="imgname">
尺寸：<input type="text" name="size" id="imgsize">
<button type="button" onclick="post()">提交</button>

</body>
</html>
```

* 前台html已经写好了，在还是刚刚的位置，
![Alt text](https://github.com/biyuxuan12/Blog/blob/master/image/indexcontrollerloc.png)
新建一个类把我们的访问指引到刚刚写好的Html页面。
```
package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String welcome() {
        return "index";
    }
}

```
* 前端完成，继续写api。这一次，我们要引入一个起步依赖用来解读json文件。
* 在如图位置 
![Alt text](https://github.com/biyuxuan12/Blog/blob/master/image/gradle.png)
加上这行代码
`implementation("com.alibaba:fastjson:1.2.61")`
* 在控制台输入
`gradle build`
* 让gradle帮你把起步依赖下载下来，
* 点一下idea右上角的gradle，再点一下刷新按钮告诉迟钝的idea这个依赖你已经有了，别等会报红了

![Alt text](https://github.com/biyuxuan12/Blog/blob/master/image/gradlefresh.png)
* ok，准备工作搞定，开始干正事。
* 这个位置

![Alt text](https://github.com/biyuxuan12/Blog/blob/master/image/apicontrollerloc.png)

* 代码如下：
```
package com.example.demo;


import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {

    @RequestMapping(value="/api",method = RequestMethod.POST)
    public String welcome(@RequestBody JSONObject jsonParam) {
        System.out.println(jsonParam.toJSONString());
     return (jsonParam.toJSONString());
    }
}

```
恭喜
现在再控制台输入gradle bootRun运行起你的网站，
访问127.0.0.1:8080 ,输入提交
![Alt text](https://github.com/biyuxuan12/Blog/blob/master/image/frontendinput.png)
你能在你网站后台可控制台看见这个。
![Alt text](https://github.com/biyuxuan12/Blog/blob/master/image/jsonresult.png)
一次简单的json数据从前台到后台传递完成了。
