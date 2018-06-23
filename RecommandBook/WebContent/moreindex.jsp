<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>加载更多</title>
</head>
<body>
 <c:forEach items="${bookList}" var="book">
 <section class="list">
       <span class="titleimg"> <a href="booksDetail.book?id=${book.id}" target="_blank">
  		<img src="${book.img_url}" width="136px" height="190px"> </a> </span>

              <a href="http://www.yzipi.com/102.htm" target="_blank" class="iu">
                  6
              </a>
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

</body>
</html>