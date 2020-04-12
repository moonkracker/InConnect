<template>
    <div>
        <b-message>
            ID №{{ message.id }} | Сообщение: {{ message.text }}
            <span style="position: absolute; right: 50px">
                    <b-button type="is-info" @click="edit">Изменить</b-button>
                    <b-button type="is-danger" @click="del">Удалить</b-button>
            </span>
        </b-message>
    </div>
</template>

<script>
    export default {
        props: ['message', 'editMessage','deleteMessage' , 'messages'],

        methods: {
            edit() {
                this.editMessage(this.message)
            },
            del() {
                this.deleteMessage(this.message)
                this.$buefy.snackbar.open({
                    duration: 3000,
                    type: 'is-danger',
                    message: 'Сообщение было удалено',
                    position: 'is-bottom-right',
                    queue: false,
                })
            }
        }
    }
</script>

<style scoped>

</style>