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
<title>Coorg-USA:About Coorg</title>
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
								<h2>About Coorg</h2>
								<p>Want to know the interesting facts about Coorg ?</p>
							</div>
						</div>
					</div>
					<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
						<!-- Indicators -->
						<ol class="carousel-indicators">
							<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
							<li data-target="#carousel-example-generic" data-slide-to="1"></li>
							<li data-target="#carousel-example-generic" data-slide-to="2"></li>
							<li data-target="#carousel-example-generic" data-slide-to="3"></li>
							<li data-target="#carousel-example-generic" data-slide-to="4"></li>
							<li data-target="#carousel-example-generic" data-slide-to="5"></li>
							<li data-target="#carousel-example-generic" data-slide-to="6"></li>
							<li data-target="#carousel-example-generic" data-slide-to="7"></li>
							<li data-target="#carousel-example-generic" data-slide-to="8"></li>
							<li data-target="#carousel-example-generic" data-slide-to="9"></li>
							<li data-target="#carousel-example-generic" data-slide-to="10"></li>
							<li data-target="#carousel-example-generic" data-slide-to="11"></li>
							<li data-target="#carousel-example-generic" data-slide-to="12"></li>
							<li data-target="#carousel-example-generic" data-slide-to="13"></li>
							<li data-target="#carousel-example-generic" data-slide-to="14"></li>
							<li data-target="#carousel-example-generic" data-slide-to="15"></li>
							<li data-target="#carousel-example-generic" data-slide-to="16"></li>
							<li data-target="#carousel-example-generic" data-slide-to="17"></li>
							<li data-target="#carousel-example-generic" data-slide-to="18"></li>
							<li data-target="#carousel-example-generic" data-slide-to="19"></li>
							<li data-target="#carousel-example-generic" data-slide-to="20"></li>
						</ol>
						<!-- Wrapper for slides -->
						<div class="carousel-inner" role="listbox">
							<div class="item active">
								<img src="${images}/abtcoorg1.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtcoorg2.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtcoorg3.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtcoorg4.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtcoorg5.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtcoorg6.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtcoorg7.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtcoorg8.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtcoorg9.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtcoorg10.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtcoorg11.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtcoorg12.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtcoorg13.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtcoorg14.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtcoorg15.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtcoorg16.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtcoorg17.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtcoorg18.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtcoorg19.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtcoorg20.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtcoorg21.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
						</div>
						<!-- Controls -->
						<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev"> <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span
							class="sr-only">Previous</span></a> <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next"> <span class="glyphicon glyphicon-chevron-right"
							aria-hidden="true"></span> <span class="sr-only">Next</span></a>
					</div>
					<br />
					<div class="row">
						<div class="col-md-12">
							<div class="well">
								<p>Coorg(Also known as Kodagu) is a small district located in the heart of Western Ghats.efore 1956 it was an administratively separate Coorg State, at which point it was merged into an
									enlarged Mysore State. It occupies an area of 4,102 square kilometres (1,584 sq mi) in the Western Ghats of southwestern Karnataka.</p>
								<p>The district is bordered by Dakshina Kannada district to the northwest, Kasargod district of Kerala to the west, Hassan district to the north, Mysore district to the east, Kannur
									district of Kerala to the southwest, and the Wayanad district of Kerala to the south. Agriculture is the most important factor that upholds the economy of Kodagu and the main crops cultivated
									in this region are rice and coffee.</p>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<h3>History</h3>
							<p>The Kodavas were the earliest agriculturists in Kodagu, having lived there for centuries. Being a warrior community as well, they carried arms during times of war and had their own
								chieftains. The Haleri dynasty, an offshoot of the Keladi Nayakas, ruled Kodagu between 1600 and 1834. Later the British ruled Kodagu from 1834, after the Coorg War, until India's independence
								in 1947. A separate state (called Coorg State) until then, in 1956 Kodagu was merged with the Mysore State (now Karnataka).</p>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<img src="${images}/coorg_saree1.jpg" class="img-responsive" alt="Responsive image" />
						</div>
						<div class="col-md-8">
							<h3>Coorg Saree</h3>
							<p>Coorg Saree</p>
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