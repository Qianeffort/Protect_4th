<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath() %>/styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath() %>/styles/global_color.css" />        
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="<%=request.getContextPath() %>/images/logo.png" alt="logo" class="left"/>
            ${managerUser.managerName }<span>，欢迎你！</span>
            <a href="<%=request.getContextPath()%>/CleanSessionServlet">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">
            <ul id="menu">
                <li><a href="<%=request.getContextPath() %>/index.jsp" class="index_off"></a></li>
               <!-- <li><a href="<%=request.getContextPath() %>/role/role_list.jsp" class="role_off"></a></li> --> 
                <li><a href="<%=request.getContextPath() %>/ManaUserListServlet" class="admin_off"></a></li>
                <li><a href="<%=request.getContextPath() %>/fee/fee_list.jsp" class="fee_off"></a></li>
                <li><a href="<%=request.getContextPath() %>/account/account_list.jsp" class="account_off"></a></li>
                <li><a href="<%=request.getContextPath() %>/service/service_list.jsp" class="service_off"></a></li>
                <li><a href="<%=request.getContextPath() %>/bill/bill_list.jsp" class="bill_off"></a></li>
                <li><a href="<%=request.getContextPath() %>/report/report_list.jsp" class="report_off"></a></li>
                <li><a href="<%=request.getContextPath() %>/user/user_info.jsp" class="information_off"></a></li>
                <li><a href="<%=request.getContextPath() %>/user/user_modi_pwd.jsp" class="password_on"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <div id="main">      
            <!--保存操作后的提示信息：成功或者失败-->      
            <div id="save_result_info" class="save_success">保存成功！</div><!--保存失败，旧密码错误！-->
            <form action="<%=request.getContextPath() %>/ResetPwdServlet" method="post" class="main_form">
            
                <div class="text_info clearfix"><span>新密码：</span></div>
                <div class="input_info">
                    <input type="password" name="passWord1" class="width200" /><span class="required">* 6-30长度以内的字母、数字和下划线的组合</span>
                    <div class="validate_msg_medium"></div>
                </div>
                <div class="text_info clearfix"><span>重复新密码：</span></div>
                <div class="input_info">
                    <input type="password"  name="passWord2" class="width200"  /><span class="required">* 两次新密码必须相同</span>
                    <div class="validate_msg_medium"></div>
                </div>
                <div class="button_info clearfix">
                    <input type="submit" value="重置" class="btn_save" onclick="showResult();" />
                    <input type="button" value="取消" class="btn_save" /><span style="color: red;">${msg }</span>
                </div>
            </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">

        </div>
    </body>
</html>
