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
                <form action="#">
                    <h1>Create Account</h1></br>
                    
                    
<!--                    <input type="text" placeholder="Username" />-->
                    <input type="email" placeholder="Email" />
                    <input type="password" placeholder="Password" />
                    <input type="password" placeholder="Confirm Password" />
<!--                    <input type="text" placeholder="Phone" />
                    <input type="text" placeholder="Address" />-->
                    <select name="vaiTro" id="role">
                        <option value="mua" selected>Customer</option>
                        <option value="ban">Seller</option>
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
            <div class="popup" id="forgotPasswordPopup">
                <div class="popup-content">
                    <h3>Reset Password</h3>
                    <input type="email" placeholder="Enter your email" />
                    <button>Send</button>
                    <button class="close-popup" id="closePopupButton">Close</button>
                </div>
            </div>
        </div>
     <%@include file="includes/script.jsp" %>
    </body>
</html>
