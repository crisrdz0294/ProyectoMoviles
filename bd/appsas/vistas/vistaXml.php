<?php 
	require_once "vista.php";

	class VistaXml extends Vista{

		public function renderizar($cuerpo){
        
        	if (isset($cuerpo["estado"])) {
            	http_response_code($body["estado"]);
        	}

     
	        header('Content-Type: text/xml; charset=utf-8');

	        $xml = new SimpleXMLElement('<apiRespuesta/>');
	        self::convertirArregloXml($cuerpo, $xml);
	        print $xml->asXML();

	        exit;
    	}

    	public function convertirArregloXml($data,&$xml){

    		foreach ($data as $key => $value) {

            	if (is_array($value)) {
                	if (is_numeric($key)) {
                    	$key = 'item' . $key;
                	}
                	$subnode = $xml->addChild($key);
                	self::convertirArregloXml($value, $subnode);
            	} else {
                	$xml->addChild("$key", htmlspecialchars("$value"));
            	}
        	}

    	}
	}
?>