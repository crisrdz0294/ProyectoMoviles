<?php 
	
	require ('conexion.php');

	class DataUsuario{

		function DataUsuario(){}

		   public function insertarUsuario($usuarios){

		  	$cedula = $usuarios->getcedula();
		    $nombre = $usuarios->getnombre();
		    $apellidos = $usuarios->getApellidos();
		    $correo = $usuarios->getCorreo();
		    $telefono = $usuarios->getTelefono();
		    $clave = $usuarios->getClave();
		    $tipo = $usuarios->getTipo();		 
		  

		 
		        $pdo = Conexion::get()->getDb();

		
		        $sentencia = "INSERT INTO tbusuario (cedula, nombre, apellidos, correo, telefono, clave, tipo)" .
		            " values (?,?,?,?,?,?,?)";

		
		        $preparedStament = $pdo->prepare($sentencia);
		        $preparedStament->bindParam(1, $cedula);
		        $preparedStament->bindParam(2, $nombre);
		        $preparedStament->bindParam(3, $apellidos);
		        $preparedStament->bindParam(4, $correo);
		        $preparedStament->bindParam(5, $telefono);
		        $preparedStament->bindParam(6, $clave);
		        $preparedStament->bindParam(7, $tipo);

		        
		        return $preparedStament->execute();
		}

		public function validarUsuario($correo,$clave){
			  $pdo = Conexion::get()->getDb();

			  $consulta="SELECT * FROM tbusuario where correo=? AND clave=?";
			  $preparedStament=$pdo->prepare($consulta);

			  $preparedStament->bindParam(1, $correo);
			  $preparedStament->bindParam(2, $clave);

			  $preparedStament->execute();

			  $usuario=$preparedStament->fetch(PDO::FETCH_ASSOC);

			  if($usuario!=null){
			  	return $usuario;
			  }else{

			  	return null;
			  }
			  
			  
		}

		public function obtenerUsuarios(){

			 $pdo = Conexion::get()->getDb();

			 $consulta="SELECT * FROM tbusuario";
			 $preparedStament=$pdo->prepare($consulta);

			 $preparedStament->execute();

			 $listaUsuarios=$preparedStament->fetchAll(PDO::FETCH_ASSOC);

			 if($listaUsuarios!=null){
			  	return $listaUsuarios;
			 }else{
				return null;
			  }

		}

		public function actualizarUsuario($usuario){

			$cedula = $usuario->getcedula();
		    $nombre = $usuario->getnombre();
		    $apellidos = $usuario->getApellidos();
		    $correo = $usuario->getCorreo();
		    $telefono = $usuario->getTelefono();
		    $clave = $usuario->getClave();
		    $tipo = $usuario->getTipo();

			$pdo = Conexion::get()->getDb();

			$sentencia = "UPDATE tbusuario SET nombre=?,apellidos=?,correo=?,telefono=?,clave=?,tipo=? Where cedula=?" ;
			
			$preparedStament = $pdo->prepare($sentencia);
		    $preparedStament->bindParam(1, $nombre);
		    $preparedStament->bindParam(2, $apellidos);
		    $preparedStament->bindParam(3, $correo);
		    $preparedStament->bindParam(4, $telefono);
		    $preparedStament->bindParam(5, $clave);
		    $preparedStament->bindParam(6, $tipo);
		    $preparedStament->bindParam(7, $cedula);

		    return $preparedStament->execute();

		}

		public function eliminarUsuario($cedula){

			$pdo = Conexion::get()->getDb();

			$consulta="DELETE FROM tbusuario where cedula=?";

			$preparedStament = $pdo->prepare($consulta);

		    $preparedStament->bindParam(1, $cedula);

		    return $preparedStament->execute();
		}
	

}

?>