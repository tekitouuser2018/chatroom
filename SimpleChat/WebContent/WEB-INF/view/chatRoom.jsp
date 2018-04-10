<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Chat Room</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="ここにサイト説明を入れます">
<meta name="keywords" content="キーワード１,キーワード２,キーワード３,キーワード４,キーワード５">
<link rel="stylesheet" href="./css/room.css">

<script type="text/javascript" src="./js/openclose.js"></script>
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>


<script type ="text/javascript">

var today = new Date();
var month = today.getMonth()+1;
var setDay = today.getFullYear()+"/"+month+"/"+today.getDate()
					+"/"+today.getHours()+":"+today.getMinutes()+":"+today.getSeconds();

$(function(){
	$("#date").text(setDay);




$("#submit").click(function(){
	var text = $("#chattext").val();
	var name = $("#name").text();
	//console.log(text+":"+name);

	getCurrentTime();

	var input ={
			Name : name,
			Text : text,
			Time: setDay,
			Flag:"all"
	};


	$.ajax({

		type:"post",
		url:"./chat",
		//dataType:"json",
		data : input,
		async: true,


		success: function(data){
	      // 成功したとき
	      // data にサーバーから返された html が入る
	      console.log("ajax successed!!");
		  var str = newTableBody(data);
	      $('.ta1').append(str);


	      //console.log(data.length);
	      //console.log(data.hoge);
			//gotRequestSuccessfully = true;
            //if(data.length != 0){// データが存在するとき

               // for(var i = 0; i < data.length; i++){
                   // var str = newTableBody(data[i]);
                    //console.log(str);
                    //$('.ta1').append(str);
                //}
            //}

	    },
	    error: function(xhr, textStatus, errorThrown){
	      // エラー処理
	      alert("Error in ajax function!!");
	    }
	});

});


$("#mychat").click(function(){
	var text = $("#chattext").val();
	var name = $("#name").text();
	console.log("myChat is clicked...");

	getCurrentTime();

	var input ={
			Name : name,
			Text : text,
			Time: setDay,
			Flag:"mine"
	};


	$.ajax({

		type:"post",
		url:"./chat",
		//dataType:"json",
		data : input,
		async: true,


		success: function(data){
	      // 成功したとき
	      // data にサーバーから返された html が入る
	      console.log("myChat function successed!!");
	      //console.log(data);
	      console.log(data.length);
	      $(".ta1").html("<table class=\"ta1\"></table>");
	      $.each(data,function(){
			  var str = newTableBody(this);
		      $('.ta1').append(str);
		});

	    },
	    error: function(xhr, textStatus, errorThrown){
	      // エラー処理
	      alert("Error in ajax function!!");
	    }
	});

});

$("#mychatPC").click(function(){
	var text = $("#chattext").val();
	var name = $("#name").text();
	console.log("myChat is clicked...");

	getCurrentTime();

	var input ={
			Name : name,
			Text : text,
			Time: setDay,
			Flag:"mine"
	};


	$.ajax({

		type:"post",
		url:"./chat",
		//dataType:"json",
		data : input,
		async: true,


		success: function(data){
	      // 成功したとき
	      // data にサーバーから返された html が入る
	      console.log("myChat function successed!!");
	      //console.log(data);
	      console.log(data.length);
	      $(".ta1").html("<table class=\"ta1\"></table>");
	      $.each(data,function(){
			  var str = newTableBody(this);
		      $('.ta1').append(str);
		});

	    },
	    error: function(xhr, textStatus, errorThrown){
	      // エラー処理
	      alert("Error in ajax function!!");
	    }
	});

});

function newTableBody(data){
	var strHead ="<tr><th>"+data.name+":"+data.time+"</th>";
	var str = strHead
					+"<td>"+data.text+"</td>"
					+"</tr>";
	return str;
}

function getCurrentTime(){
	today = new Date();
	month = today.getMonth()+1;
	setDay = today.getFullYear()+"/"+month+"/"+today.getDate()
	+"/"+today.getHours()+":"+today.getMinutes()+":"+today.getSeconds();
}

});
</script>

