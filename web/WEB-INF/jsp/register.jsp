<%-- 
    Document   : login
    Created on : Sep 17, 2024, 9:21:27 PM
    Author     : phuvu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%@include file="includes/head.jsp" %>
    <body>

        <div class="container" id="container">
            <div class="form-container sign-in-container">
                <c:if test="${not empty sessionScope.message}">
                    <p class="text-success">${sessionScope.message}</p>
                    <c:remove var="message"/>
                </c:if>

                <c:if test="${not empty sessionScope.error}">
                    <p class="text-danger">${sessionScope.error}</p>
                    <c:remove var="error"/>
                </c:if>
                <form class="account" action="${pageContext.request.contextPath}/register" method="POST">
                    <h1>Create Account</h1></br>





                    <input type="text" placeholder="User Name" name="username" />
                    <input type="email" placeholder="Email" name="email" value="${Account.getEmail()}"/>
                    <input type="password" placeholder="Password" name="password" />
                    <input type="password" placeholder="Confirm Password" />

                    <select name="role" id="role">

                        <c:forEach var="role" items="${roles}">
                            <option value="${role.getId()}" >${role.getRoleName()}</option>
                        </c:forEach>
                    </select><br>
                    <button>Register</button>
                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">

                    <div class="overlay-panel overlay-right">
                        <h1>Welcome Back!</h1>
                        <p>To keep connected with us please login with your personal info</p>
                        <button class="ghost" onclick="location.href = '${pageContext.request.contextPath}/login';">Login</button>
                    </div>
                </div>
            </div>
<!--            <div class="popup" id="forgotPasswordPopup">
                <div class="popup-content">
                    <h3>Reset Password</h3>
                    <input type="email" placeholder="Enter your email" />
                    <button>Send</button>
                    <button class="close-popup" id="closePopupButton">Close</button>
                </div>
            </div>-->
        </div>
        <%@include file="includes/script.jsp" %>
    </body>
</html>
