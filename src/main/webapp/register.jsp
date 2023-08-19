<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/css/fontawesome.all.min.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="/static/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="/static/css/responsive.bootstrap4.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/css/adminlte.min.css">
    <title>Title</title>
</head>
<body style="background-image: url('https://haycafe.vn/wp-content/uploads/2022/02/Hinh-nen-may-tinh-toi-gian.jpg');">
<div class="container d-flex justify-content-center ">
    <div class="col-5" style="margin-top: 150px;">
        <h1 class="mb-3"
            style="font-weight: bold; font-size: 44px; text-align: center; margin-bottom: 30px">Quản lý trường học</h1>
        <!-- Pills navs -->
        <ul class="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
            <li class="nav-item mt-4" role="presentation">
                <a class="nav-link" id="tab-login" data-mdb-toggle="pill" href="/login.jsp" role="tab"
                   aria-controls="pills-login" aria-selected="true">Login</a>
            </li>
            <li class="nav-item mt-4" role="presentation">
                <a class="nav-link active" id="tab-register" data-mdb-toggle="pill" href="/register.jsp" role="tab"
                   aria-controls="pills-register" aria-selected="false">Register</a>
            </li>
        </ul>
        <!-- Pills navs -->

        <!-- Pills content -->
        <div class="tab-content">
            <div class="tab-pane fade show active" id="pills-register" role="tabpanel" aria-labelledby="tab-register">
                <form action="/school-management/register" method="post" id="form-register">
                    <input type="hidden" name="action" value="register">
                    <!-- Username input -->
                    <div class="form-outline mb-4">
                        <label class="form-label" for="registerUsername">Email</label>
                        <input type="email" id="registerUsername" name="username" class="form-control"/>
                    </div>

                    <!-- Password input -->
                    <div class="form-outline mb-4">
                        <label class="form-label" for="registerPassword">Password</label>
                        <input type="password" id="registerPassword" name="password" class="form-control"/>
                    </div>

                    <!-- Repeat Password input -->
                    <div class="form-outline mb-4">
                        <label class="form-label" for="registerRepeatPassword">Repeat password</label>
                        <input type="password" id="registerRepeatPassword" name="repassword" class="form-control"/>
                    </div>

                    <!-- Checkbox -->
                    <c:if test="${not empty error}">
                        <div>
                            <p style="color: red; font-weight: bold; margin-bottom: 10px; margin-top: -5px">${error}</p>
                        </div>
                    </c:if>

                    <!-- Submit button -->
                    <button id="btnRegister" class="btn btn-primary btn-block mb-4">Sign up</button>
                </form>

                <!-- Register buttons -->
                <div class="text-center">
                    <p>I have a account? <a href="/login.jsp">Login</a></p>
                </div>
            </div>
            <!-- Pills content -->
        </div>
    </div>
</div>
<script src="/static/js/register.js"></script>
<!-- jQuery -->
<script src="/static/js/jquery.min.js"></script>
</body>
</html>