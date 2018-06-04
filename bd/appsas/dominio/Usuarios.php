<?php 
class Usuarios {
    private $cedula;
    private $nombre;
    private $apellidos;
    private $correo;
    private $telefono;
    private $clave;
    private $tipo;
            
    function Usuarios() {

    }
    
    function getCedula() {
        return $this->cedula;
    }

    function getNombre() {
        return $this->nombre;
    }

    function getApellidos() {
        return $this->apellidos;
    }

    function getCorreo() {
        return $this->correo;
    }

    function getTelefono() {
        return $this->telefono;
    }

    function getClave() {
        return $this->clave;
    }

    function getTipo() {
        return $this->tipo;
    }


    function setCedula($cedula) {
        $this->cedula = $cedula;
    }

    function setNombre($nombre) {
        $this->nombre = $nombre;
    }

    function setApellidos($apellidos) {
        $this->apellidos = $apellidos;
    }

    function setCorreo($correo) {
        $this->correo = $correo;
    }

    function setTelefono($telefono) {
        $this->telefono = $telefono;
    }

    function setClave($clave) {
        $this->clave = $clave;
    }

    function setTipo($tipo) {
        $this->tipo = $tipo;
    }


}




?>