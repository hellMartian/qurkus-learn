package id.co.noviyan.repository;

import id.co.noviyan.model.Region;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RegionRepository implements PanacheRepository<Region> {
}
