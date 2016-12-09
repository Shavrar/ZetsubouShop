package com.supernet.api;

import com.supernet.config.security.BankUserDetailsService;
import com.supernet.dto.invoice.InvoiceDTO;
import com.supernet.dto.invoice.InvoiceViewDTO;
import com.supernet.filters.invoice.InvoiceFilter;
import com.supernet.model.Invoice.Invoice;
import com.supernet.services.BaseCRUDService;
import com.supernet.services.InvoiceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by User on 04.12.2016.
 */
@PreAuthorize("isAuthenticated() and hasRole('ROLE_USER')")
@RestController
@RequestMapping(value = "api/invoices")
public class InvoiceController extends BaseController
        implements IBaseCRUDOperations<Invoice, InvoiceFilter> {


    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private BankUserDetailsService bankUserDetailsService;

    @Override
    public BaseCRUDService<Invoice, InvoiceFilter> getService() {
        return invoiceService;
    }


    @ApiOperation(value = "View info about invoice")
    @RequestMapping(value = "/{invoiceId}", method = RequestMethod.GET)
    public InvoiceViewDTO findById(
            @ApiParam(
                    name = "invoiceId", value = "The Id of category", required = true
            )
            @PathVariable Long invoiceId) {
        Invoice invoice = findOne(invoiceId);
        return convertToDto(invoice, InvoiceViewDTO.class);
    }

    /**
     * Get info about all invoices
     *
     * @param pageable select params
     * @param filter   filter params
     * @return List<Invoice>, info about all invoices to display
     */
    @ApiOperation(value = "View info about invoices")
    @RequestMapping(method = RequestMethod.GET)
    public List<Invoice> findInvoicesByParameters(
            @ApiParam(
                    name = "pageable", value = "Request parameters"
            )
                    Pageable pageable,
            @ApiParam(
                    name = "searchParams", value = "search parameters", required = true
            )
            @Valid InvoiceFilter filter) {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = user.getUsername();
        Long id = bankUserDetailsService.getUserIdByUsername(userName);
        Page<Invoice> invoices = getService().findAll(filter, pageable);
        LinkedList<Invoice> ok = new LinkedList<>();
        for (Invoice a : invoices.getContent()) {
                if(a.getUserId() == id){
                    ok.add(a);
                }
        }
        return ok;
    }

    /**
     * Delete info about invoice
     *
     * @param invoiceId invoiceId
     */
    @ApiOperation(value = "Delete info about invoice")
    @RequestMapping(value = "/{invoiceId}", method = RequestMethod.DELETE)
    public void deleteInvoice(
            @ApiParam(
                    name = "invoiceId", value = "The Id of invoice to delete"
            )
            @PathVariable Long invoiceId) {
        getService().delete(invoiceId);
    }

    @ApiOperation(value = "Create new invoice")
    @RequestMapping(method = RequestMethod.POST)
    public void createInvoice(
            @ApiParam(
                    name = "invoiceDTO", value = "info about account to create"
            )
            @Valid @RequestBody InvoiceDTO invoiceDTO) {

        Invoice invoice = new Invoice();
        invoice.setCardNumber(invoiceDTO.getCardNumber());
        invoice.setPassword(invoiceDTO.getPassword());
        invoice.setAmount(invoiceDTO.getAmount());
        invoice.setExpireDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = user.getUsername();
        invoice.setUserId(bankUserDetailsService.getUserIdByUsername(userName));
        getService().save(invoice);
    }


}
