<%-- 
    Document   : dashboard
    Created on : Sep 19, 2024, 4:43:18 PM
    Author     : phuvu
--%>

<%@ page import="model.Account" %>
<%
    Account account = (Account) session.getAttribute("account");
    if (account == null || account.getRoleID() != 2) {
        response.sendRedirect("../login.jsp");
        return;
    }
%>
<h1>Welcome Seller: <%= account.getUsername()%></h1>
