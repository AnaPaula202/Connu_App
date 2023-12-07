<?php
require 'conexion.php'; // Asegúrate de tener un archivo de conexión

$post = empty($_POST) ? json_decode(file_get_contents('php://input'), true) : $_POST;

$respuesta['exito'] = false;
$respuesta['msj'] = '';
$respuesta['idUsuario'] = null;

$idUsuario = filtrado($post['idUsuario']);
$correo = filtrado($post['mail']);
$nombre = filtrado($post['name']);

// Modificación para cambiar tipo de usuario si el correo incluye '@ucc.mx'
if (strpos($correo, '@ucc.mx') !== false) {
    $type = 2; // Cambia a tipo de usuario 2
} else {
    $type = 1; // Mantiene tipo de usuario 1
}

$query = "UPDATE usuario SET
    nombre = '$nombre',
    correo = '$correo',
    tipo_idtipo = $type
    WHERE idusuario = $idUsuario";

$res = mysqli_query($conexion, $query);

if ($res){
    $respuesta['exito'] = true;
    $respuesta['msj'] = "Se han actualizado los datos";
}else{
    $respuesta['msj'] = 'Error al actualizar los datos: ' . mysqli_error($conexion);
}

header('Content-type: application/json');
echo json_encode($respuesta);

function filtrado($datos)
{
    $datos = trim($datos);
    $datos = stripslashes($datos);
    $datos = htmlspecialchars($datos);
    return $datos;
}
?>
