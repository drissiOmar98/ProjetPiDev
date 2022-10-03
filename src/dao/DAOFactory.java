package dao;

import dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() { }
    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory(); }
        return daoFactory; }
    public SuperDAO getDAO(DAOFactory.DAOTypes Types) {
        switch (Types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            default:
                return null; } }
    public enum DAOTypes {
        CUSTOMER,
    }
}