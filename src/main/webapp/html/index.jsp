<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Login Form</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/fixedheader/3.1.6/css/fixedHeader.bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap.min.css">
<link rel="stylesheet" href="../css/style.css" type="text/css">
<meta name="robots" content="noindex,follow" />
</head>
<body>

	<div style="position: relative; height: 75px;">
		<span style="padding: 10px; position: absolute; bottom: 0;"> 
			<img src="images/MobiLytix_logo.png" alt="MobiLytix" height="75px" /> 
		</span>
		<span style="padding: 10px; position: absolute; bottom: 0; right: 0;">
			<img src="images/comviva_logo.jpg" alt="Comviva" height="50px" />
		</span>
	</div>


	<ul style="list-style-type: none; margin: 0; padding: 0; overflow: hidden; background-color: #333;">
		<li style="float: left; color: white; padding-right: 10px; padding-left: 10px;"><h3>Reconciliation Report</h3></li>
	</ul>
	<br />
	<div class="login">
		<h1>Reconciliation</h1>
		<form method="post" action="index">
			${SPRING_SECURITY_LAST_EXCEPTION.message}
			<p>
				<input type="text" name="username" value="" placeholder="Username" required>
			</p>
			<p>
				<input type="password" name="password" value="" placeholder="Password" required>
			</p>
			<p class="submit">
				<input type="submit" name="submit" value="Login">
			</p>
		</form>
	</div>
</body>
</html>
