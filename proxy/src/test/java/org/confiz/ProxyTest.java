package org.confiz;

import org.junit.Assert;
import org.junit.Test;

public class ProxyTest {
    @Test
    public void test() {
        PromotionService promotionService = new CachedPromotionProxy(new PromotionServiceImpl());
        Promotion promotion = promotionService.getPromotion(Region.US);

        Assert.assertNotNull(promotion);

        Promotion promotion1 = promotionService.getPromotion(Region.US);

        Assert.assertSame(promotion, promotion1);
        Assert.assertEquals(promotion, promotion1);
    }
}
