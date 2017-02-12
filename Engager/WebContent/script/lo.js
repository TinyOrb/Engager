$(document).ready(function(){
	
	var ajx = $.ajax({
		url:"ExitSession",
		method:"POST",
		data:{},
		async:false
	});
	ajx.done(function(msg){
		if(msg == "success")
			window.location.replace("/Index.html");
		else
			window.location.replace("/Index.html");
	});
	
})