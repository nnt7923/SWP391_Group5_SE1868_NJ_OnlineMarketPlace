<%-- 
    Document   : login
    Created on : Sep 17, 2024, 9:21:27 PM
    Author     : phuvu
--%>
<!DOCTYPE html>
<html lang="en">
     <%@include file="includes/head.jsp" %>
    <body>

        <div class="container" id="container">
            <div class="form-container sign-in-container">
                <form action="login" method="post">
                    <h1>Login</h1>
                    <div class="social-container">
                        
                        <a href="#" class="social"><i class="fa-brands fa-google"></i></a>
                    </div>
                    <span>or use your account</span>
                    <input type="text" name="username" placeholder="Email" />
                    <input type="password" name="password" placeholder="Password" />
                    <a href="#" id="forgotPasswordLink">Forgot your password?</a>
                    <button type="submit">Login</button>
                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-left">
                        <h1>Welcome Back!</h1>
                        <p>To keep connected with us please login with your personal info</p>
                        <button class="ghost" id="signIn">Login</button>
                    </div>
                    <div class="overlay-panel overlay-right">
                        <h1>Hello, Friend!</h1>
                        <p>Enter your personal details and start journey with us</p>
                        <button class="ghost" onclick="location.href = '${pageContext.request.contextPath}/register';">Register</button>
                    </div>
                </div>
            </div>
            <div class="popup" id="forgotPasswordPopup">
                <div class="popup-content">
                    <h3>Reset Password</h3>
                    <input type="email" placeholder="Enter your email" />
                    <button>Send</button>
                    <button class="close-popup" id="closePopupButton">Close</button>
                </div>
            </div>
        </div>

    </body>
</html>
