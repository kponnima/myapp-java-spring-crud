<%@ page session="true" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Coorg USA WebPage">
<meta name="author" content="Kushalappa Ponnimada">
<title>Coorg-USA:Edit Post</title>
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
<spring:url value="/blogs" var="urlblogs" />
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
									<span class="lead">Edit Event Form</span>
								</div>
								<div class="panel-body">
									<form:form method="POST" modelAttribute="post" class="form-horizontal">
										<c:if test="${not empty error}">
											<div class="text-danger">${error}</div>
										</c:if>
										<form:input type="hidden" path="postid" id="postid" />
										<form:errors path="*" class="has-error" />
										<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
											<div class="form-group">
												<label for="post-userid" class="control-label">User ID</label>
												<form:input class="form-control" id="post-userid" name="post-userid" path="user.userid" required="required" />
											</div>
											<div class="form-group">
												<label for="post-date" class="control-label">Date</label>
												<form:input class="form-control" id="post-date" name="post-date" path="date" required="required"/>
											</div>
										</sec:authorize>
										<div class="form-group">
											<label for="post-title" class="control-label">Title</label>
											<form:input type="text" class="form-control :focus" id="post-title" path="title" placeholder="Enter title" required="required" autofocus="autofocus" />
										</div>
										<div class="form-group">
											<label for="post-body" class="control-label">Comment</label>
											<form:textarea class="form-control" id="post-body" name="post-body" path="body" required="required" />
										</div>
										<div class="form-group">
											<div class="col-xs-offset-3 col-xs-9">
												<input type="submit" class="btn btn-primary" value="Update"> <a class="btn btn-default" href="${urlblogs}" role="button">Cancel</a>
											</div>
										</div>
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									</form:form>
								</div>
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