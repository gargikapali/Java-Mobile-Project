/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import business.PlayerBean;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import static jdk.nashorn.internal.runtime.Debug.id;
import model.Player;

/**
 *
 * @author Su-Ting Chen 
 */
@SessionScoped
public class LoginHandler implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Inject PlayerBean pbean;
    private Credential credential;
    private Player loggedInPlayer;

    public Credential getCredential() {
        return credential;
    }

    public Player getLoggedInPlayer() {
        return loggedInPlayer;
    }
    
    
    public LoginHandler(){
        System.out.println(">> LoginHandler");
        loggedInPlayer=null;
        
    }
    
    public void login(Integer id, String name, String password){
        
        Player player = new Player();
        try{
            player = pbean.search(id, name, password);
        }catch(Exception ex){
            System.out.println(">> player not found.");
        }
        
        if(player!=null){
            
            
            loggedInPlayer = player;
            
            Credential cred = new Credential();
            
            cred.setPlayerId(id);
            cred.setName(name);
            cred.setPassword(password);
            
            this.credential = cred;
            
            //return true;
        }
        else{
            System.out.println(">> login failed.");
            //return false;
        }
        
    }
    
}
