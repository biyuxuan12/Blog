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
