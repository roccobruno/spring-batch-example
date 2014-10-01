package com.springapp.domain;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 18:32
 * To change this template use File | Settings | File Templates.
 */
public class User {

    private String nome;
    private String cognome;
    private String cf;
    private String email;
    private Date dataRichiesta;
    private Date dataInoltroRichiesta;
    private String flagRichiestaInoltrata;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (cf != null ? !cf.equals(user.cf) : user.cf != null) return false;
        if (cognome != null ? !cognome.equals(user.cognome) : user.cognome != null) return false;
        if (dataInoltroRichiesta != null ? !dataInoltroRichiesta.equals(user.dataInoltroRichiesta) : user.dataInoltroRichiesta != null)
            return false;
        if (dataRichiesta != null ? !dataRichiesta.equals(user.dataRichiesta) : user.dataRichiesta != null)
            return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (flagRichiestaInoltrata != null ? !flagRichiestaInoltrata.equals(user.flagRichiestaInoltrata) : user.flagRichiestaInoltrata != null)
            return false;
        if (nome != null ? !nome.equals(user.nome) : user.nome != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nome != null ? nome.hashCode() : 0;
        result = 31 * result + (cognome != null ? cognome.hashCode() : 0);
        result = 31 * result + (cf != null ? cf.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (dataRichiesta != null ? dataRichiesta.hashCode() : 0);
        result = 31 * result + (dataInoltroRichiesta != null ? dataInoltroRichiesta.hashCode() : 0);
        result = 31 * result + (flagRichiestaInoltrata != null ? flagRichiestaInoltrata.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", cf='" + cf + '\'' +
                ", email='" + email + '\'' +
                ", dataRichiesta=" + dataRichiesta +
                ", dataInoltroRichiesta=" + dataInoltroRichiesta +
                ", flagRichiestaInoltrata='" + flagRichiestaInoltrata + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataRichiesta() {
        return dataRichiesta;
    }

    public void setDataRichiesta(Date dataRichiesta) {
        this.dataRichiesta = dataRichiesta;
    }

    public Date getDataInoltroRichiesta() {
        return dataInoltroRichiesta;
    }

    public void setDataInoltroRichiesta(Date dataInoltroRichiesta) {
        this.dataInoltroRichiesta = dataInoltroRichiesta;
    }

    public String getFlagRichiestaInoltrata() {
        return flagRichiestaInoltrata;
    }

    public void setFlagRichiestaInoltrata(String flagRichiestaInoltrata) {
        this.flagRichiestaInoltrata = flagRichiestaInoltrata;
    }
}
