package dao;

import models.Usuario;

import java.util.List;

public interface DAO <T>{

    public List<T> findAll();
    public Usuario findById(Integer id);
    public void save(T t);
    public void update(T t);
    public void delete(T t);



}
