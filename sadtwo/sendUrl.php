<?php
	//include_once("ViaNettSMS.php");

	if(isset($_REQUEST['MessageSender']) && isset($_REQUEST['MessageReceiver']) && isset($_REQUEST['Amount'])){
       $con = mysql_connect("localhost","root","");
       if (!$con)
       {
       die('Could not connect: ' . mysql_error());
       }
       mysql_select_db("train", $con);
       
       $amount = $_REQUEST['Amount'];
       $MessageSenderNumber = $_REQUEST['MessageSender'];
       $MessageReceiverNumber = $_REQUEST['MessageReceiver'];
       $contactnumber = '0'.$MessageReceiverNumber;
       $result = mysql_query("UPDATE register SET ticket_load = '$amount' WHERE contactnumber = '$contactnumber'") or die('Errant query:');
       
   //     		$Username = "gleenismyname@gmail.com ";
			// $Password = "suojj";
			// $MsgSender = "+4560991000";
			// $$DestinationAddress = '+63'.$MessageSenderNumber;
			// $DestinationNumberForReceiver = '+63'.$MessageReceiverNumber;
			//$MessageForReceiver = "P" . $load . '.00' . ' amount of load was loaded to your number 0' . $MessageReceiverNumber . ' by 0' . $MessageSenderNumber;

			$uname = "tickatrain@gmail.com";
			$pword = "Glenwin18";

			$info = "1";
			$test = "0";

			$from = "Tick-A-Train";
			$number = "63".$MessageReceiverNumber;
			$date_sent = date('dM Y',strtotime("+1 days"));
			$message = $date_sent.":P".$amount." loaded to 0".$MessageReceiverNumber." fr 0" .$MessageSenderNumber.".";
			$message = urldecode($message);

			$data = "&username=".$uname."&password=".$pword."&message=".$message."&from=".$from."&number=".$number."&info=".$info."&test=".$test;

			$ch = curl_init('http://www.txtlocal.com/sendsmspost.php');
			curl_setopt($ch,CURLOPT, true);
			curl_setopt($ch,CURLOPT_POSTFIELDS, $data);
			curl_setopt($ch,CURLOPT_RETURNTRANSFER, true);
			$result = curl_exec($ch);
			curl_close($ch);


			$from = "Tick-A-Train";
			$number = "63".$MessageSenderNumber;
			$date_sent = date('M d, Y',strtotime("+1 days"));
			$message = "Successfully loaded an amount of P".$amount." to 0".$MessageReceiverNumber.".";
			$message = urldecode($message);

			$data = "&username=".$uname."&password=".$pword."&message=".$message."&from=".$from."&number=".$number."&info=".$info."&test=".$test;

			$ch = curl_init('http://www.txtlocal.com/sendsmspost.php');
			curl_setopt($ch,CURLOPT, true);
			curl_setopt($ch,CURLOPT_POSTFIELDS, $data);
			curl_setopt($ch,CURLOPT_RETURNTRANSFER, true);
			$result = curl_exec($ch);
			curl_close($ch);

       mysql_close($con);
       			
       }
    else
       {
       $output = "not found";
       print(json_encode($output));
       }
?>