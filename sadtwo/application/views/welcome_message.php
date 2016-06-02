<!DOCTYPE html>

<head>
      <meta charset="utf-8" />
   <title>Log In</title>
   <link rel="shortcut icon" href="<?php echo base_url();?>images/train.ico">
   <link href="<?php echo base_url();?>css/bootstrap.css" rel="stylesheet" type="text/css"/>
   <link href="<?php echo base_url();?>css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
     <script src="<?php echo base_url();?>css/jquery.min.js"></script>
  <script src="<?php echo base_url();?>css/bootstrap.min.js"></script>
   <body background="<?php echo base_url();?>images/blue.jpg" style="background-size:cover;">
<br><br><br><br><br><br><br>
<center>
    <?php if(isset($not_admin)) { ?>
        <div class="alert alert-danger" align="center" style="width:20%">
                  <b><?php echo $not_admin?></b>
            <button class="close" data-dismiss="alert">&times;</button>
        </div>
    <?php } ?>
    <?php if(isset($employee)) { ?>
        <div class="alert alert-danger" align="center" style="width:20%">
                  <b><?php echo $employee?></b>
            <button class="close" data-dismiss="alert">&times;</button>
        </div>
    <?php } ?>
    <?php if(isset($error)) { ?>
        <div class="alert alert-danger" align="center" style="width:20%">
                  <b><?php echo $error?></b>
            <button class="close" data-dismiss="alert">&times;</button>
        </div>
    <?php } ?>
    <?php if(isset($logout)) { ?>
        <div class="alert alert-info" align="center" style="width:20%">
                  <b><?php echo $logout?></b>
            <button class="close" data-dismiss="alert">&times;</button>
        </div>
    <?php } ?>
</center>
<div class="container" style="width:30%;">

    <div class="row">
   
            <div class="panel panel-default">
                <div class="panel-heading">
            
        <center> <img src="<?php echo base_url();?>images/train.png" style="width:60%; height:60%;"></div></center>
                <div class="panel-body">
                    <?php echo form_open('login/validation',array('class'=>'form-horizontal','role'=>'form'));?>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label">
                            Email</label>
                        <div class="col-sm-9">
                            <input type="email" name="email" class="form-control" id="inputEmail3" placeholder="Email" value="<?php echo set_value("email")?>" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-3 control-label">
                            Password</label>
                        <div class="col-sm-9">
                            <input type="password" name="password" class="form-control" id="inputPassword3" placeholder="Password" required>
                        </div>
                    </div>
                   
                    <div class="form-group last">
                        <div class="col-sm-offset-3 col-sm-9">
                            <button type="submit" class="btn btn-primary btn-sm">
                                Sign in</button>
                                 <button type="reset" class="btn btn-primary btn-sm">
                                Reset</button>
                        </div>
                    </div>
                        <?php echo form_close(); ?>
                </div>
                <div class="panel-footer">
            <b><i> <h3 style="margin-left:60%;">Tick-A-Train</h3></i>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
