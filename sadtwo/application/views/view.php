<html><head>
      <meta charset="utf-8" />
   <title>Homepage</title>
   <link rel="shortcut icon" href="<?php echo base_url();?>images/train.ico">
    <link href="<?php echo base_url();?>css/bootstrap.css" rel="stylesheet" type="text/css"/>
   <link href="<?php echo base_url();?>css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
     <script src="<?php echo base_url();?>css/jquery.min.js"></script>
  <script src="<?php echo base_url();?>css/bootstrap.min.js"></script>
    <script src="<?php echo base_url();?>css/angular.min.js"></script>
</head>
<style >
.filterable {
    margin-top: 15px;
}
.filterable .panel-heading .pull-right {
    margin-top: -20px;
}
.filterable .filters input[disabled] {
    background-color: transparent;
    border: none;
    cursor: auto;
    box-shadow: none;
    padding: 0;
    height: auto;
}
.filterable .filters input[disabled]::-webkit-input-placeholder {
    color: #333;
}
.filterable .filters input[disabled]::-moz-placeholder {
    color: #333;
}
.filterable .filters input[disabled]:-ms-input-placeholder {
    color: #333;
}
.modal .modal-header {
  border-bottom: none;
  position: relative;
}
.modal .modal-header .btn {
  position: absolute;
  top: 0;
  right: 0;
  margin-top: 0;
  border-top-left-radius: 0;
  border-bottom-right-radius: 0;
}
.modal .modal-footer {
  border-top: none;
  padding: 0;
}
.modal .modal-footer .btn-group > .btn:first-child {
  border-bottom-left-radius: 0;
}
.modal .modal-footer .btn-group > .btn:last-child {
  border-top-right-radius: 0;
}

</style>
<html>
	<body>
    <center>
      <?php if($this->session->flashdata('updated')) { ?>
        <div class="alert alert-success" style="width:20%">
            <b><?php echo $this->session->flashdata('updated'); ?></b>
            <button class="close" data-dismiss="alert">&times;</button>
        </div>
        <?php } ?>
    </center>
    
   <div class="container">
    <div class="row">
    
    
   
    </div>
</div>  
<?php 
echo "<center>";
echo "<div class='container' style='width:90%;'>";
echo "<div class='row'>";
echo "<div class='panel panel-primary filterable'>";
echo "<div class='panel-heading'>";
echo "<h3 class='panel-title'>Users</h3>";
echo "<div class='pull-right'>";
echo "<button class='btn btn-default btn-xs btn-filter'>  <span class='glyphicon glyphicon-filter'></span> Filter</button>";
echo "</div>";
echo "</div>";
echo "<table class='table'>";
echo "<thead>";
echo " <tr class='filters'>";
echo "<th><input type='text' class='form-control' placeholder='Last Name' disabled></th>";
echo "<th><input type='text' class='form-control' placeholder='First Name' disabled></th>";
echo "<th><input type='text' class='form-control' placeholder='Email' disabled></th>";
echo "<th><input type='text' class='form-control' placeholder='Home Address' disabled></th>";
echo "<th><input type='text' class='form-control' placeholder='Contact Number' disabled></th>";
echo "<th><input type='text' class='form-control' placeholder='Load' disabled></th>";
echo "</tr>";
echo "</thead>";

if(isset($results)) : foreach($results as $row) :
echo "<tr>";
echo "<td>" . $row->lastname . "</td>";
echo "<td>" . $row->firstname . "</td>";
echo "<td>" . $row->email . "</td>";
echo "<td>" . $row->homeaddress . "</td>";
echo "<td>" . $row->contactnumber . "</td>";
echo "<td>" . $row->ticket_load . "</td>";
echo "<td>";
$data = array(
                        'reg_id' => $row->reg_id,
                        'lastname' => $row->lastname,
                        'firstname' => $row->firstname,
                        'email' => $row->email,
                        'homeaddress' => $row->homeaddress,
                        'contactnumber' => $row->contactnumber
                    );
                    echo form_open('login/update');
                        echo form_hidden($data);?>
                        <center><input type="submit" class="btn btn-info" value="Edit"></center>
                    <?php echo form_close();
echo "</td>";
echo "</tr>";
endforeach;
endif;
echo"</table>";
?>
      <center><?php echo $this->pagination->create_links(); ?></center>
            <?php echo anchor("login/view_schedules","View Schedules",array("class" => "btn btn-success btn-lg","style" => "margin-left:80%"));?>
            <?php echo '<br>';?>
      <?php echo anchor("login/logout","Logout",array("class" => "btn btn-primary btn-lg","style" => "margin-left:80%"));?>
  </body>

</html>
<script >
$(document).ready(function(){
    $('.filterable .btn-filter').click(function(){
        var $panel = $(this).parents('.filterable'),
        $filters = $panel.find('.filters input'),
        $tbody = $panel.find('.table tbody');
        if ($filters.prop('disabled') == true) {
            $filters.prop('disabled', false);
            $filters.first().focus();
        } else {
            $filters.val('').prop('disabled', true);
            $tbody.find('.no-result').remove();
            $tbody.find('tr').show();
        }
    });

    $('.filterable .filters input').keyup(function(e){
        /* Ignore tab key */
        var code = e.keyCode || e.which;
        if (code == '9') return;
        /* Useful DOM data and selectors */
        var $input = $(this),
        inputContent = $input.val().toLowerCase(),
        $panel = $input.parents('.filterable'),
        column = $panel.find('.filters th').index($input.parents('th')),
        $table = $panel.find('.table'),
        $rows = $table.find('tbody tr');
        /* Dirtiest filter function ever ;) */
        var $filteredRows = $rows.filter(function(){
            var value = $(this).find('td').eq(column).text().toLowerCase();
            return value.indexOf(inputContent) === -1;
        });
        /* Clean previous no-result if exist */
        $table.find('tbody .no-result').remove();
        /* Show all rows, hide filtered ones (never do that outside of a demo ! xD) */
        $rows.show();
        $filteredRows.hide();
        /* Prepend no-result row if all rows are filtered */
        if ($filteredRows.length === $rows.length) {
            $table.find('tbody').prepend($('<tr class="no-result text-center"><td colspan="'+ $table.find('.filters th').length +'">No result found</td></tr>'));
        }
    });
});
</script>