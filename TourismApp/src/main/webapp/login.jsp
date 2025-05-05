<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
<div class="left-side">
    <!-- Image will be set via CSS background -->
</div>

<div class="right-side">
    <div class="form-container">
        <h1>Welcome!</h1>
        <form action="login" method="post">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <label for="role">Login As:</label>
            <select id="role" name="role">
                <option value="user">User</option>
                <option value="admin">Admin</option>
            </select>

            <input type="submit" value="Login">
        </form>

        <form action="register.jsp" method="get">
            <input type="submit" value="Register" class="register-btn">
        </form>

        <% String error = request.getParameter("error"); %>
        <% if (error != null) { %>
        <div class="error-box">
            <%= error %>
        </div>
        <% } %>
    </div>
</div>
</body>
</html>