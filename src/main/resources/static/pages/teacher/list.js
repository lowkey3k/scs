layui.extend({
	admin: '{/}../../static/js/admin'
});

layui.use(['table', 'jquery','form', 'admin'], function() {
	var table = layui.table,
		$ = layui.jquery,
		form = layui.form,
		admin = layui.admin;
	$.ajax({
        url: "http://localhost:9001/teacher",
        type: "GET",
        contentType: "application/json;charset=utf-8",
        success: function (json) {
            if (json.result == "") {
                json.result = {};
            } else {
				table.render({
					elem: '#teacherList',
					cellMinWidth: 80,
					cols: [
						[ {
							field: 'id',title: 'ID',sort: true,width:50
						}, {
							field: 'teacherName',title: '姓名',width:80,templet:function(res) {
								alert(res.user);
                                return res.user.username;
                            }
						}, {
							field: 'sex',title: '性别',sort: true,width:80,templet:function(res){
								switch(res.sex){
									case '1':return '男';
									case '2':return '女';
									case '3':return '未知'
								}
							}
						}, {
							field: 'age',title: '年龄',sort: true,width:80
						}, {
							field: 'birthday',title: '生日',sort: true,width:80
						}, {
							field: 'email',title: '邮箱',width:150
						}, {
							field: 'phoneNumber',title: '联系方式',width:100
						}, {
							field: 'name',title: '隶属学院',templet:function(res){
                                return '<em>' + res.department.name + '</em>'
							}
						}, {
							field: 'operate',title: '操作',align:'center',toolbar: '#teacherOpBar',unresize: true
						}]
					],
					data: json.result,
					event: true,
					page: true
				});

                //监听工具条
                table.on('tool(teacherbar)', function(obj){
                    var data = obj.data;
                    if(obj.event === 'detail'){

                        layer.msg('ID：'+ data.id + ' 的查看操作');

                    } else if(obj.event === 'del'){
                        layer.confirm('真的删除行么', function(index){
                            $.ajax({
                                url: "http://localhost:9001/teacher/"+data.id,
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

