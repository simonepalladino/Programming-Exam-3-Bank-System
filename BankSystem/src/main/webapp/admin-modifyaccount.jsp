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

<c:url value="login.jsp" var="displayURL">
    <c:param name="selectedAccount"   value="${param.selectedAccount}" />
</c:url>

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
    <div style="border-style: none;margin-top: 10px;">
        <table class="table table-borderless">
            <tbody>
                <tr>
                    <td style="border-radius: 20px;background: linear-gradient(rgba(220,53,69,0.57), white 76%);width: 45%;">
                        <div class="row mb-5">
                            <div class="col-md-8 col-xl-6 text-center mx-auto">
                                <h2 style="font-weight: bold;color: var(--bs-body-bg);">Account info</h2>
                                <p class="w-lg-50" style="color: var(--bs-body-bg);">Want to remove this person?<br></p>
                                <form method="post">
                                <button class="btn btn-danger" type="submit" style="margin-bottom: 5px;margin-top: -9px;" name="delete" value="account">Delete account</button>
                                </form>
                            </div>
                        </div>
                        <div class="row mb-5" style="margin-bottom: 0px;padding-bottom: 0px;">
                            <div class="col-md-8 col-xl-6 text-center mx-auto">
                                <h2 style="font-weight: bold;"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-person-badge" style="font-size: 130px;">
                                        <path d="M6.5 2a.5.5 0 0 0 0 1h3a.5.5 0 0 0 0-1h-3zM11 8a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"></path>
                                        <path d="M4.5 0A2.5 2.5 0 0 0 2 2.5V14a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V2.5A2.5 2.5 0 0 0 11.5 0h-7zM3 2.5A1.5 1.5 0 0 1 4.5 1h7A1.5 1.5 0 0 1 13 2.5v10.795a4.2 4.2 0 0 0-.776-.492C11.392 12.387 10.063 12 8 12s-3.392.387-4.224.803a4.2 4.2 0 0 0-.776.492V2.5z"></path>
                                    </svg></h2>
                                <p class="fs-5 fw-semibold text-dark w-lg-50" style="color: var(--bs-body-bg);"><c:out value="${requestScope.selectedHolder.firstname}"/> <c:out value="${requestScope.selectedHolder.lastname}"/><br></p>
                            </div>
                        </div>
                        <div class="row mb-5">
                            <div class="col">
                                <div class="container py-4 py-xl-5" style="margin-bottom: -37px;padding-top: 24px;margin-top: -48px;">
                                    <div class="text-center text-white-50 border rounded border-0 p-3" style="background: var(--bs-red);">
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
                    </td>
                    <td></td>
                    <td style="background: linear-gradient(180deg, rgba(220,53,69,0.52), white 79%);border-radius: 20px;">
                        <div class="row mb-5">
                            <div class="col-md-8 col-xl-6 text-center mx-auto">
                                <h2 style="font-weight: bold;color: var(--bs-body-bg);padding-right: 0px;">Manage credit cards</h2>
                                <p class="w-lg-50" style="color: var(--bs-body-bg);">Too many? Less than expected?<br></p>
                                <form method="get" action="admin-addcard">
                                    <button class="btn btn-light" type="submit" style="padding-left: 66px;padding-right: 66px;" name="selectedAccount" value='<c:out value="${param.selectedAccount}"/>'>Add new card</button>
                                </form>
                            </div>
                        </div>
                        <div class="table-responsive d-flex float-start" style="overflow: auto;margin-top: 20px;max-width: 620px;">
                            <table class="table">
                                <tbody>
                                    <tr>
                                        <c:forEach var="cardList" items="${cardList}">
                                            <td>
                                                <div class="text-center d-flex flex-column flex-wrap justify-content-lg-start align-items-lg-center align-items-xl-center" data-bss-hover-animate="pulse" style="background: linear-gradient(39deg, var(--bs-gray-400), white), var(--bs-table-border-color);border-radius: 12px;width: 258.469px;">
                                                    <div class="bs-icon-lg bs-icon-rounded bs-icon-primary d-flex flex-shrink-0 justify-content-center align-items-center d-inline-block mb-3 bs-icon lg" style="background: linear-gradient(-5deg, var(--bs-gray-dark), var(--bs-gray) 77%), var(--bs-gray);margin-left: 5px;margin-top: 3px;"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-credit-card" style="font-size: 41px;">
                                                        <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4zm2-1a1 1 0 0 0-1 1v1h14V4a1 1 0 0 0-1-1H2zm13 4H1v5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V7z"></path>
                                                        <path d="M2 10a1 1 0 0 1 1-1h1a1 1 0 0 1 1 1v1a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1v-1z"></path>
                                                    </svg></div>
                                                    <div class="px-3">
                                                        <h5 class="text-center" style="margin-top: 6px;"><c:out value="${cardList.card_name}"/></h5>
                                                        <p class="fs-6"><c:out value="${cardList.card_number}"/></p>
                                                        <p class="fs-6 fw-semibold" style="margin-bottom: 0px;">Balance</p>
                                                        <p class="fs-6" style="margin-bottom: 15px;margin-top: -1px;"><fmt:formatNumber type = "number" maxFractionDigits = "2" minFractionDigits="2" value = "${cardList.balance}"/>€</p>
                                                        <form method="post">
                                                        <button class="btn btn-danger" type="submit" name="delete" value='<c:out value="${cardList.card_number}"/>' style="margin-bottom: 5px;margin-top: -9px;">Remove card</button>
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
                </tr>
            </tbody>
        </table>
    </div>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/bs-init.js"></script>
</body>

</html>