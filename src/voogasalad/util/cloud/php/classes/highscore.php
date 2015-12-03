<?php

	class HighscoreAction extends Processor{

		function add(){
			if(empty($this->title)){
				$this->title = $this->time;
			}
			$this->database_request("INSERT into highscores(engineid, gamename, playername, title, score) VALUES( '$this->id' , '$this->gamename','$this->playername', '$this->title', '$this->score')");
		}

		function retrieve(){
			$result = $this->database_request("SELECT * FROM highscores WHERE engineid='$this->id' AND gamename='$this->gamename' ORDER BY SCORE DESC");
			while($row = $result->fetch_assoc()){
				echo $this->newEntry;
				echo("gamename=".$row['GAMENAME']."\n");
				echo("playername=".$row['PLAYERNAME']. "\n");
				echo("title=".$row['TITLE'] . "\n");
				echo("score=".$row['SCORE']."\n");
			}
		}
	}

?>