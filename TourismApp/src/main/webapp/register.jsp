<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="css/register.css">
</head>
<body>

<div class="form-wrapper">
    <h1>Register</h1>
    <form action="register" method="post">
        <label>Email</label>
        <input type="email" name="email" required>

        <label>Password</label>
        <input type="password" name="password" required>

        <label>Register As</label>
        <select name="role">
            <option value="user">User</option>
            <!--<option value="admin">Admin</option> -->
        </select>

        <input type="submit" value="Register">
    </form>

    <div class="footer-link">
        <a href="login.jsp">← Back to Login</a>
    </div>



    <% String error = request.getParameter("error"); %>
    <% if (error != null) { %>
    <div class="error-box">
        <%= error %>
    </div>
    <% } %>


    <style>
        .error-box {
            background: rgba(255, 0, 0, 0.1);
            color: #b30000;
            border: 1px solid rgba(255, 0, 0, 0.3);
            padding: 12px 20px;
            margin: 20px auto;
            width: fit-content;
            border-radius: 10px;
            font-weight: bold;
            text-align: center;
            backdrop-filter: blur(6px);
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            transition: all 0.3s ease-in-out;
        }
    </style>



</div>

</body>
</html>
