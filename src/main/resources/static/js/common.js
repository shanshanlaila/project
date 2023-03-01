/**
 * 封装弹出方法
 * @param url
 * @param title
 */
function openLayer(url, title) {
    $.ajaxSettings.async = false;
    $.get(url, function (res) {
        layer.open({
            type: 1,
            title: title,
            area: ['800px', '450px'],
            content: res
        });
    });
    $.ajaxSettings.async = true;
}

/**
 * 封装提交表达方法
 * @param filter
 * @param type
 */
function mySubmit(filter, type, func) {
    layui.form.on('submit(' + filter + ')', function (data) {

        if (typeof (func) != 'undefined') {
            func(data.field);
        }


        $.ajax({
            url: data.form.action,
            async: false,
            type: type,
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(data.field),//解析json数据
            success: function (res) {
                if (res.code === 0) {
                    //响应成功关掉弹出层
                    layer.closeAll();
                    //查询
                    query();
                } else {
                    layer.alert(res.msg);
                }
            }
        })
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
}

/**
 * 公共删除方法
 * @param url
 */
function myDelete(url) {
    $.ajax({
        url: url,
        async: false,
        type: 'DELETE',
        success: function (res) {
            if (res.code === 0) {
                //查询
                query();
            } else {
                layer.alert(res.msg);
            }
        }
    });
}
