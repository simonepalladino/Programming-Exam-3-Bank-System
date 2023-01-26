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

<c:url value="holder-canceloperation.jsp" var="displayURL">
    <c:param name="error"   value="${param.error}" />
</c:url>

<body style="background: linear-gradient(0deg, var(--bs-blue), white 23%);">
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
    <section class="position-relative py-4 py-xl-5"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-question-octagon text-center d-flex d-sm-flex d-md-flex d-lg-flex justify-content-center mx-auto justify-content-sm-center justify-content-md-center justify-content-lg-center align-items-lg-center" style="font-size: 209px;padding-top: 0px;padding-bottom: 0px;margin-bottom: 13px;margin-top: 8px;color: var(--bs-blue);">
            <path d="M4.54.146A.5.5 0 0 1 4.893 0h6.214a.5.5 0 0 1 .353.146l4.394 4.394a.5.5 0 0 1 .146.353v6.214a.5.5 0 0 1-.146.353l-4.394 4.394a.5.5 0 0 1-.353.146H4.893a.5.5 0 0 1-.353-.146L.146 11.46A.5.5 0 0 1 0 11.107V4.893a.5.5 0 0 1 .146-.353L4.54.146zM5.1 1 1 5.1v5.8L5.1 15h5.8l4.1-4.1V5.1L10.9 1H5.1z"></path>
            <path d="M5.255 5.786a.237.237 0 0 0 .241.247h.825c.138 0 .248-.113.266-.25.09-.656.54-1.134 1.342-1.134.686 0 1.314.343 1.314 1.168 0 .635-.374.927-.965 1.371-.673.489-1.206 1.06-1.168 1.987l.003.217a.25.25 0 0 0 .25.246h.811a.25.25 0 0 0 .25-.25v-.105c0-.718.273-.927 1.01-1.486.609-.463 1.244-.977 1.244-2.056 0-1.511-1.276-2.241-2.673-2.241-1.267 0-2.655.59-2.75 2.286zm1.557 5.763c0 .533.425.927 1.01.927.609 0 1.028-.394 1.028-.927 0-.552-.42-.94-1.029-.94-.584 0-1.009.388-1.009.94z"></path>
        </svg>
        <div class="container position-relative">
            <div class="row d-flex justify-content-center align-items-lg-center" style="margin-bottom: 88px;margin-top: 19px;">
                <div class="col-md-8 col-lg-6 col-xl-5 col-xxl-4">
                    <div class="card mb-5">
                        <div class="card-body p-sm-5" style="margin-bottom: -17px;padding-top: 91px;padding-bottom: 91px;margin-top: 3px;">
                            <h2 class="text-center mb-4" style="margin-top: -29px;">Are you sure?</h2>
                            <form method="post">
                                <div class="text-center mb-3" style="margin-top: -27px;"><sub>Do you want to cancel this payment?</sub></div>
                                <div class="bg-light border rounded border-3 shadow d-flex align-items-lg-center" style="margin-left: 0px;margin-right: 0px;margin-top: 10px;margin-bottom: 10px;">
                                    <c:choose>
                                        <c:when test="${requestScope.movement.type == 'deposit'}">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-cash text-primary" style="font-size: 46px;margin-left: 8px;margin-right: 8px;">
                                                <path d="M8 10a2 2 0 1 0 0-4 2 2 0 0 0 0 4z"></path>
                                                <path d="M0 4a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V4zm3 0a2 2 0 0 1-2 2v4a2 2 0 0 1 2 2h10a2 2 0 0 1 2-2V6a2 2 0 0 1-2-2H3z"></path>
                                            </svg>
                                        </c:when>
                                        <c:when test="${requestScope.movement.type == 'withdraw'}">
                                            <svg class="bi bi-cash-stack text-primary" xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" style="font-size: 46px;margin-left: 8px;margin-right: 8px;">
                                                <path d="M1 3a1 1 0 0 1 1-1h12a1 1 0 0 1 1 1H1zm7 8a2 2 0 1 0 0-4 2 2 0 0 0 0 4z"></path>
                                                <path d="M0 5a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V5zm3 0a2 2 0 0 1-2 2v4a2 2 0 0 1 2 2h10a2 2 0 0 1 2-2V7a2 2 0 0 1-2-2H3z"></path>
                                            </svg>
                                        </c:when>
                                        <c:when test="${requestScope.movement.type == 'upgrade'}">
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
                                        <p class="fw-bold text-primary mb-0"><c:out value="${requestScope.movement.product_name}"/></p>
                                        <p class="text-muted mb-0"><c:out value="${requestScope.movement.card_number}"/></p>
                                    </div>
                                    <div></div><small class="fs-5 fw-semibold text-end d-lg-flex flex-fill justify-content-lg-end align-items-lg-center" style="margin-right: 8px;"><span class="fs-6 text-black-50" style="margin-right: 10px;"><c:out value="${requestScope.movement.mov_date_string}"/></span>
                                    <c:choose>
                                        <c:when test="${requestScope.movement.price >= 0}">
                                            +<fmt:formatNumber type = "number" maxFractionDigits = "2" minFractionDigits="2" value = "${requestScope.movement.price}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:formatNumber type = "number" maxFractionDigits = "2" minFractionDigits="2" value = "${requestScope.movement.price}"/>
                                        </c:otherwise>
                                    </c:choose>€</small>
                                </div>
                                <div class="text-center">
                                    <div class="btn-group" role="group">
                                        <button class="btn btn-primary" type="submit" style="background: var(--bs-red);border-color: var(--bs-red);" name="todo" value="no">No, I don't</button>
                                        <button class="btn btn-primary" type="submit" name="todo" value="yes">Yes, proceed</button>
                                    </div>
                                </div>
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