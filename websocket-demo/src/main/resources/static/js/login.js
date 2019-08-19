var form = document.getElementById('loginForm');
function login() {
	form.setAttribute('method', 'get');
	form.submit();
}

function goToRegister() {
	form.setAttribute('method', 'post');
	form.submit();
}