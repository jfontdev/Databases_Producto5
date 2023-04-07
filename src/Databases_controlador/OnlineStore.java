package Databases_controlador;

import Databases_dao.DAOException;
import Databases_vista.GestionOS;

import java.sql.SQLException;

public class OnlineStore {
    public static void main(String[] args) throws DAOException, SQLException {
        GestionOS gestion = new GestionOS();
        gestion.inicio();
    }
}