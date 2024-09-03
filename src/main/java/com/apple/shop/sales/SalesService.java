package com.apple.shop.sales;

import com.apple.shop.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SalesService {
    private final SalesRepository salesRepository;
    public void salesOrder(Map detail, Long id) {
        Sales sales = new Sales();
        Users user = new Users();
        user.setId(id);
        sales.setItemName((String) detail.get("item_name"));
        sales.setCount(Integer.parseInt((String) detail.get("count")));
        sales.setPrice(Integer.parseInt(detail.get("price").toString()));
        sales.setUser(user);
        salesRepository.save(sales);
    }
}
