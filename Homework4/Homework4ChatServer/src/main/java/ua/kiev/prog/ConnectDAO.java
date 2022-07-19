package ua.kiev.prog;

import ua.kiev.prog.DAO.UserDAOImpl;
import ua.kiev.prog.shared.ConnectionFactory;
import ua.kiev.prog.shared.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ConnectDAO {

    public ConnectDAO() {
    }

    public void ConnectTest(Connection conn) throws SQLException {
            try {
                try (Statement st = conn.createStatement()) {
                    st.execute("DROP TABLE IF EXISTS User");
                    //st.execute("CREATE TABLE Clients (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20) NOT NULL, age INT)");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            UserDAOImpl dao = new UserDAOImpl(conn, "User");
            dao.createTable(User.class);

            for(int i = 1; i < 11; i++){
                User c = new User("test" + i);
                dao.add(c);
                int id = c.getId();
                System.out.println(id);

            }

            List<User> list = dao.getAll(User.class);
            for (User use : list)
                System.out.println(use);

            System.out.println("Client column name and age");
            String[] column = {"username"};
            list = dao.getAll(User.class, column);
            for (User use : list)
                System.out.println(use);



            dao.delete(list.get(0));
    }

}
