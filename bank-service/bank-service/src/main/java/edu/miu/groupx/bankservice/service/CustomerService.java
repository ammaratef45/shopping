package edu.miu.groupx.bankservice.service;

import edu.miu.groupx.bankservice.model.Customer;
import edu.miu.groupx.bankservice.model.wrappermodel.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    public Customer createNewCustomer(User newUser, String cardType);

    public Customer findCustomerById(Long id);

    public void deleteCustomer(Customer customer);

    public Customer updateCustomerDetail(Customer customer);

    public List<Customer> getAllCustomer();

}
