<%@ page session="true" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
<title>Coorg-USA:Search</title>
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
<spring:url value="/logout" var="urlLogout" />
<spring:url value="/resources/images" var="images" />
</head>
<body>
	<div class="container">
		<jsp:include page="header1.jsp" />
		<hr>
		<!--Row with three columns divided in 1:4:1 ratio-->
		<div class="row">
			<div class="col-md-2">
				<jsp:include page="aside1.jsp" />
			</div>
			<div class="col-md-8">
				<article>
					<div class="row">
						<div class="col-md-12">
							<div class="pull-right">
								<form action="${urlLogout}" method="get">
									<input class="btn btn-primary btn-sm" id="logoutbtn" type="submit" value="Logout" /> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								</form>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="jumbotron">
								<h2>View Member Profiles</h2>
							</div>
						</div>
					</div>
					<c:choose>
						<c:when test="${not empty emptyuser}">
							<div class="row">
								<div class="col-md-12">
									<div class="well">
										<h4 class="text-danger">
											No matching member profile found for '<strong>${searchString}</strong>' search string. Please try a different search.
										</h4>
									</div>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="row">
								<div class="col-sm-6 col-md-4">
									<div class="thumbnail">
										<img src="${images}/coorg_saree1.jpg" alt="Profile Photo" class="img-rounded">
										<div class="caption">
											<h4 class="text-center">Profile Photo</h4>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="panel panel-default">
										<!-- Default panel contents -->
										<div class="panel-heading">
											<span class="lead">Member Info</span>
										</div>
										<table class="table table-bordered">
											<tr>
												<th>Firstname</th>
												<td>${searcheduser.firstname}</td>
											</tr>
											<tr>
												<th>Lastname</th>
												<td>${searcheduser.lastname}</td>
											</tr>
											<tr>
												<th>Email</th>
												<td>${searcheduser.email}</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</article>
			</div>
			<div class="col-md-2">
				<jsp:include page="aside2.jsp" />
			</div>
		</div>
		<hr>
		<jsp:include page="footer1.jsp" />
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>