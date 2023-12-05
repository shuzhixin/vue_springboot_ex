<template>
    <el-form style="width: 70%" :model="validateForm" :rules="validateRules" ref="validateForm" label-width="120px">

        <el-form-item label="订单编号" prop="id">
            <el-input v-model="validateForm.id"></el-input>
        </el-form-item>

        <el-form-item label="订单内容" prop="name">
            <el-input v-model="validateForm.name"></el-input>
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="submitForm('validateForm')">添加</el-button>
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
                validateRules: {
                    id: [
                        { required: true, message: '订单编号不能为空'  }
                    ],
                    name: [
                        { required: true, message: '订单名称不能为空'  }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                const _this = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$axios.post('http://localhost:8085/save',this.validateForm).then(function(resp){
                            if(resp.data == 'success'){
                                _this.$alert(_this.validateForm.name+'添加成功！', '对话框', {
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
        }
    }
</script>

