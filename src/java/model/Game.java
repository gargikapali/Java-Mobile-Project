/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Su-Ting Chen
 */
@Entity
@Table(name = "game")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Game.findAll", query = "SELECT g FROM Game g"),
    @NamedQuery(name = "Game.findByGameId", query = "SELECT g FROM Game g WHERE g.gameId = :gameId"),
    @NamedQuery(name = "Game.findByCreateDate", query = "SELECT g FROM Game g WHERE g.createDate = :createDate"),
    @NamedQuery(name = "Game.findByHscore", query = "SELECT g FROM Game g WHERE g.hscore = :hscore"),
    @NamedQuery(name = "Game.findByStatus", query = "SELECT g FROM Game g WHERE g.status = :status")})
public class Game implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Basic(optional = false)
    @Column(name = "game_id")
    private Integer gameId;
    //@Basic(optional = false)
    //@NotNull
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "hscore")
    private Integer hscore;
    @Column(name = "status")
    private Boolean status;
    @Lob
    @Size(max = 65535)
    @Column(name = "answer")
    private String answer;
    @Lob
    @Size(max = 65535)
    @Column(name = "flipped")
    private String flipped;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    private Collection<Record> recordCollection;

    public Game() {
    }

    public Game(Integer gameId) {
        this.gameId = gameId;
    }

    public Game(Integer gameId, Date createDate) {
        this.gameId = gameId;
        this.createDate = createDate;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getHscore() {
        return hscore;
    }

    public void setHscore(Integer hscore) {
        this.hscore = hscore;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getFlipped() {
        return flipped;
    }

    public void setFlipped(String flipped) {
        this.flipped = flipped;
    }

    @XmlTransient
    public Collection<Record> getRecordCollection() {
        return recordCollection;
    }

    public void setRecordCollection(Collection<Record> recordCollection) {
        this.recordCollection = recordCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gameId != null ? gameId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Game)) {
            return false;
        }
        Game other = (Game) object;
        if ((this.gameId == null && other.gameId != null) || (this.gameId != null && !this.gameId.equals(other.gameId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Game[ gameId=" + gameId + " ]";
    }
    
}
