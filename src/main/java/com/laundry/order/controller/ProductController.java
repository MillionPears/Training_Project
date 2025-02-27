package com.laundry.order.controller;

import com.laundry.order.dto.ApiResponse;
import com.laundry.order.dto.request.ProductCreateRequest;
import com.laundry.order.dto.request.UserCreateRequest;
import com.laundry.order.dto.response.ProductResponse;
import com.laundry.order.dto.response.UserResponse;
import com.laundry.order.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<ProductResponse>> create(
    @Valid @RequestBody ProductCreateRequest productCreateRequest) {
    ProductResponse productResponse = productService.create(productCreateRequest);
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(new ApiResponse<>(productResponse));
  }

  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<ProductResponse>> getById(@PathVariable UUID id){
    ProductResponse productResponse = productService.getById(id);
    return ResponseEntity.ok(new ApiResponse<>(productResponse));
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<List<ProductResponse>>> getAll(){
    List<ProductResponse> list = productService.getAll();
    return ResponseEntity.ok(new ApiResponse<>(list));
  }

  @GetMapping(path = "/filter",produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<ProductResponse>> filterByNameAndPrice(@RequestParam(required = false) String name,
                                                                           @RequestParam(required = false,defaultValue = "0") BigDecimal minPrice,
                                                                           @RequestParam(required = false, defaultValue = "0") BigDecimal maxPrice,
                                                                           @RequestParam(defaultValue = "name") String sortBy,
                                                                           @RequestParam(defaultValue = "ASC") String sortDirection,
                                                                           Pageable pageable){
    Page<ProductResponse> productResponses = productService.filterProductWithNameAndPrice(name, minPrice, maxPrice, sortBy, sortDirection, pageable);
    return ResponseEntity.status(HttpStatus.OK).body(productResponses);

  }
}
