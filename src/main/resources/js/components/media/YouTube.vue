<template>
    <figure class="image is-16by9">
        <iframe
                class="has-ratio"
                width="640"
                height="640"
                :src="targetSrc"
                frameborder="0"
                allowfullscreen
        >
        </iframe>
    </figure>
</template>

<script>
    export default {
        name: 'YouTube',
        props: ['src'],
        computed: {
            targetSrc() {
                let parts = this.src.split('/')
                return `https://www.youtube.com/embed/${parts[parts.length - 1]}`
            }
        }
    }
</script>

<style scoped>
</style>