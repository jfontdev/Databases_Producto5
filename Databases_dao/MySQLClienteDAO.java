package Databases_dao;

import Databases_modelo.Cliente;
import enums.ClienteTipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLClienteDAO implements ClienteDAO {
    final String INSERT = "INSERT INTO clientes(nombre, apellidos, domicilio, nif, email , tipo,tasaCliente, descuentoCliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    final String SELECT = "SELECT nombre, apellidos, domicilio, nif, email , tipo, tasaCliente, descuentoCliente FROM clientes";


    private final Connection conexion;

    public MySQLClienteDAO(Connection conexion) {
        this.conexion=conexion;
    }

    @Override
    public void create(Cliente insertado) throws DAOException{
        PreparedStatement statement = null;
        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, insertado.getNombre());
            statement.setString(2, insertado.getApellidos());
            statement.setString(3, insertado.getDomicilio());
            statement.setString(4, insertado.getNif());
            statement.setString(5, insertado.getEmail());
            statement.setString(6, insertado.getTipo().toString());
            statement.setFloat(7, insertado.getTasaCliente());
            statement.setFloat(8,insertado.getDescuentoCliente());
            if (statement.executeUpdate() ==0){
                throw new DAOException("Puede que no se haya guardado");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            if (statement != null){
                try {
                    statement.close();
                }catch (SQLException ex){
                    throw new DAOException ("Error en SQL", ex);
                }
            }
        }
    }



    private Cliente convertir(ResultSet resultSet) throws SQLException{
        String nombre = resultSet.getString("nombre");
        String apellidos = resultSet.getString("apellidos");
        String domicilio = resultSet.getString("domicilio");
        String nif = resultSet.getString("nif");
        String email = resultSet.getString("email");
        ClienteTipo tipo =  ClienteTipo.valueOf(resultSet.getString("tipo"));
        return new Cliente(nombre, apellidos, domicilio, nif, email, tipo);
    }


    @Override
    public ArrayList<Cliente> readAll() throws DAOException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            statement = conexion.prepareStatement(SELECT);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                clientes.add(convertir(resultSet));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error en SQL", ex);
                }
            }
            if (statement != null) {
                try{
                    statement.close();
                }catch (SQLException ex){
                    throw new DAOException("Error en SQL", ex);
                }
            }
        }
        return clientes;

    }

    @Override
    public void update(Cliente modificado) throws DAOException {

    }

    @Override
    public void delete(Cliente eliminado) throws DAOException {

    }

    @Override
    public Cliente readOne(String id) throws DAOException {
        return null;
    }

}

