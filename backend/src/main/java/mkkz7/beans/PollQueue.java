package mkkz7.beans;

import jakarta.ejb.Singleton;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.core.Response;
import lombok.Setter;
import mkkz7.utils.DTO.PointResultDTO;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Singleton
public class PollQueue {
    private final BlockingQueue<AsyncResponse> queue = new LinkedBlockingQueue<>();

    public void add(AsyncResponse response){
        response.setTimeout(30, TimeUnit.SECONDS);
        response.setTimeoutHandler(r -> {
            System.out.println("1");
            r.resume(Response.noContent().build());
        });
        queue.offer(response);
    }

    public void notifyAllClients(List<PointResultDTO> result){
        AsyncResponse client;
        while((client = queue.poll()) != null){
            try {
                System.out.println("Notifying clients");
                client.resume(Response.ok(result).build());
            } catch (Exception e) {

            }
        }
    }

    public void clear() {
        System.out.println("[PollQueue] Clearing cached points");
        AsyncResponse client;
        while((client = queue.poll()) != null){
            try {
                client.resume(Response.ok(Collections.emptyList()).build());
            } catch (Exception e) {

            }
        }
    }
}
