<%@ page session="true" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Coorg USA WebPage">
<meta name="author" content="Kushalappa Ponnimada">
<title>Coorg-USA:Contact Us</title>
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
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
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
								<h2>Contact Us Page</h2>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<br /> Email : <a href="mailto:kushalappapp@gmail.com">kushalappapp@gmail.com</a> <br /> Phone : +1 623 363 7164 <br />
							<address>
								19940 N 23rd Ave<br /> Apt 1064C<br /> Phoenix,Arizona<br /> 85027<br /> USA
							</address>

							<div class="contact-form">
								<form class="form-horizontal col-md-8" role="form">

									<div class="form-group">
										<label for="name" class="col-md-2">Name</label>
										<div class="col-md-10">
											<input type="text" class="form-control" id="name" placeholder="Name">
										</div>
									</div>

									<div class="form-group">
										<label for="email" class="col-md-2">Email</label>
										<div class="col-md-10">
											<input type="email" class="form-control" id="email" placeholder="Email">
										</div>
									</div>

									<div class="form-group">
										<label for="subject" class="col-md-2">Subject</label>
										<div class="col-md-10">
											<input type="text" class="form-control" id="subject" placeholder="Subject">
										</div>
									</div>

									<div class="form-group">
										<label for="message" class="col-md-2">Message</label>
										<div class="col-md-10">
											<textarea class="form-control" id="message" placeholder="Message"></textarea>
										</div>
									</div>

									<div class="form-group">
										<div class="col-md-12 text-right">
											<button type="submit" class="btn btn-lg btn-primary">Submit your message!</button>
										</div>
									</div>
								</form>
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