<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">Edit Account</h2>
    <form action="AccountController?service=updateAccount" method="post" class="mt-4">
        <input type="hidden" name="accountId" value="${account.accountId}">
        <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" class="form-control" id="username" name="username" value="${account.username}" required>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" value="${account.email}" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" name="password" value="${account.password}" required>
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">Phone</label>
            <input type="text" class="form-control" id="phone" name="phone" value="${account.phone}" required>
        </div>
        <div class="mb-3">
            <label for="address" class="form-label">Address</label>
            <input type="text" class="form-control" id="address" name="address" value="${account.address}" required>
        </div>
        <div class="mb-3">
            <label for="roleID" class="form-label">Role</label>
            <input type="number" class="form-control" id="roleID" name="roleId" value="${account.roleId}" required>
        </div>
        <div class="mb-3">
            <label for="status" class="form-label">Status</label>
            <select class="form-control" id="status" name="status" required>
                <option value="Active" ${account.status == 'Active' ? 'selected' : ''}>Active</option>
                <option value="Inactive" ${account.status == 'Inactive' ? 'selected' : ''}>Inactive</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Update Account</button>
    </form>
</div>
</body>
</html>
