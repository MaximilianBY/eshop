<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Shop</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div>
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="${contextPath}/login">Login</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#mynavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
    </nav>
</div>
<form method="post" action="${contextPath}/registration">
    <div class="container">
        <input type="hidden" name="command" value="register">
        <div class="col-md-8 offset-md-4">
            <h2>SignUp</h2>
            <p>Please, enter your credentials</p>
            <div class="form-group">
                <label for="login">Login:</label>
                <input type="text" class="form-control w-25" id="login"
                       placeholder="Enter Login" name="login"
                       required>
                <div class="invalid-feedback">Login should be entered!</div>
            </div>
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control w-25" id="name"
                       placeholder="Enter name" name="name"
                       required>
                <div class="invalid-feedback">Name should be entered!</div>
            </div>
            <div class="form-group">
                <label for="surname">Surname:</label>
                <input type="text" class="form-control w-25" id="surname"
                       placeholder="Enter surname" name="surname"
                       required>
                <div class="invalid-feedback">Surname should be entered!</div>
            </div>
            <div class="form-group">
                <label for="birthday">Date of birth:</label>
                <input type="date" class="form-control w-25" id="birthday"
                       placeholder="Enter birthday" name="birthday">
                <div class="invalid-feedback">Date of birth should be entered!</div>
            </div>
            <div class="form-group">
                <label for="email">E-mail:</label>
                <input type="email" class="form-control w-25" id="email" placeholder="Enter Email"
                       name="email" required>
                <div class="invalid-feedback">Email should be entered!</div>
            </div>
            <div class="form-group">
                <label for="phoneNumber">Phone number:</label>
                <input type="tel" class="form-control w-25" name="phoneNumber"
                       placeholder="Enter phone number" id="phoneNumber">
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control w-25" id="password"
                       placeholder="Enter password" name="password"
                       required>
                <div class="invalid-feedback">Password should be entered!</div>
                <label for="confirmPassword">Retype password:</label>
                <input type="password" class="form-control w-25" id="confirmPassword"
                       placeholder="Retype password" name="confirmPassword"
                       onChange="isPasswordMatch();"
                       required>
                <div id="divCheckPassword"></div>
                <div class="invalid-feedback">Password should be entered!</div>
            </div>
            <button type="submit" class="btn btn-primary" id="saveBtn">Register new user
            </button>
        </div>
    </div>
</form>
<script>
  function isPasswordMatch() {
    var password = $("#password").val();
    var confirmPassword = $("#confirmPassword").val();

    if (password !== confirmPassword) {
      $("#divCheckPassword").html("Passwords do not match!");
    } else {
      $("#divCheckPassword").html("Passwords match.");
    }
  }

  $(document).ready(function () {
    $("#confirm_password").keyup(isPasswordMatch);
  });

  document.getElementById('saveBtn').disabled = true;
  document.getElementById('login').addEventListener('keyup', e => {
    //Check for the input's value
    if (e.target.value === "") {
      document.getElementById('saveBtn').disabled = true;
    } else {
      document.getElementById('password').addEventListener('keyup', e => {
        document.getElementById('confirmPassword').addEventListener('keyup', c => {
          if (e.target.value === "" && c.target.value === "" || e.target.value !== c.target.value) {
            document.getElementById('saveBtn').disabled = true;
          } else {
            document.getElementById('saveBtn').disabled = false;
          }
        })
      });
    }
  });
</script>
</body>
</html>
