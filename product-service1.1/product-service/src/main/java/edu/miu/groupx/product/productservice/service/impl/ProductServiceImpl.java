package edu.miu.groupx.product.productservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import edu.miu.groupx.product.productservice.models.*;
import edu.miu.groupx.product.productservice.models.dtos.ProductDTO;
import edu.miu.groupx.product.productservice.models.dtos.ProductsDTO;
import edu.miu.groupx.product.productservice.repository.CategoryRepository;
import edu.miu.groupx.product.productservice.service.ProductImagesService;
import edu.miu.groupx.product.productservice.utils.S3Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import edu.miu.groupx.product.productservice.repository.ProductRepository;
import edu.miu.groupx.product.productservice.service.ProductService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductImagesService productImagesService;


    @Override
    public ProductDTO getProductById(long id) {
        return this.convertToProductDTO(this.productRepository.findById(id).get());
    }

    @Override
    public ProductsDTO getAllProducts() {

        List<Product> productList = this.productRepository.findAll();
        ProductsDTO productsDTO = new ProductsDTO();
        List<ProductDTO> productDTOList = productList.stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
        productsDTO.setProducts(productDTOList);
        return productsDTO;
    }


    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product product = this.productRepository.getOne(productDTO.getId());
        System.out.println(product.getUserId());
        if (product.getUserId() == productDTO.getVendorId()) {

            product.setName(productDTO.getName());
            product.setCategory(this.categoryRepository.findByName(productDTO.getCategory()));
            product.setDescription(productDTO.getDescription());
            product.getProductWarehouse().setQuantity(productDTO.getQuantity());
            product.setPrice(productDTO.getPrice());
            return this.convertToProductDTO(this.productRepository.save(product));
        } else
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "ownership check failed"
            );
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO, long vendorId) {
        ProductWarehouse productWarehouse = ProductWarehouse.builder()
                .quantity(productDTO.getQuantity())
                .status(ProductStatus.NEW)
                .build();
        Product product = Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .category(this.categoryRepository.findByName(productDTO.getCategory()))
                .productWarehouse(productWarehouse)
                .price(productDTO.getPrice())
                .pictures(new ArrayList<>())
                .userId(vendorId).build();
        Product product1 = this.productRepository.save(product);
        return this.convertToProductDTO(product1);
    }

    @Override
    public void deleteProduct(long productId, long vendorId) {
        Product product = this.productRepository.getOne(productId);
        if (product.getUserId() == vendorId) {
            product
                    .getPictures()
                    .stream()
                    .map(ProductImages::getImagePth)
                    .forEach(s -> S3Utils.deleteFileFromS3Bucket(s));
            this.productRepository.deleteById(productId);

        } else {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "ownership check failed"
            );
        }
    }

    @Override
    public ProductsDTO searchProducts(String keyword, String category) {
        ProductsDTO productsDTO = new ProductsDTO();
        List<ProductDTO> productDTOList;

        if (category.trim().length() == 0)
            productDTOList = this.productRepository.searchProductsByKeyword(keyword)
                    .stream()
                    .map(this::convertToProductDTO).collect(Collectors.toList());
        else {
            Category categoryObject = this.categoryRepository.findByName(category);
            productDTOList = this.productRepository.searchProductsByKeywordAndCategory(keyword, categoryObject.getId())
                    .stream()
                    .map(this::convertToProductDTO).collect(Collectors.toList());

        }
        productsDTO.setProducts(productDTOList);
        return productsDTO;
    }

    @Override
    public ProductsDTO getPendingProducts(long vendorId) {
        ProductsDTO productsDTO = new ProductsDTO();
        List<ProductDTO> productDTOList;
        productDTOList = this.productRepository.getProductsByProductWarehouseStatusAndUserId(ProductStatus.NEW, vendorId)
                .stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
        productsDTO.setProducts(productDTOList);
        return productsDTO;
    }

    @Override
    public ProductsDTO getApprovedProducts(long vendorId) {
        ProductsDTO productsDTO = new ProductsDTO();
        List<ProductDTO> productDTOList;
        productDTOList = this.productRepository.getProductsByProductWarehouseStatusAndUserId(ProductStatus.APPROVED, vendorId)
                .stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
        productsDTO.setProducts(productDTOList);
        return productsDTO;
    }

    @Override
    public ProductsDTO getRejectedProducts(long vendorId) {
        ProductsDTO productsDTO = new ProductsDTO();
        List<ProductDTO> productDTOList;
        productDTOList = this.productRepository.getProductsByProductWarehouseStatusAndUserId(ProductStatus.REJECTED, vendorId)
                .stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
        productsDTO.setProducts(productDTOList);
        return productsDTO;
    }

    @Override
    public void addProductImage(long productId, String url) {
        Product product = this.productRepository.getOne(productId);
        ProductImages productImages = new ProductImages();
        productImages.setImagePth(url);
        product.addImage(productImages);
        this.productRepository.save(product);
    }

    private ProductDTO convertToProductDTO(Product product) {
        ProductDTO productDTO = ProductDTO.builder().id(product.getId())
                .name(product.getName())
                .category(product.getCategory().getName())
                .description(product.getDescription())
                .quantity(product.getProductWarehouse().getQuantity())
                .price(product.getPrice())
                .vendorId(product.getUserId())
                .imageUrl(product.getPictures().stream().map(ProductImages::getImagePth).collect(Collectors.toList()))
                .build();
        return productDTO;
    }

    @Override
    public boolean checkProductOwnership(long productId, long vendorId) {
        Optional<Product> product = this.productRepository.findById(productId);
        if (product.isPresent() && product.get().getUserId() == vendorId)
            return true;
        else
            return false;
    }
    /*
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductCatagoryRepository productCatagoryRepository;
    @Autowired
    private SequenceNumberService sequenceNumberService;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductWarehouseRepository ProductWarehouseRepo;

    @Override
    public Product save(Product product) {
        // List<ProductImages> productImages=product.getPictures().g

        Product actualProduct = null;
        Long productCategoryId = product.getProductCatagoryId();
        if (productCategoryId != null) {
            ProductCatagory productCatagory = productCatagoryRepository.findById(productCategoryId).get();
            //how to set the quantity
            String productSequence = sequenceNumberService.getNextProductNumber();
            product.setProductNumber(productSequence);
            product.setProductCatagory(productCatagory);
            System.out.println(product.getProductCatagory().getId());
            actualProduct = productRepository.save(product);
            productCatagory.setQuantity(productCatagory.getQuantity() + 1);
            Long productCategoryId = product.getProductCatagoryId();
            Long productWarehouseId = product.getProductWarehouseId();
            ProductWarehouse productWarehouse = null;
            if (productCategoryId != null) {
                Category category = categoryRepository.findById(productCategoryId).get();
                //System.out.println(product.getName());
                //Set<Category> categoryList= new HashSet();
                //categoryList.add(category);
                product.addCategory(category);

                //product.setCategory(categoryList);

            }
            if (productWarehouseId != null) {
                productWarehouse = ProductWarehouseRepo.findById(productWarehouseId).get();

            }
            actualProduct = productRepository.save(product);
            productWarehouse.addProduct(actualProduct);
            ProductWarehouseRepo.save(productWarehouse);

            return actualProduct;

        }

        @Override
        public List<Product> getAllProducts () {

            return productRepository.findAll();
        }

        @Override
        public Product getProductById ( long id){

            return productRepository.findById(id).get();

        }

        @Override
        public Product getProductByName (String name){

            return productRepository.findByName(name);
        }

        @Override
        public List<Product> getProductByCategory (Category category){

            return productRepository.getByCategory(category);
        }

		@Override
		public Product updateProduct ( long id, Product newProduct){

			Product ActualProduct = getProductById(id);
			ActualProduct.setName(newProduct.getName());
			System.out.println("Before change: " + ActualProduct.getPrice());
			ActualProduct.setPrice(newProduct.getPrice());
			System.out.println("after change: " + ActualProduct.getPrice());
			ActualProduct.setCategory(newProduct.getCategory());
			ActualProduct.setAddedOn(newProduct.getAddedOn());
			// ActualProduct.setStatus(newProduct.getStatus());
			ActualProduct.setDescription(newProduct.getDescription());
			ActualProduct.setImageUrl(newProduct.getImageUrl());

			return productRepository.save(ActualProduct);

		}

		@Override
		public void deleteProduct (Long id){
			productRepository.deleteById(id);

		}

		@Override
		public void delete (Product product){
			productRepository.delete(product);

		}

		@Override
		public List<Product> search (String keyword){

			return productRepository.searchProducts(keyword);
		}

		@Override
		public List<Product> getPendingProducts () {
			return productRepository.getPendingProducts();
		}

		@Override
		public List<Product> getApprovedProducts () {
			return productRepository.getApprovedProducts();
		}

		@Override
		public List<Product> getRejectedProducts () {
			return productRepository.getRejectedProducts();
		}
        */

    /*
     * @Override public List<Product> getNewProducts(ProductStatus productStatus) {
     *
     * return productRepository.getNew(productStatus); }
     *
     * @Override public List<Product> getApprovedProducts(ProductStatus
     * productStatus) {
     *
     * return productRepository.getApproved(productStatus); }
     *
     * @Override public List<Product> getRejectedProducts(ProductStatus
     * productStatus) {
     *
     * return productRepository.getRejected(productStatus); }
     */


}
