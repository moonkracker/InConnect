<template>
    <div>
        <b-navbar>
            <template slot="brand">
                <b-navbar-item tag="router-link" :to="{ path: '/' }">
                    <i class="fas fa-paper-plane" size="is-large" alt="Simple social media network"></i>
                </b-navbar-item>
            </template>

            <template slot="start">
                <b-navbar-item href="/">
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
                            <b-navbar-item href="/logout">
                                Выйти
                            </b-navbar-item>
                        </b-navbar-dropdown>
                    </div>
                </b-navbar-item>
            </template>
        </b-navbar>
        <div class="container is-fluid ">
            <div v-if="!profile">
                <section class="hero is-warning">
                    <div class="hero-body">
                        <div class="container">
                            <h1 class="title">
                                Необходимо авторизоваться через Google
                            </h1>
                        </div>
                    </div>
                </section>
            </div>
            <div v-else>
                <messages-list :messages="messages" />
            </div>
        </div>
    </div>
</template>

<script>
    import MessagesList from "components/messages/MessageList.vue";
    import { addHandler } from "util/socks"
    import { getIndex } from "util/collections";

    export default {
        components:{
            MessagesList
        },
        data() {
            return {
                messages: frontendData.messages,
                profile: frontendData.profile
            }
        },
        created() {
            addHandler(data =>{
                let index = getIndex(this.messages, data.id)
                if (index > -1){
                    this.messages.splice(index,1,data)
                } else {
                    this.messages.push(data)
                }
            })
        }
    }
</script>

<style scoped>

</style>