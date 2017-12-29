package com.waseem.command.service.command;

import com.waseem.command.entity.Cart;
import com.waseem.command.model.service.RemoveProductFromCartRequest;
import com.waseem.command.service.Command;

/**
 * Created by khan on 12/29/17.
 */
public interface RemoveProductFromCartCommand extends Command<Cart, RemoveProductFromCartRequest> {
}
