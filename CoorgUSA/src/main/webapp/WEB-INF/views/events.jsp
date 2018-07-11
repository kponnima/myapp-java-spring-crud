<%@ page session="true" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTenTaglib.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Coorg USA WebPage">
<meta name="author" content="Kushalappa Ponnimada">
<title>Coorg-USA:Events</title>
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
<spring:url value="/events" var="urlevents" />
<spring:url value="/sendmail" var="urlsendmail" />
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
								<h2>Events Planned</h2>
								<p>View the list of events planned. Also add any events needed.</p>
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
							<c:if test="${not empty success}">
								<div class="alert alert-success">
									<strong>${success}</strong>
								</div>
							</c:if>
							<c:if test="${not empty emailerror}">
								<div class="alert alert-danger">${emailerror}</div>
							</c:if>
							<c:if test="${not empty emailsuccess}">
								<div class="alert alert-success">${emailsuccess}</div>
							</c:if>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<!-- Button HTML (to Trigger Modal) -->
							<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#eventAddModal" data-title="Addevent">Add Event</button>
						</div>
						<!-- Add-event Modal HTML -->
						<div id="eventAddModal" class="modal fade">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
										<h4 class="modal-title">Add event</h4>
									</div>
									<div class="modal-body">
										<form:form role="form" method="POST" modelAttribute="event">
											<div class="form-group has-error">
												<form:errors path="*" class="alert alert-danger" />
											</div>
											<div class="form-group">
												<label for="event-title" class="control-label">Title</label>
												<form:input type="text" class="form-control :focus" id="event-title" path="title" placeholder="Enter title" required="required" autofocus="autofocus" />
											</div>
											<div class="form-group">
												<label for="event-date" class="control-label">Date</label>
												<form:input type="text" class="form-control" id="event-date" name="date" path="date" required="required" />
											</div>
											<div class="form-group">
												<label for="event-location" class="control-label">Location</label>
												<form:select name="location" path="location" id="event-location" class="form-control" required="required">
													<form:option value="">Select Location</form:option>
													<form:options items="${eventlocationList}" />
												</form:select>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
												<input type="submit" class="btn btn-primary" value="Save Event">
											</div>
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
											<script>
												$("#event-date").datepicker({
													changeMonth: true,
													changeYear: true,
													minDate: 'today',
													dateFormat:"yy-mm-dd"
												});
											</script>
										</form:form>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<h1>Events</h1>
							<c:if test="${!empty events}">
								<table class="table">
									<tr class="bg-info">
										<th>#</th>
										<th>Title</th>
										<th>Date</th>
										<th>Location</th>
										<sec:authorize access="hasRole('ADMIN')">
											<th width="100"></th>
										</sec:authorize>
										<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
											<th width="100"></th>
										</sec:authorize>
										<sec:authorize access="hasRole('ADMIN')">
											<th width="100"></th>
										</sec:authorize>
									</tr>
									<c:forEach items="${events}" var="event" varStatus="itr">
										<tr>
											<td><c:out value="${offset + itr.index +1}" /></td>
											<td><c:out value="${event.title}" /></td>
											<fmt:formatDate value="${event.date}" var="dateString" pattern="MM/dd/yyyy" />
											<td><c:out value="${dateString}" /></td>
											<td><c:out value="${event.location}" /></td>
											<sec:authorize access="hasRole('ADMIN')">
												<td><a href="<c:url value='/${event.id}' />" class="btn btn-primary custom-width" data-toggle="modal" data-target="#eventSendModal" data-title="Sendevent">Invite</a></td>
											</sec:authorize>
											<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
												<td><a href="<c:url value='/edit-event-${event.id}' />" class="btn btn-success custom-width">Edit</a></td>
											</sec:authorize>
											<sec:authorize access="hasRole('ADMIN')">
												<td><a href="<c:url value='/delete-event-${event.id}' />" class="btn btn-danger custom-width">Delete</a></td>
											</sec:authorize>
										</tr>
									</c:forEach>
								</table>
								<tag:tenpaginate max="10" offset="${offset}" count="${count}" uri="${urlevents}" next="&raquo;" previous="&laquo;" />
							</c:if>
							<!-- Send email invite Modal HTML -->
							<div id="eventSendModal" class="modal fade">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
											<h4 class="modal-title">Send invite</h4>
										</div>
										<div class="modal-body">
											<form role="form" method="POST" action="${urlsendmail}">
												<div class="form-group">
													<label for="event-Toemail" class="control-label">To:Email</label> <input type="email" class="form-control :focus" name="event-Toemail" id="event-Toemail"
														placeholder="To:email(comma separated list for multiple)" required="required" autofocus="autofocus" multiple />
												</div>
												<div class="form-group">
													<label for="event-CCemail" class="control-label">Cc:Email</label> <input type="text" class="form-control" name="event-CCemail" id="event-CCemail" placeholder="Cc:Email(Optional)" multiple />
												</div>
												<div class="form-group">
													<label for="event-subject" class="control-label">Subject</label> <input type="text" class="form-control :focus" name="event-subject" id="event-subject" placeholder="Enter Subject"
														required="required" />
												</div>
												<div class="form-group">
													<label for="event-Comment" class="control-label">Message</label>
													<textarea class="form-control" id="event-message" name="event-message" placeholder="Message" required="required"></textarea>
												</div>
												<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
												<div class="modal-footer">
													<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
													<input type="submit" class="btn btn-primary" value="Send Invite">
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
							<c:if test="${empty events}">
								<h4 class="text-warning">
									<strong>Currently there are no events scheduled.</strong>
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