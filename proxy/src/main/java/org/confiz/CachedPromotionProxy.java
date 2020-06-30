package org.confiz;

import java.util.HashMap;
import java.util.Map;

public class CachedPromotionProxy implements PromotionService {
    private final Map<Region, Promotion> cachedPromotions = new HashMap<>();
    private PromotionService promotionService;


    public CachedPromotionProxy(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @Override
    public Promotion getPromotion(Region region) {
        Promotion promotion = cachedPromotions.get(region);
        if (promotion != null) {
            return promotion;
        }

        promotion = promotionService.getPromotion(region);
        cachedPromotions.put(region, promotion);

        return promotion;
    }
}
