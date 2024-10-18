<%
    String errorMessage = request.getParameter("error");
    if (errorMessage != null) {
        if (errorMessage.equals("invalid")) {
%>
            <div class="alert alert-danger">Invalid login. Please try again.</div>
<%
        } else if (errorMessage.equals("register_failed")) {
%>
            <div class="alert alert-danger">Registration failed. Please try again.</div>
<%
        }
    }
%>
