<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" /> 
    <script type="text/javascript">
    //刷新验证码
    function changeImg(){
        document.getElementById("validateCodeImg").src="${pageContext.request.contextPath}/ImageServlet?"+Math.random();
    }
	</script>
    </head>
    <body class="index">
        <div class="login_box">
        <form action="<%=request.getContextPath() %>/LoginServlet" method="post">
            <table>
                <tr>
                    <td class="login_info">账号：</td>
                    <td colspan="2"><input name="managerName" type="text" class="width150" /></td>
                </tr>
                <tr>
                    <td class="login_info">密码：</td>
                    <td colspan="2"><input name="passWord" type="password" class="width150" /></td>
                </tr>
                <tr>
                    <td class="login_info">验证码：</td>
                    <td class="width70"><input name="validateCode" type="text" class="width70" /></td>
                    <td><img alt="验证码看不清，换一张" src="${pageContext.request.contextPath}/ImageServlet" id="validateCodeImg" onclick="changeImg()"/>
                		<a href="javascript:void(0)" onclick="changeImg()">
                		<span style="font-size: 12px;color: white;padding-left: 30px;"><br/>换一张</span>
               			</a>
               		</td> 
                </tr>            
                <tr>
                    <td></td>
                    <td class="login_button" colspan="2">
						<input type="submit" value="登录"/>
						<span style="color: white;font-size: 12px;padding-left: 35px;">${result}${codeerror}${error}</span>
                    </td>              
                </tr>
            </table>
            </form>
        </div>
    </body>
</html>
