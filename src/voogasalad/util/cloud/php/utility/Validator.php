<?php

	class Validator{

		public static function checkPost($variable){
			if(!isset($_POST[$variable])){
				die("ERROR: $variable not set in POST data. Check your VoogaCloud client configuration, or contact Ted to tell him there's a bug.");
			}
		}

	}

?>