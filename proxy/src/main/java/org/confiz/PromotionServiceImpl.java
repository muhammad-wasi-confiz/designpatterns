package org.confiz;

import java.util.UUID;

public class PromotionServiceImpl implements PromotionService {

    public Promotion getPromotion(Region region) {
        return new Promotion(UUID.randomUUID(), UUID.randomUUID().toString(), UUID.randomUUID(), UUID.randomUUID().toString());
    }
}
