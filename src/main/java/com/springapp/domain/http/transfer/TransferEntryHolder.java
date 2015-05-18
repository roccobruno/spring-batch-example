package com.springapp.domain.http.transfer;

/**
 * Created by roccobruno on 18/05/2015.
 */
public class TransferEntryHolder {

    TransferEntry transferEntry;

public TransferEntryHolder(){}

    public TransferEntry getTransferEntry() {
        return transferEntry;
    }

    public void setTransferEntry(TransferEntry transferEntry) {
        this.transferEntry = transferEntry;
    }

    @Override
    public String toString() {
        return "TransferEntryHolder{" +
                "transferEntry=" + transferEntry +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransferEntryHolder that = (TransferEntryHolder) o;

        return !(transferEntry != null ? !transferEntry.equals(that.transferEntry) : that.transferEntry != null);

    }

    @Override
    public int hashCode() {
        return transferEntry != null ? transferEntry.hashCode() : 0;
    }
}
