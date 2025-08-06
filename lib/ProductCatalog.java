package lib;
import java.util.ArrayList;

public class ProductCatalog {

    ArrayList<Product> products = new ArrayList<>();
    // RI : products list is not null, contains no null elements ,and no duplicate product.
    // AF : AF(product) = A catelog of all available product.

    private void checkRep(){
        if (products == null) {
            throw new RuntimeException("RI violated : product is null");
        }
        for (int i = 0; i < products.size(); i++) {
            for (int j = i + 1; j < products.size(); j++) {
                if (products.get(i).equals(products.get(j))) {
                     throw new RuntimeException("RI violated : duplicate product");
                }
            }
        }
    }
    /**
     * เพิ่มสินค้าใหม่เข้าสู้แคตตาล็อก
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
     * @return  อ็อบเจกต์ Product หากพบ หรือ null หากไม่พบจะ null
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
