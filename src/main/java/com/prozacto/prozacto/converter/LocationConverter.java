package com.prozacto.prozacto.converter;


import com.prozacto.prozacto.Entity.Location.Location;
import com.prozacto.prozacto.Entity.User.User;
import com.prozacto.prozacto.model.LocationDto;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationConverter implements BaseConverter<Location, LocationDto> {

    @Override
    public Location convertModelToEntity(LocationDto model , User user) {
        Location location = Location.builder()
                .latitude(model.getLatitude())
                .longitude(model.getLongitude())
                .landmark(model.getLandmark())
                .address(model.getAddress())
                .build();

        return location;
    }

    @Override
    public LocationDto convertEntityToModel(Location entity) {
        return LocationDto.builder()
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .landmark(entity.getLandmark())
                .address(entity.getAddress())
                .build();
    }

    @Override
    public List<Location> convertModelToEntity(List<LocationDto> modelList , User user) {
        if(CollectionUtils.isEmpty(modelList))
            return new ArrayList<>();
        return modelList.stream().map(m -> convertModelToEntity(m,user)).collect(Collectors.toList());
    }

    @Override
    public List<LocationDto> convertEntityToModel(List<Location> entityList) {
        if(CollectionUtils.isEmpty(entityList))
            return new ArrayList<>();
        return entityList.stream().map(this::convertEntityToModel).collect(Collectors.toList());
    }

    @Override
    public void applyChanges(Location entity, LocationDto Model, User user) {

    }
}
