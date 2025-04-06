<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.project.model.TourismPackage" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>User Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            padding: 20px;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        .search-bar {
            text-align: center;
            margin: 20px 0;
        }

        .search-bar input {
            padding: 10px;
            width: 300px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 16px;
        }

        .card-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
        }

        .card {
            background-color: #fff;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            width: 300px;
            padding: 20px;
            transition: transform 0.2s;
        }

        .card:hover {
            transform: scale(1.03);
        }

        .card h3 {
            margin-top: 0;
            color: #1e90ff;
        }

        .card p {
            margin: 8px 0;
            color: #555;
        }

        .price {
            font-size: 18px;
            font-weight: bold;
            color: #27ae60;
            margin-top: 10px;
        }

        .no-results {
            text-align: center;
            color: #999;
            font-size: 18px;
            margin-top: 40px;
        }
    </style>
</head>
<body>

<h1>Available Packages</h1>

<div class="search-bar">
    <input type="text" id="searchInput" placeholder="Search packages by name or description..." onkeyup="filterPackages()" />
</div>

<div class="card-container" id="packageContainer">
    <%
        ArrayList<TourismPackage> packages = (ArrayList<TourismPackage>) request.getAttribute("packages");
        if (packages != null && !packages.isEmpty()) {
            for (TourismPackage pkg : packages) {
    %>
    <div class="card" data-name="<%= pkg.getName().toLowerCase() %>" data-description="<%= pkg.getDescription().toLowerCase() %>">
        <h3><%= pkg.getName() %></h3>
        <!--<p><strong>ID:</strong> <%= pkg.getId() %></p>-->
        <p><strong>Description:</strong> <%= pkg.getDescription() %></p>
        <p class="price">$<%= pkg.getPrice() %></p>
    </div>
    <%
        }
    } else {
    %>
    <div class="no-results">No packages found.</div>
    <%
        }
    %>
</div>

<script>
    function filterPackages() {
        const input = document.getElementById("searchInput").value.toLowerCase();
        const cards = document.querySelectorAll(".card");
        let visibleCount = 0;

        cards.forEach(card => {
            const name = card.getAttribute("data-name");
            const desc = card.getAttribute("data-description");
            const matches = name.includes(input) || desc.includes(input);

            card.style.display = matches ? "block" : "none";
            if (matches) visibleCount++;
        });

        // Show/hide no-results message
        const container = document.getElementById("packageContainer");
        const noResults = document.querySelector(".no-results");

        if (visibleCount === 0) {
            if (!noResults) {
                const msg = document.createElement("div");
                msg.className = "no-results";
                msg.textContent = "No matching packages found.";
                container.appendChild(msg);
            }
        } else {
            if (noResults) noResults.remove();
        }
    }
</script>

</body>
</html>
