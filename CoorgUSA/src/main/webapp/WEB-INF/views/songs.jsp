<%@ page session="true" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Coorg USA WebPage">
<meta name="author" content="Kushalappa Ponnimada">
<title>Coorg-USA:Songs</title>
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
								<h2>Songs</h2>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<c:if test="${not empty adderror}">
								<div class="alert alert-danger">${adderror}</div>
							</c:if>
							<c:if test="${not empty typeerror}">
								<div class="alert alert-danger">${typeerror}</div>
							</c:if>
						</div>
					</div>
					<c:choose>
						<c:when test="${empty songs}">
							<div class="row">
								<div class="col-sm-3">
									<!-- Button HTML (to trigger Modal) -->
									<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addSongModal" data-title="Addsong">Add Song</button>
								</div>
							</div>
							<br />
							<div class="row">
								<div class="col-md-12">
									<h4 class="text-warning">Currently there are no songs to display !</h4>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="row">
								<div class="col-md-3">
									<!-- Button HTML (to trigger Modal) -->
									<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addSongModal" data-title="Addsong">Add Song</button>
								</div>
							</div>
							<br />
							<div class="row">
								<c:forEach items="${songs}" var="song" varStatus="itr">
									<figure class="figure col-md-4">
										<img src="${images}/song.jpg" class="figure-img img-fluid" alt="${song.title}" style="width: 220px; height: 220px;">
										<figcaption class="figure-caption text-center">${song.title}</figcaption>
										<a title="Download Song" href="<c:url value='/download-song-${song.songid}' />"><span class="glyphicon glyphicon-download-alt"></span></a>
										<sec:authorize access="hasRole('USER')">
											<c:if test="${song.user.userid == user.userid}">
												<a title="Delete Song" href="<c:url value='/delete-song-${song.songid}' />" class="pull-right"><span class="glyphicon glyphicon-trash"></span></a>
											</c:if>
										</sec:authorize>
										<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
											<a title="Delete Song" href="<c:url value='/delete-song-${song.songid}' />" class="pull-right"><span class="glyphicon glyphicon-trash"></span></a>
										</sec:authorize>
									</figure>
								</c:forEach>
							</div>
						</c:otherwise>
					</c:choose>
					<!-- Add-song Modal HTML -->
					<div id="addSongModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
									<h4 class="modal-title">Add Song</h4>
								</div>
								<div class="modal-body">
									<form:form role="form" method="POST" modelAttribute="songBucket" enctype="multipart/form-data">
										<div class="form-group has-error">
											<form:errors path="*" class="alert alert-danger" />
										</div>
										<div class="form-group">
											<label for="song-file" class="control-label">Upload Song</label>
											<form:input type="file" class="form-control" id="song-file" path="songfile" required="required" accept="song/*" />
										</div>
										<div class="form-group">
											<label for="song-description" class="control-label">Description</label>
											<form:input class="form-control" id="song-description" name="song-description" path="description" placeholder="Description (Optional)" />
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
											<input type="submit" class="btn btn-primary" value="Upload">
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