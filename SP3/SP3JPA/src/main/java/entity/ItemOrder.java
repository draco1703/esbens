/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Esben
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "ItemOrder.findAll", query = "SELECT o FROM ItemOrder o")
    , @NamedQuery(name = "ItemOrder.findById", query = "SELECT o FROM ItemOrder o WHERE o.id = :order_id")
})
public class ItemOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int status;

    @OneToMany
    private List<OrderLine> orderlines = new ArrayList<>();

    public ItemOrder() {
        this(new ArrayList<>());
        status = 1;
    }

    public ItemOrder(List<OrderLine> orderlines) {

        this.orderlines = orderlines;
        status = 1;

    }

    public int getId() {
        return id;
    }

    public List<OrderLine> getOrderlines() {
        return orderlines;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderlines(List<OrderLine> orderlines) {
        this.orderlines = orderlines;
    }

    public void addOrderLine(OrderLine line) {
        this.orderlines.add(line);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
