<?php 
	
	require 'database.php';

	class DataCalificacion{

		function DataCalificacion(){}

		  public static function insertarCalificacion($calificacion){
		  	$id=0;
	     	//$puntuacion= (float)  $calificacion->getCalificacion();
	     	//$idservicio = (int)   $calificacion->getIdServicio();

	        $comando = "INSERT INTO tbcalificacion( " .
	            " comentario," .
	            " puntuacion," .
	            " fecha," .
	            " idservicio,".
	            " cedulausuario)".
	            " VALUES( ?,?,?,?,?)";

	        $sentencia = database::getInstance()->getDb()->prepare($comando);

	        return $sentencia->execute(
	            array(
	                $calificacion->getComentario(),

	     			$calificacion->getPuntuacion(),
	                $calificacion->getFecha(),

	                $calificacion->getIdServicio(),
	                $calificacion->getCedulaUsuario()
	            )
	        );
    	}

    	public static function obtenerCalificaciones($cedula,$id){

			$consulta ="SELECT comentario, puntuacion, fecha FROM tbcalificacion  WHERE cedulausuario=? AND idservicio=?";

			try{
            	$comando = database::getInstance()->getDb()->prepare($consulta);
            	$comando->execute(array($cedula,$id));
            	$row = $comando->fetch(PDO::FETCH_ASSOC);
            	return $row;
			}catch (PDOException $e) {
            	return "Fallo la consulta";
        	}

		}

	    public static function eliminarUsuario($cedula){
	    
	        $comando = "DELETE FROM tbusuario WHERE cedula=?";

	        $sentencia = database::getInstance()->getDb()->prepare($comando);

	        return $sentencia->execute(array($cedula));
	    }

	

     }

?>