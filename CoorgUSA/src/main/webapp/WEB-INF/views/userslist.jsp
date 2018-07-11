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
<title>Coorg-USA:Users List</title>
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
							<div class="panel panel-default">
								<!-- Default panel contents -->
								<div class="panel-heading">
									<span class="lead">List of Users</span>
								</div>
								<c:if test="${not empty error}">
									<div class="text-danger">${error}</div>
								</c:if>
								<c:if test="${not empty editsuccess}">
									<div class="alert alert-success">${editsuccess}</div>
								</c:if>
								<c:if test="${not empty deletesuccess}">
									<div class="alert alert-success">${deletesuccess}</div>
								</c:if>
								<c:if test="${not empty success}">
									<div class="alert alert-success">
										<strong>${success}</strong>
									</div>
								</c:if>
								<table class="table table-hover">
									<thead>
										<tr>
											<th>Username</th>
											<th>Firstname</th>
											<th>Lastname</th>
											<th>Email</th>
											<th>Status</th>
											<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
												<th width="100"></th>
											</sec:authorize>
											<sec:authorize access="hasRole('ADMIN')">
												<th width="100"></th>
											</sec:authorize>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${users}" var="user">
											<tr>
												<td>${user.username}</td>
												<td>${user.firstname}</td>
												<td>${user.lastname}</td>
												<td>${user.email}</td>
												<td>${user.status}</td>
												<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
													<td><a href="<c:url value='/edit-user-${user.userid}' />" class="btn btn-success custom-width">Edit</a></td>
												</sec:authorize>
												<sec:authorize access="hasRole('ADMIN')">
													<td><a href="<c:url value='/delete-user-${user.userid}' />" class="btn btn-danger custom-width">Delete</a></td>
												</sec:authorize>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
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