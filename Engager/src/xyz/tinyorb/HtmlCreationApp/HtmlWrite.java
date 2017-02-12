package xyz.tinyorb.HtmlCreationApp;

import java.sql.Date;

public class HtmlWrite {
	
	public String write(String Author, String fname, String lname, String Data, Date date, String heading, Integer id)
	{
		String strReturn = null;
		
		strReturn = "<!DOCTYPE html><html>";
		
		//starting head
		strReturn += "<head>";
		
		// Adding title
		strReturn += "<title>";
		strReturn += heading;
		strReturn += "</title>";
		
		//Style
		strReturn += "<style>";
		
		//Theme 1
		strReturn += "body, html{  	margin:0;  	width:100%;  	height:100%;  	min-height:768px;  }    /*---------Header start here -------------*/  div#header{  	position:absolute;  	top:0px;  	left:0px;  	width:100%;  	height:10%;  	background:black;  }    img#logo{  	position:absolute;  	top:0px;  	left:2px;  	height:100%;  	width:18%;  	z-index:5;  	cursor:pointer;  }  img#logo:hover{  	z-index:15;  }  div#loggerinfo{  	position:absolute;  	top:15px;  	right:40px;  	height:40%;  	width:100px;  	background:white;  	z-index:5;  	font-weight:bold;  	text-align:center;  	padding: 5px 10px 2px 10px;  	cursor:pointer;  }  /*---------Header ends here -------------*/    /*---------Userinfo start here -------------*/  #userinfo{  	position:absolute;  	top:10%;  	left:0px;  	height:80%;  	width:20%;  	background:#eaeaea;  }    #AuthorLabel{  	position:absolute;  	top:18%;  	horizontal-align:center;  	width:100%;  	font-weight:bold;  	text-align:center;  }    div#AuthorImage{  	position:absolute;  	top:30%;      width: 100%;      height:30%;  }  #AuthorImageDiv{  	margin:auto;  	border: 2px solid #6cd900;      background: #b6ff6c;      width: 150px;      height: 150px;      border-radius: 75px;  }    #AuthorName{  	position:absolute;  	top:60%;  	horizontal-align:center;  	width:100%;  	font-weight:bold;  	text-align:center;  	color:#400080;  }    /*---------Userinfo end here -------------*/    /*---------Maincontent start here -------------*/    #maincontent{  	position:absolute;  	top:10%;  	left:22%;  	width:50%;  	height:90%;  }    /*---------Maincontent end here -------------*/    #otherlink{  	position:absolute;  	top:10%;  	right:0px;  margin:4%;  height:70%;  	width:20%;  	border-style:solid;  	border-width:2px;  	border-radius:5px;  	border-color:blue;  }";
		strReturn += "</style>";
		
		//Script
		strReturn += "<script>";
		strReturn += "var pid = "+id +";";
		strReturn += "</script>";
		strReturn += "<link rel='shortcut icon' type='image/png' href='http://localhost:8080//image/TinyOrb-ico-logo.png'/>";
		strReturn += "</head>";

		//starting body
		strReturn += "<body>";		
		
		strReturn +="<div id='header'>";
		strReturn +="<img src='http://localhost:8080//logo.png' id='logo'/>";
		strReturn +="<div id='loggerinfo'>";
		strReturn +="Hi, User";
		strReturn +="</div>";
		strReturn +="</div>";

		strReturn +="<div id='userinfo'>";
		strReturn +="<div id='AuthorLabel'><h2>Author</h2></div>";
		strReturn +="<div id='AuthorImage'>";
		strReturn +="<div id='AuthorImageDiv'></div>";
		strReturn +="</div>";
		if(lname!=null && fname != null)
		{
			strReturn +="<div id='AuthorName'><h3>"+fname + " " + lname+"</h3></div>";
		}
		else if(fname!=null)
		{
			strReturn +="<div id='AuthorName'><h3>"+fname+"</h3></div>";
		}
		else{
			strReturn +="<div id='AuthorName'><h3>"+Author+"</h3></div>";
		}
		strReturn +="</div>";

		strReturn +="<div id='maincontent'>";
		strReturn +="<div id='heading' align='center'><h1>"+heading+"</h1></div>";
		strReturn +="<div id='dateofpost'><h4>Date: "+date+"</h4></div>";
		strReturn +="<div id='content' align='justify'>"+Data+"</div>";
		strReturn +="</div>";

		strReturn +="<div id='otherlink'></div>";
		strReturn += "</body>";
		
		strReturn += "</html>";
		
		return strReturn;
	}
	
}
