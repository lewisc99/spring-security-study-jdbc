<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<title>Luv2code Company Home page</title>


<!-- Reference Bootstrap files -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xl-10">
				<h2>Luv2code Company Home page -- Yooho -</h2>
				<hr>
				<p>Welcome to the luv2code company Home page!</p>

				<hr>
				<!--  Display user name and role -->
				<p>
					User:
					<security:authentication property="principal.username" />
					<br> <br> Role(s):
					<security:authentication property="principal.authorities" />
				</p>
				<hr>


				<!--  Add a link to point to /leaders ... this is for the managers -->
				<security:authorize access="hasRole('MANAGER')">
					<p>
						<a href="${pageContext.request.contextPath }/leaders">LeaderShip
							Meeting</a> (Only for Manager peeps)
					</p>
				</security:authorize>


				<!--  Add a link to point to /systems ... this is for the admins -->
				<security:authorize access="hasRole('ADMIN')">
					<p>
						<a href="${pageContext.request.contextPath }/systems">IT
							Systems Meeting</a> (Only for ADMIN peeps)
					</p>
				</security:authorize>

				<hr>
				<!--  Add a logout button -->
				<form:form action="${pageContext.request.contextPath }/logout"
					method="POST">

					<input type="submit" value="Logout" class="btn btn-primary" />

				</form:form>
			</div>
		</div>
	</div>
</body>
</html>