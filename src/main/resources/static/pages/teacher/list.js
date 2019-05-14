layui.extend({
	admin: '{/}../../static/js/admin'
});

layui.use(['table', 'jquery','form', 'admin','laydate'], function() {
	var table = layui.table,
		$ = layui.jquery,
		form = layui.form,
		admin = layui.admin;
		laydate = layui.laydate;
    //日期
    laydate.render({
        elem: '#L_birthday'
    });
	$.ajax({
        url: remoteUrl+"teacher",
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
                                return '<em>' + res.gradeClass.department.name + '</em>'
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
                                url: remoteUrl+"teacher/"+data.id,
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
                        layer.open({
                        	type: 1,
                            fix: false, //不固定
                            maxmin: true,
                            shadeClose: true,
                            area: [600 + 'px', 500 + 'px'],
                            shade: 0.4,
                            title: "编辑",
                            content: $('#editForm'),
                            success: function () {
                            $.ajax({
                                url: remoteUrl+"department",
                                type: 'get',//method请求方式，get或者post
                                dataType: 'json',//预期服务器返回的数据类型
                                contentType: "application/json; charset=utf-8",
                                success: function (res) {//res为相应体,function为回调函数
                                    var result = res.result;
                                    var i = 0;
                                    var op = "";
                                    $('#L_department').html("");
                                    $('#L_department').append('<option value="">请选择</option>');
                                    for (i; i < result.length; i++) {
                                        op += '<option value=' + result[i].id + '>' + result[i].name + '</option>';
                                    }
                                    $('#L_department').append(op);
                                    $('#L_department').find('option[value=' + data.gradeClass.department.id + ']').attr("selected", true);
                                    form.render();

                                }
                            });
                            $.ajax({
                                url: remoteUrl+"grade_class/getbyDepartment/" + data.gradeClass.department.id,
                                type: 'get',//method请求方式，get或者post
                                dataType: 'json',//预期服务器返回的数据类型
                                contentType: "application/json; charset=utf-8",
                                async: false,
                                success: function (res) {//res为相应体,function为回调函数
                                    var result = res.result;
                                    var i = 0;
                                    var op = "";
                                    $('#L_gradeclass').html("");
                                    for (i; i < result.length; i++) {
                                        op += '<option value=' + result[i].id + '>' + result[i].name + '</option>';
                                    }
                                    $('#L_gradeclass').append(op);
                                    $('#L_gradeclass').find('option[value=' + data.gradeClass.id + ']').attr("selected", true);
                                    form.render();
                                }
                            });
                            form.on('select(department)', function (data) {
                                $.ajax({
                                    url: remoteUrl+"grade_class/getbyDepartment/" + data.value,
                                    type: 'get',//method请求方式，get或者post
                                    dataType: 'json',//预期服务器返回的数据类型
                                    contentType: "application/json; charset=utf-8",
                                    async: false,
                                    success: function (res) {//res为相应体,function为回调函数
                                        var result = res.result;
                                        var i = 0;
                                        var op = "";
                                        $('#L_gradeclass').html("");
                                        for (i; i < result.length; i++) {
                                            op += '<option value=' + result[i].id + '>' + result[i].name + '</option>';
                                        }
                                        $('#L_gradeclass').append(op);
                                        form.render();
                                    }
                                });

                            });
                            $('#L_userno').val(data.user.number);
                            $('#L_age').val(data.age);
                            $('#L_identity').val(data.idNumber);
                            $('#L_email').val(data.email);
                            $('#L_phone').val(data.phoneNumber);
                            $('#L_username').val(data.user.username);
                            $('#L_birthday').val(data.birthday);
                            $('#L_sex').val(data.sex);


                            //监听提交
                            form.on('submit(update)', function (dataField) {

                                var datas = dataField.field;

                                var data1 = {
                                    "id": data.id,
                                    "user":
                                        {
                                            "id": data.user.id,
                                            "number": $('#L_number').val(),
                                            "username": $('#L_username').val(),
                                            "password": $('#L_pass').val()
                                        },
                                    "age": $('#L_age').val(),
                                    "sex": $("input[type='radio']:checked").val(),
                                    "birthday": $('#date').val(),
                                    "phone": $('#L_phone').val(),
                                    "email": $('#L_email').val(),
                                    'idNumber': $('#L_identity').val(),
                                    'birthday': $('#date').val(),
                                    "gradeClass": {
                                        "id": datas.grade_class,
                                        "department": {
                                            "id": datas.department
                                        }
                                    }

                                };

                                $.ajax({
                                    url: remoteUrl+"teacher/update",
                                    type: 'put',//method请求方式，get或者post
                                    dataType: 'json',//预期服务器返回的数据类型
                                    contentType: "application/json; charset=utf-8",
                                    data: JSON.stringify(data1),
                                    success: function (res) {//res为相应体,function为回调函数
                                        if (res.status === 200) {
                                            layer.alert("增加成功", {
                                                icon: 6
                                            }, function () {
                                                // 获得frame索引
                                                var index = parent.layer.getFrameIndex(window.name);
                                                //关闭当前frame
                                                parent.layer.close(index);
                                            });
                                        } else {
                                            layer.msg(res.message);
                                        }


                                    }
                                });


                            });


                        }
                    })
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

