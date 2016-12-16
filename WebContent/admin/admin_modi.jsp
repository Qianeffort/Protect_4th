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
            function showResult() {
                showResultDiv(true);
                window.setTimeout("showResultDiv(false);", 3000);
            }
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
           <!--      <li><a href="<%=request.getContextPath() %>/role/role_list.jsp" class="role_off"></a></li> -->
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
            <form action="<%=request.getContextPath() %>/ModiManaUserServlet" method="post" class="main_form">
            		<input type="hidden" name="id" value="${manaUser2.id }"/>
                    <div class="text_info clearfix"><span>姓名：</span></div>
                    <div class="input_info">
                        <input type="text" name="realName" value="${manaUser2.realName }"/>
                        <span class="required">*</span>
                    </div>
                    <div class="text_info clearfix"><span>管理员账号：</span></div>
                    <div class="input_info"><input type="text" value="${manaUser2.managerName }" readonly="readonly" class="readonly" /></div>
                    
                    <div class="text_info clearfix"><span>管理员角色：</span></div>
                    <div class="input_info"><input type="text" value="${manaUser2.role }" readonly="readonly" class="readonly" /></div>
                    <div class="text_info clearfix"><span>电话：</span></div>
                    <div class="input_info">
                        <input type="text" name="phoneNum" value="${manaUser2.phoneNum }"/>
                        <span class="required">*</span>
                    </div>
                    <div class="text_info clearfix"><span>Email：</span></div>
                    <div class="input_info">
                        <input type="text" class="width200" name="email" value="${manaUser2.email }"/>
                        <span class="required">*</span>
                    </div>
                    <div class="button_info clearfix">
                        <input type="submit" value="保存" class="btn_save" onclick="showResult();" />
                      <a href="<%=request.getContextPath()%>/ManaUserListServlet"><input type="button" value="返回列表" class="btn_save" /></a>
                      <span style="color: red">${msg }</span>
                    </div>
                    <span style="padding-left: 230px;">打  <span class="required">*</span>  处为必填项</span>
                </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
           
        </div>
    </body>
</html>
