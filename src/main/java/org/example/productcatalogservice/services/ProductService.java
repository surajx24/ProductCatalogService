package org.example.productcatalogservice.services;


import org.example.productcatalogservice.dtos.FakeStoreProductDto;
import org.example.productcatalogservice.models.Category;
import org.example.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;


import java.util.Date;
import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;


    @Override
    public Product getProductDetailsById(Long id) {

        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class,id);

        if(fakeStoreProductDtoResponseEntity.getStatusCode().is2xxSuccessful() &&
            fakeStoreProductDtoResponseEntity.hasBody()){

            return  from(fakeStoreProductDtoResponseEntity.getBody());
        }

        return null;
    }


    private Product from(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.setName(fakeStoreProductDto.getTitle());
        product.setId(fakeStoreProductDto.getId());
        product.setPrice(fakeStoreProductDto.getPrice());

        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;


    }

    @Override
    public Product replaceProduct(Long id, Product inputProduct) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }
}
