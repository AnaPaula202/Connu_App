<?php
$post = empty($_POST) ? json_decode(file_get_contents('php://input'), true) : $_POST; // ajusta a $_GET según necesidad

require 'conexion.php';

$respuesta['exito'] = false;

$name = $post['name'];
$pass = $post['pass'];
$mail = $post['mail'];
$sex = $post['sex'];

// Verificar si el correo incluye '@ucc.mx' para determinar el tipo
$type = (strpos($mail, '@ucc.mx') !== false) ? 2 : 1;

$st = 1;

$query = "INSERT INTO usuario VALUES 
                           (NULL,
                            '$name',
                            '$pass',
                            '$mail',
                            $sex,
                            $type,
                            NOW(),
                            $st)";

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
