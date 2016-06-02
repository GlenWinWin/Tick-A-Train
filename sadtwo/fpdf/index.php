<?php

//define('FPDF_FONTPATH', 'font/');
require('fpdf/fpdf.php');

//Connect to your database
mysql_connect('localhost','root','');
mysql_select_db('smcm');

//Create new pdf file
$pdf=new FPDF('P');
//Set Title
$pdf->SetTitle('St. Monique College of Manila'); 
//Open file
$pdf->Open();

//Disable automatic page break
$pdf->SetAutoPageBreak(true);
//$orientation='portrait';
//Add first page
$pdf->AddPage('P');

//print column titles for the actual page
//$pdf->SetFillColor(0);
$pdf->SetTextColor(255);
$pdf->SetFont('Arial', '', 20);
$pdf->MultiCell(150, 10, 'St. Monique College of Manila', 1, 0,'C' , 1);
$pdf->SetFont('Arial', 'B', 11);
$pdf->MultiCell(27,6, 'Last Name: ', 1, 0,'C' , 1);
$pdf->MultiCell(27, 6, 'First Name: ', 1, 0,'C' , 1);
$pdf->MultiCell(31, 6, 'Middle Name: ', 1, 0,'C' , 1);
$pdf->MultiCell(14, 6, 'Sex: ', 1, 0,'C' , 1);
$pdf->MultiCell(23, 6, 'Address: ', 1, 0,'C' , 1);
$pdf->MultiCell(24, 6, 'Birthdate: ', 1, 0,'C' , 1);
$pdf->MultiCell(26, 6, 'Birthplace: ', 1, 0,'C' , 1);
$pdf->MultiCell(34, 6, 'Telephone No.: ', 1, 0,'C' , 1);
$pdf->MultiCell(30, 6, 'Grade Level: ', 1, 0,'C' , 1);
$pdf->MultiCell(30, 6, 'School Year: ', 1, 0,'C' , 1);
$pdf->MultiCell(23, 6, 'Average: ', 1, 0,'C' , 1);
$pdf->MultiCell(33, 6, 'Fathers Name: ', 1, 0,'C' , 1);
$pdf->MultiCell(34, 6, 'Mothers Name: ', 1, 0,'C' , 1);
$pdf->MultiCell(43, 6, 'Fathers Attainment: ', 1, 0,'C' , 1);
$pdf->MultiCell(44, 6, 'Mothers Attainment: ', 1, 0,'C' , 1);
$pdf->MultiCell(44, 6, 'Fathers Occupation: ', 1, 0,'C' , 1);
$pdf->MultiCell(45, 6, 'Mothers Occupation: ', 1, 0 ,'C', 1);
$pdf->MultiCell(36, 6, 'Parent Address: ', 1, 0,'C' , 1);
$pdf->MultiCell(90, 6, 'Incase of Emergency person to be contacted: ', 1, 0,'C' , 1);
$pdf->MultiCell(34, 6, 'Telephone No.: ', 1, 0,'C' , 1);

$pdf->Ln();
//Select the Products you want to show in your PDF file
$result=mysql_query('SELECT * FROM students') ;

//initialize counter
while($row = mysql_fetch_array($result))
{
    //If the current row is the last one, create new page and print column title
    //$pdf->SetFillColor(0);
	$pdf->SetTextColor(255);
	$pdf->SetFont('Arial', '', 11);
	$student_id = $row['student_id'];
        $lastname = $row['lastname'];
        $firstname = $row['firstname'];
        $middlename = $row['middlename'];
	$sex = $row['sex'];
	$address = $row['address'];
	$birthdate = $row['birthdate'];
	$birthplace = $row['birthplace'];
	$telno = $row['telno'];
	$grade_level = $row['grade_level'];
	$schoolyear = $row['schoolyear'];
	$average = $row['average'];
	$father_name = $row['father_name'];
	$mother_name = $row['mother_name'];
	$father_attainment = $row['father_attainment'];
	$mother_attainment = $row['mother_attainment'];
	$father_occupation = $row['father_occupation'];
	$mother_occupation = $row['mother_occupation'];
	$parents_address = $row['parents_address'];
	$incase_person = $row['incase_person'];
	$incase_number = $row['incase_number'];
$pdf->MultiCell(50,30,60, $lastname, 1, 0, 'C', 1); 
$pdf->MultiCell(27,6, $firstname, 1, 0, 'C', 1);
$pdf->MultiCell(27,6, $middlename, 1, 0, 'C', 1);
$pdf->MultiCell(31,6, $sex, 1, 0, 'C', 1);
$pdf->MultiCell(14,6, $address, 1, 0, 'C', 1);
$pdf->MultiCell(23,6, $birthdate, 1, 0, 'C', 1);
$pdf->MultiCell(24,6, $birthplace, 1, 0, 'C', 1);
$pdf->MultiCell(26,6, $telno, 1, 0, 'C', 1);
$pdf->MultiCell(34,6, $grade_level, 1, 0, 'C', 1);
$pdf->MultiCell(30,6, $schoolyear, 1, 0, 'C', 1);
$pdf->MultiCell(30,6, $average, 1, 0, 'C', 1);
$pdf->MultiCell(23,6, $father_name, 1, 0, 'C', 1);
$pdf->MultiCell(33,6, $mother_name, 1, 0, 'C', 1);
$pdf->MultiCell(44,6, $father_attainment, 1, 0, 'C', 1);
$pdf->MultiCell(44,6, $mother_attainment, 1, 0, 'C', 1);
$pdf->MultiCell(45,6, $parents_address, 1, 0, 'C', 1);
$pdf->MultiCell(90,6, $incase_person, 1, 0, 'C', 1);
$pdf->MultiCell(34,6, $incase_number, 1, 0, 'C', 1);

	$pdf->Ln();
}
//Create file
$pdf->Output('smcm.pdf', 'I'); 
?>