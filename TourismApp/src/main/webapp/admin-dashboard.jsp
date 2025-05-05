<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.project.model.TourismPackage" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="css/adminDashboard.css" />
</head>
<body>

<div class="admin-header">
    <div class="admin-title">Tourism Packages Management</div>
    <div>
        <div class="admin-buttons">
            <a href="login.jsp" class="admin-btn logout">Logout</a>
            <a href="add-package.jsp" class="admin-btn add-btn">Add New Package</a>
        </div>

    </div>
</div>

<div class="search-bar-admin">
    <span class="search-iconn">🔍</span>
    <input type="text" id="searchInput" placeholder="Search by name or description..." onkeyup="filterAdminPackages()" />
</div>

<table class="admin-table" id="packageTable">
    <thead>
    <tr>
        <th>Package ID</th>
        <th>Package Name</th>
        <th>Description</th>
        <th>Duration</th>
        <th>Price</th>
        <th>Accommodation Type</th>
        <th>Discount</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>


    <%
        String message = request.getParameter("message");
        if (message != null && !message.isEmpty()) {
    %>
    <div style="background-color: #d4edda; color: #155724; padding: 10px; border-radius: 5px; margin: 10px auto; width: fit-content; text-align: center;">
        <%= message %>
    </div>
    <%
        }
    %>



    <%
        ArrayList<TourismPackage> packages = (ArrayList<TourismPackage>) request.getAttribute("packages");
        if (packages != null && !packages.isEmpty()) {
            for (TourismPackage pkg : packages) {
    %>
    <tr data-name="<%= pkg.getName().toLowerCase() %>" data-description="<%= pkg.getDescription().toLowerCase() %>">
        <td><%= pkg.getId()%></td>
        <td><%= pkg.getName() %></td>
        <td><%= pkg.getDescription() %></td>
        <td><%= pkg.getDurationDays() %> Days</td>
        <td>LKR <%= pkg.getPrice() %></td>
        <td><%= pkg.getAccommodationType() %></td>
        <td><%= pkg.getDiscount() %> %</td>
        <td>
            <a href="edit-package?id=<%= pkg.getId() %>" class="action-btn edit-btn">Edit</a>
            <a href="delete-package?id=<%= pkg.getId() %>" class="action-btn delete-btn" onclick="return confirm('Are you sure you want to delete this package?');">Delete</a>
            <a href="adminViewPackage.jsp?id=<%= pkg.getId() %>" class="action-btn view-btn">View</a>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr><td colspan="5">No packages available.</td></tr>
    <%
        }
    %>
    </tbody>
</table>

<div class="pagination-controls">
    <!-- Add pagination controls here if needed -->
</div>

<script>
    function filterAdminPackages() {
        const input = document.getElementById("searchInput").value.toLowerCase();
        const rows = document.querySelectorAll("#packageTable tbody tr");
        let found = false;

        rows.forEach(row => {
            const id = row.getAttribute("data-id");
            const name = row.getAttribute("data-name");
            const desc = row.getAttribute("data-description");
            const match = name.includes(input) || desc.includes(input);
            row.style.display = match ? "table-row" : "none";
            if (match) found = true;
        });
    }
</script>

</body>
</html>
