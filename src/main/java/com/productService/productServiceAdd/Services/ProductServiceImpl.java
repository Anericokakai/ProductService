package com.productService.productServiceAdd.Services;
import com.productService.productServiceAdd.Ecxeption.NotFoundException;
import com.productService.productServiceAdd.OpenFeignClient.ShopClient;
import com.productService.productServiceAdd.Repository.ProductRepository;
import com.productService.productServiceAdd.models.Products;
import com.productService.productServiceAdd.tdo.ProductRequest;
import com.productService.productServiceAdd.tdo.ProductResponse;
import com.productService.productServiceAdd.tdo.ShopResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Service

public class ProductServiceImpl  implements ProductService{
private final ProductRepository productRepository;
private final ModelMapper modelMapper;

private  final ShopClient shopClient;


    @Override
    public String createNewProduct(ProductRequest productRequest) {
        Products newProduct= modelMapper.map(productRequest,Products.class);
shopClient.FindShopByStoreNumber(productRequest.getStoreNumber());
        productRepository.save(newProduct);
        return "product was added Successfully";
    }


//    ! FIND PRODUCT BY ID AND ITS SHOP DATA

    @Override
    public List<Products> findAllProducts() throws NotFoundException {
        List<Products> productList =productRepository.findAll();

        if(productList.isEmpty()){
            throw new NotFoundException("no products are available !");
        }
        return productRepository.findAll();
    }

    @Override
    public ProductResponse findProductById(int id) {

if(productRepository.findById(id).isEmpty()){
    throw new EntityNotFoundException("product with the given id:"+id+" cannot  be found!");
}
        Products products= productRepository.findById(id).get();
        ProductResponse productResponse=modelMapper.map(products,ProductResponse.class);

//        ! USING OPEN FEIGN


        ShopResponse shopResponse=shopClient.FindShopByStoreNumber(products.getStoreNumber());
        productResponse.setShopResponse(shopResponse);


        return  productResponse;
    }


//    ! DELETE PRODUCTS
    @Override
    public Map<String, String> deleteAllProducts(String storeNumber) {

        List<Products> productList=productRepository.findByStoreNumber(storeNumber);
if(productList.isEmpty()){
    throw  new EntityNotFoundException("product with the given id: "+storeNumber+" cannot be found ");
}


   productRepository.deleteAllByStoreNumber(storeNumber);

    Map<String,String> message=new HashMap<>();

  message.put("productMessage","products deleted succesfully");
        return message;
    }

}
