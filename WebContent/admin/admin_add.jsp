<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath() %>/styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath() %>/styles/global_color.css" />
        <script language="javascript" type="text/javascript">
            //保存成功的提示消息
           /*  function showResult() {
                showResultDiv(true);
                window.setTimeout("showResultDiv(false);", 3000);
            } */
          function showResultDiv(flag) {
                var divResult = document.getElementById("save_result_info");
                if (flag)
                    divResult.style.display = "block";
                else
                    divResult.style.display = "none";
            }
        </script> 
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
               <!--  <li><a href="<%=request.getContextPath() %>/role/role_list.html" class="role_off"></a></li> -->
                <li><a href="<%=request.getContextPath() %>/ManaUserListServlet" class="admin_on"></a></li>
                <li><a href="<%=request.getContextPath() %>/fee/fee_list.jsp" class="fee_off"></a></li>
                <li><a href="<%=request.getContextPath() %>/account/account_list.jsp" class="account_off"></a></li>
                <li><a href="<%=request.getContextPath() %>/service/service_list.jsp" class="service_off"></a></li>
                <li><a href="<%=request.getContextPath() %>/bill/bill_list.jsp" class="bill_off"></a></li>
                <li><a href="<%=request.getContextPath() %>/report/report_list.jsp" class="report_off"></a></li>
                <li><a href="<%=request.getContextPath() %>/user/user_info.jsp" class="information_off"></a></li>
                <li><a href="<%=request.getContextPath() %>/user/user_modi_pwd.jsp" class="password_off"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <div id="save_result_info" class="save_success">保存成功！</div>
            <form action="<%=request.getContextPath() %>/AddManaUserServlet" method="post" class="main_form">
                    <div class="text_info clearfix"><span>姓名：</span></div>
                    <div class="input_info">
                        <input type="text" name="realName"/>
                        <span class="required">* 真实姓名</span>
                        <div class="validate_msg_long"></div>
                    </div>
                    <div class="text_info clearfix"><span>管理员账号：</span></div>
                    <div class="input_info">
                        <input type="text" name="manaName" />
                        <span class="required">* 5-30以内长度的字母、数字和下划线的组合</span>
                        <div class="validate_msg_long"></div>
                    </div>
                     <div class="text_info clearfix"><span>管理员角色：</span></div>
                    <div class="input_info">
                        <input type="text"  readonly="readonly" value="超级管理员" name="role"/>
                        <span class="required">* 默认</span>
                    </div>
                    <div class="text_info clearfix"><span>密码：</span></div>
                    <div class="input_info">
                        <input type="password"  name="passWord1"/>
                        <span class="required">* 6-30长度以内的字母、数字和下划线的组合</span>
                    </div>
                    <div class="text_info clearfix"><span>重复密码：</span></div>
                    <div class="input_info">
                        <input type="password" name="passWord2" />
                        <span class="required">* 两次密码必须相同</span>
                    </div>      
                    <div class="text_info clearfix"><span>电话：</span></div>
                    <div class="input_info">
                        <input type="text" class="width200" name="phoneNum"/>
                        <span class="required">* 必填</span>
                    </div>
                    <div class="text_info clearfix"><span>Email：</span></div>
                    <div class="input_info">
                        <input type="text" class="width200" name="email"/>
                        <span class="required">* 必填</span>
                    </div>
                    
                    <div class="button_info clearfix">
                        <input type="submit" value="保存" class="btn_save" onclick="showResult();" />
                        <a href="<%=request.getContextPath()%>/ManaUserListServlet"><input type="button" value="返回列表" class="btn_save" /></a>
                        <span>${msg }</span>
                        <!-- <input type="button" value="取消" class="btn_save" /> -->
                    </div>
                </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
           
        </div>
    </body>
</html>
