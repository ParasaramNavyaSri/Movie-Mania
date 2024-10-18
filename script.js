
        document.addEventListener('DOMContentLoaded', function() {
            document.getElementById('myForm').addEventListener('submit', function(event) {
                // Prevent the form from submitting if validation fails
                event.preventDefault();
                
                // Get the ID and password values
                const username = document.getElementById('uname').value;
                const password = document.getElementById('pwd').value;

                // Define the validation rules
                const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z.-]+\.[a-zA-Z]{2,6}$/;
                const minLength = 8;
                const hasUpperCase = /[A-Z]/.test(password);
                const hasLowerCase = /[a-z]/.test(password);
                const hasDigit = /[0-9]/.test(password);
                const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password);
                
                let errorMessage = '';
                let successMessage = '';

                // Check the email and password criteria
                if (!emailPattern.test(username)) {
                    errorMessage = 'Invalid email format.';
                } else if (password.length < minLength) {
                    errorMessage = `Password must be at least ${minLength} characters long.`;
                } else if (!hasUpperCase) {
                    errorMessage = 'Password must contain at least one uppercase letter.';
                } else if (!hasLowerCase) {
                    errorMessage = 'Password must contain at least one lowercase letter.';
                } else if (!hasDigit) {
                    errorMessage = 'Password must contain at least one digit.';
                } else if (!hasSpecialChar) {
                    errorMessage = 'Password must contain at least one special character.';
                } else {
                	 successMessage = 'Login Successful';
                     document.getElementById('error-message').textContent = ''; // Clear error message
                     document.getElementById('successful-message').textContent = successMessage;
                     setTimeout(function() {
                         document.getElementById('myForm').submit(); // Submit the form after validation success
                     }, 500); // Allow time to display success message
                     return; // Exit to avoid displaying the error message 
                }
                
                // Display the error or success message
                document.getElementById('error-message').textContent = errorMessage;
                document.getElementById('successful-message').textContent = successMessage;
            });
        });
function selectCity(city) {
    alert('You selected: ' + city);
    // Redirect or perform an action based on the selected city
    // Example: window.location.href = '/path/to/your/page?city=' + city;
}