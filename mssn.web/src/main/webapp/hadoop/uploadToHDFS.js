
//刷新目录
function refreshHDFSDir(){
	$('#tt').tree();
}
//收缩所有目录
function collapseAll(){
	$('#tt').tree('collapseAll');
}
//展开所有目录
function expandAll(){
	$('#tt').tree('expandAll');
}
//选择
function expandTo(){
	var node = $('#tt').tree('find','/wuzhenbao');
	$('#tt').tree('expandTo', node.target).tree('select', node.target);
}



// 上传文件
function uploadFileDialog() {
	// 获取当前选择的路径，如果没有选中则弹出提示
	var node = $('#tt').tree('getSelected');
	if (node && node.text && node.text.trim().length > 1) {
		// 判断当前选中的文件还是文件夹，如果是文件则弹出提示
		if (node.isDir) {
			// 弹出DIV选择上传的文件
			$("#uploadDiv").dialog("open");
		} else {
			$("#uploadDiv").dialog("open");
		}
	} else {
		alert("");
	}
}
function uploadFile(destDir) {
	formData = new FormData(document.getElementById("upform"));
	formData.append("destDir",destDir);
	formData.append("myfile1", myfile1.files[0]);
	formData.append("myfile2", myfile2.files[0]);
	formData.append("myfile3", myfile3.files[0]);
	$.ajax({
		contentType : "multipart/form-data",
		url : "/mssn/servlet/uploadServlet",
		type : "POST",
		data : formData,
		dataType : "text",
		processData : false, // 告诉jQuery不要去处理发送的数据
		contentType: false, // 告诉jQuery不要去设置Content-Type请求头
		success : function(data) {
			$('#tt').tree();
		},
		error:function(err){
			alert(err);
		}
	});
}


// 上传文件
function deleteFolderOrFile(){
	var node = $('#tt').tree('getSelected');
	if(node){
		if(node.root){
			alert("不能删除根目录！");
		}else{
			var deletePath = node.parentPath+node.text;
			if(confirm("确定要删除"+deletePath+"?")){
				$.ajax({
					url : "/mssn/service/hdfs/hdfsService/deletePath?deletePath="+formatPaht(deletePath),
					type : "POST",
					dataType : "json",
					success : function(data) {
						alert(data.result);
						$('#tt').tree();
					},
					error:function(err){
						alert(err);
					}
				});
			}else{
			}
		}
	}else{
		alert("未选中文件或目录！");
	}
}
// 上传文件
function downloadFile(){
	var node = $('#tt').tree('getSelected');
	if (node){
		if(node.dir){
			alert("目录不支持下载，请选择需要下载的文档！");
			return;
		}
		
		var filePath = node.parentPath+node.text;
		/*var ldform = document.ldform;
		ldform.filepath = filePath;
		ldform.submit();*/
		//定义一个form表单
		var form=$("<form>");
		form.attr("style","display:none");
		form.attr("target","");
		form.attr("method","post");
		form.attr("action","/mssn/servlet/downloadServlet");
		var input1=$("<input>");
		input1.attr("type","hidden");
		input1.attr("name","filePath");
		input1.attr("value",(filePath));
		//将表单放置在web中
		$("body").append(form);
		form.append(input1);
		//表单提交
		form.submit();
	}else{
		alert("请选择需要下载的文档！");
	}
}
// 获取当前选中的节点
function getSelected(){
	var node = $('#tt').tree('getSelected');
	if (node){
		var s = node.text;
		/*if (node.attributes){
			s += ","+node.attributes.p1+","+node.attributes.p2;
		}*/
		alert(s);
	}
}

//获取当前选中的节点
function createFolder(){
	var node = $('#tt').tree('getSelected');
	if (node){
		if(!node.dir){
			alert("新建目录的父目录必须是文件夹！");
			return;
		}
		$("#inputDriDiv").dialog("open");
	}else{
		alert("请选择新建目录的父目录！");
		return;
	}
}
/**
 * 目录加载完的事件
 */
function loadSuccess(){
	$('#tt').tree('collapseAll');
}
/**
 * 默认加载完页面执行
 */
$(function(){
	$( "#uploadDiv" ).dialog({
		autoOpen: false,
		width: 400,
		height:300,
		buttons: [
			{
				text: "Ok",
				click: function() {
					var node = $('#tt').tree('getSelected');
					if (node){
						var destDir = node.parentPath+node.text;
						uploadFile("destDir:"+destDir);
					}
					$( this ).dialog( "close" );
				}
			},
			{
				text: "Cancel",
				click: function() {
					$( this ).dialog( "close" );
				}
			}
		]
	});
	$( "#inputDriDiv" ).dialog({
		autoOpen: false,
		width: 400,
		height:200,
		buttons: [
			{
				text: "Ok",
				click: function() {
					var node = $('#tt').tree('getSelected');
					var parentPath = null;
					var newPath = $("#newDirPath").val();
					if(node.root){
						newPath = node.text + newPath;
					}else{
						newPath =node.parentPath + node.text + "/"+ newPath;
					}
					var parm =formatPaht(newPath);
					var url = "/mssn/service/hdfs/hdfsService/mkDir/"+parm;
					if(newPath){
						$.ajax({
							url : url,
							type : "GET",
							//data : parm,
							dataType : "json",
							success : function(data) {
								$('#tt').tree();
								$("#inputDriDiv").dialog("close");
							},
							error:function(err){
								alert(err.errormsg);
								$("#inputDriDiv").dialog("close");
							}
						});
					}else{
						alert("请输入新目录的名称！");
					}
				}
			},
			{
				text: "Cancel",
				click: function() {
					$( this ).dialog( "close" );
				}
			}
		]
	});
	
}); 
