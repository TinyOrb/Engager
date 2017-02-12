$(document).ready(function(){
	
	var loadPage = $.ajax({
		url:"InitialData",
		method:"POST",
		//async:false
	});
	
	loadPage.done(function(msg){
		//alert(msg);
		if(msg != "")
			{
				var data = [];
				//parse json array in object. Be carefully msg response all key and 
				//value pair must be string within double quote
				data = JSON.parse(msg);
				var htmlStr="";
				for(var inx = 0;inx < data.length;inx++)
					{
					if(data[inx].getHeading != "")
						{
							var id = data[inx].getIdArticle; 
							htmlStr +="<div class='graybox' id=c"+id+"><h3>" ;
							//console.log(data[inx]);
							// seprating object into fields
							for(var e in data[inx])
								{
									
										console.log(e + " " +data[inx][e]);
								}
							
							htmlStr+=data[inx].getHeading;
							htmlStr+="</h3>";
							htmlStr+="<div><a class='edit' href='EditorPage.html?id="+id+ "&&PID="+data[inx].getPID+"' target='_blank'>";
							htmlStr+=		"Edit";
							htmlStr+=	"</a></div>";
							htmlStr+=	"<div class='delete' onclick='gotCallDelete("+id+")'>";
							htmlStr+=		"Delete";
							htmlStr+=	"</div>";
							htmlStr+=	"<div class='view'>";
							htmlStr+=		"View";
							htmlStr+=	"</div>";
							if(data[inx].getPID != -1)
								{
									htmlStr+="<div><a class='publish' href='/jeera/"+data[inx].getAuthor+"/"+data[inx].getPID+".html"+"' target='_blank'>";
									htmlStr+=		"Publish";
									htmlStr+=	"</a></div>";
								}
							htmlStr+="</div>";
						}
						
					}
				$("div#hbody").append(htmlStr);
			}
		else
			{
			
			}
	});
});

function gotCallDelete(id)
{
	//alert(id);
	$("#c"+id).hide();
	var njax = $.ajax({
		url:"removeArticle",
		method:"POST",
		data:{x1:id},
		async:false
	});
	
	njax.done(function(msg){
		if(msg == "success")
			{
			runEffect("Post is successfully deleted")
			}
	});
}


function runEffect(message) {
	$("#message").text(message);
	$("div#message").css({'position':'fixed', 'top':'10px', 'left':'40%','color':'red','background':'yellow','padding':'2px 4px 2px 4px'});
	$("#message").fadeIn('fast');
	$("#message").delay(4000).fadeOut('slow');
}