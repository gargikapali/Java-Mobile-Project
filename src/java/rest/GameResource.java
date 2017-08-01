/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import business.GameBean;
import business.RecordBean;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import login.LoginHandler;
import model.Game;
import model.Record;

/**
 *
 * @author Su-Ting Chen
 */
@RequestScoped
@Path("/game")
public class GameResource {
    
    @EJB GameBean gameBean;
    @EJB RecordBean recordBean;
    @Inject LoginHandler loginHandler;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(){
        List<Object[]> result = gameBean.findAllRecords();
        
        if(result!=null){
            JsonArrayBuilder jsonArrayBuilder  = Json.createArrayBuilder();
            for(Object[] onegame : result){
                jsonArrayBuilder.add(
                        Json.createObjectBuilder()
                        .add("gameId", onegame[0].toString())
                        .add("numOfPlayers", onegame[1].toString())
                        );
            }

            JsonArray gamesJson = jsonArrayBuilder.build();
            return (Response.ok(gamesJson).build());
        }else{
            System.out.println("no result");
            return null;
        }
  
    }
        
    @GET
    @Path("{gameId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("gameId") String gameId){
//        Game g = gameBean.getOneGame(gameId);
//        List<Record> r = recordBean.searchRecord(gameId);
//        
//        if(g==null)
//            return (Response.status(Response.Status.NOT_FOUND).build());
//        
//        JsonObject gameJson = Json.createObjectBuilder()
//                .add("gameId", g.getGameId())
//                .add("answer", g.getAnswer())
//                .add("flipped", g.getFlipped())
//                //.add("hscore", g.getHscore())
//                //.add("createDate", g.getCreateDate().toString())
//                .add("status",g.getStatus())// new added 0204 1031pm
//                .build();
//        
//        
//        JsonArrayBuilder jsonArrayBuilder  = Json.createArrayBuilder();
//        JsonArray playersTableJson;
//        if(r.size()>0){
//            for(Record oneRecord : r){
//                jsonArrayBuilder.add(
//                Json.createObjectBuilder()
//                        .add("gameId", oneRecord.getRecordPK().getGameId())
//                        .add("playerId", oneRecord.getRecordPK().getPlayerId())
//                        .add("playerName", oneRecord.getPlayer().getName())
//                        .add("points", oneRecord.getPoints())
//                        .add("trial", oneRecord.getTrial())
//                );
//            }
//        }
//        
//        String currentPlayerId = loginHandler.getLoggedInPlayer().getPlayerId().toString();
//        
//        playersTableJson = jsonArrayBuilder.build();
//
//        JsonObject gameZoneAllInfo = Json.createObjectBuilder()
//                .add("game", gameJson)
//                .add("players", playersTableJson)
//                .add("currentPlayerId", currentPlayerId)
//                .build();
//        
//        
//        return (Response.ok(gameZoneAllInfo).build());
        
        
        
        
        /*
        Change on 0207 1035am
        */
        
        
        Game g = gameBean.getOneGame(gameId);
        List<Record> r = recordBean.searchRecord(gameId);
        
        if(g==null)
            return (Response.status(Response.Status.NOT_FOUND).build());
        
        JsonObject gameJson = Json.createObjectBuilder()
                .add("gameId", g.getGameId())
                .add("answer", g.getAnswer())
                .add("flipped", g.getFlipped())
                .add("hscore", g.getHscore())
                //.add("createDate", g.getCreateDate().toString())
                .add("status",g.getStatus())// new added 0204 1031pm
                .build();
        
        
        JsonArrayBuilder jsonArrayBuilder  = Json.createArrayBuilder();
        JsonArray playersTableJson;
        if(r.size()>0){
            for(Record oneRecord : r){
                jsonArrayBuilder.add(
                Json.createObjectBuilder()
                        .add("gameId", oneRecord.getRecordPK().getGameId())
                        .add("playerId", oneRecord.getRecordPK().getPlayerId())
                        //.add("playerName", oneRecord.getPlayer().getName())
                        .add("points", oneRecord.getPoints())
                        .add("trial", oneRecord.getTrial())
                );
            }
            
            playersTableJson = jsonArrayBuilder.build();
        }
        else{ //if this is a new game with 0 current players
            playersTableJson = null;
        }
        
        
        String currentPlayerId = loginHandler.getLoggedInPlayer().getPlayerId().toString();
        
        Boolean zeroPlayer;
        
        if(playersTableJson != null){
            
            zeroPlayer =false;
                    
            JsonObject gameZoneAllInfo = Json.createObjectBuilder()
                .add("game", gameJson)
                .add("players", playersTableJson)
                .add("currentPlayerId", currentPlayerId)
                .add("zeroPlayer", zeroPlayer)
                .build();
            return (Response.ok(gameZoneAllInfo).build());
        }else{
            zeroPlayer=true;
            JsonObject gameZoneAllInfo = Json.createObjectBuilder()
                .add("game", gameJson)
                //.add("players", playersTableJson)
                .add("currentPlayerId", currentPlayerId)
                .add("zeroPlayer", zeroPlayer)
                .build();
            return (Response.ok(gameZoneAllInfo).build());
        }
            

        
        
    }
}