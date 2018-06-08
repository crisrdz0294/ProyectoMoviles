<?php

require '../data/dataservicio.php';
include '../dominio/Servicio.php';




if ($_SERVER['REQUEST_METHOD'] == 'POST') {


  $servicioR = json_decode(file_get_contents("php://input"), true);




        switch ($servicioR["accion"]) {




            case 'insertarServicios':


            $servicio = new Servicio(0,$servicioR["nombreservicio"],$servicioR["descripcionservicio"],$servicioR["ubicacionservicio"],1,$servicioR["precioservicio"],$servicioR["tiposervicio"],$servicioR["imagen1"],$servicioR["imagen2"],$servicioR["imagen3"],$servicioR["cedulausuario"]);




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

           case 'eliminarServicio':

                $resultado=dataservicio::desactivarServicio($servicioR["idservicio"]);

                if ($resultado) {

                    print json_encode(
                        array(
                            'estado' => '1',
                            'mensaje' => 'Desactivacion exitosa')
                    );
                } else {

                    print json_encode(
                        array(
                            'estado' => '2',
                            'mensaje' => 'Desactivacion fallida')
                    );
                }

           break;

            case 'actualizarServicios':




                  $servicio = new Servicio($servicioR["idservicio"],$servicioR["nombreservicio"],$servicioR["descripcionservicio"],$servicioR["ubicacionservicio"],$servicioR["estadoservicio"],$servicioR["precioservicio"],$servicioR["tiposervicio"],$servicioR["imagen1"],$servicioR["imagen2"],$servicioR["imagen3"],$servicioR["cedulausuario"]);

                             $resultado=dataservicio::actualizarServicio($servicio);


                             if ($resultado) {

                                 print json_encode(
                                     array(
                                         'estado' => '1',
                                         'mensaje' => 'Desactivacion exitosa')
                                 );
                             } else {

                                 print json_encode(
                                     array(
                                         'estado' => '2',
                                         'mensaje' => 'Desactivacion fallida')
                                 );
                             }




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
if ($_SERVER['REQUEST_METHOD'] == 'GET') {

  //$servicioR = json_decode(file_get_contents("php://input"), true);

        switch ($_GET["accion"]) {

            case 'verTodosServicios':
                $resultado=dataservicio::verServicios();

                      if ($resultado) {
                         $result["estado"] = 1;
                         $result["mensaje"] = 'creacion exitosa';
                        $result["servicios"]=$resultado;
                         print json_encode($result);

                       } else {

                           print json_encode(
                               array(
                                   'estado' => '2',
                                   'mensaje' => 'Creación fallida'
                            )
                           );
                       }
 break;

}



}
