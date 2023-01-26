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
                            <input type="submit" value="Home" style="background:none; border-width:0px;" class="nav-link active" name="logintype"/></form></li>
                    <li class="nav-item">
                        <form method="get" action="holder-deposit">
                            <input type="submit" value="Deposit" style="background:none; border-width:0px;" class="nav-link"/></form></li>
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
    <div class="table-responsive" style="border-style: none;">
        <table class="table">
            <tbody>
                <tr>
                    <td style="border-style: none;width: 80%;">
                        <div class="container py-4 py-xl-5" style="width: 100%;height: 100%;"><strong class="fs-4 fw-bold text-center border rounded border-1 d-lg-flex justify-content-lg-center" style="margin-left: 0px;margin-right: 0px;">Recent transactions</strong>
                            <!-- Inserimento dei movimenti -->
                            <c:if test="${recentMovements.size() > 0}">
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

                                <div class="col">
                                    <div></div>
                                </div>
                                <div class="row">
                                    <div class="col text-center" style="border-style: none;">
                                        <form method="get" action="holder-canceloperation">
                                            <button class="btn btn-primary text-center" type="submit" style="width: 80%;background: var(--bs-danger);border-color: var(--bs-danger);" name="backurl" value="dashboard?logintype=holder">Cancel latest operation</button></form>
                                    </div>
                                    <div class="col text-center" style="border-style: none;">
                                        <form method="get" action="holder-deposit">
                                            <button class="btn btn-primary text-center" type="submit" style="width: 80%;" name="view" value="all">View all</button></form>
                                    </div>
                                </div>
                            </c:if>


                        </div>
                    </td>
                    <td class="text-center" style="border-style: none;"><br><strong class="fs-4 fw-bold text-center border rounded border-1 d-lg-flex justify-content-lg-center align-items-lg-center" style="margin-left: 0px;margin-right: 0px;">Total balance</strong>
                        <div class="text-center d-flex flex-column justify-content-center align-items-center justify-content-lg-center py-3" style="margin-top: 19px;margin-bottom: 19px;">
                            <div class="bs-icon-xl bs-icon-circle bs-icon-primary-light d-flex flex-shrink-0 justify-content-center align-items-center d-inline-block mb-2 bs-icon lg" style="padding-left: 0px;padding-right: 0px;padding-top: 0px;padding-bottom: 0px;box-shadow: 0px 0px 9px;"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-cash-coin" style="font-size: 52px;">
                                    <path fill-rule="evenodd" d="M11 15a4 4 0 1 0 0-8 4 4 0 0 0 0 8zm5-4a5 5 0 1 1-10 0 5 5 0 0 1 10 0z"></path>
                                    <path d="M9.438 11.944c.047.596.518 1.06 1.363 1.116v.44h.375v-.443c.875-.061 1.386-.529 1.386-1.207 0-.618-.39-.936-1.09-1.1l-.296-.07v-1.2c.376.043.614.248.671.532h.658c-.047-.575-.54-1.024-1.329-1.073V8.5h-.375v.45c-.747.073-1.255.522-1.255 1.158 0 .562.378.92 1.007 1.066l.248.061v1.272c-.384-.058-.639-.27-.696-.563h-.668zm1.36-1.354c-.369-.085-.569-.26-.569-.522 0-.294.216-.514.572-.578v1.1h-.003zm.432.746c.449.104.655.272.655.569 0 .339-.257.571-.709.614v-1.195l.054.012z"></path>
                                    <path d="M1 0a1 1 0 0 0-1 1v8a1 1 0 0 0 1 1h4.083c.058-.344.145-.678.258-1H3a2 2 0 0 0-2-2V3a2 2 0 0 0 2-2h10a2 2 0 0 0 2 2v3.528c.38.34.717.728 1 1.154V1a1 1 0 0 0-1-1H1z"></path>
                                    <path d="M9.998 5.083 10 5a2 2 0 1 0-3.132 1.65 5.982 5.982 0 0 1 3.13-1.567z"></path>
                                </svg></div>
                            <div class="px-3">
                                <h2 class="fw-bold tada animated mb-0"><fmt:formatNumber type = "number" maxFractionDigits = "2" minFractionDigits="2" value = "${requestScope.balance}"/>€</h2>
                                <c:choose>
                                    <c:when test="${requestScope.balance > 0}">
                                        <p class="mb-0">Your account is in order</p>
                                    </c:when>
                                    <c:when test="${requestScope.balance == 0}">
                                        <p class="mb-0">Your account is empty</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="mb-0">You're in a bad state</p>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="border-0" style="overflow: auto;max-width: 652.469px;min-height: 100%;"><strong class="fs-4 fw-bold text-center border rounded border-1 d-lg-flex justify-content-lg-center" style="margin-left: 0px;margin-right: 0px;">Your cards</strong>
                        <div class="table-responsive border-0 d-flex float-start" style="overflow: auto;margin-top: 20px;">
                            <table class="table">
                                <tbody>
                                    <tr>
                                        <c:forEach var="cardList" items="${cardList}">
                                            <td class="border-0">
                                                <div class="text-center d-flex flex-column flex-wrap justify-content-lg-start align-items-lg-center align-items-xl-center" style="background: linear-gradient(39deg, var(--bs-gray-400), white), var(--bs-table-border-color);border-radius: 12px;width: 258.469px;">
                                                    <div class="bs-icon-lg bs-icon-rounded bs-icon-primary d-flex flex-shrink-0 justify-content-center align-items-center d-inline-block mb-3 bs-icon lg" style="background: linear-gradient(-5deg, var(--bs-gray-dark), var(--bs-gray) 77%), var(--bs-gray);margin-left: 5px;margin-top: 3px;"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-credit-card">
                                                        <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4zm2-1a1 1 0 0 0-1 1v1h14V4a1 1 0 0 0-1-1H2zm13 4H1v5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V7z"></path>
                                                        <path d="M2 10a1 1 0 0 1 1-1h1a1 1 0 0 1 1 1v1a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1v-1z"></path>
                                                    </svg></div>
                                                    <div class="px-3">
                                                        <h5 class="text-center" style="margin-top: 6px;"><c:out value="${cardList.card_name}"/></h5>
                                                        <p class="fs-6" style="margin-bottom: 0px;"><c:out value="${cardList.card_number}"/><br></p>
                                                        <p class="fs-6"><strong>Balance</strong>: <fmt:formatNumber type = "number" maxFractionDigits = "2" minFractionDigits="2" value = "${cardList.balance}"/>€</p>
                                                    </div>
                                                </div>
                                            </td>
                                        </c:forEach>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </td>
                    <td class="border-0"><strong class="fs-4 fw-bold text-center border rounded border-1 d-lg-flex justify-content-lg-center align-items-lg-center" style="margin-left: 0px;margin-right: 0px;">Your plan</strong>
                        <div class="text-center d-flex flex-column justify-content-center align-items-center justify-content-lg-center py-3" style="margin-bottom: 19px;margin-top: -9px;">
                            <h2 class="fs-4 fw-semibold mb-0" style="margin-top: 21px;"><c:out value="${sessionScope.selectedHolder.contract_type}"/></h2>
                            <div class="bs-icon-xl bs-icon-circle d-flex flex-shrink-0 justify-content-center align-items-center d-inline-block mb-2 bs-icon lg" data-bss-hover-animate="tada" style="padding-left: 0px;padding-right: 0px;padding-top: 0px;padding-bottom: 0px;box-shadow: 0px 0px 9px;color: var(--bs-table-striped-color);background: linear-gradient(-149deg, var(--bs-yellow), var(--bs-table-border-color)), var(--bs-yellow);margin-top: 8px;"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-suit-club-fill" style="font-size: 52px;">
                                    <path d="M11.5 12.5a3.493 3.493 0 0 1-2.684-1.254 19.92 19.92 0 0 0 1.582 2.907c.231.35-.02.847-.438.847H6.04c-.419 0-.67-.497-.438-.847a19.919 19.919 0 0 0 1.582-2.907 3.5 3.5 0 1 1-2.538-5.743 3.5 3.5 0 1 1 6.708 0A3.5 3.5 0 1 1 11.5 12.5z"></path>
                                </svg></div>
                            <div class="px-3" style="margin-bottom: -40px;">
                                <c:choose>
                                    <c:when test="${sessionScope.selectedHolder.contract_type == 'Basic'}">
                                        <p class="mb-0">You're good!</p>
                                    </c:when>
                                    <c:when test="${sessionScope.selectedHolder.contract_type == 'Premium'}">
                                        <p class="mb-0">You're special.</p>
                                    </c:when>
                                    <c:when test="${sessionScope.selectedHolder.contract_type == 'Enterprise'}">
                                        <p class="mb-0">You're the best.</p>
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/bs-init.js"></script>
</body>

</html>