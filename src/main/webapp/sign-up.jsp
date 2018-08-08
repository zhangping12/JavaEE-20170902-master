<%--
  Created by IntelliJ IDEA.
  User: mingfei
  Date: 1/26/18
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>sign up page</title>
    <script src="js/jquery-3.3.1.min.js"></script>
    <script>
        $(function () {
            $('#email').blur(function () {
                $.ajax({
                    url: '/user', // request url
                    type: 'post', // request method: get, post, put, delete...
                    data: { // request data
                        email: $('#email').val(),
                        action: 'isEmailExisted'
                    },
                    success: function (res) { // success callback
                        if (res === 'true') {
                            $('small').text('Email is already existed.').css('color', 'red');
                        } else {
                            $('small').text('Email is available.').css('color', 'green');
                        }
                    }
                });
            });
        });
    </script>
</head>
<body>
<h1>Sign up page</h1>
<form action="/user" method="post">
    <input type="hidden" name="action" value="signUp">
    <input id="email" name="email" placeholder="Email"> <small></small><br>
    <input name="username" placeholder="Username"><br>
    <input type="password" name="password" placeholder="Password"><br>
    <input type="submit" value="Sign up">
</form>
${requestScope.message}
</body>
</html>
