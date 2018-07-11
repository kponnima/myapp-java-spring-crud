<%@ page session="false" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
<title>Coorg-USA:Thank You</title>
<!-- JQuery CSS and JS files -->
<script src="<c:url value="/resources/js/jquery.js" />"></script>
<script src="<c:url value="/resources/js/jquery-ui.min.js" />"></script>
<link href="<c:url value="/resources/css/jquery-ui.min.css" />" rel="stylesheet"></link>
<!-- Bootstrap CSS file -->
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"></link>
<link href="<c:url value="/resources/css/bootstrap-theme.min.css" />" rel="stylesheet"></link>
<!-- Bootstrap's JavaScript plugin-->
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<!-- MVC required URLs -->
<spring:url value="login?logout" var="urlLogin" />
</head>
<body>
	<div class="container">
		<jsp:include page="header.jsp" />
		<hr>

		<div class="row">
			<div class="col-md-12">
				<div class="jumbotron">
					<h2 class="text-center">Thank you for visiting our web site.</h2>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-offset-4 col-sm-4">
				<form action="${urlLogin}" method="get">
					<input class="btn btn-success btn-lg btn-block" id="loginagainnbtn" type="submit" value="Login Again" />
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</div>
		</div>
		<hr>
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>