<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Package</title>
    <link rel="stylesheet" type="text/css" href="css/add-package.css">
</head>
<body>

<div class="container">
    <div class="top-bar">
        <form action="login.jsp" method="post">
            <input type="submit" value="Logout" class="logout-btn">
        </form>
    </div>

    <h2>Add New Tourism Package</h2>

    <!-- Important: Use enctype="multipart/form-data" -->
    <form action="add-package" method="POST" class="form-box" enctype="multipart/form-data">
        <label for="id">Package ID:</label>
        <input type="text" name="id" id="id" required>

        <label for="name">Package Name:</label>
        <input type="text" name="name" id="name" required>

        <label for="description">Description:</label>
        <input type="text" name="description" id="description" required>

        <label for="price">Price:</label>
        <input type="number" name="price" id="price" step="0.01" required>

        <label for="imageFileName">Image:</label>
        <input type="file" name="image" accept="image/*" required>

        <input type="submit" value="Add Package" class="submit-btn">
    </form>
</div>

</body>
</html>
