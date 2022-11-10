export const setToken=(token)=>{
    return localStorage.setItem('user_token',token)
}
export const getToken=(token)=>{
    localStorage.getItem('user_token',token)
}
export const removeToken=()=>{
    return localStorage.removeItem('user_token')
}