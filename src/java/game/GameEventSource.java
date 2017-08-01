/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.SseFeature;

/**
 *
 * @author Su-Ting Chen
 */
@RequestScoped
@Path("/gameEvent")
public class GameEventSource {
    
    @Inject GameParticipants participants;
    
    @GET
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public Response get(){
        
        
        EventOutput eo = new EventOutput();
        System.out.println(">> new participant");
        participants.add(eo);

        return (Response.ok(eo).build());
    }
    
}
