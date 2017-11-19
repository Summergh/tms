// 必须导入jquery
// 必须导入jquery.validate.js
jQuery.validator.addMethod("ueditor", function(value, element, params) {
	var ue = eval(params);
	return ue.hasContents();
}, "必须填写");
/**
 * 验证英文字母
 */
jQuery.validator.addMethod("english", function(value, element) {
    var tel = /^[a-zA-Z]*$/;  
    return this.optional(element) || (tel.test(value));  
}, "请输入英文字符"); 

/**
 * 验证长度，是中英文混合的情况
 */
jQuery.validator.addMethod("mymaxlength", function(value, element, param) {
	var length = value.length;
	for (var i = 0; i < length; i++) {
		if (value.charCodeAt(i) > 127) {
			length++;
		}
	}
    return this.optional(element) || length <= param*2;  
}, "请输入不多于{0}汉字"); 

/**
 * 验证手机号码
 */
jQuery.validator.addMethod("mobile", function(value, element) {
    var tel = /^(13[0-9]|15[0-9]|18[0-9]|14[57]|17[0678])[0-9]{8}$/;  
    return this.optional(element) || (tel.test(value));
}, "手机格式不正确"); 

/**
 * 验证包含汉字
 */
jQuery.validator.addMethod("inchinese", function(value, element) {
	var tel = /.*[\u4e00-\u9fa5]+.*$/;
	return tel.test(value);
}, "必须包含汉字");

/**
 * 验证汉字
 */
jQuery.validator.addMethod("chinese", function(value, element) {
	var tel = /^[\u4e00-\u9fa5]+$/;
	return tel.test(value);
}, "请输入汉字");
jQuery.validator.addMethod("zip", function(value, element) {
	if(value.length==0){
		return true;
	}
	return /^[1-9][0-9]{5}$/.test(value);
}, "邮政编码格式不正确");
jQuery.validator.addMethod("qq", function(value, element) {
	if(value.length == 0){
		return true;
	}
	return /^[1-9]\d{4,12}$/.test(value);
}, "QQ格式不正确");

/*固话和手机号,    不支持前加086- */
jQuery.validator.addMethod("isPhoneMobile", function(value, element) {
	if(value.length==0){
		return true;
	}
	return  /^(0\d{2,3}(-)?)?\d{7,8}(-\d{3,5})?$/.test(value) || /^[4|8]?00(-)?\d{3}(-)?\d{4}$/.test(value) || /^(13[0-9]|15[0-9]|18[0-9]|14[57]|17[0678])[0-9]{8}$/.test(value);
}, "电话格式不正确");
jQuery.validator.addMethod("phone", function(value, element) {
	if(value.length == 0){
		return true;
	}
	return /^(0\d{2,3}(-)?)?\d{7,8}(-\d{3,5})?$/.test(value) || /^[4|8]?00(-)?\d{3}(-)?\d{4}$/.test(value);
}, "电话格式不正确");
jQuery.validator.addMethod("phone2", function(value, element) {
	if(value.length == 0){
		return true;
	}
	return /^([0-9]|[\-])+$/g.test(value);
}, "号码格式不正确");
jQuery.validator.addMethod("onlynumber", function(value, element) {
	if(value.length==0){
		return true;
	}
	return /^[0-9]*[0-9][0-9]*$/.test(value);
}, "只能输入数字");
jQuery.validator.addMethod("requiredselect", function(value,element) {
	if(value==""){
		return false;
	}
	return true;
}, "必选字段");