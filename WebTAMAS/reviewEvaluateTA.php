<!DOCTYPE html>
<html>
	<head >
		<meta charset="UTF-8">
		<title>McGill TAMAS</title>
		<link rel="stylesheet" type="text/css" href="login.css">

	
	</head>
<body>

	
	
	<div class="mainpage">
		<div class="mainpage_form">
		
				<p class = "logo"> McGill TAMAS - Instructor </p>
				<hr>
					
				<p class = "logo">
			
					<?php 
					require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/model/Applicant.php';
					require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/model/Application.php';
					require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/model/Assignment.php';
					require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/model/Course.php';
					require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/model/Department.php';
					require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/model/GraderJob.php';
					require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/model/Instructor.php';
					require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/model/Job.php';
					require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/model/ResourceManager.php';
					require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/model/TAjob.php';
					require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/model/User.php';
					require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/controller/InputValidator.php';
					require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/persistence/Persistence.php';
					require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/controller/Controller.php';
				
				session_start();
				
				$ps = new Persistence();
				$rm = $ps->loadDataFromStore();
				$c = new Controller();
				
				$name = $rm->getInstructor_index($_SESSION["index"])->getName();

	
				echo "Hi Professor " . $name . "<br>";
				
				for($i = 0; $i < $rm->numberOfAssignments(); $i++){
					echo $i+1 . ". " . $rm->getAssignment_index($i)->getApplicant()->getName() . "<br>";
					echo "Feedback: " . $rm->getAssignment_index($i)->getFeedback() . "<br>" . "<br>";
				}
		
				?>
							

				
				<hr>
				
				<form class = "logout" action="http://localhost/Group12/WebTAMAS/mainpage.php" >
				<button type="submit">Main Page</button>
				</form>			
				
				</div>
				</div>
				
			</body>
			
	</html>
				
				
				
				
				
				
				
				
				
				
				
				
				
				