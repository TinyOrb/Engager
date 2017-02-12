var fname;
var lname; 
var country;
var state;
var Amail;
var gender;
var dob;

$(document).ready(function(){
	
	$(".profile_table").hide();
	$(".loading").show();
	// populating country on address Input
	var country_arr = new Array("Afghanistan", "Albania", "Algeria", "American Samoa", "Angola", "Anguilla", "Antartica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Ashmore and Cartier Island", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burma", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Clipperton Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo, Democratic Republic of the", "Congo, Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia", "Cuba", "Cyprus", "Czeck Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Europa Island", "Falkland Islands (Islas Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "French Guiana", "French Polynesia", "French Southern and Antarctic Lands", "Gabon", "Gambia, The", "Gaza Strip", "Georgia", "Germany", "Ghana", "Gibraltar", "Glorioso Islands", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guernsey", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard Island and McDonald Islands", "Holy See (Vatican City)", "Honduras", "Hong Kong", "Howland Island", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Ireland, Northern", "Israel", "Italy", "Jamaica", "Jan Mayen", "Japan", "Jarvis Island", "Jersey", "Johnston Atoll", "Jordan", "Juan de Nova Island", "Kazakhstan", "Kenya", "Kiribati", "Korea, North", "Korea, South", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Man, Isle of", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Midway Islands", "Moldova", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcaim Islands", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romainia", "Russia", "Rwanda", "Saint Helena", "Saint Kitts and Nevis", "Saint Lucia", "Saint Pierre and Miquelon", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Scotland", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and South Sandwich Islands", "Spain", "Spratly Islands", "Sri Lanka", "Sudan", "Suriname", "Svalbard", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Tobago", "Toga", "Tokelau", "Tonga", "Trinidad", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "Uruguay", "USA", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands", "Wales", "Wallis and Futuna", "West Bank", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe");
	htmlstring="<datalist id='country'>";
	
	htmlstring+="<option selected value='No country(Free man)'>";
	
	for(var i =0;i<country_arr.length;i++)
	htmlstring+="<option value='"+country_arr[i]+"'>";
	
	htmlstring+="</datalist>";
	
	//updating html
	$("#country").html(htmlstring);
	
	//fetching user data
	var fjax = $.ajax({
		url:"UserProfileFetcher",
		method:"POST"
	});
	
	fjax.done(function(msg){
		console.log(msg);
		var data = JSON.parse(msg);
			fname = data.fname;
			lname = data.lname;
			country = data.country;
			state = data.state;
			Amail = data.Amail;
			gender = data.gender;
			dob = data.dob;
			
			$("#firstname").val(fname);
			$("#lastname").val(lname);
			$("input[name=country]").val(country);
			$("#state").val(state);
			$("#AlternativeEmail").val(Amail);
			$("input[name=gender]").val(gender);
			$("#DOB").val(dob);
			
			$(".profile_table").show();
			$(".loading").hide();
	});
	
	
	
	//Input element on action
	$("input").prop("disabled",true);
	
	/*$("input").change(function(){
		$("input").attr("disabled", "disabled");
	});*/
	
	
	$("#update_button").hide();
	
	$("#edit_button").click(function(){
		$("input").prop("disabled",false);
		$("#update_button").show();
		$("#edit_button").hide();
	});
	
	$("#update_button").click(function(){
		//Get all profile information
		fname = $("#firstname").val();
		lname = $("#lastname").val();
		country = $("input[name=country]").val();
		state = $("#state").val();
		Amail = $("#AlternativeEmail").val();
		gender = $("input[name=gender]").val();
		dob = $("#DOB").val();
		if(fname != "" || lname !="")
			{
				//Start action to persistence of user profile
				var ajax = $.ajax({
					url:"UserProfileUploader",
					method:"POST",
					data:{fname:fname,lname:lname,country:country,state:state,Amail:Amail,gender:gender,dob:dob}
				});
				
				ajax.done(function(msg){
					
					console.log(msg);
					runEffect(msg);
				});
				$("input").prop("disabled",true);
				$("#edit_button").show();
				$("#update_button").hide();
			}
		else
			{
				runEffect(" First name and Last Name can't empty ");
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

