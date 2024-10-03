package dao;

import models.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements DAO<Usuario> {

    private static Connection con = null;

    public UsuarioDAO (Connection c){con = c;}

    @Override
    public List<Usuario> findAll() {
        var lista = new ArrayList<Usuario>();
        try {
            var st = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return
    }

    @Override
    public Usuario findById(Integer id) {
        return null;
    }

    @Override
    public void save(Usuario usuario) {

    }

    @Override
    public void update(Usuario usuario) {

    }

    @Override
    public void delete(Usuario usuario) {

    }
}
