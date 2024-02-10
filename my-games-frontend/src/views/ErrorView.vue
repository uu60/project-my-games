<template>
  <div class="phone_not_allowed">
    <div id="phone_not_allowed_content">
      <h1 v-cloak id="notice">{{ this.$route.params.errorMsg }}</h1>
      <span id="comment1">wjn真帅</span>
    </div>
  </div>
</template>

<script>
export default {
  mounted() {
    if (this.$route.params.errorMsg == null || this.$route.params.errorMsg === '') {
      this.$router.replace("/home");
    }
  }
}
</script>

<style>
.phone_not_allowed {
  text-align: center;
  width: 100%;
  height: 100%;
}

#phone_not_allowed_content {
  width: 100%;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

#notice {
  width: 100%;
}

#comment1 {
  color: gray;
}
</style>
