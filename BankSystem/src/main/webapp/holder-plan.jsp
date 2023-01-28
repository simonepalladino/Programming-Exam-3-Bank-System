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
    <form method="post">
        <div class="container py-4 py-xl-5">
            <div class="row flash animated mb-5">
                <div class="col-md-8 col-xl-6 text-center mx-auto">
                    <h2>Change your plan</h2>
                    <p class="w-lg-50">Explore endless possibilities with your cash by making a one-time purchase</p>
                </div>
            </div>
            <div class="row gy-4 row-cols-1 row-cols-md-2 row-cols-xl-3 d-xl-flex">
                <div class="col">
                    <div class="card mb-4">
                        <div class="card-body text-center p-4">
                            <h4 class="fw-bold card-subtitle">Basic</h4>
                            <h4 class="display-5 fw-bold card-title">0€</h4>
                            <p>It&#39;s free, forever and ever</p>
                            <button class="btn btn-light d-block w-100" type="submit" name="selected" value="Basic" <c:if test="${sessionScope.selectedHolder.contract_type == 'Basic'}">disabled</c:if>>
                                <c:choose>
                                    <c:when test="${sessionScope.selectedHolder.contract_type == 'Basic'}">
                                        You're already a Basic user!
                                    </c:when>
                                    <c:otherwise>
                                        Downgrade to Basic
                                    </c:otherwise>
                                </c:choose>
                            </button>
                        </div>
                    </div>
                    <div class="bg-light border rounded border-light p-4">
                        <ul class="list-unstyled">
                            <li class="d-flex mb-2"><span class="bs-icon-xs bs-icon-rounded bs-icon-primary-light bs-icon me-2"><svg class="bi bi-check-lg" xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16">
                            <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"></path>
                        </svg></span><span>Your money in a Bank, for free.</span></li>
                            <li class="d-flex mb-2"><span class="bs-icon-xs bs-icon-rounded bs-icon-primary-light bs-icon me-2"><svg class="bi bi-check-lg" xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16">
                            <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"></path>
                        </svg></span><span>You can spend maximum 2000€ per day, sorry.</span></li>
                            <li class="d-flex mb-2"><span class="bs-icon-xs bs-icon-rounded bs-icon-primary-light bs-icon me-2"><svg class="bi bi-check-lg" xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16">
                            <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"></path>
                        </svg></span><span>You have no discounts from us.</span></li>
                        </ul>
                    </div>
                </div>
                <div class="col">
                    <div class="card border-primary border-2 mb-4">
                        <div class="card-body text-center p-4"><span class="badge rounded-pill bg-primary position-absolute top-0 start-50 translate-middle text-uppercase">Most Popular</span>
                            <h4 class="fw-bold card-subtitle">Premium</h4>
                            <h4 class="display-5 fw-bold card-title">100€<span class="fs-4 fw-normal text-muted"></span></h4>
                            <p>Then you will be amazed, until you die (or change your Bank)</p>
                            <button class="btn btn-primary d-block w-100" type="submit" name="selected" value="Premium" <c:if test="${sessionScope.selectedHolder.contract_type == 'Premium'}">disabled</c:if>>
                                <c:choose>
                                    <c:when test="${sessionScope.selectedHolder.contract_type == 'Enterprise'}">
                                        Downgrade to Premium
                                    </c:when>
                                    <c:when test="${sessionScope.selectedHolder.contract_type == 'Basic'}">
                                        Upgrade to Premium
                                    </c:when>
                                    <c:otherwise>
                                        You're already a Premium user!
                                    </c:otherwise>
                                </c:choose>
                            </button>
                        </div>
                    </div>
                    <div class="bg-light border rounded border-light p-4">
                        <ul class="list-unstyled">
                            <li class="d-flex mb-2"><span class="bs-icon-xs bs-icon-rounded bs-icon-primary-light bs-icon me-2"><svg class="bi bi-check-lg" xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16">
                            <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"></path>
                        </svg></span><span>Still your money here, still for free.</span></li>
                            <li class="d-flex mb-2"><span class="bs-icon-xs bs-icon-rounded bs-icon-primary-light bs-icon me-2"><svg class="bi bi-check-lg" xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16">
                            <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"></path>
                        </svg></span><span>You can withdraw up to 10.000€ per day!</span></li>
                            <li class="d-flex mb-2"><span class="bs-icon-xs bs-icon-rounded bs-icon-primary-light bs-icon me-2"><svg class="bi bi-check-lg" xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16">
                            <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"></path>
                        </svg></span><span>Our discounts from wonderful partners.</span></li>
                            <li class="d-flex mb-2"><span class="bs-icon-xs bs-icon-rounded bs-icon-primary-light bs-icon me-2"><svg class="bi bi-check-lg" xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16">
                            <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"></path>
                        </svg></span><span>A better status symbol (jocking).</span></li>
                        </ul>
                    </div>
                </div>
                <div class="col">
                    <div class="card mb-4">
                        <div class="card-body text-center p-4">
                            <h4 class="fw-bold card-subtitle">Enterprise</h4>
                            <h4 class="display-5 fw-bold card-title">
                                <c:choose>
                                    <c:when test="${sessionScope.selectedHolder.contract_type == 'Premium'}">
                                        900€
                                    </c:when>
                                    <c:otherwise>
                                        1000€
                                    </c:otherwise>
                                </c:choose>
                                <span class="fs-4 fw-normal text-muted"></span></h4>
                            <p>It&#39;s a must if you need all of this</p>
                            <button class="btn btn-dark d-block w-100" type="submit" name="selected" value="Enterprise" <c:if test="${sessionScope.selectedHolder.contract_type == 'Enterprise'}">disabled</c:if>>
                                <c:choose>
                                    <c:when test="${sessionScope.selectedHolder.contract_type != 'Enterprise'}">
                                        Upgrade to Enterprise
                                    </c:when>
                                    <c:otherwise>
                                        You're already an Enterprise user!
                                    </c:otherwise>
                                </c:choose>
                            </button>
                        </div>
                    </div>
                    <div class="bg-light border rounded border-light p-4">
                        <ul class="list-unstyled">
                            <li class="d-flex mb-2"><span class="bs-icon-xs bs-icon-rounded bs-icon-primary-light bs-icon me-2"><svg class="bi bi-check-lg" xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16">
                            <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"></path>
                        </svg></span><span>Unlimited money in your account.</span></li>
                            <li class="d-flex mb-2"><span class="bs-icon-xs bs-icon-rounded bs-icon-primary-light bs-icon me-2"><svg class="bi bi-check-lg" xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16">
                            <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"></path>
                        </svg></span><span>Even more discounts (up to 20%).</span></li>
                            <li class="d-flex mb-2"><span class="bs-icon-xs bs-icon-rounded bs-icon-primary-light bs-icon me-2"><svg class="bi bi-check-lg" xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16">
                            <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"></path>
                        </svg></span><span>Withdrawals extended to 100.000€ per day!</span></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/bs-init.js"></script>
</body>

</html>