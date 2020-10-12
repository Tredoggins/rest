package edu.wctc.wholesale.controller;

import edu.wctc.wholesale.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @Autowired
    private OrderService oService;
    @RequestMapping("/")
    public String showHomePage(Model m) {
        m.addAttribute("orderList",oService.getAllOrders());
        return "index";
    }
}
