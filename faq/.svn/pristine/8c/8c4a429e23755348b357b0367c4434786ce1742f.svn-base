/**
 * 检查用户的输入，返回消息数组
 */
function checkUserInput(array) {
	var msg = [];
	for (var i = 0; i < array.length; i++) {
		var obj = array[i];
		var content = $(obj.id).val();
		if (content == null || (obj.minLen && content.length < obj.minLen)) {
			msg.push(obj.name + "长度不符合要求!");
		}
		if (obj.maxLen && content.length > obj.maxLen) {
			msg.push(obj.name + "长度超过要求!");
		}
	}
	return msg;
}