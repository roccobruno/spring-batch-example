package com.springapp.service;

import com.springapp.config.ConfigTest;
import com.springapp.domain.ACCOUNT_FLAG_VALUES;
import com.springapp.domain.User;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 20:00
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(classes = {ConfigTest.class})

public class UserServiceImplTest {

    @Autowired
    IUserService userService;


    @Test
    public void testFindAllTheUsersWithNoAccount() throws Exception {
        List<User> users = userService.findAllTheUsersWithNoAccount();
        assertTrue(users.size() == 5);
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = userService.findById("brnrcc82m09g786i");
        user.setFlagRichiestaInoltrata(ACCOUNT_FLAG_VALUES.WITH_ACCOUNT.getId());
        userService.updateUser(user);

        user = userService.findById("brnrcc82m09g786i");
        assertEquals(ACCOUNT_FLAG_VALUES.WITH_ACCOUNT.getId(), user.getFlagRichiestaInoltrata());

    }

    @Test
    public void testFindById() throws Exception {
        User user = userService.findById("brngnn82m09g786i");

        assertEquals("giovanni",user.getNome());
        assertEquals("bruno",user.getCognome());
        assertEquals("giov@msn.com",user.getEmail());
        assertEquals("brngnn82m09g786i",user.getCf());
        assertEquals(ACCOUNT_FLAG_VALUES.WITHOUT_ACCOUNT.getId(),user.getFlagRichiestaInoltrata());

        assertNotNull(user.getDataInoltroRichiesta());
        assertNotNull(user.getDataRichiesta());
    }
}
