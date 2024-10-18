<%
    // Display the login success message, if present
    String loginMessage = (String) session.getAttribute("loginMessage");
    if (loginMessage != null) {
%>
    <div class="alert alert-success">
        <%= loginMessage %>
    </div>
<%
        session.removeAttribute("loginMessage"); // Clear after displaying
    }
%>

<%
    // Display the login error message, if present
    String loginError = (String) session.getAttribute("loginError");
    if (loginError != null) {
%>
    <div class="alert alert-danger">
        <%= loginError %>
    </div>
<%
        session.removeAttribute("loginError"); // Clear after displaying
    }
%>
