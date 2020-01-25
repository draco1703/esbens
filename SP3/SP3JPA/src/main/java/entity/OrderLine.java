package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Esben
 */
@Entity
public class OrderLine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;

    @ManyToOne
    private ItemType item;


    public OrderLine() {
    }

    public OrderLine(int quantity, ItemType item) {
        this.quantity = quantity;
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public ItemType getItem() {
        return item;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setItem(ItemType item) {
        this.item = item;
    }
}
