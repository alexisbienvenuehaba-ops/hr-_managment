package com.example.rh_managment.entity;

import com.example.rh_managment.enums.ServiceStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "service")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_service")
    private Long idService;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_department", nullable = false)
    private DepartmentEntity department;

    @Column(name = "name_service", nullable = false, unique = true)
    private String nameService;

    @Column(name = "description_service")
    private String descriptionService;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_service", nullable = false)
    private ServiceStatus statusService = ServiceStatus.ACTIVE;

    @CreationTimestamp
    @Column(name = "created_at_service", updatable = false)
    private LocalDateTime createdAtService;

    @UpdateTimestamp
    @Column(name = "updated_at_service")
    private LocalDateTime updatedAtService;
}