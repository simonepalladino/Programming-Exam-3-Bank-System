<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" style="height: 100%;">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>BankSystem</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/Login-Form-Basic-icons.css">
</head>

<c:url value="login.jsp" var="displayURL">
    <c:param name="logintype"   value="${param.logintype}" />
    <c:param name="error"   value="${param.error}" />
</c:url>

<c:choose>
    <c:when test="${param.logintype == 'holder'}">
        <c:set var = "grandtext" scope = "session" value = "Holder Log in"/>
        <c:set var = "logincolor" scope = "session" value = "--bs-blue"/>
    </c:when>
    <c:otherwise>
        <c:set var = "grandtext" scope = "session" value = "Admin Log in"/>
        <c:set var = "logincolor" scope = "session" value = "--bs-red"/>
    </c:otherwise>
</c:choose>

<body style="background: linear-gradient(-129deg, var(<c:out value = "${logincolor}"/>), white), var(--bs-blue);">
    <section class="position-relative py-4 py-xl-5">
        <div class="container">
            <div class="row mb-5">
                <div class="col-md-8 col-xl-6 text-center mx-auto">
                    <h2 class="text-white">
                        <c:out value = "${grandtext}"/>
                        </h2>
                    <p class="text-white w-lg-50">What do you want to do?</p>
                </div>
            </div>
            <div class="row d-flex justify-content-center">
                <div class="col-md-6 col-xl-4">
                    <div class="card mb-5">
                        <div class="card-body d-flex flex-column align-items-center">
                            <div class="bs-icon-xl bs-icon-circle bs-icon-primary bs-icon my-4" style="background: var(<c:out value = "${logincolor}"/>);">
                                <svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-person">
                                    <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"></path>
                                </svg></div>
                            <form class="text-center" method="post">
                                <div class="mb-3"><input class="form-control" type="text" name="username" placeholder="Username"></div>
                                <div class="mb-3"><input class="form-control" type="password" name="password" placeholder="Password"></div>
                                <c:choose>
                                    <c:when test="${param.error == 'errore'}">
                                        <small class="text-danger">Invalid credentials!</small>
                                    </c:when>
                                </c:choose>
                                <div class="mb-3"><button class="btn btn-primary d-block w-100" type="submit" style="background: var(<c:out value = "${logincolor}"/>); border-style: none;">Login</button></div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/bs-init.js"></script>
</body>

</html>