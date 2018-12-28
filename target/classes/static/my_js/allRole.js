//修改用户角色
function checkPower() {
    var name=$("#userPower option:selected").val();
    var role=$("#power option:selected").val();
    $.ajax({
        type:'post',
        url:'/editPower',
        data:{"username":name,
            "role":role},
        dataType:'JSON',
        success:function(data){
            console.log(data);
            if(data==1) {alert("修改成功");}
            else {alert("修改失败");}
        }
    });
}
//创建新的角色
function creatNewRole() {
    var role = $("#newRole").val();
    var power = document.getElementById("permission");
    var str = [];
    for(i=0;i<power.length;i++){
        if(power.options[i].selected){
            str.push(power[i].value);
        }
    }

    $.ajax({
        type:'post',
        url:'/creatNewRole',
        data:{"role":role,
            "str":str},
        dataType:'JSON',
        success:function(data){
            if(data==1) {alert("创建成功");}
            else {alert("创建失败");}
        }
    });
}
//检查角色是否已经存在
function checkRole() {
    var role = $("#newRole").val();
    $.ajax({
        type:'post',
        url:'/checkRole',
        data:{"role":role},
        dataType:'JSON',
        success:function(data){
            if(data!=0)
                alert("角色已存在！");
        }
    });
}
//更改角色的权限
function changePermission() {
    var role = $("#selectRole").val();
    var power = document.getElementById("selectPermission");
    var str = [];
    for(i=0;i<power.length;i++){
        if(power.options[i].selected){
            str.push(power[i].value);
        }
    }

    $.ajax({
        type:'post',
        url:'/changePermission',
        data:{"role":role,
            "str":str},
        dataType:'JSON',
        success:function(data){
            if(data==1) {alert("修改成功");}
            else {alert("修改失败");}
        }
    });
}

