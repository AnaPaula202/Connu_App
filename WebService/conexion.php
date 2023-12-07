<?php

//define el host
define('DB_HOST', 'localhost');
//defino usuario
define('DB_USER', 'root');
//defino contraseña
define('DB_PASS', '');
//defino la base de datos
define('DB_NAME', 'connu_bd');

$conexion = mysqli_connect(DB_HOST, DB_USER, DB_PASS, DB_NAME);

if ($conexion === false) { //¿error?
    exit('Error en la conexión con la bd');
}

mysqli_set_charset($conexion, 'utf8');

