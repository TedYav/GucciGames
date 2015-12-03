<?
abstract class Processor{

    protected $playername;
    protected $gamename;
    protected $name;
    protected $id;
    protected $title;
    protected $score;
    protected $time;

    protected $newEntry = "~\n";

    private $database;

    function __construct($request, $database){
        $this->loadvars($request);
        $this->database = $database;
    }

    function loadvars($request){
        $this->name = $this->loadvar($request, "name");
        $this->gamename = $this->loadvar($request, "gamename");
        $this->playername = $this->loadvar($request, "playername");
        $this->id = $this->loadvar($request, "id");
        $this->title = $this->loadvar($request, "title");
        $this->score = $this->loadvar($request, "score");
        $this->time = strftime("%r %Y-%m-%d");
    }

    private function loadvar($request, $name){
        if(isset($request[$name])){
            $target = $request[$name];
        }
        else{
            $target = "";
        }
        return addslashes($target);
    }

    function database_request($request){
        $result = $this->database->query($request);
             
        if($result === false) {

         echo('Wrong SQL: ' . $request . ' Error: ' . $this->database->error);
         die();
        }       
        else {
        return $result;
        }

    }

    abstract function add();

}
?>