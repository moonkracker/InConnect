<template>
    <div class="box">
        <article class="media">
            <div class="media-left">
                <figure class="image is-64x64">
                    <img :src="message.author.userpic" alt="Image">
                </figure>
            </div>
            <div class="media-content">
                <div class="content">
                    <div v-if="type === 'href'" class="image is-centered">
                        <figure class="image is-offset-12" :href="message.link">
                            <img v-if="message.linkCover" :src="message.linkCover" :href="message.link" alt="Placeholder image">
                            <a :href="message.link">{{message.linkTitle}} | {{message.linkDescription}} </a>
                        </figure>
                    </div>
                    <div v-if="type === 'image'" class="image is-4"  width="100">
                        <a :href="message.link">
                            <img v-if="message.linkCover" :src="message.linkCover" alt="Placeholder image">
                        </a>
                    </div>
                    <div v-if="type === 'youtube'" class="image is-centered">
                        <you-tube :src="message.link"></you-tube>
                    </div>
                    <div class="card-content">
                        <div class="media">
                            <div class="media-content">
                                <p class="title is-4">{{message.author.name}}</p>
                            </div>
                        </div>
                        <div class="content">
                            <p>
                                {{message.text}}
                            </p>
                        </div>
                    </div>
                </div>
                <nav class="level is-mobile">
                    <div class="level-left">
                        <a v-if="message.author.name === profile.name" class="level-item" aria-label="Удалить">
                            <span class="icon is-small">
                                <i class="fas fa-trash" @click="del" aria-hidden="true"></i>
                            </span>
                        </a>
                    </div>
                </nav>
            </div>
        </article>
    </div>
</template>

<script>
    import YouTube from './YouTube.vue'
    import {mapActions, mapState} from "vuex";
    export default {
        name: 'Media',
        computed: mapState(['profile']),
        components: { YouTube },
        props: ['message', 'editMessage'],
        data() {
            return {
                type: 'href'
            }
        },
        beforeMount() {
            if (this.message.link.indexOf('youtu') > -1) {
                this.type = 'youtube'
            } else if (this.message.link.match(/\.(jpeg|jpg|gif|png)$/) !== null) {
                this.type = 'image'
            } else {
                this.type = 'href'
            }
        },
        methods: {
            ...mapActions(['removeMessageAction']),
            del() {
                this.$buefy.dialog.confirm({
                    title: 'Удаление сообщения',
                    message: 'Вы действительно хотите <b>удалить</b> данное сообщение? Это действие необратимо.',
                    confirmText: 'Удалить',
                    cancelText: 'Отмена',
                    type: 'is-danger',
                    hasIcon: true,
                    onConfirm: () => {
                        this.removeMessageAction(this.message)
                        this.$buefy.toast.open('Сообщение удалено')
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>