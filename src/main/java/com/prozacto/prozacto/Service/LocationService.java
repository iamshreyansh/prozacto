package com.prozacto.prozacto.Service;

import com.prozacto.prozacto.Entity.Location.Location;
import com.prozacto.prozacto.converter.LocationConverter;
import com.prozacto.prozacto.dao.LocationDao;
import com.prozacto.prozacto.model.LocationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationService {

    @Autowired private LocationDao locationDao;
    @Autowired private LocationConverter locationConverter;

    public LocationDto getLocationById(Integer id)
    {
        Optional<Location> location = locationDao.findById(id);
        if(location.isPresent())
            return locationConverter.convertEntityToModel(location.get());
        else
            return null;
    }
}
