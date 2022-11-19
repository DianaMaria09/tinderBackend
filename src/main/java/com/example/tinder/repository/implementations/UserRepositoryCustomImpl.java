package com.example.tinder.repository.implementations;

import com.example.tinder.model.entities.User;
import com.example.tinder.repository.AddressRepository;
import com.example.tinder.repository.UserRepository;
import com.example.tinder.repository.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.sql.*;
import java.util.Properties;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @Autowired
    @Lazy
    UserRepository userRepository;
    Properties databaseProperties = new Properties();
    @Autowired
    AddressRepository addressRepository;

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(databaseProperties.getProperty("spring.datasource.url"),
                databaseProperties.getProperty("spring.datasource.username"), databaseProperties.getProperty("spring.datasource.password"));
    }

    public User findByLogin(String username, String password){
        String SQL = "SELECT * FROM users WHERE username=? AND password=?";
        try(Connection connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL)){
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                User user = new User(resultSet.getLong("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        addressRepository.findById(resultSet.getLong("id_address")).get());
                return user;
            }
            else return null;
        } catch (SQLException e){

        }
        return null;
    }
}
