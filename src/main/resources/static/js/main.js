import Vue from 'vue'
import App from 'pages/App.vue'
import VueResource from 'vue-resource'
import Buefy from 'buefy'
import { connect } from 'util/socks'
import 'buefy/dist/buefy.css'

if(frontendData.profile) {
    connect()
}

Vue.use(VueResource)
Vue.use(Buefy)

new Vue({
    el: '#app',
    render: a => a(App)
})