package dao;

import model.Administrator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryAdministratorDao implements AdministratorDao {
    private List<Administrator> adminList;

    public InMemoryAdministratorDao() {
        adminList = new ArrayList<>();
    }

    @Override
    public void add(Administrator administrator) {
        adminList.add(administrator);
    }

    @Override
    public List<Administrator> findAll() {
        return Collections.unmodifiableList(adminList);
    }

}
