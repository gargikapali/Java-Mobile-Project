/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Su-Ting Chen
 */
@Entity
@Table(name = "record")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Record.findAll", query = "SELECT r FROM Record r"),
    @NamedQuery(name = "Record.findByPlayerId", query = "SELECT r FROM Record r WHERE r.recordPK.playerId = :playerId"),
    @NamedQuery(name = "Record.findByGameId", query = "SELECT r FROM Record r WHERE r.recordPK.gameId = :gameId"),
    @NamedQuery(name = "Record.findByPoints", query = "SELECT r FROM Record r WHERE r.points = :points"),
    @NamedQuery(name = "Record.findByTrial", query = "SELECT r FROM Record r WHERE r.trial = :trial")})
public class Record implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RecordPK recordPK;
    @Column(name = "points")
    private Integer points;
    @Column(name = "trial")
    private Integer trial;
    @JoinColumn(name = "player_id", referencedColumnName = "player_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Player player;
    @JoinColumn(name = "game_id", referencedColumnName = "game_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Game game;

    public Record() {
    }

    public Record(RecordPK recordPK) {
        this.recordPK = recordPK;
    }

    public Record(int playerId, int gameId) {
        this.recordPK = new RecordPK(playerId, gameId);
    }

    public RecordPK getRecordPK() {
        return recordPK;
    }

    public void setRecordPK(RecordPK recordPK) {
        this.recordPK = recordPK;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getTrial() {
        return trial;
    }

    public void setTrial(Integer trial) {
        this.trial = trial;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recordPK != null ? recordPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Record)) {
            return false;
        }
        Record other = (Record) object;
        if ((this.recordPK == null && other.recordPK != null) || (this.recordPK != null && !this.recordPK.equals(other.recordPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Record[ recordPK=" + recordPK + " ]";
    }
    
}
