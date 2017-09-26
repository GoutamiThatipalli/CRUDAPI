<!DOCTYPE html>
<html>
    <head>
        <title>Profile</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
        			
					 table{ 
    				 border-radius:10px;
					 padding:20px;
					 box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
					 }
        </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
    <script>
    
    jQuery(document).ready(function(){          
		    $.ajax({
		    	type: "GET",
		    	url: "http://localhost:8081/CRUDAPI/rest/student-mgmt/student/profile",
		        dataType: "json", 	
		        crossOrigin: true,
		        contentType: 'application/json'
			    }).done(function (data,status) {
			    	
			  		var str = '';
		
	            	str += '<table class="table table-bordered table-striped">';
	            	str += '<thead>';
	            	str += '<tr align=center class="active" ><th>Username</th>';
	            	str += '<td><input type="text" class="formfield" data-key="userid" value="'+data.userid+'" disabled /></td></tr>';
	            	str += '<tr align=center class="active" ><th>Password</th>';
	            	str += '<td><input type="password" class="formfield" data-key="password" value="'+data.password+'" disabled/></td></tr>';
	            	str += '<tr align=center class="active" ><th>Mobileno</th>';
	            	str += '<td><input type="text" class="formfield" data-key="mobileno" value="'+data.mobileno+'" disabled /></td></tr>';
	            	str += '<tr align=center class="active" ><th>Email</th>';
	            	str += '<td><input type="text" class="formfield" data-key="email" value="'+data.email+'" disabled /></td></tr>';
	            	str += '<tr align=center class="active" ><th>City</th>';
	            	str += '<td><input type="text" class="formfield" data-key="city" value="'+data.city+'" disabled /></td></tr>';
	            	str += '<tr align=center class="active" ><th>Pincode</th>';
	            	str += '<td><input type="text" class="formfield" data-key="pincode" value="'+data.pincode+'" disabled /></td></tr>';
	            	str += '</thead>';	
	            	str += '<tbody>';
            		str += '</tbody>'
            		$('#profile').html(str);
			    }).fail(function (xhr, textStatus, error) {
			    
			    }); 
		   	return false;
        }); 
   </script>        
   <div class="container"><h1 class="text-primary" style="text-align: center;">Profile</h1></div>
   <div class="container" id="profile" style="width: 560px">
        </div>
    </body>
</html>
