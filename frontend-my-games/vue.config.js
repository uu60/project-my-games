const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 80,
    allowedHosts: 'all'
  },
  assetsDir: 'static',
  parallel: false,
  publicPath: './'
})
