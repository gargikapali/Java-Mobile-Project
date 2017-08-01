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
import model.Record;
import model.RecordPK;

/**
 *
 * @author Su-Ting Chen
 */
@Stateless
public class RecordBean {
    @PersistenceContext private EntityManager em;
    
    public List<Record> searchRecord(String gameId){
        Integer id = Integer.parseInt(gameId);
        TypedQuery<Record> query = em.createQuery("select r from Record r where r.recordPK.gameId = :gameId", Record.class);
        query.setParameter("gameId", id);
        List<Record> results = query.getResultList();
        
        return results;
    }
    
    public Record searchOneRecord(Integer playerId, Integer gameId){
        
        TypedQuery<Record> query = em.createQuery("SELECT r FROM Record r WHERE r.recordPK.playerId = :playerId"
                + " AND r.recordPK.gameId = :gameId", Record.class);
        query.setParameter("playerId", playerId);
        query.setParameter("gameId", gameId);
        List<Record> results = query.getResultList();
        
        if(results.size()>0){
            return results.get(0);
        }
        else{ 
            System.out.println("no record is found from RecordBean.searchOneRecord()");
            return (null);
        }
        
    }
    
    
    public void updatePlayerPoints(Integer playerId, Integer gameId, Integer newScore){

        Query query = em.createQuery("update Record r set r.points = :points where r.recordPK.playerId= :playerId and r.recordPK.gameId = :gameId", Record.class);
        query.setParameter("points", newScore);
        query.setParameter("playerId", playerId);
        query.setParameter("gameId", gameId);
        
        int updateCount = query.executeUpdate();
        if(updateCount > 0)
            System.out.println("Done updating record player's points; PlayerID is " + playerId + 
                    "; gameID is " + gameId + " ;new score is " + newScore);
        else
            System.out.println("failed to update player's score");

    }
    
    public void updatePlayerTrial(Integer playerId, Integer gameId, Integer newNumOfTrial){
        Query query = em.createQuery("update Record r set r.trial = :trial where r.recordPK.playerId = :playerId and r.recordPK.gameId = :gameId", Record.class);
        query.setParameter("trial",newNumOfTrial);
        query.setParameter("playerId",playerId);
        query.setParameter("gameId",gameId);
        
        int updateCount = query.executeUpdate();
        if(updateCount > 0)
            System.out.println("Done updating record player's trial; PlayerID is " + playerId + 
                    "; gameID is " + gameId + " ;new number of trial is " + newNumOfTrial);
        else
            System.out.println("failed to update player's score");
    }
    
    public void createRecord(Integer playerId, Integer gameId){
        RecordPK recordpk = new RecordPK();
        recordpk.setGameId(gameId);
        recordpk.setPlayerId(playerId);
        Record record = new Record();
        record.setRecordPK(recordpk);
        record.setPoints(0);
        record.setTrial(10);
        em.persist(record);
    }
    
    public List<Object[]> getTopScore(){
        //TypedQuery<Object[]> query = em.createQuery("SELECT r.recordPK.playerId, r.points FROM Record r ORDER BY r.points DESC", Object[].class).setMaxResults(10);
        
        TypedQuery<Object[]> query = em.createQuery("SELECT p.name, r.points FROM Record r, Player p WHERE p.playerId = r.recordPK.playerId ORDER BY r.points DESC", Object[].class).setMaxResults(10);
        List<Object[]> results = query.getResultList();
        
        if(results!=null){
            return (results);
        }
        else{
            System.out.println("error in getTopScore()");
            return null;
        }
    }
}
