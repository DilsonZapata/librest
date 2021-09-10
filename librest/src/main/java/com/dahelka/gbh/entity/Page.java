
package com.dahelka.gbh.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "page")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Page.findAll", query = "SELECT p FROM Page p"),
    @NamedQuery(name = "Page.findByIdBook", query = "SELECT p FROM Page p WHERE p.idBook = :idBook"),
    @NamedQuery(name = "Page.findByIdPage", query = "SELECT p FROM Page p WHERE p.idPage = :idPage"),
    @NamedQuery(name = "Page.findByBodyContent", query = "SELECT p FROM Page p WHERE p.bodyContent = :bodyContent")})
public class Page implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_page")
    private Integer idPage;
    @Size(max = 5000)
    @Column(name = "body_content")
    private String bodyContent;
    @JoinColumn(name = "id_book", referencedColumnName = "id_book")
    @ManyToOne
    private Book idBook;

    public Page() {
    }

    public Page(Integer idPage) {
        this.idPage = idPage;
    }

    public Integer getIdPage() {
        return idPage;
    }
/*
    public void setIdPage(Integer idPage) {
        this.idPage = idPage;
    }
*/
    public String getBodyContent() {
        return bodyContent;
    }
/*
    public void setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent;
    }

    public Book getIdBook() {
        return idBook;
    }

    public void setIdBook(Book idBook) {
        this.idBook = idBook;
    }
    */

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPage != null ? idPage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Page)) {
            return false;
        }
        Page other = (Page) object;
        return !((this.idPage == null && other.idPage != null) || (this.idPage != null && !this.idPage.equals(other.idPage)));
    }

    @Override
    public String toString() {
        return "Page{" + "idPage=" + idPage + ", bodyContent=" + bodyContent + ", idBook=" + idBook + '}';
    }
}
