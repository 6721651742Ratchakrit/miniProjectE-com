package lib.Discount;

import lib.CartItem;

/**
 * กลยุทธ์การคิดราคาแบบปกติ
 */
public class DefaultPriceStrategy implements DiscountStrategy {
    @Override
    public double calculatePrice(CartItem item) {
       return item.getProduct().getPrice() * item.getQuantity();
    }
    
}
