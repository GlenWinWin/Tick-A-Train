<html>
<head>
  <title>My calendar</title>
  <link rel="stylesheet" type="text/css" href="<?php echo base_url();?>css/stylecalendar.css">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="shadow_left">&nbsp;</td>   
    <td class="header_column">
  <table width="100%" border="0" cellspacing="10" cellpadding="0">
      <tr>
       <div class="right-left">
              <ul>
                <li class="login"><?php echo anchor("login/logout","Logout");?></a></li>
              </ul>
              </div>

        <td class="logo_area"><img src="<?php echo site_url('images/exodus.jpg'); ?>" /> </td>
        <td width="250">
          <form id="form1" name="form1" method="get" action="search.php">
        Search the website <br />
            <input name="search" type="text" id="search" />
            <input type="submit" name="Submit" value="Search" />
          </form>
          </td>
      </tr>
    </table></td>
    <td class="shadow_right">&nbsp;</td>
  </tr>
  <tr>
    <td class="horizontal_column">&nbsp;</td>
    <td class="horizontal_center"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="linkcontainer">
<tr>
         <div class="clear"> </div>
<div class="top-nav">
            <div class="wrap">
              <ul>
                <li><?php echo anchor("login/admin","Home");?></li>
                <li><?php echo anchor("login/about","About");?></li>
                <li class="active"><a href="<?php echo site_url('login/viewservices'); ?>">Services</a></li>
                <li><a href="news.html">News</a></li>
                <li><a href="contact.html">Contact</a></li>
                <li><a href="<?php echo site_url('mycal/index'); ?>">Calendar</a></li>
                <div class="clear"> </div>
              </ul>
            </div>
          </div>
      </tr>
    </table></td>

    <td class="horizontal_column">&nbsp;</td>
  </tr>
  <tr>
    <td class="shadow_left">&nbsp;</td>
    <td class="below_header"></td>
    <td class="shadow_right">&nbsp;</td>
  </tr>
  <tr>
    <td class="shadow_left">&nbsp;</td>
    <td class="main_content_box"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      </tr>
      <h1>General Dentistry</h1>
</td>
      </tr>
    </td>
    <td class="shadow_right">&nbsp;</td>
  </tr>
  <tr>
    <td class="shadow_left">&nbsp;</td>
    <td class="middle_spacer"><div class="bottom_content"></div></td>
    <td class="shadow_right">&nbsp;</td>
  </tr>

    <!---start-footer---->
    <div class="footer">
      <div class="wrap">
        <div class="footer-grids">
          <div class="footer-grid">
            <h3>OUR Profile</h3>
             <ul>
              <li><a href="#">Lorem ipsum dolor sit amet</a></li>
              <li><a href="#">Conse ctetur adipisicing</a></li>
              <li><a href="#">Elit sed do eiusmod tempor</a></li>
              <li><a href="#">Incididunt ut labore</a></li>
              <li><a href="#">Et dolore magna aliqua</a></li>
              <li><a href="#">Ut enim ad minim veniam</a></li>
            </ul>
          </div>
          <div class="footer-grid">
            <h3>Our Services</h3>
             <ul>
              <li><a href="#">Et dolore magna aliqua</a></li>
              <li><a href="#">Ut enim ad minim veniam</a></li>
              <li><a href="#">Quis nostrud exercitation</a></li>
              <li><a href="#">Ullamco laboris nisi</a></li>
              <li><a href="#">Ut aliquip ex ea commodo</a></li>
            </ul>
          </div>
          <div class="footer-grid">
            <h3>OUR FLEET</h3>
             <ul>
              <li><a href="#">Lorem ipsum dolor sit amet</a></li>
              <li><a href="#">Conse ctetur adipisicing</a></li>
              <li><a href="#">Elit sed do eiusmod tempor</a></li>
              <li><a href="#">Incididunt ut labore</a></li>
              <li><a href="#">Et dolore magna aliqua</a></li>
              <li><a href="#">Ut enim ad minim veniam</a></li>
            </ul>
          </div>
          <div class="footer-grid">
            <h3>CONTACTS</h3>
             <p>Lorem ipsum dolor sit amet ,</p>
             <p>Conse ctetur adip .</p>
             <p>ut labore Usa.</p>
             <span>(202)1234-56789</span>
          </div>
          <div class="clear"> </div>
        </div>
        <div class="clear"> </div>
        <!---start-copy-right----->
        
    </div>
    <!---End-footer---->
</body>
</html>