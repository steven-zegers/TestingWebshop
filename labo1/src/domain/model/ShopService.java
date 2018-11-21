package domain.model;

import domain.db.*;

import java.util.List;
import java.util.Properties;

public class ShopService {
    private PersonDb personDb;
    private ProductDb productDb;

    public ShopService() {

    }
    public ShopService(Properties properties) {
        personDb = new PersonDbOnServer(properties);
        productDb = new ProductDbOnServer(properties);
    }

    public Person getPerson(String personId) {
        try {
            Person p = getPersonDb().get(personId);
            return p;
        } catch(DbException e) {
            return null;
        }
    }

    public List<Person> getPersons() {
        return getPersonDb().getAll();
    }

    public void addPerson(Person person) {
        getPersonDb().add(person);
    }

    public void updatePersons(Person person) {
        getPersonDb().update(person);
    }

    public void deletePerson(String id) {
        getPersonDb().delete(id);
    }

    public PersonDb getPersonDb() {
        return personDb;
    }

    public ProductDb getProductDb() {
        return productDb;
    }
    public Product getProduct(int productId) {
        return getProductDb().get(productId);
    }
    public List<Product> getProducts() {
        return getProductDb().getAll();
    }
    public void addProduct(Product product) {
        getProductDb().add(product);
    }
    public void deleteProduct(int id) {
        getProductDb().delete(id);
    }
    public void updateProduct(Product product) {
        getProductDb().update(product);
    }

    public Person getUserIfAuthenticated(String userid, String password) {
        try {
            Person p = getPersonDb().get(userid);
            if(p.isCorrectPassword(password)) {
                return p;
            } else return null;
        } catch (DbException e) {
            return null;
        }
    }
}
