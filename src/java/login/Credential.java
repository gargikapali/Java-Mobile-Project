/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Su-Ting Chen
 */
@RequestScoped
public class Credential implements Serializable{
    
    private static final long serialVersionUID = 1L;  
    
    private Integer playerId;
    private String name;
    private String password;
    
    @PostConstruct
    private void init() {
        System.out.println(">> postconstruct credential");
    }
    
    @PreDestroy
    private void destroy() {
        System.out.println(">> destroying " + name);
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Credential createCopy(){
        Credential c = new Credential();
        c.setName(name);
        c.setPlayerId(playerId);
        c.setPassword(password);
        
        return (c);
    }

    @Override
    public String toString() {
        return "Credential{" + "playerId=" + playerId + ", name=" + name + ", password=" + password + '}';
    }
    
    
}
