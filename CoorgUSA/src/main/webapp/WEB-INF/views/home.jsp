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
<title>Coorg-USA:Home</title>
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
								<h2>${welcomemsg}</h2>
								<p>Want to know the events happening in the valley ? Check out our events calendar !!!</p>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="pull-right"></div>
						</div>
					</div>
					<!------------------------- User list link- Only for admin users ------------------------->
					<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
						<div class="well">
							<h2>
								Dear <strong>Admin User</strong>, view the list of site users
							</h2>
							<div class="row">
								<div class="col-md-12">
									<div class="center-block">
										<p><a class="btn btn-lg btn-success" href="${urluserslist}" role="button">Users List</a></p>
									</div>
								</div>
							</div>
						</div>
					</sec:authorize>
					<!------------------------- End User list link ------------------------->
					<!------------------------- Family list ------------------------->
					<div class="well">
						<p>Do you know all the families from Coorg ? Take a look at the list and let us know if you see any name missing and we'll have it added here.</p>

						<div>
							<!-- Button HTML (to Trigger Modal) -->
							<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#viewFamilyModal" data-title="Addevent">Family list</button>

							<!-- Modal HTML -->
							<div id="viewFamilyModal" class="modal fade">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
											<h4 class="modal-title">Family List</h4>
										</div>
										<div class="modal-body">
											<c:if test="${not empty lists}">
												<table class="table table-bordered">
													<tr>
														<th class="success text-center">#</th>
														<th class="success">Family Name</th>
													</tr>
													<c:set var="count" value="0" scope="page" />
													<c:forEach items="${lists}" var="listValue">
														<c:set var="count" value="${count + 1}" scope="page" />
														<tr>
															<td class="text-center"><c:out value="${count}" /></td>
															<td><c:out value="${listValue}" /></td>
														</tr>
													</c:forEach>
												</table>
											</c:if>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-------------------------End Family list ------------------------->
					<div class="well">
						<p>Want to know the events happening in US ? Check out our events calendar !!!</p>
					</div>
					<!------------------------- Comment block ------------------------->
					<div class="well">

						<h4>Leave a comment</h4>

						<form role="form" class="clearfix">
							<div class="col-md-6 form-group">
								<label class="sr-only" for="name">Name</label> <input type="text" class="form-control" id="name" placeholder="Name">
							</div>

							<div class="col-md-6 form-group">
								<label class="sr-only" for="email">Email</label> <input type="email" class="form-control" id="email" placeholder="Email">
							</div>

							<div class="col-md-12 form-group">
								<label class="sr-only" for="comment">Comment</label>
								<textarea class="form-control" id="comment" placeholder="Comment"></textarea>
							</div>

							<div class="col-md-12 form-group text-right">
								<button type="submit" class="btn btn-primary">Submit</button>
							</div>
						</form>
					</div>
					<!-------------------------End comment block ------------------------->
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