/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Su-Ting Chen
 */
public class PublishTask2 implements Runnable { //Not used
    
    private String msg;
    private GameParticipants participants;
    
    public PublishTask2(String i, GameParticipants p){
        msg = i;
        participants = p;
        
    }
    
    @Override
    public void run() {
        
        System.out.println(">> publishing:" + msg);
        participants.publish_enter(msg);
        
    }
    
}
