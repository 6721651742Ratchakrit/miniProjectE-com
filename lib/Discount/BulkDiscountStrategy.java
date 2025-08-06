package lib.Discount;

import lib.CartItem;

/**
 * กลยุทธ์ส่วนลด Bulk (ซื้อเยอะลดราคา)
 */
public class BulkDiscountStrategy  implements DiscountStrategy{
    private final int minimunQuatity;
    private final double discountPercentage;

    public BulkDiscountStrategy (int minimunQuatity,double discountPercentage){
        this.minimunQuatity = minimunQuatity;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double calculatePrice(CartItem item) {
        double originalPrice = item.getProduct().getPrice() * item.getQuantity();
        if (item.getQuantity() >= minimunQuatity) {
            return originalPrice * (1.0 - discountPercentage);
        }
        return originalPrice;
    }
    
}
