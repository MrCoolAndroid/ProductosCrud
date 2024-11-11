package com.productos.ProductosCrud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse {
    private int status;
    private Date timestamp;
    private String message;
    private String details;
}
