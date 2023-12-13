package be.pxl.hotel.service;

import be.pxl.hotel.api.response.TransactionsOverviewDTO;
import be.pxl.hotel.api.response.WalletDTO;
import be.pxl.hotel.domain.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BankService {

    private final Wallet wallet;

    public BankService(@Value("${wallet.initialAmount}") double initialAmount) {
        this.wallet = new Wallet(initialAmount);
    }

    public WalletDTO getWallet() {
        return new WalletDTO(wallet.getAmount());
    }
    
    public Transaction createTransaction(double amount, TransactionType transactionType, String description) {
        if (transactionType == TransactionType.PAYMENT) {
            if (wallet.getAmount() >= amount) {
                wallet.registerPayment(amount, description);
                return wallet.getTransactions().get(0); // Assuming the new transaction is always the first in the list
            } else {
                throw new UnsufficientMoneyException("Not enough money to make the payment");
            }
        } else if (transactionType == TransactionType.RECEIPT) {
            wallet.registerReceipt(amount, description);
            return wallet.getTransactions().get(0); // Assuming the new transaction is always the first in the list
        } else {
            throw new IllegalArgumentException("Invalid transaction type");
        }
    }

    public List<Transaction> getTransactions() {
        //TODO Sort on transaction dateTime
        return wallet.getTransactions();
    }

    public TransactionsOverviewDTO getTransactionsOverview() {
        TransactionsOverviewDTO transactionsOverviewDTO = new TransactionsOverviewDTO();

        List<Transaction> totalPayments = wallet.getTransactions().stream()
                .filter(t -> t.getTransactionType() == TransactionType.PAYMENT)
                .collect(Collectors.toList());

        List<Transaction> totalReceipts = wallet.getTransactions().stream()
                .filter(t -> t.getTransactionType() == TransactionType.RECEIPT)
                .collect(Collectors.toList());

        transactionsOverviewDTO.setPayments(totalPayments);
        transactionsOverviewDTO.setPayments(totalReceipts);

        transactionsOverviewDTO.setTotalPayments(totalPayments.stream().mapToDouble(Transaction::getAmount).sum());
        transactionsOverviewDTO.setTotalReceipts(totalReceipts.stream().mapToDouble(Transaction::getAmount).sum());

        transactionsOverviewDTO.setAveragePayments(totalPayments.stream().mapToDouble(Transaction::getAmount).average().orElse(0.0));
        transactionsOverviewDTO.setAverageReceipts(totalReceipts.stream().mapToDouble(Transaction::getAmount).average().orElse(0.0));

        return transactionsOverviewDTO;
    }

}
