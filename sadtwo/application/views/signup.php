<!DOCTYPE html>

<head>
      <meta charset="utf-8" />
   <title>Register</title>
   <link rel="shortcut icon" href="<?php echo base_url();?>images/train.ico">
   <link href="<?php echo base_url();?>css/bootstrap.css" rel="stylesheet" type="text/css"/>
   <link href="<?php echo base_url();?>css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
     <script src="<?php echo base_url();?>css/jquery.min.js"></script>
  <script src="<?php echo base_url();?>css/bootstrap.min.js"></script>
    <script src="<?php echo base_url();?>css/angular.min.js"></script>
    <script src="<?php echo base_url();?>css/ngStorage.min.js"></script>

   <body background="<?php echo base_url();?>images/blue.jpg" style="background-size:cover;">
<div class="container-fluid">
    <section class="container" style="width:90%;margin-top:5%;">
		 <div class="panel panel-default">
                
            
        <div class="panel-heading"><center> <img src="<?php echo base_url();?>images/train.png" style="width:20%; height:20%;"><?php echo anchor("login/logout","Logout",array("class" => "btn btn-primary btn-lg","style" => "margin-left:90%"));?>
</div></center>
        <center>
        <?php if(isset($add)) { ?>
        <div class="alert alert-success" style="width:20%">
                  <b><?php echo $add;?></b>
            <button class="close" data-dismiss="alert">&times;</button>
        </div>
        <?php } ?>
        </center>
               <div style="padding:50px 0">
<div class="container" style="height:40%; width:30%;" >
    <div class="row">
         
  <div class="panel panel-default">

                <div class="panel-heading">
                    <center></span>Register</div></center>
                <div class="panel-body">
            <?php echo form_open_multipart('login/register',array("method" => "post","role" => "form","name" => "myForm","ng-app" => "myApp","ng-controller" => "validateCtrl","novalidate"))?>
            <div class="row">
              <p>  <div class="col-xs-6 col-md-6">
                <label>First Name</label>
                    <input type="text" name="firstname" value="<?php echo set_value('firstname'); ?>" class="form-control" ng-model="firstname" placeholder="First Name" required autofocus maxlength="16" onkeydown="return alphaOnly(event);"/>
					<span style="color:red" ng-show="myForm.firstname.$dirty && myForm.firstname.$invalid" style="border-color:red">
					 <span ng-show="myForm.firstname.$error.required"><b><i>Firstname is required.</i></b></span>
					 </span>
           <?php echo '<b><i>'.form_error('firstname', '<span style="color:red">', '</span>').'</i></b>'; ?>
                </div>

                <div class="col-xs-6 col-md-6">
                  <label>Last Name</label>
                    <input class="form-control" name="lastname" value="<?php echo set_value('lastname');?>" ng-model="lastname" placeholder="Last Name" type="text" required maxlength="16" onkeydown="return alphaOnly(event);" />
					<span style="color:red" ng-show="myForm.lastname.$dirty && myForm.lastname.$invalid" style="border-color:red">
					 <span ng-show="myForm.lastname.$error.required"><b><i>Lastname is required.</i></b></span>
					 </span>
           <?php echo '<b><i>'.form_error('lastname', '<span style="color:red">', '</span>').'</i></b>'; ?>
				</div>
            </div></p>
            	<label>Gender:</label>
            	<p style="margin-left:20%">
						<input type="radio" class="" name="gender" id="male" placeholder="Username" value="male" checked="checked">
						<label for="male">Male</label>
            <br>
						<input type="radio" class="" name="gender" id="female" placeholder="Username" value="female">
						<label for="female">Female</label>
	</p><p>
    <label>Birthday</label>
  <input class="form-control" type="date" name="bday" value="<?php echo date('Y-m-d')?>">
  <?php echo '<b><i>'.form_error('bday', '<span style="color:red">', '</span>').'</i></b>'; ?>
</p><p>
	</p><p><label>Email Address</label>
            <input class="form-control" name="email" ng-model="email" value="<?php echo set_value('email'); ?>" required placeholder="Email Address" type="email" />
			<span style="color:red" ng-show="myForm.email.$invalid && myForm.email.$dirty">
				<span ng-show="myForm.email.$error.required"><b><i>Email is required.</i></b></span>
			 <span ng-show="myForm.email.$error.email"><b><i>Invalid email address.</i></b></span>

			 </span></p>
             <?php echo '<b>'.form_error('email', '<span style="color:red">', '</span>').'</b>'; ?>
