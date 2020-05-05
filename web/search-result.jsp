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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    
    </head>
    <body>
        <div class="toast" style="position: fixed; top: 650px; right: 25px;">
              <div class="toast-header">
                <strong class="mr-auto">Message</strong>
                <small>Now</small>
                <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="toast-body">
                ${numberOfSearched} digital has been found!
              </div>
        </div>
        <div class="container1">
            <jsp:include page="header.jsp"/>
            <div class="content1">
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
        
        
            
        
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        
        <script>
            $(document).ready(function(){
                if(${numberOfSearched} != null){
                    $('.toast').toast({ 
                       delay : 3000
                    });   
                    $(".toast").toast("show");
                }
            });
        </script>
    
    </body>
</html>
