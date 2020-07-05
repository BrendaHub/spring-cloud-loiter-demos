Java8 最大的特性就是引入Lambda表达式， 即函数式编程， 可以将行为进行传递。 总结就是： 使用不可变值与函数， 函数地不可变值进行处理， 映射成另一个值。 

## Java 重要的函数式接口

### 什么时函数式接口

函数接口是只有一个抽象方法的接口， 用作Lambda表达式的类型， 使用@Functionallnterface 注解修饰的类， 编译会检测
该类是否只有一个抽象方法或接口， 否则 ， 会报错， 可以有多个默认方法， 静态方法。 

#### java8 自带的常用函数式接口

| 函数接口 | 抽象方法 | 功能 | 参数 | 返回类型 | 示例 |
|:---|:---|:---|:---:|:---|:---|
|Predicate| test(T t)|判断真假|T|boolean|***|
|Consumer|accept(T t)|消费消息|T||void|输出一个值|
|Function|R apply(T t)|将T映射为R（转换功能）| T|R|获取T对象的名字|
|Supplier|T get（）|生产消息|None|T|工厂方法|
|UnaryOperator|T apply(T t)|一元操作|T|T|逻辑非（！）|
|BinaryOperator|apply（T t， U u）|二元操作|（T， T）|（T）|求两个数据乘积|
