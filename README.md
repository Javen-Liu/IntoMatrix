# 关于IntoMatrix

一个基于SMM、shiro以及javaex框架的博客系统

## 为什么叫 IntoMatrix ？

很早之前就听过电影《黑客帝国》，一直想看。后来大一的时候一口气把三部全看了，电影深深震撼到我了，那可是1999年就有的片子啊！！我才出生了一年！！

后来发现，这部电影这部电影的英文名是"Matrix"，国内翻译为“母体”。

个人认为这个母体翻译的中规中矩，不过英文原文中的这个"Matrix"原意为矩阵，顿时觉得挺符合这个剧情故事的，主角们都是在一个电脑模拟出的世界中生活。

所以个人对于Matrix这个词情有独钟（虽然自己的数学并不是非常厉害，也不是数学研究者，有点叶公好龙的感觉）

**总之就是有点中二**

## 为什么写这么一个项目？

大三时上的一门Java打开了我学习Java的大门，之前虽然学过c以及单片机，但是总觉得自己写不出个什么，当然也跟自己学艺不精有关。

后来就开始一直自学Java，从JavaSE，到JavaEE。从tomcat、servlet等，到spring以及spring的全家桶（spring称自己是轻量级框架，或许违背初心算是唯一的失败？/滑稽）

之前把SpringMVC以及MyBatis学完，感觉自己能够写出一个网页加上服务器，至少可以在博客这种地方自产自销

这个博客也是跟着视频一点一点抠代码抠出来的。不过那个教程的前端页面没有资源，因此我才另辟蹊径，找到了javaex框架，该框架的创始人自己也有对应的视频以及文档

[javaex官网](http://www.javaex.cn/)，大佬对应的视频教程在这里https://www.bilibili.com/video/BV1WW411c7Cr?p=16

羡慕大佬能够造轮子(我什么时候也能造轮子给别人用啊，哈哈)

## 项目目录

- **/src/test**
  
测试文件夹，对于出现错误的地方，当然是对应进行测试来排除到底是那一层出现了问题，以及排查错误
 
- **/src/main/java/com/matrix**

  - /controller
  
  前端控制器层目录，主要是配合页面进行各种请求，以调用下面service层的业务服务
  
  - /dao
  
  持久层目录，用于与数据库交互，进行crud操作。由于使用MyBatis框架，所以无需写实现类，MyBatis框架会根据对应的mappers来生成代理对象，并存入spring的ioc容器中
  
  - /entity
  
  实体类目录，数据库与我们的服务器程序交互时，实体类作为中间媒介来进行数据传播，将查到的数据封装为一个javabean对象，以供其他层调用
  
  - /realm
  
  该目录下的BlogRealm类用来配合shiro进行身份验证操作。shiro我个人研究的并不深，只是知道登陆以及访问页面时的身份验证是由shiro来做。因为懂得不是很多，所以也就不班门弄斧，惹人笑话了
  
  - /service
  
  业务层目录，其中包括了业务的接口以及实现类，用于对dao层的数据进行业务操作、组合，并将结果提供给controller层以返回给前端页面
  
  - /utils
  
  工具类目录，其中包括了md5加密工具、Json数据转换工具、response返回请求封装工具以及对于字符串String操作的工具
  
  - InitComponent

  用于设定一些存于servletContext中的一些信息，比如用户信息之类的
  
- **/src/resources**

  - /mappers
  
  dao层接口的映射xml文件，用于MyBatis框架来生成对应代理对象
  
  - /spring
  
  spring的配置文件，根据mvc、service、dao三层来分开配置（不得不说Spring Boot自动配置真的省心，深得开发人员的喜爱不是没有原因的）
  
  - jdbcConfig.properties
  
  用于配置jdbc相关的数据，老四样了
  
  - log4j.properties
  
  log4j的配置文件，不过Spring Boot已经不用log4j了
  
  - mybatis-config.xml
  
  MyBatis框架配置文件，里面设置了懒查询
  
- **/webapp**
  
  - /pages
  
  jsp页面，包括了登陆，文章管理，评论管理以及个人账户的设置改动
  
  - /static
  
  静态资源
  
## 老爹：还有一件事.jpg（知道这个的应该和我年纪相仿，童年啊）

由于初学jsp，很多jsp语法都没怎么用，只会在迫不得已的情况下用jsp的语法，很多页面的后端数据都是我阴差阳错地用ajax发送请求获取的。

当时根本不知道只需要在controller将数据封装到map中，jsp可以直接调用。

后来和高中同学交流（他是写前端的），发现他们前端开发，前端页面和后端分开部署，前后端就是这样通过前端发送请求，后端把数据封装为json来进行交互，我也算阴差阳错地学习了前后端分离地皮毛

最后就是后端登陆进去的主页面实在懒得写了，就留下一个坑给大家了，大家可以自己自由发挥（逃

如果觉得对于自己的项目或者课设有用，在fork或者下载前记得点个star（哦？）
