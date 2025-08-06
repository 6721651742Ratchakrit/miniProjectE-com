package lib;

import java.util.ArrayList;
public class ShoppingCart {

    private final ArrayList<CartItem> items;
    private final PricingService pricingService;
    private final ProductCatalog productCatalog;
    //Rep Invariant (RI):
    // - productCatalog ไม่เป็นค่า null และไม่มีสินค้าที่ซ้ำกันในตะกร้า
    // - ใน item ไม่เป็นค่า null
    //
    // Abstraction Function (AF):
    // -AF(item , pricingServic , productCatalog) = ตะกร้าสินค้าที่เก็บรายการสินค้าต่างๆ พร้อมจำนวนที่เลือกไว้จากสินค้าใน productCatalog
    //                                              และเก็บ pricingServic ไว้คิดเงินและ Discount ต่างๆ
    //
    private void checkRep(){
        if (items == null) {
            throw new RuntimeException("item is null ");
        }
        ArrayList<Product> seen = new ArrayList<>();
        for (CartItem item : items) {
            if (item == null) {
                throw new RuntimeException("item is null ");
            }
            Product product = item.getProduct();
            if (seen.contains(product)) {
                throw new RuntimeException(" duplicate product in cart");
            }
            seen.add(product);
        }
    }
    /**
     * สร้างออบเจกต์ตะกร้าสินค้าใหม่ ที่มีบริการคำนวณราคาและแคตตาล็อกสินค้า
     * @param pricingService รับ obj ส่วนลดบริการที่ใช้สำหรับคำนวณราคาของสินค้าในตะกร้า
     * @param productCatalog รับ obj แคตตาล็อกสินค้าที่ใช้ในการดูข้อมูลสินค้า
     */
    public ShoppingCart(PricingService pricingService,ProductCatalog productCatalog){
      this.items = new ArrayList<>();
      this.pricingService = pricingService;
      this.productCatalog = productCatalog;
      checkRep();
    }
    /**
     * เพิ่มสินค้าเข้าตะกร้าพร้อมกับระบุจำนวน สินค้าที่เพิ่มต้องมากกว่า 0 และถ้ามีสินค้านั้นอยู่เเล้วให้เพิ่มเเค่จำนวน
     * และถ้า product เป็น null คืนค่า blank
     * @param productID สินค้าที่ต้องการเพิ่ม
     * @param quantity จำนวนที่ต้องการเพิ่ม
     * 
     */
    public void addItem(String productID,int quantity){
         if (quantity <= 0) return;
        Product product = productCatalog.findById(productID);
        if (product == null) {
            return ;
        }
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                item.increaseQuantity(quantity);
                checkRep();
                return;
            }
        }
        items.add(new CartItem(product, quantity));
        checkRep();
    }
    /**
     * ลบสินค้าแรกที่ตรงกับสินค้าที่ระบุ
     * @param productId รหัสสินค้าที่ต้องการลบออกจากรายการ
     * หากไม่พบสินค้าที่มีรหัสตรงกัน รายการสินค้าจะไม่ถูกเปลี่ยนแปลง
     */
    public void removeItem(String productId){
       for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().getProductId().equals(productId)) {
                items.remove(i);
                checkRep();
                break;
            }
       }
    }
    /**
     * ราคาที่ได้คำนวณจากสูตรเเละ Discount แล้ว
     * @return total ราคาสินค้าที่ได้คำนวณมาเเล้ว
     */
    public double getTotalPrice(){
        double total = 0;
        for (CartItem item : items) {
            total += pricingService.calculateItemPrice(item);
        }
        return total;
    }
    /**
    * 
    * คืนค่าจำนวนรายการสินค้าที่อยู่ในตะกร้า
    * @return จำนวนรายการสินค้าที่อยู่ในตะกร้า
    */
    public int getItemCount(){
        return items.size();
    }
    /**
     * 
     * ลบสินค้าจากตะกร้า
     */
    public void clearCart(){
        items.clear();
        checkRep();
    }
}