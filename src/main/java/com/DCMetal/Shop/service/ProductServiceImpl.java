package com.DCMetal.Shop.service;

import com.DCMetal.Shop.exceptions.ResourceNotFoundException;
import com.DCMetal.Shop.model.Category;
import com.DCMetal.Shop.model.Product;
import com.DCMetal.Shop.payload.ProductDTO;
import com.DCMetal.Shop.payload.ProductResponse;
import com.DCMetal.Shop.repositories.CategoryRepository;
import com.DCMetal.Shop.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO addProduct(Long categoryId, ProductDTO productDTO)
    {
        Category category= categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
        Product product=modelMapper.map(productDTO, Product.class);
        product.setImage("default.png");
        product.setCategory(category);
        double specialPrice = product.getPrice()-((product.getDiscount() * 0.01) * product.getPrice());
        product.setSpecialPrice(specialPrice);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductResponse getAllProducts()
    {
        List<Product> products=productRepository.findAll();
        List<ProductDTO> productDTOs=products.stream().map(product->modelMapper.map(product, ProductDTO.class)).toList();
        ProductResponse productResponse=new ProductResponse();
        productResponse.setProducts(productDTOs);
        return productResponse;
    }

    @Override
    public ProductResponse searchByCateory(Long categoryId)
    {
        Category category= categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
        List<Product> products=productRepository.findByCategoryOrderByPriceAsc(category);
        List<ProductDTO> productDTOs=products.stream().map(product->modelMapper.map(product, ProductDTO.class)).toList();
        ProductResponse productResponse=new ProductResponse();
        productResponse.setProducts(productDTOs);
        return productResponse;
    }

    @Override
    public ProductResponse searchProductByKeyword(String keyword)
    {
        List<Product> products=productRepository.findByProductNameLikeIgnoreCase('%'+keyword+'%');
        List<ProductDTO> productDTOs=products.stream().map(product->modelMapper.map(product, ProductDTO.class)).toList();
        ProductResponse productResponse=new ProductResponse();
        productResponse.setProducts(productDTOs);
        return productResponse;
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO)
    {
        Product productFromDb =productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product","productId",productId));

        Product product=modelMapper.map(productDTO, Product.class);
        productFromDb.setProductName(product.getProductName());
        productFromDb.setDescription(product.getDescription());
        productFromDb.setQuantity(product.getQuantity());
        productFromDb.setDiscount(product.getDiscount());
        productFromDb.setPrice(product.getPrice());
        productFromDb.setSpecialPrice(product.getSpecialPrice());

        Product savedProduct = productRepository.save(productFromDb);

        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO deleteProduct(Long productId)
    {
        Product productFromDb =productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product","productId",productId));
        productRepository.delete(productFromDb);
        return modelMapper.map(productFromDb, ProductDTO.class);
    }

    @Override
    public ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException
    {
        Product productFromDb =productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product","productId",productId));

        String path="images/";
        String fileName=uploadImage(path,image);

        productFromDb.setImage(fileName);

        Product updatedProduct=productRepository.save(productFromDb);
        return modelMapper.map(updatedProduct, ProductDTO.class);

    }

    private String uploadImage(String path, MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();

        String randomId= UUID.randomUUID().toString();
        String fileName=randomId.concat(originalFilename.substring(originalFilename.lastIndexOf('.')));
        String filePath=path+ File.separator+fileName;

        File folder=new File(path);
        if (!folder.exists())
            folder.mkdir();

        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName;

    }

}
