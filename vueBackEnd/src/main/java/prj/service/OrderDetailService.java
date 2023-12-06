package prj.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import prj.mapper.OrderDetailMapper;
import prj.model.OrderDetail;
import prj.repo.RedisRepo;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    RedisRepo redisRepo;

    public void save(OrderDetail orderDetail){
        OrderDetail tmpOrder = null;
        tmpOrder = this.findByID(orderDetail.getId());
        if(tmpOrder == null)
        {
            orderDetailMapper.addOrderDetail(orderDetail);
        }else {
            orderDetailMapper.updateOrderDetailById(orderDetail);
        }
    }

    public OrderDetail findByID(int id){
        OrderDetail orderDetail = null;
        orderDetail = orderDetailMapper.getOrderDetailById(id);
        return orderDetail;
    }

    public PageInfo<OrderDetail> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        LambdaQueryWrapper<OrderDetail> query = new LambdaQueryWrapper<>();
        page = page + 1;
        PageHelper.startPage(page, size);

        return new PageInfo<>(orderDetailMapper.selectList(query));
    }

    public void deleteByID(int id){
        orderDetailMapper.deleteOrderDetailById(id);
    }
}
