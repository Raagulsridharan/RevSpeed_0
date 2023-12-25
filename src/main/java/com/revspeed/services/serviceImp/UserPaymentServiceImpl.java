package com.revspeed.services.serviceImp;

import com.revspeed.dao.UserPaymentDAO;
import com.revspeed.dao.daoImp.UserPaymentDAOImpl;
import com.revspeed.domain.UserPayment;
import com.revspeed.services.UserPaymentService;

public class UserPaymentServiceImpl implements UserPaymentService {

    @Override
    public UserPayment save(UserPayment userPayment) {
        UserPaymentDAO userPaymentDAO = new UserPaymentDAOImpl();
        return userPaymentDAO.save(userPayment);
    }
}
