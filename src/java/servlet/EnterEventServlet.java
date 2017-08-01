/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import business.RecordBean;
import game.GameParticipants;
import game.PublishTask;
import game.PublishTask2;
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
import model.Record;

/**
 *
 * @author Su-Ting Chen
 */
@WebServlet("/enterGame")
public class EnterEventServlet extends HttpServlet {
    
    @Inject private GameParticipants participants;
    @Inject LoginHandler loginhandler;
    @Inject RecordBean rbean;
    
    @Resource(mappedName="concurrent/myfirstthreadpool")
    private ManagedScheduledExecutorService svc;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String gidString = req.getParameter("gid");
        
        Integer gidInt = Integer.parseInt(gidString);

        String playerName = loginhandler.getCredential().getName();
        
        Integer playerId = loginhandler.getLoggedInPlayer().getPlayerId();
        
        Record r = rbean.searchOneRecord(playerId, gidInt);
        
        Integer playerScore;
        
        if(r!=null){
            playerScore = r.getPoints();
        }
        else{
            playerScore = 0;
        }

        String message = "E" + " " + gidInt + " " + playerName + " "+ playerScore + " " + playerId;
        
        svc.submit(new PublishTask(message, participants));
        
        resp.setStatus(HttpServletResponse.SC_ACCEPTED);
        
    }
    
    
    
}
