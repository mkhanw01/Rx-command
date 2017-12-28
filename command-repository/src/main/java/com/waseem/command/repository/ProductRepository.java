package com.waseem.command.repository;

import com.waseem.command.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Created by khan on 12/28/17.
 */
public interface ProductRepository  extends ReactiveMongoRepository<Product, String> {
}
