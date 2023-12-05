import Vue from 'vue'
import Router from 'vue-router'
import Index from '../views/Index'
import OrderManager from '../views/OrderManager'
import AddOrder from '../views/AddOrder'
import OrderUpdate from '../views/OrderUpdate'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: '订单管理',
      show:true,
      component:Index,
      redirect:"/OrderManager",
      children:[
        {
          path:"/OrderManager",
          name:"所有订单",
          component:OrderManager
        },
        {
          path:"/AddOrder",
          name:"添加订单",
          component:AddOrder
        } ,
         {
          path:'/OrderUpdate',
          component:OrderUpdate,
          show:false
        }
      ]
    }

  ]
})
