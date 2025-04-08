<%@ page import="com.project.service.PackageManager" %>
<%@ page import="com.project.model.TourismPackage" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  String id = request.getParameter("id");
  PackageManager.readPackages(); // Make sure data is loaded
  TourismPackage pkg = PackageManager.findPackage(id);
%>

<!DOCTYPE html>
<html>
<head>
  <title>Package Details</title>
  <link rel="stylesheet" href="css/view-package.css" />
</head>
<body>
<% if (pkg != null) { %>
<div class="details-container">
  <img src="images/<%= pkg.getImageFileName() %>" alt="<%= pkg.getName() %>" class="package-img" />
  <div class="name"><h1><%= pkg.getName() %></h1></div>
  <p><strong>Description:</strong> <%= pkg.getDescription() %></p>
  <p><strong>Price:</strong> $<%= pkg.getPrice() %></p>
  <p><strong>Duration:</strong> <%= pkg.getDurationDays() %> Days</p>

  <form method="post" action="payment.jsp">
  <input type="submit" value="Book Now" class="bookButton">
  </form>
</div>
<% } else { %>
<h2>Package not found</h2>
<% } %>
</body>
</html>
