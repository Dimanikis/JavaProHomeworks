package ua.kiev.prog.DAO;

import ua.kiev.prog.shared.User;

import java.sql.Connection;

public class UserDAOImpl extends AbstractDAO<User> {
    public UserDAOImpl(Connection conn, String table) {
        super(conn, table);
    }
}
