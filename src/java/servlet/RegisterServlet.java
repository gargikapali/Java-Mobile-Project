/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import business.PlayerBean;
import java.io.IOException;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import login.LoginHandler;


/**
 *
 * @author Su-Ting Chen, Karthiga
 */

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
    
    @EJB private PlayerBean bean;
    
    //0210 0200am
    @Inject private LoginHandler loginhandler;
    
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        
//        String name = req.getParameter("name");
//        String password = req.getParameter("password");
//        String email = req.getParameter("email");
//        
//        
//        //0210 0200am
//        Integer newID = bean.createPlayerAccount(name, password, email);
//        System.out.println(">> newID: " +  newID);
//        
//        try{
//            loginhandler.login(newID, name, password);
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//
//             resp.sendRedirect("index.html");
//        
////        try(PrintWriter pw = resp.getWriter()){
////            
////            if(loginhandler.getLoggedInPlayer()!=null){
//////            resp.setContentType("text/html");
//////
//////
//////            pw.println("Login successful.<br/>");
////
////          resp.sendRedirect("index.html");
////            
////      
////            
////            }else{
////                pw.println("login failed.");
////                //resp.sendRedirect("login.html");
////            }
//            
//            
////        }
//        
//        
//                
//}
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        
        //0210 0200am
        Integer newID = bean.createPlayerAccount(name, password, email);
        System.out.println(">> newID: " +  newID);
        
        try{
            loginhandler.login(newID, name, password);
            
            if(loginhandler.getLoggedInPlayer()!=null){
            
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                
            }
            
            else{
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
    
    
    
    
    
    
}
