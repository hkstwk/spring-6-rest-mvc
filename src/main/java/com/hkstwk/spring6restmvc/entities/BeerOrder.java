package com.hkstwk.spring6restmvc.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
public class BeerOrder {

    public BeerOrder(UUID id, Integer version, String customerRef, LocalDateTime createdDate, LocalDateTime lastModifiedDate, Customer customer, Set<BeerOrderLine> beerOrderLines) {
        this.id = id;
        this.version = version;
        this.customerRef = customerRef;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.setCustomer(customer);;
        this.beerOrderLines = beerOrderLines;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", nullable = false, updatable = false)
    private UUID id;

    @Version
    private Integer version;

    private String customerRef;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime lastModifiedDate;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "beerOrder")
    private Set<BeerOrderLine> beerOrderLines;

    public void setCustomer(Customer customer) {
        this.customer = customer;
        customer.getBeerOrders().add(this);

    }
}
