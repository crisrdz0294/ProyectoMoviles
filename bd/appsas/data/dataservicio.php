<?php

	require 'database.php';

	class DataServicio{

		public function DataServicio(){}

      public static function insertarServicio($Servicio){
        $tipo=0;

          $comando = "INSERT INTO tbservicio( ".
              "nombreservicio,".
              " descripcionservicio,".
              " ubicacionservicio,".
              " estadoservicio,".
              " precioservicio,".
              " tiposervicio,".
              " cedulausuario)".
              " VALUES(?,?,?,?,?,?,?)";

          $sentencia = database::getInstance()->getDb()->prepare($comando);

           $estado= $sentencia->execute(
              array(
                  $Servicio->getNombre(),
                  $Servicio->getDescripcion(),
                  $Servicio->getUbicacion(),
                  $Servicio->getEstado(),
                  $Servicio->getPrecio(),
                  $Servicio->getTipo(),
                  $Servicio->getCedula()

              )
          );
if($estado){

                $recuperarID="SELECT MAX(idservicio) AS idservicio FROM tbservicio";

              $sentencia2 = database::getInstance()->getDb()->prepare($recuperarID);

							$sentencia2->execute();
							$row = $sentencia2->fetch(PDO::FETCH_ASSOC);
							$idSiguiente=0;









$idSiguiente=$row['idservicio'];

if($row){


						          $comando2 = "INSERT INTO tbimagen(".
						              "imagen,".
						              " idservicio)".
						              " VALUES(?,?)";


													$sentencia3 = database::getInstance()->getDb()->prepare($comando2);


								 $estado= $sentencia3->execute(
										array(
												$Servicio->getPriImagen(),
											   $idSiguiente

										)
								);

								$estado= $sentencia3->execute(
									 array(
											 $Servicio->getSegunImage(),
												$idSiguiente

									 )
							 );
							 $estado= $sentencia3->execute(
					 			 array(
					 					 $Servicio->getterImage(),
					 						$idSiguiente

					 			 )
					 	 );

              return $estado;



}else{
		return false;
}




}else{
	return false;
}







				}

    public static function verServicios(){

								$consulta="SELECT * FROM tbservicio";

								try{

									 // Preparar sentencia
					            $comando = database::getInstance()->getDb()->prepare($consulta);
					            // Ejecutar sentencia preparada
					            $comando->execute();




          $arregloServicios=$comando->fetchAll(PDO::FETCH_ASSOC);


							 for($i=0;$i<count($arregloServicios);$i++) {

                    $idBusqueda=$arregloServicios[$i]["idservicio"];

								 	$consulta="SELECT * FROM tbimagen where idservicio=$idBusqueda ";
                  $comando = database::getInstance()->getDb()->prepare($consulta);
									  $comando->execute();
										  $arregloimagenes=$comando->fetchAll(PDO::FETCH_ASSOC);

											$arregloServicios[$i]["imagen1"]=$arregloimagenes[0]["imagen"];
												$arregloServicios[$i]["imagen2"]=$arregloimagenes[1]["imagen"];
													$arregloServicios[$i]["imagen3"]=$arregloimagenes[2]["imagen"];




							 }
							 return $arregloServicios;
								}catch(PDOException $e){
									return "Fallo la conexion";
								}
				}

				public static function desactivarServicio($id){

					$consulta = "UPDATE tbservicio" .
							" SET estadoservicio=0 ".
							"WHERE idservicio=?";



					$cmd = database::getInstance()->getDb()->prepare($consulta);

					// Relacionar y ejecutar la sentencia
					$cmd->execute(array($id));

					return $cmd;

				}


				public static function actualizarServicio($servicio){

					$consulta = "UPDATE tbservicio" .
							" SET nombreservicio=?, descripcionservicio=?, ubicacionservicio=?, estadoservicio=?,precioservicio=?, tiposervicio=?" .
							"WHERE idservicio=?";



					$cmd = database::getInstance()->getDb()->prepare($consulta);

					// Relacionar y ejecutar la sentencia
					$result=$cmd->execute(array($servicio->getNombre(),$servicio->getDescripcion(),$servicio->getUbicacion(),$servicio->getEstado(),$servicio->getPrecio(),$servicio->getTipo(),$servicio->getId()));

            $id=$servicio->getId();
					if($result){
						 	$consulta="SELECT idimagen FROM tbimagen where idservicio=$id";
								$cmd = database::getInstance()->getDb()->prepare($consulta);
                	$result=$cmd->execute();
									  $image=$cmd->fetchAll(PDO::FETCH_ASSOC);

										if($image){


											$idU=$image[0]["idimagen"];
											$idD=$image[1]["idimagen"];
												$id3=$image[2]["idimagen"];


										$consulta = "UPDATE tbimagen" .
												" SET imagen=?".
												"WHERE idimagen=$idU";



										$cmd = database::getInstance()->getDb()->prepare($consulta);

										// Relacionar y ejecutar la sentencia
										$result=$cmd->execute(array($servicio->getPriImagen()));
										$consulta = "UPDATE tbimagen" .
												" SET imagen=?".
												"WHERE idimagen=$idD";



										$cmd = database::getInstance()->getDb()->prepare($consulta);

										// Relacionar y ejecutar la sentencia
										$result=$cmd->execute(array($servicio->getSegunImage()));

										$consulta = "UPDATE tbimagen" .
												" SET imagen=?".
												"WHERE idimagen=$id3";



										$cmd = database::getInstance()->getDb()->prepare($consulta);

										// Relacionar y ejecutar la sentencia
										$result=$cmd->execute(array($servicio->getterImage()));

if($result){
	return true;
}else{
	return false;
}


}else{
	return false;
}


					}else{
						return false;
					}

				}
      }
