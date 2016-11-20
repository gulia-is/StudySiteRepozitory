<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
            <form class="navbar-form" role="form" method="post" action="/logout">
                <a role="button" class="btn btn-primary" href="profile">#${user.getNick()}</a>
                <button type="submit" class="btn btn-danger">Sign out</button>
            </form>
        </div>
    </div>
</div>

<div class="container col-lg-12 fromTop">
    <div class="col-lg-2">
        <div class="btn-scroll">
            <img src="http://data.whicdn.com/avatars/9852954/profile.png?1416261047" alt="SCROLL">
        </div>
    </div>
    <div class="col-lg-8 ">
            <div class='postdiv'>
                <c:forEach items="${mess.getArrayP()}" var="postP">
                    <div class="mess">
                        <p>#${postP.getNick()}</p>
                        <p>${postP.getText()}</p>
                        <img src="${postP.getRef()}" alt="PICTURE">
                    </div>
                </c:forEach>
            </div>

          </div>
    <div class="col-lg-2"></div>
</div>

<footer class="container footer col-lg-12 copy">
    <p>&copy;YaHin Media Group 2k16</p>
</footer>
</body>
</html>