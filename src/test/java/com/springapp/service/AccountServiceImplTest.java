package com.springapp.service;

import com.springapp.domain.ACCOUNT_FLAG_VALUES;
import com.springapp.domain.Account;
import com.springapp.domain.User;
import com.springapp.domain.UserAccount;
import com.springapp.service.http.MopHttpClientImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 23:13
 * To change this template use File | Settings | File Templates.
 */
public class AccountServiceImplTest {

    private AccountServiceImpl accountService;
    private MopHttpClientImpl mopHttpClient;


    @Before
    public void setUp() {
         accountService = new AccountServiceImpl();
         mopHttpClient = Mockito.mock(MopHttpClientImpl.class);
         accountService.setMopHttpClient(mopHttpClient);
    }

    @Test
    public void testCreateAccount() throws Exception {
       User user  = new User();
        user.setCf("brnrcc80l13g786I");
        user.setCognome("bruno");
        user.setNome("rocco");
        user.setEmail("rocco@msn.com");
        user.setDataInoltroRichiesta(new Date());
        user.setDataRichiesta(new Date());
        user.setFlagRichiestaInoltrata(ACCOUNT_FLAG_VALUES.WITHOUT_ACCOUNT.getId());

        when(mopHttpClient.createMopAccount(Mockito.any(Account.class))).thenReturn(true);
        when(mopHttpClient.addUserToMopAccount(Mockito.any(UserAccount.class))).thenReturn(true);

       Account account = accountService.createAccount(user);
           verify(mopHttpClient,times(1)).createMopAccount(any(Account.class));
        verify(mopHttpClient,times(1)).addUserToMopAccount(any(UserAccount.class));
        assertNotNull(account);
        assertEquals("brnrcc80l13g786I",account.getName());
        assertEquals("rocco@msn.com",account.getContact().getEmail());
    }
}
