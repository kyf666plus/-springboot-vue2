const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 8080
  },
  css: {
    loaderOptions: {
      less: {
        // 支持内联 JavaScript
        javascriptEnabled: true,
      },
      sass: {
     
        implementation: require('sass'),
        sassOptions: {
          indentedSyntax: true
        },
      },
    }
  }
})
