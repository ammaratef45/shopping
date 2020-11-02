package edu.miu.groupx.product.productservice.exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(Long id) {
        super("Resource id not found : " + id);
    }
}
