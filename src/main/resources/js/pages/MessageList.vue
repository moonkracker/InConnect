<template>
    <section>
        <div class="container">
            <message-form :messageAttr="message" />
            <message-row v-for="message in sortedMessages"
                         :key="message.id"
                         :message="message"
                         :editMessage="editMessage"/>
            <loader></loader>
            <go-top
                    :right="150"
                    :bottom="150"
                    bg-color="#9143bd"
            >
            </go-top>
        </div>
    </section>
</template>

<script>
    import GoTop from '@inotom/vue-go-top';
    import { mapGetters } from 'vuex'
    import MessageRow from "components/messages/MessageRow.vue"
    import MessageForm from "components/messages/MessageForm.vue"
    import Loader from "components/Loader.vue";

    export default {
        components:{
            Loader,
            MessageRow,
            MessageForm,
            GoTop
        },
        data() {
            return {
                message: null
            }
        },
        computed: mapGetters(['sortedMessages']),
        methods: {
            editMessage(message) {
                this.message = message
            }
        }
    }
</script>

<style scoped>

</style>