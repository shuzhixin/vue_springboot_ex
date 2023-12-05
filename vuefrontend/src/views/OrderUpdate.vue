<template>
    <el-form style="width: 70%" :model="validateForm" :rules="rules" ref="validateForm" label-width="120px">

        <el-form-item label="订单编号">
            <el-input v-model="validateForm.id" readOnly></el-input>
        </el-form-item>

        <el-form-item label="订单内容" prop="name">
            <el-input v-model="validateForm.name"></el-input>
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="submitForm('validateForm')">修改</el-button>
            <el-button @click="resetForm('validateForm')">重置</el-button>
        </el-form-item>

    </el-form>
</template>

<script>
    export default {
        data() {
            return {
                validateForm: {
                    id: '',
                    name: ''
                },
                rules: {
                    name: [
                        { required: true, message: '订单内容不能为空', trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                const _this = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$axios.put('http://localhost:8085/update',this.validateForm).then(function(resp){
                            if(resp.data == 'success'){
                                _this.$alert(_this.validateForm.name+'修改成功', '对话框', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/OrderManager')
                                    }
                                })
                            }
                        })
                    } else {
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        },
        created() {
            const _this = this
            this.$axios.get('http://localhost:8085/findById/'+this.$route.query.id).then(function(resp){
                _this.validateForm = resp.data
            })
        }
    }
</script>