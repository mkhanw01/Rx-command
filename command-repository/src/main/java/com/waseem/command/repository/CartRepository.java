package com.waseem.command.repository;

import com.waseem.command.entity.Cart;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Created by khan on 12/28/17.
 */
public interface CartRepository  extends ReactiveMongoRepository<Cart,String> {

}
