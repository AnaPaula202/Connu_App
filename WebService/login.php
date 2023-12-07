<?php
require 'conexion.php'; // Asegúrate de tener un archivo de conexión

$post = empty($_POST) ? json_decode(file_get_contents('php://input'), true) : $_POST;

$respuesta['exito'] = false;
$respuesta['msj'] = '';
$respuesta['idUsuario'] = null;
$respuesta['tipoUsuario'] = null;

$correo = filtrado($post['mail']);
$contrasena = filtrado($post['pass']);

$query = "SELECT * FROM usuario WHERE correo = '$correo' LIMIT 1";
$result = mysqli_query($conexion, $query);

if ($result) {
    if ($usuario = mysqli_fetch_assoc($result)) {
        if ($usuario['contrasena'] === $contrasena) {
            $respuesta['exito'] = true;
            $respuesta['msj'] = 'Inicio de sesión exitoso';
            $respuesta['idUsuario'] = $usuario['idusuario'];

            // Consulta adicional para obtener el nombre del tipo de usuario
            $idTipoUsuario = $usuario['tipo_idtipo'];
            $tipoUsuarioQuery = "SELECT * FROM tipousuario WHERE idtipousuario = $idTipoUsuario";
            $tipoUsuarioResult = mysqli_query($conexion, $tipoUsuarioQuery);

            if ($tipoUsuarioResult && $tipoUsuario = mysqli_fetch_assoc($tipoUsuarioResult)) {
                $respuesta['tipoUsuario'] = $tipoUsuario['nombre'];
            } else {
                $respuesta['msj'] = 'Error al obtener el tipo de usuario';
            }
        } else {
            $respuesta['msj'] = 'Contraseña incorrecta';
        }
    } else {
        $respuesta['msj'] = 'Usuario no encontrado';
    }
} else {
    $respuesta['msj'] = 'Error en la consulta';
}

header('Content-type: application/json');
echo json_encode($respuesta);

function filtrado($datos)
{
    $datos = trim($datos);
    $datos = stripslashes($datos);
    $datos = htmlspecialchars($datos);
    $datos = mb_strtoupper($datos);
    return $datos;
}
?>
