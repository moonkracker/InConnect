import Vue from 'vue'
import Buefy from 'buefy'
import '@babel/polyfill'
import 'api/resource'
import router from "router/router";
import App from 'pages/App.vue'
import store from "store/store"
import { connect } from 'util/socks'
import 'buefy/dist/buefy.css'
import GoTop from '@inotom/vue-go-top';

if(frontendData.profile) {
    connect()
}

Vue.use(Buefy)
Vue.use(GoTop)

new Vue({
    el: '#app',
    store,
    router,
    render: a => a(App)
})