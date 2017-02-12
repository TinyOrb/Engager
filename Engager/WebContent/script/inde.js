$(document).ready(function(){

	$("#submit").click(function(){
		var email = $("#email").val();
		if(validateEmail(email))
			{
				//alert("correct mail");
				
				$("#welcome_input").hide();
				$(".home_content").css({"opacity":"1"});
				var ajx = $.ajax({
					url:"UserInfo",
					method:"POST",
					data:{mail:email},
					async:false
					});
				ajx.done(function(msg){
					if(msg = "success")
						{
						//alert("vola successfully email");
						}
					
				});
				
				ajx.fail(function(jqXHR, textStatus){
					SendErrorInfo(jqXHR, textStatus);
				});
			}
		else
			{
				alert("Incorrect mail");
				e.preventDefault();
			}
	});
	
	$("#anyway").click(function(){
		$("#welcome_input").hide();
		$(".home_content").css({"opacity":"1"});
	});
	
});

function validateEmail(sEmail) {
    var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    if (filter.test(sEmail)) {
        return true;
    }
    else {
        return false;
    }
}

function SendErrorInfo(jqXHR, textStatus) {
	
}