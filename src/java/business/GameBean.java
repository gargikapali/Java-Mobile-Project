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
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import model.Game;

/**
 *
 * @author Su-Ting Chen
 */
@Stateless
public class GameBean {
    
    @PersistenceContext private EntityManager em;
    
    public void createGame(Game g){
        
        //em.getTransaction().begin();
        Game game = new Game();
        game.setStatus(true);
        game.setHscore(0);
        game.setAnswer(g.getAnswer());
        game.setFlipped(g.getFlipped());
        em.persist(game);
        //em.getTransaction().commit();
    }
    
    public List<Object[]> findAllRecords(){
            String SQL = "select g.gameId, count(r.recordPK.playerId) from Game g left join Record r on g.gameId=r.recordPK.gameId group by g.gameId";
        TypedQuery<Object[]> query = em.createQuery(SQL, Object[].class);
        
        if(query.getResultList()!=null){
            return (query.getResultList());
        }
        else{
            System.out.println("error in findAllRecords()");
            return null;
        }
        
    }
    
    //0205-0233pm
    public List<Game> findAllGames(){
        String SQL = "SELECT g FROM Game g";
        TypedQuery<Game> query = em.createQuery(SQL, Game.class);
        
        if(query.getResultList()!=null){
            return(query.getResultList());
        }
        else{
            System.out.println("error in findAllGames()");
            return null;
        }
    }
    

    public Game getOneGame(String gameId){
        Integer id = Integer.parseInt(gameId);
        TypedQuery<Game> query = em.createQuery("select g from Game g where g.gameId = :gameId", Game.class);
        query.setParameter("gameId", id);
        List<Game> results = query.getResultList();
        if(results.size()>0){
            return results.get(0);
        }
        else{ 
            System.out.println("no game is found from GameBean.getOneGame()");
            return (null);
        }

    }
    
    public void updateGameFlipped(Integer gameId, String strFlipped){

        Query query = em.createQuery("update Game g set g.flipped = :flipped where g.gameId = :gameId", Game.class);
        query.setParameter("flipped", strFlipped);
        query.setParameter("gameId", gameId);
        int updateCount = query.executeUpdate();
        if(updateCount > 0)
            System.out.println("Done updating game flipped; GameID is " + gameId + "; Flipped String is " + strFlipped);
        else
            System.out.println("failed to update game flipped");
    }
    
    public void updateGameStatusToFalse(Integer gameId){ //need testing!!!
        Query query = em.createQuery("update Game g set g.status = :status where g.gameId = :gameId", Game.class);
        query.setParameter("status", false);
        query.setParameter("gameId", gameId);
        int updateCount = query.executeUpdate();
        if(updateCount > 0)
            System.out.println("Done updating game status to false; GameID is " + gameId);
        else
            System.out.println("failed to update game flipped");
    }

    
}
