<?

	include 'database/VOOGADatabase.php';

	$database = VOOGADatabase::connect();

	echo "<h1>VOOGASalad Leaderboard</h1>\n\n";

	$request = "SELECT * FROM engines ORDER BY id ASC";
	$result = $database->query($request);
	VOOGADatabase::verify($result, $request, $database);

	while($team = $result->fetch_assoc()){
		outputTeam($team, $database);
	}


	function outputTeam($team, $database){
		echo("<h2>".$team['NAME']."</h2>\n\n");
		$request = "SELECT * FROM highscores WHERE engineid=" . $team['ID'] . " ORDER BY gamename asc, score desc LIMIT 10";
		$result = $database->query($request);
		VOOGADatabase::verify($result, $request, $database);
		outputGames($result);
	}

	function outputGames($games){
		$currentGame = "";
		$i = 1;
		while($row = $games->fetch_assoc()){
			if($currentGame !== $row['GAMENAME']){
				if($currentGame !== ""){
					echo "</ol>";
				}
				$currentGame = $row['GAMENAME'];
				echo "<h3>$currentGame</h3>\n\n";
				echo "<ol>";
			}
			echo "<li><span class=\"playername\">" . $row['PLAYERNAME'] . "</span>  "
					. "<span class=\"playerscore\">" . $row['SCORE'] . "</span>   "
					. "<span class=\"playertitle\">" . $row['TITLE'];
		}
		echo "</ol>";
	}
?>