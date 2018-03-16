
//刷新目录
function refreshHDFSDir(){}
/**
 * 加载事件
 */
$(function(){
	$("#hiveGrid").datagrid({data:getHiveData()});
});
/**
 * 根据页面上hive的表名称，查询该表的数据显示到grid
 * @returns
 */
function queryByTableName(){
	var node = $('#hiveTree').tree('getSelected');
	if (node && node.text && node.text.trim().length > 1) {
		var tableName = node.text;
		$.ajax({
			url : "/mssn/service/hive/hiveService/queryHiveData/"+formatPaht(tableName),
			type : "GET",
			dataType : "json",
			sync : false,
			success : function(result) {
				var dataGrid = $("#hiveGrid").datagrid({data:getHiveData(result)});
			},
			error:function(err){
				alert(err);
			}
		});
	} else {
		alert("请选择hive数据表！");
	}
}
/**
 * 从后台加载hive表数据后，到前台处理数据，生成grid表数据
 * @param data
 * @returns
 */
function getHiveData(data){
	if(data){
		var rows = [];
		for (var i = 0; i < data.length; i++) {
			rows.push({
				column1 : data[i].column1,
				column2 : data[i].column2,
				column3 : data[i].column3,
				column4 : data[i].column4,
				column5 : data[i].column5,
				column6 : data[i].column6,
				column7 : data[i].column7
			});
		}
		return rows;
	}
}

//展开所有目录
function expandAll(){
	$('#hiveTree').tree('expandAll');
}
//选择
function expandTo(){
	var node = $('#hiveTree').tree('find','/wuzhenbao');
	$('#hiveTree').tree('expandTo', node.target).tree('select', node.target);
}
/**
 * 点击hive表名称的事件，触发加载表数据
 * @param node
 * @returns
 */
function treeClick(node){
	queryByTableName();
}
function openHdfsDir(){
	$("#selectFilelocation").dialog("open");
	$("#hdfsDirDiv").show();
	$("#hdfsDirDiv").insertAfter($("#selectFilelocationSpan"));
	//$("#selectFilelocation").html(html);
}
/**
 * 点击创建外部表按钮，弹出选择hdfs目录的弹出框，选择对应的目录创建hive外部表
 * @returns
 */
function createExternalTable(fileLocatoion) {
	alert(fileLocatoion);
}
function uploadFile(destDir) {}
function formatPaht(oldPath){
	return oldPath.replace(new RegExp("/", 'g'),"a90000009a");
}

// 上传文件
function deleteFolderOrFile(){}
/**
 * 显示表详情
 * @returns
 */
function describeTables(){
	var node = $('#hiveTree').tree('getSelected');
	if (node && node.text && node.text.trim().length > 1) {
		var tableName = node.text;
		$.ajax({
			url : "/mssn/service/hive/hiveService/queryHiveData/"+formatPaht(tableName),
			type : "GET",
			dataType : "json",
			sync : false,
			success : function(result) {
				alert(result);
			},
			error:function(err){
				alert(err);
			}
		});
	} else {
		alert("请选择hive数据表！");
	}
}
// 获取当前选中的节点
function getSelected(){}

//获取当前选中的节点
function createFolder(){}

/**
 * 目录加载完的事件
 */
function loadSuccess(){
	$('#hdfsDirTree').tree('collapseAll');
	$("#hdfsDirDiv").hide();
}
/**
 * 默认加载完页面执行
 */
$(function(){
	$( "#selectFilelocation" ).dialog({
		autoOpen: false,
		width: 600,
		height:500,
		buttons: [
			{
				text: "Ok",
				click: function() {
					var node = $('#hdfsDirTree').tree('getSelected');
					if (node){
						var fileLocatoion = node.parentPath+node.text;
						createExternalTable(fileLocatoion);
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
}); 
