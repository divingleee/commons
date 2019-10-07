[TOC]

# Java 后端开发基础包

## 使用说明

pom 文件添加依赖即可

```xml
<dependency>
  <groupId>com.gree.bdc.dept4</groupId>
  <artifactId>commons</artifactId>
  <version>0.0.3</version>
</dependency>
```

## http 请求相关信息

### http 请求统一的响应类

controller 层返回给前端的数据类型：

```java
public class ResponseResult<T> implements Serializable {
    private Integer code;

    private String message;

    private T data;
}
```

### http 请求统一的内部响应码

上面提到的 http 响应类中的 code 请使用如下定义好的内部响应码：

```java
public enum ResponseCode {
    SUCCESS(1000, "请求成功"),
    /**
     * 参数相关
     */
    PARAMS_INVALID(2001, "参数无效"),
    PARAMS_ILLEGAL(2002, "非法参数"),
    /**
     * 服务器相关
     */
    SYSTEM_FAIL(3001, "服务器繁忙"),
    /**
     * 权限校验相关
     */
    TOKEN_INVALID(4001, "token 失效"),
    SESSION_INVALID(4002, "session 失效"),
    AUTHORIZED_FAIL(4003, "授权失败"),
    PERMISSION_DENIED(4004, "权限不足"),
    /**
     * 业务相关
     */
    BUSINESS_FAIL(5001, "业务异常"),
    /**
     * 资源相关
     */
    RESOURCE_NOT_FOUND(6001, "资源不存在"),
    /**
     * 超时相关
     */
    CALL_TIME_OUT(7001, "服务调用超时异常"),
    ;
}
```

### http 请求统一的分页类

当出现需要返回分页数据时，把该分页类放置于响应类 ResponseResult 的 data 中

```java
public class PageResult<T> implements Serializable {
    /**
     * 当前是第几页
     */
    Long currentPage;
    /**
     * 每页的数量
     */
    Long pageSize;
    /**
     * 一共多少页
     */
    Long totalPages;
    /**
     * 所有页的总数量
     */
    Long totalSize;
    /**
     * 当前页的数据
     */
    List<T> list;
}
```

## 常用异常类

有如下常用异常类，详情请看代码：

```
AuthorizedFailException 授权失败异常
BusinessException 业务异常
ParamsIllegalException 非法参数异常
ParamsInvalidException 参数无效异常
PermissionDeniedException 权限不足异常
ResourceNotFoundException 资源不存在异常
SessionInvalidException session 失效异常
SystemException 服务器异常
TimeOutException 服务调用超时异常
TokenInvalidException token 失效异常
```

## 日期时间转换工具类

案例

LocalDateTime 转换成 String：

```java
LocalDateTime localDateTime = LocalDateTime.of(2019, 6, 11, 17, 35, 15);
String result = DateTimeUtils.localDateTimeToString(localDateTime);
```

String 转换成 LocalDateTime：

```java
String now = "2019-06-11 17:35:15";
LocalDateTime localDateTime = DateTimeUtils.stringToLocalDateTime(now);
```

## 反射工具类

案例

```java
public class User {
    private String name;
    private Integer age;
    private String job;

    public User(String name, Integer age, String job) {
        this.name = name;
        this.age = age;
        this.job = job;
    }

    private void printName() {
        System.out.println(name);
    }
}
```

直接设置对象属性值, 无视 private/protected 修饰符, 不经过 setter 函数：

```java
@Test
public void setFieldValue() {
    User user = new User("123", 1, "worker");
    ReflectUtils.setFieldValue(user, "name", "456");
    Assert.assertTrue(user.name.equals("456"));
}
```

直接调用对象方法, 无视private/protected修饰符：

```java
@Test
public void invokeMethodByName() {
    User user = new User("123", 1, "worker");
    ReflectUtils.invokeMethodByName(user, "printName", null);
}
```

## 加密工具类

案例

md5 加密

```java
String result = EncryptionUtils.getMD5String("123");
```

sha256 加密

```java
String result = EncryptionUtils.sha256String("123");
```

## 属性拷贝工具类（浅拷贝）

```java
@Test
public void copy() {
    UserDTO userDTO = new UserDTO();
    userDTO.setAge(1);
    userDTO.setJob("manager");
    userDTO.setName("lisi");
    User user = new User();
    BeanCopyUtils.copy(userDTO, user);
    Assert.assertTrue(user.getAge() == 1);
    Assert.assertTrue(user.getJob().equals("manager"));
    Assert.assertTrue(user.getName().equals("lisi"));
}

public class User {
    private String name;
    private Integer age;
    private String job;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
}

public class UserDTO {
    private String name;
    private Integer age;
    private String job;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
}
```

