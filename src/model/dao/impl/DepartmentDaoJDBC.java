package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO department " +
                        "(name) " +
                        "values " +
                        "(?)",
                Statement.RETURN_GENERATED_KEYS);) {
            
            st.setString(1, obj.getName());

            int rows = st.executeUpdate();                
            if (rows > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    obj.setId(rs.getInt(1));
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(Department obj) {
        try (PreparedStatement st = conn.prepareStatement(
                "UPDATE department " +
                        "SET name = ? " +
                        "where id = ?");) {

            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void deleteByID(Integer id) {
        try (PreparedStatement st = conn.prepareStatement(
                "DELETE FROM department WHERE Id = ? ")) {

            st.setInt(1, id);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Department findById(Integer id) {
        ResultSet rs = null;
        try (PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM department " +
                        "where id = ?")) {

            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Department dep = instantiateDepartment(rs);

                return dep;
            }

            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Department> findAll() {
        ResultSet rs = null;
        try (PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM department " +
                        "ORDER By Name")) {

            rs = st.executeQuery();

            List<Department> list = new ArrayList<>();

            while (rs.next()) {
                Department dep = instantiateDepartment(rs);
                list.add(dep);
            }

            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }
}
