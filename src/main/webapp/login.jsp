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
                <a class="nav-link active" id="tab-login" data-mdb-toggle="pill" href="/login.jsp" role="tab"
                   aria-controls="pills-login" aria-selected="true">Login</a>
            </li>
            <li class="nav-item mt-4" role="presentation">
                <a class="nav-link" id="tab-register" data-mdb-toggle="pill" href="/register.jsp" role="tab"
                   aria-controls="pills-register" aria-selected="false">Register</a>
            </li>
        </ul>
        <!-- Pills navs -->

        <!-- Pills content -->
        <div class="tab-content">
            <div class="tab-pane fade show active" id="pills-login" role="tabpanel" aria-labelledby="tab-login">
                <form action="/school-management/login" method="post">
                    <input type="hidden" name="action" value="login"/>
                    <!-- Email input -->
                    <div class="form-outline mb-4">
                        <label class="form-label" for="loginName">Email</label>
                        <input type="email" id="loginName" name="username" class="form-control"/>
                    </div>

                    <!-- Password input -->
                    <div class="form-outline mb-4">
                        <label class="form-label" for="loginPassword">Password</label>
                        <input type="password" id="loginPassword" name="password" class="form-control"/>
                    </div>

                    <c:if test="${not empty error}">
                        <div>
                            <p style="color: red; font-weight: bold; margin-bottom: 10px; margin-top: -5px">${error}</p>
                        </div>
                    </c:if>

                    <!-- 2 column grid layout -->
                    <div class="row mb-4">
                        <div class="col-md-6 d-flex justify-content-center">
                            <!-- Checkbox -->
                            <div class="form-check mb-3 mb-md-0">
                                <input class="form-check-input" type="checkbox" value="" id="loginCheck" checked/>
                                <label class="form-check-label" for="loginCheck"> Remember me </label>
                            </div>
                        </div>

                        <div class="col-md-6 d-flex justify-content-center">
                            <!-- Simple link -->
                            <a href="#!">Forgot password?</a>
                        </div>
                    </div>

                    <!-- Submit button -->
                    <button type="submit" class="btn btn-primary btn-block mb-4">Sign in</button>
                </form>

                <!-- Register buttons -->
                <div class="text-center">
                    <p>Not a member? <a href="/register.jsp">Register</a></p>
                </div>
            </div>
        </div>
        <!-- Pills content -->
    </div>
</div>
</body>
</html>