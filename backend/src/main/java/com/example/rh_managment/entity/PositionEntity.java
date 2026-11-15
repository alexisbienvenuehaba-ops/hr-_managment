package com.example.rh_managment.entity;

import com.example.rh_managment.enums.PositionStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import org.springframework.stereotype.Component;

@Data
@Entity
@Table(name = "poste")
public class PositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPosition;
    @Column(nullable = false,unique = true)
    private String namePosition;
    @Column(nullable = false)
    private String descriptionPosition;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PositionStatus Status=PositionStatus.ACTIVE;


}
