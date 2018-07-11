<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:url value="/home" var="urlHome" />
<spring:url value="/news" var="urlNews" />
<spring:url value="/aboutcoorg" var="urlAboutCoorg" />
<spring:url value="/aboutcoorgusa" var="urlAboutCoorgusa" />
<spring:url value="/events" var="urlEvents" />
<spring:url value="/blogs" var="urlBlogs" />
<spring:url value="/search" var="urlSearch" />
<spring:url value="/albums" var="urlAlbums" />
<spring:url value="/songs" var="urlSongs" />
<spring:url value="/videos" var="urlVideos" />
<spring:url value="/contactus" var="urlContactUs" />
<nav class="navbar navbar-inverse">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="#">CoorgUSA</a>
	</div>
	<!-- Collection of nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li><a title="Home" href="${urlHome}"><span class="glyphicon glyphicon-home"></span></a></li>
			<li class="dropdown"><a title="About" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">ABOUT<span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a title="AboutCoorg" href="${urlAboutCoorg}">COORG</a></li>
					<li><a title="AboutCoorgusa" href="${urlAboutCoorgusa}">COORG USA</a></li>
				</ul></li>
			<li><a title="News" href="${urlNews}">NEWS</a></li>
			<li><a title="Events" href="${urlEvents}">EVENTS</a></li>
			<li><a title="Blogs" href="${urlBlogs}">BLOGS</a></li>
			<li class="dropdown"><a title="Store" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">STORE<span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a title="Photos" href="${urlAlbums}">PHOTOS</a></li>
					<li><a title="Songs" href="${urlSongs}">SONGS</a></li>
					<li><a title="Videos" href="${urlVideos}">VIDEOS</a></li>
				</ul></li>
		</ul>
		<form class="navbar-form navbar-left" action="<c:url value='/search'/>" method="GET">
			<div class="form-group">
				<input type="text" title="Search Profile" placeholder="Search Member Profile" class="form-control" id="searchString" name="searchString">
			</div>
			<button type="submit" class="btn btn-default">
				<span class="glyphicon glyphicon-search"></span>
			</button>
		</form>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="${urlContactUs}">CONTACT US</a></li>
		</ul>
	</div>
</nav>
<script type="text/javascript">
	$(document).ready(function() {
		$("#searchString").autocomplete({
			minLength : 2,
			source : '${pageContext. request. contextPath}/getUser'
		});
	});
</script>