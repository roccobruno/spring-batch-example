package com.springapp.domain.http.transferconfiguration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.springapp.domain.IToXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 02/10/2014
 * Time: 10:47
 * To change this template use File | Settings | File Templates.
 */
@JacksonXmlRootElement(localName = "transferConfigurations")
public class TransferConfigurations implements IToXML{
    static Logger log = LoggerFactory.getLogger(TransferConfigurations.class);

    public TransferConfigurations(){}
    private TransferConfiguration transferConfiguration = new TransferConfiguration();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransferConfigurations that = (TransferConfigurations) o;

        if (transferConfiguration != null ? !transferConfiguration.equals(that.transferConfiguration) : that.transferConfiguration != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return transferConfiguration != null ? transferConfiguration.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TransferConfigurations{" +
                "transferConfiguration=" + transferConfiguration +
                '}';
    }

    public TransferConfiguration getTransferConfiguration() {
        return transferConfiguration;
    }

    public void setTransferConfiguration(TransferConfiguration transferConfiguration) {
        this.transferConfiguration = transferConfiguration;
    }

    public static TransferConfigurations fromXML(String xml) {
        ObjectMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            TransferConfigurations value = xmlMapper.readValue(xml, TransferConfigurations.class);
            return value;
        } catch (IOException e) {
            log.error("Error in creating Account object from xmn :{}, error:{}", xml,e);
        }
        return null;
    }

    public String toXML() {
        ObjectMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            String xml = xmlMapper.writeValueAsString(this);
            return xml;
        } catch (JsonProcessingException e) {
            log.error("Error in creating xml from Account object:{}",this);
        }
        return "";
    }
}
