package tech.hackathon.shopping.domain;

import lombok.Data;
import tech.hackathon.shopping.domain.enumerations.PeriodType;
import tech.hackathon.shopping.domain.enumerations.UnitType;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name="tbl_items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double price;

    private Double unit;

    @Enumerated(EnumType.STRING)
    private UnitType unitType;

    private LocalDate lastBought;

    private Integer lastingPeriodUnit;

    @Enumerated(EnumType.STRING)
    private PeriodType periodType;
}
