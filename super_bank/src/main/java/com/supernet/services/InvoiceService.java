package com.supernet.services;

import com.supernet.filters.invoice.InvoiceFilter;
import com.supernet.model.Invoice.Invoice;
import com.supernet.reposotories.BaseRepositoryCustom;
import com.supernet.reposotories.invoice.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by User on 04.12.2016.
 */
@Service
public class InvoiceService implements BaseCRUDService<Invoice, InvoiceFilter> {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public JpaRepository<Invoice, Long> getRepository() {
        return invoiceRepository;
    }

    @Override
    public BaseRepositoryCustom<Invoice, InvoiceFilter> getRepositoryCustom() {
        return invoiceRepository;
    }

    public Invoice findInvoiceByCardNumber(Long cardnumber) {
        return invoiceRepository.findInvoiceByCardNumber(cardnumber);
    }

}