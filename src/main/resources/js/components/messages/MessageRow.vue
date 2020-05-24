<template>
    <div v-if="!message.link" class="box">
        <article class="media">
            <div class="media-left">
                <figure class="image is-64x64">
                    <img :src="profile.userpic" alt="Image">
                </figure>
            </div>
            <div class="media-content">
                <div class="content">
                    <p>
                        <strong>{{profile.name}}</strong>
                        <br>
                        {{message.text}}
                    </p>
                </div>
                <nav class="level is-mobile">
                    <div class="level-left">
                        <a class="level-item" aria-label="Изменить">
                            <span class="icon is-small">
                                <i class="fas fa-edit" @click="edit" aria-hidden="true"></i>
                            </span>
                        </a>
                        <a class="level-item" aria-label="Удалить">
                            <span class="icon is-small">
                                <i class="fas fa-trash" @click="del" aria-hidden="true"></i>
                            </span>
                        </a>
                    </div>
                </nav>
            </div>
        </article>
    </div>
    <media v-else :message="message"></media>
</template>

<script>
    import {mapActions, mapState} from 'vuex'
    import Media from 'components/media/Media.vue'

    export default {
        computed: mapState(['profile']),
        props: ['message', 'editMessage'],
        components: { Media },
        methods: {
            ...mapActions(['removeMessageAction']),
            edit() {
                this.editMessage(this.message)
            },
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