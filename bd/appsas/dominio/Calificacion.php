<?php 
class Calificacion {
    private $idCalificacion;
    private $comentario;
    private $puntuacion;
    private $fecha;
    private $idServicio;
    private $cedulaUsuario;
            
    function Calificacion() {

    }
    
    function getIdCalificacion() {
        return $this->idCalificacion;
    }

    function getComentario() {
        return $this->comentario;
    }

    function getPuntuacion() {
        return $this->puntuacion;
    }

    function getFecha() {
        return $this->fecha;
    }

    function getIdServicio() {
        return $this->idServicio;
    }

    function getCedulaUsuario() {
        return $this->cedulaUsuario;
    }


    function setIdCalificacion($idCalificacion) {
        $this->idCalificacion = $idCalificacion;
    }

    function setComentario($comentario) {
        $this->comentario = $comentario;
    }

    function setPuntuacion($puntuacion) {
        $this->puntuacion = $puntuacion;
    }

    function setFecha($fecha) {
        $this->fecha = $fecha;
    }

    function setIdServicio($idServicio) {
        $this->idServicio = $idServicio;
    }

    function setCedulaUsuario($cedulaUsuario) {
        $this->cedulaUsuario = $cedulaUsuario;
    }


}




?>