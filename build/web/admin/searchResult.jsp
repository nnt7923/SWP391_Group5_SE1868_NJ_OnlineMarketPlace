<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Kết Quả Tìm Kiếm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h2 class="mt-5">Kết Quả Tìm Kiếm</h2>
        <a href="account?action=add" class="btn btn-primary mb-3">Thêm Tài Khoản Mới</a>
        <form action="account" method="get" class="d-flex mb-3">
            <input type="hidden" name="action" value="search">
            <input type="text" name="keyword" class="form-control me-2" placeholder="Tìm kiếm theo tên...">
            <button type="submit" class="btn btn-success">Tìm kiếm</button>
        </form>

        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Password</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>Role</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="account" items="${accounts}">
                    <tr>
                        <td>${account.accountId}</td>
                        <td>${account.username}</td>
                        <td>${account.password}</td>
                        <td>${account.email}</td>
                        <td>${account.phone}</td>
                        <td>${account.address}</td>
                        <td>${account.roleId}</td>
                        <td>${account.status}</td>
                        <td>
                            <a href="account?action=edit&id=${account.accountId}" class="btn btn-warning">Sửa</a>
                            <a href="account?action=delete&id=${account.accountId}" class="btn btn-danger">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="account?action=list" class="btn btn-secondary">Quay lại</a>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
