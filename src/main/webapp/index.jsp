<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
       
       <style>
					 form{
					 align:center;
					 border:1px solid #ccc;
					 border-radius:10px;
					 width:30%;
					 margin:165px auto 0px;
					 padding:20px;
					 box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
					 }
					 button{
					 width: 50%;
					 }
					 
       </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	 <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
	 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
         
    </head>
    
    <body>
    
    <script type="text/javascript">
    
    function validateData(){
    	var data={};
    	data.userid=$("#userid").val();
    	data.password=$("#password").val();
    	
    $.ajax({
    	type: "POST",
    	 url: "http://localhost:8081/CRUDAPI/rest/student-mgmt/student/validate",
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        dataType: "json", 	
        crossOrigin: true,
        contentType: 'application/json'
    })
    
        .done(function (data,status) {  
        	if(data=="OK"){
        		window.location.href="display.html";	
        	}
        	else{
        		alert("Entered Username or Password is Incorrect");
        		window.location.href="index.jsp";
        		
        	}
    }).fail(function (xhr, textStatus, error) {
        debugger;
    }); 
    	}
    
    	</script>
	   	<div class="container"></div>
        <center>
        <form>
        <fieldset>
        <h1 align="center" class="text-primary">Login Form</h1><br/>
        <input type="text" id="userid" placeholder="Type your Name" required><br/><br/>
        <input type="password" id="password" placeholder="Type your Password" required><br/><br/>
		<button type="button" class="btn btn-primary"  onclick="validateData()" value="Login">Login</button><br/><br>
		<button type="button" class="btn btn-primary"  onclick="window.location='Register.html'">SignUp</button>      
        </fieldset>
        </form>
        
</body>
</html>