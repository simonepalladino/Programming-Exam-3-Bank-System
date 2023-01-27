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

<c:url value="errorpage.jsp" var="displayURL">
    <c:param name="error"   value="${param.error}" />
    <c:param name="backurl"   value="${param.backurl}" />
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
    <section class="position-relative py-4 py-xl-5"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-exclamation-octagon text-center d-flex d-sm-flex d-md-flex d-lg-flex justify-content-center mx-auto justify-content-sm-center justify-content-md-center justify-content-lg-center align-items-lg-center" style="font-size: 209px;padding-top: 0px;padding-bottom: 0px;margin-bottom: 13px;margin-top: 8px;color: var(--bs-blue);">
            <path d="M4.54.146A.5.5 0 0 1 4.893 0h6.214a.5.5 0 0 1 .353.146l4.394 4.394a.5.5 0 0 1 .146.353v6.214a.5.5 0 0 1-.146.353l-4.394 4.394a.5.5 0 0 1-.353.146H4.893a.5.5 0 0 1-.353-.146L.146 11.46A.5.5 0 0 1 0 11.107V4.893a.5.5 0 0 1 .146-.353L4.54.146zM5.1 1 1 5.1v5.8L5.1 15h5.8l4.1-4.1V5.1L10.9 1H5.1z"></path>
            <path d="M7.002 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0zM7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 4.995z"></path>
        </svg>
        <div class="container position-relative">
            <div class="row d-flex justify-content-center align-items-lg-center" style="margin-bottom: 88px;margin-top: 19px;">
                <div class="col-md-8 col-lg-6 col-xl-5 col-xxl-4">
                    <div class="card mb-5">
                        <div class="card-body p-sm-5" style="margin-bottom: -17px;padding-top: 91px;padding-bottom: 91px;margin-top: 3px;">
                            <h2 class="text-center mb-4" style="margin-top: -29px;">Operation failed</h2>
                            <form method="post">
                                <div class="text-center mb-3" style="margin-top: -27px;">
                                    <c:choose>
                                        <c:when test="${param.error == 'canceloperation'}">
                                            <sub>The system couldn&#39;t cancel the following operation</sub>
                                        </c:when>
                                        <c:when test="${param.error == 'nooperation'}">
                                            <sub>The system couldn&#39;t find a valid operation</sub>
                                        </c:when>
                                        <c:when test="${param.error == 'withdraw'}">
                                            <sub>There was an error while reading money quantity</sub>
                                        </c:when>
                                        <c:when test="${param.error == 'nocards'}">
                                            <sub>The user couldn't do any transaction: no debit/credit cards found</sub>
                                        </c:when>
                                        <c:when test="${param.error == 'nofund'}">
                                            <sub>The user hasn't enough fund available on selected card</sub>
                                        </c:when>
                                        <c:when test="${param.error == 'nodeposit'}">
                                            <sub>The user has exceeded the deposit limit for his account type</sub>
                                        </c:when>
                                        <c:when test="${param.error == 'nowithdraw'}">
                                            <sub>The user has exceeded the withdrawal limit for his account type</sub>
                                        </c:when>
                                        <c:when test="${param.error == 'expiredcard'}">
                                            <sub>The selected card has expired</sub>
                                        </c:when>
                                        <c:otherwise>
                                            <sub>There was an error while doing latest operation</sub>
                                        </c:otherwise>
                                    </c:choose>
                                    </div>
                                <div class="text-center">
                                    <div class="btn-group" role="group">
                                        <c:choose>
                                            <c:when test="${param.backurl != null}">
                                                <a class="btn btn-primary" role="button" style="padding-left: 41px;padding-right: 41px;" href='<c:out value="${param.backurl}"/>'>Ok</a></div>
                                            </c:when>
                                            <c:otherwise>
                                                <a class="btn btn-primary" role="button" style="padding-left: 41px;padding-right: 41px;" href="dashboard?logintype=holder">Ok</a></div>
                                            </c:otherwise>
                                        </c:choose>
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