<p><label>Home Address</label>
            <input class="form-control" name="home" placeholder="Home Address" value="<?php echo set_value('home'); ?>" ng-model="home" type="text" required ng-minlength="20"/>
		<span style="color:red" ng-show="myForm.home.$invalid && myForm.home.$dirty">
				<span ng-show="myForm.home.$error.required"><b><i>Home Address is required.</i></b></span>
			 <span ng-show="myForm.home.$error.home"><b><i>Invalid Home Address.</i></b></span>
			 </span>
      <span ng-show="myForm.home.$error.minlength" style="color:red">
       <b>  Minimum lenghth is 20 </b></span></p><p>
      <br>
       <label>Contact Number</label>
			<input class="form-control" name="contact"  ng-model="contact" value="<?php echo set_value('contact'); ?>" placeholder="Contact Number" ng-minlength="11" type="text" required onkeydown='return (event.which >= 48 && event.which <= 57) || event.which == 8 || event.which == 46'/>
	<span style="color:red" ng-show="myForm.contact.$invalid && myForm.contact.$dirty">
				<span ng-show="myForm.contact.$error.required"><b><i>Contact Number is required.</i></b></span>
			 <span ng-show="myForm.contact.$error.contact"><b><i>Invalid Contact Number</i></b></span>
			 </span>
       <span ng-show="myForm.home.$error.minlength" style="color:red">
       <b> Contact should be at least 11 numbers</b></span>
      <?php echo '<b>'.form_error('contact', '<span style="color:red">', '</span>').'</b>'; ?>

	</p>
       <div ng-app="app">
  <div ng-controller="homeCtrl">
  
      <div  ng-class="{'has-error':formData.password.$invalid && !formData.password.$pristine}">
     <label>Password</label>
        <input class="form-control"  type="password" id="password" name="password" ng-model="formData.password" ng-minlength="8" ng-maxlength="20" ng-pattern="/(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z])/" placeholder="Password" required />

        <p ng-show="myForm.password.$error.required" class="error">
        <p ng-show="myForm.password.$error.minlength" class="error" style="color:red">
          <b> Passwords must between 8 to 20 characters. </b> </p>
        <p ng-show="myForm.password.$error.pattern" class="error" style="color:red">
          <b> Must contain one lower &amp; uppercase letter, and one non-alpha character (a number or a symbol.) </b> </p>
      </div>

      <div ng-class="{'has-error':formData.password_c.$invalid && !formData.password_c.$pristine}">
      <label>Confirm Password</label>
        <input  class="form-control"  type="password" id="password_c" name="password_c" ng-model="formData.password_c" placeholder="Confirm Password" valid-password-c="formData.password" required />

        <p ng-show="myForm.password_c.$error.noMatch" class="error" style="color:red"> <b> Passwords do not match. </b> </span>
          <p ng-show="myForm.password_c.$error.required" class="error">
      </div>
      <label>Choose Picture</label>
      <input type="file" name="userfile" class="form-control" required>
      <?php echo '<b>'.form_error('userfile', '<span style="color:red">', '</span>').'</b>'; ?>
</div>
</div>
	

<br>
						

         <center> 
          <button type="submit" ng-click="saveData()" class="btn btn-lg btn-primary btn-sm" ng-disabled="myForm.dirty || myForm.firstname.$dirty && myForm.firstname.$invalid ||
  myForm.email.$error.email && myForm.email.$dirty && myForm.email.$invalid || myForm.lastname.$dirty && myForm.lastname.$invalid || myForm.home.$dirty && myForm.home.$invalid || myForm.password.$dirty && myForm.password.$invalid  || myForm.contact.$dirty && myForm.contact.$invalid">Register</button>
<button type="reset" class="btn btn-primary btn-sm">
                                Reset</button></center>
            <?php echo form_close();?>
        </div>
    </div>
</div>
</div>

</body>
</html>
<script>
var app = angular.module('myApp', ['ngStorage']);
app.controller('validateCtrl', function($scope,$localStorage) {
    $scope.firstname = '';
	  $scope.lastname = '';
	  $scope.home = '';
    $scope.email = '';
    $scope.contact = '';
      $scope.firstname = $localStorage.storeFname;
      $scope.lastname = $localStorage.storeLname;
      $scope.home = $localStorage.storeHome;
      $scope.email = $localStorage.storeEmail;
      $scope.contact = $localStorage.storeContact;
    $scope.saveData = function(){
      $localStorage.storeFname = $scope.firstname;
      $localStorage.storeLname = $scope.lastname;
      $localStorage.storeHome = $scope.home;
      $localStorage.storeEmail = $scope.email;
      $localStorage.storeContact = $scope.contact;
    }

});
app.directive('validPasswordC', function() {
  return {
    require: 'ngModel',
    scope: {

      reference: '=validPasswordC'

    },
    link: function(scope, elm, attrs, ctrl) {
      ctrl.$parsers.unshift(function(viewValue, $scope) {

        var noMatch = viewValue != scope.reference
        ctrl.$setValidity('noMatch', !noMatch)
      });

      scope.$watch("reference", function(value) {;
        ctrl.$setValidity('noMatch', value === ctrl.$viewValue);

      });
    }
  }
});
app.controller('homeCtrl', function($scope) {




});

$("#buttonAlert").click(function(){
	alert("Awesome :D");
});
function alphaOnly(event) {
  var key = event.keyCode;
  return ((key >= 65 && key <= 90) || key == 8);
};
</script>
