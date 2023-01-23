<html>
<head>
	<title>HomePage</title>
	<link rel="stylesheet" href="index.css">
</head>
<body>
	<form action="reg" method="post">
	<fieldset>
	  <legend>Employee Registration</legend>
		<input type="text" name="nm" placeholder="Enter Employee Name" required/> <br><br>
		<textarea rows='10' cols='30' name='ad' placeholder='Enter Employee Address' required></textarea> <br><br>
		<label for="gen">Enter Your Gender :-</label>
		<select name='gen'>
			<option>Male</option>
			<option>Female</option>
		</select> <br><br>
		<input type='text' name='sal' placeholder='Enter Your Salary' required/> <br><br>
		<label for="gen">Enter Date Of Birth :-</label>
		<input type='date' name='dob' required/> <br><br>
		<input type='submit' value='Register'/>
		</fieldset>	
	</form>
	<a href='emp'>Click Here to view all the employees </a>
</body>
</html>
