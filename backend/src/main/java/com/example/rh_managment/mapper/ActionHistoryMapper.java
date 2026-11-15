package com.example.rh_managment.mapper;

import com.example.rh_managment.dto.request.ActionHistoryRequestDto;
import com.example.rh_managment.dto.response.ActionHistoryResponseDto;
import com.example.rh_managment.entity.ActionHistoryEntity;
import com.example.rh_managment.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ActionHistoryMapper {

    public ActionHistoryEntity toEntity(ActionHistoryRequestDto dto, UserEntity user) {
        ActionHistoryEntity e = new ActionHistoryEntity();
        e.setUser(user);
        e.setActionHistory(dto.getActionHistory().trim());
        e.setEntityNameHistory(clean(dto.getEntityNameHistory()));
        e.setEntityIdHistory(dto.getEntityIdHistory());
        e.setOldValueHistory(clean(dto.getOldValueHistory()));
        e.setNewValueHistory(clean(dto.getNewValueHistory()));
        e.setActionDateHistory(LocalDateTime.now());
        return e;
    }

    public ActionHistoryResponseDto toResponse(ActionHistoryEntity e) {
        ActionHistoryResponseDto r = new ActionHistoryResponseDto();
        r.setIdActionHistory(e.getIdActionHistory());
        r.setIdUser(e.getUser().getIdUser());
        r.setUsernameUser(e.getUser().getUsernameUser());
        r.setActionHistory(e.getActionHistory());
        r.setEntityNameHistory(e.getEntityNameHistory());
        r.setEntityIdHistory(e.getEntityIdHistory());
        r.setOldValueHistory(e.getOldValueHistory());
        r.setNewValueHistory(e.getNewValueHistory());
        r.setActionDateHistory(e.getActionDateHistory());
        return r;
    }

    private String clean(String value) {
        if (value == null) return null;
        String v = value.trim();
        return v.isEmpty() ? null : v;
    }
}