<?php 
	
	require_once ('mysql_login.php');


class Conexion{

 
        /**
     * Única instancia de la clase
     */
    private static $conexion = null;

    /**
     * Instancia de PDO
     */
    private static $pdo;

    final private function __construct() {
        try {
            // Crear nueva conexión PDO
            self::getDb();
        } catch (PDOException $e) {
            // Manejo de excepciones
            throw new ApiExcepcion(
                500,
                0,
                "Error de conexión a base de datos",
                "http://localhost",
                "La conexión al usuario administrador de MySQL se vío afectada. Detalles: " . $e->getMessage());
        }
    }

    public static function get() {
        if (self::$conexion === null) {
            self::$conexion = new self();
        }
        return self::$conexion;
    }


    public function getDb() {
        if (self::$pdo == null) {

            // Parámetros de PDO
            $dsn = sprintf('mysql:dbname=%s; host=%s', DATABASE, HOSTNAME);
            $username = USERNAME;
            $passwd = PASSWORD;
            $options = array(
                PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8",
                PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION);

            self::$pdo = new PDO($dsn, $username, $passwd, $options);
        }

        return self::$pdo;
    }

    final protected function __clone() {
    }

    function _destructor() {
        self::$pdo = null;
    }

 }
?>