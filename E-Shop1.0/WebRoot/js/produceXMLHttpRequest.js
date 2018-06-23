/**
 * 获得XMLHttpRequest的实例
 */
var xmlHttpRequest;

function createXMLHttpRequest() {
	
	try {
		xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
	}
	catch (e1) {
		try {
			xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch (e2) {
			xmlHttpRequest = false;
		}
	}
	if (!xmlHttpRequest && typeof XMLHttpRequest != "undefined") {
		xmlHttpRequest = new XMLHttpRequest();
	}
}