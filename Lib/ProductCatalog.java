package Lib;
import java.util.*;
/**
 * คลาสทำหน้าที่เป็นแคตตาล็อกสินค้า (Repository) 
 */
public class ProductCatalog {
    ArrayList<Product> products = new ArrayList<>();
    /** RI : products list in not null, contains no null elements
     *       and no duplicate product
     *  AF : AF(products) = A catalog of all available products
     */
    private void checkRep(){
        if (products == null) {
                throw new RuntimeException("RI violated: Product");
            }
        //check for duplicated products
        for (int i = 0; i < products.size(); i++) {
            for (int j = 0; j < products.size() ; j++) {
                if (products.get(i).equals(products.get(j))) {
                    throw new RuntimeException("RI violated: Duplicated product");
                }
            }
        }
    }
    public ProductCatalog(){
        checkRep();
    }
    /**
     * เพิ่มสินค้าใหม่สู่แคตตาล็อก
     * @param product สินค้าที่ต้องการเพิ่ม
     */
    public void addProduct(Product product){
        if (product != null && !products.contains(product)) {
            products.add(product);
        }
        checkRep();
    }
    /**
     * ค้นหาสินค้าจากรหัสสินค้า
     * @param productId รหัสสินค้าที่ต้องการค้นหา
     * @return อ็อปเจกต์ Product หากพบม หรือ null หากไม่พบ
     */
    public Product findById(String productId){
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                return p;
            }
        }
        return null;
    }
}
