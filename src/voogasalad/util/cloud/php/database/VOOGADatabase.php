<?php
	class VOOGADatabase
	{
		private static $server = "mysql.evokeone.com";
		private static $username = "cs308";
		private static $password = "thisutilityisawesome!";
		private static $database = "voogasalad";

		function __construct(){
		}

		static function connect(){
			$connection = new mysqli(self::$server, self::$username, self::$password, self::$database);

			if($connection->connect_error){
				die("Unable to connect to VOOGA Database because " . $connection->connect_error);
			}

			return $connection;
		}

		static function verify($request, $database, $result){
			if($result === false) {

	         echo('Wrong SQL: ' . $request . ' Error: ' . $database->error);
	         die();
	        }       
		}

	}
?>