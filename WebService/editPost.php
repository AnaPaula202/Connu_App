<?php
require 'conexion.php';

$post = empty($_POST) ? json_decode(file_get_contents('php://input'), true) : $_POST;

$respuesta['exito'] = false;
$respuesta['msj'] = '';

$idPublicacion = filtrado($post['id']);

$contenido = filtrado($post['contenido']);
$img = filtrado($post['img']);
$tipo = filtrado($post['tipo']);

$query = "UPDATE publicacion SET 
    contenido='$contenido',
    img1='$img',
    tipo_idtipo='$tipo'
    WHERE idpublicacion = $idPublicacion";

$res = mysqli_query($conexion, $query);

if ($res) {
    $respuesta['exito'] = true;
    $respuesta['msj'] = "Se han actualizado los datos";
} else {
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
