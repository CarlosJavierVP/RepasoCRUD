import dao.UsuarioDAO;

public class Principal {
    public static void main(String[] args) {

        var dao = new UsuarioDAO(JdbcUtils.getCon());
        var todo = dao.findAll();
        System.out.println(todo);

    }
}
