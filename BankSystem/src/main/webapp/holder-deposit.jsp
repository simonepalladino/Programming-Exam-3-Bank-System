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
    <link rel="stylesheet" href="assets/css/animate.min.css">
    <link rel="stylesheet" href="assets/css/Login-Form-Basic-icons.css">
    <link rel="stylesheet" href="assets/css/Pricing-Free-Paid-badges.css">
</head>


<body style="background: linear-gradient(0deg, var(--bs-blue), white 23%);">
    <nav class="navbar navbar-light navbar-expand-md py-3">
        <div class="container"><a class="navbar-brand d-flex align-items-center"><span class="bs-icon-sm bs-icon-rounded bs-icon-primary d-flex justify-content-center align-items-center me-2 bs-icon"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-bank">
                        <path d="M8 .95 14.61 4h.89a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-.5.5H15v7a.5.5 0 0 1 .485.379l.5 2A.5.5 0 0 1 15.5 17H.5a.5.5 0 0 1-.485-.621l.5-2A.5.5 0 0 1 1 14V7H.5a.5.5 0 0 1-.5-.5v-2A.5.5 0 0 1 .5 4h.89L8 .95zM3.776 4h8.447L8 2.05 3.776 4zM2 7v7h1V7H2zm2 0v7h2.5V7H4zm3.5 0v7h1V7h-1zm2 0v7H12V7H9.5zM13 7v7h1V7h-1zm2-1V5H1v1h14zm-.39 9H1.39l-.25 1h13.72l-.25-1z"></path>
                    </svg></span><span>BankSystem</span></a><button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-1"><span class="visually-hidden">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navcol-1">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <form method="get" action="dashboard">
                            <input type="submit" value="Home" style="background:none; border-width:0px;" class="nav-link" name="logintype"/></form></li>
                    <li class="nav-item">
                        <form method="get" action="holder-deposit">
                            <input type="submit" value="Deposit" style="background:none; border-width:0px;" class="nav-link active"/></form></li>
                    <li class="nav-item">
                        <form method="get" action="holder-buy">
                            <input type="submit" value="Withdraw or Buy" style="background:none; border-width:0px;" class="nav-link"/></form></li>
                    <li class="nav-item"></li>
                </ul><small class="fs-6" style="margin-right: 18px;"><c:out value="${sessionScope.usertext}"/></small>
                <form method="get" action="holder-plan">
                    <button type="submit" class="btn btn-primary" role="button" href="holder-plan.jsp" style="margin-right: 10px;background: rgb(253,186,13);border-style: none;box-shadow: 0px 0px 5px 2px var(--bs-orange);">Change plan</button>
                </form>
                <a class="btn btn-primary" role="button" href="index.html">Logout</a>
            </div>
        </div>
    </nav>
    <div style="border-style: none;margin-top: 10px;">
        <table class="table table-borderless">
            <tbody>
                <tr>
                    <td style="border-radius: 20px;background: linear-gradient(rgba(25,110,247,0.61), white 76%);width: 45%;">
                        <div class="row mb-5" style="margin-bottom: 38px;margin-top: 0px;">
                            <div class="col-md-8 col-xl-6 text-center mx-auto">
                                <h2 class="text-black-50" style="font-weight: bold;color: var(--bs-body-bg);">Account info</h2>
                            </div>
                        </div>
                        <div class="row mb-5" style="margin-bottom: 0px;padding-bottom: 0px;margin-top: -42px;">
                            <div class="col-md-8 col-xl-6 text-center mx-auto">
                                <h2 style="font-weight: bold;"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-person-badge text-black-50" style="font-size: 130px;">
                                        <path d="M6.5 2a.5.5 0 0 0 0 1h3a.5.5 0 0 0 0-1h-3zM11 8a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"></path>
                                        <path d="M4.5 0A2.5 2.5 0 0 0 2 2.5V14a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V2.5A2.5 2.5 0 0 0 11.5 0h-7zM3 2.5A1.5 1.5 0 0 1 4.5 1h7A1.5 1.5 0 0 1 13 2.5v10.795a4.2 4.2 0 0 0-.776-.492C11.392 12.387 10.063 12 8 12s-3.392.387-4.224.803a4.2 4.2 0 0 0-.776.492V2.5z"></path>
                                    </svg></h2>
                                <p class="fs-5 fw-semibold text-black-50 w-lg-50" style="color: var(--bs-body-bg);"><c:out value="${sessionScope.selectedHolder.firstname}"/> <c:out value="${sessionScope.selectedHolder.lastname}"/><br></p>
                            </div>
                        </div>
                        <div class="row mb-5">
                            <div class="col">
                                <div class="container py-4 py-xl-5" style="margin-bottom: -37px;padding-top: 24px;margin-top: -95px;">
                                    <div class="text-center text-white-50 border rounded border-0 p-3" style="background: var(--bs-blue);">
                                        <div class="row row-cols-2 row-cols-md-4 d-lg-flex">
                                            <div class="col">
                                                <div class="p-3">
                                                    <h4 class="display-5 fw-bold text-white mb-0"><c:out value="${requestScope.withdrawals}"/></h4>
                                                    <p class="mb-0">Total withdrawals</p>
                                                </div>
                                            </div>
                                            <div class="col">
                                                <div class="p-3">
                                                    <h4 class="display-5 fw-bold text-white mb-0"><c:out value="${requestScope.deposits}"/></h4>
                                                    <p class="mb-0">Total deposits</p>
                                                </div>
                                            </div>
                                            <div class="col">
                                                <div class="p-3">
                                                    <h4 class="display-5 fw-bold text-white mb-0" style="padding-left: 0px;padding-right: 0px;margin-left: -10px;margin-right: -10px;"><fmt:formatNumber type = "number" maxFractionDigits = "2" minFractionDigits="2" value = "${requestScope.balance}"/>€</h4>
                                                    <p class="mb-0">Total Balance</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row mb-5" style="margin-bottom: 10px;">
                            <div class="col-md-8 col-xl-6 text-center mx-auto">
                                <h2 class="text-black-50" style="font-weight: bold;color: var(--bs-body-bg);">Deposit money</h2>
                                <p class="text-black-50 w-lg-50" style="color: var(--bs-body-bg);">Select one of these</p>
                            </div>
                        </div>
                        <div class="table-responsive d-flex float-start" style="overflow: auto;max-width: 620px;margin-top: -52px;">
                            <table class="table">
                                <tbody>
                                    <tr>
                                        <!-- Lista delle carte relative all'utenza -->
                                        <c:forEach var="cardList" items="${cardList}">
                                            <fmt:parseDate  value="${cardList.getDate()}"  type="date" pattern="yyyy-MM-dd" var="parsedDate" />
                                            <fmt:formatDate value="${parsedDate}" type="date" pattern="MM/yy" var="datee" />

                                            <td>
                                                <div class="text-center d-flex flex-column flex-wrap justify-content-lg-start align-items-lg-center align-items-xl-center" style="background: linear-gradient(39deg, var(--bs-gray-400), white), var(--bs-table-border-color);border-radius: 12px;width: 258.469px;">
                                                    <div class="bs-icon-lg bs-icon-rounded bs-icon-primary d-flex flex-shrink-0 justify-content-center align-items-center d-inline-block mb-3 bs-icon lg" style="background: linear-gradient(-5deg, var(--bs-gray-dark), var(--bs-gray) 77%), var(--bs-gray);margin-left: 5px;margin-top: 3px;"><svg class="bi bi-credit-card" xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" style="font-size: 41px;">
                                                        <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4zm2-1a1 1 0 0 0-1 1v1h14V4a1 1 0 0 0-1-1H2zm13 4H1v5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V7z"></path>
                                                        <path d="M2 10a1 1 0 0 1 1-1h1a1 1 0 0 1 1 1v1a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1v-1z"></path>
                                                    </svg></div>
                                                    <div class="px-3">
                                                        <h5 class="text-center" style="margin-top: 6px;"><c:out value="${cardList.card_name}"/></h5>
                                                        <h5 class="fs-6 fw-normal text-center" style="margin-top: -5px;"><c:out value="${cardList.card_type}"/></h5>
                                                        <p class="fs-6" style="margin-bottom: 2px;"><c:out value="${cardList.card_number}"/></p>
                                                        <p style="margin-top: 5px;margin-bottom: 5px;"><strong>CVV</strong>: <c:out value="${cardList.CVV}"/></p>
                                                        <p style="margin-bottom: 5px;margin-top: 0px;"><strong>Expiration</strong>: <c:out value="${datee}"/></p>
                                                        <p class="fs-6 fw-semibold" style="margin-bottom: 0px;">Balance</p>
                                                        <p class="fs-6" style="margin-bottom: 15px;margin-top: -1px;"><fmt:formatNumber type = "number" maxFractionDigits = "2" minFractionDigits="2" value = "${cardList.balance}"/>€</p>
                                                        <form method="get" action="holder-depositoncard">
                                                            <button class="btn btn-primary" type="submit" style="margin-bottom: 5px;margin-top: -9px;" name="card" value='<c:out value="${cardList.card_number}"/>'>Deposit</button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </td>
                                        </c:forEach>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </td>
                    <td></td>
                    <c:choose>
                        <c:when test="${requestScope.view == 'all'}">
                            <td style="background: linear-gradient(180deg, rgba(25,110,247,0.61), white 79%);border-radius: 20px;">
                                <div class="row mb-5" style="margin-bottom: 38px;margin-top: 0px;">
                                    <div class="col-md-8 col-xl-8 text-center mx-auto" style="margin-bottom: -48px;">
                                        <h2 class="text-black-50" style="font-weight: bold;color: var(--bs-body-bg);">Operations</h2>
                                        <p class="text-black-50 w-lg-50" style="color: var(--bs-body-bg);">Here is quick view of your incomings and outcomings.</p>
                                        <div class="table-responsive" style="padding-bottom: 0px;margin-bottom: -17px;margin-left: -40px;margin-right: -40px;margin-top: -8px;">
                                            <table class="table">
                                                <tbody>
                                                <tr>
                                                    <td style="border-style: none;padding-top: 0px;padding-bottom: 0px;padding-right: 0px;padding-left: 0px;">
                                                        <div class="btn-group" role="group">
                                                            <form method="get" action="holder-deposit"><button class="btn btn-primary" type="submit" name="view" value="cards">View by cards</button></form>
                                                            <form method="get" action="holder-deposit"><button class="btn btn-light" type="submit" name="view" value="all">View all</button></form></div>
                                                    </td>
                                                    <td style="border-style: none;"><form method="get" action="holder-canceloperation">
                                                        <button class="btn btn-primary text-center" type="submit" style="background: var(--bs-danger);border-color: var(--bs-danger);margin-bottom: 0px;margin-top: -8px;" name="backurl" value="holder-deposit?view=all">Cancel latest operation</button></form></td>
                                                </tr>
                                                <tr></tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <div class="table-responsive d-flex" style="max-height: 118vh;border-style: none;">
                                    <table class="table">
                                        <tbody>
                                        <tr></tr>
                                        <tr>
                                            <td style="border-style: none;"><strong class="text-start text-black-50" style="margin-left: 10px;">All operations</strong>
                                                <c:forEach var="recentMovements" items="${recentMovements}">
                                                    <div class="bg-light border rounded border-3 shadow d-flex align-items-lg-center" style="margin-left: 0px;margin-right: 0px;margin-top: 10px;margin-bottom: 10px;">
                                                        <c:choose>
                                                            <c:when test="${recentMovements.type == 'deposit'}">
                                                                <svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-cash text-primary" style="font-size: 46px;margin-left: 8px;margin-right: 8px;">
                                                                    <path d="M8 10a2 2 0 1 0 0-4 2 2 0 0 0 0 4z"></path>
                                                                    <path d="M0 4a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V4zm3 0a2 2 0 0 1-2 2v4a2 2 0 0 1 2 2h10a2 2 0 0 1 2-2V6a2 2 0 0 1-2-2H3z"></path>
                                                                </svg>
                                                            </c:when>
                                                            <c:when test="${recentMovements.type == 'withdraw'}">
                                                                <svg class="bi bi-cash-stack text-primary" xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" style="font-size: 46px;margin-left: 8px;margin-right: 8px;">
                                                                    <path d="M1 3a1 1 0 0 1 1-1h12a1 1 0 0 1 1 1H1zm7 8a2 2 0 1 0 0-4 2 2 0 0 0 0 4z"></path>
                                                                    <path d="M0 5a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V5zm3 0a2 2 0 0 1-2 2v4a2 2 0 0 1 2 2h10a2 2 0 0 1 2-2V7a2 2 0 0 1-2-2H3z"></path>
                                                                </svg>
                                                            </c:when>
                                                            <c:when test="${recentMovements.type == 'upgrade'}">
                                                                <svg class="bi bi-arrow-up-right-circle-fill text-primary" xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" style="font-size: 46px;margin-left: 8px;margin-right: 8px;">
                                                                    <path d="M0 8a8 8 0 1 0 16 0A8 8 0 0 0 0 8zm5.904 2.803a.5.5 0 1 1-.707-.707L9.293 6H6.525a.5.5 0 1 1 0-1H10.5a.5.5 0 0 1 .5.5v3.975a.5.5 0 0 1-1 0V6.707l-4.096 4.096z"></path>
                                                                </svg>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-bag-check text-primary" style="font-size: 46px;margin-left: 8px;margin-right: 8px;">
                                                                    <path fill-rule="evenodd" d="M10.854 8.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 0 1 .708-.708L7.5 10.793l2.646-2.647a.5.5 0 0 1 .708 0z"></path>
                                                                    <path d="M8 1a2.5 2.5 0 0 1 2.5 2.5V4h-5v-.5A2.5 2.5 0 0 1 8 1zm3.5 3v-.5a3.5 3.5 0 1 0-7 0V4H1v10a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V4h-3.5zM2 5h12v9a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V5z"></path>
                                                                </svg>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <div>
                                                            <p class="fw-bold text-primary mb-0"><c:out value="${recentMovements.product_name}"/></p>
                                                            <p class="text-muted mb-0"><c:out value="${recentMovements.card_number}"/></p>
                                                        </div>
                                                        <div></div><small class="fs-5 fw-semibold text-end d-lg-flex flex-fill justify-content-lg-end align-items-lg-center" style="margin-right: 8px;"><span class="fs-6 text-black-50" style="margin-right: 10px;"><c:out value="${recentMovements.mov_date_string}"/></span>
                                                        <c:choose>
                                                            <c:when test="${recentMovements.price >= 0}">
                                                                +<fmt:formatNumber type = "number" maxFractionDigits = "2" minFractionDigits="2" value = "${recentMovements.price}"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <fmt:formatNumber type = "number" maxFractionDigits = "2" minFractionDigits="2" value = "${recentMovements.price}"/>
                                                            </c:otherwise>
                                                        </c:choose>€</small>
                                                    </div>
                                                </c:forEach>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td style="background: linear-gradient(180deg, rgba(25,110,247,0.61), white 79%);border-radius: 20px;">
                                <div class="row mb-5" style="margin-bottom: 38px;margin-top: 0px;">
                                    <div class="col-md-8 col-xl-8 text-center mx-auto" style="margin-bottom: -48px;">
                                        <h2 class="text-black-50" style="font-weight: bold;color: var(--bs-body-bg);">Operations</h2>
                                        <p class="text-black-50 w-lg-50" style="color: var(--bs-body-bg);">Here is quick view of your incomings and outcomings.</p>
                                        <div class="table-responsive" style="padding-bottom: 0px;margin-bottom: -17px;margin-left: -40px;margin-right: -40px;margin-top: -8px;">
                                            <table class="table">
                                                <tbody>
                                                <tr>
                                                    <td style="border-style: none;padding-top: 0px;padding-bottom: 0px;padding-right: 0px;padding-left: 0px;">
                                                        <div class="btn-group" role="group">
                                                            <form method="get" action="holder-deposit"><button class="btn btn-light" type="submit" name="view" value="cards">View by cards</button></form>
                                                            <form method="get" action="holder-deposit"><button class="btn btn-primary" type="submit" name="view" value="all">View all</button></form></div>
                                                    </td>
                                                    <td style="border-style: none;"><form method="get" action="holder-canceloperation">
                                                        <button class="btn btn-primary text-center" type="submit" style="background: var(--bs-danger);border-color: var(--bs-danger);margin-bottom: 0px;margin-top: -8px;" name="backurl" value="holder-deposit">Cancel latest operation</button></form></td>
                                                </tr>
                                                <tr></tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>

                                <!-- Itera per ciascuna carta -->
                                <c:forEach var="cardRecentMovements" items="${cardRecentMovements}">
                                    <c:if test="${cardRecentMovements.getMovementsInfo().size() > 0}">
                                        <div class="table-responsive d-flex" style="max-height: 115vh;border-style: none;">
                                            <table class="table">
                                                <tbody>
                                                <tr></tr>
                                                <tr>
                                                    <td style="border-style: none;"><strong class="text-start text-black-50" style="margin-left: 10px;"><c:out value="${cardRecentMovements.getCard_name()}"/></strong>
                                                        <!-- Itera per ciascun movimento -->

                                                        <c:forEach var="recentMovements" items="${cardRecentMovements.getMovementsInfo()}">
                                                            <div class="bg-light border rounded border-3 shadow d-flex align-items-lg-center" style="margin-left: 0px;margin-right: 0px;margin-top: 10px;margin-bottom: 10px;">
                                                                <c:choose>
                                                                    <c:when test="${recentMovements.type == 'deposit'}">
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-cash text-primary" style="font-size: 46px;margin-left: 8px;margin-right: 8px;">
                                                                            <path d="M8 10a2 2 0 1 0 0-4 2 2 0 0 0 0 4z"></path>
                                                                            <path d="M0 4a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V4zm3 0a2 2 0 0 1-2 2v4a2 2 0 0 1 2 2h10a2 2 0 0 1 2-2V6a2 2 0 0 1-2-2H3z"></path>
                                                                        </svg>
                                                                    </c:when>
                                                                    <c:when test="${recentMovements.type == 'withdraw'}">
                                                                        <svg class="bi bi-cash-stack text-primary" xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" style="font-size: 46px;margin-left: 8px;margin-right: 8px;">
                                                                            <path d="M1 3a1 1 0 0 1 1-1h12a1 1 0 0 1 1 1H1zm7 8a2 2 0 1 0 0-4 2 2 0 0 0 0 4z"></path>
                                                                            <path d="M0 5a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V5zm3 0a2 2 0 0 1-2 2v4a2 2 0 0 1 2 2h10a2 2 0 0 1 2-2V7a2 2 0 0 1-2-2H3z"></path>
                                                                        </svg>
                                                                    </c:when>
                                                                    <c:when test="${recentMovements.type == 'upgrade'}">
                                                                        <svg class="bi bi-arrow-up-right-circle-fill text-primary" xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" style="font-size: 46px;margin-left: 8px;margin-right: 8px;">
                                                                            <path d="M0 8a8 8 0 1 0 16 0A8 8 0 0 0 0 8zm5.904 2.803a.5.5 0 1 1-.707-.707L9.293 6H6.525a.5.5 0 1 1 0-1H10.5a.5.5 0 0 1 .5.5v3.975a.5.5 0 0 1-1 0V6.707l-4.096 4.096z"></path>
                                                                        </svg>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-bag-check text-primary" style="font-size: 46px;margin-left: 8px;margin-right: 8px;">
                                                                            <path fill-rule="evenodd" d="M10.854 8.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 0 1 .708-.708L7.5 10.793l2.646-2.647a.5.5 0 0 1 .708 0z"></path>
                                                                            <path d="M8 1a2.5 2.5 0 0 1 2.5 2.5V4h-5v-.5A2.5 2.5 0 0 1 8 1zm3.5 3v-.5a3.5 3.5 0 1 0-7 0V4H1v10a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V4h-3.5zM2 5h12v9a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V5z"></path>
                                                                        </svg>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                                <div>
                                                                    <p class="fw-bold text-primary mb-0"><c:out value="${recentMovements.product_name}"/></p>
                                                                    <p class="text-muted mb-0"><c:out value="${recentMovements.card_number}"/></p>
                                                                </div>
                                                                <div></div><small class="fs-5 fw-semibold text-end d-lg-flex flex-fill justify-content-lg-end align-items-lg-center" style="margin-right: 8px;"><span class="fs-6 text-black-50" style="margin-right: 10px;"><c:out value="${recentMovements.mov_date_string}"/></span>
                                                                <c:choose>
                                                                    <c:when test="${recentMovements.price >= 0}">
                                                                        +<fmt:formatNumber type = "number" maxFractionDigits = "2" minFractionDigits="2" value = "${recentMovements.price}"/>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <fmt:formatNumber type = "number" maxFractionDigits = "2" minFractionDigits="2" value = "${recentMovements.price}"/>
                                                                    </c:otherwise>
                                                                </c:choose>€</small>
                                                            </div>
                                                        </c:forEach>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </c:otherwise>
                    </c:choose>

                </tr>
            </tbody>
        </table>
    </div>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/bs-init.js"></script>
</body>

</html>