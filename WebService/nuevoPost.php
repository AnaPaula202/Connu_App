<?php
$post = empty($_POST) ? json_decode(file_get_contents('php://input'), true) : $_POST; 

require 'conexion.php';

$respuesta['exito'] = false;

$tipo = $post['tipopost'];
$iduser = $post['iduser'];
$content = $post['content'];
$img = $post['img'];

$likes = 0;

$query = "INSERT INTO publicacion VALUES 
                           (NULL,
                            '$content',
                            '$img',
                            $iduser,
                            $tipo,
                            NOW(),
                            $likes)";

$res = mysqli_query($conexion, $query);

if ($res){
    $respuesta['exito'] = true;
    $respuesta['msj'] = "Se han guardado los datos";
} else {
    $respuesta['msj'] = 'Error al guardar los datos: ' . mysqli_error($conexion);
}

header('Content-type: application/json');
echo json_encode($respuesta);
?>
