/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.enterprise.context.ApplicationScoped;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseBroadcaster;

/**
 *
 * @author Su-Ting Chen
 */
@ApplicationScoped
public class GameParticipants {
    
    private SseBroadcaster broadcaster = new SseBroadcaster();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    
    public void add(EventOutput eo){
        Lock l = lock.writeLock();
        l.lock(); 
         
        try{
            broadcaster.add(eo);
            System.out.println("new eo");
        }finally{
            l.unlock();
        }
    }
    
    public void publish(String msg){
        
        String[] arr = msg.split(" ");
        
        if("E".equals(arr[0])){ // publish enter
            
            String gameId = arr[1];
            String playerName = arr[2];
            String playerScore = arr[3];
            String playerId = arr[4];

            String message2 = playerName + "(" + playerScore + ")";

            String eventName = gameId;

            OutboundEvent event = new OutboundEvent.Builder()
                    .data(String.class, message2)
                    .name(eventName)
                    .build();
            Lock l = lock.readLock();

            l.lock();

            try{
                //send out the message
                System.out.println(">> broadcasting message: "+ message2);
                broadcaster.broadcast(event);
            } finally{
                l.unlock();
            }
        
        }
        else{ //publish score
            
            String gameId = arr[2];
            System.out.println("arr[2] (gameId) >> " + gameId);

            OutboundEvent event = new OutboundEvent.Builder()
                    .data(String.class, msg)
                    .name(gameId)
                    .build();//build the message 


            //Read lock: any writer will be blocked until all readers complete
            //lock: when people come and join, cannot add while sending out

            Lock l = lock.readLock();
            l.lock();

            try{
                //send out the message
                System.out.println(">> broadcasting message: "+ msg);          
                broadcaster.broadcast(event);
            } finally{
                l.unlock();
            }
        
            
        }

    }
    
    
    public void publish_enter(String msg){
        
        //process msg : String message = gidInt + " " + playerName + " "+ playerScore + " " + playerId;
        
//        String[] msgArr = msg.split(" ");
//        String gameId = msgArr[0];
//        String playerName = msgArr[1];
//        String playerScore = msgArr[2];
//        String playerId = msgArr[3];
//        
//        String message2 = playerName + "(" + playerScore + ")";
//        
//        String eventName = "E" + gameId;
//        
//        OutboundEvent event = new OutboundEvent.Builder()
//                .data(String.class, message2)
//                .name(eventName)
//                .build();
//        Lock l = lock.readLock();
//        
//        l.lock();
//        
//        try{
//            //send out the message
//            System.out.println(">> broadcasting message: "+ message2);
//            broadcaster.broadcast(event);
//        } finally{
//            l.unlock();
//        }
//        
        
        
    }
    
}
