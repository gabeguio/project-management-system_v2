package com.nashss.se.musicplaylistservice.activity.requests;

import com.nashss.se.musicplaylistservice.activity.results.GetTicketResult;

public class GetTicketRequest {
    private final String projectId;
    private final String ticketId;

    private GetTicketRequest(String projectId, String ticketId) {
        this.projectId = projectId;
        this.ticketId = ticketId;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getTicketId() {
        return ticketId;
    }

    @Override
    public String toString() {
        return "GetTicketRequest{" +
            "projectId='" + projectId + '\'' +
            ", ticketId='" + ticketId + '\'' +
            '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String projectId;
        private String ticketId;

        public GetTicketRequest.Builder withId(String projectId) {
           this.projectId = projectId;
           return this;
        }
        public GetTicketRequest.Builder withTicketId (String ticketId) {
            this.ticketId = ticketId;
            return this;
        }

        public GetTicketRequest build() {
            return new GetTicketRequest(projectId, ticketId);
        }
    }
}



