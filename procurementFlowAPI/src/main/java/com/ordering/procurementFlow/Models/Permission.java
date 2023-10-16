package com.ordering.procurementFlow.Models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    PROCUREMENT_OFFICER_READ("procurementOfficer:read"),
    PROCUREMENT_OFFICER_UPDATE("procurementOfficer:update"),
    PROCUREMENT_OFFICER_CREATE("procurementOfficer:create"),
    PROCUREMENT_OFFICER_DELETE("procurementOfficer:delete");


    @Getter
    private final String permission;

}
