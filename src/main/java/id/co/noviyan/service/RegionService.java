package id.co.noviyan.service;

import id.co.noviyan.model.Region;
import id.co.noviyan.repository.RegionRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RegionService {

    @Inject
    RegionRepository regionRepository;

    public List<Region> getAll() {
        return regionRepository.listAll();
    }

    public Region getById(Long id) {

        return (Region) regionRepository.findByIdOptional(id).orElseThrow(
                () -> new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                        .entity("Region not found")
                        .build()));
    }

    @Transactional
    public Region create(Region region) {
        if (region.getId() != null) {
            throw new WebApplicationException(Response.status(Response.Status.CONFLICT)
                    .entity("Id must be null")
                    .build());
        }
        regionRepository.persist(region);
        return region;
    }

    @Transactional
    public Region update(Long id, Region region) {
        Region entity = getById(id);
        if (entity!=null) {
            entity.setId(id);
            entity = region;
        }
        return entity;
    }

    @Transactional
    public Region delete(Long id) {
        Region region = getById(id);
        regionRepository.deleteById(id);
        return region;
    }


}
