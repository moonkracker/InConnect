function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }
    return -1;
}

var messageApi = Vue.resource('/api/messages{/id}');

Vue.component('message-form', {
    props: ['messages', 'messageAttr'],
    data: function() {
        return {
            text: '',
            id: ''
        }
    },
    watch: {
        messageAttr: function(newVal, oldVal) {
            this.text = newVal.text;
            this.id = newVal.id;
        }
    },
    template:
        '<div style="position: relative; width: 600px;">' +
            '<b-field>'+
                '<b-input placeholder="Add message" v-model="text"></b-input>'+
                '<p class="control">'+
                    '<button class="button is-primary" @click="save">Add</button>'+
                '</p>'+
            '</b-field>'+
        '</div>',
    methods: {
        save: function() {
            var message = { text: this.text };

            if (this.id) {
                messageApi.update({id: this.id}, message).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.messages, data.id);
                        this.messages.splice(index, 1, data);
                        this.text = ''
                        this.id = ''
                    })
                )
            } else {
                messageApi.save({}, message).then(result =>
                    result.json().then(data => {
                        this.messages.push(data);
                        this.text = ''
                    })
                )
            }
        }
    }
});

Vue.component('message-row', {
    props: ['message', 'editMethod', 'messages'],
    template:
        '<div>' +
        '<b-message>'+
            '<i>ID №{{ message.id }} | Message: {{ message.text }}'+
            '<span style="position: absolute; right: 0">' +
                '<b-button type="is-info" @click="edit">Edit</b-button>'+
                '<b-button type="is-danger" @click="del">Delete</b-button>'+
            '</span>' +
        '</b-message>'+
        '</div>',
    methods: {
        edit: function() {
            this.editMethod(this.message);
        },
        del: function() {
            messageApi.remove({id: this.message.id}).then(result => {
                if (result.ok) {
                    this.messages.splice(this.messages.indexOf(this.message), 1)
                }
            })
        }
    }
});

Vue.component('messages-list', {
    props: ['messages'],
    data: function() {
        return {
            message: null
        }
    },
    template:
        '<div style="position: relative; width: 900px;">' +
            '<message-form :messages="messages" :messageAttr="message" />' +
            '<message-row v-for="message in messages" :key="message.id" :message="message" ' +
            ':editMethod="editMethod" :messages="messages" />' +
        '</div>',
    methods: {
        editMethod: function(message) {
            this.message = message;
        }
    }
});

var app = new Vue({
    el: '#app',
    template:
        '<div>'+
            '<div v-if="!profile">Необходимо авторизоваться через <a href="/login">Google</a></div>'+
            '<div v-else>'+
                '<h2>Messages</h2>'+
                '<div>{{profile.name}}&nbsp;<a href="/logout">Выйти</a></div>'+
                '<messages-list :messages="messages" />'+
            '</div>'+
        '</div>',
    data: {
        messages: frontendData.messages,
        profile: frontendData.profile
    }
});