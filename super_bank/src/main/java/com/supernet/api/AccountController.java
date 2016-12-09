package com.supernet.api;


import com.supernet.config.security.BankUserDetails;
import com.supernet.config.security.BankUserDetailsService;
import com.supernet.dto.account.AccountDTO;
import com.supernet.dto.account.AccountRegisterDTO;
import com.supernet.dto.account.AccountViewDTO;
import com.supernet.filters.account.AccountFilter;
import com.supernet.model.accounts.Account;
import com.supernet.model.accounts.Role;
import com.supernet.services.AccountService;
import com.supernet.services.BaseCRUDService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;


@RestController
@RequestMapping(value = "api/accounts")
public class AccountController
        extends BaseController
        implements IBaseCRUDOperations<Account, AccountFilter> {

    @Autowired
    private AccountService accountService;
    @Autowired
    private BankUserDetailsService bankUserDetailsService;

    @Override
    public BaseCRUDService<Account, AccountFilter> getService() {
        return accountService;
    }

    /**
     * Get info about one account
     *
     * @param accountId accountId
     * @return AccountViewDTO, info about account to display
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "View info about account")
    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
    public AccountViewDTO findAccountById(
            @ApiParam(
                    name = "accountId", value = "The Id of category", required = true
            )
            @PathVariable Long accountId) {
        Account account = findOne(accountId);
        return convertToDto(account, AccountViewDTO.class);
    }

    /**
     * Get info about all accounts
     *
     * @param pageable select params
     * @param filter   filter params
     * @return Page<AccountViewDTO>, info about all accounts to display
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "View info about accounts")
    @RequestMapping(method = RequestMethod.GET)
    public Page<AccountViewDTO> findAccountsByParameters(
            @ApiParam(
                    name = "pageable", value = "Request parameters"
            )
                    Pageable pageable,
            @ApiParam(
                    name = "searchParams", value = "search parameters", required = true
            )
            @Valid AccountFilter filter) {
        Page<Account> accounts = accountService.findAll(filter, pageable);
        return accounts
                .map(source -> convertToDto(source, AccountViewDTO.class));
    }

    /**
     * Delete info about account
     *
     * @param accountId accountId
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "Delete info about account")
    @RequestMapping(value = "/{accountId}", method = RequestMethod.DELETE)
    public void deleteAccount(
            @ApiParam(
                    name = "accountId", value = "The Id of account to delete"
            )
            @PathVariable Long accountId) {
        accountService.delete(accountId);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "Update info about account")
    @RequestMapping(value = "/{accountId}", method = RequestMethod.PUT)
    public void updateAccount(
            @ApiParam(
                    name = "accountId", value = "The Id of account to update", required = true
            )
            @PathVariable Long accountId,
            @ApiParam(
                    name = "accountDTO", value = "info about account to update"
            )
            @Valid @RequestBody AccountDTO accountDTO) {
        Account account = accountService.findOne(accountId);
        account.setFirstName(accountDTO.getFirstName());
        account.setLastName(accountDTO.getLastName());
        accountService.save(account);
    }
    @PreAuthorize("isAnonymous()")
    @ApiOperation(value = "Register new common user account")
    @RequestMapping(method = RequestMethod.POST)
    public void registerAccount(
            @ApiParam(
                    name = "accountRegisterDTO", value = "info about account to register"
            )
            @Valid @RequestBody AccountRegisterDTO accountRegisterDTO) {
        Account account = new Account();
        account.setFirstName(accountRegisterDTO.getFirstName());
        account.setLastName(accountRegisterDTO.getLastName());
        account.setLogin(accountRegisterDTO.getLogin());
        account.setPassword(accountRegisterDTO.getPassword());
        account.setUserRole(Role.ROLE_USER);
        accountService.save(account);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @ApiOperation(value = "View info about account")
    @RequestMapping(method = RequestMethod.GET, path = "/info")
    public AccountViewDTO getUserInfo(Principal principal) {
        BankUserDetails details = (BankUserDetails)bankUserDetailsService.loadUserByUsername(principal.getName());
        Account account = details.getAccount();
        account.setLogin("");
        account.setPassword("");
        return convertToDto(account, AccountViewDTO.class);
    }
}
