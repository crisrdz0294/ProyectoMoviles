<?php 

 
require '../data/dataCalificacion.php';
include '../dominio/Calificacion.php';


 if($_SERVER['REQUEST_METHOD'] == 'GET'){

 	if(isset($_GET["accion"])){
 		switch ($_GET["accion"]) {
 			case 'obtenerCalificaciones':
	 			if(isset($_GET["cedula"]) && isset($_GET["id"])){
	 				
		 			$calificacion = dataCalificacion::obtenerCalificaciones($_GET["cedula"],$_GET["id"]);

		                if ($calificacion) {

		                    $datos["estado"] = 1;
		                    $datos["calificaciones"] = $calificacion;

		                    print json_encode($datos);
		                } else {
		                    print json_encode(array(
		                        "estado" => 2,
		                        "mensaje" => "Ha ocurrido un error"
		                    ));
		                }
	            }
 				break;
 			
 			default:
 				 print_r("Fallo en la accion");
 				break;
 		}
 	}

 }else if($_SERVER['REQUEST_METHOD'] == 'POST'){


    if(isset($_GET["accion"])){

        switch ($_GET["accion"]) {

            case 'insertarCalificacion':
                  
                 $calificacionJson = json_decode(file_get_contents("php://input"), true);

                 $calificacion = new Calificacion();
                 
                 $calificacion->setComentario($calificacionJson['comentario']);
                 $calificacion->setPuntuacion($calificacionJson['puntuacion']);
                 $calificacion->setFecha($calificacionJson['fecha']);
                 $calificacion->setIdServicio($calificacionJson['servicio']);
                 $calificacion->setCedulaUsuario($calificacionJson['cedula']);

                $resultado=dataCalificacion::insertarCalificacion($calificacion);

                if ($resultado) {
       
                    print json_encode(
                        array(
                            'estado' => '1',
                            'mensaje' => 'Se califico correctamente')
                    );
                } else {
    
                    print json_encode(
                        array(
                            'estado' => '2',
                            'mensaje' => 'No se califico')
                    );
                }
            break;
          
            default:
                # code...
                break;
        }
    }

}


?>