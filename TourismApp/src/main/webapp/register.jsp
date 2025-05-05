<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="css/register.css">
</head>
<body>
<!-- FORM ON LEFT -->
<div class="form-side">
    <div class="form-container">
        <h1>Register</h1>
        <form action="register" method="post">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <label for="role">Register As:</label>
            <select id="role" name="role">
                <option value="user">User</option>
            </select>

            <input type="submit" value="Register">
        </form>

        <form action="login.jsp" method="get">
            <input type="submit" value="Back to Login" class="register-btn">
        </form>

        <% String error = request.getParameter("error"); %>
        <% if (error != null) { %>
        <div class="error-box">
            <%= error %>
        </div>
        <% } %>
    </div>
</div>

<!-- IMAGE ON RIGHT -->
<div class="image-side">
    <!-- Background set via CSS -->
</div>
</body>
</html>