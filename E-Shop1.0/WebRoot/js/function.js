window.onload = function() {
	showChater();
	scrollChater();
};
window.onscroll = scrollChater;
window.onresize = scrollChater;

function FocusItem(obj) {
	obj.parentNode.parentNode.className = "current";
	var msgBox = obj.parentNode.getElementsByTagName("span")[0];
	msgBox.innerHTML = "";
	msgBox.className = "";
}
/**
 * 動態生成註冊頁面的出生日期的日
 * 
 * @param obj
 */
var year = 0;
var month = 0;

// alert(year);
function SelectYear(obj) {

	if (obj.name = "year") {
		year = obj.value;

	}
	alert(year);
	if (obj.name = "month") {
		month = obj.value;
	}
	// alert(year);
	if (year % 4 == 0 && year % 100 != 0 && month != 2) {
		alert(3);
		for ( var i = 1; i < 30; i++) {
			alert(1);
			document.regForm.day.opptions.add(new Option(i, i));
			alert(2);
		}
	}
}

function SelectMonth(obj) {
	var month = 0;
	if (obj.name = "month") {
		month = obj.value;
	}
	alert(month);
	if (year % 4 == 0 && year % 100 != 0 && month != 2) {
		for ( var i = 1; i < 30; i++) {
			document.regForm.day.opptions.add(new Option(i, i));
		}
	}
}
function SelectDay(obj) {

	var month = 0;
	if (obj.name = "month") {
		month = obj.value;
	}
	alert(month);
	if (year % 4 == 0 && year % 100 != 0 && month != 2) {
		for ( var i = 1; i < 30; i++) {
			document.regForm.day.opptions.add(new Option(i, i));
		}
	}
}

/**
 * 用戶信息驗證
 * 
 * @param obj
 * @returns {Boolean}
 */
function CheckItem(obj) {
	var xmlHttpRequest;
	obj.parentNode.parentNode.className = "";
	var msgBox = obj.parentNode.getElementsByTagName("span")[0];
	var name = document.getElementById("userName").value;
	var inner = document.getElementById("userName").parentNode
			.getElementsByTagName("span")[0];
	switch (obj.name) {
	case "userName":
		if (obj.value == "") {
			inner.innerHTML = "用户名不能为空";
			inner.className = "error";
			return false;
		}
		if (Window.XMLHttpRequest) {
			xmlHttpRequest = new XMLHttpRequest();
		} else if (Window.ActiveXObject) {
			xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
		}
		if (!xmlHttpRequest && typeof XMLHttpRequest != "undefined") {
			xmlHttpRequest = new XMLHttpRequest();
		}
		xmlHttpRequest.open("POST", "userCheck", true);
		xmlHttpRequest.setRequestHeader("content-type",
				"application/x-www-form-urlencoded");
		xmlHttpRequest.onreadystatechange = function() {
			if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
				var msg = xmlHttpRequest.responseText;
				if (msg == "true") {
					inner.innerHTML = "<font color='green'>*該用戶名可以使用*</font>";
					inner.className = "error";
					return false;
				} else if (msg == "false") {
					inner.innerHTML = "<font color='red'>*該用戶名重複了，請重新輸入...*</font>";
					inner.className = "error";
					return false;
				} else {
					inner.innerHTML = "<font color='yellow'>*系統繁忙，請稍後再試...*</font>";
					inner.className = "error";
					return false;
				}
			}
		};
		xmlHttpRequest.send("userName=" + name);

	case "passWord":
		if (obj.value == "") {
			msgBox.innerHTML = "密码不能为空";
			msgBox.className = "error";
			return false;
		}
		if (obj.value.length < 6) {
			msgBox.innerHTML = "密码长度不能小于六";
			msgBox.className = "error";
			return false;
		}
		break;
	case "rePassWord":
		if (obj.value == "") {
			msgBox.innerHTML = "确认密码不能为空";
			msgBox.className = "error";
			return false;
		} else if (obj.value != document.getElementById("passWord").value) {
			msgBox.innerHTML = "两次输入的密码不相同";
			msgBox.className = "error";
			return false;
		}
		break;
	case "rePassWord":
		if (obj.value == "") {
			msgBox.innerHTML = "确认密码不能为空";
			msgBox.className = "error";
			return false;
		} else if (obj.value != document.getElementById("passWord").value) {
			msgBox.innerHTML = "两次输入的密码不相同";
			msgBox.className = "error";
			return false;
		}
		break;
		//印证码的操作
	case "certCode":
		if (obj.value == "") {
			msgBox.innerHTML = "印证码必須填";
			msgBox.className = "error";
			return false;
		}
		if (window.XMLHttpRequest) {
			xmlHttpRequest = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
		}
		if (!xmlHttpRequest && typeof XMLHttpRequest != "undefined") {
			xmlHttpRequest = new XMLHttpRequest();
		}
		//这里是向CheckCertPicServlet发出请求返回true/false ！！这里可能路径有点问题到时候可以讨论
		xmlHttpRequest.open("post", "checkCertPic", true);
		xmlHttpRequest.setRequestHeader("content-type","application/x-www-form-urlencoded");
		
		xmlHttpRequest.onreadystatechange = function() {
			if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
				var msg = xmlHttpRequest.responseText;
				alert(msg);
				if (msg == "true") {
					msgBox.innerHTML = "<br></br><font color='green'>*正确 *</font>";
					msgBox.className = "error";
					return true;
				} else if (msg == "false") {
					msgBox.innerHTML = "<font color='red'>*填写错误，請重新填写...*</font>";
					msgBox.className = "error";
					return false;
				} else {
					msgBox.innerHTML = "<font color='yellow'>*系統繁忙，請稍後再試...*</font>";
					msgBox.className = "error";
					return false;
				}
			}
		};
		xmlHttpRequest.send("certCode=" + certCode);
		break;
		
	case "sex":
		if (obj.value == "") {
			msgBox.innerHTML = "性别不能为空";
			msgBox.className = "error";
			return false;
		}
		break;
	case "birthday":
		if (obj.value == "") {
			msgBox.innerHTML = "出生日期不能为空";
			msgBox.className = "error";
			return false;
		} else if (onj.value) {

		}

		break;
	case "address":
		if (obj.value == "") {
			msgBox.innerHTML = "地址不能为空";
			msgBox.className = "error";
			return false;
		}
		break;
	case "connection":
		if (obj.value == "") {
			msgBox.innerHTML = "联系方式不能为空";
			msgBox.className = "error";
			return false;
		} else {
			var number = obj.value.length;
			for ( var i = 0; i < number; i++) {
				var index = obj.value.charAt(i);
				if (index >= 0 && index < 10) {

				} else {
					msgBox.innerHTML = "联系方式不能為非數字";
					msgBox.className = "error";
					return false;
				}
			}
		}
		break;
	case "email":
		if (obj.value == "") {
			msgBox.innerHTML = "电子邮箱不能为空";
			msgBox.className = "error";
			return false;
		} else if ((obj.value.indexOf("@") != -1)
				&& (obj.value.indexOf(".") != -1)
				&& (obj.value.indexOf(".") != 0)
				&& (obj.value.indexOf("@") != null)
				&& (obj.value.indexOf(".") != null)
				&& (obj.value.indexOf("@") != 0)
				&& (obj.value.indexOf("@") == obj.value.lastIndexOf("@"))
				&& (obj.value.lastIndexOf("@") != obj.value.length - 1)
				&& (obj.value.indexOf(".") == obj.value.lastIndexOf("."))
				&& (obj.value.lastIndexOf(".") != obj.value.length - 1)) {
			// alert(obj.value.indexOf("@"));
		} else {
			msgBox.innerHTML = "电子邮箱格式不正确";
			msgBox.className = "error";
			return false;
		}
		break;
	}
	return true;
}

