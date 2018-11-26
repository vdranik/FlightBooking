package com.vdranik.location.service;

import com.vdranik.location.entities.Location;
import com.vdranik.location.repos.LocationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

  @Autowired
  LocationRepository repository;

  @Override
  public Location saveLocation(Location location) {
    return repository.save(location);
  }

  @Override
  public Location updateLocation(Location location) {
    return repository.save(location);
  }

  @Override
  public void deleteLocation(Location location) {
    repository.delete(location);
  }

  @Override
  public Optional<Location> getLocationById(int id) {
    return repository.findById(id);
  }

  @Override
  public List<Location> getAllLocations() {
    return repository.findAll();
  }

  public LocationRepository getRepository() {
    return repository;
  }

  public void setRepository(LocationRepository repository) {
    this.repository = repository;
  }
}
