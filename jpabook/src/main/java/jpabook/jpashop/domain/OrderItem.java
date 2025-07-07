package jpabook.jpashop.domain;

import jakarta.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name="ORDER_ITEM_ID")
    private Long id;

    //@Column(name="ORDER_ID")
    //private Long orderId;

    @ManyToOne
    @JoinColumn(name="ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name="ITEM_ID")
    private Item item;

    //@Column(name="ITEM_ID")
    //private Long itemId;

    private int OrderPrice;
    private int count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getOrderPrice() {
        return OrderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        OrderPrice = orderPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
