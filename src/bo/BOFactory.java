package bo;

import bo.custom.Impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }
    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();

        }
        return boFactory;
    }
    public enum BOTypes {
        CUSTOMER;
    }
    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerBOImpl() ;
                }
        return null;
    }

    }