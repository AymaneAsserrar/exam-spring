package com.bikeshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String userId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cart_bikes", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "bike_id"))
    @Builder.Default
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Set<Bike> bikes = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cart_accessories", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "accessory_id"))
    @Builder.Default
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Set<Accessory> accessories = new HashSet<>();

    public void addBike(Bike bike) {
        this.bikes.add(bike);
        bike.getCarts().add(this);
    }

    public void removeBike(Bike bike) {
        this.bikes.remove(bike);
        bike.getCarts().remove(this);
    }

    public void addAccessory(Accessory accessory) {
        this.accessories.add(accessory);
        accessory.getCarts().add(this);
    }

    public void removeAccessory(Accessory accessory) {
        this.accessories.remove(accessory);
        accessory.getCarts().remove(this);
    }
}
