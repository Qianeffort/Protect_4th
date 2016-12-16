<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" /> 
    </head>
    <body class="index">
        <!--导航区域开始-->
        <div id="index_navi">
            <ul id="menu">
                <li><a href="<%=request.getContextPath() %>/index.jsp" class="index_on"></a></li>
               <!--  <li><a href="role/role_list.jsp" class="role_off"></a></li> -->
                <li><a href="<%=request.getContextPath() %>/ManaUserListServlet" class="admin_off"></a></li>
                <li><a href="fee/fee_list.jsp" class="fee_off"></a></li>
                <li><a href="account/account_list.jsp" class="account_off"></a></li>
                <li><a href="service/service_list.jsp" class="service_off"></a></li>
                <li><a href="bill/bill_list.jsp" class="bill_off"></a></li>
                <li><a href="report/report_list.jsp" class="report_off"></a></li>
                <li><a href="<%=request.getContextPath() %>/EditSelfManaUserServlet" class="information_off"></a></li>
                <li><a href="user/user_modi_pwd.jsp" class="password_off"></a></li>
            </ul>
        </div>
    </body>
</html>
