package com.ccechallenge.utils;

public interface UtilityConvert<Entity, Request, Response>{
    public Entity convertRequestForEntity(Request request);

    public Response convertEntityForResponse(Entity entity);
}
