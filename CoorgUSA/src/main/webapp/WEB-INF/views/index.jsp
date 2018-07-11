<%@ page session="false" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Coorg USA WebPage">
<meta name="author" content="Kushalappa Ponnimada">
<title>Coorg-USA:Default</title>
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
<spring:url value="/contactus" var="urlContactUs" />
</head>
<body background="<c:url value='/resources/images/coorg.jpg' />">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="well">
					<p class="text-primary">Coorg-USA</p>
					<h2 class="text-primary">${greeting}</h2>
				</div>
			</div>
		</div>
		<!--Row with 2 columns divided in 6:6 ratio-->
		<div class="row">
			<div class="col-md-6">
				<p>Join our site today, to know the exciting events happening in US !</p> <a class="btn btn-lg btn-success" href="${urlRegistration}" role="button">Sign up today</a>
			</div>
			<div class="col-md-6">
				<p>Already a member ? Login in to view our site.</p> <a class="btn btn-lg btn-primary" href="${urlLogin}" role="button">Log In</a>
			</div>
		</div>
		<hr>
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>