package it.epicode.library.dao;

import it.epicode.library.entities.Loan;

import java.util.List;

public interface LoanDao {

    void addLoan(Loan loan);

    List<Loan> getLoansByUserTessera(int cardNumber);

    List<Loan> getOverdueLoans();

}
