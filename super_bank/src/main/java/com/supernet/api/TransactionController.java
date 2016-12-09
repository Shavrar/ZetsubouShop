package com.supernet.api;

import com.supernet.config.security.BankUserDetailsService;
import com.supernet.dto.transaction.TransactionDTO;
import com.supernet.dto.transaction.TransactionViewDTO;
import com.supernet.filters.transaction.TransactionFilter;
import com.supernet.model.Invoice.Invoice;
import com.supernet.model.Transaction.Transaction;
import com.supernet.services.BaseCRUDService;
import com.supernet.services.InvoiceService;
import com.supernet.services.TransactionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Calendar;

/**
 * Created by User on 05.12.2016.
 */

@RestController
@RequestMapping(value = "api/transactions")
public class TransactionController extends BaseController
        implements IBaseCRUDOperations<Transaction, TransactionFilter>{
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private BankUserDetailsService bankUserDetailsService;

    @Override
    public BaseCRUDService<Transaction, TransactionFilter> getService() {
        return transactionService;
    }
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_USER')")
    @ApiOperation(value = "View info about invoices")
    @RequestMapping(method = RequestMethod.GET)
    public Page<TransactionViewDTO> getTransactionsByParameters(
            @ApiParam(
                    name = "pageable", value = "Request parameters"
            )
                    Pageable pageable,
            @ApiParam(
                    name = "searchParams", value = "search parameters", required = true
            )
            @Valid TransactionFilter filter) {
        Page<Transaction> transactions = getService().findAll(filter, pageable);
        return transactions
                .map(source -> convertToDto(source, TransactionViewDTO.class));
    }
    @PreAuthorize("isAnonymous()")
    @ApiOperation(value = "Create new transaction")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createInvoice(
            @ApiParam(
                    name = "transactionDTO", value = "info about transaction to create"
            )
            @Valid @RequestBody TransactionDTO transactionDTO) {
        Invoice senderInvoice = invoiceService.findInvoiceByCardNumber(transactionDTO.getSenderCardNumber());
        Invoice reciverInvoice = invoiceService.findInvoiceByCardNumber(transactionDTO.getReciverCardNumber());
        if(senderInvoice == null || reciverInvoice == null || !senderInvoice.getPassword().equals(transactionDTO.getPassword()) || senderInvoice.getAmount() < transactionDTO.getAmount()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
        senderInvoice.setAmount(senderInvoice.getAmount()-transactionDTO.getAmount());
        reciverInvoice.setAmount(reciverInvoice.getAmount()+transactionDTO.getAmount());
        invoiceService.save(senderInvoice);
        invoiceService.save(reciverInvoice);
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setReciverAccountId(reciverInvoice.getUserId());
        transaction.setSenderAccountId(senderInvoice.getUserId());
        transaction.setDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        getService().save(transaction);
        return ResponseEntity.ok().body(null);
    }

    @PreAuthorize("isAnonymous()")
    @ApiOperation(value = "Pay with your life")
    @RequestMapping(method = RequestMethod.POST,path = "/shop")
    public ResponseEntity Shop(
            @ApiParam(
                    name = "transactionDTO", value = "info about transaction to create"
            )
            @Valid @RequestBody TransactionDTO transactionDTO) {
        Invoice senderInvoice = invoiceService.findInvoiceByCardNumber(transactionDTO.getSenderCardNumber());
        if(senderInvoice == null || !senderInvoice.getPassword().equals(transactionDTO.getPassword()) || senderInvoice.getAmount() < transactionDTO.getAmount()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
        senderInvoice.setAmount(senderInvoice.getAmount()-transactionDTO.getAmount());
        invoiceService.save(senderInvoice);
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setReciverAccountId(666);
        transaction.setSenderAccountId(senderInvoice.getUserId());
        transaction.setDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        getService().save(transaction);
        return ResponseEntity.ok().body(null);
    }
}
