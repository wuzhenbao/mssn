var okcallback;//弹出框默认的OK按钮事件
var closecallback;//弹出框默认的Close按钮事件
var commonDialog = {};
commonDialog.okcallback = function(){
	$("#commonDialog").dialog( "close" );
};
commonDialog.closecallback = function(){
	$("#commonDialog").dialog( "close" );
};
/**
 * 默认加载
 * @returns
 */
$(function(){
	commonDialog.dialog = $( "#commonDialog" ).dialog({
		autoOpen: false,
		width: 600,
		height:500,
		buttons: [
			{
				text: "Ok",
				click: commonDialog.okcallback
			},
			{
				text: "Cancel",
				click: commonDialog.closecallback
			}
		]
	});
});
function openCommonDialog(contentId,okcall,closecall,title){
	var dialog = $("#commonDialog").dialog("open");
	$(".ui-dialog-buttonset").find("button").each(function(i,n){
		if($(n).html() == "Ok"){
			$(n).unbind("click").one("click",okcall);
		}
		if($(n).html() == "Cancel"){
			$(n).unbind("click").one("click",closecall);
		}
	});
	if(contentId){
		$("#"+contentId).show();
		$("#"+contentId).insertAfter($("#commonDialogSpan"));
	}
	
}
/**
 * 
 * 跳转到jquery的示例页面
 */
function toViewJquery() {
	var urlData = "redirectUrl=jquery-ui-1.12.1/index.html";
	toLoadUrl(urlData);
}
function toViewHadoop(){
	var urlData = "redirectUrl=hadoop/hadoopindex.html";
	toLoadUrl(urlData);
}
function toViewJqueryTree() {
	var urlData = "redirectUrl=hadoop/uploadToHDFS.html";
	toLoadUrl(urlData);
}
function toViewHive() {
	var urlData = "redirectUrl=hadoop/hive/hive.html";
	toLoadUrl(urlData);
}
function toViewZK() {
	var urlData = "redirectUrl=hadoop/zk/zkview.html";
	toLoadUrl(urlData);
}
function formatPaht(oldPath){
	return oldPath.replace(new RegExp("/", 'g'),"a90000009a");
}
function toLoadUrl(urlData) {
	var time = new Date();
	$.ajax({
		type : "POST",
		dataType : "html",
		url : "/mssn/redirectUrl",
		data : urlData+"&time="+time.getTime(),
		success : function(msg) {
			//alert(msg);
			$("#body_right").html("");
			$("#body_right").html(msg);
		},
		error : function(data) {
			alert("出错了：" + data);
		}
	});
}