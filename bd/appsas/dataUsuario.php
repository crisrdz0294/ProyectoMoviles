<?php 
	
	require 'database.php';

	class DataUsuario{

		function DataUsuario(){}

		public static function obtenerUsuarios(){

			$consulta="SELECT * FROM tbusuario";

			try{

				 // Preparar sentencia
            $comando = database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);
			}catch(PDOException $e){
				return "Fallo la conexion";
			}
		}

		public static function obtenerUsuarioPorCedula($cedula){

			$consulta ="SELECT * FROM tbusuario WHERE cedula=?";

			try{
            	$comando = database::getInstance()->getDb()->prepare($consulta);
            	$comando->execute(array($cedula));
            	$row = $comando->fetch(PDO::FETCH_ASSOC);
            	return $row;
			}catch (PDOException $e) {
            	return "Fallo la consulta";
        	}

		}

		public static function actualizarUsuario($cedula,$nombre,$apellidos,$correo,$telefono,$clave){


					   // Creando consulta UPDATE
		        $consulta = "UPDATE tbusuario" .
		            " SET cedula=?, nombre=?, apellidos=?, correo=?, telefono=?, clave=? " .
		            "WHERE cedula=?";

		        // Preparar la sentencia
		        $cmd = database::getInstance()->getDb()->prepare($consulta);

		        // Relacionar y ejecutar la sentencia
		        $cmd->execute(array($cedula,$nombre,$apellidos,$correo,$telefono,$clave));

		        return $cmd;
		}

		  public static function insertarUsuario($cedula,$nombre,$apellidos,$correo,$telefono,$clave,$tipoUsuario){
	     
	        $comando = "INSERT INTO tbusuario ( " .
	            "cedula," .
	            " nombre," .
	            " apellidos," .
	            " correo," .
	            " telefono,".
	            " clave,".
	            " tipo)" .
	            " VALUES( ?,?,?,?,?,?,?)";

	        $sentencia = database::getInstance()->getDb()->prepare($comando);

	        return $sentencia->execute(
	            array(
	                $cedula,
	                $nombre,
	                $apellidos,
	                $correo,
	                $telefono,
	                $clave,
	                $tipoUsuario
	            )
	        );
    	}

	    public static function eliminarUsuario($cedula){
	    
	        $comando = "DELETE FROM tbusuario WHERE cedula=?";

	        $sentencia = database::getInstance()->getDb()->prepare($comando);

	        return $sentencia->execute(array($cedula));
	    }

	

     }

?>