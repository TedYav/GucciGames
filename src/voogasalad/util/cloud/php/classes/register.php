<?php

	class RegisterAction extends Processor{

		function add(){
			$result = $this->database_request("SELECT * from engines WHERE name='$this->name'");
			if($result->num_rows > 0){
			}
			else{
				$this->database_request("INSERT into engines(name) VALUES('$this->name')");
				$result = $this->database_request("SELECT * from engines WHERE name='$this->name'");
			}
			$this->output($result);
		}

		function output($result){
			$result = $result->fetch_assoc();
			echo ($this->newEntry . "ID=" . $result['ID']);
		}
	}

?>