/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Su-Ting Chen
 */
@Entity /* NOT USING */
public class GamesTable implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private int gameId;
    private Long numOfPlayers;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public Long getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(Long numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }
    
    
}
