package org.confiz;

import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.UUID;

public class Promotion {
    private UUID promotionId;
    private String promotionName;
    private UUID productId;
    private String productName;

    public Promotion(UUID promotionId, String promotionName, UUID productId, String productName) {
        this.promotionId = promotionId;
        this.promotionName = promotionName;
        this.productId = productId;
        this.productName = productName;
    }

    public UUID getPromotionId() {
        return promotionId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public UUID getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(productId)
                .append(productName)
                .append(promotionId)
                .append(promotionName)
                .toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Promotion)) {
            return false;
        }

        Promotion promotion = (Promotion) o;

        return promotionId.equals(promotion.productId)
                && promotionName.equals(promotion.productName)
                && productId.equals(promotion.productId)
                && productName.equals(promotion.productName);
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "promotionId=" + promotionId +
                ", promotionName='" + promotionName + '\'' +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                '}';
    }
}
