<html>
	<head>
      <meta charset="utf-8" />
   <title>Update</title>
   <link rel="shortcut icon" href="<?php echo base_url();?>images/train.ico">
   <link href="<?php echo base_url();?>css/bootstrap.css" rel="stylesheet" type="text/css"/>
   <link href="<?php echo base_url();?>css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
     <script src="<?php echo base_url();?>css/jquery.min.js"></script>
  <script src="<?php echo base_url();?>css/bootstrap.min.js"></script>
<body background="<?php echo base_url();?>images/blue.jpg" style="background-size:cover;">
<div class="container-fluid">
    <section class="container" style="width:90%;margin-top:5%;">
		 <div class="panel panel-default">
                <div class="panel-heading">
            
        <center> <img src="<?php echo base_url();?>images/train.png" style="width:20%; height:20%;"></div></center>
               <div style="padding:50px 0">
<div class="container" style="height:60%; width:30%;" >
    <div class="row">
         
  <div class="panel panel-default">
                <div class="panel-heading">
                    <center></span>Update</div></center>
                <div class="panel-body">
            <?php echo form_open('login/update_user',array("method" => "post","role" => "form","name" => "myForm","ng-app" => "myApp","ng-controller" => "validateCtrl","novalidate"))?>

<!-- Hidden -->
<input type="hidden" name="reg_id" value="<?php echo $reg_id;?>" />
<!-- Hidden -->
 <div class="col-xs-6 col-md-6">
<label>First Name</label>
<input class="form-control" name="firstname" ng-model="firstname" value="<?php echo $firstname;?>" type="text"  required autofocus maxlength="16" onkeydown="return alphaOnly(event);"/>
</div>
<div class="col-xs-6 col-md-6">
<label>Last Name</label>
<input class="form-control" name="lastname" ng-model="lastname" value="<?php echo $lastname;?>"  type="text" required maxlength="16" onkeydown="return alphaOnly(event);" />					
<br>
</div>
<label>Email:</label>
<input type="email" name="email" class="form-control" value="<?php echo $email;?>"/>
<br><label>Home Address:</label>
<input type="text" name="homeaddress" class="form-control" value="<?php echo $homeaddress;?>"/>
<br><label>Contact Number:</label>
<input type="text" name="contactnumber" class="form-control" value="<?php echo $contactnumber;?>"/>
<br><center><button type="submit" class="btn btn-lg btn-primary btn-sm">Register</button></center>
<?php echo form_close();?>

</body>
</html>
<script type="text/javascript">
var app = angular.module('myApp', []);
app.controller('validateCtrl', function($scope) {
    $scope.firstname = '';
	  $scope.lastname = '';
	  $scope.home = '';
    $scope.email = '';
    $scope.contact = '';
});
</script>