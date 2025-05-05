<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.project.model.TourismPackage" %>
<%
  TourismPackage pkg = (TourismPackage) request.getAttribute("pkg");
  if (pkg == null) {
    response.sendRedirect("admin-dashboard?error=Package not found");
    return;
  }
%>
<!DOCTYPE html>
<html>
<head>
  <title>Edit Package</title>
  <link rel="stylesheet" type="text/css" href="css/edit-package.css">
</head>
<body>
<div class="ui-bg">
  <div class="card">
    <div class="card-header header-blue">
      <div class="header-line"></div>
      <button class="close-btn" type="button" aria-label="Close" onclick="window.location.href='admin-dashboard'">&times;</button>
    </div>
    <h2>Edit Tourism Package</h2>
    <form method="post" action="edit-package" enctype="multipart/form-data">
      <input type="hidden" name="id" value="<%= pkg.getId() %>"/>

      <div class="form-group">
        <label>Name:</label>
        <input type="text" name="name" value="<%= pkg.getName() %>" required/>
      </div>
      <div class="form-group">
        <label>Description:</label>
        <textarea name="description" rows="3" required><%= pkg.getDescription() %></textarea>
      </div>
      <div class="form-group">
        <label>Price:</label>
        <input type="text" name="price" value="<%= pkg.getPrice() %>" required/>
      </div>
      <div class="form-group">
        <label>Duration (Days):</label>
        <input type="number" name="durationDays" value="<%= pkg.getDurationDays() %>" required/>
      </div>


      <div class="form-group">
        <label for="accommodationType">Accommodation Type:</label>
        <select name="accommodationType" id="accommodationType" required>
          <option value="">-- Select Type --</option>
          <option value="3 Star" <%= pkg.getAccommodationType().equals("3 Star") ? "selected" : "" %>>3 Star</option>
          <option value="5 Star" <%= pkg.getAccommodationType().equals("5 Star") ? "selected" : "" %>>5 Star</option>
          <option value="7 Star" <%= pkg.getAccommodationType().equals("7 Star") ? "selected" : "" %>>7 Star</option>
          <option value="Resort" <%= pkg.getAccommodationType().equals("Resort") ? "selected" : "" %>>Resort</option>
        </select>
      </div>


      <div class="form-group">
        <label>Discount(%):</label>
        <input type="number" name="discount" value="<%= pkg.getDiscount() %>" required/>
      </div>

      <div class="form-group">
        <label>Current Image:</label>
        <% if (pkg.getImageFileName() != null && !pkg.getImageFileName().isEmpty()) { %>
        <img src="images/<%= pkg.getImageFileName() %>" alt="Current Package Image" width="150" height="auto"/>
        <% } else { %>
        <p>No image uploaded</p> <!-- Or show a placeholder image -->
        <% } %>
      </div>

      <div class="form-group">
        <label>Update Image:</label>
        <input type="file" name="image" accept="image/*"/>
      </div>

      <button type="submit" class="register-btn">Update Package</button>
      <a href="admin-dashboard" class="back-link">Back to Dashboard</a>
    </form>
  </div>
</div>
</body>
</html>
