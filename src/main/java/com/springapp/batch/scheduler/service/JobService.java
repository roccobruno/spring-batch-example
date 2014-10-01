package com.springapp.batch.scheduler.service;

import com.springapp.domain.ACCOUNT_FLAG_VALUES;
import com.springapp.domain.Account;
import com.springapp.domain.User;
import com.springapp.service.AccountCreationException;
import com.springapp.service.IAccountService;
import com.springapp.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 09:13
 * To change this template use File | Settings | File Templates.
 */
@Component
public class JobService implements IJobService {
    Logger log = LoggerFactory.getLogger(JobService.class);


    @Autowired
    private IAccountService accountService;

    @Autowired
    private IUserService userService;






    @Override
    public void process() {
        log.info("start processing...");
        // cerca tutti i record utenti senza account
        List<User> userToProcess = userService.findAllTheUsersWithNoAccount();
        log.info("found {} records to process",userToProcess.size());

                //per ogni record crea account e aggiorna record nel db
        for(User user:userToProcess) {

            log.debug("start processing user :{}",user);
            Account account = null;
            try {
                //crea account
                account = accountService.createAccount(user);
            } catch (AccountCreationException e) {
                //TODO createAccount exception handling
                e.printStackTrace();
            }
            log.debug("finished processing : ACCOUNT CREATED : {}",account);
            log.debug("updating user record..");
            //aggiorna record nel db
            user.setFlagRichiestaInoltrata(ACCOUNT_FLAG_VALUES.WITH_ACCOUNT.getId());
            userService.updateUser(user);
            log.debug("user record updated!");

        }
        log.info("process ended...");
    }
}
