package com.prozacto.prozacto.converter;

import com.prozacto.prozacto.Entity.User.User;
import com.prozacto.prozacto.model.UserDto;

import java.util.Collection;
import java.util.List;

public interface BaseConverter<E,M> {

    E convertModelToEntity(M model, User user);

    M convertEntityToModel(E entity);

    List<E> convertModelToEntity(List<M> modelList, User user);

    List<M> convertEntityToModel(List<E> entityList);

    void applyChanges(E entity, M Model, User user);
}
