package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Esben
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "ItemType.findAll", query = "SELECT i FROM ItemType i")
    , @NamedQuery(name = "ItemType.findById", query = "SELECT i FROM ItemType i WHERE i.id = :id")
    , @NamedQuery(name = "ItemType.findByItemName", query = "SELECT i FROM ItemType i WHERE i.itemName = :item_name")
    , @NamedQuery(name = "ItemType.findByDescription", query = "SELECT i FROM ItemType i WHERE i.description = :item_description")
    , @NamedQuery(name = "ItemType.findByPrice", query = "SELECT i FROM ItemType i WHERE i.price = :price")})
public class ItemType implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String itemName;
    private String description;
    private int price;

    public ItemType() {

    }

    public ItemType(String name, String description, int price) {
        this.itemName = name;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    
}
