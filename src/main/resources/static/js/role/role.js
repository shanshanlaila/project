const table = layui.table;
// 第一个实例
const tableIns = table.render({
    id: 'test',
    elem: '#roleList'
    , url: '/role/list'
    , page: true
    , parseData: function (res) {
        return {
            "code": res.code,
            "msg": res.msg,
            "count": res.data.count,
            "data": res.data.records,
        };
    }
    , cols: [[
        {field: 'roleName', title: '角色名称'}
        , {field: 'createTime', title: '创建时间'}
        , {title: '操作', toolbar: '#barDemo'}
    ]]

});

/*模糊查询*/
function query() {
    tableIns.reload({
        where: {
            like$role_name: $("#roleName").val(),
        }, page: {
            curr: 1
        }
    })
}


function intoAdd() {
    //弹窗
    openLayer('/role/toAdd', '新增角色');

    showTree('/role/listResource', 'resource')

    //提交表单
    mySubmit('addSubmit', 'POST', addIds);

}

//单元格工具事件
table.on('tool(test)', function (obj) { // 注：test 是 table 原始标签的属性 lay-filter="对应的值"
    const data = obj.data; //获得当前行数据
    const layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    const tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

    let roleId = data.roleId;

    if (layEvent === 'detail') { //查看
        //do something
        openLayer('/role/toDetail/' + roleId, '角色详情');

        showTree('/role/listResource/' + roleId+'/1', 'resource',false);
    } else if (layEvent === 'del') { //删除
        layer.confirm('确定删除吗？', function (index) {
            // 关闭确认框
            layer.close(index);
            console.log(roleId)
            myDelete('/role/' + roleId);

        });
    } else if (layEvent === 'edit') { //编辑
        //弹窗
        openLayer('/role/toUpdate/' + roleId, '修改角色');

        showTree('/role/listResource/' + roleId+'/0', 'resource');
        //提交表单
        mySubmit('updateSubmit', 'PUT', addIds);

    }
});

function showTree(url, id,showCheckbox) {
    if (typeof (showCheckbox)==='undefined'){
        showCheckbox=true;
    }
    $.ajax({
        url: url,
        async: false,
        type: 'GET',
        success: function (res) {
            if (res.code === 0) {
                //渲染树形结构
                layui.tree.render({
                    elem: '#' + id,
                    data: res.data,
                    id: id,
                    showCheckbox: true
                })
            }
        }
    })
}

const addIds = function (field) {
    let checked = layui.tree.getChecked('resource');
    field.resourceIds = getIds(checked, []);
}

/*把树形节点转换成一个数组*/
function getIds(checkData, arr) {
    for (let i in checkData) {
        arr.push(checkData[i].id);
        arr = getIds(checkData[i].children, arr);
    }
    return arr;
}


