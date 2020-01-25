package entity;

import javax.persistence.*;

/**
 *
 * @author Esben
 */
@Entity
@Table(name = "book")
public class Book {

    private int id;
    private String title;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return this.id;
    }
    
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    
    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
}
