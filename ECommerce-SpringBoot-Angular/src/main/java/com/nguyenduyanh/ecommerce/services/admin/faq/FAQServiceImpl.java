package com.nguyenduyanh.ecommerce.services.admin.faq;

import com.nguyenduyanh.ecommerce.dto.FAQDto;
import com.nguyenduyanh.ecommerce.emuns.OrderStatus;
import com.nguyenduyanh.ecommerce.entity.FAQ;
import com.nguyenduyanh.ecommerce.entity.Order;
import com.nguyenduyanh.ecommerce.entity.Product;
import com.nguyenduyanh.ecommerce.repository.FAQRepository;
import com.nguyenduyanh.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService{

    private final FAQRepository faqRepository;

    private final ProductRepository productRepository;


    @Override
    public FAQDto postFAQ(Long productId, FAQDto faqDto) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            FAQ faq = new FAQ();
             faq.setQuestion(faqDto.getQuestion());
             faq.setAnswer(faqDto.getAnswer());
             faq.setProduct(optionalProduct.get());
             return faqRepository.save(faq).getFAQDto();

        }
        return null;
    }
}
