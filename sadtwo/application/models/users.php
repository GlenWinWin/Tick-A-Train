<?php

class Users extends CI_Model{
	function can_log_in(){
    
        $sql = "SELECT * FROM register WHERE email = ? AND password = ?"; 

        $query = $this->db->query($sql, array($this->input->get('EmailAddress',TRUE), $this->input->get('Password',TRUE)));

        if($query->num_rows == 1){
            return true;
        }
        else{
            return false;
        }
        return $query->result();
    }
    function get_user_profile(){
    	$sql = "SELECT * FROM register WHERE email = ?";
        $query = $this->db->query($sql,array($this->input->get('EmailAddress',TRUE)));
        return $query->result();
    }
    function add_sched(){

        $data = array(
            'date' => $this->input->get('dateYear',TRUE) . '-' .$this->input->get('dateMonth',TRUE).'-'.$this->input->get('dateDay',TRUE),
            'time' => $this->input->get('timeHour',TRUE) . ':' .$this->input->get('timeMinute',TRUE) . ' ' . $this->input->get('timeAMPM',TRUE),
            'to' => $this->input->get('to'),
            'from' => $this->input->get('from',TRUE),
            'email' => $this->input->get('email',TRUE),
            'what_lrt' => 'LRT ' . $this->input->get('lrt',TRUE),
            'lane' => 'Lane ' . $this->input->get('lane',TRUE)
            );
        $this->db->insert('schedule',$data);

    }
    function edit_user(){
        $data = array(
            'lastname' => $this->input->post('lastname'),
            'firstname' => $this->input->post('firstname'),
            'contactnumber' => $this->input->post('contactnumber'),
            'email' => $this->input->post('email'),
            'homeaddress' => $this->input->post('homeaddress')
            );
        $this->db->where('reg_id', $this->input->post('reg_id'));
        $this->db->update('register', $data);
        return true;
    }
    function register_user(){
        $data = array(
            'lastname' => $this->input->post('lastname'),
            'firstname' => $this->input->post('firstname'),
            'contactnumber' => $this->input->post('contact'),
            'email' => $this->input->post('email'),
            'homeaddress' => $this->input->post('home'),
            'gender' => $this->input->post('gender'),
            'password' => $this->input->post('password'),
            'birthday' => $this->input->post('bday')

            );
        $this->db->insert('register',$data);

        return true;
    }
}