<%-- 
    Document   : detail
    Created on : Feb 18, 2020, 2:23:12 PM
    Author     : Khanh Xinh Tuoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Home Page</title>
            <link href="css/style.css" rel="stylesheet" type="text/css" />
        </head>
        <body> 
            <div class="container">
            <jsp:include page="header.jsp"/>
            <div class="content">
                <div class="main">
                    <div class="tittle">
                        ${one.title}
                    </div>
                    <div class="image">
                        <img src="${imagePath}${one.image}"/>
                    </div>
                    <div class="text">
                        ${one.description}
                    </div>
                    <div class="signature">
                        <div class="icon1"></div>
                        <div class="icon2"></div>
                       By ${one.author} | ${one.dateConvert}
                    </div>
                </div>
                <jsp:include page="right.jsp"/> 
            </div>
            <jsp:include page="footer.jsp"/>
        </div>
    </body>
</html>

