$(document).ready(function(){
	
	var ajx = $.ajax({
		url:"SessionExist",
		method:"POST",
		data:{},
		async:false
		});
	ajx.done(function(msg){
		if(msg != "NoUser")
			{
			 info = msg;
				//alert(msg);
				/*$.jStorage.set("tinyOrb", msg);
				alert($.jStorage.get("tinyOrb"));*/
			$("#act").text(msg);
			}
		else
			{
				window.location.replace("/login.html");
			}
	});
	
	
$("#save").click(function(){
		
		var content = tinyMCE.get('editor-getcontent').getContent();
		var nn = window.location.href.split("?")[1].split("&&")[0].split("=")[1];
		var heading = $("#heading").val();
		var category = "general";
		if(heading != "" && heading.length < 120)
			{
				var ajx2 = $.ajax({
					url:"setArticle",
					method:"POST",
					data:{data:content,head:heading,cat:category,id:nn},
					async:false
				});
				
				ajx2.done(function(msg){
					if(msg == "successful")
						runEffect(" Saved successfully ");
					else
						{
						runEffect(msg);
						}
				});
			}
		else
			runEffect(" For a post, heading is mandatory. Max length heading is 120");
	});



//on publish button

$("#publish").click(function(){
	var nn = window.location.href.split("?")[1].split("&&")[1].split("=")[1];
	var nn2 = window.location.href.split("?")[1].split("&&")[0].split("=")[1];
	var heading = $("#heading").val();
	var category = "general";
	var content = tinyMCE.get('editor-getcontent').getContent();
	var $pjax = $.ajax({
		url:"InitPublish",
		method:"POST",
		data:{data:content,head:heading,cat:category,pid:nn,aid:nn2}/*,
		async:true*/
	});
	$pjax.done(function(msg){
		if(msg != "Something went wrong at html build" && msg != "Something went wrong")
		window.location.replace(msg);
		else
			console.log(msg);
	});
});
	
});



//Messager

function runEffect(message) {
		$("#message").text(message);
		$("div#message").css({'position':'fixed', 'top':'10px', 'left':'40%','color':'red','background':'yellow','padding':'2px 4px 2px 4px'});
		$("#message").fadeIn('fast');
		$("#message").delay(4000).fadeOut('slow');
	}
