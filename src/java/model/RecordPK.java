/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Su-Ting Chen
 */
@Embeddable
public class RecordPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "player_id")
    private int playerId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "game_id")
    private int gameId;

    public RecordPK() {
    }

    public RecordPK(int playerId, int gameId) {
        this.playerId = playerId;
        this.gameId = gameId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) playerId;
        hash += (int) gameId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecordPK)) {
            return false;
        }
        RecordPK other = (RecordPK) object;
        if (this.playerId != other.playerId) {
            return false;
        }
        if (this.gameId != other.gameId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.RecordPK[ playerId=" + playerId + ", gameId=" + gameId + " ]";
    }
    
}
