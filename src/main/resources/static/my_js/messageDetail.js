function editRemark(){
    console.log("test")
    var remark = $("#remark").val();
    var title = $("#title").text();
    var id = $("#id").text();
    $.ajax({
        type:'post',
        url:'/insertRemark',
        data:{"remark":remark,
            "title":title,
            "id":id},
        dataType:'JSON',
        success:function(data){
            if(data!=0)
                alert("评论成功！");
            location.href="detailRefresh";
        }
    });
}