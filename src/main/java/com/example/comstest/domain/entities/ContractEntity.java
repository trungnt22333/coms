package com.example.comstest.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "contracts")
public class ContractEntity {
    @Id
    @GeneratedValue
    private Long id;
//    @Column(unique = true, nullable = false)

    private String contract_number;
    private String contract_type;
    private Date start_date;
    private Date end_date;
    private String status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
