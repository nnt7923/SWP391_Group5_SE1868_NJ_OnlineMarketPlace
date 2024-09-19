<%-- 
    Document   : dashboard
    Created on : Sep 19, 2024, 4:41:59 PM
    Author     : phuvu
--%>

<%@ page import="model.Account" %>
<%
    Account account = (Account) session.getAttribute("account");
    if (account == null || account.getRoleID() != 1) {
        response.sendRedirect("../login.jsp");
        return;
    }
%>
<h1>Welcome Admin: <%= account.getUsername()%></h1>

