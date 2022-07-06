package id.co.noviyan.repository;

import id.co.noviyan.model.Country;
import io.quarkus.hibernate.orm.panache.PanacheRepository;


import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CountryRepository implements PanacheRepository<Country> {
}
