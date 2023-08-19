<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>[Class]</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/css/fontawesome.all.min.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="/static/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="/static/css/responsive.bootstrap4.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/css/adminlte.min.css">
    <link rel="stylesheet" href="/static/css/style.css">
</head>

<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <!-- Navbar -->
    <nav class="main-header navbar navbar-expand navbar-white navbar-light">
        <!-- Left navbar links -->
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
            </li>
            <li class="nav-item d-none d-sm-inline-block">
                <%--                <a href="" class="nav-link"><b>User:&ensp;</b><b id="valUsername">${account.username}</b></a>--%>
                <%--                <p id="roleUser" style="display: none;">${account.role}</p>--%>
                <%--                <p id="maBnUser" style="display: none;">${account.benhNhan.mabn}</p>--%>
            </li>
        </ul>
    </nav>
    <!-- /.navbar -->

    <!-- Main Sidebar Container -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <!-- Brand Logo -->
        <a href="/starter.html" class="brand-link">
            <img src="/static/img/logo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
                 style="opacity: .8">
            <span class="brand-text font-weight-light">Quản lý trường học</span>
        </a>

        <!-- Sidebar -->
        <div class="sidebar">
            <!-- Sidebar Menu -->
            <nav class="mt-2">
                <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu"
                    data-accordion="false">
                    <li class="nav-item">
                        <a href="/subjects" class="nav-link">
                            <span class="nav-icon badge">M</span>
                            <p>[Môn học]</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/students" class="nav-link">
                            <span class="nav-icon badge">H</span>
                            <p>[Sinh viên]</p>
                        </a>
                    </li>
                    <li class="nav-item option-khoa">
                        <a href="/teachers" class="nav-link">
                            <span class="nav-icon badge">G</span>
                            <p>[Giảng viên]</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/modules" class="nav-link">
                            <span class="nav-icon badge">H</span>
                            <p>[Học phần]</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/semesters" class="nav-link">
                            <span class="nav-icon badge">H</span>
                            <p>[Học kỳ]</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/classes" class="nav-link active">
                            <span class="nav-icon badge">L</span>
                            <p>[Lớp học]</p>
                        </a>
                    </li>
                    <li class="nav-item option-khoa">
                        <a href="/departments" class="nav-link">
                            <span class="nav-icon badge">K</span>
                            <p>[Khoa]</p>
                        </a>
                    </li>
                    <li class="nav-item option-khoa">
                        <a href="/AcademicYears" class="nav-link">
                            <span class="nav-icon badge">N</span>
                            <p>[Niên khóa]</p>
                        </a>
                    </li>
                </ul>
            </nav>
            <!-- /.sidebar-menu -->
        </div>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <div class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1 class="m-0">Danh sách lớp học</h1>
                        <input id="mess" value="${message}" hidden/>
                        <%--                        <c:if test="${message != null}">--%>
                        <%--                            <p style="color: red; font-size:15px;">${message}</p>--%>
                        <%--                        </c:if>--%>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                        </ol>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.container-fluid -->
        </div>
        <!-- /.content-header -->

        <!-- Main content -->
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <button id="btnCreate" class="btn btn-primary option-khoa">Tạo mới</button>
                            </div>
                            <div class="card-header">
                                <form action="/classes" method="post">
                                    <input type="hidden" name="action" value="find"/>
                                    <input type="text" name="id" placeholder="Mã lớp học"/>
                                    <button type="submit" class="btn btn-primary option-khoa">Tìm kiếm</button>
                                </form>
                            </div>
                            <!-- /.card-header -->
                            <div class="card-body">
                                <table id="products" class="table table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th>Mã lớp học</th>
                                        <th>Tên lớp học</th>
                                        <th>Giáo viên</th>
                                        <th>Khoa</th>
                                        <th>Niên khóa</th>
                                        <th style="text-align:center" class="option-khoa">Lựa chọn</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${classes}" var="a">
                                        <tr>
                                            <td>${a.id}</td>
                                            <td>${a.className}</td>
                                            <td>${a.teacher.teacherName}</td>
                                            <td>${a.department.departmentName}</td>
                                            <td>${a.academicYear.academicYear}</td>
                                            <td style="text-align: center;" class="option-khoa">
                                                <button class="btn btn-warning btn-sm"
                                                        onclick="GetData('${a.id}','${a.className}',
                                                                '${a.teacher.id}', '${a.department.id}',
                                                                '${a.academicYear.id}')"
                                                >Sửa
                                                </button>
                                                |
                                                <button class="btn btn-danger btn-sm"
                                                        onclick="DeleteData('${a.id}','${a.className}')">Xóa
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>
                            </div>
                            <!-- /.card-body -->
                        </div>
                        <!-- /.card -->
                    </div>
                    <!-- /.col-md-6 -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <!-- Trigger/Open The Modal -->
    <!-- <button id="myBtn">Open Modal</button> -->

    <!-- The Modal -->
    <div id="myModal" class="modal">

        <!-- Modal content -->
        <div class="modal-content">
            <h3 class="modal-title">Sửa thông tin lớp học</h3>
            <div class="close">&times;</div>
            <div class="modal-body">
                <form action="/classes" method="post">
                    <input name="action" value="edit" hidden/>
                    <input name="id" hidden id="id"/>
                    <div class="form-group">
                        <label for="name">Tên lớp học</label>
                        <input type="text" name="classroom" class="form-control" id="name"
                               placeholder="Tên lớp học">
                    </div>
                    <div class="form-group">
                        <label for="teacher">Giáo viên</label>
                        <select id="teacher" name="teacher-id" class="form-control">
                            <c:forEach items="${teachers}" var="t">
                                <option value="${t.id}">${t.teacherName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="khoa">Khoa</label>
                        <select id="khoa" name="department-id" class="form-control">
                            <c:forEach items="${departments}" var="d">
                                <option value="${d.id}">${d.departmentName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="nienKhoa">Niên khóa</label>
                        <select id="nienKhoa" name="academic-year-id" class="form-control">
                            <c:forEach items="${academicYears}" var="a">
                                <option value="${a.id}">${a.academicYear}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Sửa</button>
                </form>
            </div>
        </div>

    </div>


    <div id="modalCreate" class="modal">

        <!-- Modal content -->
        <div class="modal-content">
            <h3 class="modal-title">Tạo mới thông tin lớp học</h3>
            <div class="close">&times;</div>
            <div class="modal-body">
                <form action="/classes" method="post">
                    <input name="action" value="add" hidden/>
                    <div class="form-group">
                        <label for="className">Tên lớp học</label>
                        <input type="text" name="classroom" class="form-control" id="className"
                               placeholder="Tên lớp học">
                    </div>
                    <div class="form-group">
                        <label for="gv">Giáo viên</label>
                        <select id="gv" name="teacher-id" class="form-control">
                            <c:forEach items="${teachers}" var="t">
                                <option value="${t.id}">${t.teacherName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="k">Khoa</label>
                        <select id="k" name="department-id" class="form-control">
                            <c:forEach items="${departments}" var="d">
                                <option value="${d.id}">${d.departmentName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="nk">Niên khóa</label>
                        <select id="nk" name="academic-year-id" class="form-control">
                            <c:forEach items="${academicYears}" var="a">
                                <option value="${a.id}">${a.academicYear}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Tạo mới</button>
                </form>
            </div>
        </div>

    </div>

    <div id="modalDelete" class="modal">

        <!-- Modal content -->
        <div class="modal-content">
            <h3 class="modal-title">Xóa thông tin lớp học</h3>
            <div class="close">&times;</div>
            <div class="modal-body">
                <form action="/classes" method="post">
                    <div class="input-field">
                        Bạn có chắc chắn muốn xóa lớp học <span id="tenM"></span> ?
                    </div>
                    <input id="class_id" name="id" type="hidden"/>
                    <input type="hidden" name="action" value="delete"/>
                    <button type="submit" class="btn btn-danger">Xóa</button>
                </form>
            </div>
        </div>

    </div>


    <!-- Main Footer -->
    <footer class="main-footer">
        <a href="/school-management/logout">Logout </a>
    </footer>
</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="/static/js/class.js"></script>
<!-- jQuery -->
<script src="/static/js/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="/static/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="/static/js/adminlte.min.js"></script>
<!-- DataTables  & Plugins -->
<script src="/static/js/jquery.dataTables.min.js"></script>
<script src="/static/js/dataTables.bootstrap4.min.js"></script>
<script src="/static/js/dataTables.responsive.min.js"></script>
<script src="/static/js/responsive.bootstrap4.min.js"></script>

<script>
    $("#products").DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "info": true,
        "autoWidth": true,
        "responsive": true,
    });
    $(document).ready(function () {
        var mess = document.getElementById("mess");
        if (mess.value !== "") {
            swal("Message!", mess.value, "success");
        }
    });
</script>
</body>

</html>