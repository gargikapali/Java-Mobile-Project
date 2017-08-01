/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import game.GameParticipants;
import game.GameTransaction;
import game.PublishTask;
import game.RecordTransaction;
import java.io.IOException;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import login.LoginHandler;

/**
 *
 * @author Su-Ting Chen
 */
@WebServlet("/validate")
public class ProcessSubmitServlet extends HttpServlet{
    
    @Inject private LoginHandler loginhandler;
    @Inject private GameParticipants participants;
    @Inject private GameTransaction gt;
    @Inject private RecordTransaction rt;

    @Resource(mappedName="concurrent/myfirstthreadpool")
    private ManagedScheduledExecutorService svc;
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String i1 = req.getParameter("index1");
        String i2 = req.getParameter("index2");
        String isSuc = req.getParameter("isSuccess");
        String isNew = req.getParameter("isNewPlayer");
        String gidString = req.getParameter("gid");
        
        Boolean isSuccess;
        isSuccess = isSuc.equals("true");
        
        Boolean isNewPlayer;
        isNewPlayer = isNew.equals("true");
        
        //message>> 1 2 false false 15
        System.out.println("message>> " + i1 + " " + i2 + " " + isSuc + " " + isNew + " " + gidString );
        
        /*******************************************/
        Integer idx1 = Integer.parseInt(i1);
        Integer idx2 = Integer.parseInt(i2);
        Integer gidInt = Integer.parseInt(gidString);
        /*******************************************/
        
        Integer pid = loginhandler.getLoggedInPlayer().getPlayerId();
        
        String msg = idx1 + " " + idx2 + " "+ gidInt;

        if(isSuccess){
            
            //1. pass the msg to common view
            svc.submit(new PublishTask(msg, participants)); 
            
            //2. handle new player
            if(isNewPlayer){
                System.out.println("this player is a new player in this game");
                rt.createNewRecord(pid, gidInt); 
            }
            
            //3. update Game.Flipped
            gt.updateFlipped(gidString,idx1,idx2);
            
            //4. update Game.Status
            if(gt.allFlipped(gidInt)==true){
                gt.updateStatus(gidInt);
            }
            
            //5. update Record.Points
            rt.updatePoints(pid, gidInt);
            
            //6. update Record.Trial
            rt.updateTrial(pid, gidInt);
            
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
        }
        else{ //wrong guess
            //1. update player's record (trial - 1)
            if(isNewPlayer==false) //existing player
            {
                rt.updateTrial(pid, gidInt);
            }else{                  //new player
                rt.createNewRecord(pid, gidInt); 
                rt.updateTrial(pid, gidInt);
            }
            
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
        } 
    } 
}
