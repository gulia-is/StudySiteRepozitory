<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <title>Registration</title>
    <script
            src="https://code.jquery.com/jquery-3.1.1.js"
            integrity="sha256-16cdPddA6VdVInumRGo6IbivbERE8p7CQR3HzTBuELA="
            crossorigin="anonymous">
    </script>

    <script type="text/javascript" src="http://cdn.jsdelivr.net/jquery.validation/1.15.0/jquery.validate.min.js"></script>

    <script>
        <%@include file='/js/checkForm.js' %>
    </script>

    <style>
        <%@include file='/css/styles.css' %>
    </style>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="pull-left">
            <a class="navbar-brand" href="enter">#Donats_world</a>
        </div>
        <div class="pull-right">
            <div class="form-group">
                <form id="form_signin" class="navbar-form" role="form" method="post" action="/enter">
                    <div class="form-group">
                        <input type="text" placeholder="Email" class="form-control" id="emailSI" name="userEmail" >
                    </div>
                    <div class="form-group">
                        <input type="password" placeholder="Password" class="form-control" id="passSI" name="userPassword">
                    </div>
                    <button type="submit" class="btn btn-success">Sign in</button>
                    <a role="button" class="btn btn-warning sing-up" href="signup">Sing up</a>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="container col-lg-12 fromTop">
    <div class="col-lg-2">
        <div class="btn-scroll">
            <img src="http://data.whicdn.com/avatars/9852954/profile.png?1416261047" alt="SCROLL">
        </div>
    </div>
    <div class="col-lg-8 whitebg">
        <form role="form" id="form_signup" name="form_signup" method="post" action="/signup">
            <div class="form-group">
                <label for="nickSU">#Nickname</label>
                <input type="text" class="form-control" id="nickSU" name="nick_su" placeholder="Nickname">
            </div>
            <div class="form-group">
                <label for="emailSU">Email</label>
                <input type="email" class="form-control" id="emailSU" name="email_su" placeholder="Email">
            </div>
            <div class="form-group">
                <label for="passSU1">Password</label>
                <input type="password" class="form-control" id="passSU1" name="pass_su1" placeholder="Password">
                <input type="password" class="form-control" id="passSU2" name="pass_su2" placeholder="Repeat password">
            </div>
            <button type="submit" class="btn btn-success">Sing up</button>
        </form>
    </div>
    <div class="col-lg-2"></div>
</div>


<footer class="container footer col-lg-12">
    <p>&copy;YaHin Media Group 2k16</p>
</footer>
</body>
</html>