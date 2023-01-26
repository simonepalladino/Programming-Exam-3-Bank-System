<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<body style="background: linear-gradient(0deg, var(--bs-blue), white 23%);height: 100%;">
    <nav class="navbar navbar-light navbar-expand-md py-3">
        <div class="container"><a class="navbar-brand d-flex align-items-center"><span class="bs-icon-sm bs-icon-rounded bs-icon-primary d-flex justify-content-center align-items-center me-2 bs-icon"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-bank">
                        <path d="M8 .95 14.61 4h.89a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-.5.5H15v7a.5.5 0 0 1 .485.379l.5 2A.5.5 0 0 1 15.5 17H.5a.5.5 0 0 1-.485-.621l.5-2A.5.5 0 0 1 1 14V7H.5a.5.5 0 0 1-.5-.5v-2A.5.5 0 0 1 .5 4h.89L8 .95zM3.776 4h8.447L8 2.05 3.776 4zM2 7v7h1V7H2zm2 0v7h2.5V7H4zm3.5 0v7h1V7h-1zm2 0v7H12V7H9.5zM13 7v7h1V7h-1zm2-1V5H1v1h14zm-.39 9H1.39l-.25 1h13.72l-.25-1z"></path>
                    </svg></span><span>BankSystem</span></a><button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-1"><span class="visually-hidden">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navcol-1">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <form method="get" action="dashboard">
                            <input type="submit" value="Home" style="background:none; border-width:0px;" class="nav-link" name="logintype" value="user"/></form></li>
                    <li class="nav-item">
                        <form method="get" action="holder-deposit">
                            <input type="submit" value="Deposit" style="background:none; border-width:0px;" class="nav-link"/></form></li>
                    <li class="nav-item">
                        <form method="get" action="holder-buy">
                            <input type="submit" value="Withdraw or Buy" style="background:none; border-width:0px;" class="nav-link active"/></form></li>
                    <li class="nav-item"></li>
                </ul><small class="fs-6" style="margin-right: 18px;"><c:out value="${sessionScope.usertext}"/></small>
                <form method="get" action="holder-plan">
                    <button type="submit" class="btn btn-primary" role="button" href="holder-plan.jsp" style="margin-right: 10px;background: rgb(253,186,13);border-style: none;box-shadow: 0px 0px 5px 2px var(--bs-orange);">Change plan</button>
                </form>
                <a class="btn btn-primary" role="button" href="index.html">Logout</a>
            </div>
        </div>
    </nav>
<form method="post">
    <div class="bg-light border rounded border-3 shadow d-flex align-items-lg-center" style="margin-left: 20px;margin-right: 20px;margin-bottom: -11px;margin-top: -4px;">
        <div style="margin-left: 5px;">
            <p class="fw-bold text-center text-primary mb-0" style="margin-left: 10px;">Choose your card</p>
        </div>
        <div></div><small class="fs-5 fw-semibold text-end d-lg-flex flex-fill justify-content-lg-end align-items-lg-center">

        <select class="form-select-sm" name="selectedcard" style="border-style: solid;border-color: var(--bs-blue);border-radius: 6px;width: 80vh;">
            <c:forEach var="cards" items="${cards}" varStatus="status">
                <c:choose>
                    <c:when test="${cards.card_type == 'Bancomat'}">
                        <option value='<c:out value="${cards.card_number}"/>' <c:if test="${status.first}">selected</c:if>>💳 <c:out value="${cards.card_name}"/> - <c:out value="${cards.card_number}"/></option>
                    </c:when>
                    <c:otherwise>
                        <option value='<c:out value="${cards.card_number}"/>' <c:if test="${status.first}">selected</c:if>>🏦 <c:out value="${cards.card_name}"/> - <c:out value="${cards.card_number}"/></option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select></small>
    </div>
    <div class="bg-light border rounded border-3 shadow d-flex align-items-lg-center" style="margin-top: 10px;margin-bottom: 5px;margin-left: 20px;margin-right: 20px;"><img width="100" height="80" src="assets/img/withdraw.png">
        <div style="margin-left: 5px;">
            <p class="fw-bold text-center text-primary mb-0" style="margin-left: 10px;">Withdraw money</p>
            <p class="fw-bold text-center text-primary mb-0" style="margin-left: 10px;margin-top: -5px;"><small class="fw-normal">from bank</small></p>
        </div>
        <div></div><small class="fs-5 fw-semibold text-end d-lg-flex flex-fill justify-content-lg-end align-items-lg-center">
            <input class="form-control-sm" type="text" style="margin-right: 5px;border-style: outset;border-color: var(--bs-blue);border-radius: 5px;" placeholder="Insert amount" name="withdraw">€&nbsp; &nbsp;&nbsp;
            <button class="btn btn-primary" type="submit" style="margin-right: 12px;" name="buy" value="withdraw">Withdraw now</button>
        </small>
    </div>
    <div class="table-responsive d-flex" style="max-height: 70vh;border-style: none;">
        <table class="table">
            <tbody>
                <tr></tr>
                <tr>
                    <td style="border-style: none;">

                        <c:forEach var="products" items="${products}">
                                <div class="bg-light border rounded border-3 shadow d-flex align-items-lg-center" style="margin-top: 10px;margin-bottom: 10px;margin-left: 35px;margin-right: 35px;">
                                    <img width="100" height="80" src='<c:out value="${products.image}"/>'>
                                    <div class="text-center" style="margin-left: 5px;">
                                        <p class="fw-bold text-center text-primary mb-0" style="margin-left: 10px;"><c:out value="${products.product_name}"/></p>
                                        <p class="fw-bold text-center text-primary mb-0" style="margin-left: 10px;margin-top: -5px;"><small class="fw-normal text-center"><c:out value="${products.quote}"/></small></p>
                                    </div>
                                    <div></div>
                                    <small class="fs-5 fw-semibold text-end d-lg-flex flex-fill justify-content-lg-end align-items-lg-center">
                                        <c:choose>
                                            <c:when test="${products.discountPrice != 0}">
                                                <span style="text-decoration: line-through;"><fmt:formatNumber type = "number" maxFractionDigits = "2" minFractionDigits="2" value = "${products.price}"/>€</span>&nbsp;<fmt:formatNumber type = "number" maxFractionDigits = "2" minFractionDigits="2" value = "${products.discountPrice}"/>€&nbsp;&nbsp;
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value="${products.price}"/>€&nbsp;&nbsp;
                                            </c:otherwise>
                                        </c:choose>
                                        <button class="btn btn-primary" type="submit" style="margin-right: 12px;" name="buy" value='<c:out value="${products.product_id}"/>'>Buy now</button>
                                    </small>
                                </div>
                        </c:forEach>

                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</form>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/bs-init.js"></script>
</body>

</html>