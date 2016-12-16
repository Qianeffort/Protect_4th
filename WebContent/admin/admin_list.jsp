<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="project.tarena.entity.ManagerUser,java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath() %>/styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="<%=request.getContextPath() %>/styles/global_color.css" /> 
		
        <script language="javascript" type="text/javascript">
            //显示角色详细信息
            function showDetail(flag, a) {
                var detailDiv = a.parentNode.getElementsByTagName("div")[0];
                if (flag) {
                    detailDiv.style.display = "block";
                }
                else
                    detailDiv.style.display = "none";
            }
            //重置密码
            function resetPwd() {
                alert("请至少选择一条数据！");
                //document.getElementById("operate_result_info").style.display = "block";
            }
            //删除
            function deleteAdmin() {
                var r = window.confirm("确定要删除此管理员吗？");
                document.getElementById("operate_result_info").style.display = "block";
            }
            //全选
            function selectAdmins(inputObj) {
                var inputArray = document.getElementById("datalist").getElementsByTagName("input");
                for (var i = 1; i < inputArray.length; i++) {
                    if (inputArray[i].type == "checkbox") {
                        inputArray[i].checked = inputObj.checked;
                    }
                }
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
                <li><a href="<%=request.getContextPath() %>/EditSelfManaUserServlet" class="information_off"></a></li>
                <li><a href="<%=request.getContextPath() %>/user/user_modi_pwd.jsp" class="password_off"></a></li>
            </ul>            
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <form action="<%=request.getContextPath() %>/ManaUserListServlet" method="post">
                <!--查询-->
                <div class="search_add">
                    <div>用户名：<input type="text"  name="manaName" class="text_search width200" /></div>
                    <div><input type="submit" value="搜索" class="btn_search"/></div>
                    <input type="button" value="增加管理员" class="btn_add" onclick="location.href='admin/admin_add.jsp';" />
                </div>
                <!--删除和密码重置的操作提示-->
                <div id="operate_result_info" class="operate_fail">
                    <img src="<%=request.getContextPath() %>/images/close.png" onclick="this.parentNode.style.display='none';" />
                    <span>删除失败！数据并发错误。</span><!--密码重置失败！数据并发错误。-->
                </div> 
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th>管理员ID</th>
                            <th>姓名</th>
                            <th>登录名</th>
                            <th>电话</th>
                            <th>电子邮件</th>
                            <th>授权日期</th>
                            <th class="width100">角色</th>
                            <th>操作<span style="color: red">${msg }</span></th>
                        </tr> 
                       <%List<ManagerUser> list=(List<ManagerUser>)request.getAttribute("list");
                       	 for(ManagerUser manaUser2:list){
                       		 if(manaUser2.getId()==1){
                       %>
                       	<tr>
                            <td><%=manaUser2.getId() %></td>
                            <td><%=manaUser2.getRealName() %></td>
                            <td><%=manaUser2.getManagerName() %> </td>
                            <td><%=manaUser2.getPhoneNum() %> </td>
                            <td><%=manaUser2.getEmail() %> </td>
                            <td><%=manaUser2.getAuthorization_date() %> </td>
                            <td><%=manaUser2.getRole() %> </td>
                        </tr> 
                       			 
                       		<% } if(manaUser2.getId()!=1) { %> 
                       		<tr>
                            <td><%=manaUser2.getId() %></td>
                            <td><%=manaUser2.getRealName() %></td>
                            <td><%=manaUser2.getManagerName() %> </td>
                            <td><%=manaUser2.getPhoneNum() %> </td>
                            <td><%=manaUser2.getEmail() %> </td>
                            <td><%=manaUser2.getAuthorization_date() %> </td>
                            <td><%=manaUser2.getRole() %> </td>
                            <td class="td_modi">
                                <a href="<%=request.getContextPath()%>/ModiManaUserServlet?id=<%=manaUser2.getId() %>" style="color: blue;text-decoration: underline;" >修改</a>
                                <a href="<%=request.getContextPath()%>/DelManaUserServlet?id=<%=manaUser2.getId() %>" style="color: blue;text-decoration: underline;">删除</a>
                                <a href="<%=request.getContextPath()%>/ResetPwdServlet?id=<%=manaUser2.getId() %>" style="color: blue;text-decoration: underline;">密码重置</a>
                            </td>
                        </tr> 	
                       		<%} %>
                       	<% }  %>           
                   		
                    </table>
                </div>
                <!--分页-->
                <div id="pages">
        	        <a href="#">上一页</a>
                    <a href="#" class="current_page">1</a>
                    <a href="#">2</a>
                    <a href="#">3</a>
                    <a href="#">4</a>
                    <a href="#">5</a>
                    <a href="#">下一页</a>
                </div>                    
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
           
        </div>
    </body>
</html>
