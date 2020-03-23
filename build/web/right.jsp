<%-- 
    Document   : right
    Created on : Feb 18, 2020, 2:16:29 PM
    Author     : Khanh Xinh Tuoi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="css/right.css" rel="stylesheet" type="text/css" />

<div class="right">
    <div class="newst">
        <div class="titleNews">
            <span>Digital News</span>
        </div>
        <div class="contentNews lastArticles">
            <a href="detail?id=${top1.id}">${top1.shortDes}</a>
        </div>
    </div>
    <div class="newst">
        <div class="titleNews">
            Search
        </div>
        <form action="search" method="post">
            <input class="searchBox" type="text" name="txtSearch" size="15"  required>
            <input class="searchButton" type="submit" name="btnGo" value="Go">
        </form>                        
    </div>
    <div class="newst">
        <div class="titleNews">
            <span>Last Articles</span><br>
        </div>
    <c:forEach items="${top5}" var="i">
        <div class="lastArticles">
            <a href="detail?id=${i.id}">${i.title}</a>
        </div>
    </c:forEach>
    </div>
</div>    