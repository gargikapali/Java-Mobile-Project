/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$( document ).ready(function() {
    console.log( "register ready!" );
   
    
    
     $("#regBtn").on("click", function(){
         
         $.post("register",{name:$("#name").val(), email:$("#email").val(), password:$("#password").val()})
                 .done(function(){
                     
                     window.location = "index.html";
                     
                 })
                 
                 .fail(function(){
                     
                     alert("Register failed");
                     window.location = "login.html";
                     
                 })
         
     }) 

});