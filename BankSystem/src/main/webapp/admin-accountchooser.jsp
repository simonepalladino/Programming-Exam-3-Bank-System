<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" style="min-height: 100%;">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>BankSystem</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/Login-Form-Basic-icons.css">
    <link rel="stylesheet" href="assets/css/Pricing-Free-Paid-badges.css">
</head>

<body class="text-center" style="background: linear-gradient(0deg, var(--bs-red), white 23%);">
    <nav class="navbar navbar-light navbar-expand-md py-3">
        <div class="container"><a class="navbar-brand d-flex align-items-center"><span class="bs-icon-sm bs-icon-rounded bs-icon-primary d-flex justify-content-center align-items-center me-2 bs-icon"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-bank">
                        <path d="M8 .95 14.61 4h.89a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-.5.5H15v7a.5.5 0 0 1 .485.379l.5 2A.5.5 0 0 1 15.5 17H.5a.5.5 0 0 1-.485-.621l.5-2A.5.5 0 0 1 1 14V7H.5a.5.5 0 0 1-.5-.5v-2A.5.5 0 0 1 .5 4h.89L8 .95zM3.776 4h8.447L8 2.05 3.776 4zM2 7v7h1V7H2zm2 0v7h2.5V7H4zm3.5 0v7h1V7h-1zm2 0v7H12V7H9.5zM13 7v7h1V7h-1zm2-1V5H1v1h14zm-.39 9H1.39l-.25 1h13.72l-.25-1z"></path>
                    </svg></span><span>BankSystem</span></a><button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-2"><span class="visually-hidden">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navcol-1">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <form method="get" action="admin-dashboard.jsp">
                            <input type="submit" value="Home" style="background:none; border-width:0px;" class="nav-link"/></form></li>
                    <li class="nav-item">
                        <form method="get" action="admin-addaccount">
                            <input type="submit" value="Add account" style="background:none; border-width:0px;" class="nav-link"/></form></li>
                    <li class="nav-item">
                        <form method="get" action="admin-accountchooser">
                            <input type="submit" value="Modify account" style="background:none; border-width:0px;" class="nav-link active"/></form></li>
                    <li class="nav-item"></li>
                </ul><small class="fs-6" style="margin-right: 18px;"> <c:out value="${sessionScope.usertext}"/> </small>
                <a class="btn btn-danger" role="button" href="index.html">Logout</a>
            </div>
        </div>
    </nav>
    <div class="container py-4 py-xl-5">
        <div class="row mb-5">
            <div class="col-md-8 col-xl-6 text-center mx-auto">
                <h2>Edit an account</h2>
                <p class="w-lg-50">Want to add some cards or remove a person?<br></p>
            </div>
        </div>
        <form method="get" action="admin-accountchooser">
        <input class="border rounded" type="search" style="width: 574.333px;height: 40px;box-shadow: 0px 0px 20px 3px var(--bs-red);border-radius: 10px;text-align: center;padding-top: 0px;" placeholder="Search by account name or by holder's name" name="searchname">
        <button class="btn btn-primary text-center" type="submit" style="margin-left: 20px;background: var(--bs-btn-color);border-width: 0px;border-color: var(--bs-btn-disabled-color);border-left-width: 0px;border-left-color: var(--bs-border-color-translucent);box-shadow: 0px 0px 9px var(--bs-red);height: 45.1667px;"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-search" style="font-size: 27px;color: var(--bs-red);">
                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"></path>
            </svg></button>
        </form>
    </div>
    <form method="get" action="admin-modifyaccount">
        <c:forEach var="accounts" items="${accounts}">
            <div class="bg-light border rounded border-3 shadow d-flex align-items-lg-center" style="margin-left: 159px;margin-right: 159px;margin-top: 10px;margin-bottom: 10px;"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-person-badge text-primary" style="font-size: 46px;margin-left: 8px;margin-right: 8px;">
                <path d="M6.5 2a.5.5 0 0 0 0 1h3a.5.5 0 0 0 0-1h-3zM11 8a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"></path>
                <path d="M4.5 0A2.5 2.5 0 0 0 2 2.5V14a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V2.5A2.5 2.5 0 0 0 11.5 0h-7zM3 2.5A1.5 1.5 0 0 1 4.5 1h7A1.5 1.5 0 0 1 13 2.5v10.795a4.2 4.2 0 0 0-.776-.492C11.392 12.387 10.063 12 8 12s-3.392.387-4.224.803a4.2 4.2 0 0 0-.776.492V2.5z"></path>
            </svg>
                <div></div>
                <div class="col">
                    <p class="fs-5 fw-bold text-primary mb-0"><c:out value="${accounts.firstname}"/> <c:out value="${accounts.lastname}"/></p>
                    <p class="fs-6 fw-bold text-primary mb-0"><c:out value="${accounts.username}"/></p>
                </div>
                <div class="d-md-flex d-lg-flex d-xl-flex flex-fill justify-content-md-end justify-content-lg-end justify-content-xl-end">
                    <button class="btn btn-primary text-end d-xl-flex flex-row justify-content-xl-center align-items-xl-center" type="submit" name="selectedAccount" value='<c:out value="${accounts.cf}"/>' style="padding-left: 105px;padding-right: 105px;background: var(--bs-red);border-color: var(--bs-red);margin-right: 8px;">Edit</button></div>
                <div></div>
            </div>
        </c:forEach>
    </form>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/bs-init.js"></script>
</body>

</html>