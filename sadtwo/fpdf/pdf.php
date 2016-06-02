<?php
//PDF USING MULTIPLE PAGES
//FILE CREATED BY: Carlos José Vásquez Sáez
//YOU CAN CONTACT ME: carlos@magallaneslibre.com
//FROM PUNTA ARENAS, MAGALLANES
//INOVO GROUP - http://www.inovo.cl

//define('FPDF_FONTPATH', 'font/');
require('fpdf/fpdf.php');

//Connect to your database
mysql_connect('localhost','root','');
mysql_select_db('sad');

//Create new pdf file
$pdf=new FPDF('L');

//Open file
$pdf->Open();

//Disable automatic page break
$pdf->SetAutoPageBreak(true);

//Add first page
$pdf->AddPage();

//print column titles for the actual page
$pdf->SetFillColor(0);
$pdf->SetTextColor(255);
$pdf->SetFont('Arial', 'B', 11);
$pdf->Cell(50, 6, 'customer id', 0, 0, 'C', 1);
$pdf->Cell(30, 6, 'name', 0, 0, 'C', 1);
$pdf->Cell(60, 6, 'address', 0, 0, 'C', 1);
$pdf->Ln();
//Select the Products you want to show in your PDF file
$result=mysql_query('SELECT * FROM customer');

//initialize counter
while($row = mysql_fetch_array($result))
{
    //If the current row is the last one, create new page and print column title
    $pdf->SetFillColor(255);
	$pdf->SetTextColor(0);
	$pdf->SetFont('Arial', 'B', 10);
	$customer_id = $row['customer_id'];
    $name = $row['name'];
    $address = $row['address'];
    $pdf->Cell(50, 6, $customer_id, 1, 0, 'C', 1);
    $pdf->Cell(30, 6, $name, 1, 0, 'C', 1);
    $pdf->Cell(60, 6, $address, 1, 0, 'C', 1);
	$pdf->Ln();
}
//Create file
$pdf->Output();
?>