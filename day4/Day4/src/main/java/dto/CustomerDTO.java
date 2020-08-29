/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.BankCustomer;

/**
 *
 * @author jplm
 */
public class CustomerDTO {
    
    private Long customerID;
    private String fullName; 
    private String accountNumber;
    private double balance;

    public CustomerDTO(BankCustomer bc) {
        this.customerID = bc.getId();
        this.fullName = bc.getFirstName() + bc.getLastName();
        this.accountNumber = bc.getAccountNumber();
        this.balance = bc.getBalance();
    }

    public CustomerDTO() {
    }
        
}
