/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import business.RecordBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import model.Record;

/**
 *
 * @author Su-Ting Chen
 */
@RequestScoped
public class RecordTransaction {
    
    @EJB private RecordBean rbean;
 
    
    public void updatePoints(Integer playerId, Integer gameId){
        
        Record r = rbean.searchOneRecord(playerId,gameId);
        Integer newPoint = r.getPoints() + 1;
        rbean.updatePlayerPoints(playerId, gameId, newPoint);

    }
    
    public void updateTrial(Integer playerId, Integer gameId){
        Record r = rbean.searchOneRecord(playerId, gameId);
        Integer newNumOfTrial = r.getTrial() - 1;
        rbean.updatePlayerTrial(playerId, gameId, newNumOfTrial);
    }
    
    public void createNewRecord(Integer playerId, Integer gameId){
        rbean.createRecord(playerId, gameId);
    }
    
}
