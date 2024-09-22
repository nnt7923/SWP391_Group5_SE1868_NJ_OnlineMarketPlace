<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Account" %>
<%
    Account account = (Account) session.getAttribute("account");
    if (account == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
        }
        .profile-container {
            max-width: 600px;
            margin: 0 auto;
            background-color: white;
            padding: 20px;
            box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
            border-radius: 8px;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .profile-info {
            font-size: 18px;
        }
        .profile-info p {
            margin: 10px 0;
        }
        .button-container {
            text-align: center;
            margin-top: 20px;
        }
        .button-container a {
            text-decoration: none;
            background-color: #30BD36;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
        }
        .button-container a:hover {
            background-color: #28832A;
        }
    </style>
</head>
<body>

<div class="profile-container">
    <h2>User Profile</h2>

    <div class="profile-info">
        <p><strong>Username:</strong> <%= account.getUsername() %></p>
        <p><strong>Email:</strong> <%= account.getEmail() %></p>
        <p><strong>Phone:</strong> <%= account.getPhone() %></p>
        <p><strong>Address:</strong> <%= account.getAddress() %></p>
        <p><strong>Role:</strong> 
            <%
                int role = account.getRoleId();
                switch (role) {
                    case 1: out.print("Admin"); break;
                    case 2: out.print("Seller"); break;
                    case 3: out.print("Customer"); break;
                    case 4: out.print("Shipper"); break;
                    default: out.print("Unknown Role");
                }
            %>
        </p>
    </div>
    
    <div class="button-container">
        <a href="changePassword.jsp">Change Password</a>
    </div>
    
    <div class="button-container" style="margin-top: 10px;">
        <form action="LogoutController" method="post">
            <button type="submit">Logout</button>
        </form>
    </div>
</div>

</body>
</html>
