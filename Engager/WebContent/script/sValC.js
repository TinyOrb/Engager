$(document).ready(function(){
	//alert(cokie);
	
	var ajx = $.ajax({
		//url:"SessionExist",
		url:"ContextS",
		method:"POST",
		data:{},
		async:false
		});
	
		ajx.done(function(msg){
				if(msg != "fail")
					{
						//alert(msg);
						alert("user is "+msg);
					}
				else
					{
					 alert("Incorrect combination");	
					}
			});
	ajx.fail(function(jqXHR, textStatus){
		SendErrorInfo(jqXHR, textStatus);
	});
})