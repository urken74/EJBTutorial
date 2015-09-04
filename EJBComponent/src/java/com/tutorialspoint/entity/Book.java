/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorialspoint.entity;

import com.tutorialspoint.callback.BookCallbackListener;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author gorerk
 */
@Entity
@EntityListeners({ BookCallbackListener.class })
@Table(name="books")
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    
    String name;

   /* private byte[] image;   
    
    private String xml;
   */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
/*
   @Lob @Basic(fetch= FetchType.EAGER)
   public byte[] getImage() {
      return image;
   }

   public void setImage(byte[] image) {
      this.image = image;
   }


   @Lob @Basic(fetch= FetchType.EAGER)
   public String getXml() {
      return xml;
   }

   public void setXml(String xml) {
      this.xml = xml;
   }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tutorialspoint.entity.Book[ id=" + id + " ]";
    }
    
}
