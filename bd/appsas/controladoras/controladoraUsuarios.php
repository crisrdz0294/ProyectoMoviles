<?php


require '../data/dataUsuario.php';
include '../dominio/Usuarios.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    if(isset($_GET["accion"])){

        switch ($_GET["accion"]) {

            case 'validarUsuario':

                 if(isset($_GET["correo"])&&isset($_GET["clave"])){

                $usuario = dataUsuario::obtenerUsuarioCredenciales($_GET["correo"],$_GET["clave"]);

                if ($usuario) {

                    $datos["estado"] = 1;
                    $datos["usuario"] = $usuario;

                    print json_encode($datos);
                } else {
                    print json_encode(array(
                     "estado" => 2,
                     "mensaje" => "Ha ocurrido un error"
                 ));
                }
            }

            break;

            case 'obtenerUsuarios':

              $usuarios = dataUsuario::obtenerUsuarios();

                if ($usuarios) {

                    $datos["estado"] = 1;
                    $datos["usuarios"] = $usuarios;

                    print json_encode($datos);
                } else {
                    print json_encode(array(
                        "estado" => 2,
                        "mensaje" => "Ha ocurrido un error"
                    ));
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

            case 'insertarUsuario':

                 $usuarioJson = json_decode(file_get_contents("php://input"), true);

                 $usuario = new Usuarios();

                 $usuario->setCedula($usuarioJson['cedula']);
                 $usuario->setNombre($usuarioJson['nombre']);
                 $usuario->setApellidos($usuarioJson['apellidos']);
                 $usuario->setCorreo($usuarioJson['correo']);
                 $usuario->setTelefono($usuarioJson['telefono']);
                 $usuario->setClave($usuarioJson['clave']);
                 $usuario->setTipo($usuarioJson['tipo']);

                $resultado=dataUsuario::insertarUsuario($usuario);

                if ($resultado) {

                    print json_encode(
                        array(
                            'estado' => '1',
                            'mensaje' => 'Creación exitosa')
                    );
                } else {

                    print json_encode(
                        array(
                            'estado' => '2',
                            'mensaje' => 'Creación fallida')
                    );
                }
            break;

            case 'eliminarUsuario':

                $usuarioJson = json_decode(file_get_contents("php://input"), true);
                $cedula=$usuarioJson['cedula'];

                if(isset($cedula)){
                   $resultado=dataUsuario::eliminarUsuario($cedula);

                    if ($resultado) {

                        print json_encode(
                            array(
                                'estado' => '1',
                                'mensaje' => 'Eliminacion exitosa')
                        );
                    } else {

                        print json_encode(
                            array(
                                'estado' => '2',
                                'mensaje' => 'Eliminacion fallida')
                        );
                    }
                }

            break;

            case 'actualizarUsuario':

                 $usuarioJson = json_decode(file_get_contents("php://input"), true);

                 $usuario = new Usuarios();

                 $usuario->setCedula($usuarioJson['cedula']);
                 $usuario->setNombre($usuarioJson['nombre']);
                 $usuario->setApellidos($usuarioJson['apellidos']);
                 $usuario->setCorreo($usuarioJson['correo']);
                 $usuario->setTelefono($usuarioJson['telefono']);
                 $usuario->setClave($usuarioJson['clave']);
                 $usuario->setTipo($usuarioJson['tipo']);

                $resultado=dataUsuario::actualizarUsuario($usuario);

                if ($resultado) {

                    print json_encode(
                        array(
                            'estado' => '1',
                            'mensaje' => 'Actualizacion exitosa')
                    );
                } else {

                    print json_encode(
                        array(
                            'estado' => '2',
                            'mensaje' => 'Actualizacion fallida')
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
