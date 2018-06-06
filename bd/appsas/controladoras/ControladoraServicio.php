<?php

require '../data/dataservicio.php';
include '../dominio/Servicio.php';




if ($_SERVER['REQUEST_METHOD'] == 'POST') {


  $servicioR = json_decode(file_get_contents("php://input"), true);




        switch ($servicioR["accion"]) {

            case 'insertarServicios':


            $servicio = new Servicio(0,"",$servicioR["descripcionservicio"],$servicioR["ubicacionservicio"],1,$servicioR["precioservicio"],$servicioR["tiposervicio"],$servicioR["imagen1"],$servicioR["imagen2"],$servicioR["imagen3"],$servicioR["cedulausuario"]);




           $resultado=dataservicio::insertarServicio($servicio);

           if ($resultado) {

               print json_encode(
                   array(
                       'estado' => '1',
                       'mensaje' => 'Creación exitosa'
                    )
               );

           } else {

               print json_encode(
                   array(
                       'estado' => '2',
                       'mensaje' => 'Creación fallida'
                )
               );
           }
           break;
            default:
            if(isset($servicioR["accion"])){
              print json_encode(
                  array(
                      'estado' => '1',
                      'mensaje' => 'Error accion')
              );

}

}

}
