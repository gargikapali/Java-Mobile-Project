/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import business.GameBean;
import java.io.IOException;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Game;
import test.NewGameGenerator;

/**
 *
 * @author Su-Ting Chen
 */
@WebServlet("/createNewGame")
public class CreateNewGameServlet extends HttpServlet {
    
    @Inject NewGameGenerator generator;
    @Inject GameBean bean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try{
            Game g = generator.generateNewGame();
            bean.createGame(g);
            
            //resp.setstatusOK??
            resp.sendRedirect("index.html");
            
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        
    }
    
    
    
    
    

    
}
