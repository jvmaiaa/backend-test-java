package com.desafio.backend.domain.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {

    GERENTE("gerente"),
    FUNCIONARIO("funcionario"),
    CLIENTE("cliente");

    private String role;

    RoleEnum(String role){
        this.role = role;
    }
}