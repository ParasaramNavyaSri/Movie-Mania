<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
    <title>${movie.id != null ? 'Add Movie' : 'Edit Movie'}</title>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styling.css">
</head>
<body>
<form action="AdminServlet" method="post">
    <h2 class="form-title">Add Movie</h2> 
    <input type="hidden" name="action" value="${movie.id != null ? 'insert' : 'Update'}"> <!-- Moved this up -->

    <div class="form-group">
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" value="${movie.title != null ? movie.title : ''}" required>
    </div>

    <div class="form-group">
        <label for="genre">Genre:</label>
        <input type="text" id="genre" name="genre" value="${movie.genre != null ? movie.genre : ''}" required>
    </div>

    <div class="form-group">
        <label for="price">Price:</label>
        <input type="number" id="price" name="price" value="${movie.price != null ? movie.price : ''}" step="0.01" required>
    </div>

    <div class="form-group">
        <label for="show_time">Show Time:</label>
        <input type="datetime-local" id="show_time" name="show_time" value="${movie.showTime != null ? movie.showTime : ''}" required>
    </div>

    <div class="form-group">
        <label for="image_url">Image URL:</label>
        <input type="text" id="image_url" name="image_url" placeholder="Image URL" required>
    </div>

    <div class="form-group">
        <button type="submit" class="submit-button">${movie.id != null ? 'Insert' : 'Update'}</button>
    </div>
</form>




</body>
</html>
