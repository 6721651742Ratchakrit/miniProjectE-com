package lib;

import java.util.ArrayList;
import lib.Discount.*;
/**
 * คลาสสำหรับจัดการโปรโมชั่นและคำนวณราคา
 */
public class PricingService {
    private record StrategyRule(String sku , DiscountStrategy Strategy) {}
    private final ArrayList<StrategyRule> strategies = new ArrayList<>();
    private final DiscountStrategy defaultStrategy = new DefaultPriceStrategy();
    /**
     * ลงทะเบียนกลยุทธ์ส่วนลดสำหรับสินค้า SKU ที่กำหนด
     * หากมีโปรโมชันสำหรับ SKU  นี้อยู่แล้ว จะถูกแทนที่ด้วยอันใหม่
     * @param sku   รหัสสินค้าที่ต้องการผูกกันโปรโมชั่น
     * @param strategy กลยุทธ์ส่วนลดที่จะใช้
     */
    public void addStrategy(String sku, DiscountStrategy strategy){
        StrategyRule ruleToRemove = null;
        for (StrategyRule rule : strategies) {
            if (rule.sku.equals(sku)) {
                ruleToRemove = rule;
                break;
            }
        }
        if (ruleToRemove != null) {
            strategies.remove(ruleToRemove);
        }
        strategies.add(new StrategyRule(sku, strategy));
    }
    public double calculateItemPrice(CartItem item){
        String sku = item.getProduct().getProductId();
        for (StrategyRule rule : strategies) {
            if (rule.sku().equals(sku)) {
                return rule.Strategy().calculatePrice(item);
            }
        }
        return defaultStrategy.calculatePrice(item);
    }
}
