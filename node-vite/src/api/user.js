import request from '../utils/request'

/**
 * User login
 */
let login = function (username, password) {
    let fromData = new FormData()
    fromData.append('username', username)
    fromData.append('password', password)
    return request.post('/login', fromData).then(response => {
        return response.data
    })
}

export let user = {
    login,
}
