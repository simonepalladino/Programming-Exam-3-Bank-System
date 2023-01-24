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
    <div class="container"><a class="navbar-brand d-flex align-items-center" href="#"><span class="bs-icon-sm bs-icon-rounded bs-icon-primary d-flex justify-content-center align-items-center me-2 bs-icon"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-bank">
                        <path d="M8 .95 14.61 4h.89a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-.5.5H15v7a.5.5 0 0 1 .485.379l.5 2A.5.5 0 0 1 15.5 17H.5a.5.5 0 0 1-.485-.621l.5-2A.5.5 0 0 1 1 14V7H.5a.5.5 0 0 1-.5-.5v-2A.5.5 0 0 1 .5 4h.89L8 .95zM3.776 4h8.447L8 2.05 3.776 4zM2 7v7h1V7H2zm2 0v7h2.5V7H4zm3.5 0v7h1V7h-1zm2 0v7H12V7H9.5zM13 7v7h1V7h-1zm2-1V5H1v1h14zm-.39 9H1.39l-.25 1h13.72l-.25-1z"></path>
                    </svg></span><span>BankSystem</span></a><button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-2"><span class="visually-hidden">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navcol-1">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <form method="get" action="admin-dashboard.jsp">
                        <input type="submit" value="Home" style="background:none; border-width:0px;" class="nav-link"/></form></li>
                <li class="nav-item">
                    <form method="get" action="admin-addaccount">
                        <input type="submit" value="Add account" style="background:none; border-width:0px;" class="nav-link active"/></form></li>
                <li class="nav-item">
                    <form method="get" action="admin-accountchooser">
                        <input type="submit" value="Modify account" style="background:none; border-width:0px;" class="nav-link"/></form></li>
                <li class="nav-item"></li>
            </ul><small class="fs-6" style="margin-right: 18px;"> <c:out value="${sessionScope.usertext}"/> </small>
            <a class="btn btn-danger" role="button" href="index.html">Logout</a>
        </div>
    </div>
</nav>
<section class="position-relative py-4 py-xl-5"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-person-circle text-center d-flex d-sm-flex d-md-flex d-lg-flex justify-content-center mx-auto justify-content-sm-center justify-content-md-center justify-content-lg-center align-items-lg-center" style="font-size: 209px;padding-top: 0px;padding-bottom: 0px;margin-bottom: 13px;margin-top: 8px;color: var(--bs-red);">
    <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"></path>
    <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"></path>
</svg>
    <div class="container position-relative">
        <div class="row d-flex justify-content-center align-items-lg-center" style="margin-bottom: 88px;margin-top: 19px;">
            <div class="col-md-8 col-lg-6 col-xl-5 col-xxl-4">
                <div class="card mb-5">
                    <div class="card-body p-sm-5" style="margin-bottom: -17px;padding-top: 91px;padding-bottom: 91px;margin-top: 3px;">
                        <h2 class="text-center mb-4" style="margin-top: -29px;">Register</h2>
                        <form method="post" action="admin-addaccount">
                            <div class="mb-3"><input class="form-control" type="text" id="name-1" name="username" placeholder="Insert username"></div>
                            <div class="mb-3"><input class="form-control" type="text" id="name-2" name="firstname" placeholder="Insert firstname"></div>
                            <div class="mb-3"><input class="form-control" type="text" id="email-2" name="lastname" placeholder="Insert lastname"></div>
                            <div class="mb-3"><input class="form-control" type="text" id="cf" name="cf" placeholder="Insert fiscal code"></div>
                            <div class="mb-3"><input class="form-control" type="text" id="address" name="address" placeholder="Insert address"></div>
                            <div class="mb-3"><select class="form-select" style="margin-top: 12px;" name="accounttype">
                                <optgroup label="Account type">
                                    <option value="Basic"  name="Basic" selected="">Basic</option>
                                    <option value="Premium" name="Premium">Premium</option>
                                    <option value="Enterprise" name="Enterprise">Enterprise</option>
                                </optgroup>
                            </select></div>
                            <div class="mb-3"><select class="form-select" style="margin-top: 12px;" name="cardtype">
                                <optgroup label="Card Type">
                                    <option value="Bancomat"  name ="Bancomat" selected="">Bancomat</option>
                                    <option value="Credit Card" name="Credit Card">Credit Card</option>
                                </optgroup>
                            </select></div>
                            <div class="mb-3"></div>
                            <div><button class="btn btn-primary d-block w-100" type="submit" style="background: var(--bs-red);border-color: var(--bs-red);">Create</button></div>
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