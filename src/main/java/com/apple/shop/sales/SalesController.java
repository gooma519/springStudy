package com.apple.shop.sales;

import com.apple.shop.user.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class SalesController {
    private final SalesService salesService;
    private final SalesRepository salesRepository;

    @PostMapping("/order/{id}")
    String order(@RequestBody Map body, Authentication auth, @PathVariable Long id) {
        if(auth == null) {
            return "redirect:/signin";
        }
        CustomUser user = (CustomUser) auth.getPrincipal();
        salesService.salesOrder(body, user.id);
        return "redirect:/detail/"+id;
    }

    @GetMapping("/order/all")
    String getOrderAll(Model model) {
        List<Sales> sales = salesRepository.customFindAll();
        System.out.println(sales.get(0));
        return "list.html";
    }
}
