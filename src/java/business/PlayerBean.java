/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.Player;

/**
 *
 * @author Su-Ting Chen
 */
@Stateless
public class PlayerBean {
    
    @PersistenceContext private EntityManager em;
    
    public Player search(Integer playerId, String name, String password){
        String SQL = 
        "SELECT p FROM Player p WHERE p.playerId = :playerId AND p.name = :name AND p.password = :password";
        TypedQuery<Player> query = em.createQuery(SQL, Player.class);
        query.setParameter("playerId", playerId);
        query.setParameter("name", name);
        query.setParameter("password", password);
        List<Player> p = query.getResultList();
        
        if(p.size()>0)
            return p.get(0);
        
        return null;
    }
    
    public Integer createPlayerAccount(String playerName, String password, String email){
        Player p = new Player();
        p.setName(playerName);
        p.setPassword(password);
        p.setEmail(email);
        
        em.persist(p);
        em.flush();
        return p.getPlayerId();
        
    }
}