var countdown=3;
function settime(obj) {
    if (countdown == 0) {
        obj.removeAttribute("disabled");
        obj.value="免费获取验证码";
        countdown = 3;
        this.reloadImage();
        return;
    } else {
        obj.setAttribute("disabled", true);
        obj.value="重新发送(" + countdown + ")";
        countdown--;
    }
    setTimeout(function() {
            settime(obj) }
        ,1000)
}

function reloadImage(){
    document.getElementById('imgCode').src="/getVerifyCode?time="+new Date().getTime();
}

//注册用户名是否存在，失去焦点时判断
function checkName(){
    var username=$("#username").val();
    $.ajax({
        type:'post',
        url:'/checkName',
        data:{"username":username},
        dataType:'JSON',
        success:function(data){
            if(data!=null)
                alert("用户名已存在");
        }
    });
}

//判断验证码是否正确
function checkSafeCode(){
    var safeCode=$("#safeCode").val();
    $.ajax({
        type:'post',
        url:'/checkSafeCode',
        data:{"safeCode":safeCode},
        dataType:'JSON',
        success:function(data){
            if(data==0){
                alert("验证码错误！");
            }
        }
    });
}

//判断用户名的手机格式是否正确
function checkNumber(){
    var number = $("#number").val();
    if(number.length!=11) {
        alert("手机号格式错误");
    }
    else{
        return true;
    }
}
