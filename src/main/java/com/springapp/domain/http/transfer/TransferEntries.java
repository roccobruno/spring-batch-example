package com.springapp.domain.http.transfer;

        import java.io.IOException;
        import java.util.List;

        import com.fasterxml.jackson.databind.DeserializationFeature;
        import com.fasterxml.jackson.databind.JsonNode;
        import com.springapp.domain.IToXML;
        import org.codehaus.jackson.map.DeserializationConfig;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import com.fasterxml.jackson.annotation.JsonInclude;
        import com.fasterxml.jackson.core.JsonProcessingException;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import com.fasterxml.jackson.dataformat.xml.XmlMapper;
        import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "transferEntries")
public class TransferEntries implements IToXML {
    static Logger log = LoggerFactory.getLogger(TransferEntries.class);

    private List<TransferEntry> transferEntry;

    public TransferEntries(){}


    public List<TransferEntry> getTransferEntry() {
        return transferEntry;
    }


    public void setTransferEntry(List<TransferEntry> transferEntry) {
        this.transferEntry = transferEntry;
    }


    public static TransferEntries fromXML(String xml) {
        ObjectMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            List<TransferEntry> nodes =  xmlMapper.readValue(xml, List.class);
            TransferEntries value =  new TransferEntries();
            value.setTransferEntry(nodes);
            return value;
        } catch (IOException e) {
            log.error("Error in creating TransferEntries object from xml :{}, error:{}", xml,e);
        }
        return null;
    }

    public static TransferEntries fromJson(String json) {
        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            jsonMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            TransferEntries value = jsonMapper.readValue(json, TransferEntries.class);
            return value;
        } catch (IOException e) {
            log.error("Error in creating TransferEntries object from xmn :{}, error:{}", jsonMapper,e);
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
            log.error("Error in creating xml from TransferEntries object:{}",this);
        }
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransferEntries that = (TransferEntries) o;

        return !(transferEntry != null ? !transferEntry.equals(that.transferEntry) : that.transferEntry != null);

    }

    @Override
    public int hashCode() {
        return transferEntry != null ? transferEntry.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TransferEntries{" +
                "transferEntry=" + transferEntry +
                '}';
    }
}

