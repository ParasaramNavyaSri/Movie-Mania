<%@ page import="java.util.List" %>
<%@ page import="model.Movie" %>
<%@ page import="javax.servlet.http.HttpSession" %>
 
<%
    HttpSession s = request.getSession();
    @SuppressWarnings("unchecked")
    List<Movie> cart = (List<Movie>) s.getAttribute("cart");
%>

<html>
<head>
    <title>Cart</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
    html{
cursor:pointer;
 scroll-behavior: smooth; 
}
    .card:hover {
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2); /* Increase shadow on hover */
}
.card img {
    object-fit: cover; /* Crop image to fit */
}
.card-img-top {
    margin-top: 15px;
    text-align: center; /* Center the image within its container */
    max-width: 100%;  /* Allow the image to cover the full width of its container */
    height: 50%;     /* Maintain aspect ratio by adjusting height automatically */
    margin: 0 auto;  /* Center the image horizontally */
}
    </style>
</head>
<body>
<div class="container mt-4">
    <h2>Your Cart</h2>
    <%
        if (cart == null || cart.isEmpty()) {
    %>
        <p>Your cart is empty.</p>
    <%
        } else {
    %>
        <div class="row">
            <%
                double total = 0;
                for (int i = 0; i < cart.size(); i++) {
                    Movie movie = cart.get(i);
                    total += movie.getPrice();
            %>
            <div class="col-md-4 mb-4">
                <div class="card">
                    <img src="<%= movie.getImageUrl() %>" class="card-img-top" alt="<%= movie.getTitle() %>">
                    <div class="card-body">
                        <h5 class="card-title"><%= movie.getTitle() %></h5>
                        <p class="card-text"><strong>Genre:</strong> <%= movie.getGenre() %></p>
                        <p class="card-text"><strong>Price:</strong> &#8377;<%= movie.getPrice() %></p>
                        <p class="card-text"><strong>Show Time:</strong> <%= movie.getShowTime() %></p>
                        <!-- Remove button form -->
                        <form action="removeFromCartServlet" method="post" style="display:inline;">
                            <input type="hidden" name="movieIndex" value="<%= i %>">
                            <button type="submit" class="btn btn-danger btn-sm">Remove</button>
                        </form>
                    </div>
                </div>
            </div>
            <% } %>
        </div>
        <div class="d-flex justify-content-between">
            <h5>Total: &#8377;<%= total %></h5>
            <form action="paymentServlet" method="post">
                <button type="submit" class="btn btn-success">Proceed to Payment</button>
            </form>
        </div>
    <%
        }
    %>
</div>
</body>
</html>
