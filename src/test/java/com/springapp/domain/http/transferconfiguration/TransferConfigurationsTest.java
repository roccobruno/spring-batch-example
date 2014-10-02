package com.springapp.domain.http.transferconfiguration;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 02/10/2014
 * Time: 10:51
 * To change this template use File | Settings | File Templates.
 */
public class TransferConfigurationsTest {
    @Test
    public void testFromXML() throws Exception {
        TransferConfigurations transferConfigurations = new TransferConfigurations();

        String xmlToMatch = "<transferConfigurations><transferConfiguration>" +
                "<tag>PARTNER-IN</tag>" +
                "<direction>0</direction>" +
                "<site>TS_FTP_RoutingMOP</site>" +
                "</transferConfiguration>" +
                "</transferConfigurations>";


        Assert.assertEquals(TransferConfigurations.fromXML(xmlToMatch),transferConfigurations);
    }

    @Test
    public void testToXML() throws Exception {
       TransferConfigurations transferConfigurations = new TransferConfigurations();
       transferConfigurations.setTransferConfiguration(new TransferConfiguration());
        
        String xmlToMatch = "<transferConfigurations><transferConfiguration>" +
                "<tag>PARTNER-IN</tag>" +
                "<direction>0</direction>" +
                "<site>TS_FTP_RoutingMOP</site>" +
                "</transferConfiguration>" +
                "</transferConfigurations>";


        Assert.assertEquals(xmlToMatch,transferConfigurations.toXML());
    }
}
