
package repository.db.impl;

import domain.GenericEntity;
import repository.db.DBRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import repository.db.DBConnectionFactory;
import java.sql.SQLException;

public class RepositoryDBGeneric implements DBRepository<GenericEntity> {

    @Override
    public List<GenericEntity> getAll(GenericEntity entity) throws Exception {
        List<GenericEntity> list = null;

        try {
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            String query = "SELECT * FROM " + entity.getTableName();
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            list = entity.getList(resultSet);
            resultSet.close();
            statement.close();
            return list;
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public Long add(GenericEntity entity) throws Exception {
        try {
            String sql;
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            sql = "INSERT INTO " + entity.getTableName() + "(" + entity.getColumnNamesForInsert() + ")"
                    + " VALUES (" + entity.getInsertValues() + ")";
            System.out.println(sql);
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setEscapeProcessing(false);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            } else {
                return 0L;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

  
    @Override
    public void edit(GenericEntity entity) throws Exception {
        try {
            String query;
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            query = "UPDATE " + entity.getTableName() + " SET " + entity.setAttributes() + " WHERE " + entity.getUpdateCondition();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.commit();
            statement.close();
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void deactivate(GenericEntity entity) throws Exception {
        try {
            String query;
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            query = "UPDATE " + entity.getTableName() + " SET " + entity.setAttributesDeactivate() + " WHERE " + entity.getUpdateCondition();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.commit();
            statement.close();
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public List<GenericEntity> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public GenericEntity get(GenericEntity param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public GenericEntity getByID(GenericEntity entity) throws Exception {
        List<GenericEntity> list = null;
        try {
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            String query = "SELECT * FROM " + entity.getTableName() + " WHERE " + entity.getSelectCondition();
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            list = entity.getList(resultSet);

            statement.close();
            return list.get(0);
        } catch (Exception ex) {
            throw new Exception("Doesn't exist!");
        }
    }

    @Override
    public List<GenericEntity> getByCondition(GenericEntity entity) throws Exception {
        List<GenericEntity> list = null;
        try {
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            String query = "SELECT * FROM " + entity.getTableName() + " WHERE " + entity.getSelectCondition();
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            list = entity.getList(resultSet);
            resultSet.close();
            statement.close();
            return list;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public List<GenericEntity> getBySpecificCondition(GenericEntity entity) throws Exception {
        List<GenericEntity> list = null;
        try {
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            String query = "SELECT * FROM " + entity.getTableName() + " WHERE " + entity.getSpecificSelectCondition();
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            list = entity.getList(resultSet);
            resultSet.close();
            statement.close();
            return list;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public Long addWithBlob(GenericEntity entity) throws Exception {
        try {
            String sql;
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            sql = "INSERT INTO " + entity.getTableName() + "(" + entity.getColumnNamesForInsert() + ")"
                    + " VALUES (" + entity.getInsertValuesUnprepared() + ")";
            System.out.println(sql);
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            entity.prepareStatement(statement);
            statement.setEscapeProcessing(false);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            } else {
                return 0L;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

}
