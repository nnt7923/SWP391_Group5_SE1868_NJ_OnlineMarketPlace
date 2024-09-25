<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Fruitables - Vegetable Website Template</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
        <link rel="stylesheet" href="css/login.css">
        <!-- Thêm SweetAlert t? CDN -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    </head>
    <body>
                <%
            // Hi?n th? thông báo thành công t? session
            String successMessage = (String) session.getAttribute("message");
            if (successMessage != null) {
        %>

        <script>
            Swal.fire({
                icon: 'success',
                title: 'Success',
                text: '<%= successMessage %>',
                imageUrl: 'img/siu.jpeg',
                imageWidth: 400,
                imageHeight: 200,
                imageAlt: 'Custom image',
                showClass: {
                    popup: 'animate__animated animate__fadeInDown',
                    backdrop: 'animate__animated animate__fadeIn',
                    image: 'animate__animated animate__zoomIn'
                },
                hideClass: {
                    popup: 'animate__animated animate__fadeOutUp',
                    backdrop: 'animate__animated animate__fadeOut',
                    image: 'animate__animated animate__zoomOut'
                },
                confirmButtonText: 'OK'
            });
        </script>
        <%
                session.removeAttribute("message");
            }
            
            // Hi?n th? thông báo l?i t? request attribute 'mess'
            String errorMessage = (String) request.getAttribute("mess");
            if (errorMessage != null) {
        %>

        <script>
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: '<%= errorMessage %>',
                showClass: {
                    popup: 'animate__animated animate__fadeInDown',
                    backdrop: 'animate__animated animate__fadeIn'
                },
                hideClass: {
                    popup: 'animate__animated animate__fadeOutUp',
                    backdrop: 'animate__animated animate__fadeOut'
                },
                confirmButtonText: 'OK'
            });
        </script>
        <%
            }
        %>
        <div class="container" id="container">
            <div class="form-container sign-up-container">
                <form action="EmailSender" method="post">
                    <h1>Create Account</h1>
                    <div class="social-container">
                        <a href="#" class="social"><i class="fa-brands fa-facebook"></i></a>
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid&redirect_uri=http://localhost:8080/OnlineMarketPlace/login&response_type=code&client_id=273365308613-bh0svqt2gvjp973pk4m9g1o54ac5j8v2.apps.googleusercontent.com&approval_prompt=force">
                            <i class="fa-brands fa-google"></i>
                        </a>
                    </div>
                    <span>or use your email for registration</span>
                    <input type="text" name="username" placeholder="Username" required />
                    <input type="password" name="password" placeholder="Password" required />
                    <input type="email" name="email" placeholder="Email" required />
                    <input type="text" name="phone" placeholder="Phone" required />
                    <input type="text" name="address" placeholder="Address" required />
                    <input type="hidden" name="flag" value="register" />
                    <select name="role" id="role" required>
                        <option value="3" selected>Customer</option>
                        <option value="2">Seller</option>
                        <!-- Thêm các vai trò khác n?u c?n -->
                    </select><br>
                    <button>Register</button>
                </form>
            </div>
            <div class="form-container sign-in-container">
                <form action="login" method="post">
                    <h1>Login</h1>
                    <div class="social-container">
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid&redirect_uri=http://localhost:8080/OnlineMarketPlace/login&response_type=code&client_id=273365308613-bh0svqt2gvjp973pk4m9g1o54ac5j8v2.apps.googleusercontent.com&approval_prompt=force">
                            <i class="fa-brands fa-google"></i>
                        </a>
                    </div>
                    <span>or use your account</span>
                    <input type="email" name="email" placeholder="Enter email" maxlength="30" required="required" autocomplete="off" />
                    <input type="password" name="password" placeholder="Enter password" maxlength="16" required="required" autocomplete="off" />
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
                        <button class="ghost" id="signUp">Register</button>
                    </div>
                </div>
            </div>

            <form action="EmailSender" method="post">
                <div class="popup" id="forgotPasswordPopup">
                    <div class="popup-content">
                        <h3>Reset Password</h3>
                        <input name="email_forgot" type="email" placeholder="Enter your email" />
                        <input type="hidden" name="flag" value="forgotPassword">
                        <button type="submit" id="submit-btn">Send</button>
                        <button class="close-popup" id="closePopupButton">Close</button>
                    </div>
                </div>
            </form>
        </div>
        <script>
            const signUpButton = document.getElementById('signUp');
            const signInButton = document.getElementById('signIn');
            const container = document.getElementById('container');
            const closePopupButton = document.getElementById('closePopupButton');
            const forgotPasswordLink = document.getElementById('forgotPasswordLink');
            const forgotPasswordPopup = document.getElementById('forgotPasswordPopup');

            signUpButton.addEventListener('click', () => {
                container.classList.add("right-panel-active");
            });
            signInButton.addEventListener('click', () => {
                container.classList.remove("right-panel-active");
            });
            forgotPasswordLink.addEventListener('click', () => {
                forgotPasswordPopup.style.display = 'flex';
            });
            closePopupButton.addEventListener('click', () => {
                forgotPasswordPopup.style.display = 'none';
            });
        </script>
    </body>
</html>