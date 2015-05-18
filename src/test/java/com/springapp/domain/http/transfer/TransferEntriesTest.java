package com.springapp.domain.http.transfer;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roccobruno on 18/05/2015.
 */
public class TransferEntriesTest {

    @Test
    public void testFromXML() throws Exception {


        String xml = "" +
                "<transferEntries>" +
                "    <transferEntry>" +
                "        <status>Processed</status>" +
                "        <secure>false</secure>" +
                "        <account>80062590379</account>" +
                "        <direction>Outgoing</direction>" +
                "        <login>80062590379</login></transferEntry>" +
                "    <transferEntry>" +
                "        <status>Processed2</status>" +
                "        <secure>false</secure>" +
                "        <account>333333333</account>" +
                "        <direction>Outgoing</direction>" +
                "        <login>3333333333</login></transferEntry>" +
                "</transferEntries>";

        TransferEntry transferEntry = new TransferEntry();
        transferEntry.setAccount("80062590379");
        transferEntry.setSecure(false);
        transferEntry.setStatus("Processed");
        transferEntry.setLogin("80062590379");
        transferEntry.setDirection("Outgoing");



        TransferEntries transferEntries = new TransferEntries();
        List<TransferEntry> list= new ArrayList<TransferEntry>();
        list.add(transferEntry);

        transferEntry = new TransferEntry();
        transferEntry.setAccount("333333333");
        transferEntry.setSecure(false);
        transferEntry.setStatus("Processed2");
        transferEntry.setLogin("3333333333");
        transferEntry.setDirection("Outgoing");
        list.add(transferEntry);

        transferEntries.setTransferEntry(list);


        Assert.assertEquals(TransferEntries.fromXML(xml).toString(),transferEntries.toString());
    }
}