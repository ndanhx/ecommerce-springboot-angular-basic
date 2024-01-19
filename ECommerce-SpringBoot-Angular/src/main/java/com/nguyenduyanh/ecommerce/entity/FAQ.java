package com.nguyenduyanh.ecommerce.entity;

import com.nguyenduyanh.ecommerce.dto.FAQDto;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class FAQ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public FAQDto getFAQDto() {
        FAQDto faqDto = new FAQDto();
        faqDto.setId(id);
        faqDto.setAnswer(answer);
        faqDto.setQuestion(question);
        faqDto.setProductId(product.getId());
        return faqDto;
    }
}
