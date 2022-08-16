var table = layui.table;
// 第一个实例
var tableIns = table.render({
    elem: '#customerList'
    , url: '/customer/list'
    , page: true
    , parseData: function (res) {
        return {
            "code": res.code,
            "msg": res.msg,
            "count": res.data.total,
            "data": res.data.records,
        };
    }
    , cols: [[
        {field: 'realName', title: '真实姓名'}
        , {field: 'sex', title: '性别'}
        , {field: 'age', title: '年龄'}
        , {field: 'phone', title: '手机号码'}
        , {field: 'createTime', title: '创建时间'}
        , {title: '操作', toolbar: '#barDemo'}
    ]]
});

/*按条件查询*/
function query() {
    tableIns.reload({
        where: {
            realName: $("#realName").val()
            , phone: $("#phone").val()
        }
        , page: {
            curr: 1
        }
    });
}

/*点击增加按钮弹出增加客户页面的方法*/
function toAdd() {
    //弹窗
    openLayer('/customer/toAdd', '新增客户');

    layui.form.render();//表单渲染
    //提交表单
    mySubmit('addSubmit','POST');

}


//单元格工具事件
table.on('tool(test)', function(obj){ // 注：test 是 table 原始标签的属性 lay-filter="对应的值"
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

    let customerId = data.customerId;

    if(layEvent === 'detail'){ //查看
        //do somehing
        openLayer('/customer/toDetail/'+customerId, '客户详情');
    } else if(layEvent === 'del'){ //删除
        layer.confirm('确定删除吗？', function(index){
            // 关闭确认框
            layer.close(index);

            myDelete('/customer/'+customerId);

            // 向服务端发送删除请求，执行完毕后，可通过 reloadData 方法完成数据重载
            /*
            table.reloadData(id, {
              scrollPos: 'fixed'  // 保持滚动条位置不变 - v2.7.3 新增
            });
            */
        });
    } else if(layEvent === 'edit'){ //编辑
        //弹窗
        openLayer('/customer/toUpdate/'+customerId, '修改客户');
        layui.form.render();//表单渲染
        //提交表单
        mySubmit('updateSubmit','PUT');

    }
});

