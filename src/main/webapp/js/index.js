/*
    登录操作
*/
function login() {
    /*获取参数*/
    var userName=$("#username").val();
    var userPwd=$("#password").val();

    /*判断参数是否为空*/
    if(isEmpty(userName)){
        alert("用户名不能为空！");
        return;
    }

    if(isEmpty(userPwd)){
        alert("密码不能为空！");
        return;
    }

    /*发送ajax请求*/
    $.ajax({
        url:ctx+"/user/login",
        type:"post",
        data:{
            userName:userName,
            userPwd:userPwd
        },
        success:function (data) {
            if(data.code==200){
                /*存cookie*/
                $.cookie('userIdStr', data.result.userIdStr);
                $.cookie('userName', data.result.userName);
                $.cookie('realName', data.result.realName);

                window.location.href=ctx+"/main";
            }else{
                alert(data.msg);
            }
        }
    })

}