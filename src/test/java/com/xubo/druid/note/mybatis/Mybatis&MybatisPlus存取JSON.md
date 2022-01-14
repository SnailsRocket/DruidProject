## MySQL 字段的区别

char 长度固定， 即每条数据占用等长字节空间    eg:手机号、身份证

varchar 可变长度，可以设置最大长度；适合用在长度可变的属性   eg: 姓名

text  不设置长度， 当不知道属性的最大长度时   eg: 备注 ，简历     		查询字段不会出现在text里面

json

 

## JSON MySQL 内置函数

MySQL 里面的JSON 分为 JSON Array 和 JSON Object 。$ 表示JSON对象，在索引数据时用下标(对于JSON Array类型的，索引下标从0开始)或键值(对JSON Object，含有特殊字符的key要用"" 括起来，比如$."my.name")。

eg:

```json
[9,{"id": 4, "tel": "156", "address": "武汉", "num": [26,30]},[15,27]]
$[0]: 9
$[1]: {"id": 4, "tel": "156", "address": "武汉", "num": [26,30]}
$[2]: [15,27]
$[1].id: 4
$[2].tel: 156
$[1].num[1]: 30
```


https://blog.csdn.net/dragonpeng2008/article/details/89479698   JSON 内置函数参考

> JSON_TYPE     																			查看JSON中数据的优先级
>
> JSON_ARRAY(val1,val2)															生成一个JSON数组					  
>
> JSON_OBJECT(key1,val1,key2,val2)										生成一个包含指定k-v对的json，如果有key为null，或者参数为奇数则报错
>
> JSON_QUOTE(json_val)															将json_val 用括号括起来
>
> CONVERT(json_string,JSON)													转换成JSON
>
> JSON_CONTAINS																		查询函数	
>
> JSON_CONTAINS_PATH															查询是否存在指定路径
>
> JSON_EXTRACT																		  从文档中抽取数据
>
> JSON_KEYS 																				获取json文档在指定路径下的所有键值，返回一个json array
>
> JSON_SEARCH																			查询包含指定字符串的paths，并作为一个json array返回									
>
> ...  参考上面的博客  后面还有修改函数


### json字段的利弊

 https://blog.csdn.net/qq_24468953/article/details/120758408?spm=1001.2101.3001.6650.9&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7Edefault-9.no_search_link&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7Edefault-9.no_search_link&utm_relevant_index=14


### 查询速度

char  -> varchar  -> text



## Mybatis

​	由于公司业务需要，需要将一些不需要检索，仅仅只是查询展示的字段，存入MySQL,但是这些字段又很多，而且每条数据中信息又有很大的差别(有的数据

少很多字段)。之前的做法是使用MySQL的VARCHAR数据类型，也可以使用TEXT类型，但是MySQL5.7之后新增了JSON数据格式。同时还有很多配套的内置函数。非常的好用。

​	XML SQL 的 ResultMap来做typeHandler 映射处理。	





## Mybatisplus

### 异常场景

插入数据的时候，json格式的数据存入MySQL,但是查询的时候查出来是null。需要在resultMap 中指定 typeHandler ，一般直接使用JacksonTypeHandler(使用FastjsonTypeHandler 还是null)，或者自己实现TypeHandler(需要自己实现一个AbstractTypeHandler继承BaseTypeHandler,重新一个set，三个get方法，然后实现一个parse,一个 toJson方法，具体参考DruidProject项目里面的实现细节)，然后需要转换的字段上面加上一个注解@TableField(typeHandler = JacksonTypeHandler.class)这TypeHandler 可以是JacksonTypeHandler 也可以是自己实现的。自己实现的只需要继承AbstractTypeHandler 然后T写你标记的那个字段(参考RemarkTypeHandler)。

这里特别注意一个东西，@TableName(value ="foo_bar",autoResultMap = true)  少了autoResultMap = true查询出来的值还是null。



### JacksonTypeHadler&FastJsonTypeHandler区别

这两个类的 parse toJson这两个方法的实现方式不一致，JacksonTypeHandler 中使用ObjectMapper 这个类进行转换，FastjsonTypeHandler 中使用 JSON去实现的。

#### JacksonTypeHandler 

* 支持MVC JSON解析
* 支持 MySQL JSON 解析

**传统的方法是通过 XML SQL 的 resultMap 来做 typeHandler 映射处理，但是这样会影响 MP 的功能，所以** **JacksonTypeHandler 正好可以兼容 MP 的功能和满足 支持 MySQL JSON 解析**。



#### FastjsonTypeHandler 

* 支持MVC JSON 解析
* <font color=\#0000FF>不支持 MySQL JSON 解析</font>



