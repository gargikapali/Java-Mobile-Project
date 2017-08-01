/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import login.Credential;
import login.LoginHandler;

/**
 *
 * @author Su-Ting Chen
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Inject private Credential credential;
    @Inject private LoginHandler loginhandler;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        Integer id = Integer.parseInt(req.getParameter("playerId"));
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        
        try{
            loginhandler.login(id, name, password);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
//        try(PrintWriter pw = resp.getWriter()){
//            
//            if(loginhandler.getLoggedInPlayer()!=null){
//                pw.println("login successful.");
//            }
//            else{
//                pw.println("login failed.");
//            }
//            
//        }
        try(PrintWriter pw = resp.getWriter()){
            
            if(loginhandler.getLoggedInPlayer()!=null){
            resp.setContentType("text/html");


            pw.println("Login successful.<br/>");

            resp.sendRedirect("index.html");
            
            }else{
                pw.println("login failed.");
                //resp.sendRedirect("login.html");
            }
        }
            
        
        
        
    
        
        
    }
    
    
}
