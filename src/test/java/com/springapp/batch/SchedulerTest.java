package com.springapp.batch;

import com.springapp.config.ConfigTest;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 09:16
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfigTest.class})
@ImportResource("classpath:spring-job.xml")
public class SchedulerTest {

    @Autowired
    TaskScheduler scheduler;

    @Ignore
    @Test
    public void testJob() throws Exception {

        Thread.sleep(10000);



    }

}
