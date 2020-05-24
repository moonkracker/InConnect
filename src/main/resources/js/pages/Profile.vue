<template>
        <div class="tile is-ancestor">
            <div class="tile is-vertical is-12">
                <div class="tile">
                    <div class="tile is-parent is-vertical">
                        <article class="tile is-child notification is-primary">
                            <p class="subtitle">Имя пользователя: </p>
                            <p class="title">{{profile.name}}</p>
                        </article>
                        <article class="tile is-child notification is-warning">
                            <p class="title">Данные о пользователе:</p>
                            <div class="content">
                                <p>E-mail: {{profile.email}}</p>
                                Родной язык: <strong>{{profile.locale}}</strong>
                            </div>
                        </article>
                    </div>
                    <div class="tile is-parent">
                        <article class="tile is-child notification is-info">
                            <p class="title">Фото профиля</p>
                            <figure class="image is-full">
                                <img :src="profile.userpic">
                            </figure>
                        </article>
                    </div>
                </div>
                <div class="tile is-parent">
                    <article class="tile is-child notification is-danger">
                        <div class="content">
                            <p class="subtitle">Последний визит: {{profile.lastVisit}}</p>
                        </div>
                    </article>
                </div>
            </div>
        </div>
</template>

<script>
    import { mapState } from 'vuex'
    export default {
        name: 'Profile',
        computed: mapState(['profile'])
    }
</script>

<style scoped>

</style>