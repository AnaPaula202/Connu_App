<?php
$post = empty($_POST) ? json_decode(file_get_contents('php://input'), true) : $_POST;

require 'conexion.php';

$respuesta['exito'] = false;

$postId = $post['idPost'];

$query = "DELETE FROM publicacion WHERE idpublicacion = $postId";

$res = mysqli_query($conexion, $query);

if ($res){
    $respuesta['exito'] = true;
    $respuesta['msj'] = "Se han eliminado los datos";
} else {
    $respuesta['msj'] = 'Error al eliminar los datos: ' . mysqli_error($conexion);
}

header('Content-type: application/json');
echo json_encode($respuesta);
?>
