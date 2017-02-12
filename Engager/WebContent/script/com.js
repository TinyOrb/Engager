$(document).ready(function(){
	//clear all session	
	/*var ajx = $.ajax({
		url:"ExitSession",
		method:"POST",
		data:{},
		async:false
	});
	ajx.done(function(msg){
		;
	});*/
	
	//Sign in with ajax function
	$("#bt1").click(function(){
		var r1 = $("#r1").val();
		var r2 = $("#r2").val();
		var r3 = $("#r3").val();
		var r4 = $("#r4").val();
		//alert(r1+ " " + r2 + " "+r3+" "+r4);
		if(r1 != "" && r2 !="" && r3 !="" && r4!= "")
			{
				if(validate(r1))
					{
						if(r3 == r4 && r3 != null)
							{
								//alert(" All ok");
								/* if(r2 already exist)
								 * 
								 */
							var ajx = $.ajax({
								url:"NewCandi",
								method:"POST",
								data:{x1:r2, x2:r1, x3: r3},
								async:false
								});
							ajx.done(function(msg){
								if(msg == "already exist")
									{
										//alert(msg);
										/*$.jStorage.set("tinyOrb", msg);
										alert($.jStorage.get("tinyOrb"));*/
									$("#errormsg").text("username or email already exist");
									}
								else
									{
									if(msg == "successful")
										{
										$("#errormsg").text("successfully created");
										window.location.replace("/Profile.html");
										}
									else
										{
										$("#errormsg").text("unknown error");
										}
									}
							});
								
							}
						else
							{
							$("#errormsg").text("Password in both password field must be same and not blank");
								//alert("Password in both password field must be same");
							}
					}
				else
					{
						$("#errormsg").text("Invalid Email");
						//alert("Email is wrong");
					}
			}
		else
			{
			$("#errormsg").text("All fields are mandatory");
			//alert();
			}
	});
	
	
	//Login function
	$("#bt2").click(function(){
		var i1 = $("#i1").val();
		var i2 = $("#i2").val();
		if(i1 != "" && i2 != "")
			{
				var ajx = $.ajax({
				url:"Auth0",
				method:"POST",
				data:{x1:i1, x2:i2},
				async:false
				});
			ajx.done(function(msg){
				if(msg != "")
					{
						window.location.replace("/DashBoard.html");
					}
				else
					{
						$("#errormsg2").text("Incorrect combination");
					}
			});
			
			ajx.fail(function(jqXHR, textStatus){
				SendErrorInfo(jqXHR, textStatus);
			});
			}
		else
			{
				alert("All fields are mandatory");
			}
	});
});

function validate(sEmail)
{
	var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    if (filter.test(sEmail)) {
        return true;
    }
    else {
        return false;
    }
}