package org.flowpay.team;

import org.flowpay.attendant.Attendant;
import org.flowpay.request.Request;

import java.util.*;

public class Team {
    private UUID id;
    private String department;
    private List<Attendant> attendants;
    private Queue<Request> requests;

    public Team(String department) {
        this.id = UUID.randomUUID();
        this.department = department;
        this.attendants = new ArrayList<>();
        this.requests = new LinkedList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Attendant> getAttendants() {
        return attendants;
    }

    public void setAttendants(List<Attendant> attendants) {
        this.attendants = attendants;
    }

    public Queue<Request> getRequests() {
        return requests;
    }

    public void setRequests(Queue<Request> requests) {
        this.requests = requests;
    }
}
