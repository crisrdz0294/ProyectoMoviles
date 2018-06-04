<?php 
	require_once "vista.php";

	class VistaJson extends Vista{


		public function renderizar($cuerpo){
			if(isset($cuerpo["estado"])){
				http_response_code($cuerpo["estado"]);
			}

			header('Content-Type: application/json; charset=utf8');

			$respuestaJson=json_encode($cuerpo, JSON_PRETTY_PRINT, JSON_UNESCAPED_UNICODE);

			if (json_last_error() != JSON_ERROR_NONE) {
            	$errorInternoServidor = new ApiException(
                	500,
               		 0,
                	"Error interno en el servidor. Contacte al administrador",
                	"http://localhost",
                	"Error de parsing JSON en VistaJson.php. Causa: " . json_last_error_msg());
            	throw $errorInternoServidor;
        	}

        	echo $respuestaJson;

        	exit;
		}
	}
 ?>