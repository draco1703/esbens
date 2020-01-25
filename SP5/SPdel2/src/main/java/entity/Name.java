/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Esben
 */
@Entity
@Table(name = "names")
@NamedQueries({
    @NamedQuery(name = "Name.findAll", query = "SELECT n FROM Name n")
    , @NamedQuery(name = "Name.findById", query = "SELECT n FROM Name n WHERE n.id = :id")
    , @NamedQuery(name = "Name.findByFname", query = "SELECT n FROM Name n WHERE n.fname = :fname")
    , @NamedQuery(name = "Name.findByLname", query = "SELECT n FROM Name n WHERE n.lname = :lname")
    , @NamedQuery(name = "Name.findByGender", query = "SELECT n FROM Name n WHERE n.gender = :gender")})
public class Name implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 255)
    @Column(name = "fname")
    private String fname;
    @Size(max = 255)
    @Column(name = "lname")
    private String lname;
    @Size(max = 255)
    @Column(name = "gender")
    private String gender;

    public Name() {
    }

    public Name(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Name)) {
            return false;
        }
        Name other = (Name) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Name_1[ id=" + id + " ]";
    }

}
