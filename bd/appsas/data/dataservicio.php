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
      }
