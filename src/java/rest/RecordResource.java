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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import login.LoginHandler;

/**
 *
 * @author Su-Ting Chen
 */
@RequestScoped
@Path("/rank")
public class RecordResource {
    
    @EJB GameBean gameBean;
    @EJB RecordBean recordBean;
    @Inject LoginHandler loginHandler;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(){
        
        List<Object[]> result = recordBean.getTopScore();
        
        if(result!=null){
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            for(Object[] oneResult : result){
                jsonArrayBuilder.add(
                Json.createObjectBuilder()
                .add("playerId", oneResult[0].toString())
                .add("score", oneResult[1].toString())
                );
            }
            
            JsonArray topScoreJsonArray = jsonArrayBuilder.build();
            return (Response.ok(topScoreJsonArray).build());
            
        }else{
            System.out.println("no result");
            return null;
        }

    }
    
    
}
