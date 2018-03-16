//校验选择
function checkSelect(){
	//选中计划文档所在的目录
	var node = $('#tt').tree('getSelected');
	if(node){
		if(node.root){
			alert("请选择正确的目录！");
			return false;
		}else{
			if(!node.dir){
				alert("请选择目录！");
				return false;
			}else{
				return true;
			}
		}
	}else{
		alert("未选中文件或目录！");
		return false;
	}
}

//数据去重
function dataDistinct(){
	if(checkSelect()){
		var url ="/mssn/service/hdfs/hdfsService/dataDistinct/";
		doMapreduce(url);
	}
}

function doMapreduce(url){
	//选中计算文档所在的目录
	var node = $('#tt').tree('getSelected');
	var inputPath = node.parentPath+node.text;
	if(confirm("确定要执行MapReduce计算?")){
		$.ajax({
			url : url+formatPaht(inputPath),
			type : "GET",
			dataType : "json",
			success : function(data) {
				alert(data.result);
				$('#tt').tree();
			},
			error:function(err){
				alert(err);
			}
		});
	}
}
//数据排序
function dataSort(){
	if(checkSelect()){
		var url ="/mssn/service/hdfs/hdfsService/dataSort/";
		doMapreduce(url);
	}
}
//求平均值
function dataAvg(){
	if(checkSelect()){
		var url ="/mssn/service/hdfs/hdfsService/dataAvg/";
		doMapreduce(url);
	}
}
//单表关联
function dataJoin(){
	if(checkSelect()){
		var url ="/mssn/service/hdfs/hdfsService/dataJoin/";
		doMapreduce(url);
	}
}
//多表关联
function dataJoinMult(){
	if(checkSelect()){
		var url ="/mssn/service/hdfs/hdfsService/dataJoinMult/";
		doMapreduce(url);
	}
}
//倒排索引
function dataSortDesc(){
	if(checkSelect()){
		var url ="/mssn/service/hdfs/hdfsService/dataSortDesc/";
		doMapreduce(url);
	}
}