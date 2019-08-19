/*
'use strict';
// strict 모드 사용. 자바스크립트가 묵인했던 에러들의 에러 메시지 발생(엄격한 문법 검사)

var setCookie = function(name, value) {
	var date = new Date();
	date.setTime(date.getTime() + 3*60*60*1000);
	document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
};
// 예시 : setCookie('favoriteColor', 'Blue');
// 설명 : favoriteColor=Blue, 3시간 뒤 만료됨 

var getCookie = function(name) {
	var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
	return value? value[2] : null;
};
// 예시 : getCookie('favoriteColor'); 
// 결과 : Blue 

var deleteCookie = function(name) {
	document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}
//예시 : deleteCookie('favoriteColor'); 
//결과 : Blue 삭제(만료 날짜를 1970년 1월 1일로 설정.)
*/