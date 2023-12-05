package prj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import prj.model.OrderDetail;
import prj.repo.OrderDetailRepo;
import prj.repo.RedisRepo;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepo orderDetailRepo;
    @Autowired
    RedisRepo redisRepo;

    public void save(OrderDetail orderDetail){
        redisRepo.deleteByID(orderDetail.getId());
        orderDetailRepo.save(orderDetail);
    }

    public OrderDetail findByID(int id){
        OrderDetail orderDetail = redisRepo.findByID(id);
        if(orderDetail != null) {
            System.out.println("Get From Redis");
            return orderDetail;
        }else {
            System.out.println("Get From MySQL");
            orderDetail = orderDetailRepo.findOrderdetailById(id);

            //如果在数据库存在，则加入缓存
            if(orderDetail !=null) {
                redisRepo.save(id, 24 * 60 * 60, orderDetail);
                return orderDetail;
            }
        }

        return orderDetail;
    }

    @GetMapping("/findAll/{page}/{size}")
    public Page<OrderDetail> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        PageRequest request = PageRequest.of(page,size);
        return orderDetailRepo.findAll(request);
    }

    public void deleteByID(int id){
        redisRepo.deleteByID(id);
        orderDetailRepo.deleteById(id);
    }
}
