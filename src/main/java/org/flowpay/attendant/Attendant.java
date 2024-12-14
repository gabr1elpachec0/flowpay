package org.flowpay.attendant;

import org.flowpay.request.Request;

import java.util.*;

public class Attendant {
    private UUID id;
    private String name;
    private List<Request> requests;

    public Attendant(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.requests = new ArrayList<>();
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

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendant attendant = (Attendant) o;
        return Objects.equals(id, attendant.id) &&
                Objects.equals(name, attendant.name) &&
                Objects.equals(requests, attendant.requests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, requests);
    }

    @Override
    public String toString() {
        return "Atendente => {" +
                "id=" + id +
                ", nome='" + name + '\'' +
                ", chamados=" + requests +
                '}';
    }
}
