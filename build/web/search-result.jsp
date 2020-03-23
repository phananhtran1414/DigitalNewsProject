<%-- 
    Document   : search-result
    Created on : Feb 18, 2020, 2:25:37 PM
    Author     : Khanh Xinh Tuoi
--%>

<%@page import="javax.naming.Context"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Search</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="container">
            <jsp:include page="header.jsp"/>
            <div class="content">
                <div class="main">     
                    <div class="search">
                        <c:forEach items="${list}" var="x">
                            <div class="tittle">
                                <a href="detail?id=${x.id}">      
                                    ${x.title}
                                </a>
                            </div>
                            <div class="image_search">
                                <a href="detail?id=${x.id}">    
                                    <img src="${imagePath}${x.image}" alt=""/>
                                </a>
                            </div>
                            <div class="text_search">
                                <a href="detail?id=${x.id}"> 
                                    ${x.shortDes}
                                </a>
                            </div>
                            <br/>
                        </c:forEach>
                    </div>
                    <div class="paging">
                         <c:if test="${maxPage < 1}">
                            <h3>Not Found !!</h3>
                        </c:if>
                        <c:if test="${maxPage >= 1}">
                            <c:forEach begin="1" end="${maxPage}" var="i">
                                <a class="${i==index?"active":""}" href="search?index=${i}">${i}</a>
                            </c:forEach>
                        </c:if>
                    </div>
                </div> 
                <jsp:include page="right.jsp"/> 
            </div>
            <jsp:include page="footer.jsp"/>
        </div>

    </body>
</html>
