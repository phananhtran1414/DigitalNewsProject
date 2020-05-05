/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
   
   $("#search").click(function(){
       if($("#text").val() == ""){
            alert("Must input title to search");
            return false;
       }
   });
    
   
   $("span").click(function(){
       $(this).toggleClass("toggle");
   });
    
});
