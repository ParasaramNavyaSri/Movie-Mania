<%@ page import="java.util.List" %>
<html>
<head>
    <title>Contact Us</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2>Contact Us</h2>
    <%
        // Check for a success message in the session
        String successMessage = (String) session.getAttribute("successMessage");
        if (successMessage != null) {
    %>
        <div class="alert alert-success" role="alert">
            <%= successMessage %>
        </div>
    <%
            // Clear the message after displaying it
            session.removeAttribute("successMessage");
        }
    %>
    <form action="SubmitContactFormServlet" method="post">
        <div class="mb-3">
            <label for="email" class="form-label">Your Email</label>
            <input type="email" class="form-control" id="email" name="email" required placeholder="Enter your email">
        </div>
        <div class="mb-3">
            <label for="message" class="form-label">Message</label>
            <textarea class="form-control" id="message" name="message" required rows="3" placeholder="Your message"></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>