package Databases_dao;


import Databases_modelo.Articulo;
import Databases_modelo.Cliente;
import Databases_modelo.Pedido;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MySQLPedidoDAO implements PedidoDAO {

    final String INSERT = "INSERT INTO pedidos(numeroPedidoId, numeroPedido, cliente, articulo, cantidadArticulo, fechaPedido, fechaEnvio, tiempoPreparacion, seHaEnviado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final String SELECT = "SELECT numeroPedidoId, numeroPedido, cliente, articulo, cantidadArticulo, fechaPedido, fechaEnvio, tiempoPreparacion, seHaenviado FROM pedidos";


    private final Connection conexion;

    public MySQLPedidoDAO(Connection conexion) {
        this.conexion=conexion;
    }

    @Override
    public void create(Pedido insertado) throws DAOException{
        PreparedStatement statement = null;
        try {
            statement = conexion.prepareStatement(INSERT);
            statement.setInt(1, insertado.getNumeroPedidoId());
            statement.setString(2, insertado.getNumeroPedido());
            statement.setString(3, String.valueOf(insertado.getCliente()));
            statement.setString(4, String.valueOf(insertado.getArticulo()));
            statement.setInt(5, insertado.getCantidadArticulo());
            statement.setTimestamp(6, Timestamp.valueOf(insertado.getFechaPedido()));
            statement.setTimestamp(7, Timestamp.valueOf(insertado.getFechaEnvio()));
            statement.setInt(8, insertado.getTiempoPreparacion());
            statement.setBoolean(9, insertado.getSeHaEnviado());
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

    @java.lang.Override
    public Pedido readOne(String id) throws DAOException {
        return null;
    }

    // Hay que modificar esta funcion, de momento esta arreglada para que el resto(articulos/clientes).
    private Pedido convertir(ResultSet resultSet) throws SQLException{
        int numeroPedidoId = resultSet.getInt("numeroPedidoId");
        String numeroPedido = resultSet.getString("numeroPedido");
        String cliente = resultSet.getString("cliente");
        String articulo = resultSet.getString("articulo");
        int cantidadArticulo = resultSet.getInt("cantidadArticulo");
        LocalDateTime fechaPedido = resultSet.getTimestamp("fechaPedido").toLocalDateTime();
        LocalDateTime fechaEnvio = resultSet.getTimestamp("fechaEnvio").toLocalDateTime();
        int tiempoPreparacion = resultSet.getInt("tiempoPreparacion");
        boolean seHaenviado = resultSet.getBoolean("seHaenviado");
        Cliente Cliente = null;
        Articulo Articulo = null;
        return new Pedido(Cliente, Articulo,  cantidadArticulo);
    }

    @Override
    public void update(Pedido modificado) throws DAOException {

    }

    @Override
    public ArrayList<Pedido> readAll() throws DAOException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try {
            statement = conexion.prepareStatement(SELECT);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                pedidos.add(convertir(resultSet));
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
        return pedidos;

    }

    @Override
    public void delete(Pedido eliminado) throws DAOException {

    }
}

