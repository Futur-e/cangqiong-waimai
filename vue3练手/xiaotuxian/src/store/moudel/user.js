export default {
    namespace:true,
    state(){
        return{
            profile:'zz'
        }
    },
    mutations:{
        setUser(state,payload){
            state.profile += payload
        }
    }
}
