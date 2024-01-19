package com.nguyenduyanh.ecommerce.services.admin.faq;

import com.nguyenduyanh.ecommerce.dto.FAQDto;
import com.nguyenduyanh.ecommerce.entity.FAQ;

public interface FAQService {

    FAQDto postFAQ(Long productId, FAQDto faqDto);

}
