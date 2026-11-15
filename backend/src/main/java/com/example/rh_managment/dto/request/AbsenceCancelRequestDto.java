package com.example.rh_managment.dto.request;

import lombok.Data;

@Data
public class AbsenceCancelRequestDto {

    // Renseigné seulement quand ce sont les RH qui annulent
    private Long idUser;
}