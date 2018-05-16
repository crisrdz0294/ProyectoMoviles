<?php 
	
	require_once 'mysql_login.php';


class Database
{

 
    private static $db = null;

    private static $pdo;

    final private function __construct()
    {
        try {
      
            self::getDb();
        } catch (PDOException $e) {
        }


    }

    /**
     * Retorna en la única instancia de la clase
     * @return Database|null
     */
    public static function getInstance()
    {
        if (self::$db === null) {
            self::$db = new self();
        }
        return self::$db;
    }

    /**
     * Crear una nueva conexión PDO basada
     * en los datos de conexión
     * @return PDO Objeto PDO
     */
    public function getDb()
    {
        if (self::$pdo == null) {
            self::$pdo = new PDO(
                'mysql:dbname=' . DATABASE .
                ';host=' . HOSTNAME .
                ';port:80;',
                USERNAME,
                PASSWORD,
                array(PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8")
            );

            // Habilitar excepciones
            self::$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        }

        return self::$pdo;
    }

    /**
     * Evita la clonación del objeto
     */
    final protected function __clone()
    {
    }

    function _destructor()
    {
        self::$pdo = null;
    }
}


?>