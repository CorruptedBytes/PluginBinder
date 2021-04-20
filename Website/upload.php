<?php

$secretKey = 'HCAPTCHA-SECRETKEY-HERE'; 

if(!empty($_POST['h-captcha-response'])){ 
            $verifyURL = 'https://hcaptcha.com/siteverify'; 
            $token = $_POST['h-captcha-response']; 
             
            $data = array( 
                'secret' => $secretKey, 
                'response' => $token, 
                'remoteip' => $_SERVER['REMOTE_ADDR'] 
            ); 
             
            $curlConfig = array( 
                CURLOPT_URL => $verifyURL, 
                CURLOPT_POST => true, 
                CURLOPT_RETURNTRANSFER => true, 
                CURLOPT_POSTFIELDS => $data 
            ); 
            $ch = curl_init(); 
            curl_setopt_array($ch, $curlConfig); 
            $response = curl_exec($ch); 
            curl_close($ch); 
             
            $responseData = json_decode($response); 
             
            if($responseData->success){ 
				
			$ziel = "files/";
			$zieldatei = $ziel . basename($_FILES["1"]["name"]);
			$zieldatei2 = $ziel . basename($_FILES["2"]["name"]);
			if (move_uploaded_file($_FILES["1"]["tmp_name"], $zieldatei) && move_uploaded_file($_FILES["2"]["tmp_name"], $zieldatei2)) {
				copy($zieldatei, '1.jar');
				copy($zieldatei2, '2.jar');
				include('zip.php');
				die();
			
			}
			else {
				echo "Fehler";
			}
			
			
			}
			
}
?>