package Databases_dao;

import Databases_modelo.Articulo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLArticuloDAO implements ArticuloDAO {

    final String INSERT = "INSERT INTO articulos(codigo_articulo, descripcion, precio_venta, gastos_envio, tiempo_preparacion) VALUES (?, ?, ?, ?, ?)";
    final String SELECT = "SELECT codigo_articulo, descripcion, precio_venta, gastos_envio, tiempo_preparacion FROM articulos";


    private final Connection conexion;

    public MySQLArticuloDAO(Connection conexion) {
        this.conexion=conexion;
    }

    @Override
    public void create(Articulo insertado) throws DAOException{
        PreparedStatement statement = null;
        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, insertado.getCodigoArticulo());
            statement.setString(2, insertado.getDescripcion());
            statement.setFloat(3, insertado.getPrecioVenta());
            statement.setFloat(4, insertado.getGastosEnvio());
            statement.setInt(5, insertado.getTiempoPreparacion());
            if (statement.executeUpdate() ==0){
                throw new DAOException("No se ha guardado");
            }
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage());
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



    private Articulo convertir(ResultSet resultSet) throws SQLException{
        String codArticulo = resultSet.getString("codigo_articulo");
        String descripcion = resultSet.getString("descripcion");
        Float precio_venta = resultSet.getFloat("precio_venta");
        Float gastos_envio = resultSet.getFloat("gastos_envio");
        Integer tiempo_preparacion = resultSet.getInt("tiempo_preparacion");
        return new Articulo(codArticulo, descripcion, precio_venta, gastos_envio, tiempo_preparacion);
    }


    @Override
    public void update(Articulo modificado) throws DAOException {

    }

    @Override
    public ArrayList<Articulo> readAll() throws DAOException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Articulo> articulos = new ArrayList<>();
        try {
            statement = conexion.prepareStatement(SELECT);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                articulos.add(convertir(resultSet));
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
        return articulos;

    }

    @Override
    public void delete(Articulo eliminado) throws DAOException {

    }

    @Override
    public Articulo readOne(String id) throws DAOException {
        return null;
    }

}

