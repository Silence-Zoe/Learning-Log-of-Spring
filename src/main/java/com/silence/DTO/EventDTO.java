package com.silence.DTO;

import java.util.HashMap;
import java.util.Map;

public class EventDTO {
    private String topic;
    private Integer userId;
    private Integer entityType;
    private Integer entityId;
    private Integer entityUserId;
    private Map<String, Object> data = new HashMap<>();

    public String getTopic() {
        return topic;
    }

    public EventDTO setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public EventDTO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getEntityType() {
        return entityType;
    }

    public EventDTO setEntityType(Integer entityType) {
        this.entityType = entityType;
        return this;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public EventDTO setEntityId(Integer entityId) {
        this.entityId = entityId;
        return this;
    }

    public Integer getEntityUserId() {
        return entityUserId;
    }

    public EventDTO setEntityUserId(Integer entityUserId) {
        this.entityUserId = entityUserId;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public EventDTO setData(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
}
