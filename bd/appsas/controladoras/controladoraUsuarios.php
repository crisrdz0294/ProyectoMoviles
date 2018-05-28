<?php 



require_once '../dominio/Usuarios.php';
include_once '../data/dataUsuario.php';


class ControladoraUsuarios {


	 function agregarUsuario(){
	 	
        $data = new dataUsuario();
        $jsonData = json_decode(file_get_contents("php://input"));
        $usuarios = new Usuarios();

        $usuarios->setCedula($jsonData->cedula);
        $usuarios->setNombre($jsonData->nombre);
        $usuarios->setApellidos($jsonData->apellidos);
        $usuarios->setCorreo($jsonData->correo);
        $usuarios->setTelefono($jsonData->telefono);
        $usuarios->setClave($jsonData->clave);
        $usuarios->setTipo($jsonData->tipo);

        if($data->insertarUsuario($usuarios)){
            echo true;
        }else {
            echo false;
        }
    }

    function validarUsuario(){

        $data = new dataUsuario();
        $jsonData = json_decode(file_get_contents("php://input"));

        $listaUsuarios=$data->validarUsuario($jsonData->correo,$jsonData->clave);

        if($listaUsuarios==null){
            echo "No se encuentra el usuario";
        }else{

            echo json_encode($listaUsuarios);
            
        }
    }

    function mostrarUsuarios(){
        $data = new dataUsuario();
        $jsonData = json_decode(file_get_contents("php://input"));

        $listaUsuarios=$data->obtenerUsuarios();

        if($listaUsuarios==null){
            echo "No se encuentra el usuario";
        }else{

            echo  json_encode($listaUsuarios);
            
        }

    }

    function actualizarUsuario(){
        $data = new dataUsuario();
        $jsonData = json_decode(file_get_contents("php://input"));
        $usuarios = new Usuarios();

        $usuarios->setCedula($jsonData->cedula);
        $usuarios->setNombre($jsonData->nombre);
        $usuarios->setApellidos($jsonData->apellidos);
        $usuarios->setCorreo($jsonData->correo);
        $usuarios->setTelefono($jsonData->telefono);
        $usuarios->setClave($jsonData->clave);
        $usuarios->setTipo($jsonData->tipo);

        if($data->actualizarUsuario($usuarios)){
            echo true;
        }else {
            echo false;
        }
    }

    function eliminarUsuario(){
        $data = new dataUsuario();
        $jsonData = json_decode(file_get_contents("php://input"));

        if($data->eliminarUsuario($jsonData->cedula)){
            echo true;
        }else {
            echo false;
        }
    }

}

$data = json_decode(file_get_contents("php://input"));
$op = $data->accion;


$control = new ControladoraUsuarios();

if($op=="validarUsuario"){
    $control->validarUsuario();
}
if($op=="agregarUsuario"){
    $control->agregarUsuario();
}
if($op=="mostrarUsuarios"){
    $control->mostrarUsuarios();
}

if($op=="actualizarUsuario"){
    $control->actualizarUsuario();
}

if($op=="eliminarUsuario"){
    $control->eliminarUsuario();
}

 ?>