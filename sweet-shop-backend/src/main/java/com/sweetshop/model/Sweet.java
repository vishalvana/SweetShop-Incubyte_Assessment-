package com.sweetshop.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "sweets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sweet {

    @Id
    private String id;

    private String name;

    private String description;   

    private BigDecimal price;

    private String imageUrl;       
    private String tag;           

    private int quantity;
}
