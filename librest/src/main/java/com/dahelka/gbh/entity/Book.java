package com.dahelka.gbh.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "book")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
    @NamedQuery(name = "Book.findByIdBook", query = "SELECT b FROM Book b WHERE b.idBook = :idBook"),
    @NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b WHERE b.title = :title"),
    @NamedQuery(name = "Book.findByIsbn", query = "SELECT b FROM Book b WHERE b.isbn = :isbn"),
    @NamedQuery(name = "Book.findByLangage", query = "SELECT b FROM Book b WHERE b.langage = :langage")})
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_book")
    private Integer idBook;
    @Size(max = 300)
    @Column(name = "title")
    private String title;
    @Size(max = 45)
    @Column(name = "isbn")
    private String isbn;
    @Size(max = 45)
    @Column(name = "langage")
    private String langage;
    @OneToMany(mappedBy = "idBook")
    private Collection<Page> pageCollection;

    public Book() {
    }

    public Book(Integer idBook) {
        this.idBook = idBook;
    }

    public Integer getIdBook() {
        return idBook;
    }

    public void setIdBook(Integer idBook) {
        this.idBook = idBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getLangage() {
        return langage;
    }

    public void setLangage(String langage) {
        this.langage = langage;
    }

    @XmlTransient
    public Collection<Page> getPageCollection() {
        return pageCollection;
    }

    public void setPageCollection(Collection<Page> pageCollection) {
        this.pageCollection = pageCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBook != null ? idBook.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.idBook == null && other.idBook != null) || (this.idBook != null && !this.idBook.equals(other.idBook))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Book{" + "idBook=" + idBook + ", title=" + title + ", isbn=" + isbn + ", langage=" + langage + ", pageCollection=" + pageCollection + '}';
    }

  
    
}
