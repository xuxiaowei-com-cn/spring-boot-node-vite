import axios from 'axios'

import cookies from /*! ./../helpers/cookies */ "axios/lib/helpers/cookies.js"

// create an axios instance
const service = axios.create({
    baseURL: import.meta.env.VITE_APP_BASE_API, // BASE URL
    xsrfCookieName: 'XSRF-TOKEN',
    xsrfHeaderName: 'X-XSRF-TOKEN',
    withCredentials: true, // 携带 Cookie
    timeout: 5000 // 请求超时
})

// request interceptor
service.interceptors.request.use(
    config => {

        config.headers['setHeaderName'] = cookies.read("setCookieName") + '233'

        return config
    },
    error => {

        console.error('请求异常', error)

        return Promise.reject(error)
    }
)

// response interceptor
service.interceptors.response.use(
    response => {
        const res = response.data

        // if (res.code !== '00000') {
        //
        //     return Promise.reject(new Error(res.message || 'Error'))
        // } else {
        //     return res
        // }

        return response
    },
    error => {

        console.error('响应异常', error)

        return Promise.reject(error)
    }
)

export default service
