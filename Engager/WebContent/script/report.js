var type = [];
var title = [];
var description = [];
var id = [];
var date = [];
var status = [];

$(document).ready(function(){
	
	//loading query list
	var ljax = $.ajax({
		url:"ListQuiryReport",
		method:"POST"
	});
	ljax.done(function(msg){
		//console.log(msg);
		if(msg != "No data")
			{
				var data = JSON.parse(msg);
				var htmlstring = "<table class='report_table'>";
				htmlstring+="<tr><th>Title</th><th>Date</th><th>Reopen date</th> <th>Closed date</th><th>Status</th><th style='text-align:center';}>Action</th></tr>";
				for(var i = 0; i < data.length;i++)
				{
					htmlstring+="<tr>";
					htmlstring+="<td>" +data[i].getTitle+"</td>";
					htmlstring+="<td>" +data[i].getOpendate+"</td>";
					htmlstring+="<td>" +(data[i].getReopendate != "" ? data[i].getReopendate : "NA")+"</td>";
					htmlstring+="<td>" +(data[i].getCloseddate != "" ? data[i].getCloseddate : "NA")+"</td>";
					htmlstring+="<td>";
					if(data[i].getOpenstatus == "true")
						{
							htmlstring+="Open";
						}
					
					else
						{
						htmlstring+="Closed";
						}
					htmlstring+="</td>";
					
					//Constructing button
					htmlstring+="<td>";
					var k1 ='<button onclick="popup_ticket('+ data[i].getId+',\''+ data[i].getTitle +'\',\''+data[i].getOpenstatus+'\',\''+data[i].getDescription+'\')">';
					console.log(k1);
					htmlstring+=k1 + "view"+"</button></td>";
					
					htmlstring+="</tr>";
				}
				htmlstring+="</table>";
				$("#report_list").html(htmlstring);
			}
	});
	
	//sending quiry 
	$("#submitquery").click(function(){
		var title_1 = $("#issue_title").val().split("'").join("");
		alert(title_1);
		var type_1 = $("input[name=Inquiry]").val().split("'").join("");
		var description_1 = $("#issue_description").val().split("'").join("");
		
		if(title_1 != "" && type_1 !="" && description_1 != "")
			{
				var ajax = $.ajax({
					url:"SendQuiryReport",
					method:"POST",
					data:{title_l:title_1,type_l:type_1,description_l:description_1}
				});
				ajax.done(function(msg){
					console.log(msg);
					runEffect(" Ticket has been created, reload to check ");
				});
			}
		else
			{
			runEffect(" All field must be field ");
			}
		
	});
	
});

//Messager

function runEffect(message) {
		$("#message").text(message);
		$("div#message").css({'position':'fixed', 'top':'10px', 'left':'40%','color':'red','background':'yellow','padding':'2px 4px 2px 4px','z-index':100,'font-weight':'bold'});
		$("#message").fadeIn('fast');
		$("#message").delay(4000).fadeOut('slow');
	}


// launching windows

function popup_ticket(id, title, status, description)
{
	//console.log(id +" , "+title +" , "+status+ " , "+ description);
	
	// Making pop up ticket window visible
	$(".popup_ticket_windows").show();
	
	//building css for element of pop up ticket window
	$(".popup_ticket_windows").css({'position':'fixed','height':"60%",'width':'50%','top':'25%','left':'25%','background':'grey','z-index':100});
	
	
	//Cresting and adding element on pop up ticket windows
	var htmlstring = "<div class='popup_ticket_window_titlebar'><b>Ticket window</b></div>";
	htmlstring += "<div class='popup_ticket_window_Mainbody'><b>Title : </b>"+ title +"<br><br>" +
			"<fieldset><legend>Description</legend>"+ description +"</fieldset></div>";
	htmlstring += "<div class='popup_ticket_window_commentarea'><fieldset><legend>Comment</legend>" +
			"<textarea id='ticket_comment'></textarea></fieldset></div>";
	if(status == 'true')
		{
		htmlstring += "<div class='popup_ticket_window_buttonarea'><button id='send_comment'>Add comment</button>" +
		"<button id='close_ticket'>Close ticket</button>" +
		"<button id='popup_window_close_button'>Close window</button></div>";
		}
	else
		{
		htmlstring += "<div class='popup_ticket_window_buttonarea'>" +
		"<button id='reopen_ticket'>Reopen ticket</button>" +
		"<button id='popup_window_close_button'>Close window</button></div>";
		}
	
	//Adding html string in pop up window div
	$(".popup_ticket_windows").html(htmlstring);
	
	//Adding css to element after element come into existence
	$(".popup_ticket_window_titlebar").css({'position':'absolute','height':"8%",'width':'92%','top':'0','left':'0','background':'#bcbcbc','z-index':101,'padding':'1%  4% 1% 4%'});
	$(".popup_ticket_window_Mainbody").css({'position':'absolute','height':"45%",'width':'92%','top':'10%','left':'0','background':'lightgrey','z-index':101,'padding':'1%  4% 1% 4%','overflow':'auto'});
	$(".popup_ticket_window_commentarea").css({'position':'absolute','height':"25%",'width':'92%','top':'57%','left':'0','background':'lightgrey','z-index':101,'padding':'1%  4% 1% 4%'});
	$(".popup_ticket_window_buttonarea").css({'position':'absolute','height':"12%",'width':'92%','top':'84%','left':'0','background':'lightgrey','z-index':101,'padding':'1%  4% 1% 4%'});
	$("#ticket_comment").css({'height':"90%",'width':'90%'});
	
	//implement action on button
	$("#popup_window_close_button").click(function(){
		$(".popup_ticket_windows").hide();
	})
	
	$("#send_comment").click(function(){
		var comment = $("#ticket_comment").val().split("'").join("");
		if(comment != "")
			{
				var ujax = $.ajax({
					url:"OtherQuiryOperation",
					method:"POST",
					data:{opcode:1,id:id,comment:comment},
					async:true
				});
				ujax.done(function(msg){
					runEffect(" "+msg+" ");
				});
			}
		else{
			runEffect(" Need some comment ");
			console.log(msg);
		}
	});
	
	$("#close_ticket").click(function(){
		var comment = $("#ticket_comment").val().split("'").join("");;
		
		var ujax = $.ajax({
			url:"OtherQuiryOperation",
			method:"POST",
			data:{opcode:2,id:id,comment:comment},
			async:true
		});
		ujax.done(function(msg){
			runEffect(" "+msg+" ");
			console.log(msg);
		});

		$(".popup_ticket_windows").hide();
	});
	
	$("#reopen_ticket").click(function(){
		var comment = $("#ticket_comment").val().split("'").join("");
		
		var ujax = $.ajax({
			url:"OtherQuiryOperation",
			method:"POST",
			data:{opcode:3,id:id,comment:comment},
			async:true
		});
		ujax.done(function(msg){
			runEffect(" "+msg+" ");
			console.log(msg);
		});
		
		$(".popup_ticket_windows").hide();
	});
}