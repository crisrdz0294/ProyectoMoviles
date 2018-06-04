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

		public static function obtenerUsuarioCredenciales($correo,$clave){

			$consulta ="SELECT * FROM tbusuario WHERE correo=? AND clave=?";

			try{
            	$comando = database::getInstance()->getDb()->prepare($consulta);
            	$comando->execute(array($correo,$clave));
            	$row = $comando->fetch(PDO::FETCH_ASSOC);
            	return $row;
			}catch (PDOException $e) {
            	return "Fallo la consulta";
        	}

		}

		public static function actualizarUsuario($usuario){

				$tipo=0;
				
		        $consulta = "UPDATE tbusuario" .
		            " SET nombre=?, apellidos=?, correo=?, tipo=?,telefono=?, clave=?" .
		            "WHERE cedula=?";

		         switch ($usuario->getTipo()) {
		         	
		        	case 'Arrendador':
		        		$tipo=2;
		        		break;

		        	case 'Arrendatario':
		        		$tipo=3;
		        		break;
		        	
		        	default:
		        		
		        		break;
	        	}

		        $cmd = database::getInstance()->getDb()->prepare($consulta);

		        // Relacionar y ejecutar la sentencia
		        $cmd->execute(array($usuario->getNombre(),$usuario->getApellidos(),$usuario->getCorreo(),$tipo,$usuario->getTelefono(),$usuario->getClave(),$usuario->getCedula()));

		        return $cmd;
		}

		  public static function insertarUsuario($usuario){
		  	$tipo=0;
	     
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

	        switch ($usuario->getTipo()) {
	        	case 'Arrendador':
	        		$tipo=2;
	        		break;

	        	case 'Arrendatario':
	        		$tipo=3;
	        		break;
	        	
	        	default:
	        		
	        		break;
	        }

	        return $sentencia->execute(
	            array(
	                $usuario->getCedula(),
	                $usuario->getNombre(),
	                $usuario->getApellidos(),
	                $usuario->getCorreo(),
	                $usuario->getTelefono(),
	                $usuario->getClave(),
	                $tipo
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