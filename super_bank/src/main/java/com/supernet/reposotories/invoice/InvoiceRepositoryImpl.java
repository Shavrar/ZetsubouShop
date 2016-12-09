package com.supernet.reposotories.invoice;

import com.querydsl.core.BooleanBuilder;
import com.supernet.filters.invoice.InvoiceFilter;
import com.supernet.model.Invoice.Invoice;
import com.supernet.reposotories.BaseRepositoryImpl;

/**
 * Created by User on 04.12.2016.
 */
public class InvoiceRepositoryImpl extends BaseRepositoryImpl<Invoice, InvoiceFilter>
        implements InvoiceRepositoryCustom {
        public InvoiceRepositoryImpl() {
            super(Invoice.class);
        }

        @Override
        protected BooleanBuilder getQueryPredicate(InvoiceFilter searchFilter) {
            //QInvoice invoice = QInvoice.invoice;
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            return booleanBuilder;
        }
}
