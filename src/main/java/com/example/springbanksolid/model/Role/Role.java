package com.example.springbanksolid.model.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;



public enum Role {

    USER,
    ADMIN
}
