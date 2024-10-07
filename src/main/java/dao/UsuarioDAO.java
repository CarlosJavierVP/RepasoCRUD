package dao;

import models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements DAO<Usuario> {
    public static final String SELECT_FROM_EJ = "select * from usuario";
    public static final String INSERT_INTO_EJ = "insert into usuario(email, password, is_admin)values(?, ?, ?, ?)";
    public static final String UPDATE_EJ = "update usuario set email=?, password=?, is_admin=? where id=?";
    public static final String DELETE_FROM_EJ = "delete from usuario where id=?";
    public static final String SELECT_FROM_EJ_WHERE_ID = "select * from usuario where id=?";

    private static Connection con = null;

    public UsuarioDAO (Connection c){con = c;}

    @Override
    public List<Usuario> findAll() {
        var lista = new ArrayList<Usuario>();
        try {
            var st = con.createStatement();
            ResultSet rs = st.executeQuery(SELECT_FROM_EJ);

           while (rs.next()){
               Usuario user = new Usuario();
               user.setId(rs.getInt("id"));
               user.setEmail(rs.getString("email"));
               user.setPass(rs.getString("password"));
               user.setAdmin(rs.getBoolean("is_admin"));
               lista.add(user);
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    @Override
    public Usuario findById(Integer id) {
        Usuario user = null;
        try (PreparedStatement ps = con.prepareStatement(SELECT_FROM_EJ_WHERE_ID)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPass(rs.getString("password"));
                user.setAdmin(rs.getBoolean("is_admin"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void save(Usuario usuario) {
        try(PreparedStatement ps = con.prepareStatement(INSERT_INTO_EJ, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,usuario.getEmail());
            ps.setString(2,usuario.getPass());
            ps.setBoolean(3,usuario.getAdmin());

            if (ps.executeUpdate() ==1){
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                usuario.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Usuario usuario) {
        try(PreparedStatement ps = con.prepareStatement(UPDATE_EJ)){
            ps.setString(1,usuario.getEmail());
            ps.setString(2,usuario.getPass());
            ps.setBoolean(3,usuario.getAdmin());
            ps.setInt(4,usuario.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Usuario usuario) {
        try(PreparedStatement ps = con.prepareStatement(DELETE_FROM_EJ)){
           ps.setInt(1,usuario.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
