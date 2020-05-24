import Vue from 'vue'
import VueRouter from 'vue-router'
import MessagesList from "pages/MessageList.vue"
import Profile from "pages/Profile.vue"
Vue.use(VueRouter)

const routes = [
    { path: '/', component: Profile},
    { path: '/im', component: MessagesList },
    { path: '/profile', component: Profile},
    { path: '*', component: Profile}
]

export default new VueRouter({
    mode: 'history',
    routes
})