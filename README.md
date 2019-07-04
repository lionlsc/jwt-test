# jwt实战测试
authserver负责注册登陆后颁发token。
resourceserver用于解析token。
注意修改这两个文件中的application.properties，将其中的数据库连接改为你自己的，运行端口可以不改。
authserver在8081端口运行。
resourceserver在8082端口运行。
将两个项目启动后，可以用postman向http://localhost:8081/api/register发送post请求，参数为username和userpwd，注册成功会返回token。
用postman向http://localhost:8081/api/login发送post请求，参数为username和userpwd，登陆成功会返回token。
用postman向http://localhost:8082/api/source发送post请求，参数为token(登陆注册返回的token)，解析成功会返回用户信息。

