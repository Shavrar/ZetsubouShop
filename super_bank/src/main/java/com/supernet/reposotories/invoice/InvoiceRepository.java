package com.supernet.reposotories.invoice;

import com.supernet.filters.invoice.InvoiceFilter;
import com.supernet.model.Invoice.Invoice;
import com.supernet.reposotories.BaseRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by User on 04.12.2016.

 */
public interface InvoiceRepository
        extends JpaRepository<Invoice,Long>,
        QueryDslPredicateExecutor<Invoice>,InvoiceRepositoryCustom {
        Invoice findInvoiceByCardNumber(Long name);
}
interface InvoiceRepositoryCustom extends BaseRepositoryCustom<Invoice, InvoiceFilter> {

}
