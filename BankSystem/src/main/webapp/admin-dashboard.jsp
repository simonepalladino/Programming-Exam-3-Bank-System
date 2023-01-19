<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" style="min-height: 100%;">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>BankSystem</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/animate.min.css">
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
                            <input type="submit" value="Home" style="background:none; border-width:0px;" class="nav-link active"/></form></li>
                    <li class="nav-item">
                        <form method="get" action="admin-addaccount">
                            <input type="submit" value="Add account" style="background:none; border-width:0px;" class="nav-link"/></form></li>
                    <li class="nav-item">
                        <form method="get" action="admin-accountchooser">
                            <input type="submit" value="Modify account" style="background:none; border-width:0px;" class="nav-link"/></form></li>
                    <li class="nav-item"></li>
                </ul><small class="fs-6" style="margin-right: 18px;"> <c:out value="${sessionScope.usertext}"/> </small>
                <a class="btn btn-danger" role="button" href="index.html">Logout</a>
            </div>
        </div>
    </nav>
    <div class="container py-4 py-xl-5" style="padding-bottom: 0px;padding-top: 6px;margin-bottom: -45px;">
        <div class="row mb-5">
            <div class="col-md-8 col-xl-6 text-center mx-auto">
                <h2>What would you like to do?</h2>
                <p class="w-lg-50">An admin can either insert a new account holder or delete an existing account.<br></p>
            </div>
        </div>
    </div>
    <div class="table-responsive" style="border-style: none;">
        <table class="table">
            <tbody style="border-style: none;">
                <tr style="border-style: none;">
                    <td style="border-style: none;">
                        <form method="get" action="admin-addaccount">
                        <button type="submit" class="btn btn-primary text-center m-auto" role="button" data-bss-hover-animate="pulse" style="background: var(--bs-btn-color);border-width: 0px;border-color: var(--bs-btn-disabled-color);border-left-width: 0px;border-left-color: var(--bs-border-color-translucent);box-shadow: 0px 0px 9px var(--bs-red);height: 200px;width: 200px;padding: 34px 12px;border-radius: 50%;"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-plus-lg text-center" style="font-size: 127px;color: var(--bs-red);padding-top: 0px;padding-bottom: 0px;">
                                <path fill-rule="evenodd" d="M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2Z"></path>
                            </svg></button></form></td>
                    <td style="border-style: none;">
                        <form method="get" action="admin-accountchooser">
                        <button type="submit" class="btn btn-primary text-center m-auto" role="button" data-bss-hover-animate="pulse" style="background: var(--bs-btn-color);border-width: 0px;border-color: var(--bs-btn-disabled-color);border-left-width: 0px;border-left-color: var(--bs-border-color-translucent);box-shadow: 0px 0px 9px var(--bs-red);height: 200px;width: 200px;padding: 34px 12px;border-radius: 50%;"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-pencil-fill" style="font-size: 127px;color: var(--bs-red);">
                                <path d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"></path>
                            </svg></button></form></td>
                </tr>
                <tr style="border-style: none;">
                    <td class="fs-4 fw-semibold text-center" style="border-style: none;">Add account</td>
                    <td class="fs-4 fw-semibold text-center" style="border-style: none;">Modify account</td>
                </tr>
            </tbody>
        </table>
    </div>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/bs-init.js"></script>
</body>

</html>