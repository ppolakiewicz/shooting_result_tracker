const PROXY_CONFIG = [{
  context: ["/api/v1/**"],
  target: "http://localhost:8881/",
  secure: false,
  changeOrigin: true
}];

module.exports = PROXY_CONFIG;