</head>

<body>

<!--小さな端末用（800px以下端末）のロゴとメニュー

<h1 class="logo"><a href="./chat"><img src="images/logo_s.png" alt="Chat Room"></a></h1>
-->
<div id="sh">
<hr>
<p class="roomlogo"><font color="white" size="4">Chat Room</font></p>
<hr>
<nav id="menubar-s">
<ul>
<li><a href="./chat">AllChat</a></li>
<li><a id="mychat">MyChat</a></li>
<li><a href="./login">LogOut</a></li>
</ul>
</nav>
</div>

<div id="container">

<div id="main">

<section>

<h3>ユーザー名：<b id="name"><%= (String)session.getAttribute("name") %></b></h3>
<h2><b>Chat</b></h2>
<p id="date"></p>

<!--
<table class="ta1">
<tr>
<th colspan="2" class="tamidashi">見出しが必要であればここを使います</th>
</tr>
<tr>
<th><a href="#">サイト名</a></th>
<td>ここに説明など入れて下さい。サンプルテキスト。</td>
</tr>
<tr>
<th><a href="#">サイト名</a></th>
<td>ここに説明など入れて下さい。サンプルテキスト。</td>
</tr>
<tr>
<th><a href="#">サイト名</a></th>
<td>ここに説明など入れて下さい。サンプルテキスト。</td>
</tr>
<tr>
<th><a href="#">サイト名</a></th>
<td>ここに説明など入れて下さい。サンプルテキスト。</td>
</tr>
<tr>

</tr>
</table>
-->

	<input type ="text" id="chattext" style="width:500px;">
	<button  id="submit">書き込む</button>


<table class="ta1">

<!--
<tr>
<th><a href="#">ユーザー名：0000/00/00/00:00:00</a></th>
<td>これはテンプレートです。サンプルテキスト。ここの文章が長くなった場合の折り返しはどうなるのかなああああああああああああああああああ</td>
</tr>
 -->

<c:forEach var ="chat" items="${allChat}">
	<tr>
		<th>${chat.name}:${chat.date }</th>
		<td>${chat.text}</td>
	</tr>
</c:forEach>

</table>

</section>

</div>
<!--/main-->

<div id="sub">

<!--PC用（801px以上端末）ロゴ
<h1 class="logo"><a href="./chat"><img src="./images/logo.png" alt="Chat Room"></a></h1>
-->
<p class="roomlogo" align="center"><font color="white" size="4" ><b>Chat Room</b></font></p>
<hr>
<!--PC用（801px以上端末）メニュー-->
<nav id="menubar">
<ul>
<li><a href="./chat">AllChat</a></li>
<li><a id="mychatPC">MyChat</a></li>
<li><a href="./login">LogOut</a></li>

</ul>
</nav>

</div>
<!--/sub-->

<p id="pagetop"><a href="#">↑ PAGE TOP</a></p>

<footer>
<small>Copyright&copy; <a href="./chat">Sample Web Site</a> All Rights Reserved.</small>
<span class="pr"><a href="http://template-party.com/" target="_blank">《Web Design:Template-Party》</a></span>
</footer>

</div>
<!--/container-->

<!--画面左上の装飾画像-->
<img src="./images/bg1.png" id="kazari">

<!--lightbox用jsファイル読み込み
<script src="js/lightbox-plus-jquery.min.js"></script>
-->

<!--メニューの３本バー-->
<div id="menubar_hdr" class="close"><span></span><span></span><span></span></div>
<!--メニューの開閉処理条件設定　800px以下-->
<script type="text/javascript">
if (OCwindowWidth() <= 800) {
	open_close("menubar_hdr", "menubar-s");
}
</script>
</body>
</html>
