<%@ page session="true" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customFiveTaglib.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Coorg USA WebPage">
<meta name="author" content="Kushalappa Ponnimada">
<title>Coorg-USA:News</title>
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
<spring:url value="/news" var="urlnews" />
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
								<h2>News Page</h2>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<c:if test="${not empty error}">
								<div class="alert alert-danger">${error}</div>
							</c:if>
							<c:if test="${not empty success}">
								<div class="alert alert-success">
									<strong>${success}</strong>
								</div>
							</c:if>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<!-- Button HTML (to trigger Modal) -->
							<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createNewsModal" data-title="Createnews">Add Article</button>
						</div>
						<!-- Add-news Modal HTML -->
						<div id="createNewsModal" class="modal fade">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
										<h4 class="modal-title">Create Article</h4>
									</div>
									<div class="modal-body">
										<form:form role="form" method="POST" modelAttribute="news">
											<div class="form-group has-error">
												<form:errors path="*" class="alert alert-danger" />
											</div>
											<div class="form-group">
												<label for="post-title" class="control-label">Title</label>
												<form:input type="text" class="form-control :focus" id="post-title" path="title" placeholder="Article title" required="required" autofocus="autofocus" />
											</div>
											<div class="form-group">
												<label for="post-body" class="control-label">Article</label>
												<form:textarea class="form-control" id="post-body" name="post-body" path="body" placeholder="Article Content" required="required" />
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
												<input type="submit" class="btn btn-primary" value="Save Article">
											</div>
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
										</form:form>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<c:if test="${!empty articles}">
								<h1>
									Articles <span class="badge">${fn:length(articles)}</span>
								</h1>
								<div class="list-group">
									<c:forEach items="${articles}" var="article" varStatus="itr">
										<h3 class="list-group-item">
											<c:out value="${article.title}" />
										</h3>
										<fmt:formatDate value="${article.date}" var="dateString" pattern="dd-MMM-yyyy hh:mm:ss" />
										<p class="list-group-item" style="height: 30px; padding: 5px 15px;">Posted on <c:out value="${dateString}" /> <em>by</em> <mark>
												<c:out value="${article.user.firstname}" />
											</mark> <a href="#"><span class="glyphicon glyphicon-tag"></span></a> <sec:authorize access="hasRole('USER')">
												<c:if test="${article.user.userid == user.userid}">
													<a href="<c:url value='/delete-news-${article.newsid}' />" class="pull-right"><span class="glyphicon glyphicon-trash"></span></a>
													<a href="<c:url value='/edit-news-${article.newsid}' />" class="pull-right"><span class="glyphicon glyphicon-pencil"></span></a>
												</c:if>
											</sec:authorize> <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
												<a href="<c:url value='/delete-news-${article.newsid}' />" class="pull-right"><span class="glyphicon glyphicon-trash"></span></a>
											</sec:authorize> <sec:authorize access="hasRole('ADMIN')">
												<a href="<c:url value='/edit-news-${article.newsid}' />" class="pull-right"><span class="glyphicon glyphicon-pencil"></span></a>
											</sec:authorize>
										</p>
										<p class="list-group-item"><em><c:out value="${article.body}" /></em></p>
									</c:forEach>
								</div>
								<tag:fivepaginate max="5" offset="${offset}" count="${count}" uri="${urlnews}" next="&raquo;" previous="&laquo;" />
							</c:if>
							<c:if test="${empty articles}">
								<h4 class="text-warning">
									<strong>Currently there are no articles to display.</strong>
								</h4>
							</c:if>
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