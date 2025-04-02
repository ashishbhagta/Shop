package com.DCMetal.Shop.payload;

import com.DCMetal.Shop.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse
{
    private List<ProductDTO> products;

}
