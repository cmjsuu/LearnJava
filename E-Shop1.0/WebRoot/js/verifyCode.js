/**
 * 对验证码进行验证！
 */

// 获得验证后提示信息的显示框
var msgBox;
// 验证结果
var verifyResult = false;

// 聚焦 到当前框
function VerifyOnItem(obj) {
	obj.parentNode.parentNode.className = "current";
	// 获得当前提示框
	msgBox = obj.parentNode.getElementsByTagName("span")[0];
	// 写在提示框的内容引用
	msgBox.innerHTML = "";
	// 提示框的样式引用
	msgBox.className = "";
}

// 对验证码进行验证
function VerifyOutItem(obj) {
	var xmlHttpRequest; // 得到XmlHttpRequest对象
	if (window.XMLHttpRequest) {
		xmlHttpRequest = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if (!xmlHttpRequest && typeof XMLHttpRequest != "undefined") {
		xmlHttpRequest = new XMLHttpRequest();
	}
	if (obj.value == "") {
		msgBox.innerHTML = "验证码不能为空";
		msgBox.className = "error";
		return false;
	}
	xmlHttpRequest.onreadystatechange = function() {
		if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
			var msgCode = xmlHttpRequest.responseText;
			if (msgCode == "true") {
				msgBox.innerHTML = "<font color='green'>*验证码正确*</font>";
				msgBox.className = "error";
				verifyResult = true;
				return true;
			} else if (msgCode == "false") {
				msgBox.innerHTML = "<font color='red'>*验证码有误，請重新輸入...*</font>";
				msgBox.className = "error";
				return false;
			} else {
				msgBox.innerHTML = "<font color='yellow'>*系統繁忙，請稍後再試...*</font>";
				msgBox.className = "error";
				return false;
			}
		}
	};
	xmlHttpRequest.open("POST", "certPicCheck.userForeground", true);
	xmlHttpRequest.setRequestHeader("content-type",
			"application/x-www-form-urlencoded");
	xmlHttpRequest.send("veryCode="+obj.value);
	
}




