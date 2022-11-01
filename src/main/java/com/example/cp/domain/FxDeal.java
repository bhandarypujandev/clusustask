package com.example.cp.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class FxDeal {
    @Id
    private Long id;
    private String fromCurrencyIso;
    private String toCurrencyIso;
    private Date dealTime;
    private BigDecimal amount;

}
