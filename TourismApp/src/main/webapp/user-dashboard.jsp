<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.project.model.TourismPackage" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>User Dashboard</title>
    <link rel="stylesheet" href="css/user-dashboard.css" />
</head>
<body>

<h1 class="title">Explore Tourism Packages</h1>

<div class="container">
    <div class="top-bar">
        <form action="login.jsp" method="post">
            <input type="submit" value="Logout" class="logout-btn">
        </form>
    </div>

    <div class="search-bar">
        <div class="input-wrapper">
            <span class="search-icon">🔍</span>
            <input type="text" id="searchInput" placeholder="Search by name or description..." onkeyup="filterPackages()" />
        </div>
    </div>

    <div class="card-container" id="packageContainer">
        <%
            ArrayList<TourismPackage> packages = (ArrayList<TourismPackage>) request.getAttribute("packages");
            if (packages != null && !packages.isEmpty()) {
                for (TourismPackage pkg : packages) {
                    String imageName = pkg.getName().toLowerCase().replaceAll(" ", "-") + ".jpg";
        %>
        <div class="card" data-name="<%= pkg.getName().toLowerCase() %>" data-description="<%= pkg.getDescription().toLowerCase() %>">
            <div class="card-image" style="background-image: url('images/<%= imageName %>');"></div>
            <div class="card-content">
                <h3><%= pkg.getName() %></h3>
                <p><%= pkg.getDescription() %></p>
                <div class="price-section">
                    <span>Price</span>
                    <span class="price">$<%= pkg.getPrice() %></span>
                </div>
            </div>
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
