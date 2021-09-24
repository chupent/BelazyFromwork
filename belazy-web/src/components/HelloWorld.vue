<template>
  <h2>环境:{{ nodeName }}</h2>
  <h2>地址:{{ baseUrl }}</h2>
  <h2>消息:{{ msg }}</h2>
  <button @click="count++">count is: {{ count }}</button>
</template>

<script>

export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  data() {
    return {
      count: 0,
      nodeName:import.meta.env.VITE_APP_NAME,
      baseUrl:import.meta.env.VITE_APP_BASE_URL
    }
  }
}
</script>