function checkForm(frm) {
	var els = frm.getElementsByTagName("input");
	for ( var i = 0; i < els.length; i++) {
		if (typeof (els[i].getAttribute("onblur")) == "function") {
			if (!CheckItem(els[i])){
				return false;
			}
			
		}
	}
	return true;
}

function showChater() {
	var _chater = document.createElement("div");
	_chater.setAttribute("id", "chater");
	var _dl = document.createElement("dl");
	var _dt = document.createElement("dt");
	var _dd = document.createElement("dd");
	var _a = document.createElement("a");
	_a.setAttribute("href", "chaterRoom");
	_a.onclick = openRoom;
	_a.appendChild(document.createTextNode("在线聊天"));
	_dd.appendChild(_a);
	_dl.appendChild(_dt);
	_dl.appendChild(_dd);
	_chater.appendChild(_dl);
	document.body.appendChild(_chater);
}

function openRoom() {
	window.open("chat-room.html", "chater",
			"status=0,scrollbars=0,menubar=0,location=0,width=600,height=400");
}

function scrollChater() {
	var chater = document.getElementById("chater");
	var scrollTop = document.documentElement.scrollTop;
	var scrollLeft = document.documentElement.scrollLeft;
	chater.style.left = scrollLeft + document.documentElement.clientWidth - 92
			+ "px";
	chater.style.top = scrollTop + document.documentElement.clientHeight - 25
			+ "px";
}

function inArray(array, str) {
	for (a in array) {
		if (array[a] == str)
			return true;
	}
	return false;
}

function setCookie(name, value) {
	var Days = 30;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + escape(value) + ";expires="
			+ exp.toGMTString();
}

function getCookie(name) {
	var arr = document.cookie
			.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
	if (arr != null)
		return unescape(arr[2]);
	return null;
}

function delCookie(name) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = getCookie(name);
	if (cval != null)
		document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}

function goBuy(id, price) {
	var newCookie = "";
	var oldCookie = getCookie("product");
	if (oldCookie) {
		if (inArray(oldCookie.split(","), id)) {
			newCookie = oldCookie;
		} else {
			newCookie = id + "," + oldCookie;
		}
	} else {
		newCookie = id;
	}
	setCookie("product", newCookie);
	location.href = "shopping.html";
}

function delShopping(id) {
	var tr = document.getElementById("product_id_" + id);
	var oldCookie = getCookie("product");
	if (oldCookie) {
		var oldCookieArr = oldCookie.split(",");
		var newCookieArr = new Array();
		for (c in oldCookieArr) {
			var cookie = parseInt(oldCookieArr[c]);
			if (cookie != id)
				newCookieArr.push(cookie);
		}
		var newCookie = newCookieArr.join(",");
		setCookie("product", newCookie);
	}
	if (tr)
		tr.parentNode.removeChild(tr);
}

function reloadPrice(id, status) {
	var price = document.getElementById("price_id_" + id).getElementsByTagName(
			"input")[0].value;
	var priceBox = document.getElementById("price_id_" + id)
			.getElementsByTagName("span")[0];
	var number = document.getElementById("number_id_" + id);
	if (status) {
		number.value++;
	} else {
		if (number.value == 1) {
			return false;
		} else {
			number.value--;
		}
	}
	priceBox.innerHTML = "￥" + price * number.value;
}



