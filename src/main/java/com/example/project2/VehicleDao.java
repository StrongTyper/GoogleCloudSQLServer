package com.example.project2;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class VehicleDao {
    //PersistenceContext annotation used to specify there is a database source.
    // EntityManager is used to create and remove persistent entity instances,
    // to find entities by their primary key, and to query over entities.
    @PersistenceContext
    private EntityManager entityManager;

    //Insert greeting into the database.
    public void create(Vehicle vehicle) {
        entityManager.persist(vehicle);
        return;
    }

    //Return the greeting with the passed-in id.
    public Vehicle getById(int id) {
        return entityManager.find(Vehicle.class,id);
    }

    public Vehicle update(Vehicle vehicle) {
        return entityManager.merge(vehicle);
    }

    public Vehicle find(int id) {
        Vehicle vehicle = entityManager.find(Vehicle.class,id);
        return vehicle;
    }
    public void delete(Vehicle vehicle) {
        entityManager.remove(vehicle);
    }

    public List getLastTen() {
        Query query = entityManager.createNativeQuery("SELECT v.id, v.makeModel, v.year, v.retailPrice FROM vehicles v ORDER BY v.id desc");
        List<Vehicle> vehList = query.setMaxResults(10).getResultList();
        return vehList;

    }

}
