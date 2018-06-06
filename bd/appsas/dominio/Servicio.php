<?php

class Servicio{
  private $idServicio;
  private  $nombreServicio;
  private $descripcionServicio;
  private $ubicacionServicio;
  private $estadoServicio;
  private $precioServicio;
  private $tipoServicio;
  private $imagen1;
  private $imagen2;
  private $imagen3;
   private $cedula;

   function Servicio($id,$NombreServicio,$DescripcionServicio,$UbicacionServicio,
   $EstadoServicio,$PrecioServicio,$TipoServicio,$Imagen1,$Imagen2,$Imagen3,$cedu)
   {
     $this->idServicio=$id;
      $this->nombreServicio=$NombreServicio;
     $this->descripcionServicio=$DescripcionServicio;
     $this->ubicacionServicio=$UbicacionServicio;
     $this->estadoServicio=$EstadoServicio;
     $this->precioServicio=$PrecioServicio;
     $this->tipoServicio=$TipoServicio;
     $this->imagen1=$Imagen1;
     $this->imagen2=$Imagen2;
     $this->imagen3=$Imagen3;
     $this->cedula=$cedu;

   }

  function getId(){
    return $this->idServicio;
  }

  function getNombre(){
    return $this->nombreServicio;
  }

  function getDescripcion(){
    return $this->descripcionServicio;
  }
  function getUbicacion(){
    return $this->ubicacionServicio;
  }
  function getEstado(){
    return $this->estadoServicio;
  }
  function getPrecio(){
    return $this->precioServicio;
  }
  function getTipo(){
  return $this->tipoServicio;
}
function getPriImagen(){
  return $this->imagen1;
}
function  getSegunImage(){

return $this->imagen2;
}
function  getterImage(){

return $this->imagen3;
}
function getCedula(){
  return $this->cedula;
}


}
?>
