package prj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import prj.model.OrderDetail;
import prj.service.OrderDetailService;

@RestController
public class Controller {
    @Autowired
    OrderDetailService orderDetailService;


    @GetMapping("/findOrders/{page}/{size}")
    public PageInfo<OrderDetail> findOrders(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return orderDetailService.findAll(page,size);
    }

    @PostMapping("/save")
    public String save(@RequestBody OrderDetail orderDetail){
        try {
            orderDetailService.save(orderDetail);
            return "success";
        } catch(Exception e){
            return "error";
        }
    }

    @GetMapping("/findById/{id}")
    public OrderDetail findById(@PathVariable("id") Integer id){
        return orderDetailService.findByID(id);
    }

    @PutMapping("/update")
    public String update(@RequestBody OrderDetail orderDetail){
        try {
            orderDetailService.save(orderDetail);
            return "success";
        } catch(Exception e){
            return "error";
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        orderDetailService.deleteByID(id);
    }
}
