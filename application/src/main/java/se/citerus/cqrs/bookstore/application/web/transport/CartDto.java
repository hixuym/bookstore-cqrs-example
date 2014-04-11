package se.citerus.cqrs.bookstore.application.web.transport;

import com.fasterxml.jackson.annotation.JsonProperty;
import se.citerus.cqrs.bookstore.application.web.model.Cart;
import se.citerus.cqrs.bookstore.application.web.model.Item;
import se.citerus.cqrs.bookstore.application.web.model.LineItem;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class CartDto extends TransportObject {

  @NotNull
  public final String cartId;
  @Min(1)
  public final long totalPrice;
  @Min(1)
  public final int totalQuantity;
  @NotNull
  public final List<LineItemDto> lineItems;

  public CartDto(@JsonProperty("cartId") String cartId,
                 @JsonProperty("lineItems") @Valid List<LineItemDto> lineItems,
                 @JsonProperty("totalPrice") long totalPrice,
                 @JsonProperty("totalQuantity") int totalQuantity) {
    this.cartId = cartId;
    this.totalPrice = totalPrice;
    this.totalQuantity = totalQuantity;
    this.lineItems = lineItems;
  }

  public static CartDto fromCart(Cart cart) {
    long totalCartAmount = 0;
    int totalCartQuantity = 0;
    List<LineItemDto> lineItems = new ArrayList<>();

    for (LineItem lineItem : cart.getItems()) {
      long amount = lineItem.getTotalAmount();
      int quantity = lineItem.getQuantity();

      totalCartAmount += amount;
      totalCartQuantity += quantity;

      Item item = lineItem.getItem();
      lineItems.add(new LineItemDto(item.bookId.id, item.title, item.price, quantity, amount));
    }

    return new CartDto(cart.cartId, lineItems, totalCartAmount, totalCartQuantity);
  }

}
