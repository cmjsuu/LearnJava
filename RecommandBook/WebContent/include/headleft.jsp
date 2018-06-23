<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="../WEB-INF/myTagLib.tld" prefix="myTag" %>
<script type="text/javascript">
        function do_list_row_show(strid){
            strid.getElementsByTagName('a')[0].className='on';
            strid.getElementsByTagName('ul')[0].style.display="block";
            strid.style.position="relative";
        }
        function do_list_row_unshow(strid){
            strid.getElementsByTagName('a')[0].className='';
            strid.getElementsByTagName('ul')[0].style.display="";
            strid.style.position="";
        }
    </script>
 <div class="container">
        <nav>
            <ul class="mcd-menu">
            <c:forEach items="${categoryList}" var="category">
             <li class="menu-content"  onMouseOut="do_list_row_unshow(this);" onMouseOver="do_list_row_show(this);">
                    <a href="#">
                        <strong>${category.category_value}</strong>
                        <small>${category.category_key}</small>
                    </a>
                    <c:forEach items="${categoryChildrenList}" var="categoryChildren">
                    <c:if test="${categoryChildren.parentid == category.id}">
						<ul>
                        <li><a href="#">${categoryChildren.category_value}</a></li>
                      	</ul>
					</c:if>                     
                    </c:forEach>            
                </li>           
            </c:forEach>            
            </ul>
        </nav>
    </div>
