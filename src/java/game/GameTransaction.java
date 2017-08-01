/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import business.GameBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import login.LoginHandler;
import model.Game;
import model.Player;

/**
 *
 * @author Su-Ting Chen
 */
@RequestScoped
public class GameTransaction {
    
    @EJB private GameBean gbean;

   
    public void updateFlipped(String gameId, Integer idx1, Integer idx2){
        
        System.out.println("updating flipped, gameId = " + gameId);
        Game g = gbean.getOneGame(gameId);
        String strFlipped = g.getFlipped();
        strFlipped = strFlipped.substring(1, strFlipped.length()-1);
        
        String[] stringArr = strFlipped.split(", ");
        Integer[] intArr = new Integer[stringArr.length] ;
        
        for(int i = 0 ; i < stringArr.length ; i++ ){
            intArr[i] = Integer.parseInt(stringArr[i]);
        }
        
        intArr[idx1]=1;
        intArr[idx2]=1;
        
        String newStrFlipped = "[";
        
        for(int i = 0 ; i < intArr.length-1 ; i++){
            newStrFlipped += intArr[i] + ", ";
        }
        
        newStrFlipped += intArr[intArr.length-1];
        
        newStrFlipped += "]";
        
        gbean.updateGameFlipped(Integer.parseInt(gameId), newStrFlipped);
        
    }
    
    public Boolean allFlipped(Integer gameId){
        
        
        String gameIdInString = gameId.toString();
        Game g = gbean.getOneGame(gameIdInString);
        
        Boolean allflipped = true;
        
        String strFlipped = g.getFlipped();
        strFlipped = strFlipped.substring(1, strFlipped.length()-1);
        String[] stringArr = strFlipped.split(", ");
        
        Integer[] intArr = new Integer[stringArr.length] ;
        
        for(int i = 0 ; i < stringArr.length ; i++ ){
            intArr[i] = Integer.parseInt(stringArr[i]);
        }
        
        for(int i = 0 ; i < intArr.length ; i++){
            if(intArr[i] == 0)
                allflipped = false;
        }
        
        return allflipped;
    }
    
    
    public void updateStatus(Integer gameId){ //need testing!!!!
        gbean.updateGameStatusToFalse(gameId);
    }
}
