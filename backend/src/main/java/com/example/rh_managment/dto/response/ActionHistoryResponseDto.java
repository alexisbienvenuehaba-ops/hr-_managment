package com.example.rh_managment.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActionHistoryResponseDto {
    private Long idActionHistory;
    private Long idUser;
    private String usernameUser;
    private String actionHistory;
    private String entityNameHistory;
    private Long entityIdHistory;
    private String oldValueHistory;
    private String newValueHistory;
    private LocalDateTime actionDateHistory;
}