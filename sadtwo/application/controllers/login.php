<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Login extends CI_Controller {


	public function __construct(){
        parent::__construct();
        $this->load->model('users', '', TRUE);
        $this->load->helper('form');
    }
	public function index()
	{
		$this->login_here();
	}
	public function login_here(){
		$this->load->view('welcome_message');	
	}
	public function update(){
        $info = array(
                    'user_email' => $this->input->post('email')
                    );
                $this->session->set_userdata($info);
		$info = array(
			'reg_id' => $this->input->post('reg_id'),
			'lastname' => $this->input->post('lastname'),
			'firstname' => $this->input->post('firstname'),
			'email' => $this->input->post('email'),
			'homeaddress' => $this->input->post('homeaddress'),
			'contactnumber' => $this->input->post('contactnumber')
			);
		$this->load->view('edit_user',$info);
	}
	public function update_user(){
		$info = array(
			'reg_id' => $this->input->post('reg_id'),
			'lastname' => $this->input->post('lastname'),
			'firstname' => $this->input->post('firstname'),
			'email' => $this->input->post('email'),
			'homeaddress' => $this->input->post('homeaddress'),
			'contactnumber' => $this->input->post('contactnumber')
			);
        	if($this->users->edit_user() == TRUE){
                $renameResult = rename("uploads/".$this->session->userdata('user_email').".jpg", "uploads/".$this->input->post('email').".jpg");
                if($renameResult){
        		$this->session->set_flashdata('updated', 'Updated Successfully!');
                redirect('login/view_admin');
                }
                else{
                    echo 'cannot';
                }

        	}
	}
    public function add_schedule(){
        $this->users->add_sched();
    }
	public function validation(){
		if($this->input->post('email') == "ronel110396@yahoo.com" && $this->input->post('password') == "ronel110396"){
            	$info = array(
                    'employee' => 1,
                    'email' => $this->input->post('email'),
                    'is_logged_in' => 1
                    );
            	$this->session->set_userdata($info);
                $this->punta();
        }
        else if($this->input->post('email') == "admin@gmail.com" && $this->input->post('password') == "54321"){
        	$info = array(
                    'admin' => 1,
                    'email' => $this->input->post('email'),
                    'is_logged_in' => 1
                    );
            	$this->session->set_userdata($info);
        	$this->view_admin();
        }
        else{
        	$d['error'] = 'Wrong Credentials';
     		$this->load->view('welcome_message',$d);   	
        }
	}
	public function view_admin(){
		        if($this->session->userdata('admin')){
		$qry = "Select * FROM register ORDER BY reg_id DESC";

        	$limit = 5;
            $offset = ($this->uri->segment(3) != '' ? $this->uri->segment(3):0);

            $config['base_url'] = 'http:/sadtwo/login/view_admin';
            $config['total_rows'] = $this->db->get('register')->num_rows();
            $config['per_page'] = 4;
            $config['num_links'] = 5;

            $this->pagination->initialize($config);
            $qry .= " limit {$limit} offset {$offset} ";
            $data['results'] = $this->db->query($qry)->result();
        $this->load->view('view',$data);
    	}
    	else{
    		$d['not_admin'] = 'Login First as Admin';
     		$this->load->view('welcome_message',$d);   
    	}
	}
    public function view_schedules(){
        if($this->session->userdata('admin')){
        $qry = "Select * FROM schedule ORDER BY sched_id";

            $limit = 5;
            $offset = ($this->uri->segment(3) != '' ? $this->uri->segment(3):0);

            $config['base_url'] = 'http:/sadtwo/login/view_schedules';
            $config['total_rows'] = $this->db->get('schedule')->num_rows();
            $config['per_page'] = 4;
            $config['num_links'] = 5;

            $this->pagination->initialize($config);
            $qry .= " limit {$limit} offset {$offset} ";
            $data['results'] = $this->db->query($qry)->result();
        $this->load->view('view_scheds',$data);
        }
        else{
            $d['not_admin'] = 'Login First as Admin';
            $this->load->view('welcome_message',$d);   
        }
    }
	public function punta(){
        if($this->session->userdata('employee')){
		$this->load->view('signup');
        }
        else{
            $d['employee'] = 'Login First as Employee';
            $this->load->view('welcome_message',$d);      
        }
	}
	public function do_login(){
		$info = array();
		if($this->users->can_log_in() == TRUE){
			$info = array('status' => '1');
		}
		else{
			$info = array('status' => '0');
		}
		return print(json_encode($info));
	}
	public function get_user(){
		$result = $this->users->get_user_profile();
			foreach($result as $value){
				$output[]=$value;
			}
		return print(json_encode($output));
	}
	public function register(){
		$this->form_validation->set_rules('firstname','FirstName','required|is_unique[register.firstname]');
        $this->form_validation->set_rules('lastname','LastName','required|is_unique[register.lastname]');
        $this->form_validation->set_rules('contact','Contact Number','required|is_unique[register.contactnumber]');
        $this->form_validation->set_rules('email','Email','required|is_unique[register.email]');
        $this->form_validation->set_rules('bday','Birthday','callback_validate_date');
        $this->form_validation->set_message('is_unique', '%s is already taken');

        
        if($this->form_validation->run() == FALSE){
        	$this->load->view('signup');
        }
        else{
        	        $this->doValidate();

        }
	}
	public function validate_date(){
        $currentdate = date('Y-m-d');
        if($this->input->post('bday') == $currentdate){
        	$this->form_validation->set_message('validate_date', 'Birthday should not be the current date!');
            return false;
        }
        else{
            return true;
        }
    }
    public function doValidate(){
		$config['allowed_types'] = 'jpg|jpeg';
			$this->load->library('upload', $config);
			$this->upload->initialize($config);

        	      $this->form_validation->set_rules('userfile','File','callback_doUpload');
        	    if($this->form_validation->run() == FALSE){
        	    	if(!$this->upload->do_upload('userfile')){
        				$this->load->view('signup');
	        		}
        	    }
	        	else{
        			if($this->users->register_user() == TRUE){
        				$d['add'] = 'Passenger Added';
        				$this->load->view('signup',$d);

        			}
        		}
	}
	public function doUpload(){
    	$type = explode('.',$_FILES["userfile"]["name"]);
		$type = $type[count($type)-1];
		if(in_array($type, array("jpg","jpeg"))){
			if(is_uploaded_file($_FILES["userfile"]["tmp_name"])){
    		move_uploaded_file($_FILES["userfile"]["tmp_name"],"./uploads/".$this->input->post('email').'.'.$type);
			return true;
	    	}	
	    	else{
	    		$this->form_validation->set_message('doUpload','Error Uploading File!!');
	    		return false;
	    	}
		}
		else{
			$this->form_validation->set_message('doUpload','JPEG or JPG are the only allowed types');
			return false;
		}
    	
    }
    public function logout(){
        $d['logout'] = 'You have successfully logout';
        $this->session->sess_destroy();
        $this->load->view('welcome_message',$d);
    }
}
