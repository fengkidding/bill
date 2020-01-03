# bill：账单微服务
业务：账单、商品、订单


### 目录介绍：

annotation：注解及其实现

aspect：aop切面，接口的切面

common：通用的功能模块
    log：统一结构化日志工具类
    util：工具类
    
config：配置类

controller：api接口    
    
dao：数据访问层
    db：处理mysql数据库
    es：处理es
    redis：处理redis缓存
    
interceptor：拦截器

manager：封装其他服务接口的调用

model：数据对象

mq：消息队列

pattern：设计模式目录

service：业务层

socket：长连接

doc：文档目录

mapper：mybatis的xml文件

test：单元测试目录


### 说明：

注册中心：配置在bootstrap.yml中

配置中心：配置在bootstrap.yml中，通过注册中心向配置服务拉取配置

mybatis生成工具：配置在generatorConfig.xml

日志配置：配置在logback-spring.xml，向elk发送结构化日志

结构化日志：在common/log目录下，使用LogBackUtils工具类来统一打印日志

统一异常处理：ExceptionHandle类中截获异常，并返回统一格式的结果，以及处理校验异常和业务异常

swagger文档中心：配置在Swagger2Configuration

读写分离：在config/db目录下，mybatis读写分离插件

对象转换：在model/conversion下，使用mapstruct进行对象转换

