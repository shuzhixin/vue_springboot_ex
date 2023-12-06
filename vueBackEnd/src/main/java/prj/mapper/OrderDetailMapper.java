package prj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import prj.model.OrderDetail;

import java.util.List;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
    public int addOrderDetail(OrderDetail orderDetail);
    public int deleteOrderDetailById(Integer id);
    public int updateOrderDetailById(OrderDetail orderDetail);
    public OrderDetail getOrderDetailById(Integer id);

    public Page<OrderDetail> getAllOrderDetails(IPage<OrderDetail> page);

}
