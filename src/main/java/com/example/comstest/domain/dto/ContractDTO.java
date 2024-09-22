package com.example.comstest.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractDTO {
    private Long id;
    private String contract_number;
    private String contract_type;
    private Date start_date;
    private Date end_date;
    private String status;
    private UserDTO user;
}
