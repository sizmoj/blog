本博客是一个支持markdown语法的个人博客，


演示站点:

    http://www.xizhimojie.com

================================================================
项目特性
================================================================
### # 对markdown语法的支持
### # 添加对多说插件的支持
### # 添加对七牛云存储插件的支持
### # 项目技术选型简单,莫有各种框架（servlet + DBUtil + Druid）,
### # 配置简单（各种特性，只需要修改配置文件就可以使用）
### # 对Linux系统支持


================================================================
Quick Start 
================================================================

0.  请确保你的环境已经安装JDK7+和maven,mysql，因为需要你自己手动编译（github网络卡..几个MB的文件都上传不了，如果你不会编译也可以联系我）


1. 进入~\src\main\webapp\WEB-INF，编辑db_server.properties

    数据库地址  
	url=jdbc:mysql://127.0.0.1:3306/xizhimojie  
	数据库用户名  
	username=root  
	数据库密码  
	password=123456  
	网址  
	website=www.xizhimojie.com  
    站点名称 
	sitename=细枝末节 
	多说插件（账号申请：http://duoshuo.com/） 
	duoshuo=xizhimojie 
	七牛云存储token(申请指南http://developer.qiniu.com/) 
    ACCESS_KEY=JJwpFLJRwcpgQOw4iN00_mw8OlZIfKeGzk9cdZfoN 
	SECRET_KEY=rOCnqEyDe7i6t55yP7e7-BEpcctdsU9yf299YISP1 
	QINIU_URL=7xiz35.co3m1.z0.glb.clouddn.com 
	
2. 导入数据库脚本\~blog\src\main\webapp\WEB-INF\init.sql

3. 在项目根目录下面执行maven命令 mvn clean compile war:war

4. 在target目录下面把安装包放到tomcat/ROOT下面，启动tomcat

5. 后台地址为/login, 默认密码为admin/admin, 请及时修改    

