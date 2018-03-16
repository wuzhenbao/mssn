var  zkInterval;
$(function(){
	zkInterval = setInterval(refreshZKDir,1000);
});

var time = 200;
/**
 * 创建ZKnode
 * @returns
 */
function createZKnode(){
	var okcall = function(){
		var zkNodePath = $("#nodePath").val();
		if(!zkNodePath || zkNodePath.trim().length == 0){
			alert("请输入创建节点的名称");
			return;
		}
		zkNodePath = formatPaht(zkNodePath);
		var zkNodeData = $("#nodeDate").val();
		var zkNodeVO = {
				path : 	zkNodePath,
				desc : zkNodeData
		};
		var params = {zkNodeVO:zkNodeVO};
		var url = "/mssn/service/zk/zkService/createZKNode?path="+zkNodePath+"&desc="+zkNodeData;
		$.ajax({
			url : url,
			type : "POST",
			//data : params,
			dataType : "json",
			success : function(data) {
				alert(data[0].result);
				$("#commonDialog").dialog( "close" );
				$('#zktree').tree();
			},
			error:function(err){
				alert(err.errormsg);
				$("#commonDialog").dialog( "close" );
			}
		});
		
	};
	var closecall = function(){
		$("#commonDialog").dialog( "close" );
	};
	var contentId = "createZKNodeDiv";
	openCommonDialog(contentId,okcall,closecall);
}
/**
 * 删除zk节点
 * @returns
 */
function deleteZKnode(){
	var node = $('#zktree').tree('getSelected');
	if(node && !node.root){
		var path = formatPaht((node.parent=="/"?"":node.parent)+"/"+node.text);
		var url = "/mssn/service/zk/zkService/deleteZKNode/"+path;
		$.ajax({
			url : url,
			type : "GET",
			//data : params,
			dataType : "json",
			success : function(data) {
				alert(data[0].result);
				$('#zktree').tree();
			},
			error:function(err){
				alert(err.responseText);
			}
		});
	}else{
		alert("请选择要删除的节点！");
	}
}
//刷新目录
function refreshZKDir(){
	$("#refreshTime").val(time);
	if(time > 0){
		time--;
	}else{
		$('#zktree').tree();
		time = 200;
	}
	
}
//收缩所有目录
function collapseAll(){
	$('#zktree').tree('collapseAll');
}
//展开所有目录
function expandAll(){
	$('#zktree').tree('expandAll');
}
/**
 * 目录加载完的事件
 */
function loadSuccess(){
	$('#zktree').tree('collapseAll');
}
//选择
function expandTo(){
	var node = $('#zktree').tree('find','/wuzhenbao');
	$('#zktree').tree('expandTo', node.target).tree('select', node.target);
}
/**
 * zk目录树加载出错时，把定时器停止掉。
 * @returns
 */
function onZKLoadError(){
	clearInterval(zkInterval);
	alert("查询ZK目录树出错了！");
}
/**
 * 显示节点数据
 */
function showZKData(){
	var node = $('#zktree').tree('getSelected');
	if(node && !node.root){
		var path = formatPaht(node.parent =="/"?"/"+node.text:node.parent+"/"+node.text);
		var url = "/mssn/service/zk/zkService/showZKData/"+path;
		$.ajax({
			url : url,
			type : "GET",
			//data : parm,
			dataType : "json",
			success : function(data) {
				$("#zkNodeData").val(JSON.stringify(data));
			},
			error:function(err){
				alert(err.errormsg);
			}
		});
	}else{
		alert("请选择需要显示数据的节点");
	}
}
