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
<title>Coorg-USA:About Coorg USA</title>
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
<spring:url value="/photos" var="urlphotos" />
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
								<h2>Phoenix Coorg</h2>
								<p>Want to know the interesting things about the Coorg community in Phoenix valley ?</p>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="pull-right"></div>
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
							<li data-target="#carousel-example-generic" data-slide-to="21"></li>
							<li data-target="#carousel-example-generic" data-slide-to="22"></li>
							<li data-target="#carousel-example-generic" data-slide-to="23"></li>
							<li data-target="#carousel-example-generic" data-slide-to="24"></li>
						</ol>
						<!-- Wrapper for slides -->
						<div class="carousel-inner" role="listbox">
							<div class="item active">
								<img src="${images}/abtphxcoorg1.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtphxcoorg2.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtphxcoorg3.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtphxcoorg4.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtphxcoorg5.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtphxcoorg6.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtphxcoorg7.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtphxcoorg8.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtphxcoorg9.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtphxcoorg10.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtphxcoorg11.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtphxcoorg12.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtphxcoorg13.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtphxcoorg14.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtphxcoorg15.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtphxcoorg16.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtphxcoorg17.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtphxcoorg18.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtphxcoorg19.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtphxcoorg20.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtphxcoorg21.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtphxcoorg22.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtphxcoorg23.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtphxcoorg24.jpg" class="img-responsive" alt="Responsive image" />
								<div class="carousel-caption">...</div>
							</div>
							<div class="item">
								<img src="${images}/abtphxcoorg25.jpg" class="img-responsive" alt="Responsive image" />
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
								<p>Coorgies in USA.</p>
								<p>Phoenix is a city in Arizona.</p>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<h3>Phoenix Coorg- Hockey</h3>
							<img src="${images}/phxcoorghockey.jpg" class="img-responsive" alt="Responsive image" />
							<p class="lead">'TEAM COORG' RISES IN THE US!</p>
							<div class="col-md-4">
								<img src="${images}/IndianExpress5THFEB2011.jpg" class="img-responsive" alt="Responsive image" />
								<div class="caption text-center">
									<p>Indian Express 05-FEB-2011</p>
								</div>
							</div>
							<div class="col-md-8">
								<p>DEEPTHI AIYANNA writes: The thing that matters the most to a Kodava, is preserving the identity of being a Kodava. The simple traditions of ancestral worship, respect for the
									motherland, love for nature, physical might and natural inclination of being a sportsperson sets us apart. It is this undercurrent that unifies all of us.</p>
								<p>With time, a lot of the Kodavas, have moved away from the land and settled abroad. Much has been said, done and written about the dilution of culture due to external influences. There
									was a small acid test to this that I witnessed recently.</p>
							</div>
							<p>It is the marvelous result that I saw that made me happy and encouraged me to share this first hand account with the Kodavas in and outside Kodagu.It all began with the sport of hockey, which is better known as 'Field Hockey' in USA specifically to set it aside from the more popular 'Ice Hockey'. A small group of Kodavas based in
								Phoenix, Arizona, with a special place for this sport in their heart, had a dream of putting together an all Kodava hockey team, to create an identity for the Kodavas in America. With much
								effort, sponsorship and enthusiasm from Kodavas in USA, 'Team Coorg' was formed in 2010.</p>
							<p>'Team Coorg' successfully debuted in their unique gold jersey in the 6th Annual Phoenix Cactus Classic Hockey Tournament organized by the Phoenix Scorpions. I had the opportunity of
								accompanying the team this year in their red jerseys when they represented 'Team Coorg' for the second time at the tournament. This year the team participated in the social division which
								required three female members to be on the field at all times.</p>
							<p>We, the friends and family of 'Team Coorg' showed up at the venue with just our enthusiasm and without much expectation. 'Team Coorg' took the ground against the club 'California
								Surfers'. Surfers drew first blood with 1-0 lead. 'Team Coorg' equalized shortly after closing half-time at 1-1. The match then closed with a 2-2 draw. For 'Team Coorg', this came as a very
								huge morale boost. What better way could a Kodava celebrate? We played the 'Vaalaga' on a boom-box and danced the traditional 'Kodavaat'!</p>
							<p>Why would I call this marvelous and that this performance calls for a celebration? For one, the members of 'Team Coorg' met for the first time on the day before the tournament and
								practiced for little less than two hours together. Some of the team members couldn't reach on time for the practice session as they were still commuting from different coasts of US. This meant
								they dint quite know the strengths and weaknesses of their teammates. It was blind faith in a Kodava's ability to play hockey. Mind you, the teams that they played against are university teams
								and clubs that are collocated and play hockey professionally all year round. Also, our team featured an under-15 Kodavathi!</p>
							<p>What set the Kodavas apart that they were in such a great position to frustrate the other teams and hold them to such low scores? It was the instant bond that the Kodavas formed with
								'Kodava Thak' and unique ambience created by the sweet sound of 'vaalaga' that reminded the players at all times that it was for Kodagu and to keep the spirit of Kodava kindled in their
								hearts. Far away from motherland, Kaveramme still beckons her children of Kodagu. No external influence could dilute this love of the land, culture and sport.</p>
							<p>Team Coorg, USA, has a vision that 'Team Coorg' will one day represent itself in all hockey tournaments across USA. For this to happen, we would like to reach out to more Kodavas out
								there who are willing to pick up the stick.</p>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<p><em>Please visit the photos gallery for more images from the hockey event !!! </em><a class="btn btn-primary btn-xs" href="${urlphotos}" role="button">Take Me There</a></p>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<h3>Prominent Personalities</h3>
						</div>
						<figure class="figure col-md-4">
							<img src="${images}/Prof_Appachu.jpg" class="figure-img img-fluid img-rounded" alt="Ballachanda Appachu">
							<figcaption class="figure-caption text-center">Ballachanda Appachu</figcaption>
						</figure>
						<figure class="figure col-md-4">
							<img src="${images}/Prof_Madhu.jpg" class="figure-img img-fluid img-rounded" alt="Muruvanda Madhu">
							<figcaption class="figure-caption text-center">Muruvanda Madhu</figcaption>
						</figure>
						<figure class="figure col-md-4">
							<img src="${images}/Prof_Madappa.jpg" class="figure-img img-fluid img-rounded" alt="Kundranda Harish">
							<figcaption class="figure-caption text-center">Kundranda Harish</figcaption>
						</figure>
						<div class="col-md-4">
							<p><mark>Ballachanda Appachu</mark> Moved to the valley in 2010. Has been a prominent figure in the coorgie community here and organized many of the key events.</p>
						</div>
						<div class="col-md-4">
							<p><mark>Muruvanda Madhu</mark> Moved to the valley in 2010. Has been a prominent figure in the coorgie community here and organized many of the key events.</p>
						</div>
						<div class="col-md-4">
							<p><mark>Kundranda Harish</mark> Moved to the valley in 2010. Has been a prominent figure in the coorgie community here and organized many of the key events.</p>
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