<%@ page session="false" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Coorg USA WebPage">
<meta name="author" content="Kushalappa Ponnimada">
<title>Coorg-USA:Update Password</title>
<!-- CSS file -->
<link href="<c:url value="/resources/css/jquery-ui.min.css" />" rel="stylesheet"></link>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"></link>
<link href="<c:url value="/resources/css/bootstrap-theme.min.css" />" rel="stylesheet"></link>
<!-- JS files -->
<script src="<c:url value="/resources/js/jquery.js" />"></script>
<script src="<c:url value="/resources/js/jquery-ui.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<!-- MVC required URLs -->
<spring:url value="/userslist" var="urluserslist" />
<spring:url value="/logout" var="urlLogout" />
<spring:url value="/login" var="urlLogin" />
<spring:url value="/forgotpswd" var="urlforgotpswd" />
</head>
<body>
	<div class="container">
		<jsp:include page="header.jsp" />
		<hr>
		<div class="row">
			<div class="col-md-offset-3 col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="lead">Update Password</span>
					</div>
					<div class="panel-body">
						<form:form class="form-horizontal" method="POST" modelAttribute="user" id="form-updatepswd">
							<form:input type="hidden" path="userid" />
							<form:input class="form-control input-sm hidden" type="hidden" path="firstname" />
							<form:input class="form-control input-sm hidden" type="hidden" path="lastname" />
							<form:input class="form-control input-sm hidden" type="hidden" path="username" />
							<div class="form-group">
								<label for="password">Password</label>
								<form:input class="form-control input-sm" type="password" path="password" id="password" name="password" autocomplete="off" placeholder="Password" required="required" />
							</div>
							<div class="form-group">
								<label for="confirmpassword">Confirm Password</label>
								<form:input class="form-control input-sm" type="password" path="confirmpassword" id="confirmpassword" name="confirmpassword" autocomplete="off" placeholder="Confirm Password" required="required" />
							</div>
							<form:input class="form-control input-sm hidden" type="hidden" path="email" />
							<form:input class="form-control input-sm hidden" type="hidden" path="role" value="USER" />
							<form:input class="form-control input-sm hidden" type="hidden" path="status" value="ACTIVE" />
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<div class="form-actions">
								<input type="submit" id="updatepwd_btn" class="btn btn-md btn-primary" value="Submit"> <a class="btn btn-md btn-default" href="${urlforgotpswd}" role="button">Cancel</a>
							</div>
						</form:form>
						<script type="text/javascript">
							$(document)
									.ready(
											function() {
												$("#updatepwd_btn")
														.click(
																function() {
																	var password = $(
																			"#password")
																			.val();
																	var confirmPassword = $(
																			"#confirmpassword")
																			.val();
																	if (password != confirmPassword) {
																		alert("Passwords do not match.");
																		return false;
																	}
																	return true;
																});
												$('#password').val("");
											});
						</script>
					</div>
				</div>
			</div>
		</div>
		<hr>
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>