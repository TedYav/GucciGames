<?php
	
	include 'classes/processor.php';
	include 'database/VOOGADatabase.php';
	include 'classes/register.php';
	include 'classes/highscore.php';

	if(!isset($_GET['action'])||!isset($_GET['process']))
	{
		die("ERROR: no action specified.");
	}

	$className = ucfirst($_GET['action']) . "Action";
	$methodName = $_GET['process'];
	$database = VOOGADatabase::connect();

	$processor = new $className($_GET, $database);
	$processor->$methodName();
?>