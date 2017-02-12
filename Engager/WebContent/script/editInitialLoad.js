$(document).ready(function(){
	var nn = window.location.href.split("?")[1].split("&&")[0].split("=")[1];
	if(nn != "new")
		{
			var ejax = $.ajax({
				url:"fetchArticle",
				method:"POST",
				data:{x1:nn},
				async:false
			});
			
			ejax.done(function(msg){
				console.log(msg);
				try{
				var message = JSON.parse(msg);
				
				/* checking receved data as expected
				for(var e in msg)
				console.log(e + " " +msg[e]);*/
				
				//console.log(message.heading + " " + message.data);
				
				// waiting all dom elements to get loaded
				
					$("#heading").val(message.heading);
					//tinyMCE.get('editor-getcontent').setContent(message.data);
					$("textarea").val(message.data);
				}
				catch(e)
				{
					window.location.replace("/denied.html");
					//console.log(e);
				}
				//tinyMCE.get('editor-getcontent').setContent('<p>hear is the point</p> html');
			});
		
		}
	else{
		var ejax = $.ajax({
			url:"InitArticle",
			method:"POST",
			async:false
		});
		ejax.done(function(msg){
			if(msg != "fail")
			window.location.replace("/EditorPage.html?id="+msg+"&&PID=-1");
		});
	}
	
});