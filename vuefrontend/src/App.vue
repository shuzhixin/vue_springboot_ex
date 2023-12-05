<template>
  <div id="orderManager">
    <el-container >

      <el-aside width="210px" >

        <el-menu router :default-openeds="['0', '1']">
          <el-submenu v-for="(menuItem,menuIndex) in $router.options.routes" :index="menuIndex+''" v-if="menuItem.show"  :key="menuIndex">
            
           <el-menu-item v-for="(activeItem,activeIndex) in menuItem.children" :index="activeItem.path" :key="activeItem.path"
                          :class="$route.path==activeItem.path?'is-active':''">{{activeItem.name}}</el-menu-item>
          <template slot="title">{{menuItem.name}}</template>                          
          </el-submenu>
        </el-menu>

      </el-aside>

      <el-main>
        <router-view></router-view>
      </el-main>

    </el-container>
  </div>
</template>