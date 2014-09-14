<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="com.sizmoj.common.web.Page" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
int current =  page.getPageNumber();
int totalPages = page.getCount()%10 == 0 ? page.getCount()/10 : page.getCount()/10 + 1;
int begin = Math.max(1, current - 10/2);
int end = Math.min(begin + (page.getPageSize() - 1), totalPages);
request.setAttribute("current", current);
request.setAttribute("begin", begin);
request.setAttribute("end", end);
%>

<div class="dataTables_paginate paging_simple_numbers">
	<ul class="pagination">
		 <% if (current > 1){%>
                <li class="paginate_button"><a href="?page=1">&lt;&lt;</a></li>
                <li><a href="?page=${current-1}">&lt;</a></li>
         <%}else{%>
                <li class="paginate_button disabled"><a href="#">&lt;&lt;</a></li>
                <li class="paginate_button disabled"><a href="#">&lt;</a></li>
         <%} %>
 
		<c:forEach var="i" begin="${begin}" end="${end}">
            <c:choose>
                <c:when test="${i == current}">
                    <li class="paginate_button active"><a href="?page=${i}&sortType=${sortType}&${searchParams}">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li class="paginate_button"><a href="?page=${i}&sortType=${sortType}&${searchParams}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
	  
	  	 <% if ( current < totalPages){%>
               	<li><a href="?page=${current+1}">&gt;</a></li>
               	<li><a href="?page=<%=totalPages%>">&gt;&gt;</a></li>
         <%}else{%>
                <li class="disabled"><a href="#">&gt;</a></li>
                <li class="disabled"><a href="#">&gt;&gt;</a></li>           
         <%} %>
	</ul>
</div>

