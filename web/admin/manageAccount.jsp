<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Accounts</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">Manage Accounts</h2>
    <form action="AccountController?service=listAll" method="get" class="d-flex mb-3">
        <input type="text" name="keyword" class="form-control" placeholder="Search by username" required>
        <button type="submit" class="btn btn-primary ms-2">Search</button>
    </form>
    <a href="AccountController?service=addAccountForm" class="btn btn-success mb-3">Add New Account</a>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Username</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Address</th>
            <th>Role</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="account" items="${accounts}">
            <tr>
                <td>${account.username}</td>
                <td>${account.email}</td>
                <td>${account.phone}</td>
                <td>${account.address}</td>
                <td>${account.roleId}</td>
                <td>${account.status}</td>
                <td>
                    <a href="AccountController?service=updateAccountForm&accountId=${account.accountId}" class="btn btn-warning btn-sm">Edit</a>
                    <a href="AccountController?service=deleteAccount&accountId=${account.accountId}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
