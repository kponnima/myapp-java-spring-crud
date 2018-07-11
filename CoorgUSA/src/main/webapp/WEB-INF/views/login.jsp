<%@ page session="true" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Coorg USA WebPage">
<meta name="author" content="Kushalappa Ponnimada">
<title>Coorg-USA:Login</title>
<!-- CSS file -->
<link href="<c:url value="/resources/css/jquery-ui.min.css" />" rel="stylesheet"></link>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"></link>
<link href="<c:url value="/resources/css/bootstrap-theme.min.css" />" rel="stylesheet"></link>
<!-- JS files -->
<script src="<c:url value="/resources/js/jquery.js" />"></script>
<script src="<c:url value="/resources/js/jquery-ui.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<!-- MVC required URLs -->
<spring:url value="/registration" var="urlRegistration" />
<spring:url value="/login" var="urlLogin" />
<spring:url value="/forgotpswd" var="urlforgotpswd" />
</head>
<body>
	<div class="container">
		<jsp:include page="header.jsp" />
		<hr>
		<div class="row">
			<div class="col-md-offset-3 col-md-6">
				<div class="well">
					<h2>Login Form</h2>
					<c:if test="${not empty error}">
						<div class="alert alert-danger">${error}</div>
					</c:if>
					<c:if test="${not empty msg}">
						<div class="alert alert-success">${msg}</div>
					</c:if>
					<c:if test="${not empty success}">
						<div class="alert alert-success">
							<strong>${success}</strong>
						</div>
					</c:if>
					<c:if test="${not empty passwordreset}">
						<div class="alert alert-success">
							<strong>${passwordreset}</strong>
						</div>
					</c:if>
					<form class="form-horizontal" action="<c:url value='/login'/>" method="POST" id="form-Login">
						<div class="input-group input-sm">
							<label class="input-group-addon" for="username"><span class="glyphicon glyphicon-user"></span></label> <input type="text" class="form-control :focus" id="username" name="username"
								placeholder="Enter Username" autofocus="autofocus" required="required">
						</div>
						<div class="input-group input-sm">
							<label class="input-group-addon" for="password"><span class="glyphicon glyphicon-lock"></span></label> <input type="password" class="form-control" id="password" name="password"
								placeholder="Enter Password" required="required">
						</div>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<div class="form-actions">
							<input type="submit" class="btn btn-block btn-primary btn-default" value="Log in">
						</div>
					</form>
					<br/>
					<a href="${urlforgotpswd}">Forgot Your Password ?</a>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-3 col-md-6">
				<div class="well">
					<h3 class="text-center">Not a member yet ? Register for Free</h3>
					<div class="row">
						<div class="col-sm-offset-4 col-sm-4">
							<a class="btn btn-success btn-lg btn-block" href="${urlRegistration}" role="button">Sign up</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<hr>
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>