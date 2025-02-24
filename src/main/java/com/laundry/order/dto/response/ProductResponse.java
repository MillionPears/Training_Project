package com.laundry.order.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

  private UUID id;

  private String name;

  private String description;

  private BigDecimal price;

  private Integer stockQuantity;

  private String category;
  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;
}
