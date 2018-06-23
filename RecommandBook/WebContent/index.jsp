<%@page import="javafx.scene.control.Alert"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="./WEB-INF/myTagLib.tld" prefix="myTag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>



<link rel="shortcut icon" href="http://www.yzipi.com/wp-content/themes/yzipi/images/favicon.ico">
<link rel="stylesheet" id="sytle-css" href="./css/style.css" type="text/css" media="all">
<link rel="stylesheet" id="yzipi-pc-css" href="./css/yzipi-pc.css" type="text/css" media="all">
<link rel="stylesheet" id="yzipi-phone-css" href="./css/yzipi-phone.css" type="text/css" media="all">
<script type="text/javascript" src="./js/selectivizr-min.js"></script>
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/smohan.fixednav.js"></script>
<link rel="stylesheet prefetch" href="http://puertokhalid.com/up/demos/puerto-Mega_Menu/css/normalize.css">
<link rel="stylesheet" href="./css/menu.css" media="screen" type="text/css">

<script type="text/javascript">

$(function(){ 
    $('#getMore').click(function(){
    	var page = parseInt($("#page").val());  
        $.ajax({
            type: 'get',
            url: '/RecommandBook/book',
            data: "page=" + page,
            dataType: 'json',
            beforeSend:function(){
                $('#loadingMask').show();
                $('#getMore').hide();
            },
            success: function(data) {
                $('#loadingMask').hide();
                $('#getMore').show();
                if(data.length > 0){
                	var html = '';  
                	var list = $("#list");
                    for(var i = 0; i < data.length; i++){
                       // 这里是插入数据的操作
                    	  html +="<div>"+data[i].title+"</div>"+"<div>"+data[i].date+"</div>"  
                    
                    	 
                          html += '<span class="titleimg"> <a href="booksDetail.book?id='+ data[i].id + '"target="_blank">';
                          html += '<img src="' + data[i].img_url +'" width="136px" height="190px"> </a> </span>';                   
                          html += '<div class="book_content">'
                          html += '<div class=" mecc">'
                          html += '<h2 class="mecctitle"> <a href="booksDetail.book?id=' + data[i].id + '" target="_blank">'+ data[i].book_name + ' </a> </h2>';
                          html += '<time>'+data[i].add_time + '荐购次数' + data[i].recommand_times +'</time>';
                          html += '<c:if test="'+ data[i].recommand_num_tercher + '!= 0"><a class="teach-recom">教师推荐</a></c:if>';
                          html += '</div>'
                          html += '<div class=" zaiyao" >'
                          html += '<p id="brief" style="display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 3;overflow: hidden;">' +data[i].book_brief+ '</p></div></div>';
                    }
                    list.append(html); 
                    if(data.length < 6){
                        $('#getMore').hide();
                        $('#noMore').show();
                    }
                    else{
                    	 $("#page").val(page + 1);//记录页码 
                    }
                } 
            },
            error: function(data) {
                $('#loadingMask').hide();
                $('#getMore').show();
            }
        });            
    });
})
</script>

</head>
<body>
<jsp:include page="./include/head.jsp"></jsp:include>
<div id="container-page">
   <jsp:include page="./include/headleft.jsp"></jsp:include>
  <article class="box">
      <div class="book booklist bookku">
          <div class="right">
      <div class="top">
          <div class="title"><h3>今日推荐</h3><span><a href="bookhistory.html">查看历史>></a></span></div>
      </div>

 <c:forEach items="${bookList}" var="book">
 <section class="list">
       <span class="titleimg"> <a href="booksDetail.book?id=${book.id}" target="_blank">
  		<img src="${book.img_url}" width="136px" height="190px"> </a> </span>
         
              <div class="book_content">
              <div class=" mecc">

                  <h2 class="mecctitle"> <a href="booksDetail.book?id=${book.id}" target="_blank">
                      ${book.book_name}  </a> </h2>
                  <time>
                      ${book.add_time}	   -
                     	 荐购次数
                      ${book.recommand_times}
                  </time>
                  <c:if test="${book.recommand_num_tercher != 0}">
                                     <a class="teach-recom">
                      	教师推荐
                  </a>
                  </c:if>

              </div>

              <div class=" zaiyao" >
              <p id="brief" style="display: -webkit-box;
-webkit-box-orient: vertical;
-webkit-line-clamp: 3;
overflow: hidden;">${book.book_brief}</p>
                 
              </div>
              </div>
          </section>
 </c:forEach>         
      </div>
      </div>
      <button id="getMore">Loading...</button>
      <div id="loadingMask" style="hidden:true;">正在加载</div>
      <input type="hidden" id="page" value="2">  
      <div class="clear"></div>
     
  </article>
</div>
<div class="off">
    <div class="scroll" id="scroll"> ︿ </div>
</div>

<script type="text/javascript">
    $(function(){
        showScroll();
        function showScroll(){
            $(window).scroll( function() {
                var scrollValue=$(window).scrollTop();
                scrollValue > 500 ? $('div[class=scroll]').fadeIn():$('div[class=scroll]').fadeOut();
            } );
            $('#scroll').click(function(){
                $("html,body").animate({scrollTop:0},200);
            });
        }
    })
</script>

<script type="text/javascript" src="js/wp-embed.min.js"></script>
</body>
</html>