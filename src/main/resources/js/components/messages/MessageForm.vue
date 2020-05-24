<template>
    <div class="section">
        <b-field label="Сообщение" :label-position="labelPosition">
            <b-input placeholder="Напишите сообщение..." v-model="text" expanded rounded @keyup.enter.native="save"></b-input>
            <p class="control"><button class="button is-primary" @click="save">Отправить</button></p>
        </b-field>
    </div>
</template>

<script>
    import { mapActions } from 'vuex'

    export default {
        props: ['messageAttr'],
        data() {
            return {
                text: '',
                id: null,
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
            ...mapActions(['addMessageAction', 'updateMessageAction']),
            save() {
                const message = {
                    id: this.id,
                    text: this.text
                }
                if (this.id) {
                    this.updateMessageAction(message)
                } else {
                    this.addMessageAction(message)
                }
                this.text = ''
                this.id = null
                this.$buefy.toast.open({
                    duration:3000,
                    message:'Сообщение отправлено'
                })
            }
        }
    }
</script>

<style>

</style>