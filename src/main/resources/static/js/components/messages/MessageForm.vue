<template>
    <div class="section">
        <b-field label="Сообщение" :label-position="labelPosition">
            <b-input placeholder="Напишите сообщение..." v-model="text" rounded></b-input>
            <p class="control"><button class="button is-primary" @click="save">Сохранить</button></p>
        </b-field>
    </div>
</template>

<script>
    import { sendMessage } from 'util/socks'

    export default {
        props: ['messages', 'messageAttr'],
        data() {
            return {
                text: '',
                id: '',
                labelPosition: 'on-border'
            }
        },
        watch: {
            messageAttr(newVal, oldVal) {
                this.text = newVal.text
                this.id = newVal.id
            }
        },
        methods: {
            save() {
                sendMessage({id: this.id, text: this.text})
                this.text = ''
                this.id = ''
                this.$buefy.snackbar.open({
                    duration: 3000,
                    message: 'Сообщение было сохранено',
                    position: 'is-bottom-right',
                    queue: false,
                })
            }
        }
    }
</script>

<style scoped>

</style>