<%-- 
    Document   : error
    Created on : Feb 18, 2020, 2:21:21 PM
    Author     : Khanh Xinh Tuoi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
        <link href="css/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div class="container1">
            <jsp:include page="header.jsp"/>
            <div class="content1">
                <div class="main">
                    <h1>Error</h1>
                </div>
                <jsp:include page="right.jsp"/> 
            </div> 
            <jsp:include page="footer.jsp"/>
        </div>
    </body>
</html>