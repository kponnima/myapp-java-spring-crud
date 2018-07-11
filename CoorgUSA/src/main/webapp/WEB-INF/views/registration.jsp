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
<title>Coorg-USA:Registration</title>
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
<spring:url value="/registration" var="urlRegistration" />
<spring:url value="/login" var="urlLogin" />
</head>
<body>
	<div class="container">
		<jsp:include page="header.jsp" />
		<hr>
		<article>
			<div class="row">
				<div class="well col-md-6">
					<h2>User Registration Form</h2>
					<form:form method="POST" modelAttribute="user" class="form-horizontal" id="formRegistration">
						<div class="form-group has-error">
							<form:errors path="*" class="alert alert-danger" />
						</div>
						<div class="form-group">
							<label for="firstname">Firstname</label>
							<form:input class="form-control :focus input-sm" id="firstname" path="firstname" name="firstName" type="text" placeholder="First Name" autofocus="autofocus" required="required" />
							<div class="has-error">
								<form:errors path="firstname" class="help-inline" />
							</div>
						</div>
						<div class="form-group">
							<label for="lastname">Lastname</label>
							<form:input class="form-control input-sm" id="lastname" path="lastname" name="lastName" type="text" placeholder="Last Name" required="required" />
						</div>
						<div class="form-group">
							<label for="username">Username</label>
							<form:input class="form-control input-sm" id="username" path="username" name="username" type="text" placeholder="Username" required="required" />
						</div>
						<div class="form-group">
							<label for="password">Password</label>
							<form:input class="form-control input-sm" id="password" path="password" name="password" autocomplete="off" type="password" placeholder="Password" showpassword="true" required="required" />
						</div>
						<div class="form-group">
							<label for="confirmpassword">Confirm Password</label>
							<form:input class="form-control input-sm" id="confirmpassword" path="confirmpassword" name="confirmpassword" autocomplete="off" type="password" placeholder="Confirm Password"
								required="required" />
						</div>
						<div class="form-group">
							<label for="email">Email</label>
							<form:input class="form-control input-sm" id="email" path="email" name="email" type="email" placeholder="Email@domain.com" required="required" />
						</div>
						<div class="form-group">
							<form:input class="form-control input-sm hidden" type="hidden" path="role" id="role" name="role" value="USER" />
						</div>
						<div class="form-group">
							<form:input class="form-control input-sm hidden" type="hidden" path="status" id="status" name="status" value="ACTIVE" />
						</div>
						<div class="form-actions">
							<div class="col-sm-offset-4 col-sm-10">
								<input type="submit" value="Sign up" class="btn btn-success" id="Signup_Button" />
								<input type="reset" value="Reset" onclick="myFunction()" class="btn btn-default" />
							</div>
						</div>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<script>
							function myFunction() {
							document.getElementById("formRegistration").reset();
						}
						</script>
					</form:form>
				</div>
				<div class="col-md-6">
					<div class="well well-lg">
						<h2>REGISTER TODAY</h2>
						<p>It's free. <span class="glyphicon glyphicon-ok btn-success"></span></p>
						<p>View member profiles. <span class="glyphicon glyphicon-ok btn-success"></span></p>
						<p>Create events. <span class="glyphicon glyphicon-ok btn-success"></span></p>
						<p>Get the latest news. <span class="glyphicon glyphicon-ok btn-success"></span></p>
						<p>Share articles. <span class="glyphicon glyphicon-ok btn-success"></span></p>
					</div>
					<div class="well well-lg">
						<p>Already a member ? Login in to view the site.</p>
						<a class="btn btn-primary" href="${urlLogin}" role="button">Log In</a>
					</div>
				</div>
			</div>
		</article>
		<hr>
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>