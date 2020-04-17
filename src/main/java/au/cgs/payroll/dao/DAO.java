package au.cgs.payroll.dao;

import au.cgs.payroll.entity.Entity;

import java.util.List;

public interface DAO <T extends Entity>{

    void insert(T entity);
    void delete(int id);
    void edit(T entity);
    List<T> selectAll();
    T findByID(int id);
}
