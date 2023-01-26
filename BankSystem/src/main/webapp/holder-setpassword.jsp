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

<c:url value="login.jsp" var="displayURL">
    <c:param name="passerror"   value="${param.passerror}" />
</c:url>

<body class="text-center" style="background: linear-gradient(0deg, var(--bs-blue), white 23%);">
    <section class="position-relative py-4 py-xl-5" style="padding-bottom: 143px;"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-shield-lock-fill text-center d-flex d-sm-flex d-md-flex d-lg-flex justify-content-center mx-auto justify-content-sm-center justify-content-md-center justify-content-lg-center align-items-lg-center" style="font-size: 209px;padding-top: 0px;padding-bottom: 0px;margin-bottom: 13px;margin-top: 8px;color: var(--bs-blue);">
            <path fill-rule="evenodd" d="M8 0c-.69 0-1.843.265-2.928.56-1.11.3-2.229.655-2.887.87a1.54 1.54 0 0 0-1.044 1.262c-.596 4.477.787 7.795 2.465 9.99a11.777 11.777 0 0 0 2.517 2.453c.386.273.744.482 1.048.625.28.132.581.24.829.24s.548-.108.829-.24a7.159 7.159 0 0 0 1.048-.625 11.775 11.775 0 0 0 2.517-2.453c1.678-2.195 3.061-5.513 2.465-9.99a1.541 1.541 0 0 0-1.044-1.263 62.467 62.467 0 0 0-2.887-.87C9.843.266 8.69 0 8 0zm0 5a1.5 1.5 0 0 1 .5 2.915l.385 1.99a.5.5 0 0 1-.491.595h-.788a.5.5 0 0 1-.49-.595l.384-1.99A1.5 1.5 0 0 1 8 5z"></path>
        </svg>
        <div class="container position-relative">
            <div class="row d-flex justify-content-center align-items-lg-center" style="margin-bottom: 88px;margin-top: 19px;">
                <div class="col-md-8 col-lg-6 col-xl-5 col-xxl-4">
                    <div class="card mb-5">
                        <div class="card-body p-sm-5" style="margin-bottom: -17px;padding-top: 91px;padding-bottom: 91px;margin-top: 3px;">
                            <h2 class="text-center mb-4" style="margin-top: -29px;">Set a password</h2>
                            <form method="post">
                                <div class="mb-3"><input class="form-control" type="password" id="pass-1" name="password1" placeholder="Insert password"></div>
                                <div class="mb-3"><input class="form-control" type="password" id="pass-2" name="password2" placeholder="Repeat password"></div>
                                <c:choose>
                                    <c:when test="${param.passerror == 'nomatch'}">
                                        <small class="text-danger">Password does not match!</small>
                                    </c:when>
                                    <c:when test="${param.passerror == 'same'}">
                                        <small class="text-danger">You can't set same password as CF!</small>
                                    </c:when>
                                </c:choose>
                                <div class="mb-3"></div>
                                <div><button class="btn btn-primary d-block w-100" type="submit" style="background: var(--bs-blue);border-color: var(--bs-blue);">Done</button></div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>