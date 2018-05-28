<?php 
	
	class ApiExcepcion extends Exception{

		private $estado;
    	private $codigoApi;
    	private $mensajeUsuario;
    	private $masInformacion;
    	private $mensajeDesarrollador;

    	public function __construct($estado,$codigoApi,$mensajeUsuario,$masInformacion,
    		$mensajeDesarrollador){

    		$this->estado=$estado;
    		$this->codigoApi=$codigoApi;
    		$this->mensajeUsuario=$mensajeUsuario;
    		$this->masInformacion=$masInformacion;
    		$this->mensajeDesarrollador=$mensajeDesarrollador;

    	}

    	public function getEstado(){
    		return $this->estado;
    	}

    	public function getCodigoApi(){
    		return $this->codigoApi;
    	}

    	public function getMensajeUsuario(){
    		return $this->mensajeUsuario;
    	}

    	public function getMasInformacion(){
    		return $this->masInformacion;
    	}

    	public function getMensajeDesarrollador(){
    		return $this->mensajeDesarrollador;
    	}

    	public function toArray(){

    		$cuerpoError=array(
    			"estado" =>$this->estado,
    			"codigoApi" => $this->codigoApi,
    			"mensajeUsuario" => $this->mensajeUsuario,
    			"masInformacion" => $this->masInformacion,
    			"mensajeDesarrollador" => $this->mensajeDesarrollador
    		);
    		return $cuerpoError;
    	}
	}

?>