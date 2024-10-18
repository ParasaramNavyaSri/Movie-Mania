<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment Success</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Payment Successful!</h2>
    <p>Your User ID: <%= request.getAttribute("userId") %></p>
    <a href="movie-list.jsp" class="btn btn-primary">Back to Movie List</a>
</div>
</body>
</html>
