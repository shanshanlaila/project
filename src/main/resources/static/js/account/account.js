//渲染时间控件
layui.laydate.render({
    elem: '#createTimeRange'
    , range: true
    //2020-11-12 - 2020-12-20
});


var table = layui.table;
// 第一个实例
var tableIns = table.render({
    id: 'test',
    elem: '#accountList'
    , url: '/account/list'
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
        {field: 'username', title: '用户名'}
        , {field: 'realName', title: '真实姓名'}
        , {field: 'roleName', title: '角色名称'}/*account表中没有此字段的需要联表查询*/
        , {field: 'sex', title: '性别'}
        , {field: 'createTime', title: '创建时间'}
        , {title: '操作', toolbar: '#barDemo'}
    ]]

});

function query() {
    tableIns.reload({
        where: {
            realName: $("#realName").val(),
            email: $("#email").val(),
            createTimeRange: $("#createTimeRange").val()
        }, page: {
            curr: 1
        }
    })
}

function intoAdd() {
    openLayer("/account/toAdd", "新增账号")
    let form = layui.form;
    form.render();
    mySubmit("addSubmit", 'POST');//特别注意单引号
}

table.on('tool(test)', function (obj) {
    var data = obj.data;
    var layEvent = obj.event;
    let accountId = data.accountId;
    if (layEvent === 'detail') {
        openLayer('/account/toDetail/' + accountId, "查看账户信息详情")
    } else if (layEvent === 'del') {
        layer.confirm('真的删除行么', function (index) {
            layer.close(index);
            myDelete('/account/' + accountId);
        });
    } else if (layEvent === 'edit') { //编辑
        //弹窗
        openLayer('/account/toUpdate/' + accountId, '编辑账号');

        layui.form.render();//表单渲染
        //提交表单
        mySubmit('updateSubmit', 'PUT');

    }

});

// 表单验证
layui.form.verify({
    /*新增的判重需要与所有的id经行比较，修改的判重不要与自身比较*/
    checkUsername: function (value, item) { //value：表单的值、item：表单的DOM对象
        let url = '/account/' + value;
        let accountId = $("input[name='accountId']").val();
        /*判断是否是修改页的判重*/
        if (typeof (accountId) != 'undefined') {
            url += '/' + accountId;
        }
        let error = null;
        $.ajax({
            url: url
            , async: false
            , type: 'GET'
            , success: function (res) {
                if (res.code === 0) {
                    if (res.data > 0) {
                        error = "用户名已经存在";
                    }
                } else {
                    error = "用户名检测出错";
                }
            },
            error: function () {
                error = "网络原因，用户名检测出错";
            }
        });
        if (error != null) {
            return error;
        }
    }
});
