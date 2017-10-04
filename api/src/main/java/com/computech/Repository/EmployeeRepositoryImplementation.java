package com.computech.Repository;

import com.computech.Coordinates;
import com.computech.Entity.Address;
import com.computech.Entity.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepositoryImplementation implements EmployeeRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<Employee> getAll() {

        TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.getAll", Employee.class);

        return query.getResultList();
    }

    public Employee getOne(String empId) {
        return entityManager.find(Employee.class,empId);
    }

    public Employee create(Employee employee) {
        Address address = employee.getAddress();
        entityManager.persist(address);
        employee.setAddress(address);
        entityManager.persist(employee);

        return employee;
    }

    public Employee update(Employee employee) {
        Address address = employee.getAddress();
        entityManager.merge(address);
        employee.setAddress(address);
        entityManager.merge(employee);
        return employee;
    }

    public void delete(Employee employee) {
        Address address = employee.getAddress();
        entityManager.remove(employee);
        entityManager.remove(address);
    }

    public Employee finByEmail(String email) {
        TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findByEmail",Employee.class);
        query.setParameter("paramEmail",email);
        List<Employee> result= query.getResultList();
        if(result.size()==1)
        {
            return result.get(0);
        }
        else
            return null;
    }

    public List<Employee> getNearest(String lat, String lng) {
        List<Employee> all = getAll();
        if(all.size() < 3)
        {
            return all;
        }
        double first = Double.MAX_VALUE;
        double second = Double.MAX_VALUE;
        double latitude = Double.parseDouble(lat);
        double longitude = Double.parseDouble(lng);
        List<Employee> nearest = new ArrayList<Employee>();
        for(Employee emp:all)
        {
            double distance = getDistance(latitude,longitude,Double.parseDouble(emp.getAddress().getLatitude()),Double.parseDouble(emp.getAddress().getLongitude()));
            if(distance<first)
            {
                second = first;
                first = distance;

                if(nearest.size()>1) {
                    Employee temp = nearest.get(0);
                    nearest.set(1, temp);
                    nearest.set(0, emp);
                }
                else
                {
                    if(nearest.size()==1)
                    {
                        Employee temp = nearest.get(0);
                        nearest.add(temp);
                        nearest.set(0, emp);
                    }
                    else
                    {
                        nearest.add(emp);
                    }
                }
            }

            else if(distance<second && distance!=first)
            {
                second=distance;
                nearest.set(1,emp);
            }
        }
        return nearest;
    }

    public double getDistance(double lat1, double lng1, double lat2, double lng2){
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lng2 - lng1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }
}
