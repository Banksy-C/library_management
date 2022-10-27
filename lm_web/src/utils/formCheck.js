// 账号
// let codeReg = /^(?![0-9]*$)(?![a-zA-Z]*$)[a-zA-Z0-9]{6,20}$/
// 电话: 1 3~9 0-9任意匹配9个数，9+2=11
let phoneReg = /^[1][3,4,5,6,7,8,9][0-9]{9}$/
// 必须为数字
let numberReg = /^\d+$|^\d+[.]?\d+$/
// // 密码
// let passwordReg = /^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$)([^\u4e00-\u9fa5\s]){6,20}$/
// // 联系人
// let contactsReg = /^[\u0391-\uFFE5A-Za-z]+$/
//
// let regId = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/
//
// let emailReg = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/


let FormValidate = (function () {
    function FormValidate () {}
    // From表单验证规则  可用于公用的校验部分
    FormValidate.Form = function () {
        return {
            // 电话号码的验证
            validatePhone (rule, value, callback) {
                if (!value) {
                    return callback(new Error('请输入手机号码！'))
                }
                if (!phoneReg.test(value)) {
                    callback(new Error('请输入正确格式的手机号！'))
                } else {
                    callback()
                }
            },

            // 只能数字的验证
            validateNumber (rule, value, callback) {
                if (value !== '') {
                    if (!numberReg.test(value)) {
                        callback(new Error('必须为数字！'))
                    } else {
                        callback()
                    }
                } else {
                    callback()
                }
            },


        }
    }
    return FormValidate
}())//(function () {}())为IIFE【立即调用函数】，告诉解释器这里有一个需要立即执行的函数，可自动执行

exports.FormValidate = FormValidate