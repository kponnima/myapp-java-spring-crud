<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/aboutus" var="urlAboutUs" />
<spring:url value="/privacypolicy" var="urlPrivacyPolicy" />
<spring:url value="/termsofservice" var="urlTermsOfService" />
<div class="row">
	<div class="col-sm-12">
		<footer>
			<div class="text-center">
				<a href="${urlAboutUs}">About Us</a> &nbsp;|&nbsp;<a href="${urlPrivacyPolicy}">Privacy Policy</a> &nbsp;|&nbsp;<a href="${urlTermsOfService}">Terms Of Service</a>
			</div>
		</footer>
	</div>
</div>