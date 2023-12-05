<template>
    <div>
        <el-table
                :data="OrderData"                
                style="width: 70%">
            <el-table-column
                    fixed
                    prop="id"
                    label="订单编号"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="订单内容"
                    width="100">
            </el-table-column>            
            <el-table-column                    
                    label="操作">
                <template slot-scope="scope">
                    <el-button @click="edit(scope.row)" type="text" >修改</el-button>
                    <el-button @click="delete(scope.row)" type="text" >删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        分页
        <el-pagination
                background
                layout="prev, pager, next"
                :page-size="pageSize"
                :total="total"
                @current-change="page">
        </el-pagination>
    </div>
</template>

<script>
    export default {
        methods: {
            delete(row){
                const _this = this
                this.$axios.delete('http://localhost:8085/deleteById/'+row.id).then(function(resp){
                    _this.$alert(row.name+'删除成功！', '消息', {
                        confirmButtonText: '确定',
                        callback: action => {
                            window.location.reload()
                        }
                    })
                })
            },
            edit(row) {
                this.$router.push({
                    path: '/OrderUpdate',
                    query:{
                        id:row.id
                    }
                })
            },
            page(currentPage){
                const _this = this
                this.$axios.get('http://localhost:8085/findOrders/'+(currentPage-1)+'/5').then(function(resp){
                    console.log(resp)
                    _this.OrderData = resp.data.content
                    _this.pageSize = resp.data.size
                    _this.total = resp.data.totalElements
                })
            }
        },

        data() {
            return {
                pageSize:'1',
                total:'5',
                OrderData: []
            }
        },

        created() {
            const _this = this
            this.$axios.get('http://localhost:8085/findOrders/0/5').then(function(resp){
                _this.OrderData = resp.data.content
                _this.pageSize = resp.data.size
                _this.total = resp.data.totalElements
            })
        }
    }
</script>