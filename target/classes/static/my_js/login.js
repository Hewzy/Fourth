// 校验用户名和密码是否存在
var count =0;
function checkUser(){
    var username=$("#username").val();  // 根据id获取用户名
    var password=$("#password").val();  // 根据id获取密码
    $.ajax({
        type:'post',
        url:'/checkUser',
        data:{"username":username,
            "password":password},
        dataType:'JSON',

        success:function(data){
            if(data==0) {
                alert("用户不存在");
            }
            else if(data==1){
                count++;
                // 检验密码输入次数，大于3，跳转到注册页面，小于3计数器加1
                if(count<=3){
                    var n = 3-count;
                    alert("密码错误,你还有"+n+"次机会");}
                else{
                    alert("密码次数超限，您可以重新注册");
                    location.href="register";
                }
            }
            // 跳转到controller 查询对应用户所有的自己消息内容
            else location.href="myMessage";
        }
    });
}