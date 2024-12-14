package org.flowpay.attendant;

import org.flowpay.request.Request;

import java.util.Queue;
import java.util.UUID;

public class Attendant {
    private UUID id;
    private String name;
    private Queue<Request> requests;

    public Attendant(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Queue<Request> getRequests() {
        return requests;
    }

    public void setRequests(Queue<Request> requests) {
        this.requests = requests;
    }
}
