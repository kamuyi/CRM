$(function () {
    var sid = $('#saleChanceId').val();
    // 表格初始化
    $('#dg').edatagrid({
        //查询地址
        url: ctx + "/cusDevPlan/queryCusDevPlansByParams?sid="+sid,
        saveUrl: ctx + "/cusDevPlan/saveOrUpdateCusDevPlan?sid="+sid,
        updateUrl: ctx + "/cusDevPlan/saveOrUpdateCusDevPlan?sid="+sid,
        destroyUrl: ""
    });

    //获取devResult
    var devResult=$("#devResult").val();

    //判断开发状态如果开发成功或者失败
    if(devResult==2 || devResult==3){
        // 隐藏工具条
        $('#toolbar').hide();
        // 使表格不可编辑
        $('#dg').edatagrid("disableEditing")
    }
});

//添加计划
function addRow(){
    $("#dg").edatagrid("addRow");
}

//保存计划
function saveOrUpdateCusDevPlan() {
    $("#dg").edatagrid("saveRow");

}

//删除计划
//删除
function delCusDevPlan () {
    deleteData('dg',ctx + '/cusDevPlan/deleteCusDevPlanBatch',loadEdatagrid);
}

function loadEdatagrid() {
    $("#dg").edatagrid("load");
}

// 更新开发状态
function updateSaleChanceDevResult(devResult) {
    var sid = $('#saleChanceId').val();
    $.ajax({
        url: ctx + '/saleChance/updateSaleChanceDevResult',
        type: 'post',
        data: {
            id: sid,
            devResult: devResult
        },
        success: function (data) {
            if (data.code == 200) {
                $.messager.alert('来自Crm', data.msg, 'info', function () {
                    // 隐藏工具条
                    $('#toolbar').hide();
                    // 使表格不可编辑
                    $('#dg').edatagrid("disableEditing")
                });
            } else {
                $.messager.alert('来自Crm', data.msg, 'error');
            }
        }
    })
}