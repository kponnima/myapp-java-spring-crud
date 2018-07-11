<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/home" var="urlHome" />
<spring:url value="/aboutcoorg" var="urlAboutcoorg" />
<spring:url value="/aboutcoorgusa" var="urlAboutcoorgusa" />
<spring:url value="/news" var="urlNews" />
<spring:url value="/events" var="urlEvents" />
<spring:url value="/blogs" var="urlBlogs" />
<spring:url value="/photos" var="urlPhotos" />
<spring:url value="/songs" var="urlSongs" />
<spring:url value="/videos" var="urlVideos" />
<spring:url value="/contactus" var="urlContactus" />
<aside>
	<div class="panel panel-info">
		<div class="panel-heading">Quick Links</div>
		<div class="panel-body">
			<ul class="list-group">
				<li class="list-group-item"><a title="Hockey Event" href="${urlEvents}"><small>Hockey Event</small></a></li>
				<li class="list-group-item"><a title="Recent Blogs" href="${urlBlogs}"><small>Recent Blogs</small></a></li>
			</ul>
		</div>
	</div>
</aside>