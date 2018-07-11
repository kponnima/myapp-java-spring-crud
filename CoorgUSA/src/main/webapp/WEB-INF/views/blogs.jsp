<%@ page session="true" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>Coorg-USA:Blogs</title>
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
<spring:url value="/blogs" var="urlblogs" />
<spring:url value="/reply-post" var="urlreply" />
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
								<h2>Blogs Page</h2>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<spring:hasBindErrors name="date">
								<c:set var="dateHasError" value="true" />
							</spring:hasBindErrors>
							<c:if test="${dateHasError}">
								<div class="alert alert-danger">Unable to create event as date entered is in the past!</div>
							</c:if>
							<c:if test="${not empty error}">
								<div class="alert alert-danger">${error}</div>
							</c:if>
							<c:if test="${not empty adderror}">
								<div class="alert alert-danger">${adderror}</div>
							</c:if>
							<c:if test="${not empty adderrorunique}">
								<div class="alert alert-danger">${adderrorunique}</div>
							</c:if>
							<c:if test="${not empty addsuccess}">
								<div class="alert alert-success">
									<strong>${addsuccess}</strong>
								</div>
							</c:if>
							<c:if test="${not empty editerror}">
								<div class="alert alert-danger">${editerror}</div>
							</c:if>
							<c:if test="${not empty editsuccess}">
								<div class="alert alert-success">${editsuccess}</div>
							</c:if>
							<c:if test="${not empty deletesuccess}">
								<div class="alert alert-success">${deletesuccess}</div>
							</c:if>
							<c:if test="${not empty addreplyerror}">
								<div class="alert alert-danger">${addreplyerror}</div>
							</c:if>
							<c:if test="${not empty addreplysuccess}">
								<div class="alert alert-success">${addreplysuccess}</div>
							</c:if>
							<c:if test="${not empty deletereplysuccess}">
								<div class="alert alert-success">${deletereplysuccess}</div>
							</c:if>
							<c:if test="${not empty addlikeerror}">
								<div class="alert alert-danger">${addlikeerror}</div>
							</c:if>
							<c:if test="${not empty addlikesuccess}">
								<div class="alert alert-success">${addlikesuccess}</div>
							</c:if>
							<c:if test="${not empty adddislikeerror}">
								<div class="alert alert-danger">${adddislikeerror}</div>
							</c:if>
							<c:if test="${not empty adddislikesuccess}">
								<div class="alert alert-success">${adddislikesuccess}</div>
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
							<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addPostModal" data-title="Addpost">Add Post</button>
						</div>
						<!-- Add-post Modal HTML -->
						<div id="addPostModal" class="modal fade">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
										<h4 class="modal-title">Create Post</h4>
									</div>
									<div class="modal-body">
										<form:form role="form" method="POST" modelAttribute="post">
											<div class="form-group has-error">
												<form:errors path="*" class="alert alert-danger" />
											</div>
											<div class="form-group">
												<label for="post-title" class="control-label">Title</label>
												<form:input type="text" class="form-control :focus" id="post-title" path="title" placeholder="Enter title" required="required" autofocus="autofocus" />
											</div>
											<div class="form-group">
												<label for="post-body" class="control-label">Comment</label>
												<form:textarea class="form-control" id="post-body" name="post-body" path="body" required="required" />
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
												<input type="submit" class="btn btn-primary" value="Save Post">
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
							<c:if test="${!empty posts}">
								<h1>
									Posts <span class="badge">${fn:length(posts)}</span>
								</h1>
								<div class="list-group">
									<c:forEach items="${posts}" var="post" varStatus="itr">
										<h3 class="list-group-item">
											<c:out value="${post.title}" />
										</h3>
										<fmt:formatDate value="${post.date}" var="dateString" pattern="dd-MMM-yyyy hh:mm:ss" />
										<p class="list-group-item" style="height: 30px; padding: 5px 15px;">Posted on <c:out value="${dateString}" /> <em>by</em> <mark>
												<c:out value="${post.user.firstname}" />
											</mark> <a href="#"><span class="glyphicon glyphicon-tag"></span></a> <c:if test="${post.likes > 0}">
												<c:choose>
													<c:when test="${post.likes == 1}">
														<a data-toggle="popover" data-placement="right" data-html="true"
															data-content="<c:forEach items="${likes}" var="postlike">
															<c:if test="${postlike.post.postid == post.postid}">${postlike.user.firstname}</c:if>
															</c:forEach>">${post.likes}
															Like</a>
													</c:when>
													<c:otherwise>
														<a data-toggle="popover" data-placement="right" data-html="true" data-content="<c:forEach items="${likes}" var="postlike">
															<c:if test="${postlike.post.postid == post.postid}"><table><tr>${postlike.user.firstname}</tr></table></c:if>
															</c:forEach>">${post.likes} Likes</a>
													</c:otherwise>
												</c:choose>
											</c:if> <c:if test="${post.dislikes > 0}">
												<c:choose>
													<c:when test="${post.dislikes == 1}">
														<a data-toggle="popover" data-placement="right" data-html="true"
															data-content="<c:forEach items="${dislikes}" var="postdislike">
													<c:if test="${postdislike.post.postid == post.postid}">${postdislike.user.firstname}</c:if>
													</c:forEach>">${post.dislikes}
															Dislike</a>
													</c:when>
													<c:otherwise>
														<a data-toggle="popover" data-placement="right" data-html="true"
															data-content="<c:forEach items="${dislikes}" var="postdislike">
													<c:if test="${postdislike.post.postid == post.postid}"><table><tr>${postdislike.user.firstname}</tr></table></c:if>
												</c:forEach>">${post.dislikes}
															Dislikes</a>
													</c:otherwise>
												</c:choose>
											</c:if>
										<sec:authorize access="hasRole('USER')">
											<c:if test="${post.user.userid == user.userid}">
												<a href="<c:url value='/delete-post-${post.postid}' />" class="pull-right"><span class="glyphicon glyphicon-trash"></span></a>
												<a href="<c:url value='/edit-post-${post.postid}' />" class="pull-right"><span class="glyphicon glyphicon-pencil"></span></a>
											</c:if>
										</sec:authorize>
										<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
											<a href="<c:url value='/delete-post-${post.postid}' />" class="pull-right"><span class="glyphicon glyphicon-trash"></span></a>
										</sec:authorize>
										<sec:authorize access="hasRole('ADMIN')">
											<a href="<c:url value='/edit-post-${post.postid}' />" class="pull-right"><span class="glyphicon glyphicon-pencil"></span></a>
										</sec:authorize>
										</p>
										<script>
											$(document)
													.ready(
															function() {
																$(
																		"[data-toggle=popover]")
																		.popover(
																				{
																					html : true,
																					content : function() {
																						return $(
																								'#popover-content')
																								.html();
																					}
																				});
															});
										</script>
										<p class="list-group-item"><em><c:out value="${post.body}" /></em></p>
										<div class="list-group-item" style="height: 30px; padding: 5px 15px;">
											<ul class="list-inline">
												<li>Reply <a title="Reply to post" data-toggle="collapse" data-target="#collapseReply${itr.index +1}" aria-expanded="false" aria-controls="collapseReply"><span
														class="glyphicon glyphicon-share-alt"></span></a></li>
												<li>Like <a title="Like Post" href="<c:url value='/like-post-${post.postid}' />"><span class="glyphicon glyphicon-thumbs-up"></span></a></li>
												<li>Dislike <a title="Dislike Post" href="<c:url value='/dislike-post-${post.postid}' />"><span class="glyphicon glyphicon-thumbs-down"></span></a></li>
											</ul>
										</div>
										<div class="collapse" id="collapseReply${itr.index +1}" style="height: 60px; padding: 5px 10px;">
											<form:form class="form-inline" role="form" method="POST" action="${urlreply}-${post.postid}" modelAttribute="reply">
												<div class="form-group has-error">
													<form:errors path="*" class="alert alert-danger" />
												</div>
												<div class="form-group">
													<form:input class="form-control" id="reply-body" name="reply-body" path="body" placeholder="Reply" required="required" />
													<button type="submit" class="btn btn-primary">
														<span class="glyphicon glyphicon-send"></span>
													</button>
												</div>
												<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
											</form:form>
										</div>
										<c:if test="${post.replies > 0}">
											<!-- Replies Panel HTML -->
											<div class="panel panel-success">
												<div class="panel-heading">
													<a data-toggle="collapse" href="#collapse${itr.index +1}" id="replybtn" href="#">${post.replies} Replies</a>
												</div>
												<div id="collapse${itr.index +1}" class="panel-collapse collapse">
													<div class="panel-body">
														<c:forEach items="${replies}" var="postrepl">
															<c:if test="${postrepl.post.postid == post.postid}">
																<div class="list-group">
																	<p class="list-group-item"><em>${postrepl.body}</em></p>
																	<fmt:formatDate value="${postrepl.date}" var="replydateString" pattern="dd-MMM-yyyy hh:mm:ss" />
																	<p class="list-group-item">Posted on <c:out value="${replydateString}" /> <em>by</em> <mark>
																			<c:out value="${postrepl.user.firstname}" />
																		</mark> <sec:authorize access="hasRole('USER')">
																			<c:if test="${postrepl.user.userid == user.userid}">
																				<a href="<c:url value='/delete-reply-${postrepl.replyid}' />" class="pull-right"><span class="glyphicon glyphicon-trash"></span></a>
																			</c:if>
																		</sec:authorize> <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
																			<a href="<c:url value='/delete-reply-${postrepl.replyid}' />" class="pull-right"><span class="glyphicon glyphicon-trash"></span></a>
																		</sec:authorize>
																	</p>
																</div>
															</c:if>
														</c:forEach>
													</div>
												</div>
											</div>
										</c:if>
									</c:forEach>
								</div>
								<tag:fivepaginate max="5" offset="${offset}" count="${count}" uri="${urlblogs}" next="&raquo;" previous="&laquo;" />
							</c:if>
							<c:if test="${empty posts}">
								<h4 class="text-warning">
									<strong>Currently there are no posts to display.</strong>
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