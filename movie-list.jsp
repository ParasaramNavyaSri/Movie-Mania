<%@ page import="java.util.List" %>
<%@ page import="model.Movie" %>
<%@ page import="dao.MovieDAO" %>
<%@ page import="dao.UserDAO" %>
<html>
<head>
    <title>Movie Mania</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <link href="styles.css" rel="stylesheet"> 
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.min.js"></script>
    
    
    <script src="script.js"></script>
    <jsp:include page="login.jsp" />
    <jsp:include page="register.jsp" />
    <style>
        .card-img-top {
            margin-top: 15px;
            text-align: center;
            max-width: 100%;
            height: 100%;
            margin: 0 auto;
        }
        html {
            cursor: pointer;
            scroll-behavior: smooth; 
        }
    </style>


  
</head>
<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-danger">
    <div class="container">
        <a class="navbar-brand" href="uploads/logo.png">
            <img src="uploads/logo.png" height="40" alt="MovieMania" class="d-inline-block align-text-top">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a href="#movieList" class="nav-link">Movies</a>
                </li>
                <li class="nav-item">
                    <a href="cart.jsp" class="nav-link">Cart</a>
                </li>
                <li class="nav-item">
                    <a href="contactUs.jsp" class="nav-link">Contact Us</a>
                </li>
            </ul>
            <ul class="navbar-nav ms-auto">
                <li class="nav-item dropdown">
                    <a class="btn btn-danger dropdown-toggle" href="#" id="cityDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <h6 class="mb-0">Select City</h6>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="cityDropdown">
                        <li><a class="dropdown-item" href="#" onclick="selectCity('Vijayawada')">Vijayawada</a></li>
                        <li><a class="dropdown-item" href="#" onclick="selectCity('Hyderabad')">Hyderabad</a></li>
                        <li><a class="dropdown-item" href="#" onclick="selectCity('Chennai')">Chennai</a></li>
                        <li><a class="dropdown-item" href="#" onclick="selectCity('Bangalore')">Bangalore</a></li>
                        <li><a class="dropdown-item" href="#" onclick="selectCity('Mumbai')">Mumbai</a></li>
                        <li><a class="dropdown-item" href="#" onclick="selectCity('Pune')">Pune</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <button class="btn btn-warning me-2" id="loginBtn" data-bs-toggle="modal" data-bs-target="#loginModal">
                        <h6 class="mb-0">Sign In</h6>
                    </button>
                </li>
                <li class="nav-item">
                    <button class="btn btn-warning" id="register" data-bs-toggle="modal" data-bs-target="#registerModal">
                        <h6 class="mb-0">Register</h6>
                    </button>
                </li>
            </ul>
        </div>
    </div>
</nav>


<div class="container mt-4" id="movieList">
    <h2>Movie List</h2>
    <div class="row">
        <%
            List<Movie> listMovies = MovieDAO.getAllMovies();
            for (Movie movie : listMovies) {
        %>
        <div class="col-md-4 mb-4">
            <div class="card">
                <img src="<%= movie.getImageUrl() %>" class="card-img-top" alt="<%= movie.getTitle() %>">
                <div class="card-body">
                    <h5 class="card-title"><%= movie.getTitle() %></h5>
                    <p class="card-text"><strong>Genre:</strong> <%= movie.getGenre() %></p>
                    <p class="card-text"><strong>Price:</strong>&#8377;<%= movie.getPrice() %></p>
                    <p class="card-text"><strong>Show Time:</strong> <%= movie.getShowTime() %></p>
                    <form action="cartServlet" method="post">
                        <input type="hidden" name="movieId" value="<%= movie.getId() %>">
                        <button type="submit" class="btn btn-warning btn-sm">Add to Cart</button>
                    </form>
                    <a href="AdminServlet?action=edit&id=<%= movie.getId() %>" class="btn btn-primary btn-sm">Edit</a>
                    <a href="AdminServlet?action=delete&id=<%= movie.getId() %>" class="btn btn-danger btn-sm">Delete</a>
                </div>
            </div>
        </div>
        <% } %>
    </div>
    <a href="AdminServlet?action=new" class="btn btn-success">Add New Movie</a>
</div>

<!-- Login Modal -->
<div id="loginModal" class="modal fade" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content animate">
            <div class="modal-header">
                <h5 class="modal-title">Login</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="loginForm" action="LoginServlet" method="post">
                    <div class="mb-3">
                        <label for="uname" class="form-label"><b>Username</b></label>
                        <input type="text" class="form-control" id="uname" placeholder="Enter Username" name="uname" required>
                    </div>
                    <div class="mb-3">
                        <label for="pwd" class="form-label"><b>Password</b></label>
                        <input type="password" class="form-control" id="pwd" placeholder="Enter Password" name="pwd" required>
                    </div>
                    <div id="error-message" style="color: red;"></div>
                    <button type="submit" class="btn btn-primary">Login</button>
                    <div class="form-check mt-2">
                        <input type="checkbox" class="form-check-input" id="rememberMe" checked="checked" name="remember">
                        <label class="form-check-label" for="rememberMe">Remember me</label>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                <span class="psw"><a href="#">Forgot password?</a></span>
            </div>
        </div>
    </div>
</div>

<!-- Registration Modal -->
<div id="registerModal" class="modal fade" tabindex="-1" aria-labelledby="registerModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content animate">
            <div class="modal-header">
                <h5 class="modal-title">Register</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="registerForm" action="RegisterServlet" method="post">
                    <div class="mb-3">
                        <label for="regUname" class="form-label"><b>Username</b></label>
                        <input type="text" class="form-control" id="regUname" placeholder="Enter Username" name="uname" required>
                    </div>
                    <div class="mb-3">
                        <label for="regPwd" class="form-label"><b>Password</b></label>
                        <input type="password" class="form-control" id="regPwd" placeholder="Enter Password" name="pwd" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Register</button>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                <span class="psw"><a href="#loginModal" data-bs-toggle="modal" data-bs-target="#loginModal">Already have an account? Login</a></span>
            </div>
        </div>
    </div>
</div>

</body>
</html>

