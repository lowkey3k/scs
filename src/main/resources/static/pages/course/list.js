layui.extend({
	admin: '{/}../../static/js/admin'
});

layui.use(['table', 'jquery','form', 'admin'], function() {
	var table = layui.table,
		$ = layui.jquery,
		form = layui.form,
		admin = layui.admin;
	alert(111);
    $.ajax({
        url: "http://localhost:9001/course",
        type: "GET",
        contentType: "application/json;charset=utf-8",
        success: function (json) {
            if (json.result == "") {
                json.result = {};
            } else {
                table.render({
                    elem: '#courseList',
                    cellMinWidth: 80,
                    cols: [
                        [{
                            field: 'id',title: '课程编号',sort: true
                        }, {
                            field: 'courseName',title: '课程名称',sort: true
                        }, {
                            field: 'courseHour',title: '学时',sort: true
                        },{
                            field: 'startTerm',title: '开始学期',sort: true
                        },{
                            field: 'operate',title: '操作',align:'center',toolbar: '#courseOpBar',unresize: true
                        }]
                    ],
                    data: json.result,
                    event: true,
                    page: true
                });

                //监听工具条
                table.on('tool(coursebar)', function(obj){
                    var data = obj.data;
                    if(obj.event === 'detail'){

                        layer.msg('ID：'+ data.id + ' 的查看操作');

                    } else if(obj.event === 'del'){
                        layer.confirm('真的删除行么', function(index){
                            $.ajax({
                                url: "http://localhost:9001/course/"+data.id,
                                type: "DELETE",
                                contentType: "application/json;charset=utf-8",
                                success: function (json) {
                                    obj.del();
                                    layer.close(index);
                                    layer.alert("删除成功")
                                }
                            });



                        });
                    } else if(obj.event === 'edit'){
                        layer.alert('编辑行：<br>'+ JSON.stringify(data))
                    }
                });
            }
        }
    });
	/*
	 *数据表格中form表单元素是动态插入,所以需要更新渲染下
	 * http://www.layui.com/doc/modules/form.html#render
	 * */
	$(function(){
		form.render();
	});
});

