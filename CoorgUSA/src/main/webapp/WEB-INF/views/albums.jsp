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
<title>Coorg-USA:Albums</title>
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
								<h2>Albums</h2>
							</div>
						</div>
					</div>
					<c:choose>
						<c:when test="${empty albums}">
							<div class="row">
								<div class="col-sm-12">
									<!-- Button HTML (to trigger Modal) -->
									<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addAlbumModal" data-title="Addalbum">Add Album</button>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<h4 class="text-warning">
										<strong>Currently there are no albums to display.</strong>
									</h4>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="row">
								<div class="col-sm-12">
									<!-- Button HTML (to trigger Modal) -->
									<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addAlbumModal" data-title="Addalbum">Add Album</button>
								</div>
							</div>
							<br/>
							<div class="row">
								<c:forEach items="${albums}" var="album" varStatus="itr">
									<figure class="figure col-md-3">
										<a title="${album.description}" href="<c:url value='/view-photo-${album.albumid}' />"><img src="${images}/album.jpg" class="figure-img img-fluid img-rounded" alt="${album.title}"></a>
										<figcaption class="figure-caption text-center">${album.title}</figcaption>
									</figure>
								</c:forEach>
							</div>
						</c:otherwise>
					</c:choose>
					<!-- Add-album Modal HTML -->
					<div id="addAlbumModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
									<h4 class="modal-title">Create Album</h4>
								</div>
								<div class="modal-body">
									<form:form role="form" method="POST" action="${contextPath}/add-album" modelAttribute="album">
										<div class="form-group has-error">
											<form:errors path="*" class="alert alert-danger" />
										</div>
										<div class="form-group">
											<label for="album-title" class="control-label">Title</label>
											<form:input type="text" class="form-control :focus" id="album-title" path="title" placeholder="Enter title" required="required" autofocus="autofocus" />
										</div>
										<div class="form-group">
											<label for="album-description" class="control-label">Description</label>
											<form:input class="form-control" id="album-description" name="album-description" path="description" placeholder="Description (Optional)" />
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
											<input type="submit" class="btn btn-primary" value="Save Album">
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