package id.co.noviyan.service;

import id.co.noviyan.model.Country;
import id.co.noviyan.repository.CountryRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class CountryService {

    @Inject
    CountryRepository countryRepository;

    public List<Country> getAll() {
        return countryRepository.listAll();
    }

    public Country getById(Long id) {
        return countryRepository.findByIdOptional(id).orElseThrow(()
                -> new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                .entity("Id Not Found")
                .build()));
    }

    @Transactional
    public Country create(Country country) {
        if (country.getId() != null) {
            throw new WebApplicationException(Response.status(Response.Status.CONFLICT)
                    .entity("Id Must be null").build());
        }
//        System.out.println(country);
        countryRepository.persist(country);
        return country;
    }

    @Transactional
    public Country update(Long id, Country country) {
        Country entity = getById(id);
        if (entity!=null) {
            entity.setId(id);
            entity = country;
        }
        return entity;
    }

    public Country delete(Long id) {
        Country entity = getById(id);
        countryRepository.deleteById(id);
        return entity;
    }
}
