<template>
    <div>
        <b-navbar v-if="profile">
            <template slot="brand">
                <b-navbar-item tag="router-link" :to="{ path: '/usr' }">
                    <i class="fas fa-paper-plane" size="is-large" alt="Simple social media network"></i>
                </b-navbar-item>
            </template>

            <template slot="start">
                <b-navbar-item @click="showProfile" :disabled="$route.path === '/usr'">
                    Домой
                </b-navbar-item>
            </template>

            <template slot="end">
                <b-navbar-item tag="div">
                    <div v-if="!profile">
                        <div class="buttons">
                            <a class="button is-primary" href="/login">
                                Войти
                            </a>
                        </div>
                    </div>
                    <div v-else>
                        <b-navbar-dropdown :label="profile.name">
                            <b-navbar-item @click="showProfile" :disabled="$route.path === '/usr'">
                                Моя страница
                            </b-navbar-item>
                            <hr class="navbar-divider">
                            <b-navbar-item href="/logout">
                                Выйти
                            </b-navbar-item>
                        </b-navbar-dropdown>
                    </div>
                </b-navbar-item>
            </template>
        </b-navbar>
        <div v-if="!profile">
            <div class="container">
                <div class="header">
                    <div class="logo"></div>
                </div>
                <h1>InConnect</h1>
                <h1>Один аккаунт. Доступ к различным функциям.</h1>
                <h2>Войдите, чтобы пользоваться данной социальной сетью</h2>
                <div class="has-text-centered">
                    <b-button tag="a" size="is-large" href="/login" icon-left="google" padd>Войти с помощью Google</b-button>
                </div>
            </div>
            <div class="container">
                <h1></h1>
            </div>
        </div>
        <div class="columns is-multiline is-mobile" v-if="profile">
            <div class="column is-narrow"></div>
            <div class="column is-narrow"></div>
            <div class="column is-narrow"></div>
            <div class="column is-narrow">
                <b-menu>
                    <b-menu-list label=""><b-menu-item label=""></b-menu-item></b-menu-list>
                    <b-menu-list label="InConnect">
                        <b-menu-item icon="account" label="Профиль" :disabled="$route.path === '/usr'" @click="showProfile"></b-menu-item>
                        <b-menu-item icon="forum" label="Сообщения" :disabled="$route.path === '/im'" @click="showMessages"></b-menu-item>
                    </b-menu-list>
                    <b-menu-list label="">
                        <b-menu-item label="Выйти" href="/logout"></b-menu-item>
                    </b-menu-list>
                </b-menu>
            </div>
            <div class="column is-1">
            </div>
            <div class="column is-7">
                <section class="section">
                    <div class="container">
                        <router-view></router-view>
                    </div>
                </section>
            </div>

        </div>
    </div>
</template>

<script>
    import { mapState, mapMutations } from 'vuex'
    import { addHandler } from "../util/socks"


    export default {
        computed: mapState(['profile']),
        methods: {
            ...mapMutations([
                'addMessageMutation',
                'updateMessageMutation',
                'removeMessageMutation'
            ]),
            showMessages(){
                this.$router.push('/im')
            },
            showProfile() {
                this.$router.push('/usr')
            }
        },
        created() {
            addHandler(data => {
                if (data.objectType === "MSG") {
                    switch (data.eventType) {
                        case 'CREATE':
                            this.addMessageMutation(data.body)
                            break
                        case 'UPDATE':
                            this.updateMessageMutation(data.body)
                            break
                        case 'REMOVE':
                            this.removeMessageMutation(data.body)
                            break
                        default:
                            console.error(`Looks like the event type if unknown "${data.eventType}"`)
                    }
                } else {
                    console.error(`Looks like the object type if unknown "${data.objectType}"`)
                }
            })
        }
    }
</script>

<style scoped>
    @font-face {
        font-family: 'Open Sans';
        font-style: normal;
        font-weight: 200;
        src: local('Open Sans Light'), local('OpenSans-Light'), url(//fonts.gstatic.com/s/opensans/v13/DXI1ORHCpsQm3Vp6mXoaTegdm0LZdjqr5-oayXSOefg.woff2) format('woff2');
    }

    body {
        background-color: #fff;
        margin: 0px;
        height: 100%;
        overflow: auto;
        font-size: 0;
    }

    h1 {
        font-family: 'Open Sans', arial;
        -webkit-font-smoothing: antialiased;
        color: #555;
        font-size: 42px;
        font-weight: 300;
        margin-top: 0;
        margin-bottom: 20px;
        text-align: center;
    }

    h2 {
        font-family: 'Open Sans', arial;
        -webkit-font-smoothing: antialiased;
        color: #555;
        font-size: 18px;
        font-weight: 400;
        margin-bottom: 20px;
        text-align: center;
    }

    .header {
        height: 350px;
        width: 100%;
        text-align: center;
        overflow: hidden;
    }

    .header .logo {
        background-image: url(https://ssl.gstatic.com/images/branding/googlelogo/1x/googlelogo_color_112x36dp.png);
        background-size: 112px 36px;
        background-repeat: no-repeat;
        float: none;
        margin: 250px auto 30px;
        display: block;
        height: 300px;
        width: 116px;
    }
</style>