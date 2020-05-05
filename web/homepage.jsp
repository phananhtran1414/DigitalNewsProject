<%-- 
    Document   : HomePage
    Created on : Feb 17, 2020, 5:09:15 PM
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
        <div class="container1">
            <jsp:include page="header.jsp" />
            
            <div class="content1">
                <div class="main">
                    <div class="tittle">
                          ${top1.title}
                    </div>
                    
                    <div class="image">
                        <img src="${imagePath}${top1.image}"/>
                    </div>
                    
                    <div class="text">
                       ${top1.description} 
                    </div>
                    
                    <div class="signature">
                        <div class="icon1"></div>
                        <div class="icon2"></div>
                         By ${top1.author} | ${top1.dateConvert}
                    </div>
                </div>
                    
                <jsp:include page="right.jsp" />
            </div>
            
            <jsp:include page="footer.jsp" />
        </div>
    </body>
</html>
