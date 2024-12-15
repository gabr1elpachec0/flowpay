package org.flowpay.request;

import java.util.Objects;
import java.util.UUID;

public class Request {
    private UUID id;
    private String description;
    private String type;
    private String customerName;

    public Request(String description, String customerName) {
        this.id = UUID.randomUUID();
        this.description = description;
        this.customerName = customerName;
    }

    public Request(String description, String type, String customerName) {
        this.id = UUID.randomUUID();
        this.description = description;
        this.type = type;
        this.customerName = customerName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(id, request.id) &&
                Objects.equals(description, request.description) &&
                Objects.equals(type, request.type) &&
                Objects.equals(customerName, request.customerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, type, customerName);
    }

    @Override
    public String toString() {
        return "Solicitação => {" +
                "id=" + id +
                ", descrição='" + description + '\'' +
                ", tipo='" + type + '\'' +
                ", cliente='" + customerName + '\'' +
                '}';
    }
}